<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="score" %>

<link rel="stylesheet" type="text/css" href="css/starRating+.css" />

<div class="star-rating mx-auto">
	<input type="radio" id="5-stars1" name="rating" value="5"/> 
	<label class="star">★</label> 
	<input type="radio" id="4-stars1" name="rating" value="4"/> 
	<label class="star">★</label> 
	<input type="radio" id="3-stars1" name="rating" value="3"/> 
	<label class="star">★</label> 
	<input type="radio" id="2-stars1" name="rating" value="2"/> 
	<label class="star">★</label> 
	<input type="radio" id="1-stars1" name="rating" value="1"/> 
	<label class="star">★</label>
</div>

<script>
	console.log("[로그] 별점1:"+${score});
    function setRating1(rating) { 
    	console.log("[로그] 별점1: 스크립트 진입");
        var selectedStar = document.getElementById(rating + '-stars1');
        console.log("[로그] 별점1: 엘레먼트 습득");
        if (selectedStar) {
        	console.log("[로그] 별점1: if문 진입");
            selectedStar.checked = true;
            console.log("[로그] 별점1: 엘레먼트 체크 true화");
        }
    }
    setRating1(${score});
</script>