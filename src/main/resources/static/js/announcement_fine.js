new Vue({
    el: "#table_announcement",
    data: {
        start: 0,
        // end: 5,
        pages: 1,
        page: 1,
        length: 5,
        list: [],
    },
    methods: {
        getData(object,offset,length){
            $.ajax({
                url: "../user/queryAnnouncementL",
                contentType: "application/json;charset=UTF-8",
                data: {},
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
        this.getData(this,this.start,this.length);
    },
})