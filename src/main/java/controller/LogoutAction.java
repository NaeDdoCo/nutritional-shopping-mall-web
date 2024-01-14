package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		session.removeAttribute("member");
		
		forward.setPath("mainPage.do"); 
		//로그아웃 성공시 구분할 값 : result 
		request.setAttribute("result", true);
		forward.setRedirect(false);

		return forward;
	
	}
	

}
