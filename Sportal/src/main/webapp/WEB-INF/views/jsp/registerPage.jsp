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
	<hr>

	<form action="register" method="post" onsubmit="return checkForm(this);">
		Name:<br> <input type="text" class="inputbox" name="name" id="name" required><br>
	    Username:<br> <input type="text" class="inputbox" name="username" id="username" required><div id = "usernameError" style = "red"></div>
		Password:<br> <input type="password" class="inputbox" name="password" id="password" required> <br> 
		Confirm password:<br> <input type="password" class="inputbox" name="confirmPassword" id="confirmPassword" required> <br>
		Email:<br> <input type="text" class="inputbox" name="email" id="email" pattern="^(([-\w\d]+)(\.[-\w\d]+)*@([-\w\d]+)(\.[-\w\d]+)*(\.([a-zA-Z]{2,5}|[\d]{1,3})){1,2})$"required> <br> <br> <input type="submit" value="Register">
	</form>

	<script >
		function validateEmail(email) {
			var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			return re.test(email);
		}
		//src = "../js/usernamePassValidation.js"
		function checkForm(form) {
			if (form.username.value == "") {
				alert("Error: Username cannot be blank!");
				form.username.focus();
				return false;
			}
			re = /^\w+$/;
			if (!re.test(form.username.value)) {
				alert("Error: Username must contain only letters, numbers and underscores!");
				
				/*document.getElementById("usernameError").innerHTML = "username must contain only letters, numbers and ubderscores!";
				doucment.getElementById("usernameError").style.color = "red";*/
				form.username.focus();
				return false;
			}

			if (form.password.value != ""
					&& form.password.value == form.confirmPassword.value) {
				if (form.password.value.length < 6) {
					alert("Error: Password must contain at least six characters!");
					form.password.focus();
					return false;
				}
				if (form.password.value == form.username.value) {
					alert("Error: Password must be different from Username!");
					form.password.focus();
					return false;
				}
				re = /[0-9]/;
				if (!re.test(form.password.value)) {
					alert("Error: password must contain at least one number (0-9)!");
					form.password.focus();
					return false;
				}
				re = /[a-z]/;
				if (!re.test(form.password.value)) {
					alert("Error: password must contain at least one lowercase letter (a-z)!");
					form.password.focus();
					return false;
				}
				re = /[A-Z]/;
				if (!re.test(form.password.value)) {
					alert("Error: password must contain at least one uppercase letter (A-Z)!");
					form.password.focus();
					return false;
				}
			} else {
				alert("Error: Please check that you've entered and confirmed your password!");
				form.password.focus();
				return false;
			}

			return true;
		}
	</script>
</body>
</html>