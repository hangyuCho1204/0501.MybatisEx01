

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 
    response.setContentType("text/html;");
	request.setCharacterEncoding("UTF-8"); 
	%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript">
	function historyback(){
		history.back(-1);
	}
</script>
</head>
<body>
	
	결과화면<br>
	<a href="/0402.DataAccessEx02/">메인 화면 ㄱㄱ</a><br>
	<a href="/0402.DataAccessEx02/product/insert">가입하기</a><br>
	<a href="/0402.DataAccessEx02/product/search">검색하기</a><br>
	
	
	<table border="1">
	
<%-- 	<sf:form method="post" modelAttribute="customer">
	
		<sf:input path="name"/>
		
		<input type="submit" value="검색">
	
	</sf:form> --%>
	
	<tr>
		<th>
			ID
		</th>
		<th>
			NAME
		</th>
		<th>
			price
		</th>
		<th>
			description
		</th>
		<th>
			수정
		</th>
		<th>
			삭제
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
			${bean.price}
		</td>
		<td>
			${bean.description}
		</td>
		<td>
			<a href="/0402.DataAccessEx02/product/update?id=${bean.id}">수정</a>
			<%-- <a href="/0401.DataAccessEx01_mvc_jdbc/views/customer/update?id=${bean.id}&name=${bean.name}&address=${bean.address}&email=${bean.email}">수정</a> --%>
		</td>
		<td>
			<a href="/0402.DataAccessEx02/product/delete?id=${bean.id}">삭제</a>
		</td>
	</tr>
	</c:forEach>
	
	</table>
	<input type="button" value="뒤로가기" onclick="historyback()"/>
</body>
</html>