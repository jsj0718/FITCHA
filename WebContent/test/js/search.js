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

// 모달창 띄우기
let openModal = function(movieid) {
  $.get('search-detail', { 'movieid': movieid })
    .done(function(res) {
      console.log(res)
      let resJSON = JSON.parse(res)
      let resStaffJSON = JSON.parse(resJSON[1].staffList)
      let resGenreJSON = JSON.parse(resJSON[2].genreList)
      let staffStr = "출연진 : "
      let genreStr = "장르 : "
      $('#staffList').empty()
      
      let req = {
        //        'key': 'AIzaSyAI-1OpsUWzAfl_e-eSZqtj9l5MCXNG4CY',
        'part': 'snippet',
        'type': 'video',
        'q': resJSON[0].title + ' ' + resJSON[0].subtitle + ' ' + 'trailer'
      }
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
      $('#movieTitle').text(resJSON[0].title + ' (' + resJSON[0].subtitle + ') ')
  
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
  console.log(cnt)
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
      console.log(1)
      $("#spinner")
        .remove()
      cnt += 1
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