package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;

public class ModifyUserPasswordPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 마이페이지-비밀번호 변경 페이지로 이동하는 로직
		 */
		ActionForward forward = new ActionForward();
		//정보를 보낼 경로와 redirect방식 설정
		forward.setPath("modifyUserPassword.jsp");
		//보낼 데이터 없음
		forward.setRedirect(true);
		
		return forward;
	}

}
