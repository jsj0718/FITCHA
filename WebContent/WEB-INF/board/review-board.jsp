<%@page import="com.fitcha.model.vo.MainBoardVO"%>
<%@page import="com.fitcha.model.dao.MainBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//  String userId = (String)session.getAttribute("userId");
	String userId = "rrr";
   if(userId==null){
	   
	   response.sendRedirect("login.jsp");
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/review-board.css" >
<%--   <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script> --%>
  <script src="${pageContext.request.contextPath}/js/board/review-board.js"></script>
  <script src="${pageContext.request.contextPath}/js/board/comment.js"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.1.0/js/all.js" integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9" crossorigin="anonymous"></script>

</head>
<body>

	 <div class="wrapper">

    <!-- HEADER -->
    <header style="position: relative;">
        <div>
         <nav class="sub-nav">
             <a id="login" href="#">로그인</a>     
<!--            <a href="#"><i class="fas fa-search sub-nav-logo"></i></a> -->
<!--              <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a> -->
            <a href="board-main.html">전체 게시판</a>
            <a href="#">나의 게시판</a>
            <a href="#">캘린더</a>
            <a href="#">로그아웃</a>
           
         </nav>    
      </div>      
      <div class="netflixLogo">
        <a id="logo" href="#home"><img src="img/fitcha.png" alt="Logo Image"></a>
      </div>      

    </header>
    <!-- END OF HEADER -->
    <h1 id="review-h1"><!-- ~님의 REVIEW (review-board.js) -->
    </h1>
    
    
    <div id="like-btn"></div>
	    <button type="button" class="btn btn-secondary" onclick="like()" style="border-radius: 50%">
			<i class="fas fa-heart text-light" ></i>
		</button>
	</div>
    
    
    
    <div id="review-box">
<!--    해당되는 게시물 보여주기 (review-board.js) -->
    </div>
    <%
		if(userId.equals(bvo.getUserId())){
						
	%>
    <div id="update-btn">
	    	<button type="submit" onclick="updateBoard()">수정</button>
	    	<button type="submit" onclick="deleteBoard()">삭제</button>
    </div>
    <%
		}
    %>

	
	
    <hr class="hr">
    <div id="comments">
		<div class="comment-row">
	    	<textarea id="new-comment" name="new_comment" rows=2 placeholder="댓글을 입력해주세요." ></textarea>
	    	<button type="submit" id="comment-btn" onclick="submitComment()">등록</button>
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