package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class CheckUserPasswordPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		//넘겨줄 값 x 페이지이동만
		forward.setPath("checkUserPassword.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
