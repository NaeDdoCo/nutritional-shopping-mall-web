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
		CouponDTO cpDTO = new CouponDTO();
		CouponDAO cpDAO = new CouponDAO();
		
		//세션의 member의 MID가져오기
		String MID = (String)session.getAttribute("member");
		
		//쿠폰목록 조회시 미사용_기간만료 쿠폰 삭제하고 가져오기
		cpDTO.setMID(MID);
		cpDTO.setSearchCondition("쿠폰삭제");
		boolean result = cpDAO.delete(cpDTO);
		
		if(result) {
			System.out.println("[log] 기간만료_미사용쿠폰 삭제 완료!");
		}else {
			System.out.println("[log] 기간만료_미사용쿠폰 없음");
		}
		//쿠폰목록조회
		cpDTO.setSearchCondition("쿠폰목록");
		ArrayList<CouponDTO> couponList = cpDAO.selectAll(cpDTO);
		
		//V로 반환
		request.setAttribute("couponList", couponList);
		System.out.println("[log] CouponInfoPageAction couponList ["+ couponList +"]");
		
		forward.setPath("couponInfo.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
