<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload = function(){
	var totalData ;	// 총 데이터 수
	var dataPerPage = 2;	
	var pageCount = 10;		
	var currentPage= 1;	
	
	
	snsData(totalData, dataPerPage, pageCount, currentPage); 
// 	paging(totalData, dataPerPage, pageCount, selectedPage);
}

</script>
<style type="text/css">
.page-number{ margin-right:50px;}
.clickable {cursor: pointer;}
.hover {text-decoration: underline;}
.odd{ background: #FFC;}
.even{ background: #FF9;}
.active{ width:10px; height:10px; background:#f60; color:white;}
#tbl{
		display: inline-block;
}
#box{
	margin: 0 auto;
	display: inline-block;
}
</style>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/board/test.js" defer></script>
</head>
<body>


<!-- <table class="tbl paginated" id="tbl"> -->

<!-- </table> -->
<form action="PageController">
<button type="submit"></button>
</form>
<div id="test"> </div>
<div id ="box"> </div>
<div class="paginate" ></div>  
<table class="tbl paginated" id="tbl"> </table>

</body>
</html>