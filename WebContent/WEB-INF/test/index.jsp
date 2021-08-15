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

  <div style="height: 1100px"></div>

  <!-- 인기 컨텐츠 -->
<!--   <div class="container mx-2"> -->
<!--     <div class="contents-title">FITCHA 인기 콘텐츠</div> -->
<!--     <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel"> -->
<!--       <div class="carousel-indicators"> -->
<!--         <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button> -->
<!--         <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button> -->
<!--         <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
<!--       </div> -->
<!--       <div class="carousel-inner"> -->
<!--         <div class="carousel-item active"> -->
<!--           <img src="..." class="d-block w-100" alt="..."> -->
<!--         </div> -->
<!--         <div class="carousel-item"> -->
<!--           <img src="..." class="d-block w-100" alt="..."> -->
<!--         </div> -->
<!--         <div class="carousel-item"> -->
<!--           <img src="..." class="d-block w-100" alt="..."> -->
<!--         </div> -->
<!--       </div> -->
<!--       <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev"> -->
<!--         <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span> -->
<!--       </button> -->
<!--       <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next"> -->
<!--         <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span> -->
<!--       </button> -->
<!--     </div> -->
<!--   </div> -->

  <!-- 최신 -->
  <div class="container">
    <div class="contents-title mx-5">최신 컨텐츠</div>
    <div id="recentIndicators" class="carousel slide pb-3" data-bs-ride="carousel">
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
    <div id="recommendIndicators" class="carousel slide pb-3" data-bs-ride="carousel">
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
    <div id="genderIndicators" class="carousel slide pb-3" data-bs-ride="carousel">
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
    <div id="ageIndicators" class="carousel slide pb-3" data-bs-ride="carousel">
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
  <div class="modal fade" id="myModal" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <h4 class="modal-title" id="gridSystemModalLabel">아바타2</h4>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-4">
                <figure>
                  <figcaption style="text-align: center;">드라마 | 판타지</figcaption>
                  <img style="margin: auto;" src="https://movie-phinf.pstatic.net/20150622_131/14349365467550iQnC_JPEG/movie_image.jpg?type=m203_290_2" class="img-responsive" alt="Responsive image">
                </figure>
              </div>
              <div class="col-md-8">
                <h3 style="text-align: center;">영화 줄거리</h3>
                <p class="text-left">현실과 가상세계를 넘나드는 가장 용이한 방법 중 하나가 아바타 꾸미기일 수도 있는 현재. 그리고 이 현재에서 멀지 않은 가까운 미래를 영화 소재로 삼은 는 의 분위기와 의 이야기 전개 방식을 차용한 Sf 영화이다. 포스트모더니즘 테두리에서 즐겨 논의 되는 ‘정체성’에 관한 이 영화는 미래 아시아의 한 도시를 배경으로 한다. ‘사이버링크’라는 거대 통제 시스템의 음모를 파헤치려는 동양인 왕형사와 이 시스템을 비웃는 범죄자를 추적하는 서양인 여성 헌터 대쉬를 주축으로 이야기는 전개된다. 하나의 팀이 된 이 두 주인공은 결국 정부에 의해 작용되는 ‘사이버링크’ 시스템의 거대한 비밀을 알게 되고, 이 시스템의 파괴방법을 찾아 나선다. 영화는 순수 중국 기술로 완성된 300여 컷의 Cg와 장자의 사상 등을 기반으로 둔 듯한 소품을 이용하여, 꽤나 동양적인 공상과학을 보여준다. 싱가폴 태생의 쿠오 지안 홍 감독은 음양의 조화를 기반으로 서로 대치되는 동서양의 남녀 주인공, 어둠과 밝음, 강함과 유함, 상하의 조화 등 다분히 동양적인 정서를 통해 영화의 주제에 집중한다. 음악의 비중이 크고 음악이 를 꽤나 닮았지만, 세기말적 어두움이 주 모체가 되었던 와는 달리 는 상대적으로 밝은 총천연색의 미래를 묘사한다. 강력한 악역이 없고 신선 노름하는 듯한 음모의 핵심이 다소 긴장의 끈을 풀어 놓기는 하지만, 자신의 본질을 자신의 뿌리에서 찾는 이 영화의 동양적 사상은 우리에게 익숙한 헐리우드 스타일과는 분명한 차이를 보인다.(부천판타스틱영화제 - 오병훈)</p>
              </div>
            </div>
            <hr>
            <div class="row">
              <div class="col-md-4">제목 : 아바타2</div>
              <div class="col-md-4">부제 : Avatar 2, 2021</div>
              <div class="col-md-4">평점 : 9.22</div>
              <div class="col-md-4">등급 : 전체관람가</div>
              <div class="col-md-4">나라 : 미국</div>
              <div class="col-md-4">시간 : 125분</div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary">리뷰 작성</button>
          <button type="button" class="btn btn-success">찜하기</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
  </div>
  <!-- /.modal -->
  
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>