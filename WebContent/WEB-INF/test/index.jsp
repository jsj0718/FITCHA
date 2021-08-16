<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>FITCHA</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/test/css/index.css">
  <script src="${pageContext.request.contextPath }/test/js/index.js"></script>
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
<%
  String id = (String) session.getAttribute("id");
  if (id == null) {
      response.sendRedirect(request.getContextPath() + "/sign-in");
  }
%>
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
            <a class="nav-link active" href="${pageContext.request.contextPath }/main-movie">홈</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath }/main-search">탐색하기</a>
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

  <!-- Carausel -->
  <div id="background-image" class="carousel slide carousel-fade" style="position: fixed" data-bs-ride="carousel">
    <div class="carousel-inner opacity-25">
      <div class="carousel-item active">
        <img src="${pageContext.request.contextPath }/img/login5.jpg" class="d-inline w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login6.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login7.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login9.jpg" class="d-block w-100" alt="...">
      </div>
    </div>
  </div>


  <div style="height: 500px"></div>

  <div class="container-fluid position-absolute top-50 start-50 translate-middle">   
    <div class="row justify-content-center">
      <form class="col-4" action="${pageContext.request.contextPath}/main-search?${value}" method="GET">
        <div class="input-group mb-4 flex-nowrap">
          <div class="input-group flex-nowrap input-group-lg">
            <span class="input-group-text bg-black text-light" id="addon-wrapping" style="border-top-left-radius: 15px; border-bottom-left-radius: 15px;"><i class="fas fa-search" type="submit"></i></span>
            <input type="text" class="form-control bg-black text-light" style="border-top-right-radius: 15px; border-bottom-right-radius: 15px;" placeholder="Username" aria-label="Username" aria-describedby="addon-wrapping" name="title">
          </div>
        </div>
      </form>
    </div>
  </div>

  <div style="height: 500px"></div>
  
  <!-- 인기 컨텐츠 -->
  <div class="container">
    <div class="contents-title mx-5">인기 컨텐츠</div>
    <div id="popularIndicators" class="carousel slide pb-3" data-bs-ride="carousel" data-bs-interval="false">
      <div class="carousel-indicators m-0" id="popular-bar">
      
      </div>
      <div class="carousel-inner px-3 py-2" id="popular-contents">

      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#popularIndicators" data-bs-slide="prev" style="width: 30px;">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#popularIndicators" data-bs-slide="next" style="width: 30px;">
        <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>

  <!-- 최신 -->
  <div class="container">
    <div class="contents-title mx-5">최신 컨텐츠</div>
    <div id="recentIndicators" class="carousel slide pb-3" data-bs-ride="carousel" data-bs-interval="false">
      <div class="carousel-indicators m-0" id="recent-bar">
      
      </div>
      <div class="carousel-inner px-3 py-2" id="recent-contents">

      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#recentIndicators" data-bs-slide="prev" style="width: 30px;">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#recentIndicators" data-bs-slide="next" style="width: 30px;">
        <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>

  <!-- 추천 -->
  <div class="container">
    <div class="contents-title mx-5">${id } 님에게 추천하는 컨텐츠</div>
    <div id="recommendIndicators" class="carousel slide pb-3" data-bs-ride="carousel" data-bs-interval="false">
      <div class="carousel-indicators m-0" id="recommend-bar">
      
      </div>
      <div class="carousel-inner px-3 py-2" id="recommend-contents">

      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#recommendIndicators" data-bs-slide="prev" style="width: 30px;">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#recommendIndicators" data-bs-slide="next" style="width: 30px;">
        <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
  
  <!-- 성별 -->
  <div class="container">
    <div class="contents-title mx-5">성별 추천 컨텐츠</div>
    <div id="genderIndicators" class="carousel slide pb-3" data-bs-ride="carousel" data-bs-interval="false">
      <div class="carousel-indicators m-0" id="gender-bar">
      
      </div>
      <div class="carousel-inner px-3 py-2" id="gender-contents">

      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#genderIndicators" data-bs-slide="prev" style="width: 30px;">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#genderIndicators" data-bs-slide="next" style="width: 30px;">
        <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>

  <!-- 나이 -->
  <div class="container">
    <div class="contents-title mx-5">나이별 추천 컨텐츠</div>
    <div id="ageIndicators" class="carousel slide pb-3" data-bs-ride="carousel" data-bs-interval="false">
      <div class="carousel-indicators m-0" id="age-bar">
      
      </div>
      <div class="carousel-inner px-3 py-2" id="age-contents">

      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#ageIndicators" data-bs-slide="prev" style="width: 30px;">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#ageIndicators" data-bs-slide="next" style="width: 30px;">
        <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
  
  
  <!-- 모달창 -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div id="staff-list" class="d-flex" style="overflow-x: scroll;">
            <div class="col-xs-6 col-md-3 text-center">
              <img src='https://search.pstatic.net/common/?src=https%3A%2F%2Fssl.pstatic.net%2Fsstatic%2Fpeople%2F37%2F201504171519202401.jpg&type=u120_150&quality=95'>
              <p>로버트 다우니 주니어</p>
              <p>주연 | 토니 스타크 역</p>
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


  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

</body>
</html>