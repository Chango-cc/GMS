new Vue({
    el: "#table_referee",
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
                url: "../match/queryRefereeL",
                contentType: "application/json;charset=UTF-8",
                data: {"offset":offset,"length":length},
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
            const object=this;
            $.ajax({
                url: "../match/queryRefereeNum",
                contentType: "application/json;charset=UTF-8",
                type: "get",
                success: function (result) {
                    object.setPages(Math.ceil(result / 5));
                    document.getElementById("1").classList.add("active");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error message：" + XMLHttpRequest.responseText);
                }
            })
        },
        setPages(pages){
            this.pages = pages;
        },
        setActive(newIndex) {
            document.getElementById("ul_pages").childNodes[this.page + 1].classList.remove("active");
            document.getElementById("ul_pages").childNodes[newIndex + 1].classList.add("active");
            this.page = newIndex;
            this.getData(this,(this.page-1)*this.length,this.length);
        },
        nextPage: function () {
            if (this.page < this.pages) {
                this.start += 5;
                // this.end += 5;
                this.setActive(this.page + 1);
                // this.getData(this,this.start,this.length);
            }
        },
        previousPage: function () {
            if (this.page > 1) {
                this.start -= 5;
                // this.end -= 5;
                this.setActive(this.page - 1);
                // this.getData(this,this.start,this.length);
            }
        },
        goto: function (index) {
            this.start = 5 * (index - 1);
            // this.end = 5 * (index);
            this.setActive(index);
            // this.getData(this,this.start,this.length);
        }
    },
    mounted() {
        this.getNum();
        this.getData(this,(this.page-1)*this.length,this.length);
    },
})