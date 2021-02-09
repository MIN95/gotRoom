package com.pro.mxpro.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pro.mxpro.dao.UserDAO;
import com.pro.mxpro.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	UserDAO userDAO;
	
	//���̹�
	@Override
	public void userCheck(String nemail, String snsType) throws Exception {
		UserVO userVO = new UserVO();
        userVO.setUserName(nemail);
        userVO.setSnsType(snsType);
        int check = 0;
		check = userDAO.userCheck(userVO);
		
		//ù �α��ν�, �ش���̹����� ���
		if(check==0) {					
            userVO.setPassword("0000");
            userVO.setNickname(nemail);
            userVO.setEmail(nemail);
            userDAO.createUser(userVO);
        }
	}
	//�Ϲ�
	@Override
	public UserVO loginChek(UserVO userVO) throws Exception {
		return userDAO.loginChek(userVO); 
	}

}
