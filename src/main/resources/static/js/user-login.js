$(function () {



    
    $("#login-button").click(function () {
        var id = $("#selectUserRole").val();
        if(id=='3'){
            // 学生
            $.ajax({
                url:"student/checkStudentLogin",
                type:"post",
                data:{"name":$("input[name='name']").val(),
                    "password":$("input[name='password']").val()},
                dataType:"json",
                success:function(ret){
                    if(ret.result==true){
                        location.href="index.html"
                    }else{
                        layer.msg('登录失败，请重新输入用户名密码');
                        $("input[name='password']").val("");
                        $("input[name='name']").val("");
                    }
                }
            })
        }else{
            // 教师
            $.ajax({
                url:"teacher/checkTeacherLogin",
                type:"post",
                data:{"name":$("input[name='name']").val(),
                    "password":$("input[name='password']").val()},
                dataType:"json",
                success:function(ret){
                    if(ret.result==true){
                        location.href="teacherinfo.html"
                    }else{
                        layer.msg("登录失败,请输入正确的用户名密码",{time:2000});
                        $("input[name='name']").val("");
                        $("input[name='password']").val("");
                    }
                }
            })
        }
    })

});