

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<a href="/0501.MybatisEx01/">메인 화면 ㄱㄱ</a><br>
	<a href="/0501.MybatisEx01/customer/insert">가입하기</a><br>
	<a href="/0501.MybatisEx01/customer/search">검색하기</a><br>
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
			ADDRESS
		</th>
		<th>
			E-MAIL
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
			${bean.address}
		</td>
		<td>
			${bean.email}
		</td>
		<td>
			<a href="/0501.MybatisEx01/customer/update?id=${bean.id}">수정</a>
			<%-- <a href="/0401.DataAccessEx01_mvc_jdbc/views/customer/update?id=${bean.id}&name=${bean.name}&address=${bean.address}&email=${bean.email}">수정</a> --%>
		</td>
		<td>
			<a href="/0501.MybatisEx01/customer/delete?id=${bean.id}">삭제</a>
		</td>
	</tr>
	</c:forEach>
	
	<tr>
	
		<td align="center" colspan="6">
			<c:if test="${beginPage != holdBiginPageB && endPage != holdBiginPageE}">
				<a href="list?selectPage=${holdBiginPageB}&beginPage=${holdBiginPageB}&endPage=${holdBiginPageE}">첫 페이지</a>
				<a href="list?selectPage=${beforePageE}&beginPage=${beforePageB}&endPage=${beforePageE}"> << </a>
			</c:if>
			
			<c:forEach var="i" begin="${beginPage}" end="${endPage}" step="1">
				<a href="list?selectPage=${i}&beginPage=${beginPage}&endPage=${endPage}">${i}</a>&nbsp
			</c:forEach>
		
			<c:if test="${beginPage != holdEndPageB && endPage != holdEndPageE}">
				<a href="list?selectPage=${nextPageB}&beginPage=${nextPageB}&endPage=${nextPageE}"> >> </a>
				<a href="list?selectPage=${holdEndPageE}&beginPage=${holdEndPageB}&endPage=${holdEndPageE}">마지막 페이지</a>
			</c:if>
		<%--  --%>
		</td>
	</tr>
	</table>
	<input type="button" value="뒤로가기" onclick="historyback()"/>
</body>
</html>