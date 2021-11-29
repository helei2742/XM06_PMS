jQuery(document).ready(function() {
    // initiate layout and plugins
    //Metronic.init(); // init metronic core components
    Layout.init(); // init current layout
    QuickSidebar.init(); // init quick sidebar
    Demo.init(); // init demo features
    Page.init();
});
var resultlist = [];
var Page = function() {

    // 页面分流进行余下操作
    var initPageControl = function(){
        pageId = $("#page_id").val();
        if(pageId === "conference_list"){
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
        data.conference_name = $("#record_query_setup #conference_name").val();
        data.conference_info = $("#record_query_setup #conference_info").val();
        data.conference_date = $("#record_query_setup #conference_date").val();
        data.hour_long = $("#record_query_setup #hour_long").val();
        data.address = $("#record_query_setup #address").val();
        data.type = 5;
        $.ajax({
            url: "/XM06/conference/pageQueryAllConference",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            success: function (json){
                // console.log(JSON.stringify(json));
                if(json.result_code === 0) {
                    var list = json.aaData;
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
                            html = html + record.conference_name;
                            html = html + "</td>“"
                            html = html + "<td>"
                            html = html + record.group_id;
                            html = html + "</td>";
                            html = html + "<td>"
                            html = html + record.conference_info;
                            html = html + "</td>";
                            html = html + "<td>"
                            html = html + record.conference_date.toDateString();
                            html = html + "</td>";
                            html = html + "<td>"
                            html = html + record.hour_long;
                            html = html + "</td>";
                            html = html + "<td>"
                            html = html + record.address;
                            html = html + "</td>";
                            html = html + "<td>"
                            html = html + record.creator_id;
                            html = html + "</td>";
                            html = html + "<td>"
                            html = html + record.create_date.toDateString();
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
        var data={};
        data.task_id=$("#record_add_div #task_id").val();
        data.task_name=$("#record_add_div #task_name").val();
        console.log(data)
        $.ajax({
            url: "/conference/add",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            success: function (json){
                if(json.result_code === 0){
                    alert("已经完成会议添加。");
                    window.location.reload();
                }
            },
            error: function (e){
                e.toString();
            }
        });
    };
    var onDeleteRecord = function(id){
        // 删除记录
        if(confirm("您确定要删除这条记录吗？")){
            if(id > -1){
                var data = {};
                data.id = id;
                $.ajax({
                    url: "/delete_task_record",
                    type: "POST",
                    data: JSON.stringify(data),
                    dataType: "JSON",
                    contentType: "application/json;charset=UTF-8",
                    success: function (json){
                        if(json.result_code === 0){
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
        onDeleteRecord:function(id){
            onDeleteRecord(id);
        },
        onModifyRecord:function(id){
            onModifyRecord(id);
        },
    }
}()







function demo() {
    const data = {};
    $("#demo").html("")
    data.id = $("#id").val();
    console.log(data)
    $.ajax({
        type:"POST",
        url:"/test",
        dataType:"json",
        data:JSON.stringify(data),
        contentType:"application/json",
        success:function (data){
            console.log(data)
            $("#demo").html(data.temp)
        },
        error:function (e){
            console.log(e)
        }
    })
}