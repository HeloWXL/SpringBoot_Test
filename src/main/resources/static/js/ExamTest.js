$(function(){
    // 选择题显示和隐藏
    $("#select").click(function () {
        if($("#question-1").css("display")=="none"){
            $("#question-1").show();
        }else {
            $("#question-1").hide();
        }
    })
    // 填空题显示和隐藏
    $("#blank").click(function () {
        if($("#question-2").css("display")=="none"){
            $("#question-2").show();
        }else {
            $("#question-2").hide();
        }
    })
// ------------------------------选择题---------------------------------------
    $('#question-1').empty();
    $.ajax({
        url:'select/getAllChoices',
        dataType:'json',
        type:'get',
        success:function (ret) {
            var a = 1;
            for(var i = 0; i<ret.result.length;i++){
                $node =   $('<div class="selects">\n' +
                    '                        <p><span>'+a+'</span>.'+ret.result[i].selectQuestion+'</p>\n' +
                    '                        <div class="col-sm-10" id="wi">\n' +
                    '                            <label class="radio-inline">\n' +
                    '                                <input type="radio" value="A" name="'+i+'">A</label>.&nbsp'+ret.result[i].selectA+'<br>\n' +
                    '                            <label class="radio-inline">\n' +
                    '                                <input type="radio" name="'+i+'" value="B" >B</label>.&nbsp'+ret.result[i].selectB+'<br>\n' +
                    '                            <label class="radio-inline">\n' +
                    '                                <input type="radio" value="C" name="'+i+'">C</label>.&nbsp'+ret.result[i].selectC+'<br>\n' +
                    '                            <label class="radio-inline">\n' +
                    '                                <input type="radio" name="'+i+'" value="D">.&nbsp'+ret.result[i].selectD+'</label>\n' +
                    '                        </div>\n' +
                    '                    </div>')
                $('#question-1').append($node)
                a++;
            }
        }
    })
// -----------------------------------------获取填空题列表--------------------------------
    $('#question-2').empty();
    $.ajax({
        url:'blank/getBlankByList',
        dataType:'json',
        type:'get',
        success:function (ret) {
            console.log(ret)
            var a = 1;
            for(var i = 0; i<ret.result.length;i++){
                $node = $(' <div class="blanks">\n' +
                    '                            <div class="col-sm-7" class="select">\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <p><span>'+a+'</span>.&nbsp'+ret.result[i].blankQuestion+'</p>\n' +
                    '                                    <div class="col-sm-8">\n' +
                    '                                        <span>你的答案：</span><input type="text" class="form-control">\n' +
                    '                                    </div>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>')
                $('#question-2').append($node)
                a++;
            }
        }

    })
// -----------------------------------倒计时------------------------------
    $(".timeBar").each(function () {
        $(this).countdownsync({
            dayTag: "",
            hourTag: "<label class='tt hh dib vam'>00</label><span>时</span>",
            minTag: "<label class='tt mm dib vam'>00</label><span>分</span>",
            secTag: "<label class='tt ss dib vam'>00</label><span>秒</span>",
            dayClass: ".dd",
            hourClass: ".hh",
            minClass: ".mm",
            secClass: ".ss",
            isDefault: false,
            showTemp:1

        }, function () {
            location.reload();
        });
    });

    $("button[name='submit']").click(function () {

    })

});