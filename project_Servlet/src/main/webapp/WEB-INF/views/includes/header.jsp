<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String search = request.getParameter("search");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(search!=null||search==""&&search==null){%>
	<h3>목록 (검색어: <%= search %> )</h3>
	<%}else { %>
	<h3>목록</h3>
	<%} %>
</body>
</html>