package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class TermsPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 회원가입시 약관동의 페이지
		 */
		ActionForward forward = new ActionForward();
		//정보를 보낼 경로와 redirect방식 설정
		forward.setPath("terms.jsp");
		forward.setRedirect(true);		
		
		return forward;
	}

}
