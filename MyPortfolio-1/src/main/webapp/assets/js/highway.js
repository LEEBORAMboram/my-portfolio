$(function () {
    // 1. 지도를 먼저 미리 생성하고
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 4 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    var polylines = new Array(); // 2. 선들을 저장할 배열을 만들어놓고

    $("#main_highway").change(function () {
        let name = $("#main_highway").find("option:selected").val();

        getHighwaySection(name)
    });
    $("#sub_highway").change(function () {
        let name = $("#sub_highway").find("option:selected").val()
        let name_all = $("#main_highway").find("option:selected").val() + " " + $("#sub_highway").find("option:selected").val()
        //getBicycleAccidentAddress(region_all);
    });

    function regionMaker() {

    }

    getHighwayName();
    getHighwaySection("경부고속도로");
    //getBicycleAccidentAddress();

    function getHighwayName(name) {

        $("#sub_highway").prop("disabled", false)
        $("#sub_highway").html("");

        $.ajax({
            type: "get",
            url: "/api/highwayName",
            success: function (r) {
                $("#main_highway").html("");
                console.log(r);
                for (let i = 0; i < r.length; i++) {
                    $("#main_highway").append('<option value="' + r[i] + '">' + r[i] + '</option>');
                }

            }
        })
    }

    function getHighwaySection(highway) {
        $.ajax({
            type: "get",
            url: "/api/highwaySection?highway=" + highway,
            success: function (r) {
                $("#sub_highway").html("");
                console.log(r);
                for (let i = 0; i < r.length; i++) {
                    $("#sub_highway").append('<option value="' + r[i] + '">' + r[i] + '</option>');
                }
            }
        })
    }

    function getBicycleAccidentAddress(selectbox) {
        $.ajax({
            type: "get",
            url: "/api/section/bicycleAccidentAdress?selectbox=" + selectbox,
            success: function (r) {
                console.log(r);

                let address = new Array();
                let occ_cnt = new Array();
                let latitude = new Array();
                let longitude = new Array();
                let select = new Array();
                $(".bicycle_accident").html("");

                for (let i = 0; i < 4; i++) {
                    let tag = "<tbody class='bicycle_accident'></tbody>";
                    $(".bicycle_information").append(tag);
                }

                for (let i = 0; i < r.bicycleList2.length; i++) {
                    let add = r.bicycleList2[i].spot_nm;
                    let occ = r.bicycleList2[i].occrrnc_cnt;
                    let la = r.bicycleList2[i].la_crd;
                    let lo = r.bicycleList2[i].lo_crd;
                    let se = r.bicycleList2[i].selectbox;

                    address.push(add);
                    occ_cnt.push(occ);
                    latitude.push(la);
                    longitude.push(lo);
                    select.push(se);

                    console.log(Math.floor(i / 3));

                    let page = Math.floor(i / 3);
                    let tag =
                        '<tr>' +
                        '<td>' + r.bicycleList2[i].spot_nm + '</td>' +
                        '<td>' + r.bicycleList2[i].occrrnc_cnt + '</td>' +
                        +'</tr>'
                    $(".bicycle_accident").eq(page).append(tag);
                }
                $(".bicycle_accident").eq(0).addClass("active");

                $("#real_traffic_next").click(function () {
                    let currentPage = Number($(".current").html());
                    currentPage++;
                    if (currentPage > 4) currentPage = 4;
                    $(".current").html(currentPage);
                    $(".bicycle_accident").removeClass("active");
                    $(".bicycle_accident").eq(currentPage - 1).addClass("active");
                })

                $("#real_traffic_prev").click(function () {
                    let currentPage = Number($(".current").html());
                    currentPage--;
                    if (currentPage < 1) currentPage = 1;
                    $(".current").html(currentPage);
                    $(".bicycle_accident").removeClass("active");
                    $(".bicycle_accident").eq(currentPage - 1).addClass("active");
                })

                // 3. 이전에 그려져있던 선들을 삭제하고
                removepolyline();

                for (let i = 0; i < r.bicycleList2.length; i++) {
                    // 4. 반복문 안에서 polyline 객체를 생성하고
                    // 지도에 표시할 선을 생성합니다

                    // 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
                    var linePath = [
                        new kakao.maps.LatLng(33.452344169439975, 126.56878163224233),
                        new kakao.maps.LatLng(33.452739313807456, 126.5709308145358),
                        new kakao.maps.LatLng(33.45178067090639, 126.5726886938753)
                    ];

                    // 지도에 표시할 선을 생성합니다
                    var polyline = new kakao.maps.Polyline({
                        path: linePath, // 선을 구성하는 좌표배열 입니다
                        strokeWeight: 5, // 선의 두께 입니다
                        strokeColor: '#FFAE00', // 선의 색깔입니다
                        strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                        strokeStyle: 'solid' // 선의 스타일입니다
                    });

                    // 5. 마지막에 그려지는 선을 지도의 중심점으로 세팅
                    if (i == r.bicycleList2.length - 1) {
                        setCenter(r.bicycleList2[i].la_crd, r.bicycleList2[i].lo_crd);
                        panTo(r.bicycleList2[i].la_crd, r.bicycleList2[i].lo_crd);
                    }

                    // 지도에 선을 표시합니다 
                    polyline.setMap(map);
                    // 6. 배열에 선의 정보를 저장한다 (지우기 작업을 위해 저장)
                    polylines.push(polyline);
                }
            }
        })
    }

    function removepolyline() {
        for (let i = 0; i < polylines.length; i++) {
            polylines[i].setMap(null);
        }
        polylines = new Array();
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