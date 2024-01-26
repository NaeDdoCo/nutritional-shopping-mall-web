package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.CartDAO;
import model.dto.CartDTO;

public class CartDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		ActionForward forward = new ActionForward();
		
		// DB에서 선택한 상품 삭제
		CartDTO cDTO = new CartDTO();
		CartDAO cDAO = new CartDAO();
		
		String strCid = (String)request.getParameter("cid");
		System.out.println("cart delete strCid: " + strCid);
		int intCid = Integer.parseInt(strCid);
		
		cDTO.setSearchCondition("장바구니삭제");
		cDTO.setCID(intCid);
		if (cDAO.delete(cDTO)) {
			forward.setPath("cartPage.do");
			forward.setRedirect(true);
		} else {
			forward.setPath("error.do");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
