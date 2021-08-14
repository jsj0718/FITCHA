//     $('#myModal').on('shown.bs.modal', function () {
//         $('#myInput').focus()
// 	})

window.onload = function () {
  // mouseover 시 상세 썸네일 보여주기
  //     	var timer;      
  //         $('#test1').on({
  //             'mouseenter': function () {
  //                 timer = setTimeout(function () {
  //                     console.log(1);
  //                     var element1 = document.getElementById("test1");
  //                     var element2 = document.getElementById("test2");
  //                     element1.style.display = 'none';
  //                     element2.style.display = 'block';
  //                 }, 1);
  //             }, function() {
  //             	clearTimeout(timer);
  //             }
  //         });

  //         $('#test2').on({
  //         	'mouseleave' : function () {
  //                 var element1 = document.getElementById("test1");
  //                 var element2 = document.getElementById("test2");
  //                 element1.style.display = 'block';
  //                 element2.style.display = 'none';
  //             }
  //         });

  //      var timer;
  //      $('#next').on({
  //          'click': function () {
  //              timer = setTimeout(function () {
  // //                   var curPage = document.getElementById("myCarousel1").querySelector("ol").querySelector(".active").getAttribute("data-slide-to");
  // //                   var popularContents = document.querySelector(".item.active div");

  //                  postConnect(curPage, popularContents);
  //                  console.log(popularContents);
  //              }, 1000);
  //          }, function() {
  //              clearTimeout(timer);
  //          }
  //      });

  //      $('#prev').on({
  //             'click': function () {
  //                 timer = setTimeout(function () {
  //                  var curPage = document.getElementById("myCarousel1").querySelector("ol").querySelector(".active").getAttribute("data-slide-to");
  //                     var popularContents = document.querySelector(".item.active div");

  //                     postConnect(curPage, popularContents);
  //                     console.log(popularContents);
  //                 }, 1000);
  //             }, function() {
  //              clearTimeout(timer);
  //             }
  //         });

  //     $.ajax({
  //          type : 'post', // 타입 (get, post, put 등등)
  //          url : '${pageContext.request.contextPath }/popular-contents', // 요청할 서버url
  //          async : true, // 비동기화 여부 (default : true)
  //          headers : { // Http header
  //              "Content-Type" : "application/json",
  //              "X-HTTP-Method-Override" : "POST"
  //          },
  //          dataType : 'json', // 데이터 타입 (html, xml, json, text 등등)
  //          data : JSON.stringify({ // 보낼 데이터 (Object , String, Array)
  // //               "id" : id
  //          }),
  //          success : function(result) { // 결과 성공 콜백함수
  //              console.log(result);
  //          },
  //          error : function(request, status, error) { // 결과 에러 콜백함수
  //              console.log(error)
  //          }
  //      });


  // 통신 X, 클라이언트 단에서 처리하는 로직
  //         var dataBtn = document.getElementById("dataBtn");
  //         dataBtn.onclick = function() {
  //             var dataBtn = document.getElementById("dataBtn");
  //             text.innerHTML = "데이터 수정됨";
  //         }

  //         // ajax Get방식 통신
  //         var ajaxGetBtn = document.getElementById("ajaxGetBtn");
  //         ajaxGetBtn.onclick = function() {
  //             //XMLHttpRequest 객체 생성
  //             var xhrget = new XMLHttpRequest();
  //             // ajax 방식 설정
  //             xhrget.open("GET", "ajax-server", true);
  //             // 요청 보내기
  //             xhrget.send();

  //             // 응답 성공
  //             xhrget.onreadystatechange = function() {

  //                 if(xhrget.readyState == XMLHttpRequest.DONE && xhrget.status == 200) {
  //                     // 응답 값 (서버로부터 받아온 데이터)
  //                     var text = document.getElementById("text");
  //                     text.innerHTML = xhrget.responseText;
  //                 }
  //             }
  //         }

  //         var ajaxPostBtn = document.getElementById("ajaxPostBtn");
  //         ajaxPostBtn.onclick = function() {
  //             // XMLHttpRequest 객체 생성 (ajax 통신을 위한 객체)
  //             var xhrpost = new XMLHttpRequest();

  //             // 통신할 방식, url, 동기 여부 설정
  //             xhrpost.open("POST", "ajax-server", true);
  //             // post 요청을 하기 위한 header 세팅
  //             xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  //             // 요청
  //             xhrpost.send("id=admin&name=관리자");

  //             // 응답
  //             xhrpost.onreadystatechange = function() {
  //                 if(xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status == 200) {
  //                     // 응답 값 (서버로부터 받아온 데이터)
  //                     var jsonStr = xhrpost.responseText; // json 형태의 문자열
  //                     var json = JSON.parse(jsonStr); // 문자열을 json으로 변환

  //                     var jsonBox = document.getElementById("json-box");
  //                     var html = "";
  //                     for (var i=0; i<json.length; i++) {
  //                         html += "<div> id : " + json[i].id + "<div>"
  //                                 + "<div> name : " + json[i].name + "<div>";
  //                     }
  //                     jsonBox.innerHTML = html;
  //                 }
  //             }

  //         }

  var postConnect = function (url) {
    var xhrpost = new XMLHttpRequest();

    // 통신할 방식, url, 동기 여부 설정
    xhrpost.open("POST", url, true);
    // post 요청을 하기 위한 header 세팅
    xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    // 요청
    xhrpost.send();
    // 	        xhrpost.send("curpage="+curpage);
    // 	        console.log(curpage);
    // 응답
    xhrpost.onreadystatechange = function () {
      if (xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status == 200) {
        // 	            	var popularContents = document.querySelector(".item.active div");
        var html = "";


        // 응답 값 (서버로부터 받아온 데이터)
        var jsonStr = xhrpost.responseText; // json 형태의 문자열
        var json = JSON.parse(jsonStr); // 문자열을 json으로 변환

        console.log(jsonStr);
        var block;
        for (var i = 0; i < json.length; i++) {
          block = document.querySelector("#row-" + (i / 6));
          html += '<div class="col-xs-4 col-md-2"> ' +
                      '<img src="' + json[i].poster + '" class="img-thumbnail" alt="..." data-toggle="modal" data-target="#myModal"> ' +
                   '</div> ';
          block.innerHTML = html;
        }
      }
    }
  }

  postConnect("recent-contents");
  
  
}