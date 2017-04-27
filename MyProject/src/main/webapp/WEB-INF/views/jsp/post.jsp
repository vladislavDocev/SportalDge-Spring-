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
	<a href="/index"><img
		src="img/2e967dab-b61f-48c5-b44b-39f61053c789.png"></a>
	<hr>
	<h1 style="text-align: center;">News</h1>
	<hr>
	<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/bg_BG/sdk.js#xfbml=1&version=v2.9";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

	<div class="h_nav">

		<hr>
		<c:forEach items="${post.pictures}" var="pic">
			<img src="img/${pic.mediaLink}" />
		</c:forEach>
		<h1>
			<c:out value="${post.header}" />
		</h1>
		<br>
		<c:out value="${post.content}" />
		<br> Author:
		<c:out value="${post.authorUsername}" />
		<br> Date:
		<c:out value="${post.date}" />
		<div class="fb-comments" data-href="" data-numposts="5"></div>
		<br> <br> <br>
			Comments: 
			<c:forEach items = "${post.comments}" var="comment">
				<c:out value = "${comment.description}"/><br>
				From: <c:out value = "${comment.username}"/><br>
					Posted on: <c:out value = "${comment.date}"/><br>
				Likes: <c:out value = "${comment.likes}"/><br>
				Dislikes: <c:out value = "${comment.dislikes}"/>
			</c:forEach>
		<hr>
	</div>
	
	<form:form commandName="user" action="login" method="post">
		<!-- Handle errors -->
		<form:errors path="*" cssClass="errorblock" element="div" />

		<form:textarea path = "Context"></form:textarea>

		<input type="submit" value="Login" />
	</form:form>
	
</body>
</html>