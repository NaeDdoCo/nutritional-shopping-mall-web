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

public class ModifyUserInfoPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 마이페이지-회원정보변경페이지-로그인한 회원정보 출력 로직
		 */
		ActionForward forward = new ActionForward();
		//세션의 MID 가져오기
		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		//로그인된 회원정보 DB에서 조회
		mDTO.setMID(MID);
		mDTO.setSearchCondition("회원정보");
		mDTO = mDAO.selectOne(mDTO);
//		System.out.println("[log]ModifyUserInfoPageAction mDTO [" + mDTO + "]");
		
		//조회된 회원정보 보내주기
		request.setAttribute("memberInfo", mDTO);
		//정보를 보낼 경로와 redirect방식 설정
		//개인정보수정 화면으로 이동
		forward.setPath("modifyUserInfo.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
