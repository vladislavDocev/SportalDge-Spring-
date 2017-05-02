<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="bg">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="apple-touch-icon.png" rel="apple-touch-icon">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- <script src="js/less-1.7.0.min.js"></script> -->
    <link type="text/css" href="css/main.css" rel="stylesheet">
    <!--<link type="text/css" href="css/main.less" rel="stylesheet/less">
    <script src="./js/less-1.7.0.min.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
        less.env = "development";
        less.watch();
    </script>-->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>

<body>
    <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

    <nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button class="navbar-toggle collapsed" data-target="#bs-example-navbar-collapse-1" data-toggle="collapse" type="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
                <a class="navbar-brand" href="#"><img src="img/logo.png" alt></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
           </div>
        <!-- /.container-fluid -->
    </nav>
    <br>
   
   <form:form class="form" name="regForm" id="form" method="post" action="registered" accept-charset="UTF-8"
		commandName="user">
		 <div class="form-group">
				<form:label path="username">Username</form:label>
				<form:input class="form-control" name="username" id="username" path="username"
						required="true" placeholder="Username"/>
				<font id="usernameError" style="color: red;"> </font>
				</div>
				<div class="form-group">
			<form:label path="password">Password</form:label>
				<form:input class="form-control" name="password" id="password" path="password"
						type="password" required="true" placeholder="Password"/>
				<font id="passwordError" style="color: red;">${passwordError}
				</font>
				</div>
				<div class="form-group">
			<form:label path=""> Confirm Password</form:label>
				<form:input class="form-control" name="confirmPassword" id="confirmPassword"
						path="" type="password" required="true" placeholder="Repeat Password"/>
				<font id="confirmPasswordError" style="color: red;">
				</font>
				</div>
				<div class="form-group">
			<form:label path="email"> Email</form:label>
				<form:input class="form-control" name="email" id="email" path="email"
						required="true" placeholder="Email"/>
				<font id="emailError" style="color: red;">${invalidEmailFormat}
				</font>
				</div>
			<div class="form-group">
				<input class="btn btn-primary btn-block" type="submit"
					onclick="return validate()" value="Register" />
					</div>
	</form:form>
<script type="text/javascript">
onload=function(){
var e=document.getElementById("refreshed");
if(e.value=="no")e.value="yes";
else{e.value="no";location.reload();}
}
</script>
	<script>
		function validate() {
			var f = document.getElementById("form");
			var emailError = document.getElementById("emailError");
			var email = form["email"].value;
			var passwordError = document.getElementById("passwordError");

			var confirmPasswordError = document.getElementById("confirmPasswordError");
			passwordError.innerHTML = "Incorrect Password";

			passwordError.innerHTML = "";
			confirmPasswordError.innerHTML = "";

			var password = form["password"].value;
			var confirmPassword = form["confirmPassword"].value;

			emailError.innerHTML = "";
			
			var usernameError = document.getElementById("usernameError");

			var username = form["username"].value;
			var password = form["password"].value;
			usernameError.innerHTML = "";
			
			var regx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			
			
			if (!email.match(regx)) {
				emailError.innerHTML = "Email is not written in the correct format.";
				event.preventDefault();
			}
			
			if (password.length < 6) {
				passwordError.innerHTML = "Password needs to have more than 6 symbols."
				event.preventDefault();
			}

			var re = /[0-9]/;

			if (!password.match(re)) {
				passwordError.innerHTML = "Password must contain at least one number."
				event.preventDefault();
			}
			re = /[a-z]/;
			if (!password.match(re)) {
				passwordError.innerHTML = "Password must contain at least one lowercase letter."
				event.preventDefault();
			}
			re = /[A-Z]/;
			if (!password.match(re)) {
				passwordError.innerHTML = "Password must contain at least one uppercase."
				event.preventDefault();
			}

			if (password != confirmPassword) {
				confirmPasswordError.innerHTML = "Passwords do not match."
				event.preventDefault();
			}
			
			var trimmed = username.trim();
			
			if(trimmed == ""){
				usernameError.innerHTML = "Username cannot be void";
				event.preventDefault();
			}
			if (trimmed == password) {
				usernameError.innerHTML = "Username cannot be the same as the password";
				event.preventDefault();
			}
			if(emailError != "" && usernameError != "" && passwordError != "" && confirmPasswordError != "") {
				return true;
			}
			return false;
		}

		
	</script>

       
   
                       
</body>

</html>