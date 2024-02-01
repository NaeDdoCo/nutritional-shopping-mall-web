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
		ActionForward forward = new ActionForward();
		//C -> V에 전달해줄 정보
		//이름,생년월일,성별,핸드폰번호,이메일,주소
		
		//입력되어있던 핸드폰 번호와 일치할 경우 인증번호x
		//입력되어있던 핸드폰 번호와 일치하지 경우 인증번호 필수
		
		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		
		mDTO.setMID(MID);
		mDTO.setSearchCondition("회원정보");
		mDTO = mDAO.selectOne(mDTO);
		System.out.println("[log]ModifyUserInfoPageAction mDTO [" + mDTO + "]");
		
		//DB에 저장되어있던 회원정보 보내주기
		request.setAttribute("memberInfo", mDTO);
		
		forward.setPath("modifyUserInfo.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
