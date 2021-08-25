<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/index.css">
    <link rel="stylesheet" href="/assets/css/region.css">
</head>
<body>
    <div class="left_menu">
        <a href="/" id="logo">CAR ACCIDENT INFO</a>
            <ul class="main_menu">
                <li>
                    <a href="#">Overview <span>한 눈에 보기</span></a>
                </li>
                <li>
                    <a href="/regional">Regional <span>지역별</span></a>
                </li>
                <li>
                    <a href="#">Section <span>구간별</span></a>
                </li>
                <li>
                    <a href="#">Weather <span>날씨별</span></a>
                </li>
                <li>
                    <a href="#">Accident Prediction <span>향후 예측</span></a>
                </li>
            </ul>
            <select id="region_select">
                <option value="all">지역</option>
                <option value="서울">서울특별시</option>
                <option value="인천">인천광역시</option>
                <option value="부산">부산광역시</option>
                <option value="대구">대구광역시</option>
                <option value="광주">광주광역시</option>
                <option value="대전">대전광역시</option>
                <option value="울산">울산광역시</option>
                <option value="경기">경기도</option>
                <option value="강원">강원도</option>
                <option value="충북">충청북도</option>
                <option value="충남">충청남도</option>
                <option value="전북">전라북도</option>
                <option value="전남">전라남도</option>
                <option value="경북">경상북도</option>
                <option value="경남">경상남도</option>                   
                <option value="세종">세종특별시</option>
                <option value="제주">제주도</option>
            </select>
        </div>
    </body>
</html>