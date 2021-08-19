package com.fitcha.controller;

public class Pagination {
	
	private int curPage;	//현재 나의 페이지 - url통해서 받아옴
	private int totalPage; //전체 페이지 - 
	private int totalContent; //전체 게시물 수 - select count(*) 받아옴
	private int contentCnt=6;		//한 페이지 내에 보여줄 게시물 수 - 직접 값 대입
	private int pageCnt=5;	//한 블럭에 보여줄 페이지 수 - 직접 값 대입
	private int curBlock;	//현재 블럭
	private int startBlock;	//시작블럭
	private int endBlock;	//마지막 블럭
	private int startPage;	//시작 페이지
	private int endPage;	//마지막 페이지
	private boolean prevBtn;	//이전 버튼
	private boolean nextBtn;	//다음 버튼
	
	public Pagination(int curPage, int totalContent, int contentCnt) {
		this.curPage = curPage;
		this.totalContent = totalContent;
		this.contentCnt = contentCnt;
		
		//전체 페이지 계산
		//Math.ceil -> 소수점 무조건 반올림
		totalPage = (int)Math.ceil(totalContent / (float)contentCnt);
		
		//현재 페이지 블록 계산
		curBlock = (int)Math.ceil(curPage / (double)pageCnt);
		
		//마지막 페이지 블록 계산
		endBlock = (int)Math.ceil(totalContent / (double)(pageCnt*contentCnt));
		
		//블록의 첫페이지
		startPage = (curBlock * pageCnt) - (pageCnt - 1);
		
		//블록의 마지막페이지
		if(endBlock == curBlock) {
			endPage = totalPage;
		}else {
			endPage = startPage+(pageCnt-1);
		}
		
		if(totalPage>0 && totalPage < pageCnt+1) {
			//전체 게시물이 25개 이하
			prevBtn = false;
			nextBtn = false;
		}else if(curPage > 0 && curPage < (pageCnt+1)) {
			//게시물이 25개 이상, 현재 블럭이 1블럭일때
			prevBtn = false;
			nextBtn = true;
		}else if(endBlock == curBlock) {
			//마지막 블럭이 현재 블럭일때
			prevBtn = true;
			nextBtn = false;
		}else {
			prevBtn = true;
			nextBtn = true;
		}
	}
	
	
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalContent() {
		return totalContent;
	}
	public void setTotalContent(int totalContent) {
		this.totalContent = totalContent;
	}
	public int getContentCnt() {
		return contentCnt;
	}
	public void setContentCnt(int contentCnt) {
		this.contentCnt = contentCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getStartBlock() {
		return startBlock;
	}
	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}
	public int getEndBlock() {
		return endBlock;
	}
	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrevBtn() {
		return prevBtn;
	}
	public void setPrevBtn(boolean prevBtn) {
		this.prevBtn = prevBtn;
	}
	public boolean isNextBtn() {
		return nextBtn;
	}
	public void setNextBtn(boolean nextBtn) {
		this.nextBtn = nextBtn;
	}
	
	
	
	
	
	
	
	
	
}
