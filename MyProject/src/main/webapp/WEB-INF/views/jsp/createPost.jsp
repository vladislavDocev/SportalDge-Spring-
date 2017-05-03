
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
    <script>
		function saveAjax(){
			if ( CKEDITOR.instances.editor1.getData() == '' ){
				alert( 'There is no data available.' );
			}
			else{
				var content = CKEDITOR.instances.editor1.getData();
				
				$.ajax({
					  url: "/MyProject/makePost",
					  type: "POST", //send it through get method
					  contentType : 'application/json; charset=utf-8',
					  dataType : 'json',
					  data: JSON.stringify(
							  	{
									content:content,	  		
									header: document.getElementById("header").value,
									category: document.getElementById("category").value,
							  	}			  
					  ),
					  success: function() {
						  document.getElementById("status").innerHTML = "";	  
						  	  
						  document.getElementById("status").innerHTML = "success";	  
					  		
					  },
					  error: function() {
						  document.getElementById("status").innerHTML="film";
					  }
					});
				
			}
		}

	</script>
</head>

<body>
    <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

    <nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
                <a class="navbar-brand" href="home"><img src="img/logo.png" alt=""></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                   
                    <li><a href="home">Home</a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    
		<div >
			
			<label for="category">choose category</label> <select class="form-control"
			id="category" style="margin-bottom: 4px">
			<c:forEach var="entry" items="${categories}">
			<option><c:out value="${entry.value.name}" /></option>
			</c:forEach>

		</select>
			
			<label for = "header"> header </label>
			<input class="form-control" type = "text" name = "header" id = "header"/>
			<span class="input-group-btn">
			
			<script src = "https://cdn.ckeditor.com/4.6.2/full/ckeditor.js"></script>
			<textarea name = "editor1"></textarea>
			
			<script>
				CKEDITOR.replace('editor1');
			</script>
			
			<input class="btn btn-default" type = "button" value = "Save" id = "Save" onclick = "saveAjax()" />
			<span class="glyphicon glyphicon-search"></span>
                                </span>
		</div>	
		
    
<div class="col-md-12 fix-container">
        <main class="col-md-12">
            <div class="container" style="margin-top:6em">
					
                <!-- Footer -->
                <footer>
                    <div class="row">
                        <div class="col-lg-12">
                            <p>Created By Tsanko and Vladislav</p>
                        </div>
                    </div>
                    <!-- /.row -->
                </footer>

            </div>
        </main>

    </div>

<div >
<input type="hidden" id="refreshed" value="no">
<script type="text/javascript">
onload=function(){
var e=document.getElementById("refreshed");
if(e.value=="no")e.value="yes";
else{e.value="no";location.reload();}
}
    <!-- /container -->
    <script src="js/vendor/moment.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script>
        window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')
    </script>
    <script src="js/vendor/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>