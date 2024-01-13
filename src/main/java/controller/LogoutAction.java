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
		forward.setRedirect(true);
		//수정예정: 모달창 문구 mainPage.do에서 출력 예정
		//request.setAttribute("msg", "로그아웃이 완료되었습니다! :D");
		//forward.setRedirect(false);
		return forward;
	
	}
	

}
