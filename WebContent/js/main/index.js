window.onload = function(){
	
   var navBox = document.getElementById("navBox");
  
   var xhr = new XMLHttpRequest();
   xhr.open("GET","myBoardView",true);
//   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   xhr.send();
   
   xhr.onreadystatechange = function(){
      if(xhr.readyState == XMLHttpRequest.DONE && xhr.status ==200){
         var data = xhr.responseText;
         var str3 ="";
			
           	str3 += '<a href="myBoardView">나의게시판</a>';

         
         navBox.innerHTML =str3;
      }
   
   }
}