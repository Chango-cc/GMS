new Vue({
    el: "#table_match",
    data: {
        length: 5,
        pages: 1,
        page: 1,
        list: [],
        checkboxes:[
            {id:"checkboxes_1",name:"足球",checked: true},
            {id:"checkboxes_2",name:"篮球",checked: true},
            {id:"checkboxes_3",name:"羽毛球",checked: true},
            {id:"checkboxes_4",name:"排球",checked: true},
            {id:"checkboxes_5",name:"棒球",checked: true},
        ],
        radios:[
            {id:"radio_1",name:"all",checked: false},
            {id:"radio_2",name:"待审核",checked: false},
            {id:"radio_3",name:"已审核",checked: false},
            {id:"radio_4",name:"已结束",checked: false},
        ],
        picked:"all",
    },
    methods: {
        getData(){
            const object = this;
            var str_type="";
            var type=new Array();
            for (const checkbox of object.checkboxes) {
                console.log("checked:"+checkbox.checked);
                if (checkbox.checked) {
                    str_type += (checkbox.name +",");
                }
            }
            str_type = str_type.substring(0, str_type.length - 1);
            console.log("str_type:"+str_type);
            console.log("type[]:"+type);
            $.ajax({
                url: "../match/queryMatchByCondition",
                contentType: "application/json;charset=UTF-8",
                data: {"offset":(this.page-1)*this.length,"length":object.length,"status":this.picked,"type":str_type},
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
        getNum(){
            const object = this;
            var str_type="";
            for (const checkbox of object.checkboxes) {
                if (checkbox.checked)
                    str_type += (checkbox.name +",");
            }
            str_type = str_type.substring(0, str_type.length - 1);
            $.ajax({
                url: "../match/queryMatchNumByCondition",
                contentType: "application/json;charset=UTF-8",
                data: {"status":this.picked,"type":str_type},
                type: "get",
                success: function (result) {
                    object.setPages(Math.ceil(result / object.length));
                    if (object.pages > 0) {
                        document.getElementById("page1").classList.add("active");
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        setPages(pages){
            this.pages = pages;
        },
        nextPage: function () {
            if (this.page < this.pages) {
                this.goto(this.page + 1);
            }
        },
        previousPage: function () {
            if (this.page > 1) {
                this.goto(this.page - 1);
            }
        },
        goto: function (index) {
            document.getElementById("ul_pages").childNodes[this.page + 1].classList.remove("active");
            document.getElementById("ul_pages").childNodes[index + 1].classList.add("active");
            this.page = index;
            this.getData();
        },
        getDataByCondition:function (){
            this.goto(1);
            this.getNum();
        },
        reset:function (){
            this.picked="all"
            for (const checkbox of this.checkboxes) {
                checkbox.checked=true;
            }
        },
    },
    mounted() {
        this.getNum();
        this.getData();
    },
})