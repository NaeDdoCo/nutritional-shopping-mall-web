package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.ReviewDAO;
import model.dto.ReviewDTO;

public class ReviewInfoPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 마이페이지-리뷰내역 조회 로직
		 */
		ActionForward forward = new ActionForward();
		ReviewDTO rDTO = new ReviewDTO();
		ReviewDAO rDAO = new ReviewDAO();
		
		//세션의 MID가져오기
		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		
		//DB에서 해당 MID의 리뷰목록 가져오기 
		rDTO.setMID(MID);
		rDTO.setSearchCondition("내리뷰");
		ArrayList<ReviewDTO> myReviewList = rDAO.selectAll(rDTO);
//		System.out.println("[log] ReviewInfoPageAction myReviewList [" + myReviewList + "]");
		
		//조회된 리뷰목록 V로 전달
		request.setAttribute("myReviewList", myReviewList);
		
		//정보를 보낼 경로와 redirect방식 설정
		forward.setPath("reviewInfo.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
	
}
