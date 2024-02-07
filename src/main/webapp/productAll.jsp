<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>상품 전체 출력</title>
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
<!-- error.do 범인 -->
<link href="css/style.css" rel="stylesheet">
<!-- error.do 범인 -->
<link rel="stylesheet" type="text/css" href="css/star-rating+.css" />
</head>
<body>


	<!-- 장바구니 추가 비동기처리 -->
	<script>
		function addItemToCart(PID) {
			$.ajax({
				type : "POST", // 또는 "GET"
				url : "insertCart", // 서버에서 아이디 중복 확인을 처리할 PHP 파일 경로
				data : {'PID' : PID},
				success : function(data) {
					console.log(data);
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
	
	<!-- 가격 필터 -->
    <script>
		function updateURL() {
	        // JavaScript를 사용하여 value를 가져와서 href를 동적으로 설정
	        document.getElementById('filterbtn').href = document.getElementById('filterbtn').href + '&price=' + document.getElementById('amount').textContent;
	       	console.log('textContext: '+ document.getElementById('amount').textContent);
	       	console.log('value: '+ document.getElementById('amount').value);
		}
    </script>
	<!-- 가격 필터 -->
	


	<!-- Spinner Start -->
	<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->


	<!-- Navbar start -->
	<div class="container-fluid fixed-top">
		<custom:commonHeader/>
		<custom:commonHeaderWithLogo/>
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
		<h1 class="text-center text-white display-6">상품 전체 출력</h1>
	</div>
	<!-- Single Page Header End -->


	<!-- Fruits Shop Start-->
	<div class="container-fluid fruite py-5">
		<div class="container py-5">
			<div class="row g-4">
				<div class="col-lg-12">
					<div class="row g-4">
						<div class="col-xl-3"></div>
						<div class="col-6"></div>
						<div class="col-xl-3">
							<div class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
								<label for="fruits">Default Sorting:</label>
								<select id="fruits" name="fruitlist" class="border-0 form-select-sm bg-light me-3" form="fruitform">
									<option value="volvo">Nothing</option>
									<option value="saab">Popularity</option>
									<option value="opel">Organic</option>
									<option value="audi">Fantastic</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row g-4">
						<div class="col-lg-3">
							<div class="row g-4">
								<div class="col-lg-12">
									<!-- 카테고리 -->
									<div class="mb-3">
										<h4>카테고리</h4>
										<ul class="list-unstyled fruite-categorie">
											<li>
												<div class="d-flex justify-content-between fruite-name">
													<a href="productAllPage.do?category=간"> <i class="fas fa-apple-alt me-2"></i>간
													</a>
												</div>
											</li>
											<li>
												<div class="d-flex justify-content-between fruite-name">
													<a href="productAllPage.do?category=눈"> <i class="fas fa-apple-alt me-2"></i>눈
													</a>
												</div>
											</li>
											<li>
												<div class="d-flex justify-content-between fruite-name">
													<a href="productAllPage.do?category=두뇌"> <i class="fas fa-apple-alt me-2"></i>두뇌
													</a>
												</div>
											</li>
											<li>
												<div class="d-flex justify-content-between fruite-name">
													<a href="productAllPage.do?category=면역"> <i class="fas fa-apple-alt me-2"></i>면역
													</a>
												</div>
											</li>
											<li>
												<div class="d-flex justify-content-between fruite-name">
													<a href="productAllPage.do?category=뼈/치아"> <i class="fas fa-apple-alt me-2"></i>뼈/치아
													</a>
												</div>
											</li>
											<li>
												<div class="d-flex justify-content-between fruite-name">
													<a href="productAllPage.do?category=소화"> <i class="fas fa-apple-alt me-2"></i>소화
													</a>
												</div>
											</li>
											<li>
												<div class="d-flex justify-content-between fruite-name">
													<a href="productAllPage.do?category=피부"> <i class="fas fa-apple-alt me-2"></i>피부
													</a>
												</div>
											</li>
											<li>
												<div class="d-flex justify-content-between fruite-name">
													<a href="productAllPage.do?category=활력"> <i class="fas fa-apple-alt me-2"></i>활력
													</a>
												</div>
											</li>
										</ul>
									</div>
									<!-- 카테고리 -->
								</div>
								<div class="col-lg-12">
									<!-- 가격 설정 -->
									<div class="mb-3">
										<h4 class="mb-2">가격</h4>
										<input type="range" class="form-range w-100" id="rangeInput" name="rangeInput" min="0" max="50000" value="0" oninput="amount.value=rangeInput.value">
										<output id="amount" name="amount" for="rangeInput">0</output>
									</div>
									<!-- 가격 설정 -->
								</div>
								<div class="col-lg-12">
									<div class="mb-3"></div>
								</div>
								<div class="col-lg-12">
									<!-- 필터 검색 버튼 -->
									<div class="d-flex justify-content-center my-4">
										<c:set var="currentURL" value="${request.getRequetURL()}" />
										<a href="${currentURL}?category=${param.category}" id="filterbtn" onclick="updateURL()" class="btn border border-secondary px-4 py-3 rounded-pill text-primary w-100">필터 검색</a>
									</div>
									<!-- 필터 검색 버튼 -->
								</div>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="row g-4 justify-content-center">
								<!-- 전체 제품 표시 -->
								<c:if test="${fn:length(pDTOs) > 0}">
									<c:forEach var="data" items="${pDTOs}">
										<div class="col-md-6 col-lg-6 col-xl-4">
											<div class="p-4 border border-secondary rounded position-relative fruite-item">
												<div class="fruite-img" onclick='location.href="productDetailPage.do?PID=${data.PID}";'>
													<img src=${data.imagePath } class="img-fluid w-100 rounded-top" alt="">
												</div>
												<div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${data.category}</div>
												<div>
													<h4 style="text-align:center;">${data.pName}</h4>
													<div class="star-rating space-x-4 mx-auto">
														<input type="radio" id="5-stars" name="rating" value="5" disabled/> 
														<label for="5-stars" class="star">★</label> 
														<input type="radio" id="4-stars" name="rating" value="4" disabled/> 
														<label for="4-stars" class="star">★</label> 
														<input type="radio" id="3-stars" name="rating" value="3" disabled/> 
														<label for="3-stars" class="star">★</label> 
														<input type="radio" id="2-stars" name="rating" value="2" disabled/> 
														<label for="2-stars" class="star">★</label> 
														<input type="radio" id="1-star" name="rating" value="1" disabled/> 
														<label for="1-star" class="star">★</label>
													</div>						
													<div class="line-clamp my-2" style="height: 150px;">
														<p>${data.pDetail}</p>
													</div>
													<div class="d-flex justify-content-between flex-lg-wrap">
														<p class="text-dark fs-5 fw-bold mb-0">${data.sellingPrice}원</p>
														<div class="row">
															<button class="btn border border-secondary rounded-pill px-3 text-primary" onclick="addItemToCart(${data.PID})">장바구니 추가</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
								<!-- 전체 제품 표시 -->
								<!-- 하단 페이지네이션 -->
								<div class="pagination d-flex justify-content-center mt-5">
									<c:forEach var="page" begin="1" end="${totalPages}">
										<c:url var="pageUrl" value="productAllPage.do">
											<c:param name="page" value="${page}" />
										</c:url>
										<a class="rounded" href="<c:out value="${pageUrl}" />"><c:out value="${page}" /></a>
									</c:forEach>
								</div>
								<!-- 하단 페이지네이션 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Fruits Shop End-->


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
	
	
	<!-- 별점 표시 -->
	<script>
		var radioButton = document.getElementById("${ReviewDetail.score}-stars");
		radioButton.checked = true;
	</script>
	
</body>

</html>