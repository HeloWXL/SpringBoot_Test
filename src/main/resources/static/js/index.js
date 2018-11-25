// 加载页面内容
$(function () {
    // 获取学生登录信息
    $.ajax({
        url: "student/getStuSession",
        data: {"studentBean": "studentSession"},
        type: "get",
        dataType: "json",
        success: function (ret) {
            if($.isEmptyObject(ret)){
                $('#user1').hide();
                $('#select').hide();
                $('#user-info').hide();
            }else{
                $('#user1').show();
                $('#select').show();
                $('.login').hide();
                $node = $('<img src="//t.cn/RCzsdCq" class="layui-nav-img"><span>'+ret.studentName+'</span>');
                $("#user1").html($node);
            }
        }
    })
    // 获取课程列表
    $.ajax({
        url:"course/getAllCourseList",
        type:'get',
        dataType:'json',
        async:false,
        success:function (ret) {
            console.log(ret)
            if($.isEmptyObject(ret)){
                console.log("获取课程列表失败");
            }else{
                for(var i = 0;i<ret.length;i++){
                    var image = ret[i].coursePicture;
                    var name = ret[i].courseName;
                    var tid = ret[i].teacherId;
                    var pingfen = ret[i].coursePingfen;

                    $node = $('<li>\n' +
                        '            <div class="course-list">\n' +
                        '                <a href="coursedetails.html"><img src="'+image+'" width="200px"></a>\n' +
                        '                <div class="course-list-info">\n' +
                        '                    <p>课程名:<a href="coursedetails.html" class="course">'+name+'</a></p>\n' +
                        '                    <p>教师:<a href="#" class="teacher">'+getTeacherTname(tid)+'</a></p>\n' +
                        '                    <div></div>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '        </li>')

                    $(".courselist").append($node);

                }
            }
        }
    })
    function  getTeacherTname(tid){
        var tname = ""
        $.ajax({
            url:"teacher/selectTeacherByTid",
            type:"post",
            dataType:"json",
            data:{"tid":tid},
            async:false,
            success:function (ret) {
                tname = ret.teacherName;
            }
        })
        return  tname;
    }
})


layui.use(['carousel', 'form'], function () {
    var carousel = layui.carousel
        , form = layui.form;
    //图片轮播
    carousel.render({
        elem: '#test10'
        , width: '100%'
        , height: '440px'
        , interval: 3000
    });
    //监听开关
    form.on('switch(autoplay)', function () {
        ins3.reload({
            autoplay: this.checked
        });
    });
    $('.demoSet').on('keyup', function () {
        var value = this.value
            , options = {};
        if (!/^\d+$/.test(value)) return;

        options[this.name] = value;
        ins3.reload(options);
    });

    //其它示例
    $('.demoTest .layui-btn').on('click', function () {
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });
});

layui.use(['rate'], function() {
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '.course-list-info div'
        ,value: 3
        ,readonly: true
    });
})