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

    //loding 页面加载进度控制(以img为准)
    var $img = $("img");
    var img_num = 0;
    $img.each(function (i) {
        var oImg = new Image();

        oImg.onload = function () {
            oImg.onload = null;
            img_num++;

            $(".loding b").html(parseInt(img_num / $img.size() * 100) + "%");
            if(img_num >= i) {
                $(".loding").hide();
            }
        };

        oImg.src = $img[i].src;
    });

    //控制 全部板块 的显示与隐藏
    var $bbs_blocks = $(".bbs-blocks");
    var screen_width;

    $("#all-block").hover(function () {
        screen_width = document.documentElement.clientWidth || document.body.clientWidth;
        if (screen_width < 768){
            $bbs_blocks.hide();
        }else {
            $bbs_blocks.show();
        }
    },function () {
        $bbs_blocks.css("display","none");
    });
    $bbs_blocks.hover(function () {
        $(this).css("display","block");
    },function () {
        $(this).css("display","none");
    });
});
