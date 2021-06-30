new Vue({
    el: "#table_match",
    data: {
        start: 0,
        pages: 1,
        page: 1,
        length: 5,
        list: [],
        eno:'',
        type:'',
    },
    methods: {
        getData(object,offset,length,eno,type){
            $.ajax({
                url: "../equipment/queryErepairByEnEt",
                contentType: "application/json;charset=UTF-8",
                data: {"offset":offset,"length":length,"eno":eno,"type":type},
                type: "get",
                success: function (result) {
                    console.log(result);
                    object.list=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                    alert("getData失败")
                }
            })
        },
        getNum(object,eno,type){
            $.ajax({
                url: "../equipment/queryErepairtNum",
                contentType: "application/json;charset=UTF-8",
                data: {"eno":eno,"type":type},
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
            this.getData(this,(this.page-1)*this.length,this.length,this.eno,this.type);
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
        goku(eno){
            var self = this;
            if (!confirm("您确定要入库选择的器材吗？")) {
                return false;
            }
            $.ajax({
                url: "../equipment/deleteErepairByEno",
                contentType: "application/json;charset=UTF-8",
                data: {"eno": eno},
                type: "get",
                success: function (result) {
                    console.log(result);
                    if (result) {
                        alert("删除维修记录成功！！");
                        self.getNum(self, self.eno, self.type);
                        self.getData(self, (self.page - 1) * self.length, self.length, self.eno, self.type);
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
                    } else alert("修改器材状态失败！");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        dele(eno){
            var self = this
            if (!confirm("您确定要删除选择的器材吗？")) {
                return false;
            }
            $.ajax({
                url: "../equipment/deleteErepairByEno",
                contentType: "application/json;charset=UTF-8",
                data: {"eno": eno},
                type: "get",
                success: function (result) {
                    console.log(result);
                    if (result) {
                        alert("删除维修记录成功！！");
                        self.getNum(self, self.eno, self.type);
                        self.getData(self, (self.page - 1) * self.length, self.length, self.eno, self.type);
                        self.getType(self);
                    } else alert("删除租用记录失败！！");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
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

        },

        query(){
            this.setActive(1)
            this.page=1
            this.start=0
            this.getData(this,(this.page-1)*this.length,this.length,this.eno,this.type);
            this.getNum(this,this.eno,this.type);
            document.getElementById("1").classList.add("active");
        },
    },
    mounted() {
        this.getNum(this,this.eno,this.type);
        document.getElementById("1").classList.add("active");
        this.getData(this,(this.page-1)*this.length,this.length,this.eno,this.type);
        this.getType(this);
    },
})