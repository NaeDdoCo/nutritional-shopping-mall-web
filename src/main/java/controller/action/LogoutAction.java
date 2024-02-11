package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 로그아웃 로직
		 */
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		//로그아웃시 세션의 member(MID)정보 지우고 메인 화면으로 이동
		session.removeAttribute("member");
		forward.setPath("mainPage.do"); 
		//로그아웃 성공시 구분할 값(logoutResult) 설정
		request.setAttribute("logoutResult", true);
		forward.setRedirect(false);

		return forward;
	
	}
	

}
