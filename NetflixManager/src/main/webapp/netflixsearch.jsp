<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl 포맷라이브러리 추가 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nexflix Manager List</title>
    <style>
        *{box-sizing: border-box;}
        .container{width: 600px; height: 100%; margin: auto; text-align: center; margin-top: 50px;}
        .header{width: 100%; height: 70px; line-height: 65px; font-weight: bolder; background-color: black; color: white;}
        h1{margin: 0;}
        .btnContainer{width: 100%; height: 50px; font-weight: bolder;}
        .btnContainer>div{float: left; width: 50%; height: 100%;}
        button{width: 100%; height: 100%; font-weight: 600; color: #333333; border: 1px solid black;}
        .content{width: 100%; height: 100%;}
         input{width: 100%; height: 45px; margin: 0; padding-left: 20px; border-width: 0; box-sizing : border-box;}
        .row>div{float: left; height: 50px; padding-top: 2px;}
        .title{width: 30%; height: 100%; line-height: 45px; border: 1px solid black;}
        .inputcontent{width: 70%; height: 100%;  border: 1px solid black;}
        button:hover{cursor: pointer;}

		.list_row_title>div{float: left; height: 30px; border: 1px solid black; font-size:15px; line-height: 27px; font-weight: bolder; background-color: #666666; color:white;}
        .list_row>div{float: left; height: 30px; border: 1px solid black; font-size:15px; line-height: 27px;}
        .list_id{width: 50px;}
        .list_title{width: 250px;}
        .list_genre{width: 150px;}
        .list_launch_date{width: 150px;}

        h2{margin: 0; padding: 0;}
    </style>
</head>
<body>
    <form action="search.NetflixManager">
        <div class="container">
            <div class="header">
                <h1>Nexflix Manager</h1>
            </div>

            <div class="content">

                <div class="list_row_title">
                    <div class="list_id">No.</div>
                    <div class="list_title">Title</div>
                    <div class="list_genre">Genre</div>
                    <div class="list_launch_date">Launch date</div>
                </div>
                
                <c:choose>
                    <c:when test="${not empty list}">
                        <!-- 비어있지 않다면 -->
                        <c:forEach var="i" items="${list}">
                            <!-- jstl에선 "" 필수 -->
                            <div class="list_row">
                                <div class="list_id">${i.id}</div>
                                <div class="list_title">${i.title}</div>
                                <div class="list_genre">${i.genre}</div>
                                <div class="list_launch_date"><fmt:formatDate pattern="yyyy년 MM월 출시" value="${i.launch_date}"/></div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="block">
                        <h2>출력할 내용이 없습니다.</h2>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="btnContainer">
                <div><a href="index.jsp"><button type="button">Movie Main</button></a></div>
                <div><button type="button" onclick="history.back()">Back</button></div>
            </div>

        </div>
    </form>
</body>
</html>