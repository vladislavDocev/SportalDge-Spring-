<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

	<script>
	function saveAjax(){
   		// Your code to save "data", usually through Ajax.
		if ( CKEDITOR.instances["editor1"].getData() == '' ){
			alert( 'There is no data available.' );
		}
		else{
			var data = CKEDITOR.instances["editor1"].getData();
			alert( data );
			CKEDITOR.ajax.post( 'url/admin.jsp', data, null, function( data ) {
				alert( data );
			});
		}
	}
	saveAjax();

	</script>
	
<body style="background-color: #CCCCCC;">
	<a href="/index"><img
		src="img/2e967dab-b61f-48c5-b44b-39f61053c789.png"></a>
	<hr>
	<h1 style="text-align: center;">Create Posts</h1>
	<hr>
	
	
	<form id="createPost" action="makePost" method="post">
		
		<label for="textinput1">header</label>
		<input type = "text" />
		<label for="textinput2">category</label>
		<input type = "text" />
		
		<script src="https://cdn.ckeditor.com/4.6.2/full/ckeditor.js"></script>
		<textarea name="editor1"></textarea>
		<script>
			CKEDITOR.replace('editor1');
		</script>
		
		<input type="button" value="Save" id="Save" onclick="saveAjax()" />
	</form>	
	
	
<!-- 	<script>
	function saveAjax(){
		var data = CKEDITOR.instances.editor1.getData();
   		// Your code to save "data", usually through Ajax.
		if ( CKEDITOR.instances.editor1.getData() == '' ){
			alert( 'There is no data available.' );
		}
		else{
			alert(data);
		}
	}
	saveAjax();

	</script> -->
	
	
	<!-- <script type="text/javascript">
        $('#Save').click(function () {
        	
            var form = $("#createPost");
            var url = form.attr("action");
            var formData = CKEDITOR.instances.editor1.getData();
            $.post(url, formData, function (data) {
                $("#msg").html(data);
            });
        })
    </script> -->
    
    
</body>
</body>
</html>