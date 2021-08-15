let cnt = 1

let openModal = function(movieCd){
  $.get('search-', movieCd)
  .fail(function(){
    console.log('성공')
    console.log('성공 끝')
  })
  .done(function(){
    console.log('실패')
  })
}

let loadMovies = function(){
  let req = { // 보낼 데이터 (Object , String, Array)
    "genre" : $("#genreSelect").val(),
    "country": $("#countrySelect").val(),
    "order": $("#orderSelect").val(),
    "index": cnt
  }
  $.get('search-contents', req, function(res, status){
    if (status=='success') {
      let resJSON = JSON.parse(res)
      for (let i = 0; i < resJSON.length; i++) {
        const element = resJSON[i];
        $("#searchContent")
          .append($('<img>')
            .attr('src', element.poster)
            .attr('class', 'col-xxl-1 col-lg-2 col-sm-3 col-6 p-1 poster')
            .attr('data-bs-toggle', 'modal')
            .attr('data-bs-target', '#movieModal')
            .on('click', function(){
              let modal = $('#movieModal')

          })
        )
      }
      cnt += 1
    } else {
      console.log(status)
    }
  })
}


$(document).ready(function(){
  loadMovies()
})

$(function(){
  $(document).scroll(function(){
    let scrollT = $(this).scrollTop();
    let scrollH = $(window).height();
    let contentH = $(this).height();
    if(scrollT + scrollH >= contentH) {
      loadMovies()
    }
  })
  // document.addEventListener('scroll', function(){
  //   console.log('scroll')
  // })
})