$(function () {
    //上传帖子
    $("#submit").click(function () {
        var fd = new FormData();
        var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
        var title = $("input[name='title']").val();
        var content = $("textarea").val();
        fd.append("file",fileObj);
        fd.append("title",title);
        fd.append("content",content);
        $.ajax({
            url: "/pushPost",
            type: "POST",
            data: fd,
            processData: false,  // 不处理数据
            contentType: false,  // 不设置内容类型
            success: function (data) {
                //head 信息
                var $modal_body = $(".modal-body");
                $(".header-msg").text("上传状态");
                $modal_body.css({"background":"","color":"green"});
                $modal_body.html(data);
            }
        });
    });

    //判断字符
    $("#title").keyup(function () {
        var char_num = 25;
        var title_len = $(this).val().length;
        var limit = 25 - title_len;
        if (limit > 0) $(this).next().html("还可以输入" + limit + "个字符");
        else $(this).next().html("超过字数限制");
    });
});