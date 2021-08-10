/**
 * 
 */

var movieDB = [
	 {title: "Spider-Man: Homecoming",
            rating: 3.5,
            hasWatched: true,
            runtime: 130,
            synopsis: "Peter Parker balances his life as an ordinary high school student in Queens with his superhero alter-ego Spider-Man, and finds himself on the trail of a new menace prowling the skies of New York City.",
            background: "url('https://images8.alphacoders.com/815/815755.jpg')"
           }
	];
	
	function printMovieInfo(element) {
  if (element.hasWatched)
    console.log("You have watched " + element.title + " - " + element.rating + " stars");
  else
    console.log("You have not seen " + element.title + " - " + element.rating + " stars");
}

// console stuff
movieDB.forEach(printMovieInfo);

movieDB.forEach(insertCard);

function insertCard(element, i){
  // card
  var card = document.getElementByClass("movie-card");
  card.style.backgroundImage = element.background;
  
  // title
  var title = document.getElementById("semi-title");
  title.innerText = element.title;
  card.appendChild(title);
  
  // runtime
  var runtime = document.getElementById("runtime");
  runtime.innerText = element.runtime + " min";
  card.appendChild(runtime);
  
  // rating
  var star = document.getElementByClass("fas fa-star");
  var rating = document.getElementById("rating");
  rating.innerText = element.rating + " ";
  rating.appendChild(star);
  card.appendChild(rating);
  
  // synopsis
  var synopsis = document.getElementById("synopsis");
  synopsis.innerText = element.synopsis;
  card.appendChild(synopsis);
  
  // watch/watch again
  var watch = document.getElementById("watch");
  if (element.hasWatched == false)
    watch.innerText = "WATCH MOVIE";
  else
    watch.innerText = "WATCH AGAIN";
  card.appendChild(watch);
  
  document.body.appendChild(card);
}