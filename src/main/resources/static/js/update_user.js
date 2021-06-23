const app=new Vue({
    el: "#table_user_update",
    data: {
        list: [],
        user_position: "请选择用户身份",
        user_name: "",
        updateuser:"",
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

        getupdateUser(object,index){
            // this.updateuser = this.list[index].user_name
            // alert("name:"+this.updateuser)
            $.ajax({
                url: "../user/getupdateUser",
                contentType: "application/json;charset=UTF-8",
                // data:{"index":index, "list":JSON.stringify(this.list)},
                type: "post",
                success: function (result) {
                    object.updateuser=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        getupdateUserHelp(index){
            this.getupdateUser(this,index);
            alert("name:"+this.updateuser.user_name);
        },
    },
    mounted() {
        this.getNum(this);
        this.getData(this,this.start,this.length);
    },
})