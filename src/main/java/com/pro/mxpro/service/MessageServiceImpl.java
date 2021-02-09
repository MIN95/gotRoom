package com.pro.mxpro.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.pro.mxpro.dao.MessageDAO;
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
	public List<MessageVO> getMessage(int mymsgId) throws Exception {
		return messageDAO.getMessage(mymsgId);
	}
	
	@Override
	public int chkMymsg(MessageVO messageVO) throws Exception {
		int mymsgId = messageDAO.chkMymsg(messageVO);
		if(mymsgId==0) {
			messageDAO.newMymsg(messageVO);
		}
		return messageDAO.chkMymsg(messageVO);
	}

	@Override
	public List<MessageVO> getAllMsg(int id) throws Exception {
		return messageDAO.getAllMsg(id);
	}


}
