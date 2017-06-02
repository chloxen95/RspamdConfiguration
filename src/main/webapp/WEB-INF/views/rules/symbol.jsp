<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Symbol Reader</title>
</head>
<body>
	<div class="content">
		<div class="md-col-9" style="overflow-y:scroll">
			<table  class="table table-bordered table-hover" id="symbol-detail" border="1">
				<tr><th>Symbol Name</th><th>Weight</th><th>Description</th>
				<th>Frequency</th><th>Frequency Stddev</th><th>Time</th><th>Group</th></tr>
			</table>
		</div>
	</div>
<script type="text/javascript">
$(function(){
	/*$.ajax({
		type : "GET",
		url : "rules/symbol/json",
		datatype : "json",
		success : function(result){
			alert(typeof(JSON.parse(result["symbol"])));
		}
	})*/
	$.ajax({
		type : "GET",
		url : "rules/symbol/db",
		datatype : "json",
		success : function(result){
			$.each(result.symbol, function(index, array){
				$("#symbol-detail").append("<tr><th>" + array[0] + "</th><th>" + array[1]
				+ "</th><th>" + array[2] + "</th><th>" + array[3] + "</th><th>"
				+ array[4] + "</th><th>" + array[5] + "</th><th>" + array[6] + "</th></tr>");
			})
		}
	})
	
	
})
</script>
</body>
</html>