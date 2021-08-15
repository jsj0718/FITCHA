<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>FITCHA</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<%--   <link rel="stylesheet" href="${pageContext.request.contextPath }/test/css/index.css"> --%>
  <script src="${pageContext.request.contextPath }/test/js/index.js"></script>

</head>
<body>
  <!-- Carausel -->
  <div id="background-image" class="carousel slide carousel-fade" style="position: fixed" data-bs-ride="carousel">
    <div class="carousel-inner opacity-50">
      <div class="carousel-item active">
        <img src="${pageContext.request.contextPath }/img/login5.jpg" class="d-inline w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login6.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login7.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath }/img/login9.jpg" class="d-block w-100" alt="...">
      </div>
    </div>
  </div>

  <nav class="navbar navbar-light bg-transparent">
    <div class="container-fluid">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/login">
        <img id="fitcha" class="fixed-top m-3" src="https://fontmeme.com/permalink/210815/a286ab8b02196be47db63b9059f49797.png" alt="netflix-type" border="0">
      </a>
      <div class="d-flex m-3 pt-4">
        <button type="button" class="btn btn-light btn-lg">회원가입</button>      
      </div>
    </div>
  </nav>

  <div class="container-fluid position-absolute top-50 start-50 translate-middle">   
    <div class="row justify-content-center">
      <form class="col-4">
        <div class="mb-3">
          <label for="sign-in" class="form-label fs-4 text-center">로그인</label>
          <input type="text" class="form-control" id="user-id" name="user-id" placeholder="아이디">
        </div>
        <div class="mb-3">
          <input type="password" class="form-control" id="user-password" name="user-password" placeholder="비밀번호">
        </div>
        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="cookie-check" name="cookie-check">
          <label class="form-check-label" for="cookie-check">Check me out</label>
        </div>
        <div class=" d-grid gap-2">
          <button type="submit" class="btn btn-danger">login</button>
        </div>      
      </form>    
    </div>
  </div>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>