<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>교통사고 정보</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/assets/css/outline.css">
    <!-- <link rel="stylesheet" href="/assets/css/index.css"> -->
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="/assets/js/index.js"></script>
</head> 
<body>
    <div class="container">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="dashboard_content">
                <div class="content_left">
                    <p> 
                        미국에서는 20세기에서 치른 전쟁에서의 전사자보다 많은 수의 사람들이 교통사고로 사망한다고 한다.
                        이렇게 흔히 발생하는 만큼 경각심을 가지고 주의를 기울여야 한다.
                    </p>
                </div>
                <div style="width: 340p; height: 170px; float: right; margin-top:70px; padding-top: 10px; margin-right: 200px;">
                    <a href="#" >
                        <img src="/assets/images/KakaoTalk_20210811_170157497_01.png">
                    </a>
                </div>         
        </div>
        <div class="dashboard_content">
            <div class="content_left2">
                <!-- <h1>사고 건 수</h1> -->
                <canvas id="accChart" style="width: 40%; height: 100%; float: center; overflow: inherit; "></canvas>
            </div>
            <div class="content_right2">
                <!-- <h1>사망자 수</h1> -->
                <canvas id="accDeathChart" style="width: 45%; height: 100%; float: left; padding-top: 100px; margin-top:200px; margin-left: 40px;"></canvas>
                <!-- <h1>부상자 수</h1> -->
                <canvas id="injuredChart" style="width: 45%; height: 100%;  float: right; padding-top: 100px; margin-top:200px; margin-right: 40px;"></canvas>
            </div>
        </div>
    </div>
</body>
</html>