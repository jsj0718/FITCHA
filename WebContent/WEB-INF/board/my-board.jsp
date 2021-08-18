<%@page import="com.fitcha.model.vo.MyBoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.fitcha.controller.Pagination"%>
<%@page import="com.fitcha.model.dao.MyBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%

String id = (String)session.getAttribute("id");
MyBoardDAO bdao = new MyBoardDAO();
//List<BoardVO> blist = bdao.selectBoardList();

//페이징 처리 적용
// String keyword = (String)session.getAttribute("keyword");
// int genreId = (int)session.getAttribute("genreId");
String curPage =request.getParameter("curpage");
if(curPage == null){
	curPage = "1";
}
int curPageInt = Integer.parseInt(curPage);
int totalContent = bdao.selectBoardCnt(id);
Pagination pagination = new Pagination(curPageInt,totalContent, 6);

//1page내에 보여줘야하는 게시물의 첫번째 rownum
int start = curPageInt*pagination.getContentCnt() - (pagination.getContentCnt()-1);
//한페이지 내에 보여줘야하는 게시물의 마지막 rownum
int end = curPageInt*pagination.getContentCnt();
	
List<MyBoardVO> blist = bdao.myBoardListPage(id, start, end);

// List<BoardVO> pblist = bdao.mySelectListPage(id, keyword, genreId, start, end);


 

%>

<!DOCTYPE html>
<c:set var="page" value="<%=pagination %>"/>
<c:set var="blist" value="<%=blist %>"/>
<%-- <c:set var="pblist" value="<%=pblist %>"/> --%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet"  href="${pageContext.request.contextPath }/css/board/my-board.css">
<script src="${pageContext.request.contextPath }/js/board/my-board.js"></script>
<%--   <script src="${pageContext.request.contextPath}/js/board/review-board.js"></script> --%>
  <script type="text/javascript">
  
//   window.onload= function(){
// 		var totalData ;	// 총 데이터 수
// 		var dataPerPage = 5;	
// 		var pageCount = 10;		
// 		var currentPage=1;
// 		var genre= document.getElementById("genre");
// 		var recommend = document.getElementById("recommend");
// 		genre.onchange = function(){
// 			changeSelection(totalData, dataPerPage, pageCount, currentPage);
// //	 		paging(totalData, dataPerPage, pageCount, currentPage);
// 		}
// 		recommend.onchange = function(){
// 			changeSelection(totalData, dataPerPage, pageCount, currentPage);
// //	 		paging(totalData, dataPerPage, pageCount, currentPage);
// 		}
//   }	
	//썸네일 클릭했을때 리뷰디테일 페이지로 이동
	function thumbnail(boardId) {
		location.href = "${pageContext.request.contextPath}/review_board_view?boardId="+ boardId;
	}

</script>
  


<body>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<fieldset>

<header>
 	<div class="netflixLogo">
        <a id="logo" href="#home"><img src="https://github.com/carlosavilae/Netflix-Clone/blob/master/img/logo.PNG?raw=true" alt="Logo Image"></a>
     </div>   
     <div id="title">
     	<h1>Fitcha님의 PAGE </h1>
     
     </div>
      <nav class="main-nav">                
        <a href="#recently">Recently Added</a>
        <a href="#best">Best</a>
        <a href="#">review</a>
        <form action="logout.do" method="get">
        	<button>logout</button>
        </form>
        <form action="main-movie" method="get">
        	<button>메인화면</button>
        </form>
      </nav> 
 
    <div id="search-box">
       <input type="text" placeholder="Search" id="searchBox" name="searchBox">
       <button id="search_btn" onclick="searchInput()">검색</button>
    </div>
    
    <div id="select_box">
   		 <select id="genre" name="genre" onchange="myChangeSelection()">
      			<option value="0">모든 장르</option>
      			<option value="11">코미디</option>
       			<option value="1">드라마</option>
      			<option value="5">로맨스</option>
      			<option value="19">액션</option>
     			<option value="18">SF</option>
      			<option value="15">애니메이션</option>
     			<option value="4">공포</option>
       			<option value="7">스릴러</option>
      			<option value="6">모험</option>
      			<option value="2">판타지</option>
      			<option value="8">느와르</option>
      			<option value="10">다큐멘터리</option>
      			<option value="12">가족</option>
      			<option value="17">뮤지컬</option>
      			<option value="13">미스터리</option>
     			<option value="14">전쟁</option>
     			<option value="16">범죄</option>
     	</select>
     
     	<select id="recommend" name="recommend" onchange="myChangeSelection()">
       			<option value="1">최신리뷰 순</option>
       			<option value="2">추천 순</option>
       			<option value="3">평균별점 순</option>
       			<option value="4">러닝타임 짧은 순</option>
     	</select>
    
    </div>

</header>

<section>

<!-- paging처리를 적용한 첫화면 최신 업데이트 순  -->
<div>
   <h2 id="recently">Recently Added</h2>
		<div id="review_box">
     		<div class="row">
     			 <div class="col-sm-6 col-md-4">
            		<div id="searchImg"> 
            		<c:forEach var="bvo" items="${blist }">
            			
            			<div id="arrange">
            				<img src="${bvo.poster}" alt="포스터" class="json_box" onclick="thumbnail(${bvo.boardId})">
            				<div id="caption"><h4 id="review_title">
            				<a href="#" class="btn_btn_primary" role="button">
            							<c:choose>
          								<c:when test="${fn:length(bvo.title) > 14}">
            							<c:out value="${fn:substring(bvo.title,0,13)}"/>….
          				 				</c:when>
           								<c:otherwise>
          				 				<c:out value="${bvo.title}"/>
          				 				</c:otherwise>
        				  				</c:choose> </a></h4>'
            				</div></div>
            		</c:forEach>
            		</div>
         		</div>
      		</div>
   		
		<ul id="pagination">
			<c:if test="${page.prevBtn }">
				<li><a href="myBoardView?curpage=${page.startPage-1 }">이전</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
				<c:choose>
					<c:when test="${i eq param.curpage }">
						<li>${i }</li>
					</c:when>
					<c:otherwise>
						<li><a href="myBoardView?curpage=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		 	
		 	<c:if test="${page.nextBtn }">
				<li><a href="myBoardView?curpage=${page.endPage+1 }">다음</a></li>
			</c:if>
			</ul>

		
   </div>   
  </div>

<!-- <div> -->
<!--  <h2 id="bestTitle">Best</h2> -->
  
<!--    <div id="review_box"> -->
<!--       <div class="row"> -->
<!--          <div class="col-sm-6 col-md-4"> -->
<!--             <div id="best"> </div> -->
<!--          </div> -->
<!--       </div> -->
      
<!--    </div>    -->
<!-- </div> -->
  <h2 id="search">SEARCH</h2>
     <div id="review_box">
      <div class="row">
         <div class="col-sm-6 col-md-4">
            <div id="searchBoxImg"> </div>
         </div>
      </div>
   </div>   
  <h2 id="genreTitle">SELECT BOX</h2>
     <div id="review_box">
      <div class="row">
         <div class="col-sm-6 col-md-4">
            <div id="genreBox"> </div>
         </div>
      </div>
   </div>
<!--  <div class="paginate" ></div>   -->
 
   

   
</section>
<footer>
  <p>&copy 1997-2018 Netflix, Inc.</p>
      <p>Carlos Avila &copy 2018</p>
</footer>

</fieldset>
</body>
</html>