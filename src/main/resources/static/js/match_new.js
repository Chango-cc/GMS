const app=new Vue({
    el:"#form_match",
    data: {
        isValid: false,
        matchName: "",
        description: "",
        list: [{type: "足球", state: false},
            {type: "篮球", state: false},
            {type: "羽毛球", state: false},
            {type: "排球",state: false},
            {type: "棒球", state: false}],
        type:"请选择类型",
    },
    methods:{
        submitInfo(){
            var match={
                "matchName":this.matchName,
                "matchDescribe":this.description,
                "matchType":this.type,
            };
            $.ajax({
                url: "../match/addMatch",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(match),
                type: "post",
                success: function (result) {
                    console.log(result);
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
        bootstrapValidate('#matchName', 'required:*必填项!', function (isValid) {
            app.isValid = isValid;
        });
        // $('#form_match').bootstrapValidator({
        //     message: 'This value is not valid',
        //     feedbackIcons: {
        //         valid: 'glyphicon glyphicon-ok',
        //         invalid: 'glyphicon glyphicon-remove',
        //         validating: 'glyphicon glyphicon-refresh'
        //     },
        //     fields: {
        //         matchName: {
        //             message: 'The username is not valid',
        //             validators: {
        //                 notEmpty: {
        //                     message: 'The username is required and cannot be empty'
        //                 },
        //                 regexp: {
        //                     regexp: /^[a-zA-Z0-9_]+$/,
        //                     message: 'The username can only consist of alphabetical, number and underscore'
        //                 }
        //             }
        //         },
        //         // email: {
        //         //     validators: {
        //         //         notEmpty: {
        //         //             message: 'The email is required and cannot be empty'
        //         //         },
        //         //         emailAddress: {
        //         //             message: 'The input is not a valid email address'
        //         //         }
        //         //     }
        //         // }
        //     },
        //     function (isValid){
        //         app.isValid = isValid;
        //         console.log(isValid);
        //     }
        // });
    }
})