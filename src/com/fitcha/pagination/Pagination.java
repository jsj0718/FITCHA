package com.fitcha.pagination;

public class Pagination {
    private int contentCnt = 5; // 한 페이지 당 게시글 수 - 직접 값 대입
    private int pageCnt = 5;    // 한 블럭 당 페이지 수 - 직접 값 대입
    private int curPage;    // 현재 페이지    - url 통해서 받아옴
    private int curBlock;   // 현재 블럭
    private int totalPage;  // 총 페이지 수
    private int totalContent;   // 총 게시물 수 - select count(*) 받아옴
    private int startBlock; // 시작 블럭
    private int endBlock;   // 끝 블럭
    private int startPage;  // 시작 페이지
    private int endPage;    // 끝 페이지
    private boolean prevBtn;    // 이전 버튼
    private boolean nextBtn;    // 다음 버튼
    
    public Pagination(int curPage, int totalContent, int contentCnt) {
        this.curPage = curPage; // 현재 페이지
        this.totalContent = totalContent;   // 총 게시물 수
        this.contentCnt = contentCnt;   // 한 페이지 당 게시글 수
        
        totalPage = (int) Math.ceil((double) totalContent / contentCnt);
        curBlock = (int) Math.ceil((double) curPage / pageCnt);
        endBlock = (int) Math.ceil((double) totalContent / (contentCnt * pageCnt));
        
        startPage = (curBlock * pageCnt) - (pageCnt - 1);
        
        if (endBlock == curBlock) {
            endPage = totalPage;
        } else {
            endPage = startPage + (pageCnt - 1);
        }
            
        if (totalPage > 0 && totalPage < (pageCnt + 1)) {   // 전체 게시물 개수가 25개 이하
            prevBtn = false;
            nextBtn = false;
        } else if (curPage > 0 && curPage < (pageCnt + 1)) {    // 전체 게시물 개수가 25개 이상 + 현재 블록이 1블록
            prevBtn = false;
            nextBtn = true;
        } else if (endBlock == curBlock) {                  // 마지막 블럭이 현재 블럭과 동일할 때
            prevBtn = true;
            nextBtn = false;
        } else {
            prevBtn = true;
            nextBtn = true;
        }
        
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
    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        this.curPage = curPage;
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
