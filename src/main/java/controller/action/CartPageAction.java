package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.CartDAO;
import model.dto.CartDTO;


public class CartPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 장바구니페이지-장바구니 상품 조회 로직
		 */
		ActionForward forward = new ActionForward();
		CartDTO cDTO = new CartDTO();
		CartDAO cDAO = new CartDAO();
		HttpSession session = request.getSession();

		//세션에 로그인한 MID 가져오기
		String MID = (String)session.getAttribute("member");
		
		//DB에서 장바구니 목록 조회하여 가져오기
		cDTO.setMID(MID);
		cDTO.setSearchCondition("장바구니목록출력");
		ArrayList<CartDTO> cartList = cDAO.selectAll(cDTO);
//		System.out.println("[log] CartPageAction_cartList : "+ cartList);
		
		//조회된 장바구니목록(cartList) 전달
		request.setAttribute("cartList", cartList);
		
		//정보를 보낼 경로와 redirect방식 설정
		forward.setPath("cart.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
