new Vue({
    el: "#sidebar_menu",

    data: {
        parentMenu: [
            { pid: 1, p_name: "用户管理", childMenu: [{ cid: 1, c_name: "a", url: "#" }, { cid: 2, c_name: "b", url: "#" }, { cid: 3, c_name: "c", url: "#" }, { cid: 4, c_name: "d", url: "#" }] },
            { pid: 2, p_name: "场地管理", childMenu: [{ cid: 1, c_name: "a", url: "#" }, { cid: 2, c_name: "b", url: "#" }, { cid: 3, c_name: "c", url: "#" }, { cid: 4, c_name: "d", url: "#" }] },
            { pid: 3, p_name: "器材管理", childMenu: [{ cid: 1, c_name: "a", url: "#" }, { cid: 2, c_name: "b", url: "#" }, { cid: 3, c_name: "c", url: "#" }, { cid: 4, c_name: "d", url: "#" }] },
            { pid: 4, p_name: "赛事管理", childMenu: [{ cid: 1, c_name: "a", url: "#" }, { cid: 2, c_name: "b", url: "#" }, { cid: 3, c_name: "c", url: "#" }, { cid: 4, c_name: "d", url: "#" }] },
        ],
        menu:[],
    },
    methods: {
        getMenu(object){
            $.ajax({
                url: "../match/getMenu",
                contentType: "application/json;charset=UTF-8",
                type: "post",
                success: function (result) {
                    object.menu=result;
                    console.log(object.menu);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
    },
    mounted(){
        this.getMenu(this);
    }
});