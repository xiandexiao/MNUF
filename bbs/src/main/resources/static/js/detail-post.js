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

    $("#comment").click(function () {
        var body_height = $("body").height();
        $("html,body").animate({scrollTop:body_height},800);
    });
    var $storey = $(".storey");
    /**
     * 获取楼层数
     * */
    function mark() {
        var storeyLength = $storey.length;
        $("#commentCount").html(storeyLength);
        for (var i = 0; i < storeyLength; i++) {
            $($storey[i]).html((i+1)+"楼");
        }
    }
    mark();

    //reply
    $("#reply").click(function () {
        var comment_val = $("textarea").val();
        var postId = $("input[name='postId']").val();
        var userId = $("input[name='userId']").val();

        if (comment_val.length === 0) {
            alert("内容不能为空！");
            return;
        }
        var param = JSON.stringify({"postId":postId, "userId":userId, "comment":comment_val});
        $.ajax("/addComment",{
            type: "POST",
            dataType:"json", //响应的数据类型
            contentType:"application/json;charset=utf-8",
            data: param,
            success: function () {
                location.reload();
            }
        });
    });
});