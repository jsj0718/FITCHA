let option = {
  attributes: true,
}
let modalObserver = new MutationObserver((mutations) => {
	$('#movieTrailer').attr('src', '')
})
modalObserver.observe(document.querySelector('#movieModal'), option)

let cnt = 1

let openModal = function(movieid) {
	$.get('search-detail', { 'movieid': movieid })
		.done(function(res) {
			let resJSON = JSON.parse(res)
			let req = {
				'key': 'AIzaSyAI-1OpsUWzAfl_e-eSZqtj9l5MCXNG4CY',
				'part': 'snippet',
				'type': 'video',
				'q': resJSON[0].title + ' ' + resJSON[0].subtitle + ' ' + 'trailer'
			}
			$('#movieCountry').text(resJSON[0].country)
			$('#movieGrade').text(resJSON[0].grade)
			$('#movieopendate').text(resJSON[0].opendate)
			$('#moviePoster').attr('src', resJSON[0].poster)
			$('#movieRate').text(resJSON[0].rate)
			$('#movieRunningtime').text(resJSON[0].runningtime)
			$('#movieStory').text(resJSON[0].story)
			$('#movieSubtitle').text(resJSON[0].subtitle)
			$('#movieTitle').text(resJSON[0].title)
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

let loadMovies = function() {
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
			cnt += 1
		})
		.fail(function(res, status, err) {
			console.log(err)
		})
}


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
  $('.form-select').change(function(){
    $('#searchContent').empty()
    cnt = 1
    loadMovies()
  })
  $('#searchText').on('change', function(){
    $('#searchContent').empty()
    cnt = 1
    loadMovies()
  })
})