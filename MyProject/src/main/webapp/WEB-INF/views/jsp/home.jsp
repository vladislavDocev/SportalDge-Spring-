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
                <a class="navbar-brand" href="#"><img src="img/logo.png" alt=""></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                	<li><a href="mostViewed">Most Viewed</a></li>
                    <li><a href="home">Home</a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <br>
    <br>
    <br>
    <form action = "search" method = "post">
    <div class="well">
                            <h4>Search</h4>
                            <label for="category">choose category</label>
                            <select name = "category"class="form-control" id="category" style="margin-bottom: 4px">
							<c:forEach var="entry" items="${categories}">
							<option value = "${entry.value.name}">${entry.value.name}</option>
							</c:forEach>
							</select>
                            <div class="input-group">
                            <label for= "header"></label>
                                <input class="form-control" type="text" id="header" name = "header">
                                <span class="input-group-btn">
                            <input class="btn btn-default" type="submit" value="search">
                                <span class="glyphicon glyphicon-search"></span>
                                </span>
                            </div>
                            <!-- /.input-group -->
                        </div>
    			</form>
       
                        
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
            <input type="hidden" id="refreshed" value="no">
<script type="text/javascript">
onload=function(){
var e=document.getElementById("refreshed");
if(e.value=="no")e.value="yes";
else{e.value="no";location.reload();}
}
</script>
</body>

</html>