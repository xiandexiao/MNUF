$(function () {
    var URL = window.URL || window.webkitURL;
    var $image = $('#image');
    var $rotate = $('#userImg_rotate');
    var $reUpload = $('#userImg_reUpload');
    var $zoomOut = $('#userImg_zoomOut');
    var $zoomIn = $('#userImg_zoomIn');
    var $save = $('#save');
    var croppable = false;
    var $previews = $('.userImg_preview');
    var options = {
        aspectRatio: 1,
        viewMode: 1,
        built: function () {
            croppable = true;
        },
        build: function (e) {
            var $clone = $(this).clone();

            $clone.css({
                display: 'block',
                width: '100%',
                minWidth: 0,
                minHeight: 0,
                maxWidth: 'none',
                maxHeight: 'none'
            });

            $previews.css({
                width: '100%',
                overflow: 'hidden'
            }).html($clone);
        },
        crop: function (e) {
            var imageData = $(this).cropper('getImageData');
            var previewAspectRatio = e.width / e.height;

            $previews.each(function () {
                var $preview = $(this);
                var previewWidth = $preview.width();
                var previewHeight = previewWidth / previewAspectRatio;
                var imageScaledRatio = e.width / previewWidth;

                $preview.height(previewHeight).find('img').css({
                    width: imageData.naturalWidth / imageScaledRatio,
                    height: imageData.naturalHeight / imageScaledRatio,
                    marginLeft: -e.x / imageScaledRatio,
                    marginTop: -e.y / imageScaledRatio
                });
            });
        }
    };
    var originalImageURL = $image.attr("src");
    var uploadedImageURL;


    // init
    $image.attr('src',originalImageURL).cropper(options);


    // rotate
    $rotate.on('click', function(){
        $image.cropper('rotate', 90);
    });

    // zoomOut
    $zoomOut.on('click',function(){
        $image.cropper('zoom', -0.1);
    });

    // zoomIn
    $zoomIn.on('click',function(){
        $image.cropper('zoom', 0.1);
    });

    // Move
    /*$move.on('click',function(){
     $image.cropper('setDragMode');
     });*/

    // reUpload
    $reUpload.on('click',function(){
        $image.cropper('destroy').attr('src', $scope.userInfo_imgUrl).cropper(options);
        if (uploadedImageURL) {
            URL.revokeObjectURL(uploadedImageURL);
            uploadedImageURL = '';
        }
    });

    // Keyboard
    $(document.body).on('keydown', function (e) {

        if (!$image.data('cropper') || this.scrollTop > 300) {
            return;
        }

        switch (e.which) {
            case 37:
                e.preventDefault();
                $image.cropper('move', -1, 0);
                break;

            case 38:
                e.preventDefault();
                $image.cropper('move', 0, -1);
                break;

            case 39:
                e.preventDefault();
                $image.cropper('move', 1, 0);
                break;

            case 40:
                e.preventDefault();
                $image.cropper('move', 0, 1);
                break;
        }

    });

    // save and upload cropped Img
    $save.on('click',function(){
        //common.Loading.show();

        // Use `jQuery.ajax` method
        $('#image').cropper('getCroppedCanvas').toBlob(function (blob) {
            var $modal_body =  $(".modal-body");
            var formData = new FormData();
            formData.append('avatar', blob);
            $.ajax('/uploadAvatar', {
                method: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    //head 信息
                    $(".header-msg").text("上传状态");
                    $modal_body.css({"background":"","color":"green"});
                    $modal_body.html(data.msg);
                },
                error: function (data) {
                    $modal_body.css({"background":"","color":"red"});
                    $modal_body.html(data.msg);
                }
            });
        });
    });

    // Inport Image
    var $inputImage = $('#file');
    if (URL) {
        $inputImage.change(function () {
            var files = this.files;
            var file;

            if (!$image.data('cropper')) {
                return;
            }

            if (files && files.length) {
                file = files[0];

                if (/^image\/\w+$/.test(file.type)) {
                    if (uploadedImageURL) {
                        URL.revokeObjectURL(uploadedImageURL);
                    }

                    uploadedImageURL = URL.createObjectURL(file);
                    $image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
                    $inputImage.val('');
                } else {
                    alert('请选择图片再上传！')
                }
            }
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }
    var $modifyIconTip = $("#modifyIconTip");
    $modifyIconTip.click(function () {
        $(this).hide();
        $(".bodyWrap").hide();
        $("#upload").css("display","block");
        $(".right").html("支持jpg、jpeg、png、bmp格式，文件小于5M");
        $(".left").html("设置新头像")
    });

    $("button[type='button']").click(function () {
        location.reload();
    });
    /*$("#logout").click(function () {
        window.location.href="/logout"
    });*/
    var $newnickName = $("#newnickName");
    $newnickName.focusin(function () {
       $(this).next().show();
    });
    var $modal_body =  $(".modal-body");
    $("#editSave").click(function () {
        var nickName =  $newnickName.val();
        $.ajax("/updateUser",{
            method: "POST",
            //contentType:"application/json;charset=utf-8",
            dataType:"json", //响应的数据类型
            data: {"name":nickName},
            success: function (data) {
                $modal_body.css({"background":"","color":"green"});
                $modal_body.html(data.msg);
            },
            error: function (data) {
                $modal_body.css({"background":"","color":"red"});
                $modal_body.html(data.msg);
            }
        });
        $("#nickNameCon").hide();
        $("#nickNameTitle").show();
    });
    $("#editCancel").click(function () {
        $("#nickNameCon").hide();
        $("#nickNameTitle").show();
    });
    $("#nickNameEdite").click(function () {
        $("#nickNameCon").show();
        $("#nickNameTitle").hide();
    });
    //验证码定时
    var timer = null;
    var $getKey_checkPhoneOld = $("#getKey-checkPhoneOld");
    var $email_span = $("#email-span");
    /**
     * 验证邮箱及发送验证码
     * */
    $getKey_checkPhoneOld.click(function () {


        //获取邮箱地址
        var email = $("#ce-u-check-phone").val();
        //验证邮箱

        $.ajax("/updateUser",{
            method: "post",
            data: {"email":email},
            success: function (data) {
                $email_span.html("");
                $email_span.html(data.msg);
                if(data.status === "500") {
                    return;
                }
                //定时器
                var time = 60;
                timer = setInterval(function () {
                    $getKey_checkPhoneOld.html("等待: " + time-- + " s");
                    if (time < 0 ) {
                        clearInterval(timer);
                        $getKey_checkPhoneOld.html("重新获取");
                    }
                },1000);
            },
            error: function (data) {
                $email_span.html(data.msg);
            }
        });

    });

    /**
     * 验证用户验证码
     *
     * */
    var changePasswordWrapFirst = $("#changePasswordWrapFirst");
    var $code_check = $("#ce-u-pwd-phone-check");
    $code_check.click(function () {
        var code_span = $("#code-span");
        var code = $("input[name='kapkeyPhone']").val();
        code_span.html("");
        $.ajax("/verifyCode",{
            method: "POST",
            dataType:"json", //响应的数据类型
            data: {"code":code},
            success: function (data) {
                if (data.status === "200") {
                    code_span.html(data.msg);
                    setTimeout(function () {
                        changePasswordWrapFirst.hide();
                        $("#changePasswordWrapSecond").show();
                    },1500)
                }
                code_span.html(data.msg);
            },
            error: function () {

            }
        });
    });

    /**
     * 验证密码
     * */
    var $verifyPassword = $("#verifyPassword");
    var new_password = $("input[name='new-password']");
    var re_password = $("input[name='repassword']");
    $verifyPassword.click(function () {
        var newPassword = new_password.val();
        $.ajax("/updateUser",{
            method: "POST",
            data: {"password":newPassword},
            dataType:"json", //响应的数据类型
            success: function (data) {
                $modal_body.css({"color":"green"});
                $modal_body.html(data.msg);
            }
        })
    });

    $("#modify-pwd").click(function () {
        $(this).parent().hide();
        changePasswordWrapFirst.show();
    });

});