<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Insert title here</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/test/css/index.css">
  <script>
  //     $('#myModal').on('shown.bs.modal', function () {
  //         $('#myInput').focus()
  // 	})
      
      
    
    window.onload = function() {
  //     	var element = document.getElementById("test");
  //     	element.onmouseover = function() {
  //     		element.innerHTML = '<div id="test" class="thumbnail"> ' +
  // 								     '<img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="..."> ' +
  // 								     '<div class="caption"> ' +
  // 									     '<h3>Thumbnail label</h3> ' +
  // 									     '<p>...</p> ' +
  // 									     '<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p> ' +
  // 								     '</div> ' +
  // 								  '</div>';
    
  //     	}
        
  //     	element.onmouseout = function() {
  //             element.innerHTML = '<a href="#" class="thumbnail"> ' +
  // 					                '<img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">' +
  // 					            '</a> ';
      
  //         }

    
      }
  </script>
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
  <div id="content" class="container" style="height: 2000px">
    <div class="d-flex my-5">
      <h3 class="recommend-title">보고싶은 작품을 찾아보세요.</h3>
      <form class="ms-5">
        <!-- 장르 -->
        <select class="form-select form-select-sm">
          <option>모든 장르</option>
          <option>드라마</option>
          <option>판타지</option>
        </select>
      </form>
      <!-- 나라 -->
      <form>
        <select class="form-select form-select-sm">
          <option>모든 나라</option>
          <option>한국</option>
          <option>미국</option>
        </select>
      </form>
      <!-- 선택 -->
      <form>
        <select class="form-select form-select-sm">
          <option>추천순</option>
          <option>최신순</option>
          <option>러닝타임순</option>
        </select>
      </form>
    </div>

    <!-- 컨텐츠 -->
    <h3 class="recommend-title">FITCHA 인기 콘텐츠</h3>
    <div id="search-content">

    </div>








    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#myCarosel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarosel" data-slide-to="1"></li>
        <li data-target="#myCarosel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="item active">
          <div class="row">
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail" data-toggle="modal" data-target="#myModal">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail" data-toggle="modal" data-target="#myModal">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail" data-toggle="modal" data-target="#myModal">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="row">
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="row">
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
            <div class="col-xs-4 col-md-2">
              <a href="#" class="thumbnail">
                <img src="https://movie-phinf.pstatic.net/20160531_274/1464689049759ULlJL_JPEG/movie_image.jpg?type=m203_290_2" alt="...">
              </a>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev" onclick="$('#myCarousel').carousel('prev')">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next" onclick="$('#myCarousel').carousel('next')">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
      </a>
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
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
  <script src="${pageContext.request.contextPath }/test/js/search.js"></script>
</body>
</html>