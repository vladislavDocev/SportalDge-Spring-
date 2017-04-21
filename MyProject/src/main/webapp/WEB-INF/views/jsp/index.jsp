<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

	<form action="login" method="post">
		username<br> <input type="text" name="username" required /><br>
		password <br> <input type="password" name="password" required /><br>
		<br> <input type="submit" value="Login"/>
	</form>

	<p>Or you can register here:</p>
	
	<form action="register" method="post">
		<br> <input type="submit" value="Register"/>
	</form>
</body>


</html>