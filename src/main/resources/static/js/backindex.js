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
        $(this).closest("li").attr("class","active")
        $(this).closest("li").siblings().removeAttr("class","active");

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
// ---------------------学生管理----------------------------------------
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

        // 设置li的 class属性值
        $(this).closest("li").attr("class","active")
        $(this).closest("li").siblings().removeAttr("class","active");
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
        $(".fenye ul li button[name='next']").click(function () {
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
        $(".fenye ul li button[name='prev']").click(function () {
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
// --------------------获取教师的列表函数----------------------------------------
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
        $(this).closest("li").attr("class","active")
        $(this).closest("li").siblings().removeAttr("class","active");

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
        $(".fenye ul li button[name='next']").click(function () {
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
        $(".fenye ul li button[name='prev']").click(function () {
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
// ----------------获取课程的列表----------------------------------------
    $("a[name='course']").click(function () {
        $("#course").show();
        $("#score").hide();
        $("#student").hide();
        $("#teacher").hide();
        $("#static").hide();
        $("#index").hide();
        $("#addmin").hide();
        $(this).closest("li").attr("class","active")
        $(this).closest("li").siblings().removeAttr("class","active");

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
        $(".fenye ul li button[name='next']").click(function () {
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
        $(".fenye ul li button[name='prev']").click(function () {
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
// --------------------成绩列表及统计图实现---------------------------------------------------
    $("a[name='score']").click(function () {
        $("#score").show();
        $("#student").hide();
        $("#teacher").hide();
        $("#course").hide();
        $("#static").hide();
        $("#index").hide();
        $("#addmin").hide();

        $(this).closest("li").attr("class","active")
        $(this).closest("li").siblings().removeAttr("class","active");

        //按照班级进行查询
        $(".selectpicker").empty();
        // 加载可选框
        $.ajax({
            url: 'course/getCourseNameByCourseid',
            dataType: 'json',
            type: 'get',
            cache: false,
            success: function (ret) {
                for (var i = 0; i < ret.result.length; i++) {
                    $('<option value="' + ret.result[i].courseId + '">' + ret.result[i].courseName + '</option>').appendTo(".selectpicker")
                }
            }
        })

        $("#find").click(function () {
            $("#score table").empty()
            $("#chart1").hide();
            $("#score table").show();
            var id = $("#selectCourse").val();
            $.ajax({
                url: 'score/getScoreByCourseId',
                dataType: 'json',
                type: 'get',
                data: {"cid": id},
                success: function (ret) {
                    console.log(ret)
                    for (var i = 0; i < ret.result.length; i++) {
                        $node = $(' <tr class="'+classArr[b++]+'">\n' +
                            '                                <td>\n' +
                            '                                    ' + ret.result[i].studentName + '\n' +
                            '                                </td>\n' +
                            '                                <td>\n' +
                            '                                    ' + ret.result[i].courseName + '\n' +
                            '                                </td>\n' +
                            '                                <td>\n' +
                            '                                    ' + ret.result[i].score + '\n' +
                            '                                </td>\n' +
                            '                            </tr>')
                        $("#score table").append($node);
                        if(b%5==0){
                            b=0;
                        }
                    }

                }
            })
        })
        $("#statics").click(function () {
            $("#chart1").show();
            $("#score table").hide();
            var cid = $("#selectCourse").val();

            $.ajax({
                url:'score/getCountByCourseId',
                data:{'cid':cid},
                dataType:'json',
                type:'post',
                success:function (ret) {
                    var list = ret.result;
                    if(list==null){
                        layer.msg('该课程暂无成绩')
                    }else{
                        getStatis(list)
                    }

                }
            })

        })

    })
    // 得到统计分析图
    function getStatis(data) {
        var chart1 = echarts.init(document.getElementById("chart1"));
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data:['人数']
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['50分以下','50-60分','60-70分','70-80分','80-90分','90-100分'],
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '人数',
                    min: 0,
                    max: 20,
                    interval: 50,
                    axisLabel: {
                        formatter: '{value} 人'
                    }
                }
            ],
            series: [
                {
                    name:'人数',
                    type:'bar',
                    data:data,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    }
                }
            ]
        };
        chart1.setOption(option)
    }
// --------------------管理员---------------------------------------------------
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