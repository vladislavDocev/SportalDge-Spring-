

<html>
	<head>
	<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
	<title> Insert title here </title>
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</head>

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
	
	<body style = "background-color: #CCCCCC;">
		<a href = "/index"><img
			src = "img/2e967dab-b61f-48c5-b44b-39f61053c789.png"></a>
		<hr>
		<h1 style = "text-align: center;"> Create Posts </h1>
		<hr>
		
		
		<div >
			
			<label for = "header"> header </label>
			<input type = "text" name = "header" id = "header"/>
			
			<label for ="category">category</label>
			<input type = "text" name = "category" id = "category"/>
			
			<script src = "https://cdn.ckeditor.com/4.6.2/full/ckeditor.js"></script>
			<textarea name = "editor1"></textarea>
			
			<script>
				CKEDITOR.replace('editor1');
			</script>
			
			<input type = "button" value = "Save" id = "Save" onclick = "saveAjax()" />
		</div>	
		
		<h2 id = "status"></h2>
    
	</body>

</html>