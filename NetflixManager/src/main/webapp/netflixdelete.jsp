<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nexflix Manager Delete</title>
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
    </style>
</head>
<body>
	<form action="delete.NetflixManager">
		<div class="container">
			<div class="header">
				<h1>Nexflix Manager</h1>
			</div>
			<div class="content">

				<div class="row">
					<div class=title>삭제하고자하는 No.</div>
					<div class="inputcontent">
						<input type="text" name="del"
							placeholder="Input id to delete">
					</div>
				</div>
				
			</div>

			<div class="btnContainer">
			
					<div>
						<button>Movie Delete</button>
					</div>
				
					<div><button type="button" onclick="history.back()">Back</button></div>
			
			</div>
		</div>
	</form>
</body>
</html>