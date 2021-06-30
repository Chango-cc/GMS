new Vue({
    el: "#table_match",
    data: {
        start: 0,
        pages: 1,
        page: 1,
        length: 5,
        list: [],
        userid:'',
        eno:'',
    },
    methods: {
        getData(object,offset,length,eno,userid){
            $.ajax({
                url: "../equipment/queryEreservationByEnUser",
                contentType: "application/json;charset=UTF-8",
                data: {"offset":offset,"length":length,"eno":eno,"userid":userid},
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
        getNum(object,eno,userid){
            $.ajax({
                url: "../equipment/queryEreservationNum",
                contentType: "application/json;charset=UTF-8",
                data: {"eno":eno,"userid":userid},
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
        setPages(pages){
            this.pages = pages;
        },
        setActive(newIndex) {
            document.getElementById("ul_pages").childNodes[this.page + 1].classList.remove("active");
            document.getElementById("ul_pages").childNodes[newIndex + 1].classList.add("active");
            this.page = newIndex;
            this.getData(this,(this.page-1)*this.length,this.length,this.eno,this.userid);
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
        recovery(eno){
            var self = this
            if (!confirm("您确定要回收选择的器材吗？")) {
                return false;
            }
            $.ajax({
                url: "../equipment/deleteEreservationByEno",
                contentType: "application/json;charset=UTF-8",
                data: {"eno": eno},
                type: "get",
                success: function (result) {
                    console.log(result);
                    if (result) {
                        alert("删除租用记录成功！！");
                        self.getNum(self, self.eno, self.userid);
                        self.getData(self, (self.page - 1) * self.length, self.length, self.eno, self.userid);
                        self.getType(self);
                    } else alert("删除租用记录失败！！");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
            $.ajax({
                url: "../equipment/updateEquipmentByEno",
                contentType: "application/json;charset=UTF-8",
                data: {"eno": eno, "sitution": "在库"},
                type: "get",
                success: function (result) {
                    console.log(result);
                    if (result) {
                        alert("修改器材状态为在库");
                        self.add = false
                        self.getNum(self, self.type, self.sitution);
                        self.getData(self, (self.page - 1) * self.length, self.length, self.eno, self.sitution);
                        self.getType(self);
                    } else alert("修改器材状态失败！");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },

        repair_check(eno){
            for(let i=0; i<this.list.length;i++){
                if (this.list[i].eno==eno){
                    $("#myModal").modal()
                    this.$refs.repair_eno.value=this.list[i].eno
                    this.$refs.repair_type.value=this.list[i].etype
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
                url: "../equipment/deleteEreservationByEno",
                contentType: "application/json;charset=UTF-8",
                data: {"eno": self.$refs.repair_eno.value},
                type: "get",
                success: function (result) {
                    console.log(result);
                    if (result) {
                        self.getNum(self, self.eno, self.userid);
                        self.getData(self, (self.page - 1) * self.length, self.length, self.eno, self.userid);
                        self.getType(self);
                    } else alert("删除租用记录失败！！");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })

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
            this.getData(this,(this.page-1)*this.length,this.length,this.eno,this.userid);
            this.getNum(this,this.eno,this.userid);
            document.getElementById("1").classList.add("active");
        },
    },
    mounted() {
        this.getNum(this,this.eno,this.userid);
        document.getElementById("1").classList.add("active");
        this.getData(this,(this.page-1)*this.length,this.length,this.eno,this.userid);
        // this.getType(this);
    },
})