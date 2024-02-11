package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class JoinPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 회원가입페이지로 이동하는 로직
		 */
		ActionForward forward = new ActionForward();
		//정보를 보낼 경로와 redirect방식 설정
		forward.setPath("join.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
