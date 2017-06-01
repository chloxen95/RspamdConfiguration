<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Conf Reader</title>
<meta name="author" content="chloxen95">
</head>
<body>
	<div class="content">
		<div class="col-md-3 text-content-block" style="overflow-y:scroll">
			<table class="table table-bordered table-hover" id="file-list" border="1"></table>
		</div>
		<div class="col-md-9 text-content-block">
			<div id="conf-text" class="text-content"></div>
		</div>
	</div>
	<script type="text/javascript">
		function getFileContext(fileName){
			$($.ajax({
				type : "POST",
				url : "configuration/context",
				datatype : "json",
				data : {
					fileName : fileName,
					comment : true,
					blankLine : true
				},
				success : function(result) {
					$("#conf-text").empty();
					text = "";
					$("#conf-text").append(
							"<pre style=\"width: 100%; height: 100%\" ><textarea id=\"file-context\"  "
									+"style=\"width: 100%; height: 100%\" rows="
									+ result.length + " col=500></textarea></pre>")
					$.each(result, function(index, item) {
						text = text + item + "\n";
					})
					$("#file-context").val(text);
				}
			}))
		}
	
	
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
				type : "POST",
				url : "configuration/list",
				datatype : "json",
				data : {
					path : "",
					postfix : "conf"
				},
				success : function(result){
					$.each(result, function(index, str){
						if(typeof(str) == "object"){
							$.each(str, function(index, name){
								$("#file-list").append("<tr><th class=\"file-name-th\" onclick=\"getFileContext(this.innerText)\">"+name+"</th></tr>");
							})
						}
						else
							$("#file-list").append("<tr><th class=\"file-name-th\" onclick=\"getFileContext(this.innerText)\">"+str+"</th></tr>");
					})
				}
			})
			
			$("th.file-name-th").click(function(){
				getFileContext(this.innerText);
			})

			function getFileContext(fileName){
				$.ajax({
					type : "POST",
					url : "configuration/context",
					datatype : "json",
					data : {
						fileName : fileName,
						comment : true,
						blankLine : true
					},
					success : function(result) {
						text = "";
						$("#conf-text").append(
								"<pre style=\"width: 100%; height: 100%\" ><textarea id=\"file-context\"  "
										+"style=\"width: 100%; height: 100%\" rows="
										+ result.length + " col=500></textarea></pre>")
						$.each(result, function(index, item) {
							text = text + item + "\n";
						})
						$("#file-context").val(text);
					}
				})
			}
		})
	</script>
</body>
</html>
