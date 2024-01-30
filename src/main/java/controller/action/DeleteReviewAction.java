package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.ReviewDAO;
import model.dto.ReviewDTO;

public class DeleteReviewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		//RID 받아와서 리뷰삭제
		String strRID = request.getParameter("RID");

		int RID = Integer.parseInt(strRID);
		ReviewDTO rDTO = new ReviewDTO();
		ReviewDAO rDAO = new ReviewDAO();
		rDTO.setRID(RID);
		rDTO.setSearchCondition("리뷰삭제");
		System.out.println("[log] DeleteReviewAction rDTO [" + rDTO + "]");
		
		boolean result = rDAO.delete(rDTO);

		if(result) {
			//성공시 리뷰목록으로 이동
			forward.setPath("reviewInfoPage.do");
			forward.setRedirect(false);
		}else {
			//실패시 에러페이지
			forward.setPath("error.do");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
