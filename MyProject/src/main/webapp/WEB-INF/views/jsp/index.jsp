<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: #CCCCCC;">
	<a href="index"><img
		src="img/2e967dab-b61f-48c5-b44b-39f61053c789.png"></a>
	<hr>
	<h1 style="text-align: center;">Welcome to the world of sports</h1>
	<hr>
</head>

<body>

<script src="https://cdn.ckeditor.com/4.6.2/full/ckeditor.js"></script>
<form:form commandName="user" action = "login" method = "post">
		<!-- Handle errors -->
		<form:errors path="*" cssClass="errorblock" element="div"/>
		
		<label for="textinput1">username</label></br>
		<form:input path="username" cssErrorClass="error" required = "true"/>
		<form:errors path="username" cssClass="error"/>
		<br>
		
		<label for="textinput2">password</label></br>
		<form:input type = "password" path="password" cssErrorClass="error" required = "true"/>
		<form:errors path="password" cssClass="error" />
		<br>
		
		<input type="submit" value="Login" />
	</form:form>
	

	<p>Or you can register here:</p>

	<form action="register" method="post">
		<br> <input type="submit" value="Register" />
	</form>
	
	
	<!--<form method="post" action="comment" >
		<div style="width: 400px;">
		</div>
		<div style="padding-bottom: 18px;">Add comment<span style="color: red;"> *</span><br/>
			<textarea id="comment" ${readonly} name="comment" style="width : 450px;" rows="10" class="form-control"></textarea>
		</div>
		<div style="padding-bottom: 18px;"><input value="Submit" type="submit"></div>
	</form>
-->

<textarea name="editor1"></textarea>
        <script>
            CKEDITOR.replace( 'editor1' );
        </script>
</body>


</html>