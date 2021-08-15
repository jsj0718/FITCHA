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
  <link rel="stylesheet" href="${pageContext.request.contextPath }/test/css/index.css">
  <script src="${pageContext.request.contextPath }/test/js/index.js"></script>

</head>
<body>

<!--   <nav id="navigator" class="navbar navbar-expand-lg navbar-dark" style="background-color: black;"> -->
<!--     <div class="container-fluid"> -->
<!--       <a class="navbar-brand" href="#"> -->
<%--         <img id="fitcha" alt="FITCHA" src="${pageContext.request.contextPath }/img/fitcha.png"> --%>
<!--       </a> -->
<!--       <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation"> -->
<!--         <span class="navbar-toggler-icon"></span> -->
<!--       </button> -->
<!--       <div class="collapse navbar-collapse align-items-end justify-content-between" id="navbarNav"> -->
<!--         <ul class="navbar-nav"> -->
<!--           <li class="nav-item"> -->
<%--             <a class="nav-link active" href="${pageContext.request.contextPath }/main-movie">홈</a> --%>
<!--           </li> -->
<!--           <li class="nav-item"> -->
<%--             <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath }/main-search">탐색하기</a> --%>
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a class="nav-link" href="#">My Page</a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a class="nav-link" href="#">게시판</a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a class="nav-link" href="#">찜 목록</a> -->
<!--           </li> -->
<!--         </ul> -->
<!--         <ul class="navbar-nav"> -->
<!--           <form class="d-flex"> -->
<!--             <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"> -->
<!--             <a class="btn"><i class="fas fa-search" type="submit"></i></a> -->
<!--           </form> -->
<!--           <li><a class="btn"><i class="far fa-bell"></i></a></li> -->
<!--           <li class="nav-item dropdown"> -->
<!--             <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false"> -->
<!--               회원 정보 -->
<!--             </a> -->
<!--             <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> -->
<!--               <li><a class="dropdown-item" href="#">회원 수정</a></li> -->
<!--               <li><a class="dropdown-item" href="#">회원 탈퇴</a></li> -->
<!--               <li class="dropdown-divider"></li> -->
<!--               <li><a class="dropdown-item" href="#">로그아웃</a></li> -->
<!--             </ul> -->
<!--           </li> -->
<!--         </ul> -->
<!--       </div> -->
<!--     </div> -->
<!--   </nav> -->
  <a class="fixed-top" href="https://fontmeme.com/ko/netflix-type/">
    <img id="fitcha" src="https://fontmeme.com/permalink/210815/a286ab8b02196be47db63b9059f49797.png" alt="netflix-type" border="0">
  </a>

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
  
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>