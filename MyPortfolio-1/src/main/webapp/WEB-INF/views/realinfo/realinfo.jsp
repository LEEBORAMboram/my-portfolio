<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>  
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/assets/css/realinfo.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="/assets/js/realinfo.js"></script>
</head>
<body>
    <div class="container">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="dashboard_area">
            <div class="col-3">
                <select id="search_btn1">
                    <option value="limited_area"></option>
                    <!-- <c:forEach items="${search_btn1}" var="search" varStatus="status">
                        <option value="${search.mcbscode}">${search.mchgname}</option>
                    </c:forEach> -->
                </selec>
                <div class="search_btn3">
                    <input type="text" id="search_keyword" placeholder="구와 동 을 입력해주세요.">
                    <!-- <button class="search_btn2" type="button">search</button>     -->
                    <!-- <input type="text" placeholder="지역명을 입력해주세요." id="search_keyword" style="display:none";> -->
                    <!-- <input type="buttpn" onclick="getSearchList()" class="btn btn-outline-primary mr-2" value="검색"> -->
                    <button class="search_btn3">검색</button>
                </div>
            </div> 
        </div>
        
                <div class="content_right live_confirm_area">
                    <table class="real_information_tbl">
                        <thead>
                            <tr>
                                <!-- <td>발생일자</td> -->
                                <td>발생 장소</td>
                                <td>주요 내용</td>
                                <td>기타</td>
                            </tr>
                        </thead>                
                    </table>
                </div>
                <!-- <div class="real_traffic_pager_area">
                    <button>&lt;</button>
                    <span class="current">1</span> / <span class="total">3</span>
                    <button>&gt;</button>
                </div> -->
            <!-- <div class="dashboard_area">
                <div class="content_left">
                    <div id="map" style="width:500px;height:400px;">

                        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9def6594ef29932b65ab258116f1fe92"></script>
                        <script>
                            var container = document.getElementById('map');
                            var options = {
                                center: new kakao.maps.LatLng(33.450701, 126.570667),
                                level: 4
                            };

                            var map = new kakao.maps.Map(container, options);
                        </script>
                    </div>
                </div>
            </div>-->
        </div>
    </body>
</html>