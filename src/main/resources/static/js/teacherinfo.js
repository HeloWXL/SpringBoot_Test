$(function () {

var b = 0;
var classArr = new Array("", "success", "error", "warning", "info");
var tid;
var classId;
// 加载教师信息
$.ajax({
    url:'teacher/getTeacherSession',
    dataype:'json',
    data:{"teacherBean":"teachersession"},
    type:'post',
    success:function (ret) {
        tid = ret.result.teacherId;
        classId = ret.result.classId;
        console.log(classId)
        $("#t-info img").attr("src",ret.result.teacherPicture)
        // 教师姓名
        $("input[name='name']").val(ret.result.teacherName);
        // 教师性别
        $("input[name='sex']").val(ret.result.teacherSex);
        // 教师年龄
        $("input[name='age']").val(ret.result.teacherAge);
        // 教师所教授课程
        $("input[name='major']").val(ret.result.teacherMajor);
    }
})
// 查看发布的公告
// $("#listnotice").click(function () {
//     $("#t-course").hide();
//     $("#t-score").hide();
//     $("#t-notice").hide();
//     $("#t-student").hide();
//     $("#t-test").hide();
//     $("#t-listnotice").show();
//     $("#t-listnotice tbody").empty();
//     $.ajax({
//         url:'notice/selectnoticebyTid',
//         dataType:'json',
//         data:{"tid":tid},
//         type:"post",
//         success:function (ret) {
//             console.log(ret)
//             for(var i = 0;i <ret.length;i++){
//
//                 var content  = ret[i].noticeContent;
//
//                 var $node = $('<tr class="'+classArr[b++]+'">\n' +
//                     '                        <td>\n' +
//                     '                            '+ret[i].noticeId+'\n' +
//                     '                        </td>\n' +
//                     '                        <td>\n' +
//                     '                            '+ret[i].noticeTitle+'\n' +
//                     '                        </td>\n' +
//                     '                        <td>\n' +
//                     '                             '+content+'\n' +
//                     '                        </td>\n' +
//                     '                        <td>\n' +
//                     '                            '+ret[i].noticeTime+'\n' +
//                     '                        </td>\n' +
//                     '                    </tr>')
//                 $("#t-listnotice tbody").append($node)
//             }
//
//         }
//     })
// })
// // 发布公告
// $("#notices").click(function () {
//     $("#t-course").hide();
//     $("#t-score").hide();
//     $("#t-notice").show();
//     $("#t-student").hide();
//     $("#t-test").hide();
//     $("#t-listnotice").hide();
//
//     $("button[name='submit']").click(function () {
//         $.ajax({
//             url:'notice/insertnotice',
//             dataType:'json',
//             data:{"title":$("#title").val(),
//                 "content":$("#contents").val(),
//                 "tid":tid},
//             type:"post",
//             success:function (ret) {
//                 if(ret==true){
//                     layer.msg("发布成功",{time:2000})
//                 }else{
//                     layer.msg("发布失败",{time:2000})
//                 }
//             }
//         })
//     })
//     $("")
//
//
//
// })

// 我的课程
$("#mycourse").click(function () {
    $("#t-course").show();
    $("#t-score").hide();
    $("#t-notice").hide();
    $("#t-student").hide();
    $("#t-test").hide();
    $("#t-listnotice").hide();
    $("#t-course tbody").empty();
    var pageNo = 1;
    var pageSize = 9;
    var pageCount = 0;
    $.ajax({
        url:'course/getCourseByTid',
        dataType:'json',
        type:'get',
        data:{"tid":tid,"pageNo":pageNo,"pageSize":pageSize},
        success:function (ret) {
            pageCount = ret.result.count;
            console.log('ajax:'+pageCount)
            for(var i = 0;i<ret.result.list.length;i++){
                $node =$('<tr class="'+classArr[b++]+'">\n' +
                    '                        <td>\n' +
                    '                            '+ret.result.list[i].courseName+'\n' +
                    '                        </td>\n' +
                    '                        <td>\n' +
                    '                            <img src="'+ret.result.list[i].coursePicture+'" width="65px">\n' +
                    '                        </td>\n' +
                    '                        <td>\n' +
                    '                            2018-11-24\n' +
                    '                        </td>\n' +
                    '                        <td>\n' +
                    '                            '+ret.result.list[i].courseIntroduce+'\n' +
                    '                        </td>\n' +
                    '                        <td>\n' +
                    '                            '+ret.result.list[i].coursePingfen+'\n' +
                    '                        </td>\n' +
                    '                    </tr>')
                $("#t-course tbody").append($node);
                if(b==5){
                    b=0;
                }
            }
        }
    })
    // 教师课程-下一页
    $("button[name='next']").click(function () {
        $("#t-course tbody").empty();
        var maxpage = Math.ceil(pageCount/9);
        if(pageNo<maxpage){
            ++pageNo;
        }else{
            pageNo=maxpage;
            layer.msg("已经是最后一页了",{time:1500});
        }
        getCourseByTid(pageNo,pageSize);
    })
    // 教师课程-上一页
    $("button[name='prev']").click(function () {
        $("#t-course tbody").empty();
        if(pageNo==1){
            layer.msg("已经是第一页了",{time:1500});
        }else{
            pageNo--;
        }
        getCourseByTid(pageNo,pageSize);
    })

})
// 获取教师的课程信息
function getCourseByTid(pageNo,pageSize) {
    $.ajax({
        url:'course/getCourseByTid',
        dataType:'json',
        type:'get',
        data:{"tid":tid,"pageNo":pageNo,"pageSize":pageSize},
        success:function (ret) {
            // console.log(ret.result.list[1])
            pageCount = ret.result.count;
            for(var i = 0;i<ret.result.list.length;i++){
                $node =$('<tr class="'+classArr[b++]+'">\n' +
                    '                        <td>\n' +
                    '                            '+ret.result.list[i].courseName+'\n' +
                    '                        </td>\n' +
                    '                        <td>\n' +
                    '                            <img src="'+ret.result.list[i].coursePicture+'" width="65px">\n' +
                    '                        </td>\n' +
                    '                        <td>\n' +
                    '                            2018-11-24\n' +
                    '                        </td>\n' +
                    '                        <td>\n' +
                    '                            '+ret.result.list[i].courseIntroduce+'\n' +
                    '                        </td>\n' +
                    '                        <td>\n' +
                    '                            '+ret.result.list[i].coursePingfen+'\n' +
                    '                        </td>\n' +
                    '                       <td>\n' +
                    '                      <div id="caozuo-course">\n' +
                    '                    <ul class="pagination">\n' +
                    '                        <li><button type="button" class="btn btn-info" name="delete">删除</button></li>\n' +
                    '                    </ul>\n' +
                    '                </div>     \n' +
                    '                       </td>\n' +
                    '                    </tr>')
                $("#t-course tbody").append($node);
                if(b==5){
                    b=0;
                }
            }
        }
    })
}
// 我的学生
$("#mystudent").click(function () {
    var pageNo = 1;
    var pageSize = 9;
    var pageCount = 0;
    $("#t-course").hide();
    $("#t-score").hide();
    $("#t-notice").hide();
    $("#t-student").show();
    $("#t-test").hide();
    $("#t-listnotice").hide();
    $("#t-student tbody").empty();
    $.ajax({
        url:'student/getStudentListByClassID',
        dataType:'json',
        type:'post',
        data:{"classId":classId,"pageNo":pageNo,"pageSize":pageSize},
        success:function (ret) {
            pageCount = ret.result.count;
            if($.isEmptyObject(ret)){
                console.log("获取我的学生信息失败")
            }else{
                for(var i = 0;i<ret.result.list.length;i++){

                    if(ret.result.list[i].studentProfession==null){
                        continue;
                    }else{
                        $node = $('  <tr class="'+classArr[b++]+'">\n' +
                            '                       <td>\n' +
                            '                           '+ret.result.list[i].studentName+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                            <img src="'+ret.result.list[i].studentPicture+'" width="65px">\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                          '+ret.result.list[i].studentSex+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                          '+ret.result.list[i].studentAge+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                          '+ret.result.list[i].studentProfession+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                          '+ret.result.list[i].studentSchool+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                      <div id="caozuo-student">\n' +
                            '                    <ul class="pagination">\n' +
                            '                        <li><button type="button" class="btn btn-success" name="delete">删除</button></li>\n' +
                            '                    </ul>\n' +
                            '                </div>     \n' +
                            '                       </td>\n' +
                            '                   </tr>');
                        $("#t-student tbody").append($node);
                        if(b==5){
                            b=0;
                        }
                    }
                }
            }
        }
    })
    // 下一页
    $("button[name='next']").click(function () {
        $("#t-student tbody").empty();
        var maxpage = Math.ceil(pageCount/9);
        if(pageNo<maxpage){
            ++pageNo;
        }else{
            pageNo=maxpage;
            layer.msg("已经是最后一页了",{time:1500});
        }
        getStudentByClassId(pageNo,pageSize);
    })
    // 上一页
    $("button[name='prev']").click(function () {
        $("#t-student tbody").empty();
        if(pageNo==1){
            layer.msg("已经是第一页了",{time:1500});
        }else{
            pageNo--;
        }
        getStudentByClassId(pageNo,pageSize);
    })
})
// 获取学生信息
function getStudentByClassId(pageNo,pageSize){
    $.ajax({
        url:'student/getStudentListByClassID',
        dataType:'json',
        type:'post',
        data:{"classId":classId,"pageNo":pageNo,"pageSize":pageSize},
        success:function (ret) {
            if($.isEmptyObject(ret)){
                console.log("获取我的学生信息失败")
            }else{
                for(var i = 0;i<ret.result.list.length;i++){
                        $node = $('  <tr class="'+classArr[b++]+'">\n' +
                            '                       <td>\n' +
                            '                           '+ret.result.list[i].studentName+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                            <img src="'+ret.result.list[i].studentPicture+'" width="65px">\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                          '+ret.result.list[i].studentSex+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                          '+ret.result.list[i].studentAge+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                          '+ret.result.list[i].studentProfession+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                          '+ret.result.list[i].studentSchool+'\n' +
                            '                       </td>\n' +
                            '                       <td>\n' +
                            '                      <div id="caozuo-student">\n' +
                            '                    <ul class="pagination">\n' +
                            '                        <li><button type="button" class="btn btn-success" name="delete">删除</button></li>\n' +
                            '                    </ul>\n' +
                            '                </div>     \n' +
                            '                       </td>\n' +
                            '                   </tr>');
                        $("#t-student tbody").append($node);
                        if(b==5){
                            b=0;
                        }
                }
            }
        }
    })
}



// //发布考试
// $("#begintest").click(function () {
//     $("#t-course").hide();
//     $("#t-score").hide();
//     $("#t-student").hide();
//     $("#t-test").show();
//     $("#t-notice").hide();
//     $("#t-listnotice").hide();
// })
// $("#add").click(function () {
//     location.href="addExams.html"
// })

})