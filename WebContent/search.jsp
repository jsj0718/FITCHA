<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>FITCHA</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/test/css/index.css">
</head>
<body class="bg-black">
  <nav id="navigator" class="navbar navbar-expand-lg navbar-dark" style="background-color: black;">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img id="fitcha" alt="FITCHA" src="${pageContext.request.contextPath }/img/fitcha.png">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse align-items-end justify-content-between" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/main-movie">홈</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/main-search">탐색하기</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">My Page</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">찜 목록</a>
          </li>
        </ul>
        <ul class="navbar-nav">
          <li><a class="btn"><i class="far fa-bell"></i></a></li>
          <li class="nav-item dropdown me-5">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              회원 정보
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <li><a class="dropdown-item" href="#">회원 수정</a></li>
              <li><a class="dropdown-item" href="#">회원 탈퇴</a></li>
              <li class="dropdown-divider"></li>
              <li><a class="dropdown-item" href="#">로그아웃</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- 검색 -->
  <div id="content">
    <div class="d-flex m-5 justify-content-between">
      <h3 class="recommend-title">보고싶은 작품을 찾아보세요.</h3>
      <div id="searchForm" class="d-flex">
        <select id="genreSelect" class="form-select form-select-sm mx-1">
          <option value="">모든 장르</option>
          <option value="드라마">드라마</option>
          <option value="판타지">판타지</option>
        </select>
        <select id="countrySelect" class="form-select form-select-sm mx-1">
          <option value="">모든 나라</option>
          <option value="한국">한국</option>
          <option value="미국">미국</option>
        </select>
        <select id="orderSelect" class="form-select form-select-sm mx-1">
          <option value="rate">추천순</option>
          <option value="opendate">최신순</option>
          <option value="runningtime">러닝타임순</option>
        </select>
        <div class="input-group mx-1 flex-nowrap">
          <input id="searchText" type="text" class="form-control" placeholder="제목" style="width: 200px" value="">
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
              <p id="movieStory" class="text-light m-3"></p>
            </div>
            <div class="d-flex">
              <div class="col-3 text-light">
                관객 수<br>
                나라<br>
                기타<br>
                등등<br>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
  <script src="${pageContext.request.contextPath }/test/js/search.js"></script>
</body>
</html>