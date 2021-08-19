window.onload = function(){
	
	var boardId = document.location.href.split("="); 
	var reviewBox = document.getElementById("review-box");
	var reviewH1 = document.getElementById("review-h1");

	var xhrpost = new XMLHttpRequest();
	xhrpost.open("GET","review_board?boardId="+boardId[1],true);
	xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrpost.send();
	
	
	xhrpost.onreadystatechange = function(){
		if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
			var jsonStr = xhrpost.responseText;
			var json = JSON.parse(jsonStr);
			var html ="";
			var html2 ="";
			if(userId == json[0].userId){
				html += "<div id='movie-img'>"
    					+ " <img src=" + json[0].poster + " class='img-fluid' alt='Logo Image'></div>"
						+ " <div class='write-box'>"
						+ " <div id='review-title'>"	
						+ " <input type='text' name='title' id='title' value='"+json[0].title+"'></div>"
						+ " <div id='review-content'>"
						+ " <textarea name='content' id='content' cols='65' rows='10'>"+json[0].content+"</textarea></div></div>"	
						+ " <div id='movie-story'>" + json[0].story + "</div>";
			}else{
				
				html += "<div id='movie-img'>"
    					+ " <img src=" + json[0].poster + " class='img-fluid' alt='Logo Image'></div>"
						+ " <div class='write-box'>"
						+ " <div id='review-title'>"	
						+ " <input type='text' name='title' id='title' value='"+json[0].title+"' disabled></div>"
						+ " <div id='review-content'>"
						+ " <textarea name='content' id='content' cols='65' rows='10' disabled>"+json[0].content+"</textarea></div></div>"	
						+ " <div id='movie-story'>" + json[0].story + "</div>";
				
				
			}
					
				html2 += json[0].userId+"님의 Review";
						
			
			reviewBox.innerHTML = html;
			reviewH1.innerHTML = html2;
				
		}
	}	
	viewsCount();
	commentLoad();

}

//댓글로드
function commentLoad(){
	
	var boardId = document.location.href.split("="); 
	var comment = document.getElementById("comment-row1");

	var xhrpost = new XMLHttpRequest();
	xhrpost.open("POST","comment_load",true);
	xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrpost.send("boardId="+boardId[1]);
	
	xhrpost.onreadystatechange = function(){
		if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
			var jsonStr = xhrpost.responseText;
			var json = JSON.parse(jsonStr);
			var html ="";
			for(var i=0; i<json.length; i++){
				html += "<div id='comment-box'>"
			   			+" <div class='comment-writer'>"+json[i].userId+"</div>"
					    +" <div class='comment-date'>"+json[i].cdate+"</div></div>"
	    				+" <div class='comment-content'>"+json[i].newComment+"</div>"
	    				+" <hr class='hr'>";
				}	
			
			comment.innerHTML = html;
				
		}
	}	
}

//게시물 수정
function updateBoard(){
	var boardId = document.location.href.split("=");
	var title = document.getElementById("title");
	var content = document.getElementById("content");
	var xhrpost = new XMLHttpRequest();
	
	 if(title.value==""){
		 alert("제목을 입력하세요");
         title.focus();
         return;
      }
     if(content.value==""){
         alert("내용을 입력하세요");
         content.focus();
         return;
      }


	xhrpost.open("POST","update_board",true);
	xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrpost.send("boardId="+boardId[1]+"&title="+title.value+"&content="+content.value);
	
	xhrpost.onreadystatechange = function(){
		if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
			 alert("수정이 완료되었습니다.");
				
		}
	}	
	
	
}

//게시물 삭제
function deleteBoard(){
	var boardId = document.location.href.split("=");
	
	if (window.confirm('정말 삭제하시겠습니까?')) {
	var xhrpost = new XMLHttpRequest();
	xhrpost.open("POST","delete_board",true);
	xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrpost.send("boardId="+boardId[1]);
	
		xhrpost.onreadystatechange = function(){
		if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
			 alert("삭제되었습니다.");
			 location.href="main_board_view";
			 deleteComment();
				
			}
		}
	}else{
	    return;
	}
}

//게시물 삭제시 댓글db삭제
function deleteComment(){
	var boardId = document.location.href.split("=");
	var xhrpost = new XMLHttpRequest();
	xhrpost.open("POST","delete_comment",true);
	xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrpost.send("boardId="+boardId[1]);
	
//		xhrpost.onreadystatechange = function(){
//		if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
//			 alert("삭제되었습니다.");
//			 location.href="main_board_view";
//				
//			}
//		}
	
}

//게시글 좋아요
function like(){
	var boardId = document.location.href.split("=");
	var xhrpost = new XMLHttpRequest();
	xhrpost.open("GET","like_board?boardId="+boardId[1],true);
	xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrpost.send();
	
	
	xhrpost.onreadystatechange = function(){
		if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
			alert("좋아요를 눌렀습니다.");
			}
		}
	
}

//조회수증가
function viewsCount(){
	var boardId = document.location.href.split("=");
	var xhrpost = new XMLHttpRequest();
		xhrpost.open("POST","views_board",true);
		xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhrpost.send("boardId="+boardId[1]);
		xhrpost.onreadystatechange = function(){
			if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
				}
			}
}



