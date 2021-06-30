const app=new Vue({
    el: "#table_placeFee",
    data: {
        list:[],
        placeType:"",
        money:"",
        feeMsg:"",
        changeMoney: "",
        current:1,
        size:4,
        listed: "",
        totalpage:""
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
                url: "../place/placeToSelectFee",
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
        toAddFee(){
            var object =this
            $.ajax({
                url: "../place/placeFeeToAdd",
                contentType: "application/json;charset=UTF-8",
                data: {"placeType":this.placeType,"money":this.money},
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
        checkPlaceType(){
            var object = this
            var msg;
            $.ajax({
                url: "../place/placeToCheckedFee",
                contentType: "application/json;charset=UTF-8",
                data: {"placeType":this.placeType},
                async:false,
                type: "get",
                success: function (result) {
                    console.log("niu++++++++++"+result);
                    if(result){
                        msg = "该场地类型已存在"
                    }else {
                        msg = ""
                        object.toAddFee();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
            this.feeMsg = msg;
            console.log("信息为+++----"+this.feeMsg)
        },
        changeFee(feestanderId,local){
            var object = this

            console.log("修改场地收费标准-------------------------------Vueed"+feestanderId+"  --  "+local);
            $.ajax({
                url: "../place/placeToChangeFee",
                contentType: "application/json;charset=UTF-8",
                data: {"feestanderId":feestanderId,"changeMoney":this.list[local].feestanderMoney},
                async:false,
                type: "get",
                success: function (result) {
                    console.log("niu++++++++++"+result);
                    if(result){
                        alert("修改成功")

                    }else {
                        alert("修改失败")
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        deleteFee(feestanderId,local){
            var object = this
            $.ajax({
                url: "../place/placeToDeleteFee",
                contentType: "application/json;charset=UTF-8",
                data: {"feestanderId":feestanderId},
                async:false,
                type: "get",
                success: function (result) {
                    console.log("niu++++++++++"+result);
                    if(result){

                        alert("删除成功")
                        // object.list.splice(local,1)
                        object.getdate();
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