jQuery(document).ready(function() {
    Page.init();
});

var date_flag = "";
var resultList = [];
var user = {};

// var baseURL = 'http://www.ylxteach.net/XM06';
// var baseUrl = 'http://localhost:9000/XM06';
var baseUrl = "/XM06";

var Page = function() {

    // 页面分流进行余下操作
    var initPageControl = function(){
        var pageId = $("#page_id").val();
        if(pageId === "weather"){
            initWeatherList();
        }
    };

    // 开始操作
    var initWeatherList = function(){
        initWeatherListControlEvent();
        getWeatherDatatable();
    }

    // 具体操作函数（button事件）
    var initWeatherListControlEvent = function (){
        $("#query_button").click(function (){onQueryRecord()});
        $("#submit_button").click(function () {QueryWeather()});
        $("#print_table").click(function () {printTable()});
        $("#output_table").click(function (){outputTable()} );
    }

    var printTable = function () {
        var bdHtml = $("#datatable_tab").html();
        var sprnStr = "<!--startprint-->";   //开始打印标识字符串有17个字符
        var eprnStr = "<!--endprint-->";        //结束打印标识字符串
        var prnHtml = bdHtml.substr(bdHtml.indexOf(sprnStr)+17); //从开始打印标识之后的内容
        prnHtml = prnHtml.substring(0,prnHtml.indexOf(eprnStr));//截取开始标识和结束标识之间的内容
        var iframe = null;
        iframe = document.getElementById("iframe1")

        var iWindow = iframe.contentWindow;//获取iframe的window对象
        iWindow.document.close();
        iWindow.document.write(prnHtml);
        iWindow.print(); //调用浏览器的打印功能打印指定区域
    }
    var outputTable = function () {
        var oTable = document.getElementById("datatable").innerHTML;
        var excelHtml = `
    <html>
    <head>
      <meta charset='utf-8' />
      <style  type="text/css">
        .tableA {
          border-collapse: collapse;
        }
        .tableA .title th{
          height: 50px;
          font-size: 24px;
          font-family: '微软雅黑';
          font-weight: 700;
        }
        .tableA tr th {
          border: 1px #000 solid;
          height: 40px;
          background: #efefef;
        }
        .tableA tr td {
          padding: 0 40px;
          border: 1px #000 solid;
          height: 40px;
          text-align: center;
        }
        .tableA .footer td {
          font-size: 20px;
          font-weight: 700;
        }
      </style>
    </head>
    <body>` + oTable + `</body>
    </html>
    `;
        // 创建 Blob 对象
        var excelBlob = new Blob([excelHtml], {
            type: "application/vnd.ms-excel"
        });
        var oA = document.getElementById("output_table");
        oA.href = URL.createObjectURL(excelBlob);
        oA.download = "weather.xls";

        oA.click = function () {
            this.click();
        };
    }

    var html_div = function (data) {
        var html = "";
        var item = 1;
        var weather = data.data.forecast;
        var city_div = data.data.city;
        var tips = data.data.ganmao;
        $.each(weather, function(i,e) {
            if (item === 1){
                html = html + "<tr class=\"active\">";
                item++;
            } else if (item === 2){
                html = html + "<tr class=\"success\">";
                item++;
            } else if (item === 3){
                html = html + "<tr class=\"warning\">";
                item++;
            } else {
                html = html + "<tr class=\"danger\">";
                item = 1;
            }
            html = html + "<td>";
            html = html + city_div;
            html = html + "</td>"
            html = html + "<td>";
            html = html + e.date;
            html = html + "</td>"
            html = html + "<td>";
            html = html + e.type;
            html = html + "</td>"
            html = html + "<td>"
            html = html + e.high.replace("高温", "");
            html = html + "</td>";
            html = html + "<td>"
            html = html + e.low.replace("低温", "");
            html = html + "</td>";
            html = html + "<td>"
            html = html + tips;
            html = html + "</td>";
            html = html + "</tr>";
        })
        $("#record_table_content_div").html(html);
    }

    var getWeatherDatatable = function () {
        let userIdStr = getUserIdStr();
        const uri =  encodeURIComponent (userIdStr)
        $.ajax({
            url: baseUrl+'/user/findByIdStr?userIdStr=' + uri,
            type: "get",
            success: function (json) {
                if (json.code === 200) {
                    user = json.result;
                    // console.log(user);
                    document.getElementById('user_name').innerText = user.userName;
                    // 获取列表
                    var city = $("#city").val();
                    city = city.replace("市", "");
                    city = city.replace("省", "");
                    city = city.replace("特别行政区", "");
                    city = city.replace("区", "");
                    if (city === "") {
                        $.ajax({
                            url: 'http://api.map.baidu.com/location/ip?ak=ia6HfFL660Bvh43exmH9LrI6',
                            type: 'POST',
                            dataType: 'jsonp',
                            success:function(data) {
                                city = data.content.address_detail.city;
                                city = city.replace("市", "");
                                city = city.replace("省", "");
                                city = city.replace("区", "");
                                city = city.replace("特别行政区", "");
                                $.ajax({
                                    type:'get',
                                    url:'http://wthrcdn.etouch.cn/weather_mini',
                                    data:{city:city},
                                    dataType:"jsonp",
                                    success:function(data) {
                                        if (city === "香港" || city === "香港特别行政区"){
                                            city = "hongkong";
                                        }
                                        $("#weather").html("<iframe width=\"280\" height=\"300\" frameborder=\"0\" scrolling=\"no\" hspace=\"0\" src=\"https://i.tianqi.com/?c=code&a=getcode&id=55&py=" + pinyin.getFullChars(city).toLowerCase() + "&icon=2\"></iframe>")
                                        html_div(data)
                                    }
                                })
                            }
                        });
                    } else {
                        $.ajax({
                            type: 'get',
                            url: 'http://wthrcdn.etouch.cn/weather_mini',
                            data: {city: city},
                            dataType: "jsonp",
                            success: function (data) {
                                if (city === "香港"){
                                    city = "hongkong";
                                }
                                $("#weather").html("<iframe width=\"280\" height=\"300\" frameborder=\"0\" scrolling=\"no\" hspace=\"0\" src=\"https://i.tianqi.com/?c=code&a=getcode&id=55&py=" + pinyin.getFullChars(city).toLowerCase() + "&icon=2\"></iframe>")
                                html_div(data)
                            }
                        })
                    }
                }
            }
        })
    }

    var onQueryRecord = function (){
        $("#query_weather_div").modal("show");
    }

    var QueryWeather = function () {
        // 获取查询结果
        $("#query_weather_div").modal("hide");
        getWeatherDatatable();
    }

    //Page return 开始
    return {
        init: function() {
            initPageControl();
        },
    }
}()

function getCookie(objName) {
    //获取指定名称的cookie的值
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if (temp[0] === objName) return unescape(temp[1]);
    }
}

function getUserIdStr(){
    var userIdStr;
    let localUserIdStr = getCookie('userIdStr')
    if(localUserIdStr != null){
        userIdStr = localUserIdStr
    }
    return userIdStr
}