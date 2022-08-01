(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4e5cacbb"],{"102c":function(t,e,n){},"1a21":function(t,e,n){"use strict";n("d84b")},2721:function(t,e,n){"use strict";n.r(e);var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"submit-record"},[n("show-window",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],key:"submitRecord",attrs:{"element-loading-text":"查询提交记录中","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)"}},[n("div",{attrs:{slot:"title"},slot:"title"},[n("i",{staticClass:"el-icon-s-order"}),n("span",[t._v("任务管理")]),n("i",{staticClass:"el-icon-arrow-right"}),n("span",[t._v("任务提交记录")]),n("i",{staticClass:"el-icon-arrow-right"}),n("span",[t._v(t._s(t.taskName))])]),n("div",{attrs:{slot:"main"},slot:"main"},[n("div",{staticStyle:{"text-align":"right","margin-right":"50px"}},[n("el-dropdown",[n("span",{staticClass:"el-dropdown-link"},[n("i",{staticClass:"el-icon-more-outline el-icon--right",staticStyle:{"font-size":"30px",cursor:"pointer"}})]),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[n("el-dropdown-item",[n("el-button",{attrs:{size:"mini",icon:"el-icon-s-fold"},on:{click:t.exportTaskSubmitRecord}},[t._v(" 导出excel文件 ")])],1)],1)],1)],1),n("submit-record-table",{attrs:{"record-list":t.recordList}}),n("div",{staticStyle:{"text-align":"center"}},[n("el-pagination",{attrs:{small:t.isUseSmall,"pager-count":5,"current-page":t.currentPage,"page-size":t.limit,layout:"total,prev, pager, next, jumper",total:t.total},on:{"current-change":t.handleCurrentChange,"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e}}})],1)],1)])],1)},r=[],i=(n("d3b7"),n("5deb")),a=n("3992"),s=n("bd9c"),l=n("4397"),c={name:"SubmitRecord",components:{ShowWindow:i["a"],SubmitRecordTable:a["a"]},data:function(){return{taskName:"",loading:!1,recordList:[],currentPage:1,limit:15,total:0,taskId:-1}},computed:{isUseSmall:function(){return this.$store.getters.getScreenSize.width<765}},methods:{handleCurrentChange:function(t){this.queryTaskRecordOfUser(t)},queryTaskRecord:function(t,e){var n=this;this.loading=!0,Object(s["j"])(t,e,this.limit).then((function(t){if(200===t.code){var e=t.result;n.recordList=e.list,n.total=e.total}else n.$message.error("出错了，"+t.msg)})).finally((function(){n.loading=!1}))},exportTaskSubmitRecord:function(){Object(s["g"])(this.taskId).then((function(t){Object(l["a"])(t)}))}},activated:function(){var t=this.$route.query.taskName;this.taskId=this.$route.query.taskId,t!==this.taskName&&this.queryTaskRecord(this.taskId,1)}},d=c,u=n("2877"),p=Object(u["a"])(d,o,r,!1,null,"0590f6b7",null);e["default"]=p.exports},3992:function(t,e,n){"use strict";var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return null!=this.$store.getters.getLoginUser?n("div",{staticClass:"submit-record-table"},[t.recordList!==[]?n("el-table",{staticStyle:{width:"100%","min-width":"830px"},attrs:{data:t.recordList,"empty-text":"暂无提交记录","row-style":this.$store.getters.getCardColorStyle,"header-cell-style":this.$store.getters.getCardColorStyle},on:{"selection-change":t.handleSelectionChange}},[n("el-table-column",{attrs:{type:"selection",selectable:t.handleSelectable,width:"55"}}),n("el-table-column",{attrs:{type:"index",width:"50"}}),n("el-table-column",{attrs:{prop:"user.userName",label:"提交者用户名",width:"180"}}),n("el-table-column",{attrs:{prop:"user.trueName",label:"提交者姓名",width:"100"}}),n("el-table-column",{attrs:{sortable:"",prop:"submitDate",formatter:t.submitDate,label:"提交日期"}}),n("el-table-column",{attrs:{width:"140"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-button",{attrs:{size:"mini",type:"info",icon:"el-icon-info"},on:{click:function(n){return t.taskDetail(e.row)}}},[t._v(" 查看任务详情 ")])]}}],null,!1,1249199784)}),n("el-table-column",{attrs:{width:"140"},scopedSlots:t._u([{key:"header",fn:function(e){return[t.selected.length>0?n("el-popconfirm",{attrs:{title:"确定删除选中记录吗？"},on:{confirm:t.handleDeleteAll}},[n("el-button",{attrs:{slot:"reference",type:"warning",size:"mini"},slot:"reference"},[t._v(" 删除选中"+t._s(t.selected.length)+"条 ")])],1):t._e()]}},{key:"default",fn:function(e){return[n("el-dropdown",{attrs:{size:"mini","split-button":"",type:"primary"}},[t._v(" 更多操作 "),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[n("el-dropdown-item",[n("el-button",{attrs:{size:"mini",icon:"el-icon-zoom-in"},on:{click:function(n){return t.lookDesc(e.$index,e.row)}}},[t._v(" 查看提交内容 ")])],1),n("el-dropdown-item",[n("el-button",{attrs:{size:"mini",type:"success",icon:"el-icon-download"},on:{click:function(n){return t.downloadFile(e.$index,e.row)}}},[t._v(" 下载提交文件 ")])],1),t.isMyRecord(e.row.userId)?n("el-dropdown-item",[n("el-button",{attrs:{size:"mini",type:"danger",icon:"el-icon-delete"},on:{click:function(n){return t.deleteRecord(e.$index,e.row)}}},[t._v(" 删除提交记录 ")])],1):t._e()],1)],1)]}}],null,!1,1936801717)})],1):t._e(),n("el-dialog",{attrs:{title:"提交内容",visible:t.dialogVisible},on:{"update:visible":function(e){t.dialogVisible=e}}},[null!=t.currentRecord?n("el-input",{attrs:{type:"textarea",value:t.currentRecord.description,placeholder:"无提交内容",resize:"none",autosize:"",readonly:!0}}):t._e()],1)],1):t._e()},r=[],i=n("b85c"),a=n("b114"),s=n("4397"),l=n("bd9c"),c={name:"SubmitRecordTable",props:{recordList:{type:Array,default:null}},data:function(){return{dialogVisible:!1,currentRecord:null,selected:[]}},computed:{isMyRecord:function(){var t=this;return function(e){return e===t.$store.getters.getLoginUser.id}}},methods:{submitDate:function(t,e){var n=t.submitDate;return null==n?"无记录":this.$formatDate(n)},handleSelectable:function(t){return t.userId===this.$store.getters.getLoginUser.id},handleDeleteAll:function(){var t,e=this,n=[],o=Object(i["a"])(this.selected);try{for(o.s();!(t=o.n()).done;){var r=t.value;n.push(r.id)}}catch(s){o.e(s)}finally{o.f()}var a=this.$store.getters.getLoginUser.id;console.log(n),Object(l["c"])(n,a).then((function(t){200===t.code?e.$message.success("删除记录成功"):e.$alert("删除失败")}))},handleSelectionChange:function(t){this.selected=t},taskDetail:function(t){this.$router.push({path:"/index/taskdetail",query:{task:{},taskId:t.taskId}})},lookDesc:function(t,e){this.currentRecord=e,this.dialogVisible=!0},downloadFile:function(t,e){var n=this,o=e.fileUrl;null!==o&&""!==o?Object(a["b"])(o).then((function(t){console.log(t),Object(s["a"])(t)})).catch((function(t){n.$alert("出错了，该上传文件已不存在")})):this.$message.error("该记录没有上传文件")},deleteRecord:function(t,e){var n=this,o=this.$store.getters.getLoginUser.id;console.log(e),this.$confirm("此操作将永久删除该提交记录和提交的文件, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(l["d"])(e.id,o).then((function(t){200===t.code?n.$message.success("删除提交记录成功"):n.$message.error("删除提交记录失败,"+t.msg)}))})).catch((function(){n.$message({type:"info",message:"已取消删除"})}))}}},d=c,u=(n("1a21"),n("2877")),p=Object(u["a"])(d,o,r,!1,null,"830c6304",null);e["a"]=p.exports},4397:function(t,e,n){"use strict";n.d(e,"a",(function(){return r}));n("ac1f"),n("1276"),n("d3b7"),n("3ca3"),n("ddb0"),n("2b3d"),n("9861");function o(t){var e=t.split(".");return e[e.length-1]}function r(t){var e=t.headers["filename"],n=new Blob([t.data],{type:"application/"+o(e)});if("undefined"!==typeof window.navigator.msSaveBlob)window.navigator.msSaveBlob(n,decodeURI(e));else{var r=window.URL.createObjectURL(n),i=document.createElement("a");i.style.display="none",i.href=r,i.setAttribute("download",decodeURI(e)),"undefined"===typeof i.download&&i.setAttribute("target","_blank"),document.body.appendChild(i),i.click(),document.body.removeChild(i),window.URL.revokeObjectURL(r)}}},"5deb":function(t,e,n){"use strict";var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return t.isClose?n("div",{staticClass:"show-window"},[n("el-row",{staticClass:"show-window-title"},[n("el-col",{attrs:{span:16}},[n("div",{staticClass:"hidden-sm-and-down"},[t._t("title")],2)]),n("el-col",{attrs:{offset:2,span:4}},[n("div",{staticClass:"light-tool"},[n("el-switch",{attrs:{value:t.lightType,"active-color":"#3c3f41","inactive-color":"#ffd04c","active-value":1,"inactive-value":0,"active-icon-class":"el-icon-moon","inactive-icon-class":"el-icon-sunny"},on:{change:function(e){return e.target!==e.currentTarget?null:t.colorModuleChange.apply(null,arguments)}}})],1)]),n("el-col",{staticClass:"show-window-button",attrs:{span:4}},[n("div",{staticClass:"el-icon-refresh",on:{click:t.reload}}),n("div",{staticClass:"el-icon-close",on:{click:t.close}})])],1),n("el-row",[n("el-col",{attrs:{span:24}},[n("div",{staticClass:"show-window-main",style:t.bgcColor},[n("div",{staticStyle:{position:"relative","border-radius":"20px",padding:"10px 0"},style:t.slotAreaColor},[t._t("main")],2)])])],1)],1):t._e()},r=[],i=n("4ba1"),a={name:"ShowWindow",computed:{bgcColor:function(){return{backgroundColor:this.color[this.lightType].bgcColor}},slotAreaColor:function(){return{backgroundColor:this.color[this.lightType].slotAreaBgc}}},methods:{close:function(){this.isClose=!1},reload:function(){this.$forceUpdate()},colorModuleChange:function(t){this.lightType=t,this.$store.commit(i["b"],{colorModule:t})}},data:function(){return{lightType:0,isClose:!0,color:this.$store.getters.getColor}},activated:function(){this.lightType=this.$store.getters.getColorModule}},s=a,l=(n("85e3"),n("2877")),c=Object(l["a"])(s,o,r,!1,null,"31b52f1e",null);e["a"]=c.exports},"85e3":function(t,e,n){"use strict";n("102c")},b114:function(t,e,n){"use strict";n.d(e,"a",(function(){return s})),n.d(e,"c",(function(){return l})),n.d(e,"b",(function(){return c}));var o=n("bc3a"),r=n.n(o),i=n("1bab"),a=n("f121");function s(t){var e=r.a.create({timeout:5e3,withCredentials:!0});return e(t)}function l(t,e,n,o,r){return Object(i["a"])({url:"/task/submitTask",params:{userId:t,taskId:e,description:n},onUploadProgress:r,method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:o})}function c(t){return s({url:a["b"],params:{filePath:t},responseType:"blob",headers:{Accept:"application/json","Content-Type":"application/json; charset=utf-8"}})}},bd9c:function(t,e,n){"use strict";n.d(e,"b",(function(){return i})),n.d(e,"a",(function(){return a})),n.d(e,"e",(function(){return s})),n.d(e,"h",(function(){return l})),n.d(e,"i",(function(){return c})),n.d(e,"j",(function(){return d})),n.d(e,"k",(function(){return u})),n.d(e,"l",(function(){return p})),n.d(e,"d",(function(){return h})),n.d(e,"c",(function(){return f})),n.d(e,"f",(function(){return b})),n.d(e,"g",(function(){return m}));n("b0c0");var o=n("1bab"),r=n("b114");function i(t){return Object(o["a"])({url:"/task/add",data:{taskName:t.name,groupId:t.groupId,deadline:t.date,description:t.desc,creatorId:t.creatorId},method:"post"})}function a(t){return Object(o["a"])({url:"/task/alterTask",data:t,method:"post"})}function s(t,e,n){return Object(o["a"])({url:"/task/dropTask",data:{id:t,creatorId:e,userPwd:n},method:"post"})}function l(t,e,n,r,i){return Object(o["a"])({url:"/task/pageQueryUserTask",data:{page:e,limit:n,userId:t,type:r,groupId:i},method:"post"})}function c(t){return Object(o["a"])({url:"/task/queryByTaskId",params:{taskId:t},method:"post"})}function d(t,e,n){return Object(o["a"])({url:"/task/taskSubmitRecord",data:{taskId:t,page:e,limit:n},method:"post"})}function u(t,e,n){return Object(o["a"])({url:"/task/userSubmitRecord",data:{userId:t,page:e,limit:n},method:"post"})}function p(t){return Object(o["a"])({url:"/task/queryTaskSubmitCharts",data:{taskId:t},method:"post"})}function h(t,e){return Object(o["a"])({url:"/task/deleteTaskSubmitRecord",data:{recordId:t,userId:e},method:"post"})}function f(t,e){return Object(o["a"])({url:"/task/deleteSelectedRecord",data:{recordIds:t,userId:e},method:"post"})}function b(t){return Object(r["a"])({url:"http://localhost:9000/XM06/task/exportMyRecordExcel",data:{userId:t},responseType:"blob",headers:{Accept:"application/json","Content-Type":"application/json; charset=utf-8"},method:"post"})}function m(t){return Object(r["a"])({url:"http://localhost:9000/XM06/task/exportTaskRecordExcel",data:{taskId:t},responseType:"blob",headers:{Accept:"application/json","Content-Type":"application/json; charset=utf-8"},method:"post"})}},d84b:function(t,e,n){}}]);
//# sourceMappingURL=chunk-4e5cacbb.f11e2ef1.js.map