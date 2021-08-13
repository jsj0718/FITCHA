<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/test/css/bootstrap.min.css">
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
<body>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><img id="fitcha" alt="FITCHA" src="${pageContext.request.contextPath }/img/fitcha.png"></a>
        </div>
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath }/main-movie">홈</a></li>
            <li class="active"><a href="#">탐색하기<span class="sr-only">(current)</span></a></li>
            <li><a href="#">My Page</a></li>
            <li><a href="#">게시판</a></li>
            <li><a href="#">찜 목록</a></li>
          </ul>
<!--          <form class="navbar-form navbar-left" role="search"> -->
<!--            <div class="form-group"> -->
<!--              <input type="text" class="form-control" placeholder="Search"> -->
<!--            </div> -->
<!--            <button type="submit" class="btn btn-default">Submit</button> -->
<!--          </form> -->
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-search"></span></a></li>
            <li><a href="#"><span class="glyphicon glyphicon-bell"></span></a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">회원 정보 <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">회원 수정</a></li>
                <li><a href="#">회원 탈퇴</a></li>
                <li class="divider"></li>
                <li><a href="#">로그아웃</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    
    

	<div class="container">
        <h3 class="recommend-title">보고싶은 작품을 찾아보세요.</h3>
        <form>
            <!-- 장르 -->
            <div class="form-group col-xs-3">
                <select class="form-control">
                    <option>모든 장르</option>
                    <option>드라마</option>
                    <option>판타지</option>
                </select>
            </div>
            <!-- 나라 -->
            <div class="form-group col-xs-3">
                <select class="form-control">
                    <option>모든 나라</option>
                    <option>한국</option>
                    <option>미국</option>
                </select>
            </div>
            <!-- 선택 -->
            <div class="form-group col-xs-3">
                <select class="form-control">
                    <option>추천순</option>
                    <option>최신순</option>
                    <option>러닝타임순</option>
                </select>
            </div>
        </form>
	</div>

	<!-- 컨텐츠 -->
	<div class="container">
	    <h3 class="recommend-title">FITCHA 인기 콘텐츠</h3>
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
	
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/test/js/bootstrap.min.js"></script>
    
</body>
</html>