<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<font size="14"><a href="helloworld">hello world</a></font> <br/>
	<font size="14"><a href="springmvc/testRequestMapping">TestRequestMapping</a></font> <br/>
	<font size="14"><a href="springmvc/testMethod">TestMethod</a></font> <br/>
	<form action="springmvc/testMethod" method="POST">
		<input type="submit" value="submit"/>
	</form>  <br/>
	<font size="14"><a href="springmvc/testParamsAndHeaders?username=leo&age=11">Test ParamsAndHeaders</a></font> 
	<br/>
	<font size="14"><a href="springmvc/testAntUrl/aa/suffix">Test AntUrl</a></font>
	<br/>
	<font size="14"><a href="springmvc/testPathVariable/101">Test PathVariable</a></font>
	<br/><hr/>
	<br/>
	<font size="14"><a href="springmvc/testRest/101">Test RestGet</a></font>
	<br/>
	<form action="springmvc/testRest" method="POST">
		<input type="submit" value="submit"/>
	</form>
	<br/>
	<form action="springmvc/testRest/203" method="POST">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="submit"/>
	</form>
	<br/>
	<form action="springmvc/testRest/519" method="POST">
	<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="submit"/>
	</form>
	
	<br/>
	<font size="14"><a href="springmvc/testRequestParam?username=hehe">Test RequestParam</a></font>
	
	<br/>
	<font size="14"><a href="springmvc/testRequestHeader">Test RequestHeader</a></font>
	
	<br/>
	<font size="14"><a href="springmvc/testCookieValue">Test CookieValue</a></font>
	
	<br><hr>
	<form action="springmvc/testPojo">
		Username:<input type="text" name="username"/>
		<br/>
		Password:<input type="password" name="password"/>
		<br/>
		Province:<input type="text" name="address.province"/>
		<br/>
		City:<input type="text" name="address.city"/>
		<br/>
		<input type="submit" value="submit"/>
	</form>
	
	<br/>
	<font size="14"><a href="springmvc/testServletApi">Test ServletApi</a></font>
	<br/>
	<font size="14"><a href="springmvc/testModelAndView">Test ModelAndView</a></font>
	<br/>
	<font size="14"><a href="springmvc/testMap">Test Map</a></font>
	<br/>
	<font size="14"><a href="springmvc/testSessionAttributes">Test SessionAttributes</a></font>
	<br>
	<form action="springmvc/testModelAttribute">
		<input type="hidden" name="id" value="1"/>
		Username:<input type="text" name="username" value="xkxkx"/>
		<br/>
		<input type="submit" value="submit"/>
	</form>
	<br/><br/>
	<font size="14"><a href="springmvc/testViewAndViewResovler">Test ViewAndViewResovler</a></font>
	<br/><br/>
	<font size="14"><a href="springmvc/testHelloView">Test HelloView</a></font>
	<br/><br/>
	<font size="14"><a href="springmvc/testRedirect">Test Redirect</a></font>
</center>
</body>
</html>