new Vue({
    el: "#table_match",
    data: {
        start: 0,
        pages: 1,
        page: 1,
        length: 5,
        list: [],
        typelist:[],
        type:"",
        sitution:"",
        isadd:false,
        user_id:'',
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
            // for(let i=0; i<this.list.length;i++){
            //     if (this.list[i].eno==eno){
            //         if (this.list[i].esitution=="维修中"||this.list[i].esitution=="租用中"){
            //             alert("该器材"+this.list[i].esitution+",无法租用")
            //         }
            //         else{
                        $("#myModal").modal()
                        this.$refs.rent_eno.value=this.list[i].eno
                        this.$refs.rent_type.value=this.list[i].etype
            //         }
            //     }
            // }
        },
        rent(){
            var ereservation = {
                "eno":this.$refs.rent_eno.value,
                "etype":this.$refs.rent_type.value,
                "estartrent":this.$refs.rent_stime.value,
                "eendrent":this.$refs.rent_etime.value,
                "userid":"201811701204",
            }
            var self = this;
            $.ajax({
                    url: "../equipment/addEreservation",
                    contentType: "application/json;charset=UTF-8",
                    data:JSON.stringify(ereservation),
                    type: "post",
                    success: function (result) {
                        console.log(result);
                        if (result){
                            alert("添加租用订单成功！");
                            self.isadd=true;
                            if(self.isadd){
                                $.ajax({
                                    url: "../equipment/updateEquipmentByEno",
                                    contentType: "application/json;charset=UTF-8",
                                    data: {"eno":self.$refs.rent_eno.value,"sitution":"租用中"},
                                    type: "get",
                                    success: function (result) {
                                        console.log(result);
                                        if (result){
                                            alert("修改器材状态成功");
                                            self.add=false
                                            self.getNum(self,self.type,self.sitution);
                                            self.getData(self,(self.page-1)*self.length,self.length,self.type,self.sitution);
                                            self.getType(self);
                                        }else alert("修改器材状态失败！");
                                    },
                                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                                        console.log("error message：" + XMLHttpRequest.responseText);
                                    }
                                })
                                this.add=false
                            }
                        }else alert("添加租用订单失败！");
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error message：" + XMLHttpRequest.responseText);
                        alert("controller层失败")
                    }
            })

        },
        query(){
            this.setActive(1)
            this.page=1
            this.start=0
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
})