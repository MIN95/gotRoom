package com.pro.mxpro.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pro.mxpro.vo.UserVO;

@Repository("userDAO")
public class UserDAO { 
	
	@Resource
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.pro.mxpro.mybatis.UserMapper";

	public int userCheck(UserVO userVO) {
		return sqlSession.selectOne(NAMESPACE + ".userCheck",userVO);
	}

	public UserVO loginChek(UserVO userVO) {
		return sqlSession.selectOne(NAMESPACE + ".loginChek",userVO);
	}
	
	public void createUser(UserVO userVO) {
		sqlSession.insert(NAMESPACE + ".createUser",userVO);
	}

	
}
