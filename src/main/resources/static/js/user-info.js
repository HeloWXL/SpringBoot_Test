$(function () {
    var b = 0;
    var classArr = new Array("", "success", "error", "warning", "info");
    // 课程的ID
    var cid;
    // 学生的ID
    var sid;

    $.ajax({
        url: "student/getStudentSession",
        data: {"studentBean": "studentsession"},
        type: "post",
        dataType: "json",
        success: function (ret) {
            cid = ret.result.classId;
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
                if (typeof (ret.result.studentPicture) == null) {
                    pic = "images/h.jpg";
                } else {
                    pic = ret.result.studentPicture;
                }

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
            }
        }
    })



    // 弹出公告号信息
    // $.ajax({
    //     url:'notice/getNotice',
    //     dataType:'json',
    //     type:'post',
    //     data:{"sid":sid},
    //     success:function (ret) {
    //         layui.use('layer', function(){ //独立版的layer无需执行这一句
    //             var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    //             //触发事件
    //             var active = {
    //                 notice: function(){
    //                     //示范一个公告层
    //                     layer.open({
    //                         type: 1
    //                         ,title: ret.noticeTitle
    //                         ,closeBtn: true
    //                         ,area: '300px;'
    //                         ,shade: 0.8
    //                         ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
    //                         ,btn: ['收到', '残忍拒绝']
    //                         ,btnAlign: 'c'
    //                         ,moveType: 1 //拖拽模式，0或者1
    //                         ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">ret.noticeContent</div>'
    //                         ,success: function(layero){
    //                             var btn = layero.find('.layui-layer-btn');
    //                             btn.find('.layui-layer-btn0').attr({
    //                             });
    //                         }
    //                     });
    //                 }
    //             };
    //
    //             $('#layerDemo .layui-btn').on('click', function(){
    //                 var othis = $(this), method = othis.data('method');
    //                 active[method] ? active[method].call(this, othis) : '';
    //             });
    //
    //         });
    //     }
    //
    // })


    // 我的信息
    $("#left-1 li:nth-child(2) a").click(function () {
        $("#right").show();
        $("#right-1").hide();
        $("#right-2").hide();
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
                    location.href = "videos.html"
                } else {
                    layer.msg("播放失败", {time: 1500});
                }

            }

        })
    });

    // 我的课程
    $("#left-1 li:nth-child(3) a").click(function () {
        $("#right").hide();
        $("#right-2").hide();
        $("#right-1").show();
        $("#right-1 tbody").empty();
        $.ajax({
            url: "course/getCourseByCid",
            data: {"cid": cid},
            dataType: "json",
            type: "post",
            success: function (ret) {
                // 声明一个数组
                for(var key in ret.result){
                    for(var i = 0 ;i<ret.result[key].length;i++){
                        $node = $(' <tr>\n' +
                            '                                    <td>\n' +
                            '                                        <img src="' + ret.result[key][i].coursePicture + '" width="65px">\n' +
                            '                                    </td>\n' +
                            '                                    <td>\n' +
                            '                                        ' + ret.result[key][i].courseName + '' +
                            '                                    </td>\n' +
                            '                                    <td>\n' +
                            '                                        ' + key + '' +
                            '                                    </td>\n' +
                            '                                    <td>\n' +
                            '                                        <button class="layui-btn layui-btn-fluid" value="' + ret.result[key][i].courseId + '" name="play">播放</button>\n' +
                            '                                    </td>\n' +
                            '                                </tr>')

                        $("#right-1 tbody").append($node)
                    }
                }
            }
        })
    })
    $("button[name='play']").click(function () {
        var cid = $(this).attr("value");
        alert(cid)
    })

    // 我的成绩
    $("#left-1 li:nth-child(4) a").click(function () {
        $("#right").hide();
        $("#right-1").hide();
        $("#right-2").show();
        $("#right-2 tbody").empty();
        $.ajax({
            url: "score/getScoreBySid",
            data: {"sid": sid},
            dataType: "json",
            type: "post",
            success: function (ret) {
                var courseId = ret.result.courseId;
                var score = ret.result.score;

                console.log(getCourseByCid(courseId))

                // $node = $(' <tr>\n' +
                //     '                                    <td>\n' +
                //     '                                        ' + ret.course.cname + '\n' +
                //     '                                    </td>\n' +
                //     '                                    <td>\n' +
                //     '                                        ' + ret.teacher.tname + '\n' +
                //     '                                    </td>\n' +
                //     '                                    <td>\n' +
                //     '                                       ' + ret.score + '\n' +
                //     '                                    </td>\n' +
                //     '                                </tr>')
                // $("#right-2 tbody").append($node)
            }
        })
    })

// 点击编辑 文本框可以进行编辑
    $("#edit").click(function () {
        $("#right input").attr("disabled",false)
    })
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
})

// 获取课程名
function getCourseByCid(cid) {
    var coursename='';
    $.ajax({
        url:'course/getCourseByCid',
        data:{'cid':cid},
        dataType:'json',
        type:'post',
        async : false,
        success:function (ret) {
            coursename = ret.result.courseName;
        }
    })
    return coursename;
}


console.log(getCourseByCid(203))
// function getTeacherByTid(tid) {
//
// }