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
		String MID = (String)session.getAttribute("member");
		
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		
		mDTO.setSearchCondition("회원정보");
		mDTO.setMID(MID);
		mDTO = mDAO.selectOne(mDTO);
		System.out.println("[log] WriteReviewPageAction mDTO [" + mDTO + "]");
		
		//mid, email정보 보내주기
		request.setAttribute("reviewInfo", mDTO);
				
		forward.setPath("writeReview.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
