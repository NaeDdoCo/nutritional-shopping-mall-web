<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.dto.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>메인</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">


<!-- sweetalert2 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>


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
<link href="css/style.css" rel="stylesheet">
<link href="css/div.css" rel="stylesheet">

</head>
<body>

	<!-- 로그인 유저 아이디 세션에 저장 -->
	<c:set var="member" value="${member}" />
	<!-- 로그인 유저 아이디 세션에 저장 -->


	<!-- 로그아웃 성공 모달 -->
	<c:if test="${loginResult != null}">
		<c:if test="${loginResult == true}">
			<script type="text/javascript">
				window.onload = function() {
					logoutSuccess();
				};
				function logoutSuccess() {
					Swal.fire({
						icon : 'success',
						title : '로그아웃 성공',
						text : '로그아웃이 처리되었습니다.',
					})
				}
			</script>
		</c:if>
	</c:if>
	<!-- 로그아웃 성공 모달 -->


	<!-- Spinner Start -->
	<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->


	<!-- 로고가 포홤된 헤더 시작 -->
	<div class="container-fluid fixed-top">
		<div class="container topbar bg-primary d-none d-lg-block">
			<div class="d-flex justify-content-between">
				<div class="top-info ps-2">
					<small class="me-3"> <i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">123 Street, New York</a>
					</small> <small class="me-3"> <i class="fas fa-envelope me-2 text-secondary"></i> <a href="#" class="text-white">Email@Example.com</a>
					</small>
				</div>
				<div class="top-link pe-2">
					<a href="#" class="text-white"> <small class="text-white mx-2">PrivacyPolicy</small>/
					</a> <a href="#" class="text-white"> <small class="text-white mx-2">Terms of Use</small> /
					</a> <a href="#" class="text-white"> <small class="text-white ms-2">Sales and Refunds</small>
					</a>
				</div>
			</div>
		</div>
		<div class="container px-0">
			<nav class="navbar navbar-light bg-white navbar-expand-xl">
				<a href="main.do" class="navbar-brand">
					<h1 class="text-primary display-6">NaeDdoCo Pills</h1>
				</a>
				<button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
					<span class="fa fa-bars text-primary"></span>
				</button>
				<div class="collapse navbar-collapse bg-white" id="navbarCollapse">
					<div class="navbar-nav mx-auto"></div>
					<div class="d-flex m-3 me-0">
						<c:if test="${member != null}">
							<a class="btn border border-secondary text-primary rounded-pill position-relative my-auto me-4" href="logout.do">로그아웃</a>
						</c:if>
						<button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal">
							<i class="fas fa-search text-primary"></i>
						</button>
						<c:if test="${member != null}">
							<a href="#" class="position-relative me-4 my-auto"> <i class="fa fa-shopping-bag fa-2x"></i> <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
							</a>
							<a href="sss" class="my-auto"> <i class="fas fa-user fa-2x"></i>
							</a>
						</c:if>
						<c:if test="${member == null}">
							<a class="btn border border-secondary text-primary rounded-pill position-relative my-auto me-4" href="loginPage.do">로그인</a>
						</c:if>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- 로고가 포홤된 헤더 끝 -->


	<!-- 검색 버튼 시작 -->
	<div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-fullscreen">
			<div class="modal-content rounded-0">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body d-flex align-items-center">
					<div class="input-group w-75 mx-auto d-flex">
						<input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1"> <span id="search-icon-1" class="input-group-text p-3"> <i class="fa fa-search"></i>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 검색 버튼 끝 -->


	<!-- 제품 추천 시작 -->
	<div class="container-fluid py-5 mb-5">
		<div class="container-fluid vesitable py-5">
			<div class="container py-5">
				<h1 class="mb-0">Today's Recommendation</h1>
				<div class="owl-carousel vegetable-carousel owl-theme">
					<c:if test="${fn:length(rcmDTOs) > 0}">
						<c:forEach var="data" items="${rcmDTOs}">
							<div class="border border-primary rounded position-relative vesitable-item">
								<!-- 제품 추천 1 시작 -->
								<div class="vesitable-img">
									<img src=${data.imagePath } class="img-fluid w-100 rounded-top" alt="">
								</div>
								<div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">${data.category}</div>
								<div class="p-4 rounded-bottom">
									<h4>${data.pName}</h4>
									<div class="line-clamp my-2">
										<p>${data.pDetail}</p>
									</div>
									<div class="d-flex justify-content-between flex-lg-wrap">
										<div class="row">
											<div class="col">
												<p class="text-dark fs-5 fw-bold mb-0 my-2">${data.sellingPrice}원</p>
											</div>
										</div>
										<c:if test="${member != null}">
											<div class="row">
												<a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"> <i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart
												</a>
											</div>
										</c:if>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<!-- 제품 추천 끝 -->

			<!-- 일반 리스트 시작 -->
			<div class="container-fluid fruite py-5">
				<div class="container py-5">
					<div class="tab-class text-center">
						<div class="row g-4">
							<div class="col-lg-4 text-start">
								<h1>Our Products</h1>
							</div>
							<div class="col-lg-8 text-end">
								<ul class="nav nav-pills d-inline-flex text-center mb-5">
									<li class="nav-item"><a class="d-flex m-2 py-2 bg-light rounded-pill active" data-bs-toggle="pill" href="productAllPage.do"> <span class="text-dark" style="width: 130px;">All Products</span>
									</a></li>
								</ul>
							</div>
						</div>
						<div class="tab-content">

							<div id="tab-1" class="tab-pane fade show p-0 active">
								<div class="row g-4">
									<div class="col-lg-12">
										<div class="row g-4">
											<c:if test="${fn:length(pDTOs) > 0}">
												<c:forEach var="data" items="${pDTOs}">
													<div class="col-md-6 col-lg-4 col-xl-3">
														<div class="p-4 border border-secondary rounded position-relative fruite-item">
															<div class="fruite-img">
																<img src=${data.imagePath } class="img-fluid w-100 rounded-top" alt="">
															</div>
															<div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${data.category}</div>
															<div>
																<h4>${data.pName}</h4>
																<div class="line-clamp my-2">
																	<p>${data.pDetail}</p>
																</div>
																<div class="d-flex justify-content-between flex-lg-wrap">
																	<p class="text-dark fs-5 fw-bold mb-0">${data.sellingPrice}원</p>
																	<c:if test="${member != null}">
																		<a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
																	</c:if>
																</div>
															</div>
														</div>
													</div>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 일반 리스트 끝 -->
		</div>
		<!-- 제품 추천 끝 -->


		<!-- 풋터 시작 -->
		<div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
			<div class="container py-5">
				<div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5);">
					<div class="row g-4">
						<div class="col-lg-3">
							<a href="#">
								<h1 class="text-primary mb-0">NaeDdoCo Pills</h1>
							</a>
						</div>
						<div class="col-lg-6"></div>
						<div class="col-lg-3">
							<div class="d-flex justify-content-end pt-3">
								<a class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-twitter"></i></a> <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-facebook-f"></i></a> <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-youtube"></i></a> <a class="btn btn-outline-secondary btn-md-square rounded-circle" href=""><i class="fab fa-linkedin-in"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row g-5">
					<div class="col-lg-3 col-md-6">
						<div class="footer-item">
							<h4 class="text-light mb-3">Why People Like us!</h4>
							<p class="mb-4">typesetting, remaining essentially unchanged. It was popularised in the 1960s with the like Aldus PageMaker including of Lorem Ipsum.</p>
							<a href="" class="btn border-secondary py-2 px-4 rounded-pill text-primary">Read More</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="d-flex flex-column text-start footer-item">
							<h4 class="text-light mb-3">Shop Info</h4>
							<a class="btn-link" href="">About Us</a> <a class="btn-link" href="">Contact Us</a> <a class="btn-link" href="">Privacy Policy</a> <a class="btn-link" href="">Terms & Condition</a> <a class="btn-link" href="">Return Policy</a> <a class="btn-link" href="">FAQs & Help</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="d-flex flex-column text-start footer-item">
							<h4 class="text-light mb-3">Account</h4>
							<a class="btn-link" href="">My Account</a> <a class="btn-link" href="">Shop details</a> <a class="btn-link" href="">Shopping Cart</a> <a class="btn-link" href="">Wishlist</a> <a class="btn-link" href="">Order History</a> <a class="btn-link" href="">International Orders</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="footer-item">
							<h4 class="text-light mb-3">Contact</h4>
							<p>Address: 1429 Netus Rd, NY 48247</p>
							<p>Email: Example@gmail.com</p>
							<p>Phone: +0123 4567 8910</p>
							<p>Payment Accepted</p>
							<img src="img/payment.png" class="img-fluid" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
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
		<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"> <i class="fa fa-arrow-up"></i>
		</a>


		<!-- JavaScript Libraries -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
		<script src="lib/easing/easing.min.js"></script>
		<script src="lib/waypoints/waypoints.min.js"></script>
		<script src="lib/lightbox/js/lightbox.min.js"></script>
		<script src="lib/owlcarousel/owl.carousel.min.js"></script>


		<!-- Template Javascript -->
		<script src="js/main.js"></script>


		<!-- sweetalert2 -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
</body>

</html>