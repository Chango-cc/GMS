const app = new Vue({
    el: "#form_match",
    data: {
        match:{},
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
    },
    mounted() {
        console.log("local:"+this.getQueryString("id"));
        this.getData();
    }
})