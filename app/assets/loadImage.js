javascript:(
function () {
    var images = document.getElementsByTagName("img");
    for (var i = 0; i < images.length; i++) {
        var image=images[i];

        image.style.maxWidth = "100%";

        if(image.src.indexOf("http")==0){
            window.imageloader.loadImage(image.src);
        }
    }
})()