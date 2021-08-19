//
////인기 있는 글 목록
//window.onload = function() {
//  // 장르, 나라 넣기
//  var getConnect = function(url, block) {
//    var xhrget = new XMLHttpRequest();
//
//    // 통신할 방식, url, 동기 여부 설정
//    xhrget.open("GET", url, true);
//    // 요청
//    xhrget.send();
//    // 응답
//    xhrget.onreadystatechange = function() {
//      if (xhrget.readyState == XMLHttpRequest.DONE && xhrget.status == 200) {
//
//        // 응답 값 (서버로부터 받아온 데이터)
//        var jsonStr = xhrget.responseText; // json 형태의 문자열
//        var json = JSON.parse(jsonStr); // 문자열을 json으로 변환
//
//        console.log(url);
//        console.log(jsonStr);
//
//        for (var i = 0; i < json.length; i++) {
//          $('#' + block)
//            .append($('<option>')
//              .attr('value', json[i].name)
//              .text(json[i].name))
//
//        }
//      }
//    }
//  }
//
//  getConnect("genre", "genre");
//  getConnect("country", "country");

//  var totalData;	// 총 데이터 수
//  var dataPerPage = 5;
//  var pageCount = 10;
//  var currentPage = 1;
  //	var genre= document.getElementById("genre");
  //	var recommend = document.getElementById("recommend");
  //	genre.onchange = function(){
  //		changeSelection(totalData, dataPerPage, pageCount, currentPage);
  //// 		paging(totalData, dataPerPage, pageCount, currentPage);
  //	}
  //	recommend.onchange = function(){
  //		changeSelection(totalData, dataPerPage, pageCount, currentPage);
  //// 		paging(totalData, dataPerPage, pageCount, currentPage);
  //	}




//  $.ajax({
//
//    type: 'GET',
//    url: 'my_board_popular.do',
//    dataType: 'json',
//    async: false,
//    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
//    data: {},
//    success: function(result) {
//      console.log(result);
//      console.log(result.length);
//
//      //			var poster = document.getElementById("best");
//      //			var str = "";
//
//      $.each(result, function(inx, obj) {
//
//
//        //				str += '<div id="arrange"><img src="' + obj.poster + '" alt="포스터" class="json_box" onclick="thumbnail('+ obj.boardId+'">'
//        //				 		+ '<div id="caption"><h4 id="review_title">'
//        //				 		+'<a href="#" class="btn_btn_primary" role="button">'+obj.title.substring(0,13)+"..."+'</a></h4>'
//        //				 		+'</div></div>';
//
//      });
//
//      //			poster.innerHTML = str;
//    },
//    error: function(XMLHttpRequest, textStatus, errorThrown) {
//      alert('There is an error2 : method(group)에 에러가 있습니다.');
//    }
//  });



//}

//검색용 js
//function searchInput() {
////  var searchBoxImage = document.getElementById("searchBoxImg");
//  var searchResultBox = document.getElementById("search-result-box");
//  var searchRow = document.getElementById("search-row");
//  var searchBox = document.getElementById("searchBox");
//
//  searchResultBox.style.display = "block";
//    
//  var xhr = new XMLHttpRequest();
//  xhr.open("POST", "my_board_search.do", true);
//  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//  xhr.send("searchBox=" + searchBox.value);
//
//  xhr.onreadystatechange = function() {
//    if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
//      var jsonStr = xhr.responseText;
//      var json = JSON.parse(jsonStr);
//      var str3 = "";
//
//      console.log(jsonStr)
//      console
//      for (var i = 0; i < json.length; i++) {
//        var boardTitle = json[i].boardtitle;
//        var boardContent = json[i].boardcontent;
//        if (boardTitle.length > 30) {
//          boardTitle = boardTitle.substring(0, 30) + "...";
//        }
//        if (boardContent.length > 30) {
//          boardContent = boardContent.substring(0, 30) + "...";
//        }
//
//        str3 += ' <div class="card col-3 m-3 bg-dark" style="width: 18rem;"> '
//              +   ' <img src="' + json[i].poster + '" class="json_box card-img-top" alt="..." onclick="thumbnail(' + json[i].boardId + ')"> '
//              +   ' <div class="card-body"> '
//              +     ' <h5 class="card-title text-light"> ' + boardTitle + ' </h5> '
//              +     ' <p class="card-text text-light"> ' + boardContent + ' </p> '
//              +   ' </div> '
//              + ' </div> ';
//
//      }
////      searchBoxImage.innerHTML = str3;
//      searchRow.innerHTML = str3;
//      console.log(str3);
//    }
//
//  }
//
//}

//------------------------------------------------------------------------------
//select용 js
//function changeSelection(){
//
//   var selectedGenre = document.getElementById("genre");
//   var selectedRecommend = document.getElementById("recommend");
//   var genre = selectedGenre.options[selectedGenre.selectedIndex].value;
//   var recommend = selectedRecommend.options[selectedRecommend.selectedIndex].value;
//   var row = document.getElementById("genreBox");
//
////   alert(genre);
//   
//   var xhrpost = new XMLHttpRequest();
//   xhrpost.open("POST","my_board_select.do",true);
//   xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//   xhrpost.send("genre="+genre+"&recommend="+recommend);
//   
//   xhrpost.onreadystatechange = function(){
//      if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
//         var jsonStr = xhrpost.responseText;
//         var json = JSON.parse(jsonStr);
//         console.log(jsonStr);
//         var html ="";
//	
//       		
//         
//         for(var i=0; i<json.length; i++){
//            var movieTitle = json[i].title;
//			
//            if(movieTitle.length > 20){
//               movieTitle = movieTitle.substring(0,19)+"...";
//            }
//            html += '<div id="arrange"><img src="' +  json[i].poster + '" alt="포스터" class="json_box">'
//				 		+ '<div id="caption"><h4 id="review_title">'
//				 		+'<a href="#" class="btn_btn_primary" role="button">'+movieTitle+'</a></h4>'
//				 		+'</div></div>';
//		    
//         }
//         row.innerHTML = html;
//     
//
//      }
//   }   
// 	
//}
//------------페이징처리 1----------------------------------
//function changeSelection(totalData, dataPerPage, pageCount, currentPage) {
//
//  var selectedGenre = document.getElementById("genre");
//  var selectedRecommend = document.getElementById("recommend");
//  var genre = selectedGenre.options[selectedGenre.selectedIndex].value;
//  var recommend = selectedRecommend.options[selectedRecommend.selectedIndex].value;
//  var row = document.getElementById("genreBox");
//
//  //   alert(genre);
//
//  var xhrpost = new XMLHttpRequest();
//  xhrpost.open("POST", "my_board_select.do", true);
//  xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//  xhrpost.send("genre=" + genre + "&recommend=" + recommend);
//
//  xhrpost.onreadystatechange = function() {
//    if (xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status == 200) {
//      var jsonStr = xhrpost.responseText;
//      var json = JSON.parse(jsonStr);
//      //         console.log(jsonStr);
//      var html = "";
//      totalData = json.length;
//
//      if (currentPage == 1) {
//        firstIndex = currentPage - 1;
//      } else {
//        firstIndex = (currentPage - 1) * dataPerPage;
//
//      }
//      //			console.log(dataPerPage*currentPage);
//      console.log(json);
//      //		for(var i=firstIndex; i<(dataPerPage*currentPage)-1; i++){
//      for (var i = firstIndex; i < (dataPerPage * currentPage) - 1; i++) {
//
//        html += '<div id="arrange"><img src= "' + json[i].poster + '" alt="포스터" class="json_box" onclick="thumbnail(' + json[i].boardId + '">'
//          + '<div id="caption"><h4 id="review_title">'
//          + '<a href="#" class="btn_btn_primary" role="button">' + json[i].title + '</a></h4>'
//          + '</div></div>';
//
//      }
//      //		for(var i=0; i<=json.length; i++){
//      //			html += '<div id="arrange"><img src= "' +  json[i].poster + '" alt="포스터" class="json_box">'
//      //				 		+ '<div id="caption"><h4 id="review_title">'
//      //				 		+'<a href="#" class="btn_btn_primary" role="button">'+json[i].title+'</a></h4>'
//      //				 		+'</div></div>';
//      //		}
//      row.innerHTML = html;
//
//      paging(totalData, dataPerPage, pageCount, currentPage);
//
//    }
//  }
//}
//
//
//
//
//function paging(totalData, dataPerPage, pageCount, currentPage) {
//  var totalPageDevide = totalData / dataPerPage;
//  var pageGroupDevide = currentPage / pageCount;
//
//
//  var totalPage = Math.ceil(totalPageDevide);    // 총 페이지 수
//  var pageGroup = Math.ceil(pageGroupDevide);    // 페이지 그룹
//  var last = pageGroup * pageCount + 1;   			//마지막 블록
//
//  if (last = totalPage)
//    last = totalPage;
//  var first = last - (pageCount - 1);
//  if (first <= 0) {
//    first = 1;
//  }
//  var next = last + 1; // 다음
//  var prev = first - 1; // 이전
//  var one = 1; // 맨 처음 
//  var lastNo = totalPage; // 맨 끝
//  var box = document.getElementById("box");
//  var html = "";
//
//  if (prev > 0)
//    html += "<a href=# id='one'>&lt;&lt;</a> ";
//  html += "<a href=# id='prev'>&lt;</a> ";
//
//
//  for (var i = first; i <= last; i++) {
//    html += "<a href='javascript:changeSelection(0, 6, 10, " + i + ")' id=" + i + ">" + i + "</a> ";
//  }
//
//  if (last < totalPage)
//    html += "<a href=# id='next'>&gt;</a>";
//  html += "<a href=# id='lastNo'>&gt;&gt;</a> ";
//
//
//
//  $(".paginate").html(html);    // 페이지 목록 생성
//  $(".paginate a").removeClass("page_on");
//
//  $(".paginate a#" + currentPage).addClass("page_on"); // 현재 페이지 표시	
//
//  $(".paginate a").click(function() {
//    var $item = $(this);
//    var $id = $item.attr("id");
//    var selectedPage = $item.text(); // 번호 클릭.
//
//
//
//    if ($id == "one") selectedPage = one;
//    if ($id == "prev") selectedPage = prev;
//    if ($id == "next") selectedPage = next;
//    if ($id == "lastNo") selectedPage = lastNo;
//
//    changeSelection(totalData, dataPerPage, pageCount, selectedPage);
//    //		paging(totalData, dataPerPage, pageCount, selectedPage);// 페이징
//  });
//}



//--------------------------------------페이징 처리 연습-----------------------------------------------


