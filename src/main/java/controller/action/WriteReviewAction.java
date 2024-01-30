package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.ReviewDAO;
import model.dto.ReviewDTO;

public class WriteReviewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		//V.buyinfo.jsp -> BID 받기

		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		
		String strScore = (String)request.getParameter("score");
		String contents = (String)request.getParameter("contents");
		String strBID = (String)request.getParameter("BID");
		
		int BID = Integer.parseInt(strBID);
		int score = Integer.parseInt(strScore);
		
		ReviewDTO rDTO = new ReviewDTO();
		ReviewDAO rDAO = new ReviewDAO();

		//리뷰작성(이름,이메일,별점,내용)
		rDTO.setMID(MID);
		rDTO.setBID(BID);
		rDTO.setScore(score);
		rDTO.setContents(contents);
		rDTO.setSearchCondition("리뷰작성");
		System.out.println("[log] WriteReviewAction rDTO [" + rDTO + "]");
		
		//[작성버튼]누르면 리뷰DB에 insert
		boolean result = rDAO.insert(rDTO);

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
