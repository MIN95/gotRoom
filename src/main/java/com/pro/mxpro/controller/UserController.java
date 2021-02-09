package com.pro.mxpro.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.pro.mxpro.commons.NaverLoginBO;
import com.pro.mxpro.service.UserService;
import com.pro.mxpro.vo.UserVO;
import org.json.JSONObject;

@Controller
public class UserController {
	
	@Resource 
	private UserService userService;
	
	/* NaverLoginBO */
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;
    
    @Resource
    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }
    
    String dir = "user";
    
    //�α��� ������(�Ϲݷα���,���̹��α���)
	@RequestMapping(value = "/login")
	public String loginPage(Model model, HttpSession session) throws Exception{
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        //���̹� 
        model.addAttribute("url", naverAuthUrl);
 
        /* ������ ���� URL�� View�� ���� */
		return dir+"/login";
	}
	
	//�Ϲݷα��� 
	@RequestMapping(value = "/login/default" , method =RequestMethod.POST)
	public String loginChek(UserVO userVO,Model model) throws Exception{
		UserVO vo = userService.loginChek(userVO); 
        int checkNo = vo.getCnt();
		String nickname = vo.getNickname();
        if(checkNo == 1) { 
        	model.addAttribute("nickname",nickname);
        	return "redirect:/";
        }else {
        	String warning = "�������� ���� ���̵��̰ų�, �߸��� ��й�ȣ�Դϴ�.";
        	model.addAttribute("warning",warning);
        	return dir+"/login";
        }
	}
	
	//���̹� �α��� ������ callbackȣ�� �޼ҵ�
    @RequestMapping(value = "/login/naver", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(@RequestParam String code, @RequestParam String state, HttpSession session,Model model)
            throws Exception {
        OAuth2AccessToken oauthToken; 
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //�α��� ����� ������ �о�´�.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        //ȸ������ �Ľ�
        JSONObject jObject = new JSONObject(apiResult);
        JSONObject response = jObject.getJSONObject("response");
        String nemail = (String) response.get("email");
        String snsType = "naver";
        
        //���Կ��� Ȯ�� �� DB�� ���̹��α��ΰ��� ���
        userService.userCheck(nemail,snsType);
        
        model.addAttribute("nemail",nemail);
        model.addAttribute("nickname",nemail);
        /* ���̹� �α��� ���� ������ View ȣ�� */
        return "redirect:/";
    }
    
    //�α׾ƿ�
    @RequestMapping(value = "/logout")
	public String logiout(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        session.invalidate();

		return "redirect:/";
	}
}
