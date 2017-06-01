<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Rule Reader</title>
<meta name="author" content="chloxen95">
</head>
<body>
	<div class="content">
		<div class="col-md-3 text-content-block" style="overflow-y:scroll">
			<table class="table table-bordered table-hover" id="file-list-lua" border="1">
				<tr><th style="background-color: lightgray;">Lua Rule</th></tr>
			</table>
			<div><br></div>
			<table class="table table-bordered table-hover" id="file-list-regexp" border="1">
				<tr><th style="background-color: lightgray;">Regexp Rule</th></tr>
			</table>
		</div>
		<div class="col-md-9 text-content-block">
			<div id="conf-text" class="text-content">
				<pre style="width: 100%; height: 100%"><code id="file-context" class="lua"></code></pre>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getFileContext(fileName){
			$.ajax({
				type : "POST",
				url : "rules/context",
				datatype : "json",
				data : {
					fileName : fileName,
					comment : true,
					blankLine : true
				},
				success : function(result) {
					$("#file-context").empty();
					text = "";
					$.each(result, function(index, item) {
						$("#file-context").append(item + "\n");
					})
					$("#file-context").each(function(i, block) {
					    hljs.highlightBlock(block);
					});
				}
			});
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
				url : "rules/list",
				datatype : "json",
				data : {
					type : "rule",
					postfix : "lua"
				},
				success : function(result){
					$.each(result, function(index, str){
						if(str.split("_")[0] == "lua")
							$("#file-list-lua").append("<tr><th class=\"file-name-th\" onclick=\"getFileContext(this.innerText)\">"+str+"</th></tr>");
						else if(str.split("_")[0] == "regexp")
							$("#file-list-regexp").append("<tr><th class=\"file-name-th\" onclick=\"getFileContext(this.innerText)\">"+str+"</th></tr>");
					})
				}
			})
		})
	</script>
</body>
</html>
