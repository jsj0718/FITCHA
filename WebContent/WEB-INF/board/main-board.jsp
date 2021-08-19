<%@page import="com.fitcha.model.vo.MainBoardVO"%>
<%@page import="com.fitcha.controller.Pagination"%>
<%@page import="java.util.List"%>
<%@page import="com.fitcha.model.dao.MainBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link href="https://fontmeme.com/permalink/210816/95cfd40502d9ebe4522b74e094042fcb.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/main-board.css">

<!-- <link rel="shortcut icon" href="#"> -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->

<script src="${pageContext.request.contextPath}/js/board/main-board.js"></script>
<script defer src="https://use.fontawesome.com/releases/v5.1.0/js/all.js" integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9" crossorigin="anonymous">
  
</script>
<script type="text/javascript">
  //썸네일 클릭했을때 리뷰디테일 페이지로 이동
  function thumbnail(boardId) {
    //조회수증가

    location.href = "${pageContext.request.contextPath}/review_board_view?boardId="
        + boardId;

  }
  
  //로그아웃
  var logout = function() {
    var xhrpost = new XMLHttpRequest();

    // 통신할 방식, url, 동기 여부 설정
    xhrpost.open("POST", "logout", true);
    // 요청
    xhrpost.send();
    // 응답
    xhrpost.onreadystatechange = function() {
      if (xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status == 200) {
        location.href = "${pageContext.request.contextPath}/sign-in";
      }
    }
  }
</script>


</head>
<body>
  <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>




  <%-- 	<form action="${pageContext.request.contextPath }/main-board" method="post" > --%>

    <!-- HEADER -->
    <nav id="navigator" class="navbar navbar-expand-lg navbar-dark fixed-top" style="background-color: black; height: 70px;">
      <div class="container-fluid">
        <a class="navbar-brand mt-1 ml-1" href="${pageContext.request.contextPath }/main-movie">
          <img id="fitcha" alt="FITCHA" style="height: auto; width: 100px" src="${pageContext.request.contextPath }/img/fitcha.png">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse align-items-end justify-content-between" id="navbarNav" style="background-color: black;">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/main-movie">홈</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/main-search">탐색하기</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/my-board">My Page</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/main_board_view">게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/mcalendar">찜 목록</a>
            </li>
          </ul>
          <ul class="navbar-nav">
            <li>
              <a class="btn">
                <i class="far fa-bell"></i>
              </a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 회원 정보 </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <li>
                  <a class="dropdown-item" href="#">회원 정보</a>
                </li>
                <li>
                  <a class="dropdown-item" href="#">회원 수정</a>
                </li>
                <li class="dropdown-divider"></li>
                <li>
                  <a class="dropdown-item" href="javascript:logout();">로그아웃</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- END OF HEADER -->

    <!-- --------------------------------최고 인기 리뷰와 해당 게시물 이미지 띄어주는 박스 --------------------------------------------------------------------------- -->
    <div style="height:50px;"></div>
    <div class="container">
      <div id="fixImg">
        <div id="test">
          <div id="best" class="d-flex justify-content-between m-5">
            <!-- -------------------------------최고 인기 리뷰---------------------------------------------- -->
            <div id="best-text" class="col-auto .me-auto">
              <h2 id="best-h2">FITCHA 최고 인기 리뷰</h2>
              <div id="best-sub-text" class="row">
                <p id="best-p" class="h-auto d-inline-block">
                <div>어벤져스, 해리포터 등 지금 가장 많이 보는 작품!</div>
                </p>
              </div>
              <div id="best-content" class="row">
                <p id="best-content-p">
                  극장 최신 개봉작부터 고전 영화까지, 블록버스터 영화부터 다양성 영화까지! <br>다양한 작품들의 감상을 자유롭게 작성해주세요:)
                </p>
                <p id="best-content-p">Fitcha의 가장 인기있는 리뷰를 보러 가고 싶다면 클릭!</p>
                <div class="align-self-end">
                  <p id="best-reviewText" class="row"></p>
                </div>
              </div>
            </div>
            <div id="best-review" class="col-auto"></div>
          </div>
          <!--      	<p id="best-reviewText" class="row"> </p> -->
        </div>

        <!-- -------------------------------------main 가장 많은 리뷰 띄어주는 부분------------------------- -->
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

        <!-- --------------------------------------------------------------------------------------- -->
        <!-- ----------------------------------------------------------------------------------------------------------- ---------------------------------------------->

        <!-- --------------------------------------review context 박스---------------------------------- -->
        <div id="best" class="d-flex justify-content-start">
          <div id="selectBox" class="container">
            <div class="row">
              <div id="review-p" class="d-flex justify-content-start">
                <h3>보고싶은 리뷰를 찾아보세요!</h3>
              </div>
            </div>

            <!-- ---------------------------------------------------------------------------------------- -->

            <!-- -------------------------------select 부분------------------------------------------------ -->
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
              </select>
              <select id="recommend" onchange="changeSelection()">
                <option value="1">최신리뷰 순</option>
                <option value="2">추천 순</option>
                <option value="3">평균별점 순</option>
                <option value="4">러닝타임 짧은 순</option>
              </select>

              <div id="search-box">
                <input type="text" placeholder="영화제목 검색" id="searchBox" name="searchBox">
                <button type="button" class="fa fa-search" id="search_btn" aria-hidden="true" onclick="searchInput()"></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- ----------------------------------------------------------------------------------------- -->


    <hr>


    <!-- <-----------------------------------------------review -------------------------------------->
    <div class="row">
      <h2 class="text-center mt-5">REVIEW</h2>
    </div>
    <!-- ------------------------------------------------------------------------------------------ -->

    <!--  ------------------------------------생성되는 이미지(게시물) 박스란---------------------------------------------------------------------------------- -->
    <div>
      <!--       <div class="container"> -->


      <div id="row" class="row">
        <!-- 				ajax 비동기통신을 통해 전체 게시물을 띄워주는 부분  -->

      </div>
      <!--       </div> -->

      <!-- 	페이지 개수 -->

      <!-- ----------------------v페이지-------------------------------------------------- -->
      <!-- <div> -->
      <!--       <ul id=page> -->

      <!--         			이전페이지 -->
      <%--         <c:if test="${page.prevBtn }"> --%>
      <!--           <li> -->
      <%--             <a href="main-board?curpage=${page.startPage-1}">이전</a> --%>
      <!--           </li> -->
      <%--         </c:if> --%>

      <!--         			페이지 넣기 -->
      <%--         <c:forEach var="i" begin="${page.startPage}" end="${page.endPage}" step="1"> --%>
      <%--           <c:choose> --%>
      <%--             <c:when test="${i eq param.curpage}"> --%>
      <%--               <li style="background-color: gray">${i}</li> --%>
      <%--             </c:when> --%>
      <%--             <c:otherwise> --%>
      <!--               <li> -->
      <%--                 <a href="main-board?curpage=${i}">${i}</a> --%>
      <!--               </li> -->
      <%--             </c:otherwise> --%>
      <%--           </c:choose> --%>
      <%--         </c:forEach> --%>

      <!--         			다음페이지 -->
      <%--         <c:if test="${page.nextBtn }"> --%>
      <!--           <li> -->
      <%--             <a href="main-board?curpage=${page.endPage+1}">다음</a> --%>
      <!--           </li> -->
      <%--         </c:if> --%>
      <!--       </ul> -->
      <!--    </div>  -->
      <!-- -------------------------------------------------------------------------------- -->
    </div>

    <!-- ------------------------------------------------------------------------------------------------------------------------------------------------ -->



    <!-- FOOTER -->
    <footer class="text-center">
      <p>&copy 2021 FITCHA, Inc.</p>
      <p>KITRI &copy 2021</p>
    </footer>
</body>
</html>