<!-- Prabhat: Similar to listUser.jsp ; Made to handle the "Events" database-->

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="style/theme.css">

<title>MIE350 Sample DB Web App</title>
</head>
<body>

	<%@ include file="navbar.jsp"%>
	<center>
		<h1>Show All Events</h1>
	</center>
	<div class="container-noborder">

		<%
			String name = request.getParameter("firstName");
			session.setAttribute("session_name", name);
		%>

		Hello
		<%out.print(session.getAttribute("session_name"));%>!<br>
		<br> The time is now <b><%=new java.util.Date()%></b>.<br> <br>
		The following events are in your database: <br> <br> <a
			href="EventController?action=insert">Add A New Event</a> <br> <br>
		<table border=1>
			<thead>
				<tr>
					<th>Event Id</th> 
					<th>Name</th> <!-- Prabhat: Just Id and Name for now -->
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${events}" var="event">
					<tr>
						<td align="center"><c:out value="${event.getEventid()}" /></td>
						<td align="center"><c:out value="${event.getEventName()}" /></td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>