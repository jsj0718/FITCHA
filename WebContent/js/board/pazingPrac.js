//window.onload = function(){
//	var totalData ;	// 총 데이터 수
//	var dataPerPage = 4;	
//	var pageCount = 10;		
//	var currentPage = 1;	
//	
//	snsData(totalData, dataPerPage, pageCount, currentPage); 
//	
//}

function snsData(totalData, dataPerPage, pageCount, currentPage){

 	 var allData = { "page": currentPage };
// 	 alert(allData);
	$.ajax({
		url: 'my_board_popular.do',	
		type: 'GET',
		dataType: 'json',
		async:false,
 		data: allData,
		success: function(result){
			$("#box").empty();
			totalData = result.length; 
//			console.log(alldata);
			
			if(currentPage==1){
				firstIndex = currentPage-1;
			}else{
		
				firstIndex = (currentPage-1)*dataPerPage;
			
			}
			for(var i=firstIndex; i<=(dataPerPage*currentPage)-1; i++){			
					var exText = "";
			 		exText += "<div><div class='box'>";						             	
					exText += "<a href='#'>";					            	   
					exText += "<div class='tit'>" + result[i].title + "</div>";					
					exText += "<div><img src='" + result[i].poster + "'alt='포스터'";				
					exText += "</div>";			
					exText += "</a>";
					exText += "</div></div>";
					$("#box").append(exText);
				}
			
		
			paging(totalData, dataPerPage, pageCount, currentPage);
		}
	});
}

function paging(totalData, dataPerPage, pageCount, currentPage){
	var totalPageDevide = totalData/dataPerPage;
	var pageGroupDevide = currentPage/pageCount;
	
	
	var totalPage = Math.ceil(totalPageDevide);    // 총 페이지 수
	var pageGroup = Math.ceil(pageGroupDevide);    // 페이지 그룹
	var last = pageGroup * pageCount+1;   			//마지막 블록
	
	if(last = totalPage)
		last = totalPage;
	var first = last - (pageCount-1);   
	if(first <= 0){
		first = 1;
	}
	var next = last+1; // 다음
	var prev = first-1; // 이전
	var one = 1; // 맨 처음 
	var lastNo = totalPage; // 맨 끝
	var box = document.getElementById("box");
	var html = "";
   
   if(prev > 0){
		html += "<a href=# id='one'>&lt;&lt;</a> ";
		html += "<a href=# id='prev'>&lt;</a> ";
		
	}
	for(var i=first; i <= last; i++){
		html += "<a href='PageController?page=" + i + "' id=" + i + ">" + i + "</a> ";
	}

	if(last < totalPage){
		html += "<a href=# id='next'>&gt;</a>";
		html += "<a href=# id='lastNo'>&gt;&gt;</a> ";
	}

		
	$(".paginate").html(html);    // 페이지 목록 생성
	$(".paginate a").removeClass("page_on");
	
	$(".paginate a#" + currentPage).addClass("page_on"); // 현재 페이지 표시	

	$(".paginate a").click(function(){
		var $item = $(this);
		var $id = $item.attr("id");
		var selectedPage = $item.text(); // 번호 클릭.
		
		
			
		if($id == "one")    selectedPage = one;
		if($id == "prev")    selectedPage = prev;
		if($id == "next")    selectedPage = next;
		if($id == "lastNo")    selectedPage = lastNo;
		
		snsData(totalData, dataPerPage, pageCount, selectedPage);
		paging(totalData, dataPerPage, pageCount, selectedPage);// 페이징
	});
}