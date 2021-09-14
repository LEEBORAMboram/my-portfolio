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
    <link rel="stylesheet" href="/assets/css/section_jaywalking.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="/assets/js/section_jaywalking.js"></script>
</head>
<body>
    <div class="container">
        <%@include file="/WEB-INF/views/section/section.jsp"%>
        <div class="dashboard_area">
            <div class="content_center">
                <select id="main_region">
                    <option value="local_selelct">광역시 혹은 도를 선택하세요</option>
                    <c:forEach items="${main_region}" var="region" varStatus="status">
                        <option value="${region.mcbscode}">${sub_region.mchgname}</option>
                    </c:forEach>
                </select>
                <select id="sub_region">
                    <option value="">전체</option>
                </select>
            </div>
            <div class="content_right">
                <div class="chart" style="height: 30vh; overflow-y: scroll; overflow-x: hidden;">
                    <table class="jaywalking_information">
                        <thead>
                            <tr>
                                <td>위치</td>
                                <td>사고건수</td>
                            </tr>
                        </thead>                
                    </table> 
                <!-- <div class="real_traffic_pager_area">
                    <button id="real_traffic_prev">&lt;</button>
                    <span class="current">1</span> / <span class="total">3</span>
                    <button id="real_traffic_next">&gt;</button>          
                </div> -->
                </div>
                <div id="map" style="width: 90%;height:700px; margin: 0 auto; margin-top: 30px;">
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
        </div>
    </div>
</body>
</html>