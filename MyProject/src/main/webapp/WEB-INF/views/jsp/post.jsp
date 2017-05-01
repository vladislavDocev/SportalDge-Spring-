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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body style="background-color: #CCCCCC;">
<img
		src="C:\Users\Tsanko\Desktop\images\2e967dab-b61f-48c5-b44b-39f61053c789.png">
	<hr>
	<h1 style="text-align: center;">News</h1>
	<hr>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/bg_BG/sdk.js#xfbml=1&version=v2.9";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	<script>
		function postComment() {
			
			$.ajax({
				url : "/MyProject/comment",
				type : "POST", //send it through get method
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				data : JSON.stringify({
					commentDesc : document.getElementById("commentDesc").value,
				}),
				success : function() {
					document.getElementById("status").innerHTML = "";

					document.getElementById("status").innerHTML = "success";

				},
				error : function() {
					document.getElementById("status").innerHTML = "film";
				}
			});
		}

		function likeComment(commentid) {
			$.ajax({
				url : "/MyProject/like",
				type : "POST", //send it through get method
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				data : JSON.stringify({
					commentID : commentid,//document.getElementById("commentID").value,
				}),
				success : function() {
					document.getElementById("status").innerHTML = "";

					document.getElementById("status").innerHTML = "success";

				},
				error : function() {
					document.getElementById("status").innerHTML = "film";
				}
			});
		}
	</script>
	<div class="h_nav">
		<hr>

		<h1>
			<c:out value="${post.header}" />
		</h1>
		<br>
		<div>
			<c:out value="${post.content}" escapeXml="false" />
		</div>
		<br>
		<div>
			Author :
			<c:out value="${post.authorUsername}" />
		</div>
		<br>
		<div>
			Date :
			<c:out value="${post.date}" />
		</div>
		<br> <br> <br>
		<div class="fb-comments" data-href="" data-numposts="5"></div>
		<br> <br> <br> Comments:
		<c:forEach items="${post.comments}" var="entry" varStatus = "loop">
			<c:out value="${entry.value.commentDesc}" />
			<br>
				From: <c:out value="${entry.value.username}" />
			<br>
					Posted on: <c:out value="${entry.value.date}" />
			<br>
				Likes: <c:out value="${entry.value.likes}" />
			<br>
			<br>
			<div>
				<button type="button" id="SaveLike" onclick="likeComment(${entry.value.commentID})">Like</button>
			</div>
			<br>
		</c:forEach>
		<hr>
	</div>
	<div style="padding-bottom: 18px;">
		Add comment :<span style="color: red;"></span><br />
		<textarea id="commentDesc" name="commentDesc" rows="10" align="middle"
			class="form-control" required></textarea>
		<div style="padding-bottom: 18px;">
			<button type="button" id="Save" onclick="postComment()">Post
				comment</button>
		</div>
	</div>

</body>
</html>