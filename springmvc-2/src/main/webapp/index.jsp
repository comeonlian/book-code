<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#testJson").click(function(){
		var url = this.href;
		var args = {};
		//alert(url);
		$.post(url, args, function(data){
			for(var i = 0; i < data.length; i++){
				var id = data[i].id;
				var lastName = data[i].lastName;
				
				alert(id + ": " + lastName);
			}
		});
		return false;
	});
});
</script>
</head>
<body>
<center>
	<!-- HH-hh@126.com-1-105 -->
	<form action="testConverterService" method="POST">
		Employee: <input type="text" name="employee"/>
		<br/>
		<input type="submit" name="submit"/>
	</form>
	<br/><br/>
	<hr/>
	<a id="testJson" href="testJson">Test Json</a>
	<br/><br/>
	<hr/>
	<a id="emps" href="emps">Test emps</a>
	<br/><br/>
	<hr/>
	<form action="testHttpMessageConverter" method="post" >
		File: <input type="file" name="file" />
		Desc: <input type="text" name="desc"/>
		<input type="submit" value="Submit" />
	</form>
	<br/><br/>
	<hr/>
	<a href="testResponseEntity">Test ResponseEntity</a>
	<br/><br/>
	<hr/>
	<a href="i18n">i18n</a>
	<br/><br/>
	<hr/>
	<form action="testFileUpLoad" method="POST" enctype="multipart/form-data">
		File: <input type="file" name="file"/>
		Desc: <input type="text" name="desc"/>
		<input type="submit" value="Submit"/>
	</form>
	
	<br/><br/>
	<hr/>
	<a href="testExceptionHandlerExceptionResolver?i=10">Test ExceptionHandlerExceptionResolver</a>
	<br/><br/>
	<hr/>
	<a href="testResponseStatusExceptionResolver?i=10">Test ResponseStatusExceptionResolver</a>
	<br/><br/>
	<hr/>
	<a href="testDefaultHandlerExceptionResolver">Test DefaultHandlerExceptionResolver</a>
	<br/><br/>
	<hr/>
	<a href="testSimpleMappingExceptionResolver?i=5">Test SimpleMappingExceptionResolver</a>
</center>
</body>
</html>