$(function () {
    // $(".search_btn3").click(function(){
    //     let id = $("#search_keyword").val();
    //     if(id == "" || id == null || id == undefined) {
    //         alert("지역명을 입력해주세요");
    //         return;
    //     }
    // });
    $("#search_btn1").change(function () {
        //let region = $("#search_btn1").find("option:selected").val();
        searchContent(region);
        
    });
    // $("#search_btn1 option:first-child").prop("selected", false);
   
    //검색
    $(".search_btn3").click(function (){
        let search = $("#search_btn3").val();
        let search_all = $("#search_btn1").find("option:selected").val() + " " + $("#search_keyword").val();
        searchContent(search_all);
    });
    
    getCityRegions();
    searchContent();

    function getCityRegions() {

        // $(".search_btn1").prop("disabled", false);
        // $(".search_btn1").html("");

        $.ajax({
            type: "get",
            url: "/api/city",
            success: function (r) {
                $("#search_btn1").html("");
                console.log(r);
                for (let i = 0; i < r.length; i++) {
                    $("#search_btn1").append('<option value="' + r[i] + '">' + r[i] + '</option>');
                }

            }
        })
    }
    function searchContent(search) {
        // 검색
        //console.log(search)
        let keyword = $("#search_keyword").val();
        if (keyword == undefined || keyword == null) keyword="";
        console.log(search);

        $.ajax({
            type: "get",
            url: "/api/contentRealInfo?search="+search,
            success: function (r) {
                console.log(r);

                //let dt = new Array();
                let address = new Array();
                let lane = new Array();
                let content = new Array();
                // let latitude = new Array();
                // let longitude = new Array();

                $(".real_information").html("");

                for (let i = 0; i < 4; i++) {
                    let tag = "<tbody class='real_information'></tbody>";
                    $(".real_information_tbl").append(tag);
                }
 
                for (let i = 0; i < r.contentList.length; i++) {
                    //let a = r.contentList[i].diff;
                    let b = r.contentList[i].addressJibun;
                    let c = r.contentList[i].incidentTitle;
                    let d = r.contentList[i].lane;
                    // let e = r.contentList[i].locationDataX;
                    // let f = r.contentList[i].locationDataY;

                    //dt.push(a);
                    address.push(b);
                    content.push(c);
                    lane.push(d);
                    // latitude.push(e);
                    // longitude.push(f);

                    console.log(Math.floor(i/3));

                    let page = Math.floor(i/3);
                    let tag =
                        '<tr>' +
                        // '<td>' + r.contentList[i].diff + '</td>' +
                        '<td>' + r.contentList[i].addressJibun + '</td>' +
                        '<td>' + r.contentList[i].incidentTitle + '</td>' +
                        '<td>' + r.contentList[i].lane + '</td>' +
                        +'</tr>'
                    $(".real_information").eq(page).append(tag);
                }
                
                $(".real_information").eq(0).addClass("active");        
            }
        });

        // $("#real_traffic_next").click(function () {
        //     let currentPage = Number($(".current").html());
        //     currentPage++;
        //     if (currentPage > 4) currentPage = 4;
        //     $(".current").html(currentPage);
        //     $(".real_information").removeClass("active");
        //     $(".real_information").eq(currentPage - 1).addClass("active");
        // })

        // $("#real_traffic_prev").click(function () {
        //     let currentPage = Number($(".current").html());
        //     currentPage--;
        //     if (currentPage < 1) currentPage = 1;
        //     $(".current").html(currentPage);
        //     $(".real_information").removeClass("active");
        //     $(".real_information").eq(currentPage - 1).addClass("active");
        // })
    }   
})