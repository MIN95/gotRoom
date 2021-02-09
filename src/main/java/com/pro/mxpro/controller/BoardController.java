package com.pro.mxpro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.util.StringUtils;
import com.pro.mxpro.commons.Criteria;
import com.pro.mxpro.commons.PageMaker;
import com.pro.mxpro.service.BoardService;
import com.pro.mxpro.vo.BoardVO;

@Controller
public class BoardController {
	
	@Resource
	BoardService boardService;
	
	@Inject
	PageMaker pageMaker;
	
	String dir = "board";
	
	int now = 1;
	
	@RequestMapping(value = {"/board/list", "/board/list/{page}"})
	public ModelAndView getBoardList(@PathVariable(required = false) Integer page,Criteria cri,HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName(dir+"/list");
		//검색
		Map<String, Object> map = new HashMap<String, Object>();
		String search = request.getParameter("search");
		String selectOption = request.getParameter("selectOption");
		if(search != null) {
			cri.setSelectOption(selectOption);
			cri.setSearch(search);
			map.put("selectOption",selectOption);
			map.put("search",search);
			mav.addObject("map",map);
			
		}
		//페이징
		if(page == null) {
			page = 1;
		}
		now = page;
		cri.setPage(page);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.countBoardList(cri));
		mav.addObject("pageMaker",pageMaker);
		//결과출력
		List<BoardVO> list = boardService.getBoardList(cri);	
		mav.addObject("list",list);
		return mav;
	}
	
	@RequestMapping(value = "/board/detail/{no}")
	public ModelAndView getContents(@PathVariable(required = false) Integer no,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName(dir+"/detail");
		
		// 저장된 쿠키 불러오기 
		Cookie cookies[] = request.getCookies(); 
		Map<Object,Object> mapCookie = new HashMap<Object,Object>(); 
		if(request.getCookies() != null){
			for (int i = 0; i < cookies.length; i++) { 
				Cookie obj = cookies[i]; 
				mapCookie.put(obj.getName(),obj.getValue()); 
			} 
		} 
		// 저장된 쿠키중에 view 만 불러오기 
		String cookieReadView = (String) mapCookie.get("view"); 
		// 저장될 새로운 쿠키값 생성 
		String newCookieReadView = "|" + no; 
		// 저장된 쿠키에 새로운 쿠키값이 존재하는 지 검사 
		if ( StringUtils.indexOfIgnoreCase(cookieReadView,newCookieReadView) == -1 ) { 
			// 없을 경우 쿠키 생성 
			Cookie cookie = new Cookie("view",cookieReadView + newCookieReadView); 
			//cookie.setMaxAge(1000); // 초단위 
			response.addCookie(cookie); 
			// 조회수 업데이트 
			boardService.updateView(no); 
		} 
		if(no == null) {
			no = 1;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map = boardService.getContents(no);
		mav.addObject("map",map);
		return mav;
	}
	
	@RequestMapping(value = "/board/delete/{no}")
	public ModelAndView deleteContents(@PathVariable(required = false) Integer no) throws Exception{
		//디테일 들어왔을때 디테일 들어오기 전 주소 갖고와서
		ModelAndView mav = new ModelAndView();
		int page= now;
		String para= pageMaker.makeSearch();
		if(para==null) {
			para = "";
		}
		mav.setViewName("redirect:/board/list/"+page+para);
		
		boardService.deleteContents(no);
		return mav;

	}
}
