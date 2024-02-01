package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class CheckUserPasswordAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		//입력받은 비밀번호가 일치하는지 확인
		//세션의 MID 받아오기
		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		String MPW = request.getParameter("mPassword");

		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();

		mDTO.setMID(MID);
		mDTO.setmPassword(MPW);
		mDTO.setSearchCondition("비밀번호확인");	
		mDTO = mDAO.selectOne(mDTO);

		//비밀번호가 일치하면
		//어디로 보낼지 (modifyUserInfo or modifyUserPassword)
		if (mDTO != null) {
			// 비밀번호 일치
			System.out.println("[log] CheckUserPasswordAction 비밀번호 일치!");
		
			//where값 받아오기
			String getPath = request.getParameter("where");

			//where값에 따른 경로 설정
			if(getPath.equals("modifyUserInfo")) {
				forward.setPath("modifyUserInfoPage.do");
				forward.setRedirect(false);
			}else if(getPath.equals("modifyUserPassword")) {
				forward.setPath("modifyUserPasswordPage.do");
				forward.setRedirect(false);
			}else {
				forward.setPath("error.do");
				forward.setRedirect(true);
			}
		} else {
			// 비밀번호 불일치
			System.out.println("[log] CheckUserPasswordAction 비밀번호 불일치");
		}

		return forward;
	}

}
