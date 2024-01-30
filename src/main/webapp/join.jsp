<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

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
<link href="css/number.css" rel="stylesheet">

</head>

<body>

	<!-- 중복 버튼을 눌렀을 때 중복검사하는 ajax -->
	<script type="text/javascript">
		var MIDResult;
		function checkMID() {
			// 사용자가 입력한 아이디 가져오기
			var MID = $("#MID").val();
			if (MID === "") {
				Swal.fire({
					icon : 'error',
					title : '아이디 검사',
					text : '아이디를 입력해주세요.',
				})
				return 0;
			}
			// AJAX 요청 보내기
			$.ajax({
				type : "POST", // 또는 "GET"
				url : "checkId", // 서버에서 아이디 중복 확인을 처리할 PHP 파일 경로
				data : {
					'MID' : MID
				},
				success : function(data) {
					MIDResult = data
					if (data === "suc") {
						Swal.fire({
							icon : 'success',
							title : '아이디 검사',
							text : '사용 가능한 아이디 입니다.',
						})
					} else {
						Swal.fire({
							icon : 'error',
							title : '아이디 검사',
							text : '사용 불가능한 아이디 입니다.',
						})
					}
				}
			});
		}
	</script>
	<!-- 중복 버튼을 눌렀을 때 중복검사하는 ajax -->


	<!-- 비밀 중복검사 -->
	<script type="text/javascript">
		var pwResult = false;
		function pwSameCheck() {
			if ($('#password').val() == $('#confirmPassword').val()) {
				pwResult = true
				Swal.fire({
					icon : 'success',
					title : '비밀번호 검사',
					text : '비밀번호가 일치합니다.',
				})
			} else {
				Swal.fire({
					icon : 'error',
					title : '비밀번호 검사',
					text : '비밀번호가 일치하지 않습니다.',
				})
			}
		}
	</script>
	<!-- 비밀 중복검사 -->


	<!-- 비밀번호 포맷 검사 -->
	<script>
		function pwFormatCheck() {

			var reg = new RegExp("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

			if (!reg.test($('#password').val())) {
				Swal.fire({
					icon : 'error',
					title : '비밀번호 검사',
					text : '영어 대소문자와 숫자를 포함해야합니다.',
				})
			}
		}
	</script>
	<!-- 비밀번호 포맷 검사 -->


	<!-- 회원가입 조건이 충족됬는지 확인 -->
	<script>
		function checkForm() {
			if (MIDResult == "suc" && pwResult == "suc") {
				return true;
			} else {
				return false;
			}
		}
	</script>
	<!-- 회원가입 조건이 충족됬는지 확인 -->


	<!-- 휴대폰 인증 요청 -->
	<script type="text/javascript">
		var telResult;
		function checkTel() {
			var phoneNum1 = $("#phoneNum1").val();
			var phoneNum2 = $("#phoneNum2").val();
			var phoneNum3 = $("#phoneNum3").val();
			$.ajax({
				type : "POST",
				url : "checkTel",
				data : {
					'phoneNum1' : phoneNum1,
					'phoneNum2' : phoneNum2,
					'phoneNum3' : phoneNum3
				},
				success : function(data) {
					telResult = data;
					var authNum = document.getElementById("authNum");
					authNum.readOnly = false
				}
			});
		}
	</script>
	<!-- 휴대폰 인증 요청 -->


	<!-- 휴대폰 인증번호 확인 -->
	<script type="text/javascript">
		var authNumResult = false;
		function checkAuthNum() {
			var authNum = $("#authNum").val();
			if (telResult === authNum) {
				Swal.fire({
					icon : 'success',
					title : '휴대폰 인증',
					text : '인증이 완료되었습니다.',
				})
				authNumResult = true;
				var authNum = document.getElementById("authNum");
				authNum.readOnly = true
			} else {
				Swal.fire({
					icon : 'error',
					title : '휴대폰 인증',
					text : '인증이 실패하였습니다.',
				})
			}
		}
	</script>
	<!-- 휴대폰 인증번호 확인 -->


	<!-- 필수 항목 누락 검사 -->
	<script>
		function checkRequirement() {
			if ($("#MID").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '아이디를 입력해주세요.',
				})
			} else if (MIDResult == null) {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '아이디 중복 검사를 진행해주세요',
				})
			} else if ($("#password").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '비밀번호를 입력해주세요.',
				})
			} else if ($("#confirmPassword").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '재입력을 입력해주세요.',
				})
			} else if (pwResult == false) {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '비밀번호 검사를 진행해주세요.',
				})
			} else if ($("#mName").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '이름을 입력해주세요.',
				})
			} else if ($("#year").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '생년월일을 입력해주세요.',
				})
			} else if ($("#month").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '생년월일 달을 입력해주세요.',
				})
			} else if ($("#day").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '생년월일 일을 입력해주세요.',
				})
			} else if ($("#phoneNum2").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '휴대폰 가운데 자리를 입력해주세요.',
				})
			} else if ($("#phoneNum3").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '휴대폰 끝 자리를 입력해주세요.',
				})
			} else if (authNumResult == false) {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '인증번호 확인을 진행해주세요.',
				})
			} else if ($("#email1").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '이메일 아이디를 입력해주세요.',
				})
			} else if ($("#email2").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '이메일 주소를 입력해주세요.',
				})
			} else if ($("#zipNo").val() == "") {
				Swal.fire({
					icon : 'error',
					title : '필수 항목 검사',
					text : '주소를 입력해주세요.',
				})
			} else {
				console.log("로그1");
				var joinForm = document.getElementById("joinForm");
				joinForm.submit();
			}
		}
	</script>
	<!-- 필수 항목 누락 검사 -->


	<!-- 숫자 길이 제한 -->
	<script>
		function limitNumLength(element, maxLength) {
			if (element.value.length > maxLength) {
				element.value = element.value.slice(0, maxLength);
			}
		}

		function checkMinLength(element, minLength) {
			if (element.value.length < minLength) {
				element.focus();
			}
		}
	</script>
	<!-- 숫자 길이 제한 -->


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
						<div class="nav-item dropdown">
							<div class="dropdown-menu m-0 bg-secondary rounded-0">
								<a href="cart.html" class="dropdown-item">Cart</a> <a href="chackout.html" class="dropdown-item">Chackout</a> <a href="testimonial.html" class="dropdown-item">Testimonial</a> <a href="404.html" class="dropdown-item active">404 Page</a>
							</div>
						</div>
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
		<h1 class="text-center text-white display-6">회원가입</h1>
	</div>
	<!-- Single Page Header End -->


	<!-- 회원가입 시작 -->
	<div class="container-fluid py-5">
		<div class="container py-5 text-center">
			<div class="row justify-content-center">
				<div class="col-lg-6">
					<form action="join.do" name="joinForm" id="joinForm" method="POST">
						<div class="row g-4">
							<div class="col-lg-8">
								<input class="form-control p-3  border-secondary" type="text" name="MID" id="MID" placeholder="아이디" maxlength="15" onblur="checkMinLength(this, 2)">
							</div>
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" id="checkIdDupl" type='button' onclick="checkMID()">중복 검사</button>
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary " type="password" name="mPassword1" id="password" placeholder="비밀번호" maxlength="15" onblur="pwFormatCheck()">
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary" type="password" name="mPassword2" id="confirmPassword" placeholder="재입력" maxlength="15" onblur="pwSameCheck()">
							</div>
							<div class="col-lg-12">
								<input class="form-control p-3  border-secondary" type="text" name="mName" id="mName" placeholder="이름" maxlength="20">
							</div>
							<div class="col-lg-4">
								<input class="form-control p-3 border-secondary" type="number" name="year" id="year" placeholder="yyyy" oninput="limitNumLength(this, 4)" onblur="checkMinLength(this, 4)">
							</div>
							<div class="col-lg-4">
								<input class="form-control p-3 border-secondary" type="number" name="month" id="month" placeholder="mm" oninput="limitNumLength(this, 2)" onblur="checkMinLength(this, 2)">
							</div>
							<div class="col-lg-4">
								<input class="form-control p-3 border-secondary" type="number" name="day" id="day" placeholder="dd" oninput="limitNumLength(this, 2)" onblur="checkMinLength(this, 2)">
							</div>
							<div class="col-lg-6">
								<input class="form-check-input p-3 border-secondary" type="radio" name="gender" value="남" checked="checked">
								<p>남자</p>
							</div>
							<div class="col-lg-6">
								<input class="form-check-input p-3 border-secondary" type="radio" name="gender" value="여">
								<p>여자</p>
							</div>
							<div class="col-lg-2">
								<input class="form-control p-3 border-secondary" type="number" name="phoneNum1" id="phoneNum1" value="010" readonly style="background-color:white;">
							</div>
							<div class="col-lg-3">
								<input class="form-control p-3 border-secondary" type="number" name="phoneNum2" id="phoneNum2" placeholder="0000" oninput="limitNumLength(this, 4)">
							</div>
							<div class="col-lg-3">
								<input class="form-control p-3 border-secondary" type="number" name="phoneNum3" id="phoneNum3" placeholder="0000" oninput="limitNumLength(this, 4)">
							</div>
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onclick="checkTel()">인증번호 발송</button>
							</div>
							<div class="col-lg-8">
								<input class="form-control p-3 border-secondary" type="text" name="authNum" id="authNum" placeholder="인증번호" readonly style="background-color:white;">
							</div>
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onclick="checkAuthNum()">인증번호 확인</button>
							</div>
							<div class="col-lg-5">
								<input class="form-control p-3 border-secondary" type="text" name="email1" placeholder="이메일 아이디" maxlength="60">
							</div>
							<div class="col-lg-1">
								<P class="mt-3">@</P>
							</div>
							<div class="col-lg-5">
								<input class="form-control p-3 border-secondary" type="text" name="email2" id="email2" placeholder="이메일 주소" maxlength="60">
							</div>
							<div class="col-lg-8">
								<input class="form-control p-3 border-secondary" type="number" id="zipNo" name="zipNo" id="zipNo" placeholder="우편번호" readonly style="background-color:white;">
							</div>
							<div class="col-lg-4">
								<input class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onClick="goPopup()" value="우편번호 찾기">
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary " type="text" id="roadAddrPart1" name="roadAddrPart1" id="roadAddrPart1" placeholder="도로명 주소" readonly style="background-color:white;">
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary" type="text" id="addrDetail" name="addrDetail" id="addrDetail" placeholder="상세 주소" readonly style="background-color:white;">
							</div>
							<div>
								<input type="checkbox" name="skel" value="뼈/치아">뼈/치아 
								<input type="checkbox" name="liver" value="간">간 
								<input type="checkbox" name="eye" value="눈">눈 
								<input type="checkbox" name="energy" value="활력">활력 
								<input type="checkbox" name="immune" value="면역">면역 
								<input type="checkbox" name="brain" value="두뇌">두뇌 
								<input type="checkbox" name="skin" value="피부">피부 
								<input type="checkbox" name="digest" value="소화">소화
							</div>
							<div class="col-lg-6">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onclick="checkRequirement()">회원가입</button>
							</div>
							<div class="col-lg-6">
								<button class="btn border border-secondary text-primary rounded-pill px-5 py-3" type="button" onclick="location.href='loginPage.do'">취소</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 회원가입 끝 -->


	<!-- Footer Start -->
	<div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
		<div class="container py-5">
			<div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5);">
				<div class="row g-4">
					<div class="col-lg-3">
						<a href="#">
							<h1 class="text-primary mb-0">NaeDdoCo Pills</h1>
						</a>
					</div>
					<div class="col-lg-6">
						<div class="position-relative mx-auto"></div>
					</div>
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
	<!-- Back to Top -->


	<!-- JavaScript Libraries -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/waypoints/waypoints.min.js"></script>
	<script src="lib/lightbox/js/lightbox.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>
	<!-- JavaScript Libraries -->


	<!-- Template Javascript -->
	<script src="js/main.js"></script>
	<!-- Template Javascript -->


	<!-- 주소 api 자바스크립트 -->
	<script language="javascript">
		// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. 
		// (＂팝업 API 호출 소스"도 동일하게 적용시켜야 합니다.)
		//document.domain = "abc.go.kr";
		function goPopup() {

			//경로는 시스템에 맞게 수정하여 사용
			//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를
			//호출하게 됩니다.
			var pop = window.open("jusoPopup.jsp", "pop",
					"width=570,height=420, scrollbars=yes, resizable=yes");

			//** 2017년 5월 모바일용 팝업 API 기능 추가제공 **/
			// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서
			// 실제 주소검색 URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
			// var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
		}

		function jusoCallBack(roadAddrPart1, addrDetail, zipNo) {

			// 2017년 2월 제공항목이 추가되었습니다. 원하시는 항목을 추가하여 사용하시면 됩니다.
			document.joinForm.roadAddrPart1.value = roadAddrPart1;
			document.joinForm.addrDetail.value = addrDetail;
			document.joinForm.zipNo.value = zipNo;

		}
	</script>
	<!-- 주소 api 자바스크립트 -->


</body>
</html>