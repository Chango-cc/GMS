new Vue({
    el: "#table_match",
    data: {
        start: 0,
        // end: 5,
        pages: 1,
        page: 1,
        length: 5,
        list: [],
        typelist:[],
        type:"",
        sitution:"",
        isadd:false,
        user_id:'',
        eno:'',
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
        remove(eno){
            var self = this
            for(let i=0; i<this.list.length;i++){
                if (this.list[i].eno==eno){
                    if (this.list[i].esitution=="租用中"){
                        alert("该器材"+this.list[i].esitution+",无法删除")
                    }
                    else{
                        if (!confirm("您确定要删除选择的数据吗？")) {
                            return false;
                        }
                        $.ajax({
                            url: "../equipment/deleteEquipmentByEno",
                            contentType: "application/json;charset=UTF-8",
                            data: {"eno": eno},
                            type: "get",
                            success: function (result) {
                                console.log(result);
                                if (result) {
                                    alert("删除器材成功！！");
                                } else alert("删除器材失败！！");
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                console.log("error message：" + XMLHttpRequest.responseText);
                            }
                        })
                        $.ajax({
                            url: "../equipment/deleteErepairByEno",
                            contentType: "application/json;charset=UTF-8",
                            data: {"eno": eno},
                            type: "get",
                            success: function (result) {
                                console.log(result);
                                if (result) {
                                    alert("删除维修中的器材成功！！");
                                    self.getNum(self, self.eno, self.userid);
                                    self.getData(self, (self.page - 1) * self.length, self.length, self.eno, self.userid);
                                    self.getType(self);
                                } else alert("删除维修中的器材失败！！");
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                console.log("error message：" + XMLHttpRequest.responseText);
                            }
                        })

                    }
                }
            }

        },
        repair_check(eno){
            for(let i=0; i<this.list.length;i++){
                if (this.list[i].eno==eno){
                    if (this.list[i].esitution=="租用中"||this.list[i].esitution=="维修中"){
                        alert("该器材正处于"+this.list[i].esitution)
                    }
                    else{
                        $("#myModal").modal()
                        this.$refs.repair_eno.value=this.list[i].eno
                        this.$refs.repair_type.value=this.list[i].etype
                    }
                }
            }
        },
        repair() {
            var self = this;
            var erepair = {
                "eno": this.$refs.repair_eno.value,
                "etype": this.$refs.repair_type.value,
                "erepaircost": this.$refs.repair_cost.value,
                "erepairremark": this.$refs.repair_remark.value,
            }
                $.ajax({
                    url: "../equipment/addErepair",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(erepair),
                    type: "post",
                    success: function (result) {
                        if (result) {
                            alert("添加维修订单成功！")
                            $.ajax({
                                url: "../equipment/updateEquipmentByEno",
                                contentType: "application/json;charset=UTF-8",
                                data: {"eno": self.$refs.repair_eno.value, "sitution": "维修中"},
                                type: "get",
                                success: function (result) {
                                    console.log(result);
                                    if (result) {
                                        alert("修改器材状态成功");
                                        self.$refs.repair_cost.value=''
                                        self.$refs.repair_remark.value=''
                                        self.getNum(self, self.type, self.sitution);
                                        self.getData(self, (self.page - 1) * self.length, self.length, self.type, self.sitution);
                                        self.getType(self);
                                    } else alert("修改器材状态失败！");
                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    console.log("error message：" + XMLHttpRequest.responseText);
                                }
                            })
                        } else {
                            alert("维修器材失败！")
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error message：" + XMLHttpRequest.responseText);
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