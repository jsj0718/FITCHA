<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String id = (String) session.getAttribute("id");
    if (id == null) {
    	response.sendRedirect(request.getContextPath() + "/sign-in");
    }
    
    String title = request.getParameter("title");
    if (title == null) {
    	title = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FITCHA</title>
<link href="https://fontmeme.com/permalink/210816/95cfd40502d9ebe4522b74e094042fcb.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath }/test/css/index.css">
<script>
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
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/main-search">탐색하기</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/my-board">My Page</a>
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

  <div style="height: 100px"></div>

  <!-- 검색 -->
  <div id="content">
    <div class="d-flex m-5 justify-content-between">
      <h3 class="recommend-title">보고싶은 작품을 찾아보세요.</h3>
      <div id="searchForm" class="d-flex">
        <select id="genreSelect" class="form-select form-select-sm mx-1">
          <option value="">모든 장르</option>
        </select>
        <select id="countrySelect" class="form-select form-select-sm mx-1">
          <option value="">모든 나라</option>
        </select>
        <select id="orderSelect" class="form-select form-select-sm mx-1">
          <option value="rate">추천순</option>
          <option value="opendate">최신순</option>
          <option value="runningtime">러닝타임순</option>
        </select>
        <div class="input-group mx-1 flex-nowrap">
          <input id="searchText" type="text" class="form-control" placeholder="제목" style="width: 200px" value="<%=title%>">
          <span class="input-group-text" id="basic-addon2"><i class="fas fa-search"></i></span>
        </div>
      </div>
    </div>

    <div id="searchContent"></div>

    <!-- 모달 -->
    <div class="modal fade show" style="display: none;" id="movieModal" aria-hidden="false">
      <div class="modal-dialog modal-xl">
        <div class="modal-content bg-dark">
          <div class="modal-header">
            <h5 class="modal-title text-light" id="movieTitle"></h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <iframe id="movieTrailer" frameborder="0" width=100% height='470' allowfullscreen></iframe>
            <div class="d-flex">
              <img id="moviePoster" alt="" class="m-3 col-lg-3 col-6">
              <p id="movieStory" class="text-light m-3" style="overflow: auto; height: 350px;"></p>
            </div>
            <div class="d-flex">
              <div class="col-3 m-3 text-light">
                <p id="movieOpenDate" class="text-light m-3"></p>
                <p id="movieGrade" class="text-light m-3"></p>
                <p id="movieCountry" class="text-light m-3"></p>
                <p id="movieRunningTime" class="text-light m-3"></p>
              </div>
              <div class="text-light m-2 mt-3">
                <p id="movieGenre" class="text-light m-3"></p>
                <p id="movieStaff" class="text-light m-3"></p>
              </div>
            </div>
            <div id="staffList" class="d-flex" style="overflow-x: scroll;"></div>
          </div>
          <div class="modal-footer" style="display: inline;">
            <div class="row buttonList">
              <div class="col-sm-9">
                <button type="button" class="btn btn-secondary" id="dipBtn" value="none" style="border-radius : 50%"><i class="fas fa-plus text-dark"></i></button>
                <button type="button" class="btn btn-secondary" id="likeBtn" value="none" style="border-radius : 50%"><i class="fas fa-thumbs-up text-dark"></i></button>
              </div>
              <div class="col-sm-3">
                <button type="button" class="btn btn-secondary ms-5" data-bs-target="#reviewModal" data-bs-toggle="modal" data-bs-dismiss="modal">리뷰 작성</button>
                <button type="button" class="btn btn-light" data-bs-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 리뷰 작성 모달창 -->
  <div class="modal fade" id="reviewModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content bg-dark text-light">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalToggleLabel2">Review</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="reviewTitle" class="form-label">TITLE</label>
            <input type="text" class="form-control" id="reviewTitle" placeholder="제목" required>
          </div>
          <div class="mb-3">
            <label for="reviewDate" class="form-label">DATE</label>
            <input type="date" class="form-control" id="reviewDate" readonly="readonly">
          </div>
          <div class="form-check mb-3">
            <input class="form-check-input" type="radio" name="reviewAccess" id="open" value="O">
            <label class="form-check-label" for="open"> 공개 </label>
            <br>
            <input class="form-check-input" type="radio" name="reviewAccess" id="close" value="C" checked>
            <label class="form-check-label" for="close"> 비공개 </label>
          </div>
          <div class="mb-3">
            <label for="contents" class="form-label">내용</label>
            <textarea class="form-control" id="reviewContents" rows="3" placeholder="내용" required></textarea>
          </div>

        </div>
        <div class="modal-footer">
          <button class="btn btn-secondary" id="reviewBtn">작성</button>
          <button class="btn btn-light" data-bs-target="#exampleModalToggle" data-bs-toggle="modal" data-bs-dismiss="modal">취소</button>
        </div>
      </div>
    </div>
  </div>
  
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
  <script src="${pageContext.request.contextPath }/test/js/search.js"></script>

</body>
</html>