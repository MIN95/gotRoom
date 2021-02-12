package com.pro.mxpro.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		mav.setViewName(dir+"/myMsg");
		
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
		String targetUserid = request.getParameter("targetUserid");
		HttpSession session = request.getSession(false);
		String nickname = (String) session.getAttribute(SessionNames.LOGIN);
		int id = messageService.getUserId(nickname);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(dir+"/msgRoom");
		mav.addObject("id",id);
		mav.addObject("targetUserid", targetUserid);
		mav.addObject("mymsgId",mymsgId);
		int count = messageService.countMessage(mymsgId);
		mav.addObject("count",count);
		return mav;
	}
	
	@RequestMapping(value = "/user/getMsg", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
	public ModelAndView getMoreMsg(@RequestBody String form) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName(dir+"/chatBubble");
		
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> map = om.readValue(form,new TypeReference<Map<String,Object>>(){});
		List<MessageVO> list = messageService.getMessage(map);
		mav.addObject("id",map.get("id"));
		mav.addObject("targetUserid",map.get("targetUserid"));
		mav.addObject("list", list);
		return mav;
	}

}
