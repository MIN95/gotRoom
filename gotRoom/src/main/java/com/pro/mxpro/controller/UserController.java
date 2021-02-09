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
    
    //로그인 페이지(일반로그인,네이버로그인)
	@RequestMapping(value = "/login")
	public String loginPage(Model model, HttpSession session) throws Exception{
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        //네이버 
        model.addAttribute("url", naverAuthUrl);
 
        /* 생성한 인증 URL을 View로 전달 */
		return dir+"/login";
	}
	
	//일반로그인 
	@RequestMapping(value = "/login/default" , method =RequestMethod.POST)
	public String loginChek(UserVO userVO,Model model) throws Exception{
		UserVO vo = userService.loginChek(userVO); 
        int checkNo = vo.getCnt();
		String nickname = vo.getNickname();
        if(checkNo == 1) { 
        	model.addAttribute("nickname",nickname);
        	return "redirect:/";
        }else {
        	String warning = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
        	model.addAttribute("warning",warning);
        	return dir+"/login";
        }
	}
	
	//네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/login/naver", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(@RequestParam String code, @RequestParam String state, HttpSession session,Model model)
            throws Exception {
        OAuth2AccessToken oauthToken; 
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        //회원정보 파싱
        JSONObject jObject = new JSONObject(apiResult);
        JSONObject response = jObject.getJSONObject("response");
        String nemail = (String) response.get("email");
        String snsType = "naver";
        
        //가입여부 확인 후 DB에 네이버로그인계정 등록
        userService.userCheck(nemail,snsType);
        
        model.addAttribute("nemail",nemail);
        model.addAttribute("nickname",nemail);
        /* 네이버 로그인 성공 페이지 View 호출 */
        return "redirect:/";
    }
    
    //로그아웃
    @RequestMapping(value = "/logout")
	public String logiout(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        session.invalidate();

		return "redirect:/";
	}
}
