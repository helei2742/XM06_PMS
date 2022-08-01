(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7f90e5a8"],{"102c":function(t,e,o){},"5deb":function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.isClose?o("div",{staticClass:"show-window"},[o("el-row",{staticClass:"show-window-title"},[o("el-col",{attrs:{span:16}},[o("div",{staticClass:"hidden-sm-and-down"},[t._t("title")],2)]),o("el-col",{attrs:{offset:2,span:4}},[o("div",{staticClass:"light-tool"},[o("el-switch",{attrs:{value:t.lightType,"active-color":"#3c3f41","inactive-color":"#ffd04c","active-value":1,"inactive-value":0,"active-icon-class":"el-icon-moon","inactive-icon-class":"el-icon-sunny"},on:{change:function(e){return e.target!==e.currentTarget?null:t.colorModuleChange.apply(null,arguments)}}})],1)]),o("el-col",{staticClass:"show-window-button",attrs:{span:4}},[o("div",{staticClass:"el-icon-refresh",on:{click:t.reload}}),o("div",{staticClass:"el-icon-close",on:{click:t.close}})])],1),o("el-row",[o("el-col",{attrs:{span:24}},[o("div",{staticClass:"show-window-main",style:t.bgcColor},[o("div",{staticStyle:{position:"relative","border-radius":"20px",padding:"10px 0"},style:t.slotAreaColor},[t._t("main")],2)])])],1)],1):t._e()},i=[],r=o("4ba1"),l={name:"ShowWindow",computed:{bgcColor:function(){return{backgroundColor:this.color[this.lightType].bgcColor}},slotAreaColor:function(){return{backgroundColor:this.color[this.lightType].slotAreaBgc}}},methods:{close:function(){this.isClose=!1},reload:function(){this.$forceUpdate()},colorModuleChange:function(t){this.lightType=t,this.$store.commit(r["b"],{colorModule:t})}},data:function(){return{lightType:0,isClose:!0,color:this.$store.getters.getColor}},activated:function(){this.lightType=this.$store.getters.getColorModule}},s=l,a=(o("85e3"),o("2877")),c=Object(a["a"])(s,n,i,!1,null,"31b52f1e",null);e["a"]=c.exports},"7b00":function(t,e,o){},"85e3":function(t,e,o){"use strict";o("102c")},"9bba":function(t,e,o){"use strict";o("7b00")},ae4c:function(t,e,o){"use strict";o.r(e);var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("show-window",[o("div",{attrs:{slot:"title"},slot:"title"},[o("i",{staticClass:"el-icon-s-order"}),o("span",[t._v("公告管理")]),o("i",{staticClass:"el-icon-arrow-right"}),o("span",[t._v("发布公告")])]),o("div",{attrs:{slot:"main"},slot:"main"},[o("create-announce-form",{on:{createAnnounce:t.createAnnounce}})],1)])],1)},i=[],r=(o("d3b7"),o("5deb")),l=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"create-announce-form"},[o("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:t.ruleForm,rules:t.rules,"label-width":"100px"}},[o("el-form-item",{attrs:{label:"公告标题",prop:"title"}},[o("el-input",{model:{value:t.ruleForm.title,callback:function(e){t.$set(t.ruleForm,"title",e)},expression:"ruleForm.title"}})],1),o("el-form-item",{attrs:{label:"公告内容",prop:"mainBody"}},[o("el-input",{attrs:{type:"textarea","show-word-limit":!0,rows:20,minlength:1,maxlength:7e3},model:{value:t.ruleForm.mainBody,callback:function(e){t.$set(t.ruleForm,"mainBody",e)},expression:"ruleForm.mainBody"}})],1),o("el-form-item",[o("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.submitForm("ruleForm")}}},[t._v("立即创建")]),o("el-button",{on:{click:function(e){return t.resetForm("ruleForm")}}},[t._v("重置")])],1)],1)],1)},s=[],a={name:"CreateAnnounceForm",data:function(){return{ruleForm:{title:"",mainBody:""},rules:{title:[{required:!0,message:"请输入公告标题",trigger:"blur"},{min:1,max:30,message:"长度在 1 到 50                                                                                                                                                         个字符",trigger:"blur"}],mainBody:[{required:!0,message:"请输入公告内容",trigger:"blur"}]}}},methods:{submitForm:function(t){var e=this;this.$refs[t].validate((function(t){if(!t)return!1;e.$emit("createAnnounce",{title:e.ruleForm.title,mainBody:"mainbody:"+e.ruleForm.mainBody})}))},resetForm:function(t){this.$refs[t].resetFields()}}},c=a,u=(o("9bba"),o("2877")),m=Object(u["a"])(c,l,s,!1,null,"9685c162",null),d=m.exports,f=o("9442"),h={name:"CreateAnnounce",components:{CreateAnnounceForm:d,ShowWindow:r["a"]},methods:{createAnnounce:function(t){var e=this,o=this.$loading({lock:!0,text:"正在发布公告",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"}),n=this.$store.getters.getLoginUser.id;Object(f["b"])(n,t.title,t.mainBody).then((function(t){console.log(t),200===t.code?e.$message.success("发布公告成功"):e.$alert("发布公告失败，"+t.msg)})).finally((function(){o.close()}))}}},g=h,b=Object(u["a"])(g,n,i,!1,null,"2b736cf8",null);e["default"]=b.exports}}]);
//# sourceMappingURL=chunk-7f90e5a8.78bf0722.js.map