new Vue({
    el: "#table_user",
    data: {
        start: 0,
        // end: 5,
        pages: 1,
        page: 1,
        length: 5,
        list: [],
        modalid:"",
        user_position: "请选择用户身份",
        user_name: "",
        userindex1:1,
        userindex2:-1,
        userid:0,
        updateUserName:"",
        updateUserPosition:"",
        updateUserTel:"",
    },
    methods: {
        getData(object,offset,length){
            $.ajax({
                url: "../user/queryUserL",
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
        ischecked(index,id){
                this.userindex1=index;
                console.log("index1：" + index);
                console.log("userindex1：" + this.userindex1);
        },
        dochecked(index,id){
            this.userid=id;
            this.userindex2=index;
            this.modalid="";
            if(this.userindex1==this.userindex2){
                this.modalid="#myModal4_update"
                console.log("??????：" +this.modalid);
            }
            console.log("index2：" + index);
            console.log("userindex2：" + this.userindex2);
        },
        updateUser(){
            var object=this;
            $.ajax({
                url: "../user/updateUser",
                contentType: "application/json;charset=UTF-8",
                data: {"id":this.userid,"updateUserName":this.updateUserName,"updateUserPosition":this.updateUserPosition,"updateUserTel":this.updateUserTel},
                type: "get",
                success: function (result) {
                    for(let i=0;i<this.list.length;i++){
                        if(this.list[i].id==this.userid){
                            this.list[i].user_name=this.updateUserName;
                            this.list[i].user_position=this.updateUserPosition;
                            this.list[i].user_tel=this.updateUserTel;
                        }
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        checkUser(object){
            var checkFirst;
            var checkSecond;
            if(this.user_position=="请选择用户身份"||this.user_position=="全部"){
                checkFirst="";
            }else{
                checkFirst=this.user_position;
            };
            if(this.user_name=="请输入用户名字"||this.user_name==""){
                checkSecond="";
            }else{
                checkSecond=this.user_name;
            };
            $.ajax({
                url: "../user/checkUserL",
                contentType: "application/json;charset=UTF-8",
                data: {"user_position":checkFirst,"user_name":checkSecond},
                type: "get",
                success: function (result) {
                    object.list=result;
                    console.log(" message：" + object.list);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        checkUserHelp(){
            this.checkUser(this);//这样做的目的是给上面的这个函数传入这个Vue的object（也就是这里的this，但不能在其他页面直接调用上面的方法，因为不能用其他页面里的 元素（例如div）的 this）
        },

        getupdateUser(index){
            this.updateuser = this.list[index].user_name
            // alert("name:"+this.updateuser)
            // $.ajax({
            //     url: "../user/getupdateUser",
            //     contentType: "application/json;charset=UTF-8",
            //     // data:{"index":index, "list":JSON.stringify(this.list)},
            //     type: "post",
            //     success: function (result) {
            //         object.updateuser=result;
            //     },
            //     error: function (XMLHttpRequest, textStatus, errorThrown) {
            //         console.log("error message：" + XMLHttpRequest.responseText);
            //     }
            // })
        },
        // getupdateUserHelp(index){
        //     this.getupdateUser(this,index);
        //     alert("name:"+this.updateuser.user_name);
        // },

        delUser(object,index){
            $.ajax({
                url: "../user/deleteUser",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(this.list[index]),//传给controller获取
                type: "post",
                success: function (result) {
                    object.list=result;
                    // console.log("刷新了："+object.list);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        delUsertoObject(index){
            this.userindex2=index;
            if(this.userindex1==this.userindex2){
                this.delUser(this,index);//这样做的目的是给上面的这个函数传入这个Vue的object（也就是这里的this，但不能在其他页面直接调用上面的方法，因为不能用其他页面的this）
            }
         },

        refreshList(object){
            $.ajax({
                url: "../user/refreshList",
                contentType: "application/json;charset=UTF-8",
                // data: {"offset":offset,"length":length},
                type: "get",
                success: function (result) {
                    object.list=result;
                    console.log("refreshList：" + this.list);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
            console.log("refreshList()：" + "执行wan了");
        },
        refreshListHelp(){
            this.refreshList(this);//这样做的目的是给上面的这个函数传入这个Vue的object（也就是这里的this，但不能在其他页面直接调用上面的方法，因为不能用其他页面的this）
        },

        getNum(object){
            $.ajax({
                url: "../user/queryUserNum",
                contentType: "application/json;charset=UTF-8",
                type: "post",
                success: function (result) {
                    object.setPages(Math.ceil(result / 5));
                    document.getElementById("1").classList.add("active");
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
        this.getNum(this);
        this.getData(this,this.start,this.length);
    },
})