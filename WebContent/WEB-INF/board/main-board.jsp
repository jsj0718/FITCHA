<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>FitCha</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/main-board.css" >
  <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.1.0/js/all.js" integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9" crossorigin="anonymous"></script>

</head>
<body>
	
	

  <div class="wrapper">

    <!-- HEADER -->
    <header style="position: relative;">
        
         <nav class="sub-nav">
             <a id="login" href="#">로그인</a>     
            <a href="#">전체 게시판</a>
            <a href="#">나의 게시판</a>
            <a href="#">캘린더</a>
            <a href="#">로그아웃</a>
         </nav>    
         
      <div class="netflixLogo">
        <a id="logo" href="#home"><img src="img/fitcha.png" alt="Logo Image"></a>
      </div>  
      
<!-- 	      <div class="main-nav">                 -->
<!-- 	        <a href="#home">코미디</a> -->
<!-- 	        <a href="#tvShows">드라마</a> -->
<!-- 	        <a href="#movies">로맨스</a> -->
<!-- 	        <a href="#originals">공포</a> -->
<!-- 	        <a href="#">판타지</a> -->
<!-- 	        <a target="_blank" href="https://codepen.io/cb2307/full/NzaOrm">액션</a>         -->
<!-- 	      </div> -->
    </header>
    <!-- END OF HEADER -->
    <div id="best">
	    <div id="best-text">
		    <h2 id="best-h2">FITCHA 최고 인기 리뷰</h2>
		    <div id="best-sub-text">
		    	<p id="best-p">다만 악에서 구하소서, 해리포터 등 지금 가장 많이 보는 작품!</p>
		    </div>
		    <div id="best-content">
		    	<p id="best-content-p">극장 최신 개봉작부터 고전 영화까지, 블록버스터 영화부터 다양성 영화까지, 국내 인기 TV시리즈부터 HBO 인기 TV시리즈까지! 이 모든 작품을 2주 무료 이용으로 감상하세요. 취향에 맞는 작품을 선별하여 추천해드릴게요 :)</p>
		    </div>
	    </div>
	    <div id="best-review">
	    	<div id="best-board-img">
				<img src="img/avenger.jpg" alt="Logo Image">
			</div>
			<div id="best-writer">
				<div id="best-board-writer">
					<p id="writer-p">작성자</p>
					<p id="writer">kangjisoo</p>
				</div>
				<div id="best-board-title">
					<p id="title-p">제목</p>
					<p id="title"><a href="#">어벤져스:인피니티 워 리뷰</a></p>
				</div>
			</div>
	    </div>
    </div>
    
   	<div id="review-p">
   		<h3>보고싶은 리뷰를 찾아보세요!</h3>
   	</div>
   	
	 <select id="genre" >
	    <option value="none">모든 장르</option>
	    <option value="코미디">코미디</option>
	    <option value="드라마">드라마</option>
	    <option value="로맨스">로맨스</option>
	    <option value="공포">공포</option>
	  </select>
	  <select id="recommend" >
	    <option value="none">추천 순</option>
	    <option value="평균별점">평균별점 순</option>
	    <option value="최신리뷰">최신리뷰 순</option>
	    <option value="러닝타임">러닝타임 짧은 순</option>
	  </select>
	  


		<h2 id="review-text">REVIEW</h2>
		<div id="review-box">
			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="img/avenger.jpg" alt="포스터">
						<div class="caption">
							<h4>제목</h4>
							<p>작성자</p>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="img/avenger.jpg" alt="포스터">
						<div class="caption">
							<h4>제목</h4>
							<p>작성자</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="img/avenger.jpg" alt="포스터">
						<div class="caption">
							<h4>제목</h4>
							<p>작성자</p>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="img/avenger.jpg" alt="포스터">
						<div class="caption">
							<h4>제목</h4>
							<p>작성자</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="img/avenger.jpg" alt="포스터">
						<div class="caption">
							<h4>제목</h4>
							<p>작성자</p>
						</div>
					</div>
				</div>
			</div>			
		</div>













		<!-- FOOTER -->
    <footer>
      <p>&copy 2021 FITCHA, Inc.</p>
    </footer>
  </div>
</body>
</html>