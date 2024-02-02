<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>개인정보수정</title>
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
<link href="css/style.css" rel="stylesheet">
</head>

<body>

	<%-- 세션 확인 후 없으면 메인으로 --%>
	<custom:emthySessionAndGoToMain/>


	<!-- Spinner Start -->
	<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->


	<!-- Navbar start -->
	<div class="container-fluid fixed-top">
		<div class="container topbar bg-primary d-none d-lg-block">
			<div class="d-flex justify-content-between">
				<div class="top-info ps-2">
					<small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">123 Street, New York</a></small> <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">Email@Example.com</a></small>
				</div>
				<div class="top-link pe-2">
					<a href="#" class="text-white"> <small class="text-white mx-2">PrivacyPolicy</small> /
					</a> <a href="#" class="text-white"> <small class="text-white mx-2">Terms of Use</small> /
					</a> <a href="#" class="text-white"> <small class="text-white ms-2">Sales and Refunds</small>
					</a>
				</div>
			</div>
		</div>
		<div class="container px-0">
			<nav class="navbar navbar-light bg-white navbar-expand-xl">
				<!-- 로고 버튼 -->
				<a href="mainPage.do" class="navbar-brand">
					<h1 class="text-primary display-6">NaeDdoCo Pills</h1>
				</a>
				<!-- 로고 버튼 -->
				<button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
					<span class="fa fa-bars text-primary"></span>
				</button>
				<div class="collapse navbar-collapse bg-white" id="navbarCollapse">
					<div class="navbar-nav mx-auto">
						<a class="btn text-primary mb-0 mt-1" href="modifyUserInfoPage.do" class="nav-item nav-link">개인정보수정</a> 
						<a href="modifyUserPasswordPage.do" class="nav-item nav-link">비밀번호변경</a> 
						<a href="buyInfoPage.do" class="nav-item nav-link">구매내역</a> 
						<a href="reviewInfoPage.do" class="nav-item nav-link">리뷰내역</a> 
						<a href="couponInfoPage.do" class="nav-item nav-link">쿠폰관리</a>
					</div>
					<div class="d-flex m-3 me-0">
						<a class="btn border border-secondary text-primary rounded-pill position-relative my-auto me-4" href="logout.do">로그아웃</a>
						<button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal">
							<i class="fas fa-search text-primary"></i>
						</button>
						<a href="cartPage.do" class="position-relative me-4 my-auto"> <i class="fa fa-shopping-bag fa-2x"></i>
						</a> <a href="mypage.do" class="my-auto"> <i class="fas fa-user fa-2x"></i>
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
		<h1 class="text-center text-white display-6">개인정보수정</h1>
	</div>
	<!-- Single Page Header End -->


	<!-- 404 Start -->
	<div class="container-fluid py-5">
		<div class="container py-5 text-center">
			<div class="row justify-content-center">
				<div class="col-lg-6">
					<form action="login.jsp" method="POST" name="joinForm" onsubmit="return checkField()">
						<div class="row g-4">
							<div class="col-lg-12">
								<input class="form-control p-3  border-secondary" type="text" value="${memberInfo.mName}" name="mName" placeholder="이름" required>
							</div>
							<div class="col-lg-2">
								<input class="form-control p-3 border-secondary" type="text" name="phoneNum1" value="010" readonly required>
							</div>
							<div class="col-lg-3">
								<input class="form-control p-3 border-secondary" type="text" name="phoneNum2" placeholder="0000" required>
							</div>
							<div class="col-lg-3">
								<input class="form-control p-3 border-secondary" type="text" name="phoneNum3" placeholder="0000" required>
							</div>
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onclick="">인증번호 발송</button>
							</div>
							<div class="col-lg-8">
								<input class="form-control p-3 border-secondary" type="text" name="authNum" placeholder="인증번호" required>
							</div>
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onclick="">인증번호 확인</button>
							</div>
							<div class="col-lg-4">
								<input class="form-control p-3 border-secondary" type="text" name="email1" placeholder="이메일 아이디" required>
							</div>
							<div class="col-lg-1">
								<P class="mt-3">@</P>
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary" type="text" name="email2" placeholder="이메일 주소" required>
							</div>
							<div class="col-lg-8">
								<input class="form-control p-3 border-secondary" type="text" value="${memberInfo.mPostCode}" name="zipcode" placeholder="우편번호" required>
							</div>
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onclick="">우편번호 찾기</button>
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary " type="text" value="${memberInfo.mAddress}" name="address1" placeholder="도로명 주소" required>
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary" type="text" value="${memberInfo.mDetailedAddress}" name="address2" placeholder="지번 주소" required>
							</div>
							<div class="col-lg-6">
								<input class="btn border-secondary text-primary rounded-pill py-3 px-5" type="submit" value="수정">
							</div>
							<div class="col-lg-6">
								<a class="btn border border-secondary text-primary rounded-pill px-5 py-3" href="mypage.do">취소</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 404 End -->


	<!-- Footer Start -->
	<div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
		<div class="container py-5">
			<div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5);">
				<div class="row g-4">
					<div class="col-lg-3">
						<!-- 로고 버튼 -->
						<a href="mainPage.do" class="navbar-brand">
						<h1 class="text-primary display-6">NaeDdoCo Pills</h1>
					</a>
					<!-- 로고 버튼 -->
					</div>
					<div class="col-lg-6">
					</div>
					<div class="col-lg-3">
						<div class="d-flex justify-content-end pt-3">
							<a class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle" href="">
								<i class="fab fa-twitter"></i>
							</a> 
							<a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href="">
								<i class="fab fa-facebook-f"></i>
							</a> 
							<a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href="">
								<i class="fab fa-youtube"></i>
							</a> 
							<a class="btn btn-outline-secondary btn-md-square rounded-circle" href="">
								<i class="fab fa-linkedin-in"></i>
							</a>
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
	<!-- Footer End -->


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

	<script>
		function checkField() {

			if (!document.joinForm.MID.value) {

				alert("비밀번호를 입력하지 않았습니다.");

				document.joinForm.focus();

				return false;

			}

		}
	</script>

</body>
</html>