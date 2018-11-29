$(function () {
    // 全局变量
    var a = 0;
    var b = 0;
    var classArr = new Array("", "success", "error", "warning", "info");
    var image = "images/img.jpg";
    var images = "images/c2.jpg";
    String.prototype.endWith = function (endStr) {
        var d = this.length - endStr.length;
        return (d >= 0 && this.lastIndexOf(endStr) == d);
    }

    var s = ".jpg";
// ------------------首页统计数据----------------首页事件-----------------------------------
    $("a[name='index']").click(function () {
        $("#index").show();
        $("#student").hide();
        $("#teacher").hide();
        $("#static").hide();
        $("#course").hide();
        $("#score").hide();
        $("#addmin").hide();
    })
    $.ajax({
        url: "admin/countForAdmin",
        dataType: "json",
        type: 'get',
        success: function (ret) {
            $("#count-ul li:nth-child(1) p span").html(ret.result.student);
            $("#count-ul li:nth-child(2) p span").html(ret.result.teacher);
            $("#count-ul li:nth-child(3) p span").html(ret.result.course);
        }
    })

// ------------------------------获取管理员登录信息---------------------------------------
    $.ajax({
        url: "admin/getAdminSession",
        dataType: "json",
        data: {"adminBean": "adminSession"},
        type: "post",
        success: function (ret) {
            if ($.isEmptyObject(ret.result)) {
                $("#admin").hide();
                console.log("管理员未登录");
            } else {
                $("#admin").show();
                $("#admin p").html(ret.result.adminName);

            }
        }
    })

// ----------------------退出登录--管理员  没处理好--------------------
    $("#admin a").click(function () {
        $.ajax({
            url: "admin/AdminLoginOut",
            dataType: "json",
            type: "POST",
            success: function (ret) {
                console.log("loginout:" + ret);
                if (ret == true) {
                    layer.msg("用户已退出", {time: 2000});
                    location.href = "backlogin.html";
                }
            }

        })
    })

// -----------------------------学生管理----------------------------------------
    $("a[name='student']").click(function () {
        var pageNo = 1;
        var pageSize = 9;
        var pageCount = 0;
        $("#student").show();
        $("#teacher").hide();
        $("#static").hide();
        $("#course").hide();
        $("#score").hide();
        $("#index").hide();
        $("#addmin").hide();
        // 先清空之前的列表
        $("#student table tbody").empty();
        $.ajax({
            url: "student/studentList",
            type: "get",
            dataType: "json",
            data: {"pageNo": pageNo, "pageSize": pageSize},
            cache: false,
            async: false,
            success: function (ret) {
                pageCount = ret.result.count;
                for (var i = 0; i < ret.result.list.length; i++) {
                    $node = $("<tr class=\"" + classArr[b++] + "\">\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentId + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    <img src=" + ret.result.list[i].studentPicture + " width=\"50px\">\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentName + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentAge + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentSex + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentSchool + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                   <button type=\"button\" class=\"btn btn-info\" name='update'>修改</button> " +
                        "                                   <button type=\"button\" class=\"btn btn-warning\" name='delete'>删除</button> " +
                        "                                </td>\n" +
                        "                            </tr>")
                    $("#student table tbody").append($node);
                    if (b == 5) {
                        b = 0;
                    }
                }
            }
        })

        // 根据学生的ID删除学生的信息 没做好哦
        $("button").on("click", "input[name='delete']']", function (event) {
            alert($(this))
        })

        // 分页管理-下一页
        $("#student-fenye ul li button[name='next']").click(function () {
            $("#student table tbody").empty();

            var maxpage = Math.ceil(pageCount / 10);
            if (pageNo < maxpage) {
                ++pageNo;
            } else {
                pageNo = maxpage;
                layer.msg("已经是最后一页了", {time: 2500});
            }
            getstudentLit(pageNo, pageSize);

        });
        // 分页管理-上一页
        $("#student-fenye ul li button[name='prev']").click(function () {
            $("#student table tbody").empty();
            if (pageNo == 1) {
                layer.msg("已经是第一页了", {time: 2500});
            } else {
                pageNo--;
            }
            getstudentLit(pageNo, pageSize);
        });
    })
    function getstudentLit(pageNo, pageSize) {
        $.ajax({
            url: "student/studentList",
            type: "get",
            dataType: "json",
            data: {"pageNo": pageNo, "pageSize": pageSize},
            cache: false,
            async: false,
            success: function (ret) {
                pageCount = ret.result.count;
                for (var i = 0; i < ret.result.list.length; i++) {
                    $node = $("<tr class=\"" + classArr[b++] + "\">\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentId + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    <img src=" + ret.result.list[i].studentPicture + " width=\"50px\">\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentName + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentAge + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentSex + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].studentSchool + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                   <button type=\"button\" class=\"btn btn-info\" name='update'>修改</button> " +
                        "                                   <button type=\"button\" class=\"btn btn-warning\" name='delete'>删除</button> " +
                        "                                </td>\n" +
                        "                            </tr>")
                    $("#student table tbody").append($node);
                    if (b == 5) {
                        b = 0;
                    }
                }
            }
        })
    }

// ------------------------------获取教师的列表函数----------------------------------------
    $("a[name='teacher']").click(function () {
        var pageNo = 1;
        var pageSize = 9;
        var pageCount = 0;
        $("#teacher").show();
        $("#student").hide();
        $("#static").hide();
        $("#course").hide();
        $("#score").hide();
        $("#index").hide();
        $("#addmin").hide();
        // 先将教师列表进行清空
        $("#teacher table tbody").empty();
        //加载教师信息
        $.ajax({
            url: "teacher/teachertList",
            type: "get",
            data: {"pageNo": pageNo, "pageSize": pageSize},
            dataType: "json",
            cache: false,
            async: false,
            success: function (ret) {
                pageCount = ret.result.count;
                for (var i = 0; i < ret.result.list.length; i++) {
                    $node = $("<tr class=\"" + classArr[a++] + "\">\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherId + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    <img src=" + ret.result.list[i].teacherPicture + " width=\"50px\">\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherName + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherMajor + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherAge + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherSex + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                   <button type=\"button\" class=\"btn btn-info\" name='update'>修改</button> " +
                        "                                   <button type=\"button\" class=\"btn btn-warning\" name='delete'>删除</button> " +
                        "                                </td>\n" +
                        "                            </tr>")
                    $("#teacher table tbody").append($node);
                    if (a == 5) {
                        a = 0;
                    }
                }
            }
        })
        // 分页管理-下一页
        $("#teacher-fenye ul li button[name='next']").click(function () {
            $("#teacher table tbody").empty();
            var maxpage = Math.ceil(pageCount / 10);
            if (pageNo < maxpage) {
                ++pageNo;
            } else {
                pageNo = maxpage;
                layer.msg("已经是最后一页了", {time: 1500});
            }
            getTeacherList(pageNo, pageSize);
        });
        // 分页管理-上一页
        $("#teacher-fenye ul li button[name='prev']").click(function () {
            $("#teacher table tbody").empty();
            if (pageNo == 1) {
                layer.msg("已经是第一页了", {time: 1500});
            } else {
                pageNo--;
            }
            getTeacherList(pageNo, pageSize);
        });
    })
    function getTeacherList(pageNo, pageSize) {
        $.ajax({
            url: "teacher/teachertList",
            type: "get",
            data: {"pageNo": pageNo, "pageSize": pageSize},
            dataType: "json",
            cache: false,
            async: false,
            success: function (ret) {
                pageCount = ret.result.count;
                for (var i = 0; i < ret.result.list.length; i++) {
                    $node = $("<tr class=\"" + classArr[a++] + "\">\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherId + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    <img src=" + ret.result.list[i].teacherPicture + " width=\"50px\">\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherName + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherMajor + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherAge + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].teacherSex + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                   <button type=\"button\" class=\"btn btn-info\" name='update'>修改</button> " +
                        "                                   <button type=\"button\" class=\"btn btn-warning\" name='delete'>删除</button> " +
                        "                                </td>\n" +
                        "                            </tr>")
                    $("#teacher table tbody").append($node);
                    if (a == 5) {
                        a = 0;
                    }
                }
            }
        })
    }


// ----------------------------------------------------------------------
    $("a[name='static']").click(function () {
        $("#static").show();
        $("#student").hide();
        $("#teacher").hide();
        $("#course").hide();
        $("#score").hide();
        $("#index").hide();
        $("#addmin").hide();
    })

// ----------------获取课程的 列表----------------------------------------
    $("a[name='course']").click(function () {
        $("#course").show();
        $("#score").hide();
        $("#student").hide();
        $("#teacher").hide();
        $("#static").hide();
        $("#index").hide();
        $("#addmin").hide();
        var pageNo = 1;
        var pageSize = 9;
        var pageCount = 0;
        // 先将教师列表进行清空
        $("#course table tbody").empty();
        //加载教师信息
        $.ajax({
            url: "course/courseList",
            type: "get",
            data: {"pageNo": pageNo, "pageSize": pageSize},
            dataType: "json",
            cache: false,
            async: false,
            success: function (ret) {
                pageCount = ret.result.count;
                for (var i = 0; i < ret.result.list.length; i++) {
                    $node = $("<tr class=\"" + classArr[a++] + "\">\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].courseId + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    <img src=" + ret.result.list[i].coursePicture + " width=\"50px\">\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].courseName + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].coursePingfen + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].courseIntroduce + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].courseVideo + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                   <button type=\"button\" class=\"btn btn-info\" name='update'>修改</button> " +
                        "                                   <button type=\"button\" class=\"btn btn-warning\" name='delete'>删除</button> " +
                        "                                </td>\n" +
                        "                            </tr>")
                    $("#course table tbody").append($node);
                    if (a == 5) {
                        a = 0;
                    }
                }
            }
        })
        // 分页管理-下一页
        $("#course-fenye ul li button[name='next']").click(function () {
            $("#course table tbody").empty();
            var maxpage = Math.ceil(pageCount / 10);
            if (pageNo < maxpage) {
                ++pageNo;
            } else {
                pageNo = maxpage;
                layer.msg("已经是最后一页了", {time: 2500});
            }
            getCourseList(pageNo, pageSize);
        });
        // 分页管理-上一页
        $("#course-fenye ul li button[name='prev']").click(function () {
            $("#course table tbody").empty();
            if (pageNo == 1) {
                layer.msg("已经是第一页了", {time: 2500});
            } else {
                pageNo--;
            }
            getCourseList(pageNo, pageSize);
        })
    })
    function getCourseList(pageNo, pageSize) {
        $.ajax({
            url: "course/courseList",
            type: "get",
            data: {"pageNo": pageNo, "pageSize": pageSize},
            dataType: "json",
            cache: false,
            async: false,
            success: function (ret) {
                pageCount = ret.result.count;
                for (var i = 0; i < ret.result.list.length; i++) {
                    $node = $("<tr class=\"" + classArr[a++] + "\">\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].courseId + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    <img src=" + ret.result.list[i].coursePicture + " width=\"50px\">\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].courseName + "\n" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].coursePingfen + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].courseIntroduce + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                    " + ret.result.list[i].courseVideo + "" +
                        "                                </td>\n" +
                        "                                <td>\n" +
                        "                                   <button type=\"button\" class=\"btn btn-info\" name='update'>修改</button> " +
                        "                                   <button type=\"button\" class=\"btn btn-warning\" name='delete'>删除</button> " +
                        "                                </td>\n" +
                        "                            </tr>")
                    $("#course table tbody").append($node);
                    if (a == 5) {
                        a = 0;
                    }
                }
            }
        })
    }
// ------------------------------------------------------------------------
    $("a[name='score']").click(function () {
        $("#score").show();
        $("#student").hide();
        $("#teacher").hide();
        $("#course").hide();
        $("#static").hide();
        $("#index").hide();
        $("#addmin").hide();


        $.ajax({
            url:'score/getScoreList',
            dataType:'json',
            type:'post',
            success:function (ret) {
                if(ret.result.length>0){
                    var sid = ret.result.studentId;
                    var cid = ret.result.teacherId;
                    var score = ret.result.score;
                    console.log(ret);
                }else{
                    layer.msg("获取失败",{time:3000})
                }
            }
        })
        //按照班级进行查询



    })


// -----------------------新增管理员-------------------------------------------------
    $("a[name='addadmin']").click(function () {
        layer.open({
            title: false,
            type: 2,
            closeBtn: 0, //不显示关闭按钮
            shade: 0.5,
            area: ['300px', '300px'],
            offset: 'auto',
            anim: 2,
            shadeClose: true,
            content: ['addAdmin.html', 'no']
        });

    })
})
// ------------------------------------------------------------------------

//     var math;
//     var chinese;
//     var english;
//     var polity;
//     $.ajax({
//         url: 'scoreview/getEveryClassScore',
//         type: 'get',
//         dataType: 'json',
//         success: function (ret) {
//             // 课程
//             chinese = ret[0].chinese;
//             math = ret[0].math;
//             english = ret[0].english;
//             polity = ret[0].polity;
//             chart_1(chinese, math, english, polity)
//             // 语文成绩 雷达图
//             chinese_echarts(chinese);
//             // 数学成绩雷达图
//             math_echarts(math)
//             //英语成绩雷达图
//             english_echarts(english)
//             // 政治成绩雷达图
//             polity_echarts(polity)
//
//         }
//     })
// })
// function math_echarts(math) {
//     var chart3 = echarts.init(document.getElementById("chart3"));
//     option = {
//         title : {
//             text: '数学考试成绩分析',
//         },
//         tooltip : {
//             trigger: 'axis'
//         },
//         legend: {
//             orient : 'vertical',
//             x : 'right',
//             y : 'bottom',
//             data:['数学']
//         },
//         toolbox: {
//             show : true,
//             feature : {
//                 mark : {show: true},
//                 dataView : {show: true, readOnly: false},
//                 restore : {show: true},
//                 saveAsImage : {show: true}
//             }
//         },
//         polar : [
//             {
//                 indicator : [
//                     { text: '101', max: 100},
//                     { text: '102', max: 100},
//                     { text: '103', max: 100},
//                     { text: '104', max: 100},
//                     { text: '105', max: 100},
//                     { text: '106', max: 100},
//                     { text: '107', max: 100},
//                     { text: '108', max: 100},
//                     { text: '109', max: 100},
//                     { text: '110', max: 100}
//                 ]
//             }
//         ],
//         calculable : true,
//         series : [
//             {
//                 name: '数学成绩雷达图',
//                 type: 'radar',
//                 data : [
//                     {
//                         value : math,
//                         name : '数学'
//                     }
//                 ]
//             }
//         ]
//     };
//     chart3.setOption(option)
// }
// function chinese_echarts(chinese) {
//     var chart2 = echarts.init(document.getElementById("chart2"));
//     option = {
//         title : {
//             text: '语文考试成绩雷达图',
//         },
//         tooltip : {
//             trigger: 'axis'
//         },
//         legend: {
//             orient : 'vertical',
//             x : 'right',
//             y : 'bottom',
//             data:['语文']
//         },
//         toolbox: {
//             show : true,
//             feature : {
//                 mark : {show: true},
//                 dataView : {show: true, readOnly: false},
//                 restore : {show: true},
//                 saveAsImage : {show: true}
//             }
//         },
//         polar : [
//             {
//                 indicator : [
//                     { text: '101', max: 100},
//                     { text: '102', max: 100},
//                     { text: '103', max: 100},
//                     { text: '104', max: 100},
//                     { text: '105', max: 100},
//                     { text: '106', max: 100},
//                     { text: '107', max: 100},
//                     { text: '108', max: 100},
//                     { text: '109', max: 100},
//                     { text: '110', max: 100}
//                 ]
//             }
//         ],
//         calculable : true,
//         series : [
//             {
//                 name: '语文成绩雷达图',
//                 type: 'radar',
//                 data : [
//                     {
//                         value : chinese,
//                         name : '语文'
//                     }
//                 ]
//             }
//         ]
//     };
//     chart2.setOption(option)
// }
// function english_echarts(english) {
//     var chart4 = echarts.init(document.getElementById("chart4"));
//     option = {
//         title : {
//             text: '英语考试成绩雷达图',
//         },
//         tooltip : {
//             trigger: 'axis'
//         },
//         legend: {
//             orient : 'vertical',
//             x : 'right',
//             y : 'bottom',
//             data:['英语']
//         },
//         toolbox: {
//             show : true,
//             feature : {
//                 mark : {show: true},
//                 dataView : {show: true, readOnly: false},
//                 restore : {show: true},
//                 saveAsImage : {show: true}
//             }
//         },
//         polar : [
//             {
//                 indicator : [
//                     { text: '101', max: 100},
//                     { text: '102', max: 100},
//                     { text: '103', max: 100},
//                     { text: '104', max: 100},
//                     { text: '105', max: 100},
//                     { text: '106', max: 100},
//                     { text: '107', max: 100},
//                     { text: '108', max: 100},
//                     { text: '109', max: 100},
//                     { text: '110', max: 100}
//                 ]
//             }
//         ],
//         calculable : true,
//         series : [
//             {
//                 name: '英语成绩雷达图',
//                 type: 'radar',
//                 data : [
//                     {
//                         value : english,
//                         name : '英语'
//                     }
//                 ]
//             }
//         ]
//     };
//     chart4.setOption(option)
// }
// function polity_echarts(polity) {
//     var chart5 = echarts.init(document.getElementById("chart5"));
//     option = {
//         title : {
//             text: '政治考试成绩雷达图',
//         },
//         tooltip : {
//             trigger: 'axis'
//         },
//         legend: {
//             orient : 'vertical',
//             x : 'right',
//             y : 'bottom',
//             data:['政治']
//         },
//         toolbox: {
//             show : true,
//             feature : {
//                 mark : {show: true},
//                 dataView : {show: true, readOnly: false},
//                 restore : {show: true},
//                 saveAsImage : {show: true}
//             }
//         },
//         polar : [
//             {
//                 indicator : [
//                     { text: '101', max: 100},
//                     { text: '102', max: 100},
//                     { text: '103', max: 100},
//                     { text: '104', max: 100},
//                     { text: '105', max: 100},
//                     { text: '106', max: 100},
//                     { text: '107', max: 100},
//                     { text: '108', max: 100},
//                     { text: '109', max: 100},
//                     { text: '110', max: 100}
//                 ]
//             }
//         ],
//         calculable : true,
//         series : [
//             {
//                 name: '政治成绩雷达图',
//                 type: 'radar',
//                 data : [
//                     {
//                         value : polity,
//                         name : '政治'
//                     }
//                 ]
//             }
//         ]
//     };
//     chart5.setOption(option)
// }
// function chart_1(chinese,math,english,polity){
//     // 基于准备好的容器(这里的容器是id为chart1的div)，初始化echarts实例
//     var chart1 = echarts.init(document.getElementById("chart1"));
//     option = {
//         title : {
//             text: '学生考试成绩分析',
//         },
//         tooltip : {
//             trigger: 'axis'
//         },
//         legend: {
//             orient : 'vertical',
//             x : 'right',
//             y : 'bottom',
//             data:['语文','数学','英语','政治']
//         },
//         toolbox: {
//             show : true,
//             feature : {
//                 mark : {show: true},
//                 dataView : {show: true, readOnly: false},
//                 restore : {show: true},
//                 saveAsImage : {show: true}
//             }
//         },
//         polar : [
//             {
//                 indicator : [
//                     { text: '101', max: 100},
//                     { text: '102', max: 100},
//                     { text: '103', max: 100},
//                     { text: '104', max: 100},
//                     { text: '105', max: 100},
//                     { text: '106', max: 100},
//                     { text: '107', max: 100},
//                     { text: '108', max: 100},
//                     { text: '109', max: 100},
//                     { text: '110', max: 100}
//                 ]
//             }
//         ],
//         calculable : true,
//         series : [
//             {
//                 name: '成绩分析表',
//                 type: 'radar',
//                 data : [
//                     {
//                         value : chinese,
//                         name : '语文'
//                     },
//                     {
//                         value : math,
//                         name : '数学'
//                     },
//                     {
//                         value :english,
//                         name : '英语'
//                     },
//                     {
//                         value : polity,
//                         name : '政治'
//                     }
//                 ]
//             }
//         ]
//     };
//     chart1.setOption(option)
// }
