
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.User"%>



<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body>
<div class = "container">
<div class = "row">
<div class = "col">
	<h1>User Management</h1>
	
	<form id="formUser" name="formUser" method="post" action="User.jsp">
		 Name :
		 <input id="Name" name="Name" type="text"
 						class="form-control form-control-sm">
 						
		<br> Address:
		<input id="Address" name="Address" type="text"
 						class="form-control form-control-sm">
 						
		<br> TelephoneNo:
		<input id="TelephoneNo" name="TelephoneNo" type="text"
 						class="form-control form-control-sm">
 						
 						
		<br> PremisesId:
		<input id="PremisesId" name="PremisesId" type="text"
						 class="form-control form-control-sm">
						 
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
						 class="btn btn-primary">
						 
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<br/>
	<!-- Show output -->

	<div id= "alertSuccess" class="alert alert-success">
 	
 		
 	</div>
 	<div id = "alertError" class="alert-danger"></div>
	
	<br>
	<br>
	
	<div id ="divUserGrid">
	<%
	 User userObj = new User(); 
	 out.print(userObj.readUser()); 
	%>
	</div>

</body>
</html> 