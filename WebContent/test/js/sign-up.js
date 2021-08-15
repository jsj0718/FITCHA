/**
 * 
 */

window.onload = function() {
  const moveToSignin = document.getElementById("move-to-signin");
  moveToSignin.onclick = function() {
    location.href = "/sign-in";
    console.log(1);
  }
}

