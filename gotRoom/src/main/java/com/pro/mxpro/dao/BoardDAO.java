package com.pro.mxpro.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pro.mxpro.commons.Criteria;
import com.pro.mxpro.vo.BoardVO;

@Repository("boardDAO")
public class BoardDAO {
	
	@Resource
	SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.pro.mxpro.mybatis.BoardMapper";
	
	public int  countBoardList(Criteria cri) {
		return sqlSession.selectOne(NAMESPACE + ".countBoardList",cri);
	}
	
	public List<BoardVO> getBoardList(Criteria cri) {
		return sqlSession.selectList(NAMESPACE + ".getBoardList",cri);
	}

	public Map<String, Object> getContents(int no) {
		return sqlSession.selectOne(NAMESPACE + ".getContents",no);
	}
	
	public void updateView(int no) {
		sqlSession.update(NAMESPACE + ".updateView",no);
	}

	public void deleteContents(Integer no) {
		sqlSession.delete(NAMESPACE  + ".deleteContents",no);
	}
}
