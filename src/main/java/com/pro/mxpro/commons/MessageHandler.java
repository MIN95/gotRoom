package com.pro.mxpro.commons;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import com.pro.mxpro.service.MessageService;
import com.pro.mxpro.vo.ChatVO;
import com.pro.mxpro.vo.MessageVO;

@EnableAsync
@Controller
public class MessageHandler {
	private final SimpMessageSendingOperations template;
	
	@Resource
	private MessageService messageService;
	
    @Autowired
    public MessageHandler(SimpMessagingTemplate template) {
    	this.template = template;
	}  
     
	@MessageMapping("/chat")
	public void message(ChatVO chatVO,SimpMessageHeaderAccessor headerAccessor) throws Exception{
		String sessionId = headerAccessor.getSessionId(); // Session ID
	    Map<String, Object> attrs = headerAccessor.getSessionAttributes(); // Session Attributes
		System.out.println("sessionId>>  "+sessionId);
		System.out.println("attrs>>  "+attrs);
		
		Date time = new Date();
		chatVO.setTime(time);		
		template.convertAndSend("/topic/messages/"+chatVO.getMymsgId(),chatVO);
		messageService.insertMessge(chatVO);
	}
	
	@MessageMapping("/groupchat")
	public void groupMessage(ChatVO chatVO) throws Exception{
		template.convertAndSend("/topic/groupmessages",chatVO);
	}
	
}
