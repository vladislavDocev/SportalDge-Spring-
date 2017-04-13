<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: #CCCCCC;">
	<a href="index.jsp"><img
		src="images/2e967dab-b61f-48c5-b44b-39f61053c789.png"></a>
	<hr>
	<h1 style="text-align: center;">Welcome to the world of sports</h1>
	<h3>Failed to login</h3>
	<hr>
</head>
<script type="text/javascript">
	
	function register() {
		location = "registerPage.jsp";
	}
</script>
<body>

	<form action="login" method="post">
		username<br> <input type="text" name="username" /><br>
		password <br> <input type="password" name="password" /><br>
		<br> <input type="submit" value="Login"/>
	</form>
	
	<p>Or you can register here:</p>
	<input type="button" name="register" value="Register"
		onClick="register()" />

</body>


</html>