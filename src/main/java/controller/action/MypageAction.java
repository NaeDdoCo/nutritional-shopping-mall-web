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

public class MypageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		 
//		이름/성별/생년월일/휴대폰번호/이메일/건강상태
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("member");
		//입력받은 비밀번호 받아오기
		String mPassword = request.getParameter("mPassword");
		
		//DB에서 마이페이지(이름, 생년월일, 성별, 전화번호, 이메일, 주소) 정보 받아오기
		mDTO.setMID(mid);
		mDTO.setmPassword(mPassword);
		mDTO.setSearchCondition("회원정보");
		mDTO = mDAO.selectOne(mDTO);
		
		request.setAttribute("memberInfo", mDTO);
		
		forward.setPath("myPage.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
