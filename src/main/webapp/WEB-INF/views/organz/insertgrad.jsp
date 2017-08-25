<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/list.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/adminform.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$ ('#file').change(function () {
	    //alert($('#file').prop("files")[0].name);
	    for(var i=0;i<$('#file').prop("files").length;i++){
	    	$('#apndngfiles').append("<p>"+$('#file').prop("files")[i].name+"</p>")	
	    }
	    
	});	
	
	$("#prnt").click(function(){
		var url="${pageContext.servletContext.contextPath }/resources/html/child.html"
		window.open(url,"childForm", "width=450, height=250, resizable = no, scrollbars = no");
	})
	
	
})


//	function addFile(){
		//$('#filetable').append( $('#preset tr').clone() );

	//}

</script>
</head>
<body>

	<c:import url="/WEB-INF/views/include/header.jsp" />
	<div class="container">
		<div class="row">
			<c:import url="/WEB-INF/views/organz/include/menu.jsp" />
		</div>

		<hr class="nav-line">
		<div class="row">

			<form class="updateform" id="updateform" name="updateform"
				method="post" enctype="multipart/form-data"
				action="${pageContext.servletContext.contextPath }/organz/insert?type=${param.type}">

				<div class="form-group">

					<label>기관구분:</label> <select id="hmnbrdDstnct" name="hmnbrdDstnct">
						<option value="국내">국내</option>
						<option value="국외">국외</option>
					</select> <select id="orgnzDstnct" name="orgnzDstnct">
						<option value="대학원">대학원</option>
						<option value="대학교">대학교</option>
						<option value="학과">학과</option>
					</select> <br> <br> <label>기관명:</label> <input type="text"
						class="form-control" id="orgnzNm" name="orgnzNm"> <label>기관영문명:</label>
					<input type="text" class="form-control" id="engOrgnzNm"
						name="engOrgnzNm"> <label>홈페이지주소:</label> <input
						type="text" class="form-control" id="hmpageUrl" name="hmpageUrl">
					<label>전화번호:</label> <input type="text" class="form-control"
						id="telNo" name="telNo"> <label>팩스번호:</label> <input
						type="text" class="form-control" id="faxNo" name="faxNo">
					<label>주소:</label> <input type="text" class="form-control"
						id="addr" name="addr"> <label>상세주소:</label> <input
						type="text" class="form-control" id="detailAddr" name="detailAddr">
					<label>부모번호:</label> <input type="text" class="form-control"
						id="prntsOrgnzStr" name="prntsOrgnzStr">
						
						<!--  <button type="button" onclick="addFile()">파일 추가</button>-->
				<table id="filetable" cellpadding="5" cellspacing="0">
					<tr name="tr_attach_file">
						<th>첨부파일 </th>
						<td><input id="file" type="file" name="attachFile" multiple /></td>
					</tr>
					
					<!-- 추가 버튼을 누르면 위 숨겨진 테이블의 tr 을 가져다가 추가할 겁니닷 -->
				</table>
				<div id='apndngfiles'>
					
				</div>


				</div>

				<button type="submit" class="btn btn-primary btn-lg">입력</button>
			</form>

			<!--  <form name="form1" method="post" action="upload"
				enctype="multipart/form-data">
				
				<input type="submit" value="upload">
			</form>-->


			<!--<table id="preset" style="display: none;">
				<tr name="tr_attach_file">
					<th>첨부파일</th>
					<td><input id="file" type="file" name="attachFile" multiple/></td>
				</tr>
			</table>-->
			
			<a id="prnt" type="button" class="button">123123</a>

		</div>






	</div>

	<c:import url="/WEB-INF/views/include/footer.jsp" />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>