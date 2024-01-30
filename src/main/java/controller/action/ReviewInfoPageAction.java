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
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		ReviewDTO rDTO = new ReviewDTO();
		ReviewDAO rDAO = new ReviewDAO();
		
		//내리뷰 목록
		String MID = (String)session.getAttribute("member");
		
		rDTO.setMID(MID);
		rDTO.setSearchCondition("내리뷰");
		//RID, MID, BID, SCORE,CONTENTS,CREATE_TIME,P_ID,P_NAME 받아오기
		ArrayList<ReviewDTO> myReviewList = rDAO.selectAll(rDTO);
		System.out.println("[log] ReviewInfoPageAction myReviewList [" + rDTO + "]");
		
		request.setAttribute("myReviewList", myReviewList);
		
		forward.setPath("reviewInfo.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
	
}
