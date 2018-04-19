$(function () {
    //   回到顶部的bar的控制
    var $go_top = $("#go-top");
    window.onscroll = function () {
        var client_height = document.documentElement.clientHeight;
        var scroll_top = document.documentElement.scrollTop || document.body.scrollTop;
        if (scroll_top > client_height + 100) {
            $go_top.show();
        }else {
            $go_top.hide();
        }
    };
    $go_top.click(function(){
        $("html,body").animate({scrollTop:0},500);
    });
});