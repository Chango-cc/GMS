const app=new Vue({
    el: "#table_placeUse",
    data: {
        start: 0,
        pages: 1,
        page: 1,
        length: 5,
        listed: "",
        list: [],
        storey:"请选择场地楼层",//场地楼层
        type:"请选择场地类型",//场地类型
        placeNo:"",
        modelid:"",
        dateList:[],
        choose:[],
        keepList:[],
        title:"",
        act:"active",
        timein:"fa fa-toggle-on fa-2x",
        number:-1,
        startusetime:"",
        timeLocal:"start",
        hh:"",
        mf:"",
        ss:"",
        current:1,
        size:4,
        totalpage:""
    },
    filters: {
        formatDate: function (value) {
            let date = new Date(value);
            let y = date.getFullYear();
            let MM = date.getMonth() + 1;
            MM = MM < 10 ? ('0' + MM) : MM;
            let d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            return y + '-' + MM + '-' + d ;
        }
    },
    methods: {
        turnpagepre(){
            if (this.current !== 1){
                this.current=this.listed.current-1
                this.sentChecked(this.title)
            }
        },
        turnpagenext(){
            if (this.current !== this.totalpage){
                this.current=this.listed.current+1
                this.sentChecked(this.title)
            }
        },
        turnpage(local){
            // if(local > 0 && local < this.totalpage+1){
            //
            // }
            this.current=local
            this.sentChecked(this.title)
        },
        settimed(week){
            this.title="场地一周信息"
            var object = this
            $.ajax({
                url: "../place/placeToSelectWeek",
                contentType: "application/json;charset=UTF-8",
                data: {"week":week,"current":this.current,"size":this.size},
                type: "get",
                success: function (result) {
                    // object.list=result;
                    // console.log("-----------   "+result)
                    object.listed=result;
                    object.totalpage=object.listed.pages;
                    object.list=result.records;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        sentChecked(check){
            this.act="";
            if(this.title !== check){
                this.current=1
            }
            if(check==="场地一周信息"){
                check=""
            }
            this.title=check;
            console.log("要输入的查询状态----------------------------"+check);
            var object=this;
            var checkedFirst;
            var checkedSecond;
            if(this.storey=="请选择场地楼层"){
                checkedFirst=""
            }else
                checkedFirst=this.storey;
            if(this.type=="请选择场地类型"){
                checkedSecond=""
            }else
                checkedSecond=this.type;

            $.ajax({
                url: "../place/placeToSelect",
                contentType: "application/json;charset=UTF-8",
                data: {"current":this.current,"size":this.size,"storey":checkedFirst,"type":checkedSecond,"check":check},
                type: "get",
                success: function (result) {
                    // object.list=result;
                    console.log("-----------   "+result)
                    object.listed=result;
                    object.totalpage=object.listed.pages;
                    object.list=result.records;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })

        },
        tochangkeep(){
            $.ajax({
                url: "../place/toChangeKeeped",
                contentType: "application/json;charset=UTF-8",
                type: "get",
                success: function (result) {
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        changestate(){
            $.ajax({
                url: "../place/placeToChange",
                contentType: "application/json;charset=UTF-8",
                type: "get",
                success: function (result) {
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        sentSelect(){
            this.sentChecked(this.title)
        },
        applyEdit(applyId){

            this.applyState(applyId,"待使用")

        },
        applySuccess(applyId){

            this.applyState(applyId,"已通过")

        },
        applyDelete(applyId,local){
            // SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            // Date date = dateformat.parse("2016-6-19");
            // var num = (new Date().getTime()-date1.getTime())/(24*60*60*1000);
            // console.log("---------"+this.list[local].applyDate+"---------"+new Date()+"------"+(this.list[local].applyDate - new Date()))
            var time = this.list[local].applyDate;
            var timestamp2 = Date.parse(new Date(this.formatDate(time)));
            var num = (new Date().getTime()-timestamp2)/(24*60*60*1000);
            if(num<0){
                console.log("-----------+----------"+timestamp2+"++++++++++++++++"+num+"-----")
                 this.applyState(applyId,"已退订")
            }else {
                alert("请提前一天退订")
            }
        },
        formatDate: function (value) {
            let date = new Date(value);
            let y = date.getFullYear();
            let MM = date.getMonth() + 1;
            MM = MM < 10 ? ('0' + MM) : MM;
            let d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            return y + '-' + MM + '-' + d ;
        },
        applyFail(applyId){

            this.applyState(applyId,"审核不通过")

        },
        applyPay(applyId){

            this.applyState(applyId,"已完成")

        },
        applyState(applyId,applyState){
            var object=this;
            $.ajax({
                url: "../place/placeToUpdateState",
                contentType: "application/json;charset=UTF-8",
                data: {"applyId":applyId,"applyState":applyState},
                type: "get",
                success: function (result) {
                    console.log("点击×后的结果---------------"+result+"用于匹配的申请编号 -------------"+applyId);
                    if(result){
                        // for(var i=0;i<object.list.length;i++){
                        //     if (object.list[i].applyId === applyId){
                        //         object.list.splice(i,1)
                        //     }
                        // }
                        object.sentChecked(object.title);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        setTime(applyId,local,start,end,timeperoid){
            console.log("对应的位置---"+local+"---开始时间---"+start+"--结束时间---"+end+"----申请的时间---"+"----申请的时间段----"+timeperoid)
            var localed = "in"+local
            let timearry = []
            this.getNow()
            var object = this
            //获得切割后的时间段
            timearry = this.timeArray(timeperoid)
            var j = parseInt(timearry[0])
            var k = parseInt(timearry[1])
            var now = parseInt(this.hh)
            console.log("-------j---------"+j+"-----k-------"+k+"------now------"+now)
            if((now === j || now > j) && now < k){
                console.log("对应的位置888888---"+start+"---开始时间888888---"+end+"     "+(start ===null || end ===null))
                if(start ===null || end ===null){

                    if(this.timein==="fa fa-toggle-on fa-2x"){
                        document.getElementById(localed).setAttribute("class",this.timein)
                        this.timein="fa fa-toggle-off fa-2x"
                    }else
                    {
                        document.getElementById(localed).setAttribute("class",this.timein)
                        this.timein="fa fa-toggle-on fa-2x"
                    }
                    if(start !==null){
                        this.timeLocal="end"
                    }
                    this.startusetime = this.hh+":"+this.mf
                    $.ajax({
                        // 设置json的属性和值
                        url: "../place/placeToSetTime",
                        contentType: "application/json;charset=UTF-8",
                        data: {'applyId':applyId,'timeStart':this.startusetime,'timeLocal':this.timeLocal},
                        async:false,
                        type: "get",
                        success: function (data) {
                            alert("场地开始使用")
                            if(start ===null){
                                object.list[local].applyStart=object.startusetime
                            }
                            else{
                                object.list[local].applyEnd=object.startusetime
                            }
                        }
                    })
                }
                }
                if(now === k || now > k){
                alert("超出时间，不可使用")
               }
               if(now < j){
                alert("尚未到时间")
               }
            console.log("-----------------d "+this.startusetime)
        },
        timeArray:function(time){
            return time.split('-')
        },
//获取使用开始使用时间
        getNow(){
            let yy = new Date().getFullYear();
            let mo = new Date().getMonth();
            let da = new Date().getDate();
            let hh = new Date().getHours();
            let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
            let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
            this.hh = hh
            this.mf = mf
        },
        chooseToKeep(aim){
            this.state=aim;
            console.log("----------------");
            console.log("开始了，目前的长度为"+this.choose.length);
            console.log("选中的位置是"+aim);
            var bool = -1;
            if(this.choose.length === 0){
                this.choose.push(aim)
            }else {
                for(var i=0;i<this.choose.length;i++){
                    console.log("选中的位置是"+this.choose[i]);
                    if(aim ===  this.choose[i] ){
                        bool = i
                    }
                }
                if(bool === -1){
                    this.choose.push(aim)
                }else {
                    this.choose.splice(bool,1)
                }
            }
            console.log("结束了，目前的长度为"+this.choose.length);
            console.log("----------------");
        },
        toKeep(){
            console.log("准备了，目前的长度为"+this.choose.length);
            for( var i = 0;i<this.choose.length;i++){

                this.keepList.push(this.list[this.choose[i]])
                console.log("选中的位置是"+this.list[this.choose[i]].placeNo);
            }
            $.ajax({
                url: "../place/placeKeeping",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(this.keepList),
                type: "post",
                success: function (result) {
                    console.log("niu++++++++++"+result);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
            for( var i = 0;i<this.choose.length;i++){

                this.list.splice(this.choose[i],1)

            }
            this.state=-1
            this.choose=[]
        },
        // getNum(object){
        //     $.ajax({
        //         url: "../match/queryMatchNum",
        //         contentType: "application/json;charset=UTF-8",
        //         type: "post",
        //         success: function (result) {
        //             object.setPages(Math.ceil(result / 5));
        //             document.getElementById("1").classList.add("active");
        //         },
        //         error: function (XMLHttpRequest, textStatus, errorThrown) {
        //             console.log("error message：" + XMLHttpRequest.responseText);
        //         }
        //     })
        // },

        setPages(pages){
            this.pages = pages;
        },
        setActive(newIndex) {
            document.getElementById("ul_pages").childNodes[this.page + 1].classList.remove("active");
            document.getElementById("ul_pages").childNodes[newIndex + 1].classList.add("active");
            this.page = newIndex;
        },
        nextPage: function () {
            if (this.page < this.pages) {
                this.start += 5;
                // this.end += 5;
                this.setActive(this.page + 1);
                this.getData(this,this.start,this.length);
            }
        },
        previousPage: function () {
            if (this.page > 1) {
                this.start -= 5;
                // this.end -= 5;
                this.setActive(this.page - 1);
                this.getData(this,this.start,this.length);
            }
        },
        goto: function (index) {
            this.start = 5 * (index - 1);
            // this.end = 5 * (index);
            this.setActive(index);
            this.getData(this,this.start,this.length);
        },
        getDate(){
            var date = new Date();
            var list = this.getDateRange(date,8,false)
            return list
        },
        getDateRange(dateNow,intervalDays,bolPastTime){
            let oneDayTime = 24 * 60 * 60 * 1000;
            let list = [];
            let lastDay;
            if(bolPastTime == true){//前一周
                lastDay = new Date(dateNow.getTime() - intervalDays * oneDayTime);
                list.push(this.formateDate(lastDay));
                list.push(this.formateDate(dateNow));
            }else{//后一周
                for(var i = 1;i<intervalDays;i++){
                    lastDay = new Date(dateNow.getTime() + i * oneDayTime);
                    list.push(this.formateDate(lastDay));
                }
            }
            return list;
        },
        formateDate(time){
            let year = time.getFullYear()
            let month = time.getMonth() + 1
            let day = time.getDate()
            if (month < 10) {
                month = '0' + month
            }
            if (day < 10) {
                day = '0' + day
            }
            return year + '-' + month + '-' + day + ''
        },
    },
    mounted() {
        this.sentChecked("待审核");
        this.changestate();//更新是否为已失约
    },
})