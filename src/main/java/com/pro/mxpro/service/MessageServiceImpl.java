package com.pro.mxpro.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.pro.mxpro.dao.MessageDAO;
import com.pro.mxpro.vo.ChatVO;
import com.pro.mxpro.vo.MessageVO;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	@Resource
	private MessageDAO messageDAO;
	
	@Override
	public int getUserId(String nickname) throws Exception {
		return messageDAO.getUserId(nickname);
	}
	
	@Override
	public int countMessage(Integer mymsgId) throws Exception {
		return messageDAO.countMessage(mymsgId);
	}
	
	@Override
	public List<MessageVO> getMessage(Map<String, Object> map) throws Exception {
		return messageDAO.getMessage(map);
	}
	
	@Override
	public int chkMymsg(MessageVO messageVO) throws Exception {
		int mymsgId = messageDAO.chkMymsg(messageVO);
		if(mymsgId==0) {
			messageVO.setParticipant2(0);
			messageDAO.newMymsg(messageVO);
		}
		return messageDAO.chkMymsg(messageVO);
	}

	@Override
	public List<MessageVO> getAllMsg(int id) throws Exception {
		return messageDAO.getAllMsg(id);
	}

	@Override
	@Async("threadPoolTaskExecutor")
	public void insertMessge(ChatVO chatVO) throws Exception {
		MessageVO messageVO = new MessageVO();
		messageVO.setMymsgId(chatVO.getMymsgId());
		messageVO.setMsgLog(chatVO.getMessage());
		messageVO.setSpeaker(messageDAO.getUserId(chatVO.getName()));
		messageDAO.insertMessage(messageVO);
	}

}
