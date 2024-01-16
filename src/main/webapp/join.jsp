<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
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
<link href="css/number.css" rel="stylesheet">

</head>

<body>

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<!-- jquery -->


	<!-- 중복 버튼을 눌렀을 때 중복검사하는 ajax -->
	<script type="text/javascript">
		var MIDResult;
		function checkMID() {
	    	// 사용자가 입력한 아이디 가져오기
	    	var MID = $("#MID").val();
	    	if(MID === ""){
	    		Swal.fire({
        			icon: 'error',
        			title: '아이디 검사',
        			text: '아이디를 입력해주세요.',
   	 			})
   	 			return 0;
	    	}
	    	// AJAX 요청 보내기
	    	$.ajax({
	        	type: "POST", // 또는 "GET"
	        	url: "CheckId", // 서버에서 아이디 중복 확인을 처리할 PHP 파일 경로
	        	data: { 'MID': MID },
	        	success: function(data) {
	        		MIDResult = data
	        		if(data === "suc"){
	        			Swal.fire({
	            			icon: 'success',
	            			title: '아이디 검사',
	            			text: '사용 가능한 아이디 입니다.',	
	       	 			})
	        		} else {
						Swal.fire({
	            			icon: 'error',
	            			title: '아이디 검사',
	            			text: '사용 불가능한 아이디 입니다.',
	       	 			})
	        		}
	        	}
	    	});
		}	
	</script>
	<!-- 중복 버튼을 눌렀을 때 중복검사하는 ajax -->


	<!-- 비밀 중복검사하는 ajax -->
	<script type="text/javascript">
		var pwResult;
		function pwCheck(){
	    	if($('#password').val() == $('#confirmPassword').val()){
	    		pwResult = "suc"
	    		Swal.fire({
        			icon: 'success',
        			title: '비밀번호 검사',
        			text: '비밀번호가 일치합니다.',
   	 			})
	    	} else {
				Swal.fire({
        			icon: 'error',
        			title: '비밀번호 검사',
        			text: '비밀번호가 일치하지 않습니다.',
   	 			})
	    	}
		}
	</script>
	<!-- 비밀 중복검사하는 ajax -->
	
	
	<!-- 회원가입 조건이 충족됬는지 확인 -->
	<script>
		function checkForm(){
			if(MIDResult=="suc"&&pwResult=="suc"){
				return true;
			}else{
				return false;
			}			
		}
	</script>
	<!-- 회원가입 조건이 충족됬는지 확인 -->

	
	<!-- 휴대폰 인증 요청 -->
	<script type="text/javascript">
		function checkTel() {
			$.ajax({
	        	type: "POST",
	        	url: "CheckTel",
	        	success: function(data) {
	        		MIDResult = data
	        		if(data === "suc"){
	        			Swal.fire({
	            			icon: 'success',
	            			title: '아이디 검사',
	            			text: '사용 가능한 아이디 입니다.',	
	       	 			})
	        		} else {
						Swal.fire({
	            			icon: 'error',
	            			title: '아이디 검사',
	            			text: '사용 불가능한 아이디 입니다.',
	       	 			})
	        		}
	        	}
	    	});
		}
	</script>
	

	<!-- Spinner Start -->
	<div id="spinner"
		class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->


	<!-- Navbar start -->
	<div class="container-fluid fixed-top">
		<div class="container topbar bg-primary d-none d-lg-block">
			<div class="d-flex justify-content-between">
				<div class="top-info ps-2">
					<small class="me-3"><i
						class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#"
						class="text-white">123 Street, New York</a></small> <small class="me-3"><i
						class="fas fa-envelope me-2 text-secondary"></i><a href="#"
						class="text-white">Email@Example.com</a></small>
				</div>
				<div class="top-link pe-2">
					<a href="#" class="text-white">
						<small class="text-white mx-2">PrivacyPolicy</small>
					/</a> 
					<a href="#" class="text-white">
						<small class="text-white mx-2">Terms of Use</small>
					/</a> 
					<a href="#" class="text-white">
						<small class="text-white ms-2">Sales and Refunds</small>
					</a>
				</div>
			</div>
		</div>
		<div class="container px-0">
			<nav class="navbar navbar-light bg-white navbar-expand-xl">
				<a href="main.jsp" class="navbar-brand">
					<h1 class="text-primary display-6">NaeDdoCo Pills</h1>
				</a>
				<button class="navbar-toggler py-2 px-3" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
					<span class="fa fa-bars text-primary"></span>
				</button>
				<div class="collapse navbar-collapse bg-white" id="navbarCollapse">
					<div class="navbar-nav mx-auto">
						<div class="nav-item dropdown">
							<div class="dropdown-menu m-0 bg-secondary rounded-0">
								<a href="cart.html" class="dropdown-item">Cart</a> <a
									href="chackout.html" class="dropdown-item">Chackout</a> <a
									href="testimonial.html" class="dropdown-item">Testimonial</a> <a
									href="404.html" class="dropdown-item active">404 Page</a>
							</div>
						</div>
					</div>
					<div class="d-flex m-3 me-0">
						<button
							class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4"
							data-bs-toggle="modal" data-bs-target="#searchModal">
							<i class="fas fa-search text-primary"></i>
						</button>
						<a href="#" class="position-relative me-4 my-auto"> <i
							class="fa fa-shopping-bag fa-2x"></i> <span
							class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
							style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
						</a> <a href="#" class="my-auto"> <i class="fas fa-user fa-2x"></i>
						</a>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->


	<!-- Modal Search Start -->
	<div class="modal fade" id="searchModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-fullscreen">
			<div class="modal-content rounded-0">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Search by
						keyword</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body d-flex align-items-center">
					<div class="input-group w-75 mx-auto d-flex">
						<input type="search" class="form-control p-3"
							placeholder="keywords" aria-describedby="search-icon-1">
						<span id="search-icon-1" class="input-group-text p-3"><i
							class="fa fa-search"></i></span>
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
					<form action="join.do" name="joinForm" method="POST">
						<div class="row g-4">
							<div class="col-lg-8">
								<input class="form-control p-3  border-secondary" type="text" name="MID" id="MID" placeholder="아이디">	
							</div>	
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" id="checkIdDupl" type='button' onclick="checkMID()">중복 검사</button>	
							</div>		
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary " type="password" name="mPassword1" id="password" placeholder="비밀번호" required>
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary" type="password" name="mPassword2" id="confirmPassword" placeholder="재입력" onblur="pwCheck()" required>
							</div>
							<div class="col-lg-12">
								<input class="form-control p-3  border-secondary" type="text" name="mName" placeholder="이름" required>
							</div>
							<div class="col-lg-4">
								<input class="form-control p-3 border-secondary" type="number" name="year" placeholder="yyyy" required>
							</div>
							<div class="col-lg-4">
								<input class="form-control p-3 border-secondary" type="number" name="month" placeholder="mm" required>
							</div>
							<div class="col-lg-4">
								<input class="form-control p-3 border-secondary" type="number" name="day" placeholder="dd" required>
							</div>
							<div class="col-lg-6">
								<input class="form-check-input p-3 border-secondary" type="radio" name="gender" value="남" checked="checked">남자
							</div>
							<div class="col-lg-6">
								<input class="form-check-input p-3 border-secondary" type="radio" name="gender" value="여">여자
							</div>
							<div class="col-lg-2">
								<input class="form-control p-3 border-secondary" type="number" name="phoneNum1" value="010" readonly required>
							</div>
							<div class="col-lg-3">
								<input class="form-control p-3 border-secondary" type="number" name="phoneNum2" placeholder="0000" required>
							</div>
							<div class="col-lg-3">
								<input class="form-control p-3 border-secondary" type="number" name="phoneNum3" placeholder="0000" required>
							</div>
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onclick="checkTel()">인증번호 발송</button>	
							</div>
							<div class="col-lg-8">
								<input class="form-control p-3 border-secondary" type="text" name="authNum" placeholder="인증번호" required>
							</div>
							<div class="col-lg-4">
								<button class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="button" onclick="">인증번호 확인</button>	
							</div>
							<div class="col-lg-5">
								<input class="form-control p-3 border-secondary" type="text" name="email1" placeholder="이메일 아이디" required>
							</div>
							<div class="col-lg-1">
								<P class="mt-3">@</P>
							</div>
							<div class="col-lg-5">
								<input class="form-control p-3 border-secondary" type="text" name="email2" placeholder="이메일 주소" required>
							</div>
							<div class="col-lg-8">
								<input class="form-control p-3 border-secondary" type="number" id="zipNo" name="zipNo" placeholder="우편번호" readonly required/>
							</div>
							<div class="col-lg-4">
								<input class="btn border border-secondary text-primary rounded-pill px-4 py-3"  type="button" onClick="goPopup()" value="우편번호 찾기"/>
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary " type="text" id="roadAddrPart1" name="roadAddrPart1" placeholder="도로명 주소" readonly required/>
							</div>
							<div class="col-lg-6">
								<input class="form-control p-3 border-secondary" type="text" id="addrDetail" name="addrDetail" placeholder="상세 주소" readonly required/>
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
								<input class="btn border border-secondary text-primary rounded-pill px-4 py-3" type="submit" value="회원가입">
								
							</div>
							<div class="col-lg-6">
								<button class="btn border border-secondary text-primary rounded-pill px-5 py-3" type="button" onclick="">취소</button>	
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
			<div class="pb-4 mb-4"
				style="border-bottom: 1px solid rgba(226, 175, 24, 0.5);">
				<div class="row g-4">
					<div class="col-lg-3">
						<a href="#">
							<h1 class="text-primary mb-0">NaeDdoCo Pills</h1>
						</a>
					</div>
					<div class="col-lg-6">
						<div class="position-relative mx-auto">
						</div>
					</div>
					<div class="col-lg-3">
						<div class="d-flex justify-content-end pt-3">
							<a
								class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle"
								href=""><i class="fab fa-twitter"></i></a> <a
								class="btn btn-outline-secondary me-2 btn-md-square rounded-circle"
								href=""><i class="fab fa-facebook-f"></i></a> <a
								class="btn btn-outline-secondary me-2 btn-md-square rounded-circle"
								href=""><i class="fab fa-youtube"></i></a> <a
								class="btn btn-outline-secondary btn-md-square rounded-circle"
								href=""><i class="fab fa-linkedin-in"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row g-5">
				<div class="col-lg-3 col-md-6">
					<div class="footer-item">
						<h4 class="text-light mb-3">Why People Like us!</h4>
						<p class="mb-4">typesetting, remaining essentially unchanged.
							It was popularised in the 1960s with the like Aldus PageMaker
							including of Lorem Ipsum.</p>
						<a href=""
							class="btn border-secondary py-2 px-4 rounded-pill text-primary">Read
							More</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="d-flex flex-column text-start footer-item">
						<h4 class="text-light mb-3">Shop Info</h4>
						<a class="btn-link" href="">About Us</a> <a class="btn-link"
							href="">Contact Us</a> <a class="btn-link" href="">Privacy
							Policy</a> <a class="btn-link" href="">Terms & Condition</a> <a
							class="btn-link" href="">Return Policy</a> <a class="btn-link"
							href="">FAQs & Help</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="d-flex flex-column text-start footer-item">
						<h4 class="text-light mb-3">Account</h4>
						<a class="btn-link" href="">My Account</a> <a class="btn-link"
							href="">Shop details</a> <a class="btn-link" href="">Shopping
							Cart</a> <a class="btn-link" href="">Wishlist</a> <a class="btn-link"
							href="">Order History</a> <a class="btn-link" href="">International
							Orders</a>
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
					<span class="text-light"><a href="#"><i
							class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All
						right reserved.</span>
				</div>
				<div class="col-md-6 my-auto text-center text-md-end text-white">
					<!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
					<!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
					<!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
					Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML
						Codex</a> Distributed By <a class="border-bottom"
						href="https://themewagon.com">ThemeWagon</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Copyright End -->


	<!-- Back to Top -->
	<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top">
		<i class="fa fa-arrow-up"></i>
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
		function goPopup(){
			
			//경로는 시스템에 맞게 수정하여 사용
			//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를
			//호출하게 됩니다.
			var pop = window.open("jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
			
			//** 2017년 5월 모바일용 팝업 API 기능 추가제공 **/
			// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서
			// 실제 주소검색 URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
			// var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
		}
		
		function jusoCallBack(roadAddrPart1,addrDetail, zipNo){
			
 			// 2017년 2월 제공항목이 추가되었습니다. 원하시는 항목을 추가하여 사용하시면 됩니다.
 			document.joinForm.roadAddrPart1.value = roadAddrPart1;
 			document.joinForm.addrDetail.value = addrDetail;
 			document.joinForm.zipNo.value = zipNo;
 			
		}
		
	</script>
	<!-- 주소 api 자바스크립트 -->
	
	
</body>
</html>