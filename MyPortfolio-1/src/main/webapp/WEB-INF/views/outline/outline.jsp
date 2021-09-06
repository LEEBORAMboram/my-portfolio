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
    <link rel="stylesheet" href="/assets/css/index.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</head> 
<body>
    <div class="container">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="dashboard_area">
                <div class="content_right">
                    <p>친척과 친구 포함 주변인 중 꼭 몇 명은 교통사고로 목숨을 잃은 사람이 있을 정도로 매우 흔한 사망 원인이다.
                        미국에서는 20세기에서 치른 전쟁에서의 전사자보다 많은 수의 사람들이 교통사고로 사망한다고 한다.
                        (2013∼2016년 사고 통계에 따르면) 보행자 사망자의 70% 정도가 인도와 차도가 따로 없는 주택가 이면도로 등의 보차혼용도로에서 발생하였다. 
                        따라서 각 지자체에서는 이면도로에서의 제한 속도를 낮추는 것을 추진하고 있으며, 부산은 2019년 연말부터 도심 전역으로 확대될 예정이다. 
                        또한 무단횡단에 따른 사망자는 2018년 기준 보행자 사망사고의 34.8%를 차지하고 있다. 
                        이 외에도 교차로에서 우회전하면서 일어나는 사고가 전체 보행자 사고에서의 비율은 낮지만 2012~2017년 동안 크게 증가했다. 
                        누구에게나 발생할 수 있는 만큼 경각심을 가지고 주의를 기울여야 한다.
                    </p>
                </div>
            </div>
            <div class="dashboard_content">
                <div class="content_left2">
                    <p class="con_name"> 사망자 수 </p>
                    <p class="con_number"> 17,477</p>
                    <p class="con_name"> 부상자 수 </p>
                    <p class="con_number"> 1,599,966</p>
                </div>
                <div class="content_right2">
                    <p class="ex"> 2017년 ~ 2020년 기준</p>
                </div>
            </div>
            <div class="dashboard_area">
                <div class="content_left3">
                    <h1>사망자</h1>
                    <canvas id="accDeathChart" style="width: 100%; height: 100%;"></canvas>
                </div>
                <div class="content_right3">
                    <h1>부상자</h1>
                    <canvas id="injuredChart" style="width: 100%; height: 100%;"></canvas>
                </div>
            </div>
    </div>
</body>
</html>