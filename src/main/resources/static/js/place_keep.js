const app=new Vue({
    el: "#table_placeKeep",
    data: {
        start: 0,
        pages: 1,
        page: 1,
        length: 5,
        list: [],
        storey:"请选择场地楼层",//场地楼层
        type:"请选择场地类型",//场地类型
        checkedIdleft:-1,
        placeNo:"",
        placemsg:"",
        modelid:"",
        timeList0:[
            {time:"08:00~09:00",active:false},{time:"09:00~10:00",active:false},{time:"10:00~11:00",active:false},{time:"11:00~12:00",active:false},
            {time:"14:00~15:00",active:false},{time:"15:00~16:00",active:false},{time:"16:00~17:00",active:false},{time:"17:00~18:00",active:false},
            {time:"18:00~19:00",active:false},{time:"19:00~20:00",active:false},{time:"20:00~21:00",active:false},{time:"21:00~22:00",active:false}
        ],
        timeList1:[
            {time:"08:00~09:00",active:false},{time:"09:00~10:00",active:false},{time:"10:00~11:00",active:false},{time:"11:00~12:00",active:false},
            {time:"14:00~15:00",active:false},{time:"15:00~16:00",active:false},{time:"16:00~17:00",active:false},{time:"17:00~18:00",active:false},
            {time:"18:00~19:00",active:false},{time:"19:00~20:00",active:false},{time:"20:00~21:00",active:false},{time:"21:00~22:00",active:false}
        ],
        timeList2:[
            {time:"08:00~09:00",active:false},{time:"09:00~10:00",active:false},{time:"10:00~11:00",active:false},{time:"11:00~12:00",active:false},
            {time:"14:00~15:00",active:false},{time:"15:00~16:00",active:false},{time:"16:00~17:00",active:false},{time:"17:00~18:00",active:false},
            {time:"18:00~19:00",active:false},{time:"19:00~20:00",active:false},{time:"20:00~21:00",active:false},{time:"21:00~22:00",active:false}
        ],
        timeList3:[
            {time:"08:00~09:00",active:false},{time:"09:00~10:00",active:false},{time:"10:00~11:00",active:false},{time:"11:00~12:00",active:false},
            {time:"14:00~15:00",active:false},{time:"15:00~16:00",active:false},{time:"16:00~17:00",active:false},{time:"17:00~18:00",active:false},
            {time:"18:00~19:00",active:false},{time:"19:00~20:00",active:false},{time:"20:00~21:00",active:false},{time:"21:00~22:00",active:false}
        ],
        timeList4:[
            {time:"08:00~09:00",active:false},{time:"09:00~10:00",active:false},{time:"10:00~11:00",active:false},{time:"11:00~12:00",active:false},
            {time:"14:00~15:00",active:false},{time:"15:00~16:00",active:false},{time:"16:00~17:00",active:false},{time:"17:00~18:00",active:false},
            {time:"18:00~19:00",active:false},{time:"19:00~20:00",active:false},{time:"20:00~21:00",active:false},{time:"21:00~22:00",active:false}
        ],
        timeList5:[
            {time:"08:00~09:00",active:false},{time:"09:00~10:00",active:false},{time:"10:00~11:00",active:false},{time:"11:00~12:00",active:false},
            {time:"14:00~15:00",active:false},{time:"15:00~16:00",active:false},{time:"16:00~17:00",active:false},{time:"17:00~18:00",active:false},
            {time:"18:00~19:00",active:false},{time:"19:00~20:00",active:false},{time:"20:00~21:00",active:false},{time:"21:00~22:00",active:false}
        ],
        timeList6:[
            {time:"08:00~09:00",active:false},{time:"09:00~10:00",active:false},{time:"10:00~11:00",active:false},{time:"11:00~12:00",active:false},
            {time:"14:00~15:00",active:false},{time:"15:00~16:00",active:false},{time:"16:00~17:00",active:false},{time:"17:00~18:00",active:false},
            {time:"18:00~19:00",active:false},{time:"19:00~20:00",active:false},{time:"20:00~21:00",active:false},{time:"21:00~22:00",active:false}
        ],
        dateList:[],
        Time:[],
        choose:[],
        keepList:[],
        applytype:"教务",

        // 所有页面的数据
        totalPage: [],
        pageSize: 8,
        // 共几页
        pageNum: 1,
        // 当前显示的数据
        dataShow: "",
        // 默认当前显示第一页
        currentPage: 0,

    },
    methods: {
        // getData(object,offset,length){
        //     $.ajax({
        //         url: "../place/queryPlaceKeep",
        //         contentType: "application/json;charset=UTF-8",
        //         data: {"offset":offset,"length":length},
        //         type: "post",
        //         success: function (result) {
        //             // object.list=result;
        //         },
        //         error: function (XMLHttpRequest, textStatus, errorThrown) {
        //             console.log("error message：" + XMLHttpRequest.responseText);
        //         }
        //     })
        // },
        sentChecked(){
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

            for(var i=0;i<this.dateList.length;i++){
                var timelist = [];
                var name = [this.timeList0,this.timeList1,this.timeList2,this.timeList3,this.timeList4,this.timeList5,this.timeList6]
                name[i].forEach(v => {
                    if(v.active){
                        timelist.push(v.time)
                    }
                })
                if(timelist.length!==0){
                    this.Time.push({
                        date:this.dateList[i].date,
                        timelist:timelist
                    })
                }
            }
            if(this.Time.length === 0){
                this.Time.push({
                    date:this.dateList[0].date,
                    timelist:timelist
                })
            }
            var msg= {};
            msg.timeCollection=this.Time;
            msg.storey=checkedFirst;
            msg.type=checkedSecond;
            // console.log("hhdafka:"+msg.timeCollection[0].timelist[0]);
            $.ajax({
                url: "../place/placeKeepSelect",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(msg),
                async:false,
                type: "post",
                success: function (result) {
                    console.log("sdfgsdfsd    "+result)
                     object.list=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
            for(var j=0;j<this.dateList.length;j++){
                var named = [this.timeList0,this.timeList1,this.timeList2,this.timeList3,this.timeList4,this.timeList5,this.timeList6]
                named[j].forEach(v => {
                    v.active=false;
                })
            }
            for(var k=0;k<this.choose.length;k++){
                document.getElementById("msgtable").getElementsByTagName("input")[this.choose[k]].checked=false;
            }
            this.choose=[]
            this.Time = []

            this.currentPage = 0
            this.showData()
            this.getA()
        },
        turnPage(index){
            this.dataShow = this.totalPage[index]
            this.currentPage = index-1

        },
        // 下一页
        nextPage() {
            if (this.currentPage === this.pageNum - 1) return ;
            this.dataShow = this.totalPage[++this.currentPage];
        },
        // 上一页
        prePage() {
            if (this.currentPage === 0) return ;
            this.dataShow = this.totalPage[--this.currentPage];
        },
        chooseToKeep(aim){
            var c = aim+(this.currentPage)*8;
            var bool = -1;
            if(this.choose.length === 0){
                this.choose.push(c)
            }else {
                for(var i=0;i<this.choose.length;i++){
                    if(c ===  this.choose[i] ){
                        bool = i
                    }
                }
                if(bool === -1){
                    this.choose.push(c)
                }else {
                    this.choose.splice(bool,1)
                }
            }
        },
        tochange() {
            var object = this;
            var url = location.search; //获取url中"?"符后的字串
            var theRequest = new Object();
            if(url.indexOf("?")!=-1){
                result = url.substr(url.indexOf("=")+1);
            }
            var changContent = {}
            changContent.placeAvailable = this.changitem
            changContent.date = result
            console.log("     ++  "+result.toString())
            $.ajax({
                url: "../place/placeApplyToUpdata",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(changContent),
                async:false,
                type: "post",
                success: function (result) {
                    if(result==="success"){
                        alert("修改成功")
                        document.getElementById("return").click();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })

        },
        tochoosechange(local){

            var option=local+(this.currentPage)*8
            this.changitem=this.list[option]
        },
        // chooseToKeep(aim){
        //     this.state=aim;
        //     console.log("----------------");
        //     console.log("开始了，目前的长度为"+this.choose.length);
        //     console.log("选中的位置是"+aim);
        //     var bool = -1;
        //     if(this.choose.length === 0){
        //         this.choose.push(aim)
        //     }else {
        //         for(var i=0;i<this.choose.length;i++){
        //             console.log("选中的位置是"+this.choose[i]);
        //             if(aim ===  this.choose[i] ){
        //                 bool = i
        //             }
        //         }
        //         if(bool === -1){
        //             this.choose.push(aim)
        //         }else {
        //             this.choose.splice(bool,1)
        //         }
        //     }
        //     console.log("结束了，目前的长度为"+this.choose.length);
        //     console.log("----------------");
        // },
        toKeep(){
            console.log("准备了，目前的长度为"+this.choose.length);
            for( var i = 0;i<this.choose.length;i++){

                this.keepList.push(this.list[this.choose[i]])
                console.log("选中的位置是"+this.list[this.choose[i]].placeNo);
            }
            var keep= {};
            keep.keepList=this.keepList;
            keep.date=this.applytype;
            $.ajax({
                url: "../place/placeKeeping",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(keep),
                type: "post",
                success: function (result) {
                    console.log("niu++++++++++"+result);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
            for(var j=0;j<this.choose.length;j++){
                document.getElementById("msgtable").getElementsByTagName("input")[this.choose[j]].checked=false;
            }
            for( var i = 0;i<this.choose.length;i++){

                this.list.splice(this.choose[i],1)

            }
            this.choose=[]
            this.keepList=[]
            this.showData()
            this.getA()
        },
        //每页显示数据
        showData(){
            console.log("长度:"+this.list.length)

            this.pageNum = Math.ceil(this.list.length / this.pageSize) || 1;
            for (let i = 0; i < this.pageNum; i++) {
                var page = []
                var checks = []
                page = this.list.slice(this.pageSize * i, this.pageSize * (i + 1))
                for(let i=0;i<page.length;i++){
                    checks.push({
                        active:false
                    })
                }
                var a = {}
                a.page = page
                a.checks = checks
                this.totalPage[i] = a
            }
            console.log("总页数："+this.pageNum+"  ")
        },
        //dd
        getA(){
            // 获取到数据后显示第一页内容
            this.dataShow = this.totalPage[this.currentPage];
            console.log("当前："+this.dataShow +"  ")
        },
        // sentCheckedHelp(object){
        //     var checkedFirst;
        //     var checkedSecond;
        //     if(this.storey=="请选择场地楼层"){
        //         checkedFirst=""
        //     }else
        //         checkedFirst=this.storey;
        //     if(this.type=="请选择场地类型"){
        //         checkedSecond=""
        //     }else
        //         checkedSecond=this.type;
        //     // var list;
        //     $.ajax({
        //         url: "../place/queryPlaceByChecked",
        //         contentType: "application/json;charset=UTF-8",
        //         data: {"storey":checkedFirst,"type":checkedSecond},
        //         type: "get",
        //         success: function (result) {
        //             object.list=result;
        //         },
        //         error: function (XMLHttpRequest, textStatus, errorThrown) {
        //             console.log("error message：" + XMLHttpRequest.responseText);
        //         }
        //     })
        //     // this.list=list;
        //     console.log("数据筛选成功");
        // },

        // state(checkedleft){
        //     this.checkedIdleft=checkedleft;
        //     console.log("选择的场地编号为"+this.checkedIdleft);
        // },
        // placeEditHelp(){
        //     if(this.checkedIdleft==this.checkedright){
        //         $.ajax({
        //             url: "../place/queryPlaceByChecked",
        //             contentType: "application/json;charset=UTF-8",
        //             data: {"storey":checkedFirst,"type":checkedSecond},
        //             type: "get",
        //             success: function (result) {
        //                 object.list=result;
        //             },
        //             error: function (XMLHttpRequest, textStatus, errorThrown) {
        //                 console.log("error message：" + XMLHttpRequest.responseText);
        //             }
        //         })
        //     }
        // },
        // placeEdit(placeId){
        //     console.log("选择的场地编号为"+this.checkedIdleft+"选择的操作行是"+placeId);
        //     if(this.checkedIdleft==placeId) {
        //          this.modelid="#staticBackdropEdit"
        //     }else
        //         this.modelid=""
        // },
        // placeDeleteHelp(object,placeId){
        //     if(this.checkedIdleft==placeId){
        //         console.log("判断条件"+this.checkedIdleft+"      " +placeId)
        //         $.ajax({
        //             url: "../place/placeToDelete",
        //             contentType: "application/json;charset=UTF-8",
        //             data: {"placeId":placeId},
        //             type: "get",
        //             success: function (result) {
        //                 object.sentChecked();
        //                 object.state();
        //             },
        //             error: function (XMLHttpRequest, textStatus, errorThrown) {
        //                 console.log("error message：" + XMLHttpRequest.responseText);
        //             }
        //         })
        //     }
        // },
        // placeDelete(placeId){
        //     this.placeDeleteHelp(this,placeId);
        // },

        // checkPlaceNo:function(e){
        //     var msg;
        //     $.ajax({
        //         url: "../place/placeToChecked",
        //         contentType: "application/json;charset=UTF-8",
        //         data: {"placeNo":this.placeNo},
        //         async:false,
        //         type: "get",
        //         success: function (result) {
        //             console.log("niu++++++++++"+result);
        //             if(result){
        //                 msg = "该场地已存在，场地编号重复"
        //                 e.preventDefault()
        //             }else {
        //                 msg = ""
        //                 return true
        //             }
        //         },
        //         error: function (XMLHttpRequest, textStatus, errorThrown) {
        //             console.log("error message：" + XMLHttpRequest.responseText);
        //         }
        //     })
        //     this.placemsg = msg;
        // },

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

        // setPages(pages){
        //     this.pages = pages;
        // },
        // setActive(newIndex) {
        //     document.getElementById("ul_pages").childNodes[this.page + 1].classList.remove("active");
        //     document.getElementById("ul_pages").childNodes[newIndex + 1].classList.add("active");
        //     this.page = newIndex;
        // },
        // nextPage: function () {
        //     if (this.page < this.pages) {
        //         this.start += 5;
        //         // this.end += 5;
        //         this.setActive(this.page + 1);
        //         this.getData(this,this.start,this.length);
        //     }
        // },
        // previousPage: function () {
        //     if (this.page > 1) {
        //         this.start -= 5;
        //         // this.end -= 5;
        //         this.setActive(this.page - 1);
        //         this.getData(this,this.start,this.length);
        //     }
        // },
        // goto: function (index) {
        //     this.start = 5 * (index - 1);
        //     // this.end = 5 * (index);
        //     this.setActive(index);
        //     this.getData(this,this.start,this.length);
        // },
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
    created(){
        // this.dateList = this.getDate()
        for (var i=0;i<this.getDate().length;i++){
            this.dateList.push({
                date:this.getDate()[i],
                active:false
            })
        }
        // this.showData()
        // this.getA()
    },
    mounted() {
         this.sentChecked();
    },
})