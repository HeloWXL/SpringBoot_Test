var sid;
$(function () {
    var b = 0;
    var classArr = new Array("", "success", "error", "warning", "info");
    $.ajax({
        url: "student/getStudentSession",
        data: {"studentBean": "studentsession"},
        type: "post",
        dataType: "json",
        async:false,
        success: function (ret) {
            sid = ret.result.studentId;
            if ($.isEmptyObject(ret.result)) {
                $('#user1').hide();
                $('#select').hide();
            } else {
                // 判断学生的信息
                var sex;
                if (typeof (ret.result.studentSex) == null) {
                    sex = null;
                } else {
                    sex = ret.result.studentSex;
                }
                var age;
                if (typeof (ret.result.studentAge) == null) {
                    age = null;
                } else {
                    age = ret.result.studentAge;
                }
                var school;
                if (typeof (ret.result.studentSchool) == null) {
                    school = null;
                } else {
                    school = ret.result.studentSchool;
                }
                var pic;
                pic = ret.result.studentPicture;

                var name;
                if (typeof (ret.result.studentName) == null) {
                    name = null;
                } else {
                    name = ret.result.studentName;
                }

                var profession;
                if (typeof (ret.result.studentProfession) == null) {
                    profession = null;
                } else {
                    profession = ret.result.studentProfession;
                }
                // 设置导航栏中的内容
                $('#user1').show();
                $('#select').show();
                $('.login').hide();

                $("#user1 img").attr("src", pic);
                $("#user1 span").html(name)
                // 设置个人详情页中内容
                $("input[name='name']").val(name)
                $("input[name='age']").val(age)
                $("input[name='school']").val(school)
                $("input[name='profession']").val(profession)
                $("#right img").attr("src",pic)
            }
        }
    })

    // $("#courses").empty();
    // $.ajax({
    //     url:'history/recommendList',
    //     data:{"studentId":sid},
    //     dataType:'json',
    //     type:'post',
    //     success:function (ret) {
    //         for(var i = 0 ;i<ret.result.length;i++){
    //             $node=$('<div class="img">\n' +
    //                 '                    <img src="'+ret.result[i].coursePicture+'" width="185px">\n' +
    //                 '                    <p>课程名：<span>'+ret.result[i].courseName+'</span></p>\n' +
    //                 '                    <p>教师名：<span>'+ret.result[i].teacherName+'</span></p>\n' +
    //                 '                </div>')
    //             $("#courses").append($node);
    //         }
    //     }
    // })

    // 我的信息
    $("#left-1 li:nth-child(2) a").click(function () {
        $("#right").show();
        $("#right-1").hide();
        $("#right-2").hide();
        $("#center").hide();
    })
    $("#right-1").on("click", "button[name='play']", function (event) {
        var cid = $(this).attr("value");
        $.ajax({
            url: "course/selectByCid",
            data: {"cid": cid},
            dataType: "json",
            type: "post",
            success: function (ret) {
                console.log(ret);
                if (ret == true) {
                    layer.msg("正在跳转...", {time: 1500});
                    location.href = "评分.html"
                } else {
                    layer.msg("播放失败", {time: 1500});
                }

            }
        })
    });
    // 我的课程
    $("#left-1 li:nth-child(3) a").click(function () {

        $(this).closest("li").attr("class","active")
        $(this).closest("li").siblings().removeAttr("class","active");
        $("#right").hide();
        $("#right-2").hide();
        $("#center").hide();
        $("#right-1").show();

        $("#right-1 tbody").empty();
        $.ajax({
            url: "course/getCourseBySid",
            data: {"sid":sid},
            dataType: "json",
            type: "post",
            async:false,
            success: function (ret) {
                // 声明一个数组
                for(var key in ret.result){
                    $node = $(' <tr class="'+classArr[b++]+'">\n' +
                        '                                    <td>\n' +
                        '                                        <img src="' + ret.result[key].coursePicture + '" width="65px">\n' +
                        '                                    </td>\n' +
                        '                                    <td>\n' +
                        '                                        ' + ret.result[key].courseName + '' +
                        '                                    </td>\n' +
                        '                                    <td>\n' +
                        '                                        ' + key + '' +
                        '                                    </td>\n' +
                        '                                    <td>\n' +
                        '                                        <button class="layui-btn layui-btn-fluid" value="' + ret.result[key].courseId + '" name="play">播放</button>\n' +
                        '                                    </td>\n' +
                        '                                </tr>')

                    $("#right-1 tbody").append($node)
                    if(b%5==0){
                        b=0;
                    }
                }
            }
        })
    })
    // 点击播放
    $("button[name='play']").click(function () {
        location.href="play.html"
    })
    // 我的成绩
    $("#left-1 li:nth-child(5) a").click(function () {

        $(this).closest("li").attr("class","active")
        $(this).closest("li").siblings().removeAttr("class","active");

        $("#right").hide();
        $("#right-1").hide();
        $("#right-2").show();
        $("#right-2 tbody").empty();
        $("#center").hide();
        $.ajax({
            url: "score/getScoreBySid",
            data: {"sid": sid},
            dataType: "json",
            type: "post",
            async:false,
            success: function (ret) {
                if(ret.result.length>0){
                    for(var key in ret.result){
                        if(ret.result[key].score !=null){
                            $node = $(' <tr>\n' +
                                '                                    <td>\n' +
                                '                                        ' + ret.result[key].courseName + '\n' +
                                '                                    </td>\n' +
                                '                                    <td>\n' +
                                '                                        ' + ret.result[key].teacherName + '\n' +
                                '                                    </td>\n' +
                                '                                    <td>\n' +
                                '                                       ' + ret.result[key].score + '\n' +
                                '                                    </td>\n' +
                                '                                </tr>')
                            $("#right-2 tbody").append($node)
                        }
                    }
                }else{
                    layer.msg("该学生还没进行考试....",{time:3000})
                }
            }
        })
    })
    // 点击编辑 文本框可以进行编辑
    $("#edit").click(function () {
        $("#right input").attr("disabled",false)
    })
    // 修改学生信息
    $("#alter").click(function () {
       var name =  $("input[name='name']").val()
        var age  = $("input[name='age']").val();
        var school = $("input[name='school']").val()
       var profession  =  $("input[name='profession']").val()
        var student = JSON.stringify({
            "studentName":name,
            "studentAge":age,
            "studentSchool":school,
            "studentProfession":profession
        })
        $.ajax({
            url:'student/updatestudent',
            dataType:'json',
            data:student,
            type:'post',
            contentType:"application/json; charset=utf-8",
            success:function (ret) {
                console.log(ret)
                layer.msg("修改成功")
            }
        })
    })
    // 我的考试
    $("#left-1 li:nth-child(4) a").click(function () {
        $(this).closest("li").attr("class","active")
        $(this).closest("li").siblings().removeAttr("class","active");
        $("#right").hide();
        $("#right-2").hide();
        $("#right-1").hide();
        $("#center tbody").empty();
        $("#center").show();
        
        $.ajax({
            url:'exam/getExamBySid',
            data:{'sid':sid},
            dataType:'json',
            type:'post',
            async:false,
            success:function (ret) {
                for(var i = 0 ; i<ret.result.length;i++){
                    $node = $('  <tr>\n' +
                        '                                <td>\n' +
                        '                                    '+ret.result[i].examName+'\n' +
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                   '+getTeahcerByTid(ret.result[i].teacherId)+'\n' +
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                   '+ret.result[i].createTime+'\n' +
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                    <button type="button" class="btn btn-primary" name="StartExam" id="'+ret.result[i].examId+'">开始考试</button>\n' +
                        '                                </td>\n' +
                        '                            </tr>')
                    $("#center tbody").append($node)
                }
            }
        })

        $("button").bind("click","button[name='StartExam']",function (event) {
            var examId = $(this).attr("id");
            getvalue(examId)
        })

        function getvalue(examId) {
            localStorage.examId = examId;
            location.href = 'ExamTest.html';
        }


        function getTeahcerByTid(tid) {
            var teacherName ='';
            $.ajax({
                url:'teacher/getTeacherByTid',
                data:{"tid":tid},
                dataType:'json',
                async:false,
                type:'post',
                success:function (ret) {
                    teacherName = ret.result.teacherName;
                }
            })
            return teacherName;
        }
        
        


    })
})