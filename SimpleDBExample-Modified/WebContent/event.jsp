<!-- PRABHAT: Similar to user.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
	href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="style/theme.css">

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>MIE350 Sample DB Web App: Add/Update An Event</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>.

	<div class="container">
		<center>
			<h1>Add/Update An Event</h1>
		</center>
		<script>
			$(function() {
				$('input[name=dob]').datepicker();
			});
		</script>
		Note: the Event ID is a fixed field and cannot be changed. <br> <br>

		<form method="POST" action='EventController' name="frmAddEvent">
			Event ID*: <input type="text" readonly="readonly" name="eventid"
				value="<c:out value="${event.eventid}" />"><br> Event
			Name: <input type="text" name="eventName"
				value="<c:out value="${event.eventName}" />"><br> <input
				type="submit" value="Submit" />
		</form>
	</div>	
</body>
</html>