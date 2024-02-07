<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>상품 상세</title>
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

<!-- 파비콘 -->
<custom:favicon/>
</head>

<body>

	<!-- 장바구니 추가 비동기처리 -->
	<script>
		function addItemToCart(PID) {
			var cQty = document.getElementById('cQty').value;
			//console.log("[로그] 수량:"+cQty);
			
			$.ajax({
				type : "POST", // 또는 "GET"
				url : "insertCart", // 서버에서 아이디 중복 확인을 처리할 PHP 파일 경로
				data : {'PID' : PID,
						'cQty' : cQty},
				success : function(data) {
					//console.log(data);
					if (data === "true") {
						Swal.fire({
							icon : 'success',
							title : '장바구니 추가',
							text : '추가되었습니다.',
						})
					} else {
						Swal.fire({
							icon : 'error',
							title : '장바구니 추가',
							text : '실패하였습니다.',
						})
					}
				}
			});
		}
	</script>
	<!-- 장바구니 추가 비동기처리 -->


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
					<div class="navbar-nav mx-auto"></div>
					<div class="d-flex m-3 me-0">
						<!-- 로그아웃 버튼 -->
						<c:if test="${member != null}">
							<a class="btn border border-secondary text-primary rounded-pill position-relative my-auto me-4" href="logout.do">로그아웃</a>
						</c:if>
						<!-- 로그아웃 버튼 -->
						<button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal">
							<i class="fas fa-search text-primary"></i>
						</button>
						<c:if test="${member != null}">
						<!-- 장바구니 버튼 -->
						<c:if test="${member != null}">
							<a href="cartPage.do" class="position-relative me-4 my-auto"> 
								<i class="fa fa-shopping-bag fa-2x"></i> 
							</a>
						</c:if>
						<c:if test="${member == null}">
							<a href="loginPage.do" class="position-relative me-4 my-auto"> 
								<i class="fa fa-shopping-bag fa-2x"></i> 
							</a>
						</c:if>
						<!-- 장바구니 버튼 -->
						<!-- 프로필 버튼 -->
						<a href="mypage.do" class="my-auto"> <i class="fas fa-user fa-2x"></i>
						</a>
						<!-- 프로필 버튼 -->
						</c:if>
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
		<h1 class="text-center text-white display-6">상품 상세</h1>
	</div>
	<!-- Single Page Header End -->


	<!-- Single Product Start -->
	<div class="container-fluid py-5 mt-5">
		<div class="container py-5">
			<div class="row g-4 mb-5">
				<div class="col-lg-8 col-xl-9">
					<c:if test="${productDetail!= null}">
						<div class="row g-4">
							<div class="col-lg-6">
								<div class="border rounded">
									<a href="#"> <img src="${productDetail.imagePath}" class="img-fluid rounded" alt="Image">
									</a>
								</div>
							</div>
							<!-- 제품 상세 -->

							<div class="col-lg-6">
								<h4 class="fw-bold mb-3">${productDetail.pName}</h4>
								<p class="mb-3">카테고리: ${productDetail.category}</p>
								<h5 class="fw-bold mb-3">${productDetail.sellingPrice}원</h5>
								<div class="d-flex mb-4">
									<custom:starRate1 score='${productDetail.ancAvgRating}' index='0'/>
								</div>
								<p class="mb-4">${productDetail.pDetail}</p>
								<div class="input-group quantity mb-5" style="width: 100px;">
									<div class="input-group-btn">
										<button class="btn btn-sm btn-minus rounded-circle bg-light border">
											<i class="fa fa-minus"></i>
										</button>
									</div>
										<input type="text" id="cQty" class="form-control form-control-sm text-center border-0" value="1">
									<div class="input-group-btn">
										<button class="btn btn-sm btn-plus rounded-circle bg-light border">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
								<c:if test="${member != null}">
									<button class="btn border border-secondary rounded-pill px-3 text-primary" onclick="addItemToCart(${productDetail.PID})">장바구니 추가</button>
								</c:if>
								<c:if test="${member == null}">
									<a href="loginPage.do" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> 장바구니 추가</a>
								</c:if>
							</div>

							<!-- 제품 상세 -->
							<div class="col-lg-12">
								<!-- 하단 메뉴 -->
								<nav>
									<div class="nav nav-tabs mb-3">
										<button class="nav-link active border-white border-bottom-0" type="button" role="tab" id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about" aria-controls="nav-about" aria-selected="true">설명</button>
										<button class="nav-link border-white border-bottom-0" type="button" role="tab" id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission" aria-controls="nav-mission" aria-selected="false">리뷰</button>
									</div>
								</nav>
								<!-- 하단 메뉴 -->
								<div class="tab-content mb-5">
									<div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">

										<div class="row bg-light align-items-center justify-content-center py-2">
											<div class="col-6 text-center">
												<p class="mb-0">성분</p>
											</div>
											<div class="col-6 text-left">
												<p class="mb-0">${productDetail.ingredient}</p>
											</div>
										</div>
										<div class="row align-items-center justify-content-center py-2">
											<div class="col-6 text-center text-center">
												<p class="mb-0 text-left">용법</p>
											</div>
											<div class="col-6 text-left">
												<p class="mb-0">${productDetail.usage}</p>
											</div>
										</div>
										<div class="row bg-light align-items-center justify-content-center py-2">
											<div class="col-6 text-center">
												<p class="mb-0">소비기한</p>
											</div>
											<div class="col-6 text-left">
												<p class="mb-0">${productDetail.exp}</p>
											</div>
										</div>

									</div>
									<div class="tab-pane" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">
										<c:forEach var="review" items="${reviewList}" varStatus="loop">
											<div class="d-flex">
												<img src="img/avatar.jpg" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
												<div class="">
													<p class="mb-2" style="font-size: 14px;">${review.createTime}</p>
													<div class="d-flex justify-content-between">
														<h5 class="mt-2">${review.MID}</h5>
														<custom:starRate2 score='${review.score}' index='${loop.index}'/>
													</div>
													<p>${review.contents}</p>
												</div>
											</div>
										</c:forEach>
									</div>
									<div class="tab-pane" id="nav-vision" role="tabpanel">
										<p class="text-dark">Tempor erat elitr rebum at clita. Diam dolor diam ipsum et tempor sit. Aliqu diam amet diam et eos labore. 3</p>
										<p class="mb-0">Diam dolor diam ipsum et tempor sit. Aliqu diam amet diam et eos labore. Clita erat ipsum et lorem et sit</p>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- Single Product End -->


	<!-- 풋터 시작 -->
	<custom:commonFooter/>
	<!-- 풋터 끝 -->
	

	<!-- 카피라이트 시작 -->
	<custom:commonCopyright/>
	<!-- 카피라이트 끝 -->


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