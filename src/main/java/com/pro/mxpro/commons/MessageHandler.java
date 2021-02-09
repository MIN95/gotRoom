package com.pro.mxpro.commons;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.pro.mxpro.vo.ChatVO;

@Controller
public class MessageHandler {
	private final SimpMessageSendingOperations template;

    @Autowired
    public MessageHandler(SimpMessagingTemplate template) {
    	this.template = template;
	}
	
	@MessageMapping("/chat")
	public void message(ChatVO chatVO) throws Exception{
		Date time = new Date();
		chatVO.setTime(time);
		template.convertAndSend("/topic/messages/"+chatVO.getMymsgId(),chatVO);
	}
	
	@MessageMapping("/groupchat")
	public void groupMessage(ChatVO chatVO) throws Exception{
		template.convertAndSend("/topic/groupmessages",chatVO);
	}
}
