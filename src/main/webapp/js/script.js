$(".btn1").click(function() {
    fadeOut();
    setInterval(() => redirect('/generate'), 3000);
});

$(".btn2").click(function() {
    fadeOut();
    setInterval(() => redirect('/results'), 3000);
});

function fadeOut(){
    $("button").fadeOut(3000);
}

function redirect(path) {
    $(location).attr('href', path);
}
