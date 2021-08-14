$(function(){
  console.log("222")
  $('#content').click(function(){
    var scrollT = $(this).scrollTop(); //스크롤바의 상단위치
    var scrollH = $(this).height(); //스크롤바를 갖는 div의 높이
    var contentH = $('#divContent').height(); //문서 전체 내용을 갖는 div의 높이
    if(scrollT + scrollH +1 >= contentH) { // 스크롤바가 아래 쪽에 위치할 때
      // $('#divContent').append(imgs);
      console.log("#####")
    }
    console.log("###")
  })
  $('#content').click(function(){
    console.log("###")
  })
  // document.addEventListener('scroll', function(){
  //   console.log('scroll')
  // })
})
/*
$.ajax({
  type: 'get', // 타입 (get, post, put 등등)
  url: '${pageContext.request.contextPath }/popular-contents', // 요청할 서버url
  async: true, // 비동기화 여부 (default : true)
  headers: { // Http header
    "Content-Type": "application/json",
    "X-HTTP-Method-Override": "GET"
  },
  dataType: 'json', // 데이터 타입 (html, xml, json, text 등등)
  data: JSON.stringify({ // 보낼 데이터 (Object , String, Array)
    //               "id" : id
  }),
  success: function (result) { // 결과 성공 콜백함수
    console.log(result);
  },
  error: function (request, status, error) { // 결과 에러 콜백함수
    console.log(error)
  }
});
*/