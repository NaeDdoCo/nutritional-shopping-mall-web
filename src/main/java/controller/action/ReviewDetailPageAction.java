package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.ReviewDAO;
import model.dto.ReviewDTO;

public class ReviewDetailPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		//리뷰상세_필요정보 : 이름,이메일,별점,내용
		//받아 올 정보 : RID
		String strRID = request.getParameter("RID");
		int RID = Integer.parseInt(strRID);
		
		ReviewDTO rDTO = new ReviewDTO();
		ReviewDAO rDAO = new ReviewDAO();
		
		rDTO.setRID(RID);
		rDTO.setSearchCondition("리뷰상세");
		rDTO = rDAO.selectOne(rDTO);
		request.setAttribute("ReviewDetail", rDTO);
		System.out.println("[log] ReviewDetailPageAction rDTO [" + rDTO + "]");
		
		forward.setPath("reviewDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
