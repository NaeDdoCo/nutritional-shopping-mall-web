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
		/*
		 * 장바구니페이지-장바구니 상품 삭제 로직
		 */
		
		ActionForward forward = new ActionForward();
		
		//DB에서 선택한 상품 삭제
		CartDTO cDTO = new CartDTO();
		CartDAO cDAO = new CartDAO();
		
		//V에서 선택된 상품의 CID 받아오기
		String strCid = (String)request.getParameter("CID");
//		System.out.println("[log] cart delete strCid: " + strCid);
		int intCid = Integer.parseInt(strCid);
		
		cDTO.setSearchCondition("장바구니비우기");
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
