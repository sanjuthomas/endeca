<!-- @author ourownjava.com -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" type="text/javascript"></script>
	<script src="js/jquery.autocomplete.js"></script>  
	<style>
		input {
			font-size: 120%;
		}
	</style>
</head>
<body>
<div align="center" style="font-family:Calibri;color:white;">
	<br/><br/><br/>
	<h3>>Endeca type ahead using dimension search (With ancestors). </h3>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>> Search Term </b> <input type="text" size="90" id="userInput1" name="userInput1"/>
	<script>
		$("#userInput1").autocomplete("productsWithAncestors.jsp");
	</script>
	
</div>
</body>
</html>