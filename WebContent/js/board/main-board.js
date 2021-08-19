window.onload = function(){
	
	var row = document.getElementById("row");
	var xhr = new XMLHttpRequest();
	xhr.open("GET","main_board",true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == XMLHttpRequest.DONE && xhr.status ==200){
			var jsonStr = xhr.responseText;
			var json = JSON.parse(jsonStr);
			var html ="";
//			console.log("JSON1: " + json);
			for(var i=0; i<json.length; i++){
				var movieTitle = json[i].title;
				if(movieTitle.length > 20){
					movieTitle = movieTitle.substring(0,19)+"...";
				}
				var boardId = json[i].boardId;
				html += "<div class='col-sm-4 col-md-2'>"
						+" <div class='thumbnail' onclick='thumbnail("+boardId+")'>"
						+" <img src= " + json[i].poster + " alt='포스터' class='thumbnail-img'>"
						+" <div class='caption'>"
						+" <h4 class='thumbnail-title'>" + movieTitle + "</h4>"
						+" <p class='thumbnail-writer'>" + json[i].userId +"</p></div></div></div>";
			}
			row.innerHTML = html;
		}
	
	}
	
	bestReview();
	
   

}
function bestReview(){
	
	var bestReview = document.getElementById("best-review");
	var xhr = new XMLHttpRequest();
	xhr.open("GET","main_board_best",true);
	xhr.send();
	
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == XMLHttpRequest.DONE && xhr.status ==200){
			var jsonStr = xhr.responseText;
			var json = JSON.parse(jsonStr);
			var html ="";
			var boardId = json[0].boardId;
//			console.log("JSON2: " + json[0].poster);
			html += "<div id='best-board-img'>"
					+ " <img src= " + json[0].poster +" alt='poster'></div>"
					+ " <div id='best-writer'>"
					+ " <div id='best-board-writer'>"
					+ " <p id='writer-p'>작성자</p>"
					+ " <p id='writer'> "+json[0].userId+" </p></div>"
					+ " <div id='best-board-title'>"
					+ " <p id='title-p'>제목</p>"
					+ " <p id='title' onclick='viewsCount()'><a href='review_board_view?boardId="+boardId+"'> "+json[0].title+"</a></p></div></div>";
				
			bestReview.innerHTML = html;
		}
	}
}

function changeSelection(){
	var selectedGenre = document.getElementById("genre");
	var selectedRecommend = document.getElementById("recommend");
	var genre = selectedGenre.options[selectedGenre.selectedIndex].value;
	var recommend = selectedRecommend.options[selectedRecommend.selectedIndex].value;
	var row = document.getElementById("row");
//	alert(genre);
	
	var xhrpost = new XMLHttpRequest();
	xhrpost.open("POST","main_board_select",true);
	xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrpost.send("genre="+genre+"&recommend="+recommend);
	
	xhrpost.onreadystatechange = function(){
		if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status ==200){
			var jsonStr = xhrpost.responseText;
			var json = JSON.parse(jsonStr);
//			console.log(jsonStr);
			var html ="";
			
			for(var i=0; i<json.length; i++){
				var movieTitle = json[i].title;
				if(movieTitle.length > 20){
					movieTitle = movieTitle.substring(0,19)+"...";
				}
				var boardId = json[i].boardId;
				html += "<div class='col-sm-4 col-md-2'>"
						+" <div class='thumbnail' onclick='thumbnail("+boardId+")'>"
						+" <img src= " + json[i].poster + " alt='포스터' class='thumbnail-img'>"
						+" <div class='caption'>"
						+" <h4 class='thumbnail-title'>" + movieTitle + "</h4>"
						+" <p class='thumbnail-writer'>" + json[i].userId +"</p></div></div></div>";
			}
			row.innerHTML = html;
				
		}
	}	
	
}


function searchInput(){
   var row = document.getElementById("row");
   var searchBox = document.getElementById("searchBox");
   var xhr = new XMLHttpRequest();
   xhr.open("POST","main_board_search",true);
   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   xhr.send("searchBox="+searchBox.value);
   
   xhr.onreadystatechange = function(){
      if(xhr.readyState == XMLHttpRequest.DONE && xhr.status ==200){
         var jsonStr = xhr.responseText;
         var json = JSON.parse(jsonStr);
         var html ="";
      
         console.log("JSON: " + json);
         for(var i=0; i<json.length; i++){
            var movieTitle = json[i].title;
            if(movieTitle.length > 20){
               movieTitle = movieTitle.substring(0,19)+"...";
            }
         
 	 var boardId = json[i].boardId;
             html += "<div class='col-sm-4 col-md-2'>"
						+" <div class='thumbnail' onclick='thumbnail("+boardId+")'>"
						+" <img src= " + json[i].poster + " alt='포스터' class='thumbnail-img'>"
						+" <div class='caption'>"
						+" <h4 class='thumbnail-title'>" + movieTitle + "</h4>"
						+" <p class='thumbnail-writer'>" + json[i].userId +"</p></div></div></div>";

         }
         row.innerHTML =html;
      }
   
   }

}








