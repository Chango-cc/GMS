const app=new Vue({
    el: "#table_place",
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
        place:{
            placeStorey:"",
            placeType:"",
            placeState:"",
            placeId:""
        }
    },
    methods: {
        getData(object,offset,length){
            $.ajax({
                url: "../place/queryPlace",
                contentType: "application/json;charset=UTF-8",
                data: {"offset":offset,"length":length},
                type: "post",
                success: function (result) {
                    object.list=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
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
            // var list;
            $.ajax({
                url: "../place/queryPlaceByChecked",
                contentType: "application/json;charset=UTF-8",
                data: {"storey":checkedFirst,"type":checkedSecond},
                type: "get",
                success: function (result) {
                    object.list=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
            // this.list=list;
            console.log("数据筛选成功");
        },
        // sentChecked(){
        //     this.sentCheckedHelp(this);
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

        placeEdit(placeId){
            this.checkedIdleft=placeId
        },
        placeDelete(placeId){
            var object=this;
                $.ajax({
                    url: "../place/placeToDelete",
                    contentType: "application/json;charset=UTF-8",
                    data: {"placeId":placeId},
                    type: "get",
                    success: function (result) {
                        object.sentChecked();
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error message：" + XMLHttpRequest.responseText);
                    }
                })
        },
        // placeDelete(placeId){
        //     this.placeDeleteHelp(this,placeId);
        // },
        checkPlaceNo:function(e){
            var msg;
            $.ajax({
                url: "../place/placeToChecked",
                contentType: "application/json;charset=UTF-8",
                data: {"placeNo":this.placeNo},
                async:false,
                type: "get",
                success: function (result) {
                    console.log("niu++++++++++"+result);
                    if(result){
                        msg = "该场地已存在，场地编号重复"
                        e.preventDefault()
                    }else {
                        msg = ""
                        return true
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
            this.placemsg = msg;
        },
        placeUdate(){
            var object=this;
            this.place.placeId = this.checkedIdleft
            $.ajax({
                url: "../place/placeUpdate",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(this.place),
                async:false,
                type: "post",
                success: function (result) {

                    document.getElementById("close").click();
                    for(var i=0;i<object.list.length;i++){
                        if (object.list[i].placeId == object.checkedIdleft){

                            object.list[i].placeStorey=object.place.placeStorey,
                                object.list[i].placeType=object.place.placeType,
                                object.list[i].placeState=object.place.placeState
                        }
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
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
        }
    },
    mounted() {
        this.getData(this,this.start,this.length);
    },
})