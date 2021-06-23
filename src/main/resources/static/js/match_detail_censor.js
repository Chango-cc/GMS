const app = new Vue({
    el: "#form_match",
    data: {
        isValid: false,
        list: [],
        refereeName:"请选择",
        match:{},
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
                    object.getReferee();
                    console.log(object.match);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        getReferee() {
            const object=this;
            $.ajax({
                url: "../match/inquireRefereeByType",
                contentType: "application/json;charset=UTF-8",
                data:{"type":object.match.matchType},
                type: "get",
                success: function (result) {
                    object.list=result;
                    console.log(object.list);
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