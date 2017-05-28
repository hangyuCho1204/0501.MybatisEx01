<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="/0501.MybatisEx01/customer/list">회원목록</a><br>
	<sf:form method="post" modelAttribute="customer" >
	
		<sf:input path="name"/>
		
	    <input type="submit" value="검색">
	
	</sf:form>
	
	<table border="1">
	<tr>
		<th>
			ID
		</th>
		<th>
			NAME
		</th>
		<th>
			address
		</th>
		<th>
			email
		</th>
	</tr>
	
	<c:forEach var="bean" items="${lists}" >
	
	<tr>
		<td>
			${bean.id}
		</td>
		<td>
			${bean.name}
		</td>
		<td>
			${bean.address}
		</td>
		<td>
			${bean.email}
		</td>
	</tr>
	</c:forEach>
	</table>
	
</body>	
</html>