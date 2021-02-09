package com.pro.mxpro.vo;

import java.util.Date;

public class BoardVO {
	 private int boardNo;
	 private int view;
	 private String writer;
	 private String title;
	 private String contents;
	 private Date writeDate;
	 public BoardVO() {
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", view=" + view + ", writer=" + writer + ", title=" + title
				+ ", contents=" + contents + ", writeDate=" + writeDate + "]";
	}
}
