new Vue({
    el: "#table_match",
    data: {
        start: 0,
        pages: 1,
        page: 1,
        length: 5,
        list: [],
        userid:'201811701204',
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
        cancel(eno){
            var self = this
            if (!confirm("您确定要取消预约吗？")) {
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
                    } else alert("修改器材状态失败！");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
    },
    mounted() {
        this.getNum(this,this.eno,this.userid);
        document.getElementById("1").classList.add("active");
        this.getData(this,(this.page-1)*this.length,this.length,this.eno,this.userid);
        // this.getType(this);
    },
})