<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rspamd Configuration</title>
<meta name="author" content="chloxen95">

<!-- Base Css Files -->
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
								class="dropdown-toggle" data-toggle="dropdown">
									<!-- <span
									class="rounded-image topbar-profile-image">
										<img
										src="images/users/user-35.jpg"> </span> -->
										<strong>chloxen95</strong>
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
							<a href="javascript:;"><i class="fa fa-envelope-o pulse"></i></a>
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
				<div id="sidebar-menu">
					<ul>
						<li class='has_sub'><a href='javascript:void(0);'><i
								class='icon-home-3'></i><span>Dashboard</span> <span
								class="pull-right"><i class="fa fa-angle-down"></i></span></a>
							<ul>
								<li><a href='index.html' class='active'><span>Dashboard
											v1</span></a></li>
								<li><a href='index2.html'><span>Dashboard v2</span></a></li>
							</ul></li>
						<li class='has_sub'><a href='javascript:void(0);'><i
								class='icon-feather'></i><span>UI Elements</span> <span
								class="pull-right"><i class="fa fa-angle-down"></i></span></a>
							<ul>
								<li><a href='alerts.html'><span>Alerts</span></a></li>
								<li><a href='buttons.html'><span>Buttons</span></a></li>
							</ul></li>
						<li class='has_sub'><a href='javascript:void(0);'><i
								class='icon-pencil-3'></i><span>Forms</span> <span
								class="pull-right"><i class="fa fa-angle-down"></i></span></a>
							<ul>
								<li><a href='forms.html'><span>Form Elements</span></a></li>
								<li><a href='advanced-forms.html'><span>Advanced
											Forms</span></a></li>
							</ul></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
				<<div class="clearfix"></div>
				<br>
				<br>
				<br>
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
				</div></div>
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
	</script>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/libs/jquery/jquery-1.11.1.min.js"></script>
	<script src="assets/libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/libs/fastclick/fastclick.js"></script>
	<script src="assets/libs/jquery-sparkline/jquery-sparkline.js"></script>
	<!-- Demo Specific JS Libraries -->
	<script src="assets/libs/prettify/prettify.js"></script>
	<script src="assets/libs/bootstrap-select/bootstrap-select.min.js"></script>

	<script src="assets/js/init.js"></script>
</body>
</html>