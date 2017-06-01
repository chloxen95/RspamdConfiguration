<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Symbol Reader</title>
</head>
<body>

<script type="text/javascript">
$(function(){
	$.ajax({
		type : "GET",
		url : "rules/symbol/line",
		datatype : "json",
		success : function(result){
			alert(typeof(JSON.parse(result["symbol"])));
		}
	})
	
})
</script>
</body>
</html>