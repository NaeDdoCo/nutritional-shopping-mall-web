package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.CouponDAO;
import model.dto.CouponDTO;

public class CouponInfoPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		CouponDTO cDTO = new CouponDTO();
		CouponDAO cDAO = new CouponDAO();
		//세션의 MID 가져오기
		String MID = (String)session.getAttribute("member");
		//MID로 쿠폰목록 가져와서 보내기
		cDTO.setMID(MID);
		ArrayList<CouponDTO> couponList = cDAO.selectAll(cDTO);
		request.setAttribute("couponList", couponList);
		
		forward.setPath("couponInfo.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
