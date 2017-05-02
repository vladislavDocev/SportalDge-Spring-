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
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="apple-touch-icon" href="apple-touch-icon.png">
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- <script src="js/less-1.7.0.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="css/main.css" />
<!--<link href="css/main.less" rel="stylesheet/less" type="text/css" /> 
         <script src="./js/less-1.7.0.min.js" type="text/javascript"></script> 
         <script type="text/javascript" charset="utf-8"> 
            less.env = "development";
           less.watch();
        </script>-->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>

<body>

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
		<c:forEach items="${post.comments}" var="entry" varStatus="loop">
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
				<button class="btn btn-primary" type="button" id="SaveLike"
					onclick="likeComment(${entry.value.commentID})">Like</button>
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


	<!-- /container -->
	<script type="text/javascript">
onload=function(){
var e=document.getElementById("refreshed");
if(e.value=="no")e.value="yes";
else{e.value="no";location.reload();}
}
</script>
	<script src="js/vendor/moment.min.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
        window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')
    </script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script src="js/main.js"></script>
</body>

</html>