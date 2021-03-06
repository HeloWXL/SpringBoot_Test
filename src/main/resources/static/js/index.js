// 加载页面内容
$(function () {
    // 获取学生登录信息
    $.ajax({
        url: "student/getStudentSession",
        data: {"studentBean": "studentsession"},
        type: "post",
        dataType: "json",
        success: function (ret) {
            if(ret.result==null){
                $('#user1').hide();
                $('#select').hide();
                $('#user-info').hide();
                $('.login').show();
                console.log("用户未登录")
            }else{
                $('#user-info').show();
                $('#user1').show();
                $('#select').show();
                $('.login').hide();

                $node = $('<img src="'+ret.result.studentPicture+'" class="layui-nav-img"><span>'+ret.result.studentName+'</span>');
                $("#user1").html($node);
            }
        }
    })
    // 获取课程列表
    $.ajax({
        url:"course/courseList",
        type:'get',
        dataType:'json',
        async:false,
        success:function (ret) {
            if($.isEmptyObject(ret.result)){
                console.log("获取课程列表失败");
            }else{
                console.log(ret)
                for(var i = 0;i<ret.result.list.length;i++){
                    var image = ret.result.list[i].coursePicture;
                    var name = ret.result.list[i].courseName;
                    var tid = ret.result.list[i].teacherId;
                    var pingfen = ret.result.list[i].coursePingfen;

                    $node = $('<li>\n' +
                        '            <div class="course-list">\n' +
                        '                <a><img src="'+image+'" width="200px"></a>\n' +
                        '                <div class="course-list-info">\n' +
                        '                    <p>课程名:<a class="courseName" id="'+ret.result.list[i].courseId+'">'+name+'</a></p>\n' +
                        '                    <p>教师:<a href="#" class="teacher">'+getTeahcerByTid(tid)+'</a></p>\n' +
                        '                    <div></div>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '        </li>')
                    $(".course").append($node);
                }
            }
        }
    })

    function getTeahcerByTid(tid) {
        var teacherName ='';
        $.ajax({
            url:'teacher/getTeacherByTid',
            data:{"tid":tid},
            dataType:'json',
            async:false,
            type:'post',
            success:function (ret) {
                console.log(ret)
                teacherName = ret.result.teacherName;
            }
        })
        return teacherName;
    }



    // 获取存储值
    function getvalue(id) {
        if (window.localStorage) {
            //存储变量的值
            localStorage.name = id;
            location.href = 'coursedetail.html';
        } else {
            alert("NOT SUPPORT");
        }
    }

    $("div").on("click","a[class='courseName']",function (event) {
        var  id = $(this).attr("id");
        getvalue(id);
    })

})

// -------------------------------------------------------------------------
layui.use(['carousel', 'form'], function () {
    var carousel = layui.carousel
        , form = layui.form;
    //图片轮播
    carousel.render({
        elem: '#test10'
        , width: '100%'
        , height: '440px'
        , interval: 5000
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
