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
	 
	//�˶� on/off
	//���� ���� �� ����Ʈ�� on->��� �߰�
	//������ �˶� �︮�°� ����Ʈ->��� �߰�
//	@ResponseBody
//	@RequestMapping(value="/notification/{loction}/{}")
}
