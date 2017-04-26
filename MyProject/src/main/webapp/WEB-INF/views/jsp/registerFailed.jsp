<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
	<h3>Registration failed: username and/or email already exist.</h3>
	<form:form id="form" method="post" action="registered"
		commandName="user">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input id="name" path="name" required="true" /></td>
				<td><font id="nameError" style="color: red;"> </font></td>
			</tr>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input id="username" path="username" required="true" /></td>
				<td><font id="usernameError" style="color: red;"> </font></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input id="password" path="password" type="password"
						required="true" /></td>
				<td><font id="passwordError" style="color: red;">${passwordError}
				</font></td>
			</tr>
			<tr>
				<td><form:label path=""> Confirm Password</form:label></td>
				<td><form:input id="confirmPassword" path="" type="password"
						required="true" /></td>
				<td><font id="confirmPasswordError" style="color: red;">
				</font></td>
			</tr>
			<tr>
				<td><form:label path="email"> Email</form:label></td>
				<td><form:input id="email" path="email" required="true" /></td>
				<td><font id="emailError" style="color: red;">${invalidEmailFormat}
				</font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					onclick="return validate()" value="Register" /></td>
			</tr>
		</table>
	</form:form>

	<script>
		function validate() {
			var f = document.getElementById("form");
			return (validateEmail(f) & validatePassword(f) & validateUsername(f));
		}

		function validateEmail(form) {
			var error = document.getElementById("emailError");

			var email = form["email"].value;
			error.innerHTML = "";
			var regx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			if (!email.match(regx)) {
				error.innerHTML = "Email is not written in the correct format.";
				event.preventDefault();
			}
			return "" === error.innerHTML;
		}

		function validatePassword(form) {
			var error1 = document.getElementById("passwordError");
			var error2 = document.getElementById("confirmPasswordError");
			error1.innerHTML = "Incorrect Password";
			var password = form["password"].value;

			error1.innerHTML = "";
			error2.innerHTML = "";

			var confirmPassword = form["confirmPassword"].value;

			if (password.length < 6) {
				error1.innerHTML = "Password needs to have more than 6 symbols."
				event.preventDefault();
			}

			var re = /[0-9]/;

			if (!password.match(re)) {
				error1.innerHTML = "Password must contain at least one number."
				event.preventDefault();
			}
			re = /[a-z]/;
			if (!password.match(re)) {
				error1.innerHTML = "Password must contain at least one lowercase letter."
				event.preventDefault();
			}
			re = /[A-Z]/;
			if (!password.match(re)) {
				error1.innerHTML = "Password must contain at least one uppercase."
				event.preventDefault();
			}

			if (password != confirmPassword) {
				error2.innerHTML = "Passwords do not match."
				event.preventDefault();
			}
			return "" === error.innerHTML;
		}

		function validateUsername(form) {
			var error = document.getElementById("usernameError");

			var username = form["username"].value;
			var password = form["password"].value;
			error.innerHTML = "";

			if (username == password) {
				error.innerHTML = "Username cannot be the same as the password";
				event.preventDefault();
			}
			return "" === error.innerHTML;
		}
	</script>
</body>
</html>