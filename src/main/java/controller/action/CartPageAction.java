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

		ActionForward forward = new ActionForward();
		CartDTO cDTO = new CartDTO();
		CartDAO cDAO = new CartDAO();
		HttpSession session = request.getSession();

		//세션에 로그인한 mid로 장바구니 DB목록 가져오기
		String MID = (String)session.getAttribute("member");
		
		cDTO.setMID(MID);
		cDTO.setSearchCondition("장바구니목록출력");
		ArrayList<CartDTO> cartList = cDAO.selectAll(cDTO);
		System.out.println("[log] cartList : "+ cartList);

		request.setAttribute("cartList", cartList);

		forward.setPath("cart.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
