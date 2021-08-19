<%@page import="vo.ListVo" %>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%List<ListVo> list = (List<ListVo>)request.getAttribute("list");%>	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>o_o+</title>
<style>
	body {background-color: #f2f7f7;border: solid;margin: 10px;}
	form {background-color: #6c9aa1;border: solid;margin: 10px;}
	a {color: #31393b;}
</style>
</head>
<body>
	<h3>주소록 Servlet</h3>
	
	<jsp:include page="/WEB-INF/views/includes/header.jsp">
	<jsp:param value="" name="s"/>
	</jsp:include>
	
	<form action="<%=request.getContextPath() %>/" method="POST">
		<label for="search">검색어</label>
		<input type="hidden" name="a" value="search" /> 
		<input type="text" name="search" id="search" />
		<input type="submit" value="검색">
	</form>
	</tr>
	</br>
	
	<table border=2>
	<tr>
		<th>이름</th>
		<th>휴대전화</th>
		<th>전화번호</th>
		<th>도구</th>
	</tr>
	<!-- 전화번호 리스트: 목록 -->
	<!-- 루프 시작 -->
	<%for (ListVo vo : list) {%>
	<tr>
		<td><%=vo.getlistName()%></td>
		<td><%=vo.getlistHp()%></td>
		<td><%=vo.getlistTel()%></td>
		<td colspan="2">
				<!-- 삭제 폼 -->
				<form action="<%=request.getContextPath()%>/"
					method="POST">
					<input type="hidden" name="a" value="delete" /> 
					<input type="hidden" name="no" value="<%=vo.getListId() %>" />
					<input type="submit" value="삭제" />
				</form>
			</td>	
		</tr>	
		<%
		}
		%>
		</table>
		</br>
		<a href="<%=request.getContextPath() %>/?a=insert">새 주소 추가</a>
</body>
</html>