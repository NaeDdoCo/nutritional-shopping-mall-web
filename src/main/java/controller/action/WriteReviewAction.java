package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.BuyInfoDAO;
import model.dao.ReviewDAO;
import model.dto.BuyInfoDTO;
import model.dto.ReviewDTO;

public class WriteReviewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		ReviewDTO rDTO = new ReviewDTO();
		ReviewDAO rDAO = new ReviewDAO();
		BuyInfoDTO bDTO = new BuyInfoDTO();
		BuyInfoDAO bDAO = new BuyInfoDAO();
		
		//세션의 MID 가져오기
		String MID = (String)session.getAttribute("member");
		
		//V_buyinfo.jsp -> BID/score/contents 가져오기
		String strBID = (String)request.getParameter("BID");
		String strScore = (String)request.getParameter("score");
		String contents = (String)request.getParameter("contents");
		//int로 변환
		int BID = Integer.parseInt(strBID);
		int score = Integer.parseInt(strScore);
		
		//MID,BID,score,contents로 reviewInsert
		rDTO.setMID(MID);
		rDTO.setBID(BID);
		rDTO.setScore(score);
		rDTO.setContents(contents);
		rDTO.setSearchCondition("리뷰작성");
		System.out.println("[log] WriteReviewAction rDTO [" + rDTO + "]");
		boolean rDTOresult = rDAO.insert(rDTO);
		
		//리뷰작성유무확인
		//BID로 HAS_RIVIEW = 1로 업데이트
		bDTO.setBID(BID);
		bDTO.setSearchCondition("리뷰유무");
		boolean bDTOresult = bDAO.update(bDTO);
		
		//리뷰작성성공시 HAS_RIVIEW도 함께 업데이트하여 페이지이동
		if(rDTOresult && bDTOresult) {
			//성공시 리뷰목록으로 이동
			System.out.println("[log] reviewInsert & hasReviewUpdate 성공! ");
			forward.setPath("reviewInfoPage.do");
			forward.setRedirect(false);
		}else {
			//실패시 에러페이지
			System.out.println("[log] reviewInsert & hasReviewUpdate 실패 ");
			forward.setPath("error.do");
			forward.setRedirect(true);
		}

		return forward;
	}

}
