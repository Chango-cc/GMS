const app=new Vue({
    el: "#table_placeNotice",
    data: {
        list:[],
        noticeTitle:"",
        noticeContent:"",
        current:1,
        size:4,
        listed: "",
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
                this.getdate()
            }
        },
        turnpagenext(){
            if (this.current !== this.totalpage){
                this.current=this.listed.current+1
                this.getdate()
            }
        },
        turnpage(local){
            this.current=local
            this.getdate()
        },
        getdate(){
            var object=this;
            $.ajax({
                url: "../place/placeToSelectNotice",
                contentType: "application/json;charset=UTF-8",
                data: {"current":this.current,"size":this.size},
                type: "get",
                success: function (result) {
                    // object.list=result;
                    object.listed=result;
                    object.totalpage=object.listed.pages;
                    object.list=result.records;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        toAddNotice(){
            var object =this
            $.ajax({
                url: "../place/placeNoticeToAdd",
                contentType: "application/json;charset=UTF-8",
                data: {"noticeTitle":this.noticeTitle,"noticeContent":this.noticeContent},
                async:false,
                type: "get",
                success: function (result) {
                    console.log("niu++++++++++"+result);
                    if(result){
                        document.getElementById("closeing").click();
                        object.getdate();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        changeNotice(noticeId,local){
            var object = this
            console.log("修改场地收费标准-------------------------------Vueed"+noticeId+"  --  "+local+"------标题--"+this.list[local].noticeTitle+"----正文-----"+this.list[local].noticeContent);
            $.ajax({
                url: "../place/placeToChangeNotice",
                contentType: "application/json;charset=UTF-8",
                data: {"noticeId":noticeId,"noticeTitle":this.list[local].noticeTitle,"noticeContent":this.list[local].noticeContent},
                async:false,
                type: "get",
                success: function (result) {
                        alert("修改成功")
                        object.list[local].noticeDate = result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        deleteNotice(noticeId,local){
            var object = this
            $.ajax({
                url: "../place/placeToDeleteNotice",
                contentType: "application/json;charset=UTF-8",
                data: {"noticeId":noticeId},
                async:false,
                type: "get",
                success: function (result) {
                    console.log("niu++++++++++"+result);
                    if(result){

                        alert("删除成功")
                        // object.list.splice(local,1)
                        object.getdate()
                    }else {
                        alert("删除失败")
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
        },
    },
    mounted() {
        this.getdate();
    },
})