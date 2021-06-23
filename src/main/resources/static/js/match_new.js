const app=new Vue({
    el:"#form_match",
    data: {
        isValid: false,
        username: "",
        description: "",
        list: [{type: "足球", state: false},
            {type: "篮球", state: false},
            {type: "羽毛球", state: false},
            {type: "排球",state: false},
            {type: "棒球", state: false}],
        type:"请选择类型",
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
        indexSelect(e){
            console.log(e);
            console.log(e.target.selectedIndex); // 选择项的index索引
            console.log(e.target.value);// 选择项的value，也就是v-bind:value 的绑定值，如果换成 v-bind:value="item.id",则打印的是 选中项的id
            console.log(this.type);
        },
    },
    mounted(){
        console.log(this.type);
    }
})