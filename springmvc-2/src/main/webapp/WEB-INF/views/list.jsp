<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示员工信息</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".del").click(function(){
		var href = $(this).attr('href');
		//alert(href);
		$("form").attr("action",href).submit();
		return false;
	});
});

</script>
</head>
<body>
<center>
	
	<c:if test="${empty requestScope.employees}">
		没有员工信息
	</c:if>
	<c:if test="${not empty requestScope.employees}">
		<table border="1" cellpadding="20" cellspacing="20">
			<tr>
				<td>Id</td>
				<td>LastName</td>
				<td>Email</td>
				<td>Gender</td>
				<td>Department</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
			<c:forEach items="${requestScope.employees}" var="emp">
				<tr>
					<td>${emp.id }</td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td>${emp.gender == 0? 'Famale':'male'}</td>
					<td>${emp.department.departmentName}</td>
					<td><a href="emp/${emp.id}">Edit</a></td>
					<td><a class="del" href="emp/${emp.id}" >Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<br/><br/>
	<hr/>
	<a href="emp">Add Employees</a>
	
	<br/><br/>
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
</center>
</body>
</html>