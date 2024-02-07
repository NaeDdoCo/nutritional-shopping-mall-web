<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<title>회원가입 약관</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/terms.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>

	<!-- Spinner Start -->
	<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->


	<!-- Navbar start -->
	<div class="container-fluid fixed-top">
		<custom:commonHeader />
		<div class="container px-0">
			<nav class="navbar navbar-light bg-white navbar-expand-xl">
				<a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">NaeDdoCo Pills</h1></a>
				<button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
					<span class="fa fa-bars text-primary"></span>
				</button>
				<div class="collapse navbar-collapse bg-white" id="navbarCollapse">
					<div class="navbar-nav mx-auto"></div>
					<div class="d-flex m-3 me-0">
						<button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal">
							<i class="fas fa-search text-primary"></i>
						</button>
						<a href="#" class="position-relative me-4 my-auto"> <i class="fa fa-shopping-bag fa-2x"></i> <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
						</a> <a href="#" class="my-auto"> <i class="fas fa-user fa-2x"></i>
						</a>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->


	<!-- Modal Search Start -->
	<div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-fullscreen">
			<div class="modal-content rounded-0">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body d-flex align-items-center">
					<div class="input-group w-75 mx-auto d-flex">
						<input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1"> <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Search End -->


	<!-- Single Page Header start -->
	<div class="container-fluid page-header py-5">
		<h1 class="text-center text-white display-6">회원가입 약관</h1>
	</div>
	<!-- Single Page Header End -->


	<!-- Contact Start -->
	<div class="container-fluid contact py-5">
		<div class="container py-5">
			<div class="p-5 bg-light rounded">
				<div class="row g-4">
					<div class="col-12">
						<div class="text-center mx-auto" style="max-width: 700px;">
							<h1 class="text-primary">약관</h1>
						</div>
						<form action="joinPage.do" class="">
							<textarea class="w-100 form-control border-0 mb-4" rows="10" cols="10" placeholder="Your Message">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                </textarea>
							<input type="checkbox" name="a" value="뼈/치아">
							<button class="w-100 btn form-control border-secondary py-3 bg-white text-primary " type="submit">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Contact End -->


	<!-- 풋터 시작 -->
	<custom:commonFooter/>
	<!-- 풋터 끝 -->
	

	<!-- Copyright Start -->
	<div class="container-fluid copyright bg-dark py-4">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
					<span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
				</div>
				<div class="col-md-6 my-auto text-center text-md-end text-white">
					<!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
					<!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
					<!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
					Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a> Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Copyright End -->



	<!-- Back to Top -->
	<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>


	<!-- JavaScript Libraries -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/waypoints/waypoints.min.js"></script>
	<script src="lib/lightbox/js/lightbox.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Template Javascript -->
	<script src="js/main.js"></script>
</body>

</html>