
	
function warnEmpty(){
	alert("댓글을 입력해주세요.");
}
function dateToString(date){
	const dateString = date.toISOString();
	const dateToString = dateString.substring(0,10) + " " + dateString.substring(11, 19);
	return dateToString;
}
function submitComment(){
	const newcommentEL = document.getElementById("new-comment");
	const newcomment = newcommentEL.value.trim();
	var boardId = document.location.href.split("=");
	
	
	if(newcomment){
		const dateEL = document.createElement('div');
		dateEL.classList.add("comment-date");
		const dateString = dateToString(new Date());
		dateEL.innerText = dateString;
		
		const writerEL = document.createElement('div');
		writerEL.classList.add('comment-writer');
		const writerString = "작성자";
		writerEL.innerText = writerString;
		
		const contentEL = document.createElement('div');
		contentEL.classList.add('comment-content');
		contentEL.innerText = newcomment;
		
		const commentEL = document.createElement('div');
		commentEL.classList.add('comment-row1');
		
		const hrEL = document.createElement('hr');
		hrEL.classList.add('hr');
		
		commentEL.appendChild(writerEL);
		commentEL.appendChild(dateEL);
		commentEL.appendChild(contentEL);
		commentEL.appendChild(hrEL);
		
		document.getElementById('comments').appendChild(commentEL);
		newcommentEL.value = "";
		
//		const countEL = document.getElementById('comment-count');
//		const count = countEL.innerText;
//		countEL.innerText = parseInt(count) +1;
		
		var xhrpost = new XMLHttpRequest();
		xhrpost.open("POST","comment",true);
		xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhrpost.send("boardId="+boardId[1]+"&newComment="+newcomment+"&userId="+writerString);
		
		
	}
	else warnEmpty();
	
}

//function insertComment(){
//	var boardId = document.location.href.split("=");
//	const newcomment = newcommentEL.value.trim();
//	//로그인된 아이디로 넣기
//	const writerString = "작성자";
//	
//	var xhrpost = new XMLHttpRequest();
//	xhrpost.open("POST","review_board",true);
//	xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//	xhrpost.send("boardId="+boardId[1]+"&content="+newcomment+"&userId="+writerString);
//	
//
//}