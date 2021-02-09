package com.pro.mxpro.controller;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pro.mxpro.commons.MessageHandler;
import com.pro.mxpro.service.NotifyService;

@Controller
public class NotifyController {
	
	@Resource
	private NotifyService notifyService;
	
	private static Logger logger = LoggerFactory.getLogger(MessageHandler.class);
	 
	//알람 on/off
	//글은 썼을 때 디폴트가 on->디비에 추가
	//쪽지도 알람 울리는게 디폴트->디비에 추가
//	@ResponseBody
//	@RequestMapping(value="/notification/{loction}/{}")
}
