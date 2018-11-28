
$(function () {
    var tid ;
    $.ajax({
        url:'teacher/getTeacherSession',
        dataype:'json',
        data:{"teacherBean":"teachersession"},
        type:'post',
        success: function (ret) {
            tid = ret.result.teacherId;
        }
    })

    $("button[name='submit']").click(function () {
            var question = $("#blanks textarea").val();
            var answer = $("input[name='answer']").val();
            $.ajax({
                url:'blank/insertBlank',
                dataType:'json',
                type:'get',
                data:{ "blankQuestion":question,
                    "blankAnswer":answer,
                    "teacherId":tid},
                success:function (ret) {
                    if(ret.result==true){
                        layer.msg("添加成功",{time:2000})
                        layer.close(layer.index);
                    }else{
                        layer.msg("添加失败",{time:2000})
                    }
                }
            })
    })

    $("button[name='back']").click(function () {
        history.back();
    })
})