<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<!-- AJAX : Asynchronous(비동기) Javascript And XML (제이쿼리 슬림 버전에서는 동작하지않는다)-->
	<!-- 
페이지를 새로 고침하지않아도 데이터 통신 할 수 있는 방법

전체 페이지를 새로 고치지 않고도 페이지의 일부만을 위한 데이터를 로드하는 기법
출처: https://visualize.tistory.com/402 [시각화를 배우고 정리합니다:티스토리]

자바스크립트의 라이브러리중 하나이며, 비동기식 자바스크립트와 xml을 뜻한다.
Ajax를 사용하면 페이지 새로고침 없이 페이지 일부의 데이터를 바꿀 수 있게 된다.
데이터 로드를 위해 XMLHttpRequest 객체를 이용하는 것이 특징이다.

사용자에 의해 이벤트가 발생하면 핸들러에 의해 바로 자바스크립트를 불러온다.
그 후 자바스크립트에서는 XmlHttpRequest 객체를 이용하여 서버로 요청을 보내는데, 그동안 웹 브라우저는 응답을 기다릴 필요 없이 다른 작업을 수행할 수 있다(비동기 방식).
서버측에서 처리를 마치고 XmlHttpRequest 객체를 전달 받으면 이를 토대로 Ajax요청을 처리하게 되는 것이다.
클라이언트가 직접 원하는 정보를 서버에게 요청하여 얻는 방식을 client pooling 방식이라고 하는데 Ajax가 이 방식을 사용한다. 반면, 푸시 알림같은 server push방식의 서비스를 만들 수는 없다.
 -->

	<fieldset>
		<legend>Exam01. Simple AJAX</legend>
		<button id="exam01">Exam01</button>
		<script>
			$("#exam01").on("click",function(){
				$.ajax({
					url:"/exam01.ajax",
					type:"post" //get or post 아예 안쓰면 default는 get
				}); // ajax 함수에 json 형태의 인자를 보내는 중. ajax는 json타입의 중괄호를 넣어 파라미터를 완성해줘야한다.
				// ! AJAX 는 응답을 기다리지 않고 다음 코드로 넘어간다.

				// 코드1
				// 코드2
				// 보통 코드는 1을 실행하고 응답을 기다린 뒤 2를 실행하는데
				// AJAX는 코드1을 실행하고 응답을 기다리지않고 바로 2를 실행한다. - 그래서 비동기 통신이다. 멀티 쓰레드. 여러개의 코드가 실행되는 것.
			})
		</script>
	</fieldset>

	<fieldset>
		<legend>Exam02. AJAX With parameter</legend>
		<button id="exam02">Exam02</button>
		<script>
			$("#exam02").on("click",function(){
				$.ajax({
					url:"/exam02.ajax",
					type:"get",
					data:{
						key1:"Value1", // "" 안묶으면 이런 명의 변수가 존재해서는 안된다.
						"key2":"Value2"	// 나중에 꼬이는거 방지하려면 ""을 무조건 해라.
					} // key:"value"
					
					
				//	data:[{
				//		key1:"Value1",
				//		"key2":"Value2"
				//	},{}]
					
				}); 
			})
		</script>
	</fieldset>

	<fieldset>
		<legend>Exam03. AJAX Response</legend>
		<button id="exam03">Exam03</button>
		<script>
			$("#exam03").on("click",function(){
				$.ajax({
					url:"/exam03.ajax"
				}).done(function(resp){ // ajax 통신이 끝났을 때 .done(function(//response.getWriter().append("Ajax Works!");//){}) : callback 함수 
					console.log(resp);
				});
			})
		</script>
	</fieldset>


	<!-- 
클라이언트가 서버에게 데이터를 담아 Request 하는 방법
1. Form + Submit
2. Get 방식을 모방한 파라미터 전달 ex) /list.board?cpage=1 // Post 방식은 모방 불가능. Submit으로만 가능
3. AJAX 통식 (Submit에 구애 받지않는다.)
 -->

	<fieldset>
		<legend>Exam04. AJAX Response with multiple values</legend>
		<button id="exam04">Exam04</button>
		<script>
			$("#exam04").on("click",function(){
				$.ajax({
					url:"/exam04.ajax"
				}).done(function(resp){
					console.log(resp);
					console.log(resp[0]);
					let arry = JSON.parse(resp); // 역직렬화 // 형변환
					console.log(arry);
					console.log(arry[0]);
					console.log(arry[1]);
					console.log(arry[2]);
				});
			})
		</script>
	</fieldset>

	<fieldset>
		<legend>Exam05. AJAX Response with Java Object</legend>
		<button id="exam05">Exam05</button>
		<script>
			$("#exam05").on("click",function(){
				$.ajax({
					url:"/exam05.ajax"
				}).done(function(resp){
					console.log(resp); // 오브젝트(resp.seq 안나온다.) 모양으로 생긴 String 임
					console.log(resp.seq);
					
					let arry = JSON.parse(resp); // 역직렬화 // 형변환
					console.log(arry);
					console.log(arry.seq); // 오브젝트로 나와서 . 찍고 사용 가능
				});
			})
		</script>
	</fieldset>
	
	<fieldset>
		<legend>Exam06. AJAX Response with multiple Java Object</legend>
		<button id="exam06">Exam06</button>
		<script>
		/*	$("#exam06").on("click",function(){
				$.ajax({
					url:"/exam06.ajax"
				}).done(function(resp){
					console.log(resp); // 객체 배열처럼 생긴 String 이 넘어옴.
					let arry = JSON.parse(resp);
					console.log(arry);
					console.log(arry[0]);
					console.log(arry[0].seq);
				});
			})
		*/
		
		/*
		$("#exam06").on("click",function(){
			$.ajax({
				url:"/exam06.ajax",
				dataType:"json" // 돌아오는 타입
			}).done(function(resp){
				console.log(resp);
				console.log(resp[0]);
				console.log(resp[0].seq);
			});
		})
		*/
		
		/*
		$("#exam06").on("click", () => {
			$.post("/exam06.ajax", null, (res) => {console.log(res);}, "json");
		});
		
		https://beam307.github.io/2017/12/05/ajax/
		*/
			
		 $("#exam06").on("click", () => {
	         $.post("/exam06.ajax", (res) => {console.log(res);}, "json");
	     });
		
		 /*
		 [hi] [오후 3:08] 필수는 <>, [] 선택 , 선택할꺼면 써라
		 [hi] [오후 3:09] 몰라서 물어보고옴
		 [hi] [오후 3:09] ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
		 [hi] [오후 3:09] 공식은 아닌데 ms에서 밀고있는 정의?
	     */
		
		</script>
	</fieldset>
	
	<fieldset>
		<legend>Exam07. AJAX Response with multiple Java Object</legend>
		<button id="exam07">Exam07</button>
		<script>
		$("#exam07").on("click",function(){
			$.ajax({
				url:"/exam07.ajax",
				dataType:"json" // 돌아오는 타입
			}).done(function(resp){
				console.log(resp);
			});
		});
		</script>
	</fieldset>
</body>
</html>