let option = {
  attributes: true,
}

// DOM 변화 감지 (MovieTrailer의 속성을 삭제)
let modalObserver = new MutationObserver((mutations) => {
  $('#movieTrailer').attr('src', '')
})
// 삭제 후 modal의 속성을 true로 변환
//modalObserver.observe(document.querySelector('#movieModal'), option)

// 모달창 띄우기
let openModal = function(movieid) {
  $('#movieTitle').empty()
  $('#movieTrailer').empty()
  $('#moviePoster').attr('src', '')
  $('#movieStory').empty()
  $('#movieOpenDate').empty()
  $('#movieGrade').empty()
  $('#movieCountry').empty()
  $('#movieRunningTime').empty()
  $('#movieGenre').empty()
  $('#movieStaff').empty()
  $('#staffList').empty()
  $("#dipBtn").empty()
  $("#likeBtn").empty()
  movieId = movieid
  $.get('search-detail', { 'movieid': movieid })
    .done(function(res) {
      console.log(res)
      let resJSON = JSON.parse(res)
      let resStaffJSON = JSON.parse(resJSON[1].staffList)
      let resGenreJSON = JSON.parse(resJSON[2].genreList)
      let youtubeKey = resJSON[3];
      let dip = resJSON[4];
      let like = resJSON[5];
      if (dip == 'dip') {
        $("#dipBtn").empty()

        $("#dipBtn")
          .append($('<i>')
            .attr('class', 'fas fa-check text-light'))
          .attr('value', 'dip')
      } else if (dip == 'none') {
        $("#dipBtn").empty()

        $("#dipBtn")
          .append($('<i>')
            .attr('class', 'fas fa-plus text-dark'))
          .attr('value', 'none')
      }

      if (like == 'like') {
        $("#likeBtn").empty()

        $("#likeBtn")
          .append($('<i>')
            .attr('class', 'fas fa-thumbs-up text-light'))
          .attr('value', 'like')
      } else if (like == 'none') {
        $("#likeBtn").empty()

        $("#likeBtn")
          .append($('<i>')
            .attr('class', 'fas fa-thumbs-up text-dark'))
          .attr('value', 'none')
      }


      let staffStr = "출연진 : "
      let genreStr = "장르 : "
      $('#staffList').empty()

      let req = {
        'key': youtubeKey.substring(1, youtubeKey.length-1),
        'part': 'snippet',
        'type': 'video',
        'q': resJSON[0].title + ' ' + resJSON[0].subtitle + ' ' + 'trailer'
      }

      let rate = Math.ceil(resJSON[0].rate)
      let star = '<span><i class="fas fa-star"></i></span>'.repeat(parseInt(rate / 2)) + '<span><i class="fas fa-star-half-alt"></i></span>'.repeat(parseInt(rate % 2)) + '<span><i class="far fa-star"></i></span>'.repeat(parseInt((10 - rate) / 2))
      let title = resJSON[0].title + ' (' + resJSON[0].subtitle + ') ' + star

      if (resJSON[0].hasOwnProperty("country")) {
        $('#movieCountry').text('나라: ' + resJSON[0].country)
      }
      if (resJSON[0].runningtime > 0) {
        $('#movieRunningTime').text('길이: ' + resJSON[0].runningtime + '분')
      }
      if (resJSON[0].hasOwnProperty("opendate")) {
        $('#movieOpenDate').text('개봉일: ' + resJSON[0].opendate.substr(0, 10))
      }
      if (resJSON[0].hasOwnProperty("grade")) {
        $('#movieGrade').text('등급: ' + resJSON[0].grade)
      }
      if (resJSON[0].rate > 0) {
        $('#movieRate').text('평점: ' + resJSON[0].rate)
      }
      $('#moviePoster').attr('src', resJSON[0].poster)
      $('#movieStory').text(resJSON[0].story)
      //      $('#movieTitle').text(title)
      $('#movieTitle').html(title)


      // 출연진 추가
      for (let i = 0; i < resStaffJSON.length; i++) {
        const element = resStaffJSON[i];
        let staffname = ''
        let role = ''
        let rolename = ''
        let roleStr = ''

        if (element.hasOwnProperty('staffname')) {
          staffname = element.staffname;
        }
        if (element.hasOwnProperty('role')) {
          role = element.role;
        }
        if (element.hasOwnProperty('rolename')) {
          rolename = element.rolename;
        }

        if (role === '감독' || rolename === '') {
          roleStr = role
        } else if (role !== '' && rolename !== '') {
          roleStr = role + ' | ' + rolename
        }

        staffStr += element.staffname + ', '


        $('#staffList')
          .append($('<div>')
            .attr('class', 'col-xs-6 col-md-3 text-center')
            .append($('<img>')
              .attr('src', element.staffimg))
            .append($('<p>')
              .attr('class', 'text-light m-3')
              .text(element.staffname))
            .append($('<p>')
              .attr('class', 'text-light m-3')
              .text(roleStr)))
      }
      $("#movieStaff")
        .text(staffStr.substr(0, staffStr.length - 2))

      // 장르 추가
      for (let i = 0; i < resGenreJSON.length; i++) {
        const element = resGenreJSON[i];
        genreStr += element.genrename + ', '
      }
      $("#movieGenre")
        .text(genreStr.substr(0, genreStr.length - 2))

      $.get('https://www.googleapis.com/youtube/v3/search', req)
        .done(function(res) {
          let videoId = res.items[0].id.videoId
          $('#movieTrailer').attr('src', 'https://www.youtube.com/embed/' + videoId)
        })
        .fail(function(res, status, err) {
          console.log(err)
        })
    })
    .fail(function(res, status, err) {
      console.log(err)
    })
}


window.onload = function() {

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

  var getConnect = function(url, block, style) {
    var xhrget = new XMLHttpRequest();

    // 통신할 방식, url, 동기 여부 설정
    xhrget.open("GET", url, true);
    // 요청
    xhrget.send();
    // 응답
    xhrget.onreadystatechange = function() {
      if (xhrget.readyState == XMLHttpRequest.DONE && xhrget.status == 200) {

        // 응답 값 (서버로부터 받아온 데이터)
        var jsonStr = xhrget.responseText; // json 형태의 문자열
        var json = JSON.parse(jsonStr); // 문자열을 json으로 변환

        console.log(url);
        console.log(jsonStr);

        // html에 들어갈 요소 및 블록
        var barBlock = document.querySelector("#" + block + "-bar");
        var contentsBlock = document.querySelector("#" + block + "-contents");
        var barHtml = "";
        var contentsHtml = "";

        for (var i = 0; i < json.length; i++) {
          if (i % 12 === 0 && i === 0) {
            barHtml += '<button type="button" data-bs-target="#' + block + 'Indicators" data-bs-slide-to="' + (parseInt((i + 1) / 12)) + '" class="active" aria-current="true" aria-label="Slide ' + (parseInt(i / 12) + 1) + '"></button>';
            contentsHtml += '<div class="carousel-item active" > ' +
              '<div class="row"> ';
          }

          contentsHtml += 
//          '<div class="col-xs-4 col-md-2 text-center"> ' +
            '<img class="col-xxl-1 col-lg-2 col-sm-3 col-6 p-1 poster" src="' + json[i].poster + '" class="rounded ' + style + '" alt="..." data-bs-toggle="modal" data-bs-target="#movieModal" onclick="openModal('+ json[i].movieid +');"> '
//            '</div> ';
          if (i === (json.length - 1)) {
            contentsHtml += '</div> ' +
              '</div> ';
          } else if (i % 12 === 11) {
            barHtml += '<button type="button" data-bs-target="#' + block + 'Indicators" data-bs-slide-to="' + (parseInt((i + 1) / 12)) + '" aria-label="Slide ' + (parseInt((i + 1) / 12) + 1) + '"></button>';
            contentsHtml += '</div> ' +
              '</div> ' +
              '<div class="carousel-item" > ' +
              '<div class="row"> ';
          }

          barBlock.innerHTML = barHtml;
          contentsBlock.innerHTML = contentsHtml;
        }
      }
    }
  }

  //  onclick="function() {openModal(' + json[i].movieid + ')};"

  getConnect("popular-contents", "popular", "poster")
  getConnect("recent-contents", "recent", "poster");
  getConnect("recommend-contents", "recommend", "poster");
  getConnect("gender-contents", "gender", "poster");
  getConnect("age-contents", "age", "poster");

  
  // 찜하기, 좋아요 버튼 설정
  $(document).ready(function() {
    $("#dipBtn").click(function() {
      var xhrpost = new XMLHttpRequest();
      // 통신할 방식, url, 동기 여부 설정
      xhrpost.open("POST", "dips", true);
      // post 통신을 위한 헤더 설정
      xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      // 요청
      xhrpost.send("dip=" + $(this).attr('value') + "&movieid=" + movieId);
      // 응답
      xhrpost.onreadystatechange = function() {
        if (xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status == 200) {
          console.log(xhrpost.responseText);
  
          dip = xhrpost.responseText
          if (dip == 'dip') {
            $("#dipBtn").empty()
  
            $("#dipBtn")
              .append($('<i>')
                .attr('class', 'fas fa-check text-light'))
              .attr('value', 'dip')
          } else if (dip == 'none') {
            $("#dipBtn").empty()
  
            $("#dipBtn")
              .append($('<i>')
                .attr('class', 'fas fa-plus text-dark'))
              .attr('value', 'none')
          }
  
        }
      }
    });
  
    $("#likeBtn").click(function() {
      var xhrpost = new XMLHttpRequest();
      // 통신할 방식, url, 동기 여부 설정
      xhrpost.open("POST", "likes", true);
      // post 통신을 위한 헤더 설정
      xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      // 요청
      xhrpost.send("like=" + $(this).attr('value') + "&movieid=" + movieId);
      // 응답
      xhrpost.onreadystatechange = function() {
        if (xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status == 200) {
          console.log(xhrpost.responseText);
  
          like = xhrpost.responseText
          if (like == 'like') {
            $("#likeBtn").empty()
  
            $("#likeBtn")
              .append($('<i>')
                .attr('class', 'fas fa-thumbs-up text-light'))
              .attr('value', 'like')
          } else if (like == 'none') {
            $("#likeBtn").empty()
  
            $("#likeBtn")
              .append($('<i>')
                .attr('class', 'fas fa-thumbs-up text-dark'))
              .attr('value', 'none')
          }
  
        }
      }
    });
  });
  
  // 달력 기본값 설정
  today = new Date();
  today = today.toISOString().slice(0, 10);
  var reviewDate = document.getElementById("reviewDate");
  reviewDate.value = today;

  // 리뷰 내용 가져오기
  var reviewTitle = document.getElementById("reviewTitle");
  var reviewContents = document.getElementById("reviewContents");
  var reviewBtn = document.getElementById("reviewBtn");
  reviewBtn.onclick = function() {
    var reviewAccess = $("input:radio[name='reviewAccess']:checked").val();
    var xhrpost = new XMLHttpRequest();
    // 통신할 방식, url, 동기 여부 설정
    xhrpost.open("POST", "review/2", true);
    // post 통신을 위한 헤더 설정
    xhrpost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    // 요청
    xhrpost.send("movieid=" + movieId + "&reviewtitle=" + reviewTitle.value + "&reviewdate=" + reviewDate.value + "&reviewaccess=" + reviewAccess + "&reviewcontents=" + reviewContents.value);
    // 응답
    xhrpost.onreadystatechange = function() {
      if (xhrpost.readyState == XMLHttpRequest.DONE && xhrpost.status == 200) {
        alert(xhrpost.responseText);
        $('#reviewModal').attr('style', 'none');
        reviewTitle.value = "";
        reviewContents.value = "";
      }
    }
  }
}

