
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
            var question = $("#selects textarea").val();
            var a = $("input[name='a']").val();
            var b = $("input[name='b']").val();
            var c = $("input[name='c']").val();
            var d = $("input[name='d']").val();
            var y = $("input[name='y']").val();


            console.log(a+" "+" "+b+" "+c+" "+d+" "+y)
            console.log(tid)
            $.ajax({
                url:'select/insertSelect',
                dataType:'json',
                type:'get',
                data:{
                    "selectQuestion":question,
                    "selectA":a,
                    "selectB":b,
                    "selectC":c,
                    "selectD":d,
                    "Answer":y,
                    "teacherId":tid
                },
                success:function (ret) {
                    if(ret.result==true){
                        layer.msg("选择题添加成功",{time:2000})
                        layer.close(layer.index);
                    }else{
                        layer.ms("选择题添加失败",{time:2000})
                    }
                }
            })
    })

    $("button[name='back']").click(function () {
        layer.closeAll();
    })
})