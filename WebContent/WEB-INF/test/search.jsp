<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String id = (String) session.getAttribute("id");
  if (id == null) {
      response.sendRedirect(request.getContextPath() + "/sign-in");
  }
  
  String movieName = request.getParameter("movie-name");
  System.out.println(movieName);
%>
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
    
    window.onload = function() {
      var movieName = "${movieName}";
      console.log(movieName);      
    }
  </script>
</head>

<body class="bg-black">
  <nav id="navigator" class="navbar navbar-expand-lg navbar-dark fixed-top" style="background-color: black; height: 70px;">
    <div class="container-fluid">
      <a class="navbar-brand mt-1 ml-1" href="#">
        <img id="fitcha" alt="FITCHA" style="height: auto; width: 100px" src="${pageContext.request.contextPath }/img/fitcha.png">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse align-items-end justify-content-between" id="navbarNav" style="background-color : black;">
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
          <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <a class="btn"><i class="fas fa-search" type="submit"></i></a>
          </form>
          <li><a class="btn"><i class="far fa-bell"></i></a></li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              회원 정보
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <li><a class="dropdown-item" href="#">회원 정보</a></li>
              <li><a class="dropdown-item" href="#">회원 수정</a></li>
              <li class="dropdown-divider"></li>
              <li><a class="dropdown-item" href="javascript:logout();">로그아웃</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div style="height: 100px"></div>
  
  <div id="content">
    <div class="d-flex my-5">
      <h3 class="recommend-title">보고싶은 작품을 찾아보세요.</h3>
      <form class="ms-5">
        <!-- 장르 -->
        <select id="genreSelect" class="form-select form-select-sm">
          <option value="">모든 장르</option>
          <option value="드라마">드라마</option>
          <option value="판타지">판타지</option>
        </select>
      </form>
      <!-- 나라 -->
      <form>
        <select id="countrySelect" class="form-select form-select-sm">
          <option value="">모든 나라</option>
          <option value="한국">한국</option>
          <option value="미국">미국</option>
        </select>
      </form>
      <!-- 선택 -->
      <form>
        <select id="orderSelect" class="form-select form-select-sm">
          <option value="rate">추천순</option>
          <option value="opendate">최신순</option>
          <option value="runningtime">러닝타임순</option>
        </select>
      </form>
    </div>

    <!-- 컨텐츠 -->


    <h3 class="recommend-title">FITCHA 인기 콘텐츠</h3>
    <div class="d-flex justify-content-center">
      <div id="searchContent"></div>
    </div>


    <div class="modal fade show" style="display: none;" id="movieModal" aria-hidden="false">
      <div class="modal-dialog modal-xl">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            ...
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 모달창 -->
  <div class="modal fade" id="myModal" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="gridSystemModalLabel">Avatar2</h4>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-4">.col-md-4</div>
              <div class="col-md-4 col-md-offset-4">.col-md-4 .col-md-offset-4</div>
            </div>
            <div class="row">
              <div class="col-md-3 col-md-offset-3">.col-md-3 .col-md-offset-3</div>
              <div class="col-md-2 col-md-offset-4">.col-md-2 .col-md-offset-4</div>
            </div>
            <div class="row">
              <div class="col-md-6 col-md-offset-3">.col-md-6 .col-md-offset-3</div>
            </div>
            <div class="row">
              <div class="col-sm-9">
                Level 1: .col-sm-9
                <div class="row">
                  <div class="col-xs-8 col-sm-6">
                    Level 2: .col-xs-8 .col-sm-6
                  </div>
                  <div class="col-xs-4 col-sm-6">
                    Level 2: .col-xs-4 .col-sm-6
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary">리뷰 작성</button>
          <button type="button" class="btn btn-success">찜 하기</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
  <script src="${pageContext.request.contextPath }/test/js/search.js"></script>
</body>
</html>