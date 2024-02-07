<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인</title>
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

<!-- 카카오 SDK 로드 -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.6.0/kakao.min.js" integrity="sha384-6MFdIr0zOira1CHQkedUqJVql0YtcZA1P0nbPrQYJXVJZUkTk/oX4U9GhUIs3/z8" crossorigin="anonymous"></script>

<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


</head>
<body>

	<!-- 로그인 실패 모달 -->
	<c:if test="${loginResult != null}">
		<c:if test="${loginResult == false}">
			<script type="text/javascript">
				window.onload = function() {
					loginFail();
				};
				function loginFail() {
					Swal.fire({
						icon : 'error',
						title : '로그인 실패',
						text : '로그인이 실패하였습니다.',
					})
				}
			</script>
		</c:if>
	</c:if>
	<!-- 로그인 실패 모달 -->

	<!-- Spinner Start -->
	<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->

	<!-- Navbar start -->
	<div class="container-fluid fixed-top">
		<custom:commonHeader/>
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
						<div class="nav-item dropdown"></div>
					</div>
					<div class="d-flex m-3 me-0">
						<button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal">
							<i class="fas fa-search text-primary"></i>
						</button>
						<!-- 로그인 버튼 -->
						<c:if test="${member == null}">
							<a class="btn border border-secondary text-primary rounded-pill position-relative my-auto me-4" href="loginPage.do">로그인</a>
						</c:if>
						<!-- 로그인 버튼 -->
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
		<h1 class="text-center text-white display-6">로그인</h1>
	</div>
	<!-- Single Page Header End -->


	<div class="container-fluid py-5">
		<div class="container py-5 text-center">
			<div class="row justify-content-center">
				<div class="col-lg-6">
					<!-- 로그인 폼 -->
					<form action="login.do" method="POST">
						<input class="form-control p-3 border-secondary my-3" type="text" name="MID" placeholder="아이디" required> 
						<input class="form-control p-3 border-secondary my-3" type="password" name="mPassword" placeholder="비밀번호" required>
						<input class="btn border-secondary text-primary rounded-pill py-3 px-5 my-3" type="submit" value="로그인"> 
						<a class="btn border-secondary text-primary rounded-pill py-3 px-5 my-3" href="termsPage.do">회원가입</a>
					</form>
					<!-- 로그인 폼 -->
				</div>
			</div>
			<hr>
			<!-- 카카오 로그인 -->
			<a id="kakao-login-btn"></a>
			<!-- 카카오 로그인 -->
		</div>
	</div>


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
	
	<!-- 카카오 로그인 -->
	<script>
	//카카오 초기화
	Kakao.init('8a69ee438b3b0270acfb88808676567f'); // 사용하려는 앱의 JavaScript 키 입력
	console.log(Kakao.isInitialized()); // 초기화 판단여부
	
	Kakao.Auth.createLoginButton({
		container: '#kakao-login-btn',
		success: function(authObj) {
			Kakao.API.request({
				url: '/v2/user/me',
				success: function(result) {
					location.replace("/nutritional-shopping-mall2/kakaoLogin.do?kakaoId=" + result.id);
				},
	            fail: function(error) {
					Swal.fire({
						icon : 'error',
						title : '로그인 실패',
						text : '로그인이 실패하였습니다.',
					})
				},
			})
		},
		fail: function(err) {
			Swal.fire({
				icon : 'error',
				title : '로그인 실패',
				text : '로그인이 실패하였습니다.',
			})
		},
	})
	</script>
	<!-- 카카오 로그인 -->
	

</body>
</html>