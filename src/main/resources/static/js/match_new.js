const app=new Vue({
    el:"",
    data: {
        isValid: false,
        username: "",
        description: "",
        list: [{type: "足球", state: false},
            {type: "篮球", state: false},
            {type: "羽毛球", state: false},
            {type: "排球",state: false},
            {type: "棒球", state: false}],
    },
    methods:{
        getData(object,offset,length){
            $.ajax({
                url: "../match/addMatch",
                contentType: "application/json;charset=UTF-8",
                type: "post",
                success: function (result) {

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
    },
})