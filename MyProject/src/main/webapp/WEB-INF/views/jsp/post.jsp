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
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body style="background-color: #CCCCCC;">
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
					comment : document.getElementById("comment").value,
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
		<c:forEach items="${post.comments}" var="comment">
			<c:out value="${comment.description}" />
			<br>
				From: <c:out value="${comment.username}" />
			<br>
					Posted on: <c:out value="${comment.date}" />
			<br>
				Likes: <c:out value="${comment.likes}" />
			<br>
			<br>
			<form method="post" action="comment/likeComment">
				<div style="width: 400px;"></div>
				<div style="padding-bottom: 18px;">
					<input type="text" id="postId" name="postId" value="${post.postID}"
						hidden="true"> <input type="text" id="commentId"
						name="commentId" value="${comment.commentID}" hidden="true">
					<input value="Like" type="submit">

					<div class="post" postid="${post.postID}"></div>
				</div>
			</form>
			<br>
		</c:forEach>
		<hr>
	</div>
	<div style="padding-bottom: 18px;">
		Add comment :<span style="color: red;"></span><br /> 
		<textarea id="comment"
				name="comment" rows="10" align="middle" class="form-control"
				required></textarea>
	<div style="padding-bottom: 18px;">
		<button type="button" id="Save" onclick="postComment()">Post comment</button>
	</div>
</div>

</body>
</html>