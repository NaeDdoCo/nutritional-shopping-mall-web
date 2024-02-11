package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class ErrorAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 에러페이지
		 */
		ActionForward forward = new ActionForward();
		forward.setPath("error.jsp");
		forward.setRedirect(true);
		
		return forward;
	}

	
		
}
