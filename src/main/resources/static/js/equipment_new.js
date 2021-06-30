new Vue({
    el:"#equiment_new",
    data:{
        eno:'',
        etype:'',
        ecost:'',
        epurtime:'',
        esitution:'在库',
        typelist:[],
    },
    methods:{
        getType(object){
            $.ajax({
                url: "../equipment/queryEquipmentEtype",
                contentType: "application/json;charset=UTF-8",
                type: "post",
                success: function (result) {
                    object.typelist=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        changeType(event){
            this.etype=event.target.value;
        },
        addequipment(){
            var self = this;
            if (this.eno.length<5||this.eno.length>5){
                alert("请输入五位数字！")
            }else{
                var equipment= {
                    "eno": this.eno,
                    "etype": this.etype,
                    "ecost": this.ecost,
                    "epurtime": this.epurtime,
                    "esitution": this.esitution,
                }
                $.ajax({
                    url: "../equipment/addEquipment",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(equipment),
                    type: "post",
                    success: function (result) {
                        console.log(result);
                        if (result){
                            alert("添加器材成功！");
                            self.eno=''
                            self.etype=''
                            self.ecost=''
                            self.epurtime=''
                        }else alert("该器材编号已存在，请重新输入！");
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("添加失败！");
                        console.log("error message：" + XMLHttpRequest.responseText);
                    }
                })
            }
            }

    },
    mounted(){
        this.getType(this);
    }
})