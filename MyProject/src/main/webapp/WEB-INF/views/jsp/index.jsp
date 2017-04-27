<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


	<form:form commandName="user" action="login" method="post">
		<!-- Handle errors -->
		<form:errors path="*" cssClass="errorblock" element="div" />

		<label for="textinput1">username</label>
		<form:input path="username" cssErrorClass="error" required="true" />
		<form:errors path="username" cssClass="error" />
		<br>

		<label for="textinput2">password</label>
		<form:input type="password" path="password" cssErrorClass="error"
			required="true" />
		<form:errors path="password" cssClass="error" />
		<br>

		<input type="submit" value="Login" />
	</form:form>


	<p>Or you can register here:</p>

	<form action="register" method="post">
		<br> <input type="submit" value="Register" />
	</form>

	<div class="h_nav">
		<c:forEach var="entry" items="${posts}">
			<tr>
				<td>
					<c:forEach items = "${entry.value.pictures}" var = "pic">
						<img alt="picture" src="${pic.mediaLink}">
					</c:forEach>
				</td>
  				<td><h1><c:out value="${entry.value.header}" /></h1></td>
  				<br>
  				<td><c:out value = "${entry.value.content}"/></td>
  				<br>
  				<td>Author: <c:out value = "${entry.value.authorUsername}"/></td>
  				<br>
  				<td>Date: <c:out value = "${entry.value.date}"/></td>
  				
  			</tr>
		</c:forEach>
	</div>
</html>