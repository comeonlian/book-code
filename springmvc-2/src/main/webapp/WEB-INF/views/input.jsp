<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示员工信息</title>
</head>
<body>
<center>
	<form:form action="${pageContext.request.contextPath}/emp" method="POST" modelAttribute="employee">
		
		<c:choose>
			<c:when test="${empty employee.id }">
				LastName: <form:input path="lastName"/> 
			</c:when>
			<c:otherwise>
				<form:hidden path="id"/>
				<input type="hidden" name="_method" value="PUT" />
			</c:otherwise>
		</c:choose>
		<br/><br/>
		Email: <form:input path="email"/>
		<br/><br/>
		Gender: <form:radiobuttons path="gender" items="${requestScope.genders}"/>
		<br/><br/>
		Department: <form:select path="department.id" items="${requestScope.departments}" 
					itemLabel="departmentName" itemValue="id">
		</form:select>
		<br/><br/>
		Birth: <form:input path="birth" />
		<br/><br/>
		Salary: <form:input path="salary" />
		<br/><br/>
		<form:button>提交</form:button>
		
	</form:form>
</center>
</body>
</html>