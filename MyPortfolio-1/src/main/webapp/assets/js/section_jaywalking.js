$(function () {
    // 1. 지도를 먼저 미리 생성하고
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 4 // 지도의 확대 레벨
    };  

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    var circles = new Array(); // 2. 원들을 저장할 배열을 만들어놓고

    $("#main_region").change(function () {
        let region = $("#main_region").find("option:selected").val();
        
        getJaywalkingSigungu(region)
    });
    $("#sub_region").change(function () {
        let region = $("#sub_region").find("option:selected").val()
        let region_all = $("#main_region").find("option:selected").val() + " " + $("#sub_region").find("option:selected").val()
        getJaywalkingAccidentAddress(region_all);
    });

    function regionMaker() {

    }

    getSectionJaywalkingRegions();
    getJaywalkingSigungu("서울특별시");
    getJaywalkingAccidentAddress();

    function getSectionJaywalkingRegions(region) {

        $("#sub_region").prop("disabled", false)
        $("#sub_region").html("");

        $.ajax({
            type: "get",
            url: "/api/section_jaywalking/regions",
            success: function (r) {
                $("#main_region").html("");
                console.log(r);
                for (let i = 0; i < r.length; i++) {
                    $("#main_region").append('<option value="' + r[i] + '">' + r[i] + '</option>');
                }

            }
        })
    }

    function getJaywalkingSigungu(spot) {
        $.ajax({
            type: "get",
            url: "/api/section_jaywalking/sigungu?spot=" + spot,
            success: function (r) {
                $("#sub_region").html("");
                console.log(r);
                for (let i = 0; i < r.length; i++) {
                    $("#sub_region").append('<option value="' + r[i] + '">' + r[i] + '</option>');
                }
            }
        })
    }

    function getJaywalkingAccidentAddress(selectbox) {
        $.ajax({
            type: "get",
            url: "/api/section_jaywalking/address?selectbox=" + selectbox,
            success: function (r) {
                console.log(r);

                let address = new Array();
                let occ_cnt = new Array();
                let latitude = new Array();
                let longitude = new Array();
                let select = new Array();

                $(".jaywalking_accident").html("");

                for (let i = 0; i < 4; i++) {
                    let tag = "<tbody class='jaywalking_accident'></tbody>";
                    $(".jaywalking_information").append(tag);
                }

                for (let i = 0; i < r.jaywalkingList.length; i++) {
                    let add = r.jaywalkingList[i].spot_nm;
                    let occ = r.jaywalkingList[i].occrrnc_cnt;
                    let la = r.jaywalkingList[i].la_crd;
                    let lo = r.jaywalkingList[i].lo_crd;
                    let se = r.jaywalkingList[i].selectbox;

                    address.push(add);
                    occ_cnt.push(occ);
                    latitude.push(la);
                    longitude.push(lo);
                    select.push(se);

                    console.log(Math.floor(i/3));

                    let page = Math.floor(i/3);
                    let tag =
                        '<tr>' +
                        '<td>' + r.jaywalkingList[i].spot_nm + '</td>' +
                        '<td>' + r.jaywalkingList[i].occrrnc_cnt + '</td>' +
                        +'</tr>'
                    $(".jaywalking_accident").eq(page).append(tag);
                }
                $(".jaywalking_accident").eq(0).addClass("active");

                $("#real_traffic_next").click(function () {
                    let currentPage = Number($(".current").html());
                    currentPage++;
                    if (currentPage > 4) currentPage = 4;
                    $(".current").html(currentPage);
                    $(".jaywalking_accident").removeClass("active");
                    $(".jaywalking_accident").eq(currentPage - 1).addClass("active");
                })

                $("#real_traffic_prev").click(function () {
                    let currentPage = Number($(".current").html());
                    currentPage--;
                    if (currentPage < 1) currentPage = 1;
                    $(".current").html(currentPage);
                    $(".jaywalking_accident").removeClass("active");
                    $(".jaywalking_accident").eq(currentPage - 1).addClass("active");
                })

                // 3. 이전에 그려져있던 원들을 삭제하고
                removeCircle();
                for(let i=0; i<r.jaywalkingList.length; i++) {
                    // 4. 반복문 안에서 circle객체를 생성하고
                    // 지도에 표시할 원을 생성합니다
                    var circle = new kakao.maps.Circle({
                        center : new kakao.maps.LatLng(r.jaywalkingList[i].la_crd, r.jaywalkingList[i].lo_crd),  // 원의 중심좌표 입니다 
                        radius: 100, // 미터 단위의 원의 반지름입니다 
                        strokeWeight: 3, // 선의 두께입니다 
                        strokeColor: '#ff2222', // 선의 색깔입니다
                        strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                        strokeStyle: 'solid', // 선의 스타일 입니다
                        fillColor: '#ff7777', // 채우기 색깔입니다
                        fillOpacity: 0.7  // 채우기 불투명도 입니다   
                    });
                    // 5. 마지막에 그려지는 원을 지도의 중심점으로 세팅
                    if(i == r.jaywalkingList.length - 1) {
                        setCenter(r.jaywalkingList[i].la_crd, r.jaywalkingList[i].lo_crd);
                        panTo(r.jaywalkingList[i].la_crd, r.jaywalkingList[i].lo_crd);
                    }
                    // 지도에 원을 표시합니다 
                    circle.setMap(map);
                    // 6. 배열에 원의 정보를 저장한다 (지우기 작업을 위해 저장)
                    circles.push(circle);
                }
            }
        })
    }

    function removeCircle() {
        for(let i = 0; i < circles.length; i++) {
            circles[i].setMap(null);
        }
        circles = new Array();
    }
    function setCenter(lat, lng) {            
        // 이동할 위도 경도 위치를 생성합니다 
        var moveLatLon = new kakao.maps.LatLng(lat, lng);
        
        // 지도 중심을 이동 시킵니다
        map.setCenter(moveLatLon);
    }
    
    function panTo(lat, lng) {
        // 이동할 위도 경도 위치를 생성합니다 
        var moveLatLon = new kakao.maps.LatLng(lat, lng);
        
        // 지도 중심을 부드럽게 이동시킵니다
        // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
        map.panTo(moveLatLon);            
    }     
})