jQuery(document).ready(function() {
    Page.init();
});

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
        var temperature = data.data.wendu + "℃";

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
            html = html + "<td>";
            html = html + temperature;
            html = html + "</td>"
            html = html + "<td>"
            html = html + e.high.replace("高温", "");
            html = html + "</td>";
            html = html + "<td>"
            html = html + e.low.replace("低温", "");
            html = html + "</td>";
            html = html + "<td>"
            html = html + e.fengxiang;
            html = html + "</td>";
            html = html + "<td>"
            html = html + tips;
            html = html + "</td>";
            html = html + "</tr>";
        })
        $("#record_table_content_div").html(html);
    }

    var getWeatherDatatable = function () {
        // 获取列表
        var city = $("#city").val();
        if (city === "") {
            $.ajax({
                url: 'http://api.map.baidu.com/location/ip?ak=ia6HfFL660Bvh43exmH9LrI6',
                type: 'POST',
                dataType: 'jsonp',
                success:function(data) {
                    city = data.content.address_detail.city;
                    city = city.replace("市", "");
                    $.ajax({
                        type:'get',
                        url:'http://wthrcdn.etouch.cn/weather_mini',
                        data:{city:city},
                        dataType:"jsonp",
                        success:function(data) {
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
                    html_div(data)
                }
            })
        }
    }

    var onQueryRecord = function (){
        // 获取查询结果
        getWeatherDatatable();
    }

    //Page return 开始
    return {
        init: function() {
            initPageControl();
        },
    }
}()