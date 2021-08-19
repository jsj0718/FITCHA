<%@page import="java.util.ArrayList"%>
<%@page import="com.fitcha.model.vo.MyBoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.fitcha.controller.Pagination"%>
<%@page import="com.fitcha.model.dao.MyBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
  String id = (String) session.getAttribute("id");
  if (id == null) {
      response.sendRedirect(request.getContextPath() + "/sign-in");
  }
  MyBoardDAO bdao = new MyBoardDAO();
  
  String btitleVal = request.getParameter("btitle");
  String bcontentVal = request.getParameter("bcontent");
  String mtitleVal = request.getParameter("mtitle");
  String countryVal = request.getParameter("country");
  String genreVal = request.getParameter("genre");
  String orderVal = request.getParameter("order");
  
  if (btitleVal == null) {
      btitleVal = "";
  }
  if (bcontentVal == null) {
      bcontentVal = "";
  }
  if (mtitleVal == null) {
      mtitleVal = "";
  }
  if (countryVal == null) {
      countryVal = "";
  }
  if (genreVal == null) {
      genreVal = "";
  }
  if (orderVal == null) {
      orderVal = "b.boardid";
  }
  
  //페이징 처리 적용
  // String keyword = (String)session.getAttribute("keyword");
  // int genreId = (int)session.getAttribute("genreId");
  String curPage = request.getParameter("curpage");
  if (curPage == null) {
  	curPage = "1";
  }
  int curPageInt = Integer.parseInt(curPage);
  int totalContent = bdao.selectBoardCnt(id, mtitleVal, countryVal, genreVal);

  
  Pagination pagination = new Pagination(curPageInt, totalContent, 6);
  
  //1page내에 보여줘야하는 게시물의 첫번째 rownum
  int start = curPageInt * pagination.getContentCnt() - (pagination.getContentCnt() - 1);
  //한페이지 내에 보여줘야하는 게시물의 마지막 rownum
  int end = curPageInt * pagination.getContentCnt();
  
  ArrayList<MyBoardVO> blist = bdao.selectBoardList(id, btitleVal, bcontentVal, countryVal, mtitleVal, genreVal, orderVal, start, end);
  
  for (MyBoardVO mbvo : blist) {
      System.out.println(mbvo.getBtitle());
  }
//   List<MyBoardVO> blist = bdao.myBoardListPage(id, start, end);
//   List<BoardVO> pblist = bdao.mySelectListPage(id, keyword, genreId, start, end);
%>

<!DOCTYPE html>
<c:set var="page" value="<%=pagination%>" />
<c:set var="blist" value="<%=blist%>" />
<%-- <c:set var="pblist" value="<%=pblist %>"/> --%>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FITCHA</title>

<link href="https://fontmeme.com/permalink/210816/95cfd40502d9ebe4522b74e094042fcb.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<%--   <link rel="stylesheet" href="${pageContext.request.contextPath }/css/board/my-board.css"> --%>

<script src="${pageContext.request.contextPath }/js/board/my-board.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/board/review-board.js"></script> --%>
<script type="text/javascript">
  	//썸네일 클릭했을때 리뷰디테일 페이지로 이동
  	function thumbnail(boardId) {
  		location.href = "${pageContext.request.contextPath}/review_board_view?boardId="+ boardId;
  	}
  
    // 로그아웃
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
    
    window.onload = function() {
   // 장르, 나라 넣기
      var getConnect = function(url, block) {
        var xhrget = new XMLHttpRequest();

        // 통신할 방식, url, 동기 여부 설정
        xhrget.open("GET", url, true);
        // 요청
        xhrget.send();
        // 응답
        xhrget.onreadystatechange = function() {
          if (xhrget.readyState == XMLHttpRequest.DONE && xhrget.status == 200) {

            // 응답 값 (서버로부터 받아온 데이터)
            var jsonStr = xhrget.responseText; // json 형태의 문자열
            var json = JSON.parse(jsonStr); // 문자열을 json으로 변환

            console.log(url);
            console.log(jsonStr);

            for (var i = 0; i < json.length; i++) {
              $('#' + block)
                .append($('<option>')
                  .attr('value', json[i].name)
                  .text(json[i].name))

            }
          }
        }
      }

      getConnect("genre", "genre");
      getConnect("country", "country");

      var genre = document.getElementById("genre").options[document.getElementById("genre").selectedIndex].value;
      var country = document.getElementById("country").options[document.getElementById("country").selectedIndex].value;
      var order = document.getElementById("order").options[document.getElementById("order").selectedIndex].value;
      var text = document.getElementById("searchBox").value;
      
    <%
      if (totalContent == 0) {
    %>
          $('#paging').attr('style', 'display:none');
    <%
      }
    %>  
      var totalData;  // 총 데이터 수
      var dataPerPage = 5;
      var pageCount = 10;
      var currentPage = 1;
      
      var searchBtn = document.getElementById("search_btn");
      searchBtn.onclick = function() {
        var genre = document.getElementById("genre").options[document.getElementById("genre").selectedIndex].value;
        var country = document.getElementById("country").options[document.getElementById("country").selectedIndex].value;
        var order = document.getElementById("order").options[document.getElementById("order").selectedIndex].value;
        var text = document.getElementById("searchBox").value;
        
        location.href = "${pageContext.request.contextPath}/my-board?genre="+genre+"&country="+country+"&order="+order+"&mtitle="+text;
      }       
    }
  </script>
</head>
<body class="bg-black">
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
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/my-board">My Page</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/main_board_view">게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/mcalendar">찜 목록</a>
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

  <div style="height: 100px;"></div>

  <div class="d-flex m-5 justify-content-between">
    <h3 class="recommend-title text-light">보고싶은 게시글을 찾아보세요.</h3>
    <div id="searchForm" class="d-flex col-8">
      <select id="genre" class="form-select w-25 form-select-sm mx-1">
        <option value="">모든 장르</option>
      </select>
      <select id="country" class="form-select w-25 form-select-sm mx-1">
        <option value="">모든 나라</option>
      </select>
      <select id="order" name="order" class="form-select w-25 form-select-sm mx-1">
        <option value="b.boardid">최신리뷰 순</option>
        <option value="b.likes">추천 순</option>
        <option value="b.views">조회수 순</option>
      </select>
      <div class="input-group mx-1 flex-nowrap">
        <button class="btn btn-outline-secondary" type="button" id="search_btn" onclick="searchInput()">
          <i class="fas fa-search text-light"></i>
        </button>
        <input type="text" class="form-control" placeholder="Search" aria-label="Example text with button addon" aria-describedby="button-addon1" id="searchBox" name="searchBox" value="">
      </div>
    </div>
  </div>




  <section>
    <!-- paging처리를 적용한 첫화면 최신 업데이트 순  -->
    <div id="recently-box">
      <h2 id="recently" class="text-light ms-5 mb-3" style="font-size: 1.4vw; font-weight: 700; color: #e5e5e5;">나의 게시판</h2>
      <div class="row">
        <c:forEach var="bvo" items="${blist }">
          <div class="card col-3 m-3 bg-dark" style="width: 18rem;">
            <img src="${bvo.poster}" alt="포스터" class="json_box card-img-top" onclick="thumbnail(${bvo.boardId})">
            <div class="card-body">
              <h4 id="review_title" class="card-title text-light">
                <c:choose>
                  <c:when test="${fn:length(bvo.btitle) > 14}">
                    <c:out value="${fn:substring(bvo.btitle,0,13)}" />….
                          </c:when>
                  <c:otherwise>
                    <c:out value="${bvo.btitle}" />
                  </c:otherwise>
                </c:choose>
              </h4>
              <p class="card-text text-light">
                <c:choose>
                  <c:when test="${fn:length(bvo.content) > 30}">
                    <c:out value="${fn:substring(bvo.content,0 , 30)}" />….
                          </c:when>
                  <c:otherwise>
                    <c:out value="${bvo.content}" />
                  </c:otherwise>
                </c:choose>
              </p>
            </div>
          </div>
        </c:forEach>


        <!-- 페이징 처리 -->
        <nav id="paging" aria-label="Page navigation example" style="display : block;">
          <ul class="pagination d-flex justify-content-center">
            <c:if test="${page.prevBtn }">
              <li class="page-item">
                <a class="page-link bg-dark text-light" href="myBoardView?curpage=${page.startPage-1 }&genre=<%=genreVal %>&country=<%=countryVal %>&order=<%=orderVal %>&mtitle=<%=mtitleVal%>">Previous</a>
              </li>
            </c:if>
            <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
              <c:choose>
                <c:when test="${i eq param.curpage || (i eq 1 && empty param.curpage )}">
                  <li class="page-item">
                    <a class="page-link bg-light text-dark">${i}</a>
                  </li>
                </c:when>
                <c:otherwise>
                  <li class="page-item">
                    <a class="page-link bg-dark text-light" href="myBoardView?curpage=${i}&genre=<%=genreVal %>&country=<%=countryVal %>&order=<%=orderVal %>&mtitle=<%=mtitleVal%>">${i}</a>
                  </li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:if test="${page.nextBtn }">
              <li class="page-item">
                <a class="page-link bg-dark text-light" href="myBoardView?curpage=${page.endPage+1 }&genre=<%=genreVal %>&country=<%=countryVal %>&order=<%=orderVal %>&mtitle=<%=mtitleVal%>">Previous</a>
              </li>
            </c:if>
          </ul>
        </nav>
      </div>
    </div>
  <%    
    if (totalContent == 0) {
  %>
      <h3 class="text-center mt-5">검색된 게시물이 없습니다. 다시 검색해주세요.</h3>
  <%
    }
  %>
  </section>

  <footer class="text-center">
    <p>&copy 2021 FITCHA, Inc.</p>
    <p>KITRI &copy 2021</p>
  </footer>

  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

</body>
</html>