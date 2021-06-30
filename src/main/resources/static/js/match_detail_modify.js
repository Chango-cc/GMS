const app = new Vue({
    el: "#form_match",
    data: {
        isValid: false,
        list: [{type: "足球", state: false},
            {type: "篮球", state: false},
            {type: "羽毛球", state: false},
            {type: "排球", state: false},
            {type: "棒球", state: false}],
        match:{},
        type:""
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
        getQueryString(name) {
            let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            let r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return decodeURIComponent(r[2]);
            }
            return null;
        },
        getData() {
            const object=this;
            $.ajax({
                url: "../match/queryMatchById",
                contentType: "application/json;charset=UTF-8",
                data:{"id":object.getQueryString("id")},
                type: "get",
                success: function (result) {
                    object.match=result;
                    console.log(object.match);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        submitInfo() {
            const object=this;
            $.ajax({
                url: "../match/updateMatch",
                contentType: "application/json;charset=UTF-8",
                data:{"match":object.match},
                type: "post",
                success: function (result) {
                    console.log(result);
                    alert("提交成功");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        deleteConfirm(){
            var result=confirm("are you sure delete match 确定要取消吗");
            console.log(this.match);
            if (result){
                this.cancelMatch();
            }else {
                console.log("取消操作");
                console.log(this.match);
            }
        },
        cancelMatch(){
            const object=this;
            const id=this.match.matchId;
            $.ajax({
                url: "../match/deleteMatch",
                contentType: "application/json;charset=UTF-8",
                data:{"id":id},
                type: "get",
                success: function (result) {
                    console.log(result);
                    if (result) {
                        alert("取消成功");
                        object.match=null;
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        }
    },
    mounted() {
        console.log("local:"+this.getQueryString("id"));
        this.getData();
    }
})