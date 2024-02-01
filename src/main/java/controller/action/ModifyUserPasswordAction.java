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

public class ModifyUserPasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		//입력받은 비밀번호 일치시 비밀번호 업데이트
		
		HttpSession session = request.getSession();
		String MID = (String) session.getAttribute("member");
		String MPW = request.getParameter("mPassword");
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();

		mDTO.setMID(MID);
		mDTO.setmPassword(MPW);
		mDTO.setSearchCondition("비밀번호변경");
		boolean result = mDAO.update(mDTO);
		
		if(result) {
			System.out.println("[log] 비밀번호변경 성공!");
			//비밀번호 변경시 계정 로그아웃 후 메인화면으로 이동
			session.removeAttribute("member");
			forward.setPath("mainPage.do");
			//로그아웃시 main에 표시할 모달정보 전달
			request.setAttribute("logoutResult", true);
			forward.setRedirect(false);
		}else {
			System.out.println("[log] 비밀번호변경 실패");
		}
		
		return forward;
	}

}
