(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4abcbef5"],{2490:function(t,e,n){"use strict";n("d157")},3302:function(t,e,n){"use strict";n("44ec")},"44ec":function(t,e,n){},"54e7":function(t,e,n){},7039:function(t,e,n){"use strict";n("b1e9")},"75a4":function(t,e,n){},abb1:function(t,e,n){"use strict";n.d(e,"a",(function(){return r})),n.d(e,"b",(function(){return a})),n.d(e,"c",(function(){return s})),n.d(e,"d",(function(){return c}));var i=n("1bab");function r(){return Object(i["a"])({url:"/systemRecord/7day",method:"post"})}function a(){return Object(i["a"])({url:"/systemRecord/total",method:"post"})}function s(t){return Object(i["a"])({url:"/project/querySubmitChartInfo",data:{projectId:t},method:"post"})}function c(t){return Object(i["a"])({url:"/project/queryUserSubmit14day",data:{projectId:t},method:"post"})}},b1e9:function(t,e,n){},b9c0:function(t,e,n){},ba23:function(t,e,n){"use strict";n("54e7")},c0f5:function(t,e,n){"use strict";n("75a4")},d157:function(t,e,n){},dfdc:function(t,e,n){"use strict";n("b9c0")},fefd:function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"introduce"},[n("div",[n("el-carousel",{attrs:{interval:4e3,height:"45vw"}},t._l(t.getSlideCount,(function(e){return n("el-carousel-item",{key:e},[n("img",{staticClass:"carousel-img",attrs:{src:t.getSlideImg(e),alt:""},on:{click:t.toPage}})])})),1)],1),n("introduce-title",{attrs:{title:"我们的功能","title-desc":"专为企业项目而生","title-eng":"How can we do"}}),n("function-card"),n("br"),n("br"),n("br"),n("hr"),n("introduce-title",{attrs:{title:"开发成员","title-desc":"专业的开发团队","title-eng":"Who we are"}}),n("member-card"),n("br"),n("br"),n("hr"),n("introduce-title",{attrs:{title:"系统使用情况","title-desc":"科学的数据分析","title-eng":"How many user"}}),n("system7day-charts"),n("br"),n("br"),n("system-total-charts")],1)},r=[],a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{width:"auto",height:"400px",padding:"15px"},attrs:{id:"system7dayChart"}})},s=[],c=n("abb1"),o={name:"System7dayCharts",data:function(){return{option:{title:{text:"7天内的使用情况",y:"bottom",x:"center",textStyle:{marginBottom:15}},tooltip:{trigger:"axis"},legend:{data:[],x:"center",padding:[0,25,0,0]},grid:{left:"3%",right:"4%",bottom:"15%",top:"80px",containLabel:!0},toolbox:{feature:{saveAsImage:{}}},xAxis:{type:"category",boundaryGap:!1,data:[]},yAxis:{type:"value",axisLabel:{formatter:"{value} 人"}},series:[]},legendData:[],series:[],xAxisData:[]}},methods:{echartsInit:function(){this.$echarts.init(document.getElementById("system7dayChart")).setOption(this.option)},setOption:function(t,e,n){this.option.legend.data=t,this.option.xAxis.data=e,this.option.series=n}},mounted:function(){var t=this;Object(c["a"])().then((function(e){var n=e.xAxis,i=e.legend,r=[];for(var a in e.series){var s={type:"line"};s.name=a,s.data=e.series[a],r.push(s)}t.setOption(i,n,r),t.echartsInit()}))}},l=o,u=n("2877"),d=Object(u["a"])(l,a,s,!1,null,"e6b2cf22",null),f=d.exports,m=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"function-card"},[n("div",{staticClass:"main"},t._l(t.dataList,(function(t){return n("function-card-item",{attrs:{bgc:t.bgc,title:t.title,description:t.description}})})),1)])},p=[],b=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"function-card-item",style:t.cardItemStyle},[n("h3",[t._v(t._s(t.title))]),n("div",[t._v(" "+t._s(t.description)+" ")])])},h=[],g={name:"FunctionCardItem",props:{title:{type:String,default:"功能标题"},description:{type:String,default:"描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息"},bgc:{type:String,default:"#7e8dc8"},fontColor:{type:String,default:"#ffffff"}},computed:{cardItemStyle:function(){return{backgroundColor:this.bgc,color:this.fontColor}}}},y=g,v=(n("7039"),Object(u["a"])(y,b,h,!1,null,"3bd3d675",null)),x=v.exports,_={name:"FunctionCard",components:{FunctionCardItem:x},data:function(){return{dataList:[{title:"用户管理",description:"安全的邮箱验证，创建即可使用本系统",bgc:"#7ea6c8"},{title:"小组管理",description:"用户可创建加入小组，以组为单位进行项目开发。",bgc:"#967ec8"},{title:"任务管理",description:"发布限时任务，可用于周报提交、收集资料、发布阶段成功等",bgc:"#7e8cc8"},{title:"项目管理",description:"即时查看更新项目进度、成员等信息，还可下载查看成员提交的内容与文件。",bgc:"#827ec8"},{title:"便签功能",description:"随时自订便签，待办工作不遗忘。",bgc:"#907ec8"},{title:"即时通知",description:"小组成员可及时收到消息，提高沟通交流效率",bgc:"#7e8dc8"}]}}},C=_,S=(n("2490"),Object(u["a"])(C,m,p,!1,null,"37fb02f1",null)),I=S.exports,O=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"function-title"},[n("h4",[t._v(t._s(t.title))]),n("span",[t._v(t._s(t.titleEng))]),n("div",[t._v(t._s(t.titleDesc))])])},j=[],w={name:"IntroduceTitle",props:{title:{type:String,default:""},titleEng:{type:String,default:""},titleDesc:{type:String,default:""}}},$=w,E=(n("ba23"),Object(u["a"])($,O,j,!1,null,"42251048",null)),A=E.exports,L=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"member-card"},t._l(t.infoList,(function(e){return n("member-card-item",[n("div",{attrs:{slot:"img"},slot:"img"},[n("img",{attrs:{src:e.imgSrc,alt:""}})]),n("div",{attrs:{slot:"info"},slot:"info"},[n("span",[t._v(t._s(e.name))]),n("p",[t._v(t._s(e.desc))])])])})),1)},k=[],T=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"member-card-item"},[i("el-card",{attrs:{"body-style":{padding:"0px",maxWidth:"310px"}}},[t._t("img",(function(){return[i("img",{attrs:{src:n("88f4")}})]})),i("div",{staticStyle:{padding:"14px"}},[t._t("info",(function(){return[i("span",[t._v("项目经理:何磊")]),i("p",[t._v("四川大学计算机与科学专业本科生")])]}))],2)],2)],1)},D=[],F={name:"MemberCardItem"},M=F,B=(n("c0f5"),Object(u["a"])(M,T,D,!1,null,"4fb7d026",null)),q=B.exports,H=n("c5ab"),J={name:"MemberCard",components:{MemberCardItem:q},data:function(){return{infoList:H["b"]}}},P=J,R=(n("3302"),Object(u["a"])(P,L,k,!1,null,"d3e4e3e8",null)),U=R.exports,W=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{width:"auto",height:"400px",padding:"15px"},attrs:{id:"systemTotalChart"}})},z=[],G={name:"SystemTotalCharts",data:function(){return{option:{title:{text:"系统使用情况统计"},grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:{type:"category",data:[]},yAxis:{type:"value"},series:[{data:[],type:"bar",itemStyle:{normal:{label:{show:!0,position:"top",textStyle:{color:"black",fontSize:16}}}}}]},color:["#c7c87e","#c8a57e","#7ec897","#c8917e","#cea08b","#c8a47e","#a5c87e"]}},methods:{echartsInit:function(){this.$echarts.init(document.getElementById("systemTotalChart")).setOption(this.option)},setOption:function(t,e){this.option.xAxis.data=t,this.option.series[0].data=e}},mounted:function(){var t=this;Object(c["b"])().then((function(e){for(var n=e.xAxis,i=[],r=0;r<e.series.length;r++){var a=e.series[r],s={value:a,itemStyle:{color:t.color[r]}};i.push(s)}t.setOption(n,i),t.echartsInit()}))}},K=G,N=Object(u["a"])(K,W,z,!1,null,"6affcf1a",null),Q=N.exports,V={name:"Introduce",components:{SystemTotalCharts:Q,MemberCard:U,IntroduceTitle:A,FunctionCard:I,System7dayCharts:f},computed:{isLogin:function(){return null!=this.$store.getters.getLoginUser},getSlideImg:function(){return function(t){return H["a"][t-1]}},getSlideCount:function(){return H["a"].length}},methods:{toPage:function(){this.isLogin?this.$router.push("/index"):this.$router.push("/login")}}},X=V,Y=(n("dfdc"),Object(u["a"])(X,i,r,!1,null,"42c9d036",null));e["default"]=Y.exports}}]);
//# sourceMappingURL=chunk-4abcbef5.8b16c428.js.map