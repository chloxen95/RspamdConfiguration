<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<meta name="author" content="chloxen95">
</head>
<body>
	<div class="content">
		<div class="col-md-3 text-content-block">
			<div id="file-list"></div>
		</div>
		<div class="col-md-9 text-content-block">
			<div id="conf-text" class="text-content"></div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {

			function readConfJson(result) {
				$.each(result, function(key, value) {
					if (key == ".include") {
						$("#conf-name").append("<b>.include</b>");
						$("#conf-text").append(function() {
							return value;
						});
					}
				})
			}
			
			$.ajax({
				type : "GET",
				url : "",
				datatype : "json",
				success : function(result){
					$.each(result, function(index, str){
						$("#file-list").append("<h4>"+str+"</h4>");
					})
				}
			})

			$.ajax({
				type : "POST",
				url : "file/entire",
				datatype : "json",
				data : {
					path : "src/test/resources/options.inc",
					comment : false,
					blankLine : false
				},
				success : function(result) {
					text = "";
					$("#conf-text").append(
							"<textarea id=\"file-context\" style=\"width: 100%; height: 100%\" rows="
									+ result.length + " col=500></textarea>")
					$.each(result, function(index, item) {
						text = text + item + "\n";
					})
					$("#file-context").val(text);
				}
			})
		})
	</script>
</body>
</html>
