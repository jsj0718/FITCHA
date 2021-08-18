<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    
    
    
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main/login.css">
<script src="${pageContext.request.contextPath }/js/main/login.js"></script>
</head>
<body>
	<div class="cotn_principal">
		<div class="backcolor">
			<div class="cont_centrar">

				<div class="cont_login">
					<div class="cont_info_log_sign_up">
						<div class="col_md_login">
							<div class="cont_ba_opcitiy">



								<h2>LOGIN</h2>
								<p>Please Click the Login Button</p>
								<button class="btn_login" onclick="cambiar_login()">LOGIN</button>
							</div>
						</div>
						<div class="col_md_sign_up">
							<div class="cont_ba_opcitiy">
								<h2>SIGN UP</h2>


								<p>Please Click the Sign-up Button</p>

								<button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN
									UP</button>
							</div>
						</div>
					</div>


					<div class="cont_back_info">
						<div class="cont_img_back_grey">
							<img
								src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
								alt="" />
						</div>

					</div>
						<div class="cont_forms">
							<div class="cont_img_back_">
								<img
									src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
									alt="" />
							</div>
					<form action="${pageContext.request.contextPath}/index-login" method="post" name="loginForm" id="loginForm">
							<div class="cont_form_login">
								<a href="#" onclick="ocultar_login_sign_up()"><i
									class="material-icons">&#xE5C4;</i></a>
								<h2>LOGIN</h2>
								<input type="text" placeholder="Id" name="id" required/> 
								<input type="password" placeholder="Password" name="pw" required/>
								<span style="color:red; font-size=12px;">${requestScope.msg }</span>
								<input type="submit" class="btn_login" id="loginBtn" value="LOGIN" />
							</div>
					</form>



					<form action="${pageContext.request.contextPath}/index-join" method="post" name="signUpForm" id="signUpForm">
						<div class="cont_form_sign_up">
							<a href="#" onclick="ocultar_login_sign_up()">
								<i class="material-icons"></i>
							</a>
							<h2>SIGN UP</h2>
							<input type="text" placeholder="Name" name="join-name"/>
							<input type="text" placeholder="Id" name="join-id" /> 
							<input type="password" placeholder="Password" name="join-pw" /> 
							<input type="password" placeholder="Confirm Password" /><br>
							<input type="email" placeholder="EMAIL" name="join-email" />
							<input type="radio" name="gender">Male, <input type="radio" name="gender">Female <br>
							Birth Date: <input type="date" name="birth"><br>
							<span style="color:red; font-size=12px;">${requestScope.msg }</span>
							<input type="submit" class="btn_sign_up" id="signin-btn" onclick="cambiar_sign_up()" value="SIGN UP"/>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	</div>
</body>
</html>