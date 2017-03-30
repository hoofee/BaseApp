var setImageSrc=function(src,base64){
        var images = document.getElementsByTagName("img");
        for (i = 0; i < images.length; i++) {
            var img = images[i];
            if (src == img.src) {
                img.src = 'data:image/png;base64,'+base64;
                break;
            }
        }
    }
