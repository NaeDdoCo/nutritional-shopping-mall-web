<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="score" %>

<link rel="stylesheet" type="text/css" href="css/starRating+.css" />

<div class="star-rating mx-auto">
	<input type="radio" id="5-stars2" name="rating" value="5"/> 
	<label class="star">★</label> 
	<input type="radio" id="4-stars2" name="rating" value="4"/> 
	<label class="star">★</label> 
	<input type="radio" id="3-stars2" name="rating" value="3"/> 
	<label class="star">★</label> 
	<input type="radio" id="2-stars2" name="rating" value="2"/> 
	<label class="star">★</label> 
	<input type="radio" id="1-stars2" name="rating" value="1"/> 
	<label class="star">★</label>
</div>

<script>
	console.log("[로그] 별점2:"+${score});
    function setRating2(rating) { 
        var selectedStar = document.getElementById(rating + '-stars2');
        if (selectedStar) {
            selectedStar.checked = true;
        }
    }
    setRating2('${score}');
</script>