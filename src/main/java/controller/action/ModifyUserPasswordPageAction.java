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
		
		ActionForward forward = new ActionForward();
		//비밀번호 업데이트 화면으로 이동
		//넘겨줄 데이터xx
		forward.setPath("modifyUserPassword.jsp");
		forward.setRedirect(true);
		
		return forward;
	}

}
