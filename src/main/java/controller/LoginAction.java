package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		MemberDAO mDAO = new MemberDAO();
		MemberDTO mDTO = new MemberDTO();
		
		HttpSession session = request.getSession();
		
		mDTO.setSearchCondition("로그인");
		mDTO.setMid((String)request.getParameter("MID"));
		mDTO.setmPassword((String)request.getParameter("mPassword"));
		
		mDTO = mDAO.selectOne(mDTO);
		if (mDTO != null) {
			session.setAttribute("member", mDTO.getMid());
			forward.setPath("mainPage.do");
			forward.setRedirect(true);
			return forward;
		}
		
		return null;
	}

}
