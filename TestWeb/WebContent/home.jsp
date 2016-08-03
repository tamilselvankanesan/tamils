<%@page import="com.success.TestBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	Total Code Size:	
	<%= ((List<TestBean>)request.getAttribute("codes")).size() %>
	
		<c:forEach items="${codes}" var="cc">
		<br/>
		<c:set var="size" value="${requestScope[cc.code].size()}">
		</c:set>
		<%-- <c:out value="${coddd}"/> size : <c:out value="${size}"/> --%>
		<br/>
		<c:if test="${size > 0}">
			<c:out value="${cc.code} exists"></c:out>
		</c:if>
	</c:forEach>
</body>
</html>