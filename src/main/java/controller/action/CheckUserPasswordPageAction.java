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
		/*
		 * 마이페이지-개인정보수정/비밀번호변경 선택시 
		 * 현재 비밀번호를 입력하는 페이지로 이동
		 */
		ActionForward forward = new ActionForward();
		//정보를 보낼 경로와 redirect방식 설정
		forward.setPath("checkUserPassword.jsp");
		//GET방식으로 넘어온 where정보를 보내줘야하기 때문에 forward
		forward.setRedirect(false);
		
		return forward;
	}

}
