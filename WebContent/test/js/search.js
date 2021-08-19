let option = {
  attributes: true,
}

// DOM 변화 감지 (MovieTrailer의 속성을 삭제)
let modalObserver = new MutationObserver((mutations) => {
  $('#movieTrailer').attr('src', '')
})
// 삭제 후 modal의 속성을 true로 변환
modalObserver.observe(document.querySelector('#movieModal'), option)

// 페이지 로드 시 카운트 수
let cnt = 1
// 모달창 띄울 때 movieId 가져오기
let movieId = 0;

// 모달창 띄우기
let openModal = function(movieid) {
  movieId = movieid
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

// 영화 검색 함수
let loadMovies = function() {
  $("#searchContent")
    .append($('<div>')
      .attr('class', 'top-50 start-50 text-center')
      .attr('id', 'spinner')
      .attr('style', 'position: fixed;')
      .append($('<div>')
        .attr('class', 'spinner-border')
        .attr('role', 'status')
        .attr('style', "color : white;")
        .append($('span')
          .attr('class', 'visually-hidden'))))
  let req = {
    "genre": $("#genreSelect").val(),
    "country": $("#countrySelect").val(),
    "order": $("#orderSelect").val(),
    "index": cnt,
    "title": $('#searchText').val()
  }
  $.get('search-contents', req)
    .done(function(res) {
      let resJSON = JSON.parse(res)


      for (let i = 0; i < resJSON.length; i++) {
        const element = resJSON[i];
        $("#searchContent")
          .append($('<img>')
            .attr('src', element.poster)
            .attr('class', 'col-xxl-1 col-lg-2 col-sm-3 col-6 p-1 poster')
            .attr('data-bs-toggle', 'modal')
            .attr('data-bs-target', '#movieModal')
            .on('click', function() {
              openModal(element.movieid)
            })
          )
      }
      // 로딩 완료 시 스피너 제거
      $("#spinner")
        .remove()
      cnt += 1
      // 데이터가 없는 경우 예외처리
      if (resJSON.length === 0) {
        $("#searchContent")
          .append($('<div>')
            .attr('class', 'container-fluid position-absolute top-50 start-50 translate-middle')
            .append($('<div>')
              .attr('class', 'row justify-content-center text-center')
              .append($('<p>')
                .attr('class', 'text-dark')
                .attr('style', 'font-size : 1.4vw; font-weight : 700;')
                .text('설정하신 장르/국가/특징에 맞는 작품이 없습니다. 다시 검색해주세요.')
              )
            )
          )
      }
    })
    .fail(function(res, status, err) {
      console.log(err)
    })
}

// 페이지 로드 시 영화 검색
$(document).ready(function() {
  loadMovies()
})


$(function() {
  // 스크롤 이벤트
  $(document).scroll(function() {
    let scrollT = $(this).scrollTop();
    let scrollH = $(window).height();
    let contentH = $(this).height();
    if (scrollT + scrollH >= contentH) {
      loadMovies()
    }
  })

  // 검색 이벤트
  $('.form-select').change(function() {
    $('#searchContent').empty()
    cnt = 1
    loadMovies()
  })
  $('#searchText').on('change', function() {
    $('#searchContent').empty()
    cnt = 1
    loadMovies()
  })
})

window.onload = function() {
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
  
  // 장르, 나라 넣기
  var getConnect = function(url, block) {
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

        for (var i = 0; i < json.length; i++) {
          $('#'+ block +'Select')
            .append($('<option>')
              .attr('value', json[i].name)
              .text(json[i].name))
              
        }
      }
    }
  }
  
  getConnect("genre", "genre");
  getConnect("country", "country");
}

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