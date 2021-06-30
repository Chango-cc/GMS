const cpn2=Vue.extend({
    // el: "#table_match",
    data: function () {
        return{
            // start: 0,
            pages: 1,
            page: 1,
            length: 5,
            list: [],
            typelist: [],
            type: "",
            sitution: "",
            isadd: false,
            user_id: '',
        }
    },
    methods: {
        getData(object,offset,length,type,sitution){
            $.ajax({
                url: "../equipment/queryEquipmentByEnEs",
                contentType: "application/json;charset=UTF-8",
                data: {"offset":offset,"length":length,"type":type,"sitution":sitution},
                type: "get",
                success: function (result) {
                    console.log(result);
                    object.list=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        getNum(object,type,sitution){
            $.ajax({
                url: "../equipment/queryEquipmentNum111",
                contentType: "application/json;charset=UTF-8",
                data: {"type":type,"sitution":sitution},
                type: "get",
                success: function (result) {
                    object.setPages(Math.ceil(result / 5));
                    // document.getElementById("1").classList.add("active");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })

        },
        getType(object){
            $.ajax({
                url: "../equipment/queryEquipmentEtype",
                contentType: "application/json;charset=UTF-8",
                type: "post",
                success: function (result) {
                    object.typelist=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        changeType(event){
            this.type=event.target.value;
        },
        changeSitu(event){
            this.sitution=event.target.value;
        },
        setPages(pages){
            this.pages = pages;
        },
        setActive(newIndex) {
            document.getElementById("ul_pages").childNodes[this.page + 1].classList.remove("active");
            document.getElementById("ul_pages").childNodes[newIndex + 1].classList.add("active");
            this.page = newIndex;
            this.getData(this,(this.page-1)*this.length,this.length,this.type,this.sitution);
        },
        nextPage: function () {
            if (this.page < this.pages) {
                this.start += 5;
                // this.end += 5;
                this.setActive(this.page + 1);
            }
        },
        previousPage: function () {
            if (this.page > 1) {
                this.start -= 5;
                // this.end -= 5;
                this.setActive(this.page - 1);
            }
        },
        goto: function (index) {
            this.start = 5 * (index - 1);
            // this.end = 5 * (index);
            this.setActive(index);
        },
        rent_check(eno){
            for(let i=0; i<this.list.length;i++){
                if (this.list[i].eno==eno){
                    if (this.list[i].esitution=="维修中"||this.list[i].esitution=="租用中"){
                        alert("该器材"+this.list[i].esitution+",无法租用")
                    }
                    else{
                        $("#myModal").modal()
                        this.$refs.rent_eno.value=this.list[i].eno
                        this.$refs.rent_type.value=this.list[i].etype
                    }
                }
            }
            console.log(matchApp.eList);
        },
        rent(){
            var ereservation = {
                "eno":this.$refs.rent_eno.value,
                "etype":this.$refs.rent_type.value,
                "estartrent":this.$refs.rent_stime.value,
                "eendrent":this.$refs.rent_etime.value,
                "userid":"201811701204",
            }
            console.log("console ereservation:")
            console.log(ereservation);
            var self = this;
            // $.ajax({
            //     url: "../equipment/addEreservation",
            //     contentType: "application/json;charset=UTF-8",
            //     data:JSON.stringify(ereservation),
            //     type: "post",
            //     success: function (result) {
            //         console.log(result);
            //         if (result){
            //             alert("添加租用订单成功！");
            //             self.isadd=true;
            //             if(self.isadd){
            //                 $.ajax({
            //                     url: "../equipment/updateEquipmentByEno",
            //                     contentType: "application/json;charset=UTF-8",
            //                     data: {"eno":self.$refs.rent_eno.value,"sitution":"租用中"},
            //                     type: "get",
            //                     success: function (result) {
            //                         console.log(result);
            //                         if (result){
            //                             alert("修改器材状态成功");
            //                             self.add=false
            //                             self.getNum(self,self.type,self.sitution);
            //                             self.getData(self,(self.page-1)*self.length,self.length,self.type,self.sitution);
            //                             self.getType(self);
            //                         }else alert("修改器材状态失败！");
            //                     },
            //                     error: function (XMLHttpRequest, textStatus, errorThrown) {
            //                         console.log("error message：" + XMLHttpRequest.responseText);
            //                     }
            //                 })
            //                 this.add=false
            //             }
            //         }else alert("添加租用订单失败！");
            //     },
            //     error: function (XMLHttpRequest, textStatus, errorThrown) {
            //         console.log("error message：" + XMLHttpRequest.responseText);
            //         alert("controller层失败")
            //     }
            // })
            // var eItem={"eno":self.$refs.rent_eno.value,"sitution":"租用中"};
            matchApp.ereservationList.push(ereservation);
        },
        query(){
            this.setActive(1)
            this.page=1
            this.getData(this,(this.page-1)*this.length,this.length,this.type,this.sitution);
            this.getNum(this,this.type,this.sitution);
            document.getElementById("1").classList.add("active");
        },
    },
    mounted() {
        this.getNum(this,this.type,this.sitution);
        document.getElementById("1").classList.add("active");
        this.getData(this,(this.page-1)*this.length,this.length,this.type,this.sitution);
        this.getType(this);
    },
    template:'<div id="table_match" class="table_body bg-white">\n' +
        '        <div>器材类型：\n' +
        '            <select @change="changeType($event)">\n' +
        '                <option value="">请选择</option>\n' +
        '                <option v-for="item in typelist" :value="item">{{item}}</option>\n' +
        '            </select>\n' +
        '            <select @change="changeSitu($event)">\n' +
        '                <option value="">请选择</option>\n' +
        '                <option value="在库">在库</option>\n' +
        '                <option value="租用中">租用中</option>\n' +
        '                <option value="维修中">维修中</option>\n' +
        '            </select>\n' +
        '            <div class="btn btn-primary" @click="query">查询</div>\n' +
        '        </div>\n' +
        '        <table class="table table-bordered">\n' +
        '            <thead>\n' +
        '            <tr>\n' +
        '                <th>器材编号</th>\n' +
        '                <th>类型</th>\n' +
        '                <th>购置费用</th>\n' +
        '                <th>购置时间</th>\n' +
        '                <th>当前状态</th>\n' +
        '                <th>操作</th>\n' +
        '            </tr>\n' +
        '            </thead>\n' +
        '            <tbody>\n' +
        '            <tr v-for="(item,index) in list" >\n' +
        '                <td>{{item.eno}}</td>\n' +
        '                <td>{{item.etype}}</td>\n' +
        '                <td>{{item.ecost}}￥</td>\n' +
        '                <td>{{item.epurtime}}</td>\n' +
        '                <td>{{item.esitution}}</td>\n' +
        '                <td><div class="btn btn-warning" style="margin-left: 15px" @click="rent_check(item.eno)">租用</div></td>\n' +
        '            </tr>\n' +
        '            </tbody>\n' +
        '        </table>\n' +
        '        <!-- 模态框 -->\n' +
        '        <div class="modal fade" id="myModal">\n' +
        '            <div class="modal-dialog">\n' +
        '                <div class="modal-content">\n' +
        '\n' +
        '                    <!-- 模态框头部 -->\n' +
        '                    <div class="modal-header">\n' +
        '                        <h4 class="modal-title">租用信息</h4>\n' +
        '                        <button type="button" class="close" data-dismiss="modal">&times;</button>\n' +
        '                    </div>\n' +
        '\n' +
        '                    <!-- 模态框主体 -->\n' +
        '                    <div class="modal-body">\n' +
        '                        <div class="container">\n' +
        '                            <div class="row">\n' +
        '                                <div class="col-sm-6" >器材编号：<input type="text" ref="rent_eno" readonly="readonly"></div>\n' +
        '                                <div class="col-sm-6" >器材类型：<input type="text" ref="rent_type" readonly="readonly"></div>\n' +
        '                                <div class="col-sm-6" style="margin-top: 10px;">开始租用时间：<input type="datetime-local" ref="rent_stime" ></div>\n' +
        '                                <div class="col-sm-6" style="margin-top: 10px;">结束租用时间:<input type="datetime-local" ref="rent_etime"></div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '\n' +
        '                    <!-- 模态框底部 -->\n' +
        '                    <div class="modal-footer">\n' +
        '                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="rent(this)">确定</button>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <nav aria-label="Page navigation example">\n' +
        '            <ul id="ul_pages" class="pagination justify-content-end">\n' +
        '                <li class="page-item" @click="previousPage()">\n' +
        '                    <a class="page-link" aria-label="Previous">\n' +
        '                        <span aria-hidden="true">&lt;</span>\n' +
        '                    </a>\n' +
        '                </li>\n' +
        '                <li class="page-item" v-for="index of pages" :key="index" @click="goto(index)" :id="index">\n' +
        '                    <a class="page-link">{{index}}</a>\n' +
        '                </li>\n' +
        '                <li class="page-item" @click="nextPage()">\n' +
        '                    <a class="page-link">\n' +
        '                        <span aria-hidden="true">&gt;</span>\n' +
        '                    </a>\n' +
        '                </li>\n' +
        '            </ul>\n' +
        '        </nav>\n' +
        '    </div>'
})

const matchApp=new Vue({
    el:"#form_match",
    data: {
        isValid: false,
        matchName: "",
        description: "",
        mlist: [{type: "足球", state: false},
            {type: "篮球", state: false},
            {type: "羽毛球", state: false},
            {type: "排球",state: false},
            {type: "棒球", state: false}],
        mtype:"请选择类型",
        ereservationList:[],

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
        keepListtemp:[],

    },
    methods:{
        submitInfo(){
            var match={
                "matchName":this.matchName,
                "matchDescribe":this.description,
                "matchType":this.mtype,
                "keepList":this.keepList,
                "ereservationList":this.ereservationList
            };
            $.ajax({
                url: "../match/addMatch",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(match),
                type: "post",
                success: function (result) {
                    console.log(result);
                    if (result)
                        alert("创建成功");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        indexSelect(e){
            console.log(e);
            console.log(e.target.selectedIndex); // 选择项的index索引
            console.log(e.target.value);// 选择项的value，也就是v-bind:value 的绑定值，如果换成 v-bind:value="item.id",则打印的是 选中项的id
            console.log(this.mtype);
        },

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
            console.log("hhdafka:"+msg.timeCollection[0].timelist[0]);
            $.ajax({
                url: "../place/placeKeepSelect",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(msg),
                type: "post",
                success: function (result) {
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
            this.Time = []
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
                // if (this.keepList.length!=0) {
                // }
                this.keepListtemp.push(this.list[this.choose[i]])
                console.log("选中的位置是" + this.list[this.choose[i]].placeNo);
            }
            loop1:
            for( var i = 0;i<this.keepListtemp.length;i++){
                if (this.keepList.length != 0) {
                    // var bool=true;
                    for (var j = 0; j < this.keepList.length; j++) {
                        if (this.keepList[j].placeNo == this.keepListtemp[i].placeNo
                            && this.keepList[j].placeStorey == this.keepListtemp[i].placeStorey
                            && this.keepList[j].applyDate == this.keepListtemp[i].applyDate
                            && this.keepList[j].applyPeriod == this.keepListtemp[i].applyPeriod) {
                            continue loop1;
                        }
                    }
                    this.keepList.push(this.keepListtemp[i]);
                }else {
                    this.keepList.push(this.keepListtemp[i])
                    console.log("选中的位置是" + this.keepListtemp[i].placeNo);
                }

                // }
            }
            this.keepListtemp=[];
            for(var j=0;j<this.choose.length;j++){
                document.getElementById("msgtable").getElementsByTagName("input")[this.choose[j]].checked=false;
            }
            for( var i = 0;i<this.choose.length;i++){
                this.list.splice(this.choose[i],1)
            }
            this.choose=[];
            // var match={
            //     "matchName":this.matchName,
            //     "matchDescribe":this.description,
            //     "matchType":this.mtype,
            //     "keepList":this.keepList,
            //     "ereservationList":this.ereservationList
            // };
            // $.ajax({
            //     url: "../match/addMatch",
            //     contentType: "application/json;charset=UTF-8",
            //     data: JSON.stringify(match),
            //     type: "post",
            //     success: function (result) {
            //         console.log(result);
            //     },
            //     error: function (XMLHttpRequest, textStatus, errorThrown) {
            //         console.log("error message：" + XMLHttpRequest.responseText);
            //     }
            // });
        },
        removeKeepList: function(index) {
            // this.keepList[index].show = false;
            this.keepList.splice(index, 1);
            console.log(this.keepList);
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
        removeEquipmentList(index){
            this.eList.splice(index,1);
        }
    },
    created(){
        // this.dateList = this.getDate()
        for (var i=0;i<this.getDate().length;i++){
            this.dateList.push({
                date:this.getDate()[i],
                active:false
            })
        }
    },
    mounted(){
        console.log(this.mtype);
        bootstrapValidate('#matchName', 'required:*必填项!', function (isValid) {
            matchApp.isValid = isValid;
        });
        this.sentChecked();
        // $('#form_match').bootstrapValidator({
        //     message: 'This value is not valid',
        //     feedbackIcons: {
        //         valid: 'glyphicon glyphicon-ok',
        //         invalid: 'glyphicon glyphicon-remove',
        //         validating: 'glyphicon glyphicon-refresh'
        //     },
        //     fields: {
        //         matchName: {
        //             message: 'The username is not valid',
        //             validators: {
        //                 notEmpty: {
        //                     message: 'The username is required and cannot be empty'
        //                 },
        //                 regexp: {
        //                     regexp: /^[a-zA-Z0-9_]+$/,
        //                     message: 'The username can only consist of alphabetical, number and underscore'
        //                 }
        //             }
        //         },
        //         // email: {
        //         //     validators: {
        //         //         notEmpty: {
        //         //             message: 'The email is required and cannot be empty'
        //         //         },
        //         //         emailAddress: {
        //         //             message: 'The input is not a valid email address'
        //         //         }
        //         //     }
        //         // }
        //     },
        //     function (isValid){
        //         app.isValid = isValid;
        //         console.log(isValid);
        //     }
        // });
    },
    components: {
        cpn2: cpn2
    },
})

