<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>교통사고 정보</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="/assets/js/index.js"></script>

</head>
<body>
    <div class="container">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="right_area">
            <div class="content_head"> 
                <span class="car_accident">
                    <span>지역별 자동차 사고 현황</span>
                </span>
            </div>
            <div class="content_area">
                <div>
                    <p> 사망자 수 (2017년 ~ 2020년)</p>
                    <p id="death"> 17,477</p>
                </div>
                <div>
                    <p> 부상자 수 (2017년 ~ 2020년)</p>
                    <p id="injured"> 1,599,966</p>
                </div>
            </div>
            <div class="content_right">
                <h1>사망자</h1>
                <canvas id="accDeathChart" style="width: 100%; height: 100%;"></canvas>
            </div>
            <div class="content_right">
                <h1>부상자</h1>
                <canvas id="injuredChart" style="width: 100%; height: 100%;"></canvas>
            </div>
        </div>
    </div>
</body>
</html>