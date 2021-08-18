<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String id = (String) session.getAttribute("id");
  if (id != null) {
      response.sendRedirect(request.getContextPath() + "/main-movie");
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>FITCHA</title>
  <link href="https://fontmeme.com/permalink/210816/95cfd40502d9ebe4522b74e094042fcb.png" rel="shortcut icon" type="image/x-icon">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/test/css/sign.css">
  <script type="text/javascript">  
    window.onload = function() {
      const moveToSignin = document.getElementById("move-to-signin");
      moveToSignin.onclick = function() {
        location.href = "${pageContext.request.contextPath}/sign-in";
      }  
    }
  </script>
</head>
<body style="color: #e5e5e5;">
  <!-- Carausel -->
  <div id="background-image" class="carousel slide carousel-fade" style="position: fixed" data-bs-ride="carousel">
    <div class="carousel-inner opacity-75">
      <div class="carousel-item active">
        <img src="${pageContext.request.contextPath }/img/login13.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login20.jpg" class="d-inline w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login19.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login6.jpg" class="d-block w-100" alt="...">
      </div>
    </div>
  </div>

  <nav class="navbar navbar-light bg-transparent fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/sign-up">
        <img id="fitcha" class="fixed-top m-3" style="height: auto; width: 100px" src="https://fontmeme.com/permalink/210815/a286ab8b02196be47db63b9059f49797.png" alt="netflix-type" border="0">
      </a>
      <div class="d-flex m-3">
        <button type="button" class="btn btn-light btn-lg" id="move-to-signin">로그인</button>      
      </div>
    </div>
  </nav>

  <div class="container-fluid position-absolute top-50 start-50 translate-middle">   
    <div class="row justify-content-center">
      <form class="col-4" action="${pageContext.request.contextPath}/sign-up" method="POST">
        <div class="mb-4">
          <label for="sign-in" class="form-label fs-4 text-center" style="font-size: 1.4vw; font-weight: 700;">회원가입</label>
          <input type="text" class="form-control" id="user-id" name="user-id" placeholder="아이디 (5~20자 영문, 한글)" required>
        </div>
        <div class="mb-4">
          <input type="password" class="form-control" id="user-password" name="user-password" placeholder="비밀번호 (8~20자 영문, 숫자, 특수문자 포함)" required>
        </div>
        <div class="mb-4">
          <input type="password" class="form-control" id="user-password-check" name="user-password-check" placeholder="비밀번호 확인" required>
        </div>
        <div class="mb-4">
          <input type="text" class="form-control" id="user-name" name="user-name" placeholder="이름 (2~30자 영문, 한글)" required>
        </div>
        <div class="mb-4">
          <input type="email" class="form-control" id="user-email" name="user-email" placeholder="이메일" required>
        </div>
        <div class="mb-4">
          <input type="date" class="form-control" id="user-birth" name="user-birth" required>
        </div>
        <div class="mb-4 text-center">
          <div class="form-check">
            <input type="radio" class="form-check-input btn-check" name="user-gender" id="male" value="male" autocomplete="off" checked>
            <label class="btn btn-outline-light" for="male">남자</label>
            <input type="radio" class="form-check-input btn-check" name="user-gender" id="female" value="female" autocomplete="off">
            <label class="btn btn-outline-light" for="female">여자</label>            
          </div>
        </div>
        <p class="text-center text-danger">${requestScope.msg }</p>
        <div class=" d-grid gap-2">
          <button type="submit" class="btn btn-danger">회원가입</button>
        </div>      
      </form>    
    </div>
  </div>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>