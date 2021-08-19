<%@page import="com.fitcha.model.vo.MainBoardVO"%>
<%@page import="com.fitcha.model.dao.MainBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
 	String userId = (String)session.getAttribute("id");
	System.out.println("userId: "+ userId);
// 	String userId = "rrr";
   if(userId==null){
	   
       response.sendRedirect(request.getContextPath() + "/sign-in");
   }
   
   String boardId = request.getParameter("boardId");
   MainBoardDAO bdao = new MainBoardDAO();
   MainBoardVO bvo = bdao.reviewDetail(boardId);
%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>FitCha</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- CSS only -->
  <link href="https://fontmeme.com/permalink/210816/95cfd40502d9ebe4522b74e094042fcb.png" rel="shortcut icon" type="image/x-icon">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/review-board.css" >
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
  <script src="${pageContext.request.contextPath}/js/board/review-board.js"></script>
  <script src="${pageContext.request.contextPath}/js/board/comment.js"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.1.0/js/all.js" integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9" crossorigin="anonymous"></script>
  <script>
    var userId = '<%=(String)session.getAttribute("id")%>';
    
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
<body>

<!-- 	 <div class="wrapper"> -->

    <!-- HEADER -->
<nav id="navigator" class="navbar navbar-expand-lg navbar-dark fixed-top" style="background-color: black; height: 70px;">
      <div class="container-fluid">
        <a class="navbar-brand mt-1 ml-1" href="${pageContext.request.contextPath }/main-movie">
          <img id="fitcha" alt="FITCHA" style="height: auto; width: 100px" src="${pageContext.request.contextPath }/img/fitcha.png">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse align-items-end justify-content-between" id="navbarNav" style="background-color: black;">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/main-movie">홈</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/main-search">탐색하기</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/my-board">My Page</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/main_board_view">게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/mcalendar">찜 목록</a>
            </li>
          </ul>
          <ul class="navbar-nav">
            <li>
              <a class="btn">
                <i class="far fa-bell"></i>
              </a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 회원 정보 </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <li>
                  <a class="dropdown-item" href="#">회원 정보</a>
                </li>
                <li>
                  <a class="dropdown-item" href="#">회원 수정</a>
                </li>
                <li class="dropdown-divider"></li>
                <li>
                  <a class="dropdown-item" href="javascript:logout();">로그아웃</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- END OF HEADER -->
    <div id="head-box">
	    <h1 id="review-h1">	<!-- ~님의 REVIEW (review-board.js) -->
	    </h1>
	    
	    
	    <div id="like-btn">
		    <button type="button" class="btn btn-secondary" onclick="like()" style="border-radius: 50%">
				<i class="fas fa-heart text-light" ></i>
			</button>
		</div>
	</div>
    
    
    
<!--     <div id="review-box"> -->
    	<div id="review-box">
<!--    해당되는 게시물 보여주기 (review-board.js) -->
    	</div>
    <%
		if(userId != null && userId.equals(bvo.getUserId())){
						
	%>
    <div id="update-btn">
		    <button type="button" class="btn btn-secondary" onclick="updateBoard()" style="border-radius: 50%">
				<i class="fas fa-pen"></i>
			</button>

		    <button type="button" class="btn btn-secondary" onclick="deleteBoard()" style="border-radius: 50%">
				<i class="fas fa-trash-alt"></i>
			</button>
	</div>
<!-- 	    	<button type="submit" onclick="updateBoard()">수정</button> -->
<!-- 	    	<button type="submit" onclick="deleteBoard()">삭제</button> -->
  
    <%
		}
    %>

	
	
    <hr class="hr">
    <div id="comments">
		<div class="comment-row">
	    	<textarea id="new-comment" name="new_comment" rows=2 placeholder="댓글을 입력해주세요." ></textarea>
	    	<button type="button" class="btn btn-secondary" id="comment-btn" onclick="submitComment()" style="border-radius: 50%">
			    <i class="fas fa-comment-dots"></i>
			</button>
	    </div>
	    
	    
	    
	    <div id="comment-row1">
	    <!-- 댓글로드 -->
	    </div>
	 </div>

    
    
    
    
    		<!-- FOOTER -->
<!--     <footer> -->
<!--       <p>&copy 1997-2018 FITCHA, Inc.</p> -->
<!--       <p>KONG &copy 2018</p> -->
<!--     </footer> -->
</body>
</html>