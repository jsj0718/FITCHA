<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<%
    String id = (String) session.getAttribute("id");
    if (id == null) {
      response.sendRedirect(request.getContextPath() + "/sign-in");
    }
    
    String title = request.getParameter("title");
    if (title == null) {
      title = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
<title>FITCHA</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link href="https://fontmeme.com/permalink/210816/95cfd40502d9ebe4522b74e094042fcb.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<noscript>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/main/noscript.css" />
</noscript>
<link href='${pageContext.request.contextPath }/calendar-assets/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/calendar-assets/main.js'></script>

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
	document.addEventListener('DOMContentLoaded', function() {
		var dipsId = document.getElementById("dipsId"); // 값선언
		var userId = document.getElementById("userId");
		var movieId = document.getElementById("movieId");
		var ddate = document.getElementById("ddate");
		var image_url =  document.getElementById("image_url");
		var saveBtn = document.getElementById("save-btn");
		var updateBtn = document.getElementById("update-btn");
		var deleteBtn = document.getElementById("delete-btn");
		
		var movieTitle = document.getElementById("movieTitle");
		var dipsNo = document.getElementById("dipsNo");
		
		var img="";
		//var mjson = .getAttribute("mjson");
		
		<%
      String mjson2 = (String) request.getAttribute("movieJson");
      Gson gson = new Gson();
    %>
<%-- 		alert(<%=mjson1%>); --%>
<%-- 		alert(<%=json%>); --%>
		
		var calendarEl = document.getElementById('calendar');
		<%-- var obj, x;
		obj = {"id":"test"};
		for (x in <%=mjson1%>){
			alert(x);
			alert(obj[x]);
		} --%>
		var calendar = new FullCalendar.Calendar(calendarEl, {
			width: "100px",
			initialView : 'dayGridMonth',
			dayMaxEventRows: true,
			views: {
				dayGrid: {
				},
					timeGrid: {
					},
						dayMaxEventRows:6
			}
		,
			events: <%=mjson2%>
			,
			dateClick : function(info) { // 날짜 클릭 시, 해당날짜
				dipsNo.value=""; // 공백 값, null 값이 들어온다..
				movieTitle.value="";
				image_url.value="";
				ddate.value = info.dateStr; // info 날짜 값?
				
				saveBtn.setAttribute("type","button"); // 저장버튼
				updateBtn.setAttribute("type","hidden"); // 수정버튼 숨김
				deleteBtn.setAttribute("type","hidden"); // 삭제버튼 숨김
				
				location.href="${pageContext.request.contextPath }/mcalendar#memo"; // memo로 넘어감
				
// 				calendar.addEvent(
// 						{
// 							id : 'b',
// 							title : 'my event',
// 							start : info.dateStr
// 						}
// 						);
			
			},
		
			
			eventContent: function(arg){
				let arrayOfDomNodes = []
				// image event
// 				let imgEventWrap=document.createElement('div')
				let titleEventWrap=document.createElement('div')
// 				if(arg.event.extendedProps.image_url){
				if(arg.event.extendedProps.movieTitle){
// 					let imgEvent = '<img src="'+arg.event.extendedProps.image_url+'" >'
					let titleEvent = arg.event.extendedProps.movieTitle;
					//imgEventWrap.classList = "fc-event-img"
					titleEventWrap.classList = "span.fc-title"
					titleEventWrap.innerHTML=titleEvent;
				}
// 				arrayOfDomNodes=[imgEventWrap]
				arrayOfDomNodes=[titleEventWrap]
				
				return{ domNodes: arrayOfDomNodes }
			},
			
			eventClick : function(arg) { // 이벤트 클릭 시
				
				dipsNo.value = arg.event.extendedProps.dipsNo; // 넘버값 = id
// 				alert(arg.event.startStr);
				ddate.value = arg.event.startStr; // 날짜값 = 시작날짜
				movieTitle.value = arg.event.extendedProps.movieTitle; // 제목값 = 제목
// 				memo.value = arg.event.extendedProps.description; // 메모 값 = 메모에 적은 문장?
// 				image_url.value = arg.event.extendedProps.image_url;
				image_url.src = arg.event.extendedProps.image_url;
// 				alert("img경로:" +image_url.value);
				
				saveBtn.setAttribute("type","hidden"); // 저장 히든
				updateBtn.setAttribute("type","hidden"); // 수정
				deleteBtn.setAttribute("type","button"); // 삭제
				
				location.href="${pageContext.request.contextPath }/mcalendar#memo"; // memo로 넘어감
				
				
			},
// 			dayRender: function(date, cell){
// 				cell.html("<img src='" +img+"' >");
// 			}
			//events : ${mjson}
		});
		calendar.render(); // 달력 출력?
	
		const cancelBtn = document.getElementById("cancel-btn"); 
		var dipsId = document.getElementById("dipsId");
		var ddate = document.getElementById("ddate");
		var movieId = document.getElementById("movieId");
		var userId = document.getElementById("userId");
// 		var image_url = document.getElementById("image_url");
		var movieTitle = document.getElementById("movieTitle");
		var dipsNo = document.getElementById("dipsNo");
		
		cancelBtn.onclick = function() { // 취소 버튼이 눌렸을 때
			
			dipsNo.value="";
			ddate.value="";
			movieTitle.value="";
// 			image_url.value="";
			
			var saveBtn = document.getElementById("save-btn");
			var updateBtn = document.getElementById("update-btn");
			var deleteBtn = document.getElementById("delete-btn");
			
			saveBtn.setAttribute("type","button"); // 저장버튼
			updateBtn.setAttribute("type","hidden"); // 수정버튼 숨김
			deleteBtn.setAttribute("type","hidden"); // 삭제버튼 숨김
			
			location.href = "${pageContext.request.contextPath }/mcalendar#home"; // 캘린더로 돌아옴
		}
		
// 		const saveBtn = document.getElementById("save-btn");
		saveBtn.onclick = function() { // 저장버튼을 누를 시
			//ajax 통신 요청
			var xhr = new XMLHttpRequest();
			xhr.open("POST","${pageContext.request.contextPath}/Dips-regist", true); // post 통신
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send("ddate="+ddate.value+"&movieId="+movieId.value+"&userId="+userId.value); // 날짜는 날짜값, 제목은 제목값, 메모는 메모에 적힌 글 +"&memo="+memo.value
			
// 			xhr.onreadystatechange = function() {
// 				if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
					
// 					var code = xhr.responseText;
					
// 					if(code) {
// 						//성공
// 						location.href="${pageContext.request.contextPath}/mcalendar#home"; // 저장하고 캘린더로 돌아옴
// 						alert("메모 등록 성공")
					
// 						calendar.addEvent(
// 	 						{
// 	 							movieId : title.value,
// 	 							start : ddate.value,
// 	 							description : memo.value
// 	 						}
// 		 					);
						
// 					} else {
// 						//실패
// 						alert("메모 등록 실패")
						
// 					}
					
// 				}
// 			}
			
			//document.form.submit(); 
			//서버 요청 (페이지 이동)
		}
		
// 		const updateBtn = document.getElementById("update-btn");
// 		updateBtn.onclick = function() {
			
// 			document.form.action = "${pageContext.request.contextPath }/dips-update";
// 			document.form.submit();
// 		}
		
// 		const deleteBtn = document.getElementById("delete-btn");
		deleteBtn.onclick = function() {
			
			document.form.action = "${pageContext.request.contextPath }/dips-delete";
			document.form.submit();
		}
	
});
	
</script>

</head>
<body class="is-preload bg-black">
  <!-- HEADER -->
  <header>
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
              <a class="nav-link" href="${pageContext.request.contextPath }/main_board_view">게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/mcalendar">찜 목록</a>
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
  </header>

  <!-- Wrapper-->
  <div id="wrapper">

    <!-- Nav -->
    <nav id="nav">
      <a href="#home" class="icon solid fa-home">
        <span></span>
      </a>
      <!-- 						<a href="#work" class="icon solid fa-folder"><span>Work</span></a> -->
      <a href="#memo" class="icon solid fa-envelope">
        <span></span>
      </a>
      <!-- 						<a href="https://twitter.com/ajlkn" class="icon brands fa-twitter"><span>Twitter</span></a> -->
    </nav>

    <!-- Main -->
    <div id="main">

      <!-- Me -->
      <article id="home" class="panel intro">
        <div id='calendar'  class="text-light text-white w-50 position-absolute top-50 start-50 translate-middle" style="font-color:white"></div>
      </article>

      <!-- memo -->
      <article id="memo" class="panel">
        <!-- 								<header> -->
        <!-- 									<h2>My Memo</h2> -->
        <!-- 								</header> -->
        <form action="${pageContext.request.contextPath }/Dips-regist" method="post" name="form">
          <input type="hidden" name="no" id="no">

          <div class="w-50 h-50 d-inline-block position-relative position-absolute top-50 start-50 translate-middle">
            <div class="row d-grid gap-4 ">
            
              <div class=" fw-bolder fs-4 position-absolute top-0 end-0 text-end">
                <input type="date" name="ddate" id="ddate" class="mt-5" readonly>
              </div>
              
              <div class="col-12  fw-bolder fs-4 position-absolute top-50 end-0 translate-middle-y text-end" >
                <input type="text" name="movieTitle" id="movieTitle" placeholder="movieTitle" readonly>
              </div>
              
              <div class="col-12  fw-bolder fs-4 position-absolute bottom-0 end-0 text-end">
                <input type="text" name="dipsNo" id="dipsNo" class="mb-5" readonly>
              </div>

              <div class="col-12 mw-100 position-absolute top-50 start-0 translate-middle-y">
                <img src='' id="image_url" name="image_url" alt="포스터" style="width:300px" />
                <!-- 												out.print("Poster"); -->
                <!-- 												<input type="text" name="image_url" id="image_url" > -->
              </div>
              <!-- 											<div class="col-12"> -->
              <!-- 												<textarea name="memo" id="memo-area" placeholder="Memo" rows="6"></textarea> -->
              <!-- 											</div> -->
              <div class="fw-bolder position-absolute top-100 start-50 translate-middle text-center">
                <input type="button" value="저장" id="save-btn">
                <input type="hidden" value="수정" id="update-btn">
                <input type="hidden" value="삭제" id="delete-btn">
                <input type="button" value="취소" id="cancel-btn">
              </div>
            </div>
          </div>
        </form>
      </article>

    </div>

    <!-- Footer -->
<!--     <div id="footer text-center"> -->
<!--       <ul class="copyright text-light"> -->
<!--         <li>&copy; Untitled.</li> -->
<!--         <li> -->
<!--           Design: -->
<!--           <a href="#" class="text-light">FITCHA</a> -->
<!--         </li> -->
<!--       </ul> -->
<!--     </div> -->

  <footer class="text-center">
    <p class="text-light">&copy 2021 FITCHA, Inc.</p>
    <p class="text-light">KITRI &copy 2021</p>
  </footer>

  </div>
  
  <!-- Scripts -->
  <script src="${pageContext.request.contextPath }/js/main/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath }/js/main/browser.min.js"></script>
  <script src="${pageContext.request.contextPath }/js/main/breakpoints.min.js"></script>
  <script src="${pageContext.request.contextPath }/js/main/util.js"></script>
  <script src="${pageContext.request.contextPath }/js/main/main.js"></script>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
  
</body>
</html>