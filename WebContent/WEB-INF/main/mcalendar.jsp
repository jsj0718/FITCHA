<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>FITCHA</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main/main.css" />
		<noscript><link rel="stylesheet" href="${pageContext.request.contextPath }/css/main/noscript.css" /></noscript>
		
		<link href='${pageContext.request.contextPath }/calendar-assets/main.css' rel='stylesheet' />
		
	    <script src='${pageContext.request.contextPath }/calendar-assets/main.js'></script>

<script>

	document.addEventListener('DOMContentLoaded', function() {
		var no = document.getElementById("no"); // 변수선언
		var mdate = document.getElementById("mdate");
		var title = document.getElementById("title");
		var memo = document.getElementById("memo-area");
		var saveBtn = document.getElementById("save-btn");
		var updateBtn = document.getElementById("update-btn");
		var deleteBtn = document.getElementById("delete-btn");
		
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView : 'dayGridMonth',
			dateClick : function(info) { // 날짜 클릭 시

				no.value=""; // 공백 값
				title.value="";
				memo.value="";

				mdate.value = info.dateStr; // info 날짜 값?
				
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
			eventClick : function(arg) { // 이벤트 클릭 시
				
				no.value = arg.event.id; // 넘버값 = id
				mdate.value = arg.event.startStr; // 날짜값 = 시작날짜
				title.value = arg.event.title; // 제목값 = 제목
				memo.value = arg.event.extendedProps.description; // 메모 값 = 메모에 적은 문장?
				
				saveBtn.setAttribute("type","hidden"); // 저장 히든
				updateBtn.setAttribute("type","button"); // 수정
				deleteBtn.setAttribute("type","button"); // 삭제
				
				location.href="${pageContext.request.contextPath }/mcalendar#memo"; // memo로 넘어감
				
				
			},
			events : ${mjson}

		});
		calendar.render(); // 달력 출력?
	
		const cancelBtn = document.getElementById("cancel-btn"); 
		var no = document.getElementById("no");
		var mdate = document.getElementById("mdate");
		var title = document.getElementById("title");
		var memo = document.getElementById("memo-area");
		
		cancelBtn.onclick = function() { // 취소 버튼이 눌렸을 때
			
			no.value="";
			mdate.value="";
			title.value="";
			memo.value="";
			
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
			xhr.open("POST","${pageContext.request.contextPath}/memo-regist", true); // post 통신
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send("mdate="+mdate.value+"&title="+title.value+"&memo="+memo.value); // 날짜는 날짜값, 제목은 제목값, 메모는 메모에 적힌 글
			
			xhr.onreadystatechange = function() {
				if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
					
					var code = xhr.responseText;
					
					if(code) {
						//성공
						location.href="${pageContext.request.contextPath}/mcalendar#home"; // 저장하고 캘린더로 돌아옴
						alert("메모 등록 성공")
					
						calendar.addEvent(
	 						{
	 							title : title.value,
	 							start : mdate.value,
	 							description : memo.value
	 						}
		 					);
						
					} else {
						//실패
						alert("메모 등록 실패")
						
					}
					
				}
			}
			
			//document.form.submit(); 
			//서버 요청 (페이지 이동)
		}
		
// 		const updateBtn = document.getElementById("update-btn");
		updateBtn.onclick = function() {
			
			document.form.action = "${pageContext.request.contextPath }/memo-update";
			document.form.submit();
		}
		
// 		const deleteBtn = document.getElementById("delete-btn");
		deleteBtn.onclick = function() {
			
			document.form.action = "${pageContext.request.contextPath }/memo-delete";
			document.form.submit();
		}
	
});
	
</script>

</head>
	<body class="is-preload">
	<!-- HEADER -->
    <header>
      <div class="netflixLogo">
        <a id="logo" href="#home"><img src="https://github.com/carlosavilae/Netflix-Clone/blob/master/img/logo.PNG?raw=true" alt="Logo Image"></a>
      </div>      
      <nav class="main-nav">                
        <a href="#home">Home</a>
        <a href="#tvShows">전체 게시판</a>
        <a href="#movies">나의 게시판</a>
        <a href="#originals">Originals</a>
        <a href="#">Recently Added</a>
<!--         <a target="_blank" href="https://codepen.io/cb2307/full/NzaOrm">Portfolio</a>         -->
      </nav>
      <nav class="sub-nav">
        <a href="#"><i class="fas fa-search sub-nav-logo"></i></a>
        <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a>
        <a href="login">로그아웃</a>        
      </nav>      
    </header>

		<!-- Wrapper-->
			<div id="wrapper">

				<!-- Nav -->
					<nav id="nav">
						<a href="#home" class="icon solid fa-home"><span></span></a>
<!-- 						<a href="#work" class="icon solid fa-folder"><span>Work</span></a> -->
						<a href="#memo" class="icon solid fa-envelope"><span></span></a>
<!-- 						<a href="https://twitter.com/ajlkn" class="icon brands fa-twitter"><span>Twitter</span></a> -->
					</nav>

				<!-- Main -->
					<div id="main">

						<!-- Me -->
							<article id="home" class="panel intro">
								<div id='calendar'></div>
							</article>
						
						<!-- memo -->
							<article id="memo" class="panel">
<!-- 								<header> -->
<!-- 									<h2>My Memo</h2> -->
<!-- 								</header> -->
								<form action="${pageContext.request.contextPath }/memo-regist" method="post" name="form">
									<input type="hidden" name="no" id="no">
								
									<div>
										<div class="row">
										<img src = "assets/css/img/avenger.jpg">
											<div>
												<input type="date" name="mdate" id="mdate">
											</div>
											<div class="col-12">
												<input type="text" name="title" id="title" placeholder="Title" />
											</div>
											<div class="col-12">
												<textarea name="memo" id="memo-area" placeholder="Memo" rows="6"></textarea>
											</div>
											<div class="col-12">
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
					<div id="footer">
						<ul class="copyright">
							<li>&copy; Untitled.</li><li>Design: <a href="#">FITCHA</a></li>
						</ul>
					</div>

			</div>
			
		<!-- Scripts -->
			<script src="${pageContext.request.contextPath }/js/main/jquery.min.js"></script>
			<script src="${pageContext.request.contextPath }/js/main/browser.min.js"></script>
			<script src="${pageContext.request.contextPath }/js/main/breakpoints.min.js"></script>
			<script src="${pageContext.request.contextPath }/js/main/util.js"></script>
			<script src="${pageContext.request.contextPath }/js/main/main.js"></script>

	</body>
</html>