//When I accept is clicked in the cookies bar call removeCookies function
function removeCookies(){
    var cookieGone = document.querySelector("#cookieBar"); //set the variable to the div that contains the entire cookie bar
    cookieGone.remove(); //remove the entire div
}

//When the cart in the top right navigation bar is clicked, send alert message to page "cart is empty throught cartAlert functin
function cartAlert(){
    alert("The cart is Empty!");
}

//OnmouseOver, change the source of the image 
function changeImage(){
    var newImage = document.querySelector("#mainImage"); //set the query selector to the big middle picture of page
    newImage.src = "assets/succulents-2.jpg"; //change its source
}
//OnmouseOut, change the source image back to the original
function revertImage(){
    var newImage = document.querySelector("#mainImage"); //set the query selector to the big middle picture of page
    newImage.src = "assets/succulents-1.jpg"; //change its source 
}