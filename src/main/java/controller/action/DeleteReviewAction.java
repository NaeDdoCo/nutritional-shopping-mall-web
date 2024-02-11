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
		/*
		 * 마이페이지-리뷰내역-리뷰삭제 로직
		 */
		ActionForward forward = new ActionForward();
		ReviewDTO rDTO = new ReviewDTO();
		ReviewDAO rDAO = new ReviewDAO();
		//V로부터 RID 받아오기
		String strRID = request.getParameter("RID");
		//int로 형변환
		int RID = Integer.parseInt(strRID);
		
		//DB에서 해당 리뷰삭제
		rDTO.setRID(RID);
		rDTO.setSearchCondition("리뷰삭제");
//		System.out.println("[log] DeleteReviewAction rDTO [" + rDTO + "]");
		
		//삭제결과 result에 담기
		boolean result = rDAO.delete(rDTO);

		//정보를 보낼 경로와 redirect방식 설정
		if(result) {
			//성공시 리뷰목록으로 이동
//			System.out.println("[log] 리뷰삭제 결과 성공!");
			forward.setPath("reviewInfoPage.do");
			forward.setRedirect(false);
		}else {
			//실패시 에러페이지로 이동
//			System.out.println("[log] 리뷰삭제 결과 실패");
			forward.setPath("error.do");
			forward.setRedirect(true);
		}
		return forward;
	}

}
