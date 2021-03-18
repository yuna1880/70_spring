<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시작페이지</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	/*
	function fetchGetBoardList() {
		/*
		fetch("ajaxGetBoardList.do").then(function (response) {
			response.json().then(function (result) {
				console.log(result);
			})
		})
		
		fetch("ajaxGetBoardList.do").then(async function (response) {
			let result = await response.json();
			console.log(result);
		})
	}
	*/
	
	//promise를 공부! async await 꼭 알아두고 해보자.
	async function fetchGetBoardList() {
		let response = await fetch("ajaxGetBoardList.do");
		console.log(response);
		let result = await response.json();
		console.log(result);	
	}

	function ajaxGetBoardList() {
		alert(">> ajaxGetBoardList() 실행! ")
		//(요청명 , 요청방식, 데이터타입, 성공했을때 실행할 콜백함수 작성)
		$.ajax("ajaxGetBoardList.do",{
			type:"get",
			dataType: "json",
			success : function(returnValue){
				alert("성공!!!!");
				//콘솔에 전달받은 데이터가 표시된다.
				console.log(returnValue);
				
				//데이터 갯수만큼 ul태그 만들어주기.
				var dispHtml="<ul>";
				$.each(returnValue, function(index, obj){
					dispHtml += "<li>";
					dispHtml += this.seq + ", ";
					dispHtml += this.title + ", ";
					dispHtml += this["writer"] + ", ";
					dispHtml += this["regdate"] + ", ";
					dispHtml += obj["cnt"];
					dispHtml += "</li>";
				});
				dispHtml += "</ul>";
				$("#dispData").html(dispHtml);
				
			},
			error : function(){
				alert("실패!!");
			}
		});
	}

</script>
</head>
<body>

<div id="container">
	<h1>게시판 프로그램</h1>
	<hr>
	<p><a href="login.do">로그인 페이지로 이동(GET)</a></p>
	<hr>
	<p><a href="javascript:ajaxGetBoardList()">게시글 목록 가져오기(Ajax - JSON) </a></p>
	<hr>
	<div id="dispData">
		<ul>
			<li>데이터를 가져와서 출력하기</li>
		</ul>
	</div>
	
	<button onclick="fetchGetBoardList()">fetch</button>
</div>


</body>
</html>




