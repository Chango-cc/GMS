const app = new Vue({
    el: "#form_referee",
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
    methods: {
        checkInfo() {

        },
        CheckItem: function (item) {
            item.state = !item.state;
        },
        submitInfo: function () {
            console.log(this.isValid);
            if (this.isValid) {
                var type = "";
                for (i = 0; i < this.list.length; i++) {
                    if (this.list[i].state) {
                        type += (this.list[i].type + ",");
                    }
                }
                type = type.substring(0, type.length - 1);
                var referee = {
                    "refereeName": this.username,
                    "refereeDescribe": this.description,
                    "refereePhoto": type
                }
                console.log(referee)
                $.ajax({
                    url: "../match/addReferee",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(referee),
                    type: "post",
                    success: function (result) {
                        console.log(result);
                        if (result) {
                            alert("添加成功！");
                            document.getElementById("form_referee").reset();
                        } else alert("添加失败！");
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error message：" + XMLHttpRequest.responseText);
                    }
                })
            } else console.log("can't submit!");
        }
    },
    mounted() {
        bootstrapValidate('#userName', 'required:*必填项!', function (isValid) {
            app.isValid = isValid;
        });
    }
});
