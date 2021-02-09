package com.pro.mxpro.service;

import java.util.List;

import com.pro.mxpro.vo.MessageVO;

public interface MessageService {

	int getUserId(String userName) throws Exception;

	List<MessageVO> getMessage(int mymsgId) throws Exception;

	List<MessageVO> getAllMsg(int id) throws Exception;

	int chkMymsg(MessageVO messageVO) throws Exception;

}
