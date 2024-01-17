package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do") // new 가 사실은 작성되어있었던것!!!!!
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MainPageAction mainPageAction;
	private JoinPageAction joinPageAction;
	private JoinAction joinAction;
	private LogoutAction logoutAction;
	private TermsPageAction termsPageAction;
	private MypageAction mypageAction;
	private LoginPageAction loginPageAction;
	private LoginAction loginAction;
	private ProductAllPageAction productAllPageAction;
	private ProductDetailPageAction productDetailPageAction;
	private CartPageAction cartPageAction;
	private CartDeleteAction cartDeleteAction;
	private BuyPageAction buyPageAction;
	private CouponSetPageAction couponSetPageAction;
	private BuyCompPageAction buyCompPageAction;
	private BuyInfoPageAction buyInfoPageAction;
	private WriteReviewPageAction writeReviewPageAction;
	private WriteReviewAction writeReviewAction;
	private ReviewDetailPageAction reviewDetailPageAction;
	private ReviewInfoPageAction reviewInfoPageAction;
	private DeleteReviewAction deleteReviewAction;
	
	public FrontController() {
		super();
		mainPageAction = new MainPageAction();
		joinPageAction = new JoinPageAction();
		joinAction = new JoinAction();
		logoutAction= new LogoutAction();
		termsPageAction = new TermsPageAction();
		mypageAction = new MypageAction();
		loginPageAction = new LoginPageAction();
		loginAction = new LoginAction();
		productAllPageAction = new ProductAllPageAction();
		productDetailPageAction = new ProductDetailPageAction();
		cartPageAction = new CartPageAction();
		cartDeleteAction = new CartDeleteAction();
		buyPageAction = new BuyPageAction();
		couponSetPageAction = new CouponSetPageAction();
		buyCompPageAction = new BuyCompPageAction();
		buyInfoPageAction = new BuyInfoPageAction();
		writeReviewPageAction = new WriteReviewPageAction();
		writeReviewAction = new WriteReviewAction();
		reviewDetailPageAction = new ReviewDetailPageAction();
		reviewInfoPageAction = new ReviewInfoPageAction();
		deleteReviewAction = new DeleteReviewAction();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// controller.jsp 의 코드를 옮겨올예정!

		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String action = uri.substring(cp.length());
		System.out.println("[FrontController] action: " + action);

		ActionForward forward = null;
		if (action.equals("/mainPage.do")) {// 메인페이지
			forward = mainPageAction.execute(request, response);
		} else if (action.equals("/joinPage.do")) {// 회원가입페이지
			forward = joinPageAction.execute(request, response);
		} else if (action.equals("/join.do")) {// 회원가입
			forward = joinAction.execute(request, response);
		} else if (action.equals("/logout.do")) {// 로그아웃
			forward = logoutAction.execute(request, response);
		} else if (action.equals("/termsPage.do")) {// 약관페이지
			forward = termsPageAction.execute(request, response);
		} else if (action.equals("/mypage.do")) {// 마이페이지
			forward = mypageAction.execute(request, response);
		} else if (action.equals("/loginPage.do")) {// 로그인페이지
			forward = loginPageAction.execute(request, response);
		} else if (action.equals("/login.do")) {// 로그인
			forward = loginAction.execute(request, response);
		} else if (action.equals("/productAllPage.do")) {// 상품전체목록
			forward = productAllPageAction.execute(request, response);
		} else if (action.equals("/productDetailPage.do")) {// 상품상세페이지
			forward = productDetailPageAction.execute(request, response);
		} else if (action.equals("/cartPage.do")) {// 장바구니
			forward = cartPageAction.execute(request, response);
		} else if (action.equals("/cartDelete.do")) {// 장바구니 제품 삭제
			forward = cartDeleteAction.execute(request, response);
		} else if (action.equals("/buyPage.do")) {// 구매페이지
			forward = buyPageAction.execute(request, response);
		} else if (action.equals("/couponSetPage.do")) {// 구매페이지에서 쿠폰 적용시
			forward = couponSetPageAction.execute(request, response);
		} else if (action.equals("/buyCompPage.do")) {// 결제완료페이지
			forward = buyCompPageAction.execute(request, response);
		} else if (action.equals("/buyInfoPage.do")) {// 마이페이지-구매내역
			forward = buyInfoPageAction.execute(request, response);
		} else if (action.equals("/writeReviewPage.do")) {// 리뷰작성페이지
			forward = writeReviewPageAction.execute(request, response);
		} else if (action.equals("/writeReview.do")) {// 리뷰작성페이지-리뷰작성
			forward = writeReviewAction.execute(request, response);
		} else if (action.equals("/reviewDetailPage.do")) {// 리뷰상세
			forward = reviewDetailPageAction.execute(request, response);
		} else if (action.equals("/reviewInfoPage.do")) {// 리뷰목록
			forward = reviewInfoPageAction.execute(request, response);
		} else if (action.equals("/deleteReview.do")) {// 리뷰삭제
			forward = deleteReviewAction.execute(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
		
		if (forward == null) {
			response.sendRedirect("error.jsp");
			return;
		}
		if (forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
			// pageContext.forward(forward.getPath());
		}

	}

}
