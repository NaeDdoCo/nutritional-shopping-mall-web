package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.action.BuyCompPageAction;
import controller.action.BuyInfoPageAction;
import controller.action.BuyPageAction;
import controller.action.CartDeleteAction;
import controller.action.CartPageAction;
import controller.action.CouponSetPageAction;
import controller.action.DeleteReviewAction;
import controller.action.ErrorAction;
import controller.action.JoinAction;
import controller.action.JoinPageAction;
import controller.action.LoginAction;
import controller.action.LoginPageAction;
import controller.action.LogoutAction;
import controller.action.MainPageAction;
import controller.action.MypageAction;
import controller.action.ProductAllPageAction;
import controller.action.ProductDetailPageAction;
import controller.action.ReviewDetailPageAction;
import controller.action.ReviewInfoPageAction;
import controller.action.TermsPageAction;
import controller.action.WriteReviewPageAction;
import controller.async.InsertCartAction;

public class HandlerMapper {
	private Map<String,Action> mappings;
	
	public HandlerMapper() {
		this.mappings = new HashMap<String,Action>();
		
		System.out.println("매퍼로그");
		
		this.mappings.put("/mainPage.do", new MainPageAction()); // 메인페이지
		this.mappings.put("/joinPage.do", new JoinPageAction());// 회원가입페이지
		this.mappings.put("/join.do", new JoinAction());// 회원가입
		this.mappings.put("/loginPage.do", new LoginPageAction());// 회원가입
		this.mappings.put("/logout.do", new LogoutAction());// 로그아웃
		this.mappings.put("/termsPage.do", new TermsPageAction());// 약관페이지
		this.mappings.put("/mypage.do", new MypageAction());// 마이페이지
		this.mappings.put("/login.do", new LoginAction()); // 로그인페이지
		this.mappings.put("/productAllPage.do", new ProductAllPageAction());// 상품전페목록
		this.mappings.put("/productDetailPage.do", new ProductDetailPageAction());// 상품상세페이지
		this.mappings.put("/cartPage.do", new CartPageAction());// 장바구니
		this.mappings.put("/cartDelete.do", new CartDeleteAction());// 장바구니 상품 삭제
		this.mappings.put("/buyPage.do", new BuyPageAction());// 구매페이지
		this.mappings.put("/couponSetPage.do", new CouponSetPageAction());// 구매페이지에서 쿠폰 적용시
		this.mappings.put("/buyCompPage.do", new BuyCompPageAction());// 결제완료페이지
		this.mappings.put("/buyInfoPage.do", new BuyInfoPageAction());// 마이페이지-구매내역
		this.mappings.put("/writeReviewPage.do.do", new WriteReviewPageAction());// 리뷰작성페이지
		this.mappings.put("/reviewDetailPage.do", new ReviewDetailPageAction());// 리뷰상세
		this.mappings.put("/reviewInfoPage.do", new ReviewInfoPageAction());// 리뷰목록
		this.mappings.put("/deleteReview.do", new DeleteReviewAction());// 리뷰삭제
		//에러상황일때
		this.mappings.put("/error.do", new ErrorAction());
		
	}
	public Action getAction(String commend) {
		return mappings.get(commend);

	}
	
	
}
