jQuery(document).ready(function() {
    // initiate layout and plugins
    //Metronic.init(); // init metronic core components
    Layout.init(); // init current layout
    QuickSidebar.init(); // init quick sidebar
    Demo.init(); // init demo features
    Page.init();
});

var resultlist = [];
// var baseURL = 'http://www.ylxteach.net/XM06';
var baseUrl = 'http://localhost:9000/XM06';

var Page = function() {

    // 页面分流进行余下操作
    var initPageControl = function(){
        var pageId = $("#page_id").val();
        if(pageId === "conference_list"){
            console.log(baseUrl)
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
    }

    // 具体操作函数（其余部分）
    var initConferenceRecordList = function(){
        getConferenceRecordDatatable();
    }

    // 业务函数
    var getConferenceRecordDatatable = function () {
        // 获取列表
        var data = {};
        data.conferenceName = $("#record_query_setup #conference_name").val();
        data.conferenceInfo = $("#record_query_setup #conference_info").val();
        data.conferencedate = $("#record_query_setup #conference_date").val();
        data.hourLong = $("#record_query_setup #hour_long").val();
        data.address = $("#record_query_setup #address").val();
        data.creatorId = $("#record_query_setup #creator_id").val();
        data.groupId = $("#record_query_setup #group_id").val();
        // console.log(data)
        if (data.conferenceName === "" && data.creatorId === "" && data.groupId === ""){
            data.type = 5;
        }
        if (data.creatorId !== ""){
            data.type = 1;
        }
        if (data.groupId !== ""){
            data.type = 4;
        }
        // console.log(data)
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
                            html = html + i;
                            html = html + "</td>";
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
            },
            error:function (e){
                console.log(e.toString())
            }
        })
    };
    var submitAddRecordDiv = function(){
        // 添加记录
        var data = {};
        data.conferenceName = $("#record_add_div #conference_name").val();
        data.conferenceInfo = $("#record_add_div #conference_info").val();
        data.conferenceDate = new Date($("#record_add_div #conference_date").val()).getTime();
        data.hourLong = $("#record_add_div #hour_long").val();
        data.address = $("#record_add_div #address").val();
        data.creatorId = $("#record_add_div #creator_id").val();
        data.groupId = $("#record_add_div #group_id").val();

        $.ajax({
            url: baseUrl + "/conference/add",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            success: function (json){
                if(json.code === 200){
                    console.log(data)
                    alert("已经完成会议添加。");
                    window.location.reload();
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
                data.groupId = 16;
                data.creatorId = 7;
                // console.log(data);
                $.ajax({
                    url: baseUrl + "/conference/delete",
                    type: "POST",
                    data: JSON.stringify(data),
                    dataType: "JSON",
                    contentType: "application/json;charset=UTF-8",
                    success: function (json){
                        if(json.code === 200){
                            window.location.reload();
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
        console.log(resultlist)
        for (var i = 0; i < resultlist.length; i++){
            if(id === parseInt(resultlist[i].id)){
                // 读取到的result[i].id为string类型，要转化为number类型才能比较
                $("#record_modify_div #id").val(resultlist[i].id);
                $("#record_modify_div #task_id").val(resultlist[i].task_id);
                $("#record_modify_div #task_name").val(resultlist[i].task_name);
                $("#record_modify_div").modal("show");
                break;
            }
        }

    };
    var submitModifyRecordDiv = function (){
        if(confirm("您确定要修改该记录吗？")){
            var data={};
            data.id=$("#record_modify_div #id").val();;
            data.task_id=$("#record_modify_div #task_id").val();
            data.task_name=$("#record_modify_div #task_name").val();
            $.ajax({
                url: "/modify_task_record",
                type: "POST",
                data: JSON.stringify(data),
                dataType: "JSON",
                contentType: "application/json;charset=UTF-8",
                success: function (json){
                    if(json.result_code === 0){
                        alert("已经完成任务信息修改。");
                        window.location.reload();
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
        initTaskRecordList();
    }
    var onAddRecord = function(){
        // 展开添加界面
        $("#record_add_div").modal("show");
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


function getUserIdStr(state){
    let localUserIdStr = getCookie('userIdStr')
    if(state.userIdStr == null && localUserIdStr != null){
        state.userIdStr = localUserIdStr
    } else if(localUserIdStr == null && state.userIdStr != null){
        addCookie('userIdStr', state.userIdStr, 24*7)
    }
    return state.userIdStr
}

function getCookie(objName) {//获取指定名称的cookie的值
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if (temp[0] == objName) return unescape(temp[1]);
    }

}
function addCookie(objName, objValue, objHours) {      //添加cookie
    var str = objName + "=" + escape(objValue);
    if (objHours > 0) {                               //为时不设定过期时间，浏览器关闭时cookie自动消失
        var date = new Date();
        var ms = objHours * 3600 * 1000;
        date.setTime(date.getTime() + ms);
        str += "; expires=" + date.toGMTString();
    }
    document.cookie = str;
}