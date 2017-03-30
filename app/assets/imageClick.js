javascript:(
function () {
    var objs = document.getElementsByTagName("img");
    var fiterUrl = ["146528280313428779.png", "96PKGghCric2ZKgLicrt1BlHWEgl1STcEsia560dUXITgOteKIxVoraX9m7ENkUeI5n6ib1Q2KUSXiafL5GrBoq7CRA"];
    var imgUrls = "";
    var hasFilter = false;
    for (var i = 0; i < objs.length; i++) {
        var imageUrl=objs[i].src;
        var isContains = false;
        for (var j = 0; j < fiterUrl.length; j++) {
            if (imageUrl.indexOf(fiterUrl[j]) >= 0) {
                isContains = true;
                hasFilter = true;
                break;
            }
        }
        if (isContains) continue;
        else {
            imgUrls += imageUrl + ',';
            objs[i].onclick = (function (i) {
                return function () {
                    window.imageClickListener.imageClick(imgUrls, i);
                }
            }(hasFilter ? i - 1 : i));
        }
    }
})()