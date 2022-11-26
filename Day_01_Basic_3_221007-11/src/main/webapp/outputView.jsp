<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message List</title>
</head>
<body>

	<table border=1 align=center>
		<tr>
			<th colspan=3>Message List
		</tr>
		<tr>
			<th>seq
			<th>writer
			<th>message
		</tr>

		<c:choose>
		
			<c:when test="${not empty list}">
				<!-- 비어있지 않다면 -->
				<c:forEach var="i" items="${list}">
					<!-- jstl에선 "" 필수 -->
					<tr>
						<td>${i.seq}
						<td>${i.writer}
						<td>${i.message}
					</tr>
				</c:forEach>
			</c:when>
			
			<c:otherwise>
				<tr>
					<th colspan=3>출력할 내용이 없습니다.
				</tr>
			</c:otherwise>
			
		</c:choose>
		
		<tr>
			<td colspan=3>
				<form action="delete.message">
					<input type=text name=delSeq placeholder="Input seq to delete">
					<button>Delete</button>
				</form>
			</td>
		</tr>
		<tr>
			<td colspan=3>
				<form action="update.message">
					<input type=text name=seq placeholder="Input seq to modify"><br>
					<input type=text name=writer placeholder="Input writer to modify"><br>
					<input type=text name=message placeholder="Input message to modify">
					<button>Modify</button>
				</form>
			</td>
		</tr>
		<tr>
			<td colspan=3 align=center><a href="index.html"><button>Back</button></a></td>
		</tr>
	</table>


</body>
</html>