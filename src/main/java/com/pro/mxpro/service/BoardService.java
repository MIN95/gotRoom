package com.pro.mxpro.service;

import java.util.List;
import java.util.Map;

import com.pro.mxpro.commons.Criteria;
import com.pro.mxpro.vo.BoardVO;

public interface BoardService {
	
	int countBoardList(Criteria cri) throws Exception;
	
	List<BoardVO> getBoardList(Criteria cri) throws Exception;

	Map<String, Object> getContents(int no) throws Exception;
	
	void updateView(int no) throws Exception;

	void deleteContents(Integer no) throws Exception;
}
