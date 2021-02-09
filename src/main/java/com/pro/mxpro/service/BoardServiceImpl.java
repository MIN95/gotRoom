package com.pro.mxpro.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.pro.mxpro.commons.Criteria;
import com.pro.mxpro.dao.BoardDAO;
import com.pro.mxpro.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource
	BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoardList(Criteria cri) throws Exception{
		return boardDAO.getBoardList(cri);
	}

	@Override
	public int countBoardList(Criteria cri) throws Exception {
		return boardDAO.countBoardList(cri);
	}

	@Override
	public Map<String, Object> getContents(int no) throws Exception {
		return boardDAO.getContents(no);
	}

	@Override
	public void updateView(int no) throws Exception {
		boardDAO.updateView(no);
	}

	@Override
	public void deleteContents(Integer no) throws Exception {
		boardDAO.deleteContents(no);
	}

}
