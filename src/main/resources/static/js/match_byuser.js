new Vue({
    el: "#table_match",
    data: {
        length: 5,
        pages: 1,
        page: 1,
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
        list: [],
    },
    methods: {
        getData(object,offset,length){
            console.log("offset:"+offset+"\nlength:"+length);
            $.ajax({
                url: "../match/queryMatchByUser",
                contentType: "application/json;charset=UTF-8",
                data: {"offset":offset,"length":length},
                type: "get",
                success: function (result) {
                    object.list=result;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        getNum(object){
            $.ajax({
                url: "../match/queryMatchNumByUser",
                contentType: "application/json;charset=UTF-8",
                type: "get",
                success: function (result) {
                    object.setPages(Math.ceil(result / object.length));
                    if (object.pages > 0)
                        document.getElementById("page1").classList.add("active");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        setPages(pages){
            this.pages = pages;
        },
        goto(newIndex) {
            document.getElementById("ul_pages").childNodes[this.page + 1].classList.remove("active");
            document.getElementById("ul_pages").childNodes[newIndex + 1].classList.add("active");
            this.page = newIndex;
            this.getData(this,(this.page-1)*this.length,this.length);
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
        // goto: function (index) {
        //     this.setActive(index);
        // }
    },
    mounted() {
        this.getNum(this);
        this.getData(this,(this.page-1)*this.length,this.length);
    },
})