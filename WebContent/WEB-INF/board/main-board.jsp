<%@page import="com.fitcha.model.vo.MainBoardVO"%>
<%@page import="com.fitcha.controller.Pagination"%>
<%@page import="java.util.List"%>
<%@page import="com.fitcha.model.dao.MainBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
MainBoardDAO bdao = new MainBoardDAO();
//List<BoardVO> blist = bdao.selectBoardList();

//페이징 처리 적용
String curPage = request.getParameter("curpage");
if (curPage == null) {
	curPage = "1";
}
int curPageInt = Integer.parseInt(curPage);
int totalContent = bdao.selectBoardCnt();

Pagination pagination = new Pagination(curPageInt, totalContent, 5);

//1page내에 보여줘야하는 게시물의 첫번째 rownum
int start = curPageInt * pagination.getContentCnt() - (pagination.getContentCnt() - 1);
//한페이지 내에 보여줘야하는 게시물의 마지막 rownum
int end = curPageInt * pagination.getContentCnt();

List<MainBoardVO> blist = bdao.selectBoardPage(start, end);
%>

<!DOCTYPE html>
<c:set var="page" value="<%=pagination%>" />
<c:set var="blist" value="<%=blist%>" />
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FitCha</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/board/main-board.css">
<link rel="shortcut icon" href="#">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="${pageContext.request.contextPath}/js/board/main-board.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"
	integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9"
	crossorigin="anonymous">
	
	
</script>
<script type="text/javascript">
	//썸네일 클릭했을때 리뷰디테일 페이지로 이동
	function thumbnail(boardId) {
		//조회수증가
		
		location.href = "${pageContext.request.contextPath}/review_board_view?boardId="+ boardId;
		
		
		
	}

</script>


</head>
<body>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>




	<%-- 	<form action="${pageContext.request.contextPath }/main-board" method="post" > --%>
	<div class="wrapper">

		<!-- HEADER -->
		<header style="position: relative;">

			<nav class="sub-nav">
				<a id="login" href="#">로그인</a> <a href="#">전체 게시판</a> <a href="#">나의
					게시판</a> <a href="#">캘린더</a> <a href="#">로그아웃</a>
			</nav>

			<div class="netflixLogo">
				<a id="logo" href="#home"><img src="img/fitcha.png"
					alt="Logo Image"></a>
			</div>
		</header>
		<!-- END OF HEADER -->
		<div id="best">
			<div id="best-text">
				<h2 id="best-h2">FITCHA 최고 인기 리뷰</h2>
				<div id="best-sub-text">
					<p id="best-p">다만 악에서 구하소서, 해리포터 등 지금 가장 많이 보는 작품!</p>
				</div>
				<div id="best-content">
					<p id="best-content-p">극장 최신 개봉작부터 고전 영화까지, 블록버스터 영화부터 다양성
						영화까지, 국내 인기 TV시리즈부터 HBO 인기 TV시리즈까지! 이 모든 작품을 2주 무료 이용으로 감상하세요. 취향에
						맞는 작품을 선별하여 추천해드릴게요 :)</p>
				</div>
			</div>
			<div id="best-review">
				<!-- 	    ajax 비동기통신으로 좋아요가 가장 많은 리뷰를 띄워주는 부분 -->
				<!-- 	    	<div id="best-board-img"> -->
				<!-- 				<img src="img/avenger.jpg" alt="Logo Image"> -->
				<!-- 			</div> -->
				<!-- 			<div id="best-writer"> -->
				<!-- 				<div id="best-board-writer"> -->
				<!-- 					<p id="writer-p">작성자</p> -->
				<!-- 					<p id="writer">kangjisoo</p> -->
				<!-- 				</div> -->
				<!-- 				<div id="best-board-title"> -->
				<!-- 					<p id="title-p">제목</p> -->
				<!-- 					<p id="title"><a href="#">어벤져스:인피니티 워 리뷰</a></p> -->
				<!-- 				</div> -->
				<!-- 			</div> -->
			</div>




		</div>


		<div id="review-p">
			<h3>보고싶은 리뷰를 찾아보세요!</h3>
		</div>

		<div id="select-box">
			<select id="genre" onchange="changeSelection()">

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
			</select> <select id="recommend" onchange="changeSelection()">
				<option value="1">최신리뷰 순</option>
				<option value="2">추천 순</option>
				<option value="3">평균별점 순</option>
				<option value="4">러닝타임 짧은 순</option>
			</select>

			<div id="search-box">
				<input type="text" placeholder="영화제목 검색" id="searchBox"
					name="searchBox">
				<button type="button" class="fa fa-search" id="search_btn"
					aria-hidden="true" onclick="searchInput()"></button>
			</div>
		</div>



		<hr>

		<h2 id="review-text">REVIEW</h2>

		<div>
			<div id="container">


				<div id="row">
					<!-- 				ajax 비동기통신을 통해 전체 게시물을 띄워주는 부분  -->

				</div>
			</div>

			<!-- 	페이지 개수 -->

			<ul id=page>

				<!-- 			이전페이지 -->
				<c:if test="${page.prevBtn }">
					<li><a href="main-board?curpage=${page.startPage-1}">이전</a></li>
				</c:if>

				<!-- 			페이지 넣기 -->
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}"
					step="1">
					<c:choose>
						<c:when test="${i eq param.curpage}">
							<li style="background-color: gray">${i}</li>
						</c:when>
						<c:otherwise>
							<li><a href="main-board?curpage=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- 			다음페이지 -->
				<c:if test="${page.nextBtn }">
					<li><a href="main-board?curpage=${page.endPage+1}">다음</a></li>
				</c:if>
			</ul>

		</div>



		<!-- FOOTER -->
		<footer>
			<p>&copy 2021 FITCHA, Inc.</p>
		</footer>
		<!--     </form> -->
	</div>
</body>
</html>