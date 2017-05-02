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
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body style="background-color: #CCCCCC;">
	<a href="index"><img
		src="C:\Users\Tsanko\Desktop\images\2e967dab-b61f-48c5-b44b-39f61053c789.png"></a>
	<hr>
	<h1 style="text-align: center;">Welcome to the world of sports</h1>
	<hr>

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

	<hr>
	<!-- Search Well -->
	<form action = "search" method = "post">
	<div class="well">
		<h4>Search</h4>
		<label for="category">choose category</label> <select name = "category"class="form-control"
			id="category" style="margin-bottom: 4px">
			<c:forEach var="entry" items="${categories}">
			<option value = "${entry.value.name}">${entry.value.name}</option>
			</c:forEach>

		</select>
		<div class="input-group">
			<label for = "header"> header </label>
			<input type = "text" name = "header" id = "header"/>
				<input type="submit" value="Search" />
		</div>
		<!-- /.input-group -->
	</div>
	</form>
	<hr>

	<div class="h_nav">
		<c:forEach var="entry" items="${posts}">

			<hr>
			<div>
				<h1>
					<a href="post/${entry.value.postID}"><c:out
							value="${entry.value.header}" /></a>
				</h1>
			</div>
			<div>
				<a href="post/${entry.value.postID}"> <c:out
						value="${entry.value.content}" escapeXml="false" /></a>
			</div>

			<div>
				Author :
				<c:out value="${entry.value.authorUsername}" />
			</div>
			<div>
				Date :
				<c:out value="${entry.value.date}" />
			</div>
			<br>
			<br>
			<br>
			<hr>
		</c:forEach>
	</div>
</body>
</html>