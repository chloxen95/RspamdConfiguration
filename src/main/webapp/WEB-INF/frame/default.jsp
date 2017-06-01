<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rspamd Configuration</title>
<!-- Base Css Files -->
<link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
<link
	href="assets/libs/jqueryui/ui-lightness/jquery-ui-1.10.4.custom.min.css"
	rel="stylesheet" />
<link href="assets/libs/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/libs/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/libs/fontello/css/fontello.css" rel="stylesheet" />
<link href="assets/libs/animate-css/animate.min.css" rel="stylesheet" />
<link href="assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="assets/libs/highlight/css/tomorrow.css" rel="stylesheet" type="text/css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="assets/libs/jquery/jquery-1.11.3.min.js"></script>
<script src="assets/libs/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/libs/fastclick/fastclick.js"></script>
<script src="assets/libs/jquery-sparkline/jquery-sparkline.js"></script>
<!-- Demo Specific JS Libraries -->
<script src="assets/libs/prettify/prettify.js"></script>
<script src="assets/libs/bootstrap-select/bootstrap-select.min.js"></script>
<script src="assets/js/init.js"></script>
<script src="assets/libs/highlight/js/highlight.pack.js"></script>
<style type="text/css">
.default-sidebar-menu a, .default-sidebar-menu a:HOVER {
	background-color: #343838;
	border-color: #3f4848;
	height: 50px
}
.default-sidebar-text {
	color: white;
	font-size: 15px
}
.default-sidebar-menu a:FIRST-OF-TYPE {
	background-color: #68C39F !important;
	border-color: #68C39F !important;
}
</style>
</head>
<body class="fixed-left">
	<!-- Begin page -->
	<div id="wrapper">

		<!-- Top Bar Start -->
		<div class="topbar">
			<div class="topbar-left">
				<div class="logo">
					<!--<h1>
						<a href="#"><img src="assets/img/logo.png" alt="Logo"></a>
					</h1>-->
					<h1>Logo</h1>
				</div>
			</div>
			<!-- Button mobile view to collapse sidebar menu -->
			<div class="navbar navbar-default" role="navigation">
				<div class="container">
					<div class="navbar-collapse2">
						<ul class="nav navbar-nav navbar-right top-navbar">
							<li class="dropdown iconify hide-phone"><a href="#"
								onclick="javascript:toggle_fullscreen()"><i
									class="icon-resize-full-2"></i></a></li>
							<li class="dropdown topbar-profile"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown"> <!-- <span
									class="rounded-image topbar-profile-image">
										<img
										src="images/users/user-35.jpg"> </span> --> <strong>chloxen95</strong>
									<i class="fa fa-caret-down"></i>
							</a></li>
							<li class="right-opener"><a href="javascript:;"
								class="open-right"><i class="fa fa-angle-double-left"></i><i
									class="fa fa-angle-double-right"></i></a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<!-- Top Bar End -->
		<!-- Left Sidebar Start -->
		<div class="left side-menu">
			<div class="sidebar-inner slimscrollleft">
				<!-- Search form -->
				<form role="search" class="navbar-form">
					<div class="form-group">
						<input type="text" placeholder="Search" class="form-control">
						<button type="submit" class="btn search-button">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</form>
				<div class="clearfix"></div>
				<!--- Profile -->
				<div class="profile-info">
					<div class="col-xs-4">
						<!--<a href="profile.html" class="rounded-image profile-image"><img
							src="images/users/user-100.jpg"></a>-->
					</div>
					<div class="col-xs-8">
						<div class="profile-text">
							Hi <b>chloxen95</b>
						</div>
						<div class="profile-buttons">
							<a href="/RspamdConfiguration/"><i class="fa fa-home pulse"></i></a>
							<a href="#connect" class="open-right"><i
								class="fa fa-comments"></i></a> <a href="javascript:;"
								title="Sign Out"><i class="fa fa-power-off text-red-1"></i></a>
						</div>
					</div>
				</div>
				<!--- Divider -->
				<div class="clearfix"></div>
				<hr class="divider" />
				<div class="clearfix"></div>
				<!--- Divider -->
				<div class="list-group default-sidebar-menu">
					<a href="#" class="list-group-item active">
						<h4 class="list-group-item-heading">Configuration Files</h4>
					</a> <a href="conf" class="list-group-item">
						<h4 class="default-sidebar-text">Conf File</h4>
					</a> <a href="inc" class="list-group-item">
						<h4 class="default-sidebar-text">Inc File</h4>
					</a>
				</div>
				<div class="list-group default-sidebar-menu">
					<a href="#" class="list-group-item active">
						<h4 class="list-group-item-heading">Rules</h4>
					</a> <a href="rule" class="list-group-item">
						<h4 class="default-sidebar-text">Rule</h4>
					</a> <a href="symbol" class="list-group-item">
						<h4 class="default-sidebar-text">Symbol</h4>
					</a> <a href="module" class="list-group-item">
						<h4 class="default-sidebar-text">Module</h4>
					</a>
				</div>
				<div class="clearfix"></div>
				<div class="clearfix"></div>
				<br> <br> <br>
			</div>
			<div class="left-footer" style="height: initial;">
				<div class="portlets">
					<div id="chat_groups" class="widget transparent nomargin">
						<h2>Chat Groups</h2>
						<div class="widget-content">
							<ul class="list-unstyled">
								<li><a href="javascript:;"><i
										class="fa fa-circle-o text-red-1"></i> Colleagues</a></li>
								<li><a href="javascript:;"><i
										class="fa fa-circle-o text-blue-1"></i> Family</a></li>
								<li><a href="javascript:;"><i
										class="fa fa-circle-o text-green-1"></i> Friends</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Left Sidebar End -->
		<!-- Right Sidebar Start -->
		<div class="right side-menu"></div>
		<!-- Right Sidebar End -->
		<!-- Start right content -->
		<div class="content-page" style="margin-top: 50px">
			<!-- ============================================================== -->
			<!-- Start Content here -->
			<!-- ============================================================== -->
			<sitemesh:write property='body' />
			<!-- ============================================================== -->
			<!-- End content here -->
			<!-- ============================================================== -->

		</div>
		<!-- End right content -->

	</div>
	<!-- End of page -->
	<script>
		var resizefunc = [];
		$(function(){
			$(".content").css("height", window.innerHeight - 100);
		})
	</script>

</body>
</html>