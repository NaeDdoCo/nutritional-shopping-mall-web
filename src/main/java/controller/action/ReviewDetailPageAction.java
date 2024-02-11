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
		/*
		 * 리뷰상세조회로직
		 */
		ActionForward forward = new ActionForward();
		ReviewDTO rDTO = new ReviewDTO();
		ReviewDAO rDAO = new ReviewDAO();
		//V로부터 RID가져오기
		String strRID = request.getParameter("RID");
		//int로 변환
		int RID = Integer.parseInt(strRID);
		
		//DB에서 해당 리뷰내용 가져오기 
		//리뷰상세 필요정보 : MID,이메일,별점,내용,IMG
		rDTO.setRID(RID);
		rDTO.setSearchCondition("리뷰상세");
		rDTO = rDAO.selectOne(rDTO);
		//조회된 리뷰내용 V로 전달
		request.setAttribute("ReviewDetail", rDTO);
//		System.out.println("[log] ReviewDetailPageAction rDTO [" + rDTO + "]");
		
		//정보를 보낼 경로와 redirect방식 설정
		forward.setPath("reviewDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
