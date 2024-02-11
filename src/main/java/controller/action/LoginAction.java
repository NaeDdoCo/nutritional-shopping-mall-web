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

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 로그인페이지-로그인 로직
		 */
		ActionForward forward = new ActionForward();
		
		MemberDAO mDAO = new MemberDAO();
		MemberDTO mDTO = new MemberDTO();
		HttpSession session = request.getSession();
		
		//DB에서 존재하는 ID,PW인지 여부 확인 
		mDTO.setSearchCondition("로그인");
		mDTO.setMID((String)request.getParameter("MID"));
		mDTO.setmPassword((String)request.getParameter("mPassword"));
		
		mDTO = mDAO.selectOne(mDTO);
		if (mDTO != null) {
			//결과가 있으면 존재하는 계정
			//계정의 MID를 member라는 이름으로 세션에 보내주고 메인페이지로 이동
			session.setAttribute("member", mDTO.getMID());
			forward.setPath("mainPage.do");
			forward.setRedirect(true);
		} else {
			//아니면 없는 계정
			//로그인실패 계정 결과 보내주고 로그인페이지로 이동
			request.setAttribute("loginResult", false);
			forward.setPath("loginPage.do");
			forward.setRedirect(false);
		}
		return forward;
	}

}
