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
        if(pageId === "device_list"){
            // console.log(baseUrl)
            initDeviceList();
        }
    };

    // 开始操作
    var initDeviceList = function(){
        initDeviceListControlEvent();
        initDeviceRecordList();
    }

    // 具体操作函数（button事件）
    var initDeviceListControlEvent = function (){
        $('#add_button').click(function() {onAddRecord();});
        $('#record_modify_div_submit_button').click(function() {onModifyDivSubmit();});
        $('#record_add_div_submit_button').click(function() {onAddDivSubmit();});
        $("#query_button").click(function (){onQueryRecord()});
        $("#desc_button").click(function (){queryByDESC()});
        $("#asc_button").click(function (){queryByASC()});
        $("#print_table").click(function () {printTable()});
        $("#output_table").click(function (){outputTable()} );
    }

    // 具体操作函数（其余部分）
    var initDeviceRecordList = function(){
        getDeviceRecordDatatable();
    }

    // 业务函数
    var queryByDESC = function () {
        date_flag = "DESC";
        getDeviceRecordDatatable();
    }
    var queryByASC = function () {
        date_flag = "ASC";
        getDeviceRecordDatatable();
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
        oA.download = "device_list.xls";

        oA.click = function () {
            this.click();
        };
    }
    var loadingIndex = function () {
        window.location.href = baseUrl + "/#/index/welcome";
    }


    var getDeviceRecordDatatable = function () {
        // 获取列表
        let userIdStr = getUserIdStr();
        const uri =  encodeURIComponent (userIdStr)
        $.ajax({
            url: baseUrl+'/user/findByIdStr?userIdStr=' + uri,
            type: "get",
            success: function (json) {
                if (json.code === 200) {
                    console.log(json)
                    user = json.result;
                    // console.log(user);
                    document.getElementById('user_name').innerText = user.userName;
                    var data = {};
                    data.deviceId = $("#record_query_setup #device_id").val();
                    data.deviceName = $("#record_query_setup #device_name").val();
                    data.creatorId = $("#record_query_setup #creator_id").val();
                    data.groupId = $("#record_query_setup #group_id").val();
                    // console.log(data)
                    if (data.deviceId === "" && data.deviceName === "" && data.creatorId === "" && data.groupId === ""){
                        // 查找全部设备
                        data.type = 10000;
                    }
                    if (data.deviceId !== "" && data.deviceName === "" && data.creatorId === "" && data.groupId === ""){
                        // 按照设备ID查询设备
                        data.type = 1;
                    }
                    if (data.deviceId === "" && data.deviceName !=="" &&data.creatorId === "" && data.groupId === "" ){
                        // 按照设备名称查询设备
                        data.type = 2;
                    }
                    if (data.deviceId === "" && data.deviceName ==="" &&data.creatorId !== "" && data.groupId === "" ){
                        // 按照创建人查询设备
                        data.type = 3;
                    }
                    if (data.deviceId === "" && data.deviceName ==="" &&data.creatorId === "" && data.groupId !== "" ){
                        // 按照小组查询设备
                        data.type = 4;
                    }
                    if (data.deviceId !== "" && data.deviceName !=="" &&data.creatorId === "" && data.groupId === "" ){
                        // 按照设备号和设备名字查询设备
                        data.type = 5;
                    }
                    if (data.deviceId !== "" && data.deviceName ==="" &&data.creatorId !== "" && data.groupId === "" ){
                        // 按照设备号和创建人查询设备
                        data.type = 6;
                    }
                    if (data.deviceId !== "" && data.deviceName ==="" &&data.creatorId === "" && data.groupId !== "" ){
                        // 按照设备号和小组查询设备
                        data.type = 7;
                    }
                    if (data.deviceId === "" && data.deviceName !=="" &&data.creatorId !== "" && data.groupId === "" ){
                        // 按照设备名称和创建人查询设备
                        data.type = 8;
                    }
                    if (data.deviceId === "" && data.deviceName !=="" &&data.creatorId === "" && data.groupId !== "" ){
                        // 按照设备名称和小组查询设备
                        data.type = 9;
                    }
                    if (data.deviceId === "" && data.deviceName ==="" &&data.creatorId !== "" && data.groupId !== "" ){
                        // 按照创建人和小组查询设备
                        data.type = 10;
                    }
                    if (data.deviceId !== "" && data.deviceName !=="" &&data.creatorId !== "" && data.groupId === "" ){
                        // 按照设备号、设备名字和创建人查询设备
                        data.type = 11;
                    }
                    if (data.deviceId !== "" && data.deviceName !=="" &&data.creatorId === "" && data.groupId !== "" ){
                        // 按照设备号、设备名字和小组查询设备
                        data.type = 12;
                    }
                    if (data.deviceId !== "" && data.deviceName ==="" &&data.creatorId !== "" && data.groupId !== "" ){
                        // 按照设备号、创建人和小组查询设备
                        data.type = 13;
                    }
                    if (data.deviceId === "" && data.deviceName !=="" &&data.creatorId !== "" && data.groupId !== "" ){
                        // 按照设备名称、创建人和小组查询设备
                        data.type = 14;
                    }
                    if (data.deviceId !== "" && data.deviceName !=="" &&data.creatorId !== "" && data.groupId !== "" ){
                        // 按照设备编号、设备名称、创建人和小组查询设备
                        data.type = 15;
                    }
                    $.ajax({
                        url: baseUrl + "/device/pageQueryAllDevice",
                        type: "POST",
                        data: JSON.stringify(data),
                        dataType: "JSON",
                        contentType: "application/json;charset=UTF-8",
                        success: function (json){
                            if(json.code === 200) {
                                var list = json.result;
                                var html = "";
                                var item = 1;
                                var record = null;
                                var i = 0;
                                if (date_flag === "DESC" || date_flag === "") {
                                    date_flag = "";
                                    if (list !== undefined && list.length > 0) {
                                        for (i = 0; i < list.length; i++) {
                                            record = list[i];
                                            resultList.push(record);
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
                                            html = html + record.deviceId;
                                            html = html + "</td>";
                                            html = html + "<td>"
                                            html = html + record.deviceName;
                                            html = html + "</td>";
                                            html = html + "<td>"
                                            html = html + record.groupId;
                                            html = html + "</td>";
                                            html = html + "<td>";
                                            html = html + record.creatorId;
                                            html = html + "</td>";
                                            html = html + "<td>"
                                            html = html + new Date(record.createDate);
                                            html = html + "</td>";
                                            html = html + "<td>";
                                            html = html + "<div><a href=\"javascript:Page.onModifyRecord(" + record.id + ")\">【修改记录】</a><a href=\"javascript:Page.onDeleteRecord(" + record.id + ")\">【删除记录】</a><div>";
                                            html = html + "</td>";
                                            html = html + "</tr>";
                                        }
                                    }
                                } else {
                                    date_flag = "";

                                    if (list !== undefined && list.length > 0) {
                                        for (i = list.length - 1; i >= 0; i--) {
                                            record = list[i];
                                            resultList.push(record);
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
                                            html = html + record.deviceId;
                                            html = html + "</td>";
                                            html = html + "<td>"
                                            html = html + record.deviceName;
                                            html = html + "</td>";
                                            html = html + "<td>"
                                            html = html + record.groupId;
                                            html = html + "</td>";
                                            html = html + "<td>";
                                            html = html + record.creatorId;
                                            html = html + "</td>";
                                            html = html + "<td>"
                                            html = html + new Date(record.createDate);
                                            html = html + "</td>";
                                            html = html + "<td>";
                                            html = html + "<div><a href=\"javascript:Page.onModifyRecord(" + record.id + ")\">【修改记录】</a><a href=\"javascript:Page.onDeleteRecord(" + record.id + ")\">【删除记录】</a><div>";
                                            html = html + "</td>";
                                            html = html + "</tr>";
                                        }
                                    }
                                }
                                $("#record_table_content_div").html(html);

                            }
                            else {
                                json.msg = decodeURI(json.msg);
                                alert(json.msg);
                            }
                        },
                        error:function (e){
                            console.log(e.toString())
                        }
                    })
                } else {
                    if (user.id === undefined){
                        alert("您尚未登录，请返回主页并登录！");
                    }
                }
            }
        })
    };
    var submitAddRecordDiv = function(){
        // 添加记录
        var data = {};
        data.deviceId = $("#record_add_div #device_id").val();
        data.deviceName = $("#record_add_div #device_name").val();
        data.creatorId = user.id;
        data.groupId = $("#record_add_div #group_id").val();

        $.ajax({
            url: baseUrl + "/device/add",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            success: function (json){
                if(json.code === 200){
                    // console.log(data)
                    alert("已经完成设备添加。");
                    window.location.reload();
                } else {
                    json.msg = decodeURI(json.msg);
                    alert(json.msg);
                }
            },
            error: function (e){
                e.toString();
            }
        });
    };
    var onDeleteRecord = function(id, groupId, creatorId){
        // 删除记录
        if(confirm("您确定要删除这条设备记录吗？")){
            if(id > -1){
                var data = {};
                data.id = id;
                data.userId = user.id;
                $.ajax({
                    url: baseUrl + "/device/delete",
                    type: "POST",
                    data: JSON.stringify(data),
                    dataType: "JSON",
                    contentType: "application/json;charset=UTF-8",
                    success: function (json){
                        if(json.code === 200){
                            alert("删除设备成功！");
                            window.location.reload();
                        } else {
                            json.msg = decodeURI(json.msg);
                            alert(json.msg);
                        }
                    },
                    error: function (e){
                        e.toString();
                    }
                })
            }
        }
    };

    var onModifyRecord = function(id){
        // console.log(resultlist)
        for (var i = 0; i < resultList.length; i++){
            if(id === parseInt(resultList[i].id)){
                // 读取到的result[i].id为string类型，要转化为number类型才能比较
                $("#record_modify_div #id").val(resultList[i].id);
                $("#record_modify_div #device_id").val(resultList[i].deviceId);
                $("#record_modify_div #device_name").val(resultList[i].deviceName);
                $("#record_modify_div #creator_id").val(resultList[i].creatorId);
                $("#record_modify_div #group_id").val(resultList[i].groupId);
                $("#record_modify_div").modal("show");
                break;
            }
        }

    };
    var submitModifyRecordDiv = function (){
        if(confirm("您确定要修改该记录吗？")){
            var data = {};
            data.id = $("#record_modify_div #id").val();
            data.deviceId = $("#record_modify_div #device_id").val();
            data.deviceName = $("#record_modify_div #device_name").val();
            data.userId = user.id;
            data.creatorId = $("#record_modify_div #creator_id").val();
            data.groupId = $("#record_modify_div #group_id").val();
            // console.log(data)
            $.ajax({
                url: baseUrl + "/device/modify",
                type: "POST",
                data: JSON.stringify(data),
                dataType: "JSON",
                contentType: "application/json;charset=UTF-8",
                success: function (json){
                    if(json.code === 200){
                        // console.log(111111111111111111)
                        alert("已经完成设备信息修改！");
                        window.location.reload();
                    } else {
                        json.msg = decodeURI(json.msg);
                        alert(json.msg);
                    }
                },
                error: function (e){
                    e.toString();
                }
            });
        }
    }

    var onQueryRecord = function (){
        // 获取查询结果
        getDeviceRecordDatatable();
    }

    var onAddRecord = function(){
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
                    // 展开添加界面
                    if (user.id !== undefined){
                        $("#record_add_div").modal("show");
                    } else {
                        alert("您尚未登录，请返回主页并登录！");
                    }
                } else {
                    if (user.id === undefined){
                        alert("您尚未登录，请返回主页并登录！");
                    }
                }
            }
        })
    }

    var onAddDivSubmit = function (){
        // 提交结束
        $("#record_add_div").modal("hide");
        submitAddRecordDiv()
    };

    var onModifyDivSubmit=function (){
        // 提交结束
        $("#record_modify_div").modal("hide");
        submitModifyRecordDiv()
    };


    //Page return 开始
    return {
        init: function() {
            initPageControl();
        },
        onDeleteRecord:function(id, groupId, creatorId){
            onDeleteRecord(id, groupId, creatorId);
        },
        onModifyRecord:function(id){
            onModifyRecord(id);
        },
    }
}()




function getCookie(objName) {//获取指定名称的cookie的值
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
    // console.log(userIdStr)
    return userIdStr
}