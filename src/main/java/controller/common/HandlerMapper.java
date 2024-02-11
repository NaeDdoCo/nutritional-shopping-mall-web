package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.action.BuyCompPageAction;
import controller.action.BuyInfoPageAction;
import controller.action.BuyPageAction;
import controller.action.CartDeleteAction;
import controller.action.CartPageAction;
import controller.action.CheckUserPasswordAction;
import controller.action.CheckUserPasswordPageAction;
import controller.action.CouponInfoPageAction;
import controller.action.DeleteReviewAction;
import controller.action.ErrorAction;
import controller.action.JoinAction;
import controller.action.JoinPageAction;
import controller.action.LoginAction;
import controller.action.LoginPageAction;
import controller.action.LogoutAction;
import controller.action.MainPageAction;
import controller.action.ModifyUserInfoAction;
import controller.action.ModifyUserInfoPageAction;
import controller.action.ModifyUserPasswordAction;
import controller.action.ModifyUserPasswordPageAction;
import controller.action.MypageAction;
import controller.action.ProductAllPageAction;
import controller.action.ProductDetailPageAction;
import controller.action.ReviewDetailPageAction;
import controller.action.ReviewInfoPageAction;
import controller.action.TermsPageAction;
import controller.action.WriteReviewAction;
import controller.action.WriteReviewPageAction;
import kakaoLogin.KakaoLoginAction;

public class HandlerMapper {
	private Map<String,Action> mappings;
	
	public HandlerMapper() {
		this.mappings = new HashMap<String,Action>();
//		System.out.println("[log] HandlerMapper ");
		this.mappings.put("/mainPage.do", new MainPageAction()); // 메인페이지
		this.mappings.put("/joinPage.do", new JoinPageAction());// 회원가입페이지
		this.mappings.put("/termsPage.do", new TermsPageAction());// 약관페이지
		this.mappings.put("/join.do", new JoinAction());// 회원가입
		this.mappings.put("/loginPage.do", new LoginPageAction());// 로그인페이지
		this.mappings.put("/login.do", new LoginAction()); // 로그인
		this.mappings.put("/logout.do", new LogoutAction());// 로그아웃
		this.mappings.put("/mypage.do", new MypageAction());// 마이페이지
		this.mappings.put("/checkUserPasswordPage.do", new CheckUserPasswordPageAction());// 마이페이지_비밀번호확인페이지
		this.mappings.put("/checkUserPassword.do", new CheckUserPasswordAction());// 마이페이지_비밀번호확인
		this.mappings.put("/modifyUserInfoPage.do", new ModifyUserInfoPageAction());// 마이페이지_개인정보수정페이지
		this.mappings.put("/modifyUserInfo.do", new ModifyUserInfoAction());// 마이페이지_개인정보수정
		this.mappings.put("/modifyUserPasswordPage.do", new ModifyUserPasswordPageAction());// 마이페이지_비밀번호변경페이지
		this.mappings.put("/modifyUserPassword.do", new ModifyUserPasswordAction());// 마이페이지_비밀번호변경
		this.mappings.put("/buyInfoPage.do", new BuyInfoPageAction());// 마이페이지_구매내역
		this.mappings.put("/couponInfoPage.do", new CouponInfoPageAction());// 마이페이지_쿠폰목록(쿠폰관리)
		this.mappings.put("/writeReviewPage.do", new WriteReviewPageAction());// 리뷰작성페이지
		this.mappings.put("/writeReview.do", new WriteReviewAction());// 리뷰작성
		this.mappings.put("/reviewInfoPage.do", new ReviewInfoPageAction());// 리뷰목록페이지
		this.mappings.put("/reviewDetailPage.do", new ReviewDetailPageAction());// 리뷰상세페이지
		this.mappings.put("/deleteReview.do", new DeleteReviewAction());// 리뷰삭제
		this.mappings.put("/productAllPage.do", new ProductAllPageAction());// 상품전체목록페이지
		this.mappings.put("/productDetailPage.do", new ProductDetailPageAction());// 상품상세페이지
		this.mappings.put("/cartPage.do", new CartPageAction());// 장바구니페이지
		this.mappings.put("/cartDelete.do", new CartDeleteAction());// 장바구니상품삭제
		this.mappings.put("/buyPage.do", new BuyPageAction());// 결제페이지
		this.mappings.put("/buyCompPage.do", new BuyCompPageAction());// 결제완료페이지(구매로직)
		this.mappings.put("/kakaoLogin.do", new KakaoLoginAction());// 카카오계정로그인
		this.mappings.put("/error.do", new ErrorAction());//에러페이지
		
	}
	public Action getAction(String commend) {
		return mappings.get(commend);

	}
	
	
}
