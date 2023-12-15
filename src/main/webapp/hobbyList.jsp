<%@ page import="db.dto.HobbyDTO" %>
<%@ page import="db.dao.HobbyDAO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<%@ include file="navigation.jsp" %>
	<% 
		HobbyDAO hobbyDAO = new HobbyDAO();
		List<HobbyDTO> hobbyList = hobbyDAO.selectHobbyList();
	%>	
		
		<h1>취미목록</h1> 
		<a href="./index.jsp">메인메이지로 이동</a>
		
		<table>
			<tr>
				<th>아이디</th>
				<th>순번</th>
				<th>취미</th>
				<th>선호도</th>
			</tr>

	<%
		if(hobbyList !=null){
			for(HobbyDTO hobbyInfoDAO : hobbyList) {
			
	%>
		<tr>
		<td><%=hobbyInfoDAO.getId()%></td>
		<td><%=hobbyInfoDAO.getNo()%></td>
		<td><%=hobbyInfoDAO.getHobby()%></td>
		<td><%=hobbyInfoDAO.getPrefer()%></td>
		</tr>	
         
	<% 
		}
	}
	%>	
	
	</table>

</body>
</html>