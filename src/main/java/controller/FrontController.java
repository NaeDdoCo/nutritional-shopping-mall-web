package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

@WebServlet("*.do") // new 가 사실은 작성되어있었던것!!!!!
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
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
			forward = new MainPageAction().execute(request, response);
		} else if (action.equals("/joinPage.do")) {// 회원가입페이지
			forward = new JoinPageAction().execute(request, response);
		} else if (action.equals("/join.do")) {// 회원가입
			forward = new JoinAction().execute(request, response);
		} else if (action.equals("/logout.do")) {// 로그아웃
			forward = new LogoutAction().execute(request, response);
		} else if (action.equals("/termsPage.do")) {// 약관페이지
			forward = new TermsPageAction().execute(request, response);
		} else if (action.equals("/mypage.do")) {// 마이페이지
			forward = new MypageAction().execute(request, response);
		} else if (action.equals("/loginPage.do")) {// 로그인페이지
			forward = new LoginPageAction().execute(request, response);
		} else if (action.equals("/login.do")) {// 로그인
			forward = new LoginAction().execute(request, response);
		} else if (action.equals("/productAllPage.do")) {// 상품전체목록
			forward = new ProductAllPageAction().execute(request, response);
		} else if (action.equals("/productDetailPage.do")) {// 상품상세페이지
			forward = new ProductDetailPageAction().execute(request, response);
		} else if (action.equals("/cartPage.do")) {// 장바구니
			forward = new CartPageAction().execute(request, response);
		} else if (action.equals("/cartDelete.do")) {// 장바구니 제품 삭제
			forward = new CartDeleteAction().execute(request, response);
		} else if (action.equals("/buyPage.do")) {// 구매페이지
			forward = new BuyPageAction().execute(request, response);
		} else if (action.equals("/couponSetPage.do")) {// 구매페이지에서 쿠폰 적용시
			forward = new CouponSetPageAction().execute(request, response);
		} else if (action.equals("/buyCompPage.do")) {// 결제완료페이지
			forward = new BuyCompPageAction().execute(request, response);
		} else if (action.equals("/buyInfoPage.do")) {// 마이페이지-구매내역
			forward = new BuyInfoPageAction().execute(request, response);
		} else if (action.equals("/writeReviewPage.do")) {// 리뷰작성페이지
			forward = new WriteReviewPageAction().execute(request, response);
		} else if (action.equals("/writeReview.do")) {// 리뷰작성페이지-리뷰작성
			forward = new WriteReviewAction().execute(request, response);
		} else if (action.equals("/reviewDetailPage.do")) {// 리뷰상세
			forward = new ReviewDetailPageAction().execute(request, response);
		} else if (action.equals("/reviewInfoPage.do")) {// 리뷰목록
			forward = new ReviewInfoPageAction().execute(request, response);
		} else if (action.equals("/deleteReview.do")) {// 리뷰삭제
			forward = new DeleteReviewAction().execute(request, response);
		}

		/*
		 * 모델 테스트용 .do
		 * 
		 * 조건문 안에 내용은 테스트 예제 코드
		 */
		else if (action.equals("/testSelectAll.do")) {
			System.out.println("[FrontController] testSelectAll");

			// 테스트 페이지로 리다이렉트
			forward = new ActionForward();
			forward.setPath("test.jsp");
			forward.setRedirect(true);

			MemberDTO mDTO = new MemberDTO();
			MemberDAO mDAO = new MemberDAO();

			// 필요한 요소에 맞게 set
			mDTO.setMid("teemo");
			ArrayList<MemberDTO> datas = mDAO.selectAll(mDTO);

			// 결과 확인
			if (datas == null) {
				System.out.println("[FrontController] datas == null");
			} else {
				for (MemberDTO data : datas) {
					System.out.println(data);
				}
			}

		} else if (action.equals("/testSelectOne.do")) {
			System.out.println("[FrontController] testSelectOne");
			
			// 테스트 페이지로 리다이렉트
			forward = new ActionForward();
			forward.setPath("test.jsp");
			forward.setRedirect(true);
			
			MemberDTO mDTO = new MemberDTO();
			MemberDAO mDAO = new MemberDAO();

			// 필요한 요소에 맞게 set
			mDTO.setMid("teemo");
			mDTO = mDAO.selectOne(mDTO);

			// 결과 확인
			System.out.println(mDTO);
		} else if (action.equals("/testInsert.do")) {
			System.out.println("[FrontController] testInsert");

			// 테스트 페이지로 리다이렉트
			forward = new ActionForward();
			forward.setPath("test.jsp");
			forward.setRedirect(true);
			
			MemberDTO mDTO = new MemberDTO();
			MemberDAO mDAO = new MemberDAO();

			// 필요한 요소에 맞게 set
			mDTO.setMid("teemo");

			// 결과 확인 + DB에서 직접 확인
			System.out.println(mDAO.insert(mDTO));
		} else if (action.equals("/testUpdate.do")) {
			System.out.println("[FrontController] testUpdate");

			// 테스트 페이지로 리다이렉트
			forward = new ActionForward();
			forward.setPath("test.jsp");
			forward.setRedirect(true);
			
			MemberDTO mDTO = new MemberDTO();
			MemberDAO mDAO = new MemberDAO();

			// 필요한 요소에 맞게 set
			mDTO.setMid("teemo");

			// 결과 확인 + DB에서 직접 확인
			System.out.println(mDAO.update(mDTO));
		} else if (action.equals("/testDelete.do")) {
			System.out.println("[FrontController] testDelete");

			// 테스트 페이지로 리다이렉트
			forward = new ActionForward();
			forward.setPath("test.jsp");
			forward.setRedirect(true);
			
			MemberDTO mDTO = new MemberDTO();
			MemberDAO mDAO = new MemberDAO();

			// 필요한 요소에 맞게 set
			mDTO.setMid("teemo");

			// 결과 확인 + DB에서 직접 확인
			System.out.println(mDAO.delete(mDTO));
		}

		else {
			// 404
			// TODO: 에러페이지
		}

		if (forward == null) {
			// 에러 상황
			forward = new ActionForward();
			forward.setPath("");
			forward.setRedirect(true);

			request.setAttribute("msg", "forward 가 null 입니다..........");
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
