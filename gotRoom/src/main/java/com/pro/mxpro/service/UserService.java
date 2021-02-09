package com.pro.mxpro.service;

import com.pro.mxpro.vo.UserVO;

public interface UserService {

	void userCheck(String nemail, String snsType) throws Exception;

	UserVO loginChek(UserVO userVO) throws Exception;

}
