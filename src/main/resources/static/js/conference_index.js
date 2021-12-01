jQuery(document).ready(function() {
    // initiate layout and plugins
    //Metronic.init(); // init metronic core components
    Layout.init(); // init current layout
    QuickSidebar.init(); // init quick sidebar
    Demo.init(); // init demo features
    Page.init();
});

var date_flage = "";
var resultlist = [];
var user = {};

// var baseURL = 'http://www.ylxteach.net/XM06';
// var baseUrl = 'http://localhost:9000/XM06';
var baseUrl = "/XM06";

var Page = function() {

    // 页面分流进行余下操作
    var initPageControl = function(){
        var pageId = $("#page_id").val();
        if(pageId === "conference_list"){
            // console.log(baseUrl)
            initConferenceList();
        }
    };

    // 开始操作
    var initConferenceList = function(){
        initConferenceListControlEvent();
        initConferenceRecordList();
    }

    // 具体操作函数（button事件）
    var initConferenceListControlEvent = function (){
        $('#add_button').click(function() {onAddRecord();});
        $('#record_modify_div_submit_button').click(function() {onModifyDivSubmit();});
        $('#record_add_div_submit_button').click(function() {onAddDivSubmit();});
        $("#query_button").click(function (){onQueryRecord()});
        $("#before_date_button").click(function (){queryBeforeConference()});
        $("#after_date_button").click(function (){queryAfterConference()});
        $("#out_login").click(function () {outLogin()});
        $("#print_table").click(function () {printTable()});
        $("#output_table").click(function (){outputTable()} );
    }

    // 具体操作函数（其余部分）
    var initConferenceRecordList = function(){
        getConferenceRecordDatatable();
    }

    // 业务函数
    var queryBeforeConference = function () {
        date_flage = "before";
        getConferenceRecordDatatable();
    }
    var queryAfterConference = function () {
        date_flage = "after";
        getConferenceRecordDatatable();
    }
    var outLogin = function () {
        if(confirm("您确定要退出登录吗？")){
            delCookie("userIdStr");
            window.location.href = "http://localhost:8080/#/index/login";
        }

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
        oA.download = "datatable.xls";

        oA.click = function () {
            this.click();
        };
    }
    var loadingIndex = function () {
        window.location.href = "http://localhost:8080/#/index/welcome";
    }


    var getConferenceRecordDatatable = function () {
        // 获取列表
        // console.log(user.id);
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
                    var data = {};
                    if (date_flage === "before"){
                        data.type = 2;
                        date_flage = "";
                    } else if (date_flage === "after"){
                        data.type = 3;
                        date_flage = "";
                    } else {
                        data.conferenceName = $("#record_query_setup #conference_name").val();
                        data.creatorId = $("#record_query_setup #creator_id").val();
                        data.groupId = $("#record_query_setup #group_id").val();
                        // console.log(data)
                        if (data.conferenceName === "" && data.creatorId === "" && data.groupId === ""){
                            // 查找全部会议
                            data.type = 10000;
                        }
                        if (data.conferenceName === "" && data.creatorId !== "" && data.groupId === ""){
                            // 查找创建人创建的会议
                            data.type = 1;
                        }
                        if (data.conferenceName === "" && data.creatorId === "" && data.groupId !== "" ){
                            // 查找小组会议
                            data.type = 4;
                        }
                        if (data.conferenceName !== "" && data.creatorId === "" && data.groupId === "") {
                            // 查找会议名字关键字
                            data.type = 5;
                        }
                        if (data.conferenceName !== "" && data.creatorId !== "" && data.groupId === "") {
                            // 查找会议名字关键字和创建人id
                            data.type = 6;
                        }
                        if (data.conferenceName !== "" && data.creatorId === "" && data.groupId !== "") {
                            // 查找会议名字关键字和小组ID
                            data.type = 7;
                        }
                        if (data.conferenceName !== "" && data.creatorId !== "" && data.groupId !== "") {
                            // 全条件查找会议
                            data.type = 8;
                        }
                        if (data.conferenceName === "" && data.creatorId !== "" && data.groupId !== "") {
                            // 按照小组ID和创建人ID查找会议
                            data.type = 9;
                        }
                    }
                    $.ajax({
                        url: baseUrl + "/conference/pageQueryAllConference",
                        type: "POST",
                        data: JSON.stringify(data),
                        dataType: "JSON",
                        contentType: "application/json;charset=UTF-8",
                        success: function (json){
                            if(json.code === 200) {

                                var list = json.result.list;
                                var html = "";
                                var item = 1;
                                if (list !== undefined && list.length > 0) {
                                    for (var i = 0; i < list.length; i++) {
                                        var record = list[i];
                                        resultlist.push(record);
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
                                        html = html + record.conferenceName;
                                        html = html + "</td>“"
                                        html = html + "<td>"
                                        html = html + record.groupId;
                                        html = html + "</td>";
                                        html = html + "<td>"
                                        html = html + record.conferenceInfo;
                                        html = html + "</td>";
                                        html = html + "<td>"
                                        html = html + new Date(record.conferenceDate);
                                        html = html + "</td>";
                                        html = html + "<td>"
                                        html = html + record.hourLong;
                                        html = html + "</td>";
                                        html = html + "<td>"
                                        html = html + record.address;
                                        html = html + "</td>";
                                        html = html + "<td>"
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
        data.conferenceName = $("#record_add_div #conference_name").val();
        data.conferenceInfo = $("#record_add_div #conference_info").val();
        // console.log(($("#record_add_div #conference_date").val()).type);
        data.conferenceDate = new Date($("#record_add_div #conference_date").val()).getTime();
        data.hourLong = $("#record_add_div #hour_long").val();
        data.address = $("#record_add_div #address").val();
        data.creatorId = user.id;
        data.groupId = $("#record_add_div #group_id").val();

        $.ajax({
            url: baseUrl + "/conference/add",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            success: function (json){
                if(json.code === 200){
                    // console.log(data)
                    alert("已经完成会议添加。");
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
        if(confirm("您确定要删除这条会议记录吗？")){
            if(id > -1){
                var data = {};
                data.id = id;
                data.userId = user.id;
                // console.log(data);
                $.ajax({
                    url: baseUrl + "/conference/delete",
                    type: "POST",
                    data: JSON.stringify(data),
                    dataType: "JSON",
                    contentType: "application/json;charset=UTF-8",
                    success: function (json){
                        if(json.code === 200){
                            alert("删除会议成功！");
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
        for (var i = 0; i < resultlist.length; i++){
            if(id === parseInt(resultlist[i].id)){
                // 读取到的result[i].id为string类型，要转化为number类型才能比较
                $("#record_modify_div #id").val(resultlist[i].id);
                $("#record_modify_div #conference_name").val(resultlist[i].conferenceName);
                $("#record_modify_div #conference_info").val(resultlist[i].conferenceInfo);
                var d = new Date(resultlist[i].conferenceDate);
                var dateString  = d.getFullYear() + "-" +  ("0"+(d.getMonth()+1)).slice(-2)  + "-"  + ("0" + d.getDate()).slice(-2)
                    + "T" + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);
                $("#record_modify_div #conference_date").val(dateString);
                $("#record_modify_div #hour_long").val(resultlist[i].hourLong);
                $("#record_modify_div #address").val(resultlist[i].address);
                $("#record_modify_div #creator_id").val(resultlist[i].creatorId);
                $("#record_modify_div #group_id").val(resultlist[i].groupId);
                $("#record_modify_div").modal("show");
                break;
            }
        }

    };
    var submitModifyRecordDiv = function (){
        if(confirm("您确定要修改该记录吗？")){
            var data = {};
            data.id = $("#record_modify_div #id").val();
            data.conferenceName = $("#record_modify_div #conference_name").val();
            data.conferenceInfo = $("#record_modify_div #conference_info").val();
            data.conferenceDate = new Date($("#record_modify_div #conference_date").val()).getTime();
            data.hourLong = $("#record_modify_div #hour_long").val();
            data.address = $("#record_modify_div #address").val();
            data.userId = user.id;
            data.creatorId = $("#record_modify_div #creator_id").val();
            data.groupId = $("#record_modify_div #group_id").val();
            // console.log(data)
            $.ajax({
                url: baseUrl + "/conference/modify",
                type: "POST",
                data: JSON.stringify(data),
                dataType: "JSON",
                contentType: "application/json;charset=UTF-8",
                success: function (json){
                    if(json.code === 200){
                        // console.log(111111111111111111)
                        alert("已经完成任务信息修改！");
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
        initConferenceList();
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
        // console.log(111111111111111)
        $("#record_modify_div").modal("hide");
        submitModifyRecordDiv()
    };

    // cookie相关
    function getUserIdStr(){
        var userIdStr;
        let localUserIdStr = getCookie('userIdStr')
        if(localUserIdStr != null){
            userIdStr = localUserIdStr
        }
        // console.log(userIdStr)
        return userIdStr
    }

    function getCookie(objName) {//获取指定名称的cookie的值
        var arrStr = document.cookie.split("; ");
        for (var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            if (temp[0] === objName) return unescape(temp[1]);
        }

    }

    function delCookie (name) {
        var exp=new Date();
        console.log('cookie')
        exp.setTime(exp.getTime()-10000);
        var cval = getCookie(name)
        if (cval != null) {
            document.cookie = name + '=' + cval + ';expires=' + exp.toUTCString()+";path=/";
        }
    }

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