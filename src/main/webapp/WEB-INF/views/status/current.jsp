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
			<table class="table table-bordered table-hover" id="file-list" border="1">
				<tr><th style="background-color: lightgray;">Conf Files</th></tr>
			</table>
		</div>
		<div class="col-md-9 text-content-block">
			<div id="conf-text" class="content text-content" style=" overflow-y: scroll;">
				<div class="col-md-1"></div>
				<div class="col-md-9" style="height: 100%;">
					<!-- <div><pre><code style="color: graytext;">
# Systemd enabled top level configuration file
#
# Please don't modify this file as your changes might be overwritten with
# the next update.
#
# You can modify '$LOCAL_CONFDIR/rspamd.conf.local.override' to redefine
# parameters defined on the top level
#
# You can modify '$LOCAL_CONFDIR/rspamd.conf.local' to add
# parameters defined on the top level
#
# For specific modules or configuration you can also modify
# '$LOCAL_CONFDIR/local.d/file.conf' - to add your options or rewrite defaults
# '$LOCAL_CONFDIR/override.d/file.conf' - to override the defaults
#
# See https://rspamd.com/doc/tutorials/writing_rules.html for details
					</code></pre></div> -->
					<div class="panel panel-default" style="padding: 2px 20px; background-color: #d1dae0;">
						<h4><b>Include&nbsp&nbsp&nbsp</b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						Path: <input style="width: 290px; background-color: #d1dae0;" value="$CONFDIR/common.conf"></h4>
					</div>
					<div class="panel panel-default" style="padding: 2px 20px; background-color: #d1dae0; height: 600px;">
						<div class="panel-heading" style="padding: 0; border: 0; margin-top: 15px;">
							<h4 class="panel-title" style=" background-color: #d1dae0"><b>Options</b></h4>
						</div>
						<div style="padding: 2px 20px; margin-top: 15px;">
							<div class="panel panel-default" style="padding: 0 12px">
								<h4>type: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="console"></h4>
							</div>
						</div>
						<div style="padding: 2px 20px;">
							<div class="panel panel-default" style="padding: 0 12px">
								<h4>systemd: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="true"></h4>
							</div>
						</div>
						<div style="padding: 2px 20px; width: 400px;">
							<div class="panel panel-default" style="padding: 0 12px">
								<h4><b>Include</b></h4>
								<h4>try: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="Upecified"></h4>
								<h4>priority: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="Upecified"></h4>
								<h4>duplicate: &nbsp&nbsp&nbsp<input value="Upecified"></h4>
								<h4>Path: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="$CONFDIR/options.inc"></h4>
							</div>
						</div>
						<div style="padding: 2px 20px; width: 400px; position: relative; bottom: 204px; left: 400px">
							<div class="panel panel-default" style="padding: 0 12px">
								<h4><b>Include</b></h4>
								<h4>try: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="True"></h4>
								<h4>priority: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="1"></h4>
								<h4>duplicate: &nbsp&nbsp&nbsp<input value="merge"></h4>
								<h4>Path: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="$CONFDIR/options.inc"></h4>
							</div>
						</div>
						<div style="padding: 2px 20px; width: 400px; position: relative; bottom: 204px;">
							<div class="panel panel-default" style="padding: 0 12px">
								<h4><b>Include</b></h4>
								<h4>try: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="True"></h4>
								<h4>priority: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="10"></h4>
								<h4>duplicate: &nbsp&nbsp&nbsp<input value="Upecified"></h4>
								<h4>Path: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="$LOCAL_CONFDIR/override.d/options.inc"
></h4>
							</div>
						</div>
						<div style="padding: 2px 20px; width: 400px; position: relative; bottom: 408px; left: 400px">
							<div class="panel panel-default" style="padding: 0 12px">
								<h4><b>Include</b></h4>
								<h4>try: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="True"></h4>
								<h4>priority: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="1"></h4>
								<h4>duplicate: &nbsp&nbsp&nbsp<input value="merge"></h4>
								<h4>Path: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input value="$CONFDIR/options.inc"></h4>
							</div>
						</div>
					</div>
				</div>
			</div>
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
					/*$("#conf-text").empty();
					text = "";
					$("#conf-text").append(
							"<pre style=\"width: 100%; height: 100%\" ><textarea id=\"file-context\"  "
									+"style=\"width: 100%; height: 100%\" rows="
									+ result.length + " col=500></textarea></pre>")
					$.each(result, function(index, item) {
						text = text + item + "\n";
					})
					$("#file-context").val(text);*/
				}
			}))
		}
	
	
		$(function() {
			$("input").css("border-radius", "2px");
			$("input").css("border", "3px");

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
