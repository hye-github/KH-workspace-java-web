<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NexflixManager</title>
	<style>
        .container{width: 600px; height: 100%; margin: auto; text-align: center; margin-top: 50px;}
        .header{width: 100%; height: 70px; line-height: 65px; font-weight: bolder; background-color: black; color: white;}
        h1{margin: 0;}
        .insertcheck{height: 50px; text-align: center; line-height: 48px; border: 1px solid black;}
        .btnContainer{width: 100%; height: 50px; font-weight: bolder;}
        .btnContainer>div{float: left; width: 50%; height: 100%;}
        button{width: 100%; height: 100%; font-weight: 600; color: #333333; border: 1px solid black;}
        button:hover{cursor: pointer;}
    </style>
</head>
<body>
	<div class="container">
		<div class="header">
			<h1>Nexflix Manager</h1>
		</div>
		<div class="insertcheck">입력이 완료 되었습니다.</div>
		<div class="btnContainer">
			<div><button type="button" onclick="history.back()">Back</button></div>
			<div>
				<a href="read.NetflixManager"><button type="button">Movie List</button></a>
				<!-- a href="NetflixManagerRead"><button type="button">Movie List</button></a -->
			</div>
		</div>
	</div>
</body>
</html>