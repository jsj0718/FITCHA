<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/board/my-board.css">
<body>
<fieldset>
<header>
 	<div class="netflixLogo">
        <a id="logo" href="#home"><img src="https://github.com/carlosavilae/Netflix-Clone/blob/master/img/logo.PNG?raw=true" alt="Logo Image"></a>
     </div>   
     <div id="title">
      <h1 > FITCHA님의 MYPAGE</h1>
     
     </div>
      <nav class="main-nav">                
        <a href="#recently">Recently Added</a>
        <a href="#best">Best</a>
        <a href="#">review</a>
      </nav> 
    
    <div id="search-box">
       <input type="text" placeholder="Search" id="input-search">
       <button type="button" id="search-btn">검색</button>
    </div>
     
</header>
<section>
   <h2 id="recently">Recently Added</h2>
      <div id="review-box">
      <div class="row">
         <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
               <img src="https://images8.alphacoders.com/815/815755.jpg" alt="포스터" width=242px;>
               <div class="caption">
                  <h4 id="review-title">제목</h4>
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
               <img src="https://images8.alphacoders.com/815/815755.jpg" alt="포스터" width=242px;>
               <div class="caption">
                  <h4>제목</h4>
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
               <img src="https://images8.alphacoders.com/815/815755.jpg" alt="포스터" width=242px;>
               
               <div class="caption">
                  <h4>제목</h4>
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
               <img src="https://images8.alphacoders.com/815/815755.jpg" alt="포스터" width=242px;>
               <div class="caption">
                  <h4>제목</h4>
                  <p>
                     <a href="#" class="btn btn-primary" role="button">보기</a>
                  </p>
               </div>
            </div>
         </div>
      </div>
   </div>   
  <h2 id="best">Best</h2>
      <div id="review-box">
      <div class="row">
         <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
               <img src="https://images8.alphacoders.com/815/815755.jpg" alt="포스터" width=242px;>
               <div class="caption">
                  <h4 id="review-title">제목</h4>
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
               <img src="https://images8.alphacoders.com/815/815755.jpg" alt="포스터" width=242px;>
               <div class="caption">
                  <h4>제목</h4>
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
               <img src="https://images8.alphacoders.com/815/815755.jpg" alt="포스터" width=242px;>
               <div class="caption">
                  <h4>제목</h4>
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
               <img src="https://images8.alphacoders.com/815/815755.jpg" alt="포스터" width=242px;>
               <div class="caption">
                  <h4>제목</h4>
                  <p>
                     <a href="#" class="btn btn-primary" role="button">보기</a>
                  </p>
               </div>
            </div>
         </div>
      </div>
   </div>     
</section>
<footer>
  <p>&copy 1997-2018 Netflix, Inc.</p>
      <p>Carlos Avila &copy 2018</p>
</footer>
</fieldset>
</body>
</html>