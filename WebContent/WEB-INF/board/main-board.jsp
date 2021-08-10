<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>FitCha</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" >
  <link rel="stylesheet" href="css/board/board-main.css" >
  <script src="js/bootstrap.min.js"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.1.0/js/all.js" integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9" crossorigin="anonymous"></script>

  <script src="main.js"></script>
</head>
<body>
	
	

  <div class="wrapper">

    <!-- HEADER -->
    <header style="position: relative;">
        
         <nav class="sub-nav">
             <a id="login" href="#">로그인</a>     
          	 <a href="#"><i class="fas fa-search sub-nav-logo"></i></a>
             <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a>
            <a href="#">전체 게시판</a>
            <a href="#">나의 게시판</a>
            <a href="#">캘린더</a>
            <a href="#">로그아웃</a>
           
         </nav>    
         
      <div class="netflixLogo">
        <a id="logo" href="#home"><img src="img/fitcha.png" alt="Logo Image"></a>
      </div>  
       
	
      <nav class="sub-nav">
        <a href="#"><i class="fas fa-search sub-nav-logo"></i></a>
        <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a>
        <a href="#">Account</a>        
      </nav>      
	      <div class="main-nav">                
	        <a href="#home">코미디</a>
	        <a href="#tvShows">드라마</a>
	        <a href="#movies">로맨스</a>
	        <a href="#originals">공포</a>
	        <a href="#">판타지</a>
	        <a target="_blank" href="https://codepen.io/cb2307/full/NzaOrm">액션</a>        
	      </div>
    </header>
    <!-- END OF HEADER -->
    
    
    <h2 id="today-best">TODAY BEST</h2>
		<div class="best-box">
			<div id="best-board-img">
				<img src="img/avenger.jpg" alt="Logo Image">
			</div>
			<div id="best-board-writer">
				<p>작성자</p>
			</div>
			<div id="best-board-title">
				<p>제목</p>
			</div>
			<div id="best-board-content">
				<p>내용</p>
			</div>
		</div>



		<h2 id="review-text">REVIEW</h2>
		<div id="review-box">
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="img/avenger.jpg" alt="포스터">
					<div class="caption">
						<h4>제목</h4>
						<p>작성자</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">보기</a> 
						</p>
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
						<p>
							<a href="#" class="btn btn-primary" role="button">보기</a>
						</p>
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
						<p>
							<a href="#" class="btn btn-primary" role="button">보기</a>
						</p>
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
						<p>
							<a href="#" class="btn btn-primary" role="button">보기</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>













		<!-- FOOTER -->
    <footer>
      <p>&copy 1997-2018 FITCHA, Inc.</p>
      <p>KONG &copy 2018</p>
    </footer>
  </div>
</body>
</html>