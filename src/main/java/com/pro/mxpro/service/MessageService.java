package com.pro.mxpro.service;

import java.util.List;
import java.util.Map;

import com.pro.mxpro.vo.ChatVO;
import com.pro.mxpro.vo.MessageVO;

public interface MessageService {

	int getUserId(String userName) throws Exception;

	List<MessageVO> getMessage(Map<String, Object> map) throws Exception;

	List<MessageVO> getAllMsg(int id) throws Exception;

	int chkMymsg(MessageVO messageVO) throws Exception;

	void insertMessge(ChatVO chatVO) throws Exception;

	int countMessage(Integer mymsgId) throws Exception;

}
