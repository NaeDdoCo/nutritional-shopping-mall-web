package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class WriteReviewPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		
		//세션의 MID 가져오기
		String MID = (String)session.getAttribute("member");
		
		//MID로 email 가져오기
		mDTO.setMID(MID);
		mDTO.setSearchCondition("회원정보");
		mDTO = mDAO.selectOne(mDTO);
		//MID 추가
		mDTO.setMID(MID);
		System.out.println("[log] WriteReviewPageAction mDTO [" + mDTO + "]");
		
		//MID, email 정보 보내주기
		request.setAttribute("reviewInfo", mDTO);
				
		forward.setPath("writeReview.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
