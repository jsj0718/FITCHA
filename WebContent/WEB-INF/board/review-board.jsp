<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>FitCha</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/board/review-board.css" >
  <script src="${pageContext.request.contextPath }/js/bootstrap/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath }/js/board/comment.js"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.1.0/js/all.js" integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9" crossorigin="anonymous"></script>

 
</head>
<body>
	 <div class="wrapper">

    <!-- HEADER -->
    <header style="position: relative;">
        <div>
         <nav class="sub-nav">
             <a id="login" href="#">로그인</a>     
           <a href="#"><i class="fas fa-search sub-nav-logo"></i></a>
             <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a>
            <a href="board-main.html">전체 게시판</a>
            <a href="#">나의 게시판</a>
            <a href="#">캘린더</a>
            <a href="#">로그아웃</a>
           
         </nav>    
      </div>      
      <div class="netflixLogo">
        <a id="logo" href="#home"><img src="img/fitcha.png" alt="Logo Image"></a>
      </div>      

      <nav class="sub-nav">
        <a href="#"><i class="fas fa-search sub-nav-logo"></i></a>
        <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a>
        <a href="#">Account</a>        
      </nav>      
    </header>
    <!-- END OF HEADER -->
    <h1 id="review-h1">작성자님의 Review</h1>
    <div class="review-box">
    	<div id="movie-img">
    		<img src="img/avenger.jpg" alt="Logo Image">
    	</div>
    	
    	<div class="movie-info">
			<div id="movie-content">
				<p>영화정보</p>
			</div>
		</div >
		
		<div class="write-box">
	    	<div id="review-writer">
					<p>작성자</p>
			</div>
			<div id="review-title">
					<p>제목</p>
			</div>
			<div id="review-content">
					<p>내용</p>
			</div>
    	</div>
    </div>
    <hr class="hr">
    <div id="comments">
		<div class="comment-row">
	    	<textarea id="new-comment" name="new_comment" rows=2 placeholder="댓글을 입력해주세요." ></textarea>
	    	<button type="submit" id="comment-btn" onclick="submitComment()">등록</button>
	    </div>
	    
	    <div class="comment-row1">
	    	<div id="comment-box">
			    <div class="comment-writer">작성자</div>
			    <div class="comment-date">2021-08-10 14:05:55</div>
		    </div>
	    	<div class="comment-content">sample comment1</div>
	    	<hr class="hr">
	    </div>

    
    
    
    
    		<!-- FOOTER -->
<!--     <footer> -->
<!--       <p>&copy 1997-2018 FITCHA, Inc.</p> -->
<!--       <p>KONG &copy 2018</p> -->
<!--     </footer> -->
</body>
</html>