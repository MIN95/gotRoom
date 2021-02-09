package com.pro.mxpro.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.pro.mxpro.commons.SessionNames;
import com.pro.mxpro.service.MessageService;
import com.pro.mxpro.vo.MessageVO;

@Controller
public class MessageController {
	
	@Resource
	private MessageService messageService;
	
	String dir = "user";
	
	@RequestMapping(value = "/user/mymsg")
	public ModelAndView myMsg(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/myMsg");
		
		HttpSession session = request.getSession(false);
		String nickname = (String) session.getAttribute(SessionNames.LOGIN);
		int id = messageService.getUserId(nickname);
		List<MessageVO> list = messageService.getAllMsg(id);
		mav.addObject("list",list);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/checkMsg", method=RequestMethod.POST)
	public int checkMsg(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession(false);
		String nickname = (String) session.getAttribute(SessionNames.LOGIN);
		String targetUserid = request.getParameter("targetUserid");
		if(nickname == targetUserid) {
			return 0;
		}
		MessageVO messageVO = new MessageVO();
		messageVO.setParticipant1(messageService.getUserId(nickname));
		messageVO.setParticipant2(messageService.getUserId(targetUserid));
		messageVO.setTargetUserid(targetUserid);
		
		int mymsgId = messageService.chkMymsg(messageVO);
		return mymsgId;
	}
	
	@RequestMapping(value = "/user/msgRoom/{mymsgId}")
	public ModelAndView getMessage(HttpServletRequest request,@PathVariable Integer mymsgId)throws Exception{
		ModelAndView mav = new ModelAndView();
		String targetUserid = request.getParameter("targetUserid");
		mav.addObject("targetUserid", targetUserid);
		mav.addObject("mymsgId",mymsgId);
		
		List<MessageVO> list = messageService.getMessage(mymsgId);
		mav.addObject("list", list);
		mav.setViewName(dir+"/msgRoom");
		
		return mav;
	}

}
