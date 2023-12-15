<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.dao.PersonInfoDAO" %>
<%@ page import="db.dto.PersonInfoDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<%@ include file="navigation.jsp" %>
<%
		request.setCharacterEncoding("UTF-8"); //문자인코딩 설정
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if( name == null){
			//이름이 안넘어왔는디?
		}
		System.out.println("modifyPerson_proc 파라미터 : " + id + " " + name);
		int intId = Integer.parseInt(id);
		
		PersonInfoDAO personInfoDAO = new PersonInfoDAO();
		
		PersonInfoDTO personInfo = new PersonInfoDTO(intId, name);
		
		int result = personInfoDAO.modifyPersonInfo(personInfo);
		
		if(result > 0) {
	%>
	
		<script>
			alert('수정 성공'); 
			location.href = 'personInfo.jsp?id=<%=id%>'; 	//해당 경로로 이동
		</script>
		
	<% 
		} else {
	%>		
		<script>
			alert('수정 실패');
			//location.href = 'addPeson.jsp'; //해당 경로로 이동
			history.back(); //뒤로 이동
		</script> 
	<% 
		}
	%>

</body>
</html>