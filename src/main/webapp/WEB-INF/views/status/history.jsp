<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="content">
		<div class="col-md-6">
			<div id="main" style="width: 100%; height:800px;"></div>
		</div>
		<div class="col-md-3" style="height:800px;">
			<div class="history-detail-block">
				<div class="history-detail-content">
					<h3><b>SCANNED</b></h3>
					<h2>12411</h2>
					<h5>6% increased</h5>
					<span class="glyphicon glyphicon-envelope"></span>
				</div>
			</div>
			<br><br>
			<div class="history-detail-block">
				<div class="history-detail-content">
					<h3><b>CLEAN</b></h3>
					<h2>2530</h2>
					<h5>6% increased</h5>
					<span class="glyphicon glyphicon-ok"></span>
				</div>
			</div>
			<br><br>
			<div class="history-detail-block">
				<div class="history-detail-content">
					<h3><b>GRAYLIST</b></h3>
					<h2>1111</h2>
					<h5>6% increased</h5>
					<span class="glyphicon glyphicon-tags"></span>
				</div>
			</div>
			<br><br>
			<div class="history-detail-block">
				<div class="history-detail-content">
					<h3><b>SOFT REJECT</b></h3>
					<h2>2030</h2>
					<h5>6% increased</h5>
					<span class="glyphicon glyphicon-remove-circle"></span>
				</div>
			</div>
		</div>
		<div class="col-md-3" style="height:800px;">
			<div class="history-detail-block">
				<div class="history-detail-content">
					<h3><b>LEARNED</b></h3>
					<h2>21312</h2>
					<h5>6% increased</h5>
					<span class="glyphicon glyphicon-book"></span>
				</div>
			</div>
			<br><br>
			<div class="history-detail-block">
				<div class="history-detail-content">
					<h3><b>PROBABLE</b></h3>
					<h2>2830</h2>
					<h5>6% increased</h5>
					<span class="glyphicon glyphicon-question-sign"></span>
				</div>
			</div>
			<br><br>
			<div class="history-detail-block">
				<div class="history-detail-content">
					<h3><b>REJECT</b></h3>
					<h2>3910</h2>
					<h5>6% increased</h5>
					<span class="glyphicon glyphicon-remove"></span>
				</div>
			</div>
			<br><br>
		</div>
	</div>
	
	<script>
    var myChart = echarts.init(document.getElementById("main"));

    var option = {
    	    title: {
    	    	text: "History Data",
    	    	left: "center"
    	    },
    		tooltip: {
    	        trigger: "item",
    	        formatter: "{a} <br/>{b}: {c} ({d}%)"
    	    },
    	    legend: {
    	        orient: "vertical",
    	        x: "left",
    	        data:["Scanned","Learned", "Clean","Probable","Greylist","Reject","Soft Reject"]
    	    },
    	    series: [
    	        {
    	            name:"Total Data",
    	            type:"pie",
    	            selectedMode: "single",
    	            radius: [0, "30%"],

    	            label: {
    	                normal: {
    	                    position: "inner"
    	                }
    	            },
    	            labelLine: {
    	                normal: {
    	                    show: false
    	                }
    	            },
    	            data:[
    	                {value:12411, name:"Scanned"},
    	                {value:21312, name:"Learned"},
    	            ]
    	        },
    	        {
    	            name:"History Data",
    	            type:"pie",
    	            radius: ["40%", "55%"],

	            	data:[
	                	{value:2530, name:"Clean"},
	                	{value:2830, name:"Probable"},
	                	{value:1111, name:"Greylist"},
	                	{value:3910, name:"Reject"},
	                	{value:2030, name:"Soft Reject"}
	            	]
    	        }
    	    ]
    	};

    myChart.setOption(option);
    
    $(function(){
    	var blockBGColor =["#f57a82", "#8bbbc1", "#afd4c3", "#efbc71", 
    						"#758d9e", "#e6b4a2", "#96bba3"];
    	var changeBGColor =["#dd6e75", "#61a0a8", "#91c7ae", "#ca8622", 
    						"#2f4554", "#d48265", "#749f83"];
    	$(".history-detail-block").each(function(index, element){
    		$(element).css("background-color", blockBGColor[index]);
    	});
    	$(".history-detail-content h5").each(function(index, element){
    		$(element).css("background-color", changeBGColor[index]);
    	});
    })
	</script>
</body>
</html>