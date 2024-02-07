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

public class ModifyUserInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		//입력받은 회원정보 update
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		//개인정보에서 변경할 값 : mName/phoneNumber/email/address
		
		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		String mName = request.getParameter("mName");

		// String 조합
		String phoneNumber = request.getParameter("phoneNum1") + "-" + request.getParameter("phoneNum2") + "-" + request.getParameter("phoneNum3");
		String email = request.getParameter("email1") + "@" + request.getParameter("email2");

		String zipNo = request.getParameter("zipcode");
		String roadAddrPart1 = request.getParameter("address1");
		String addrDetail = request.getParameter("address2");
		
		mDTO.setSearchCondition("회원정보변경");
		mDTO.setMID(MID);
		mDTO.setmName(mName);
		mDTO.setPhoneNumber(phoneNumber);
		mDTO.setEmail(email);
		mDTO.setmAddress(roadAddrPart1);
		mDTO.setmDetailedAddress(addrDetail);
		mDTO.setmPostCode(Integer.parseInt(zipNo));
		System.out.println("update memberDTO: " + mDTO);
		boolean result = mDAO.update(mDTO);
		
		if(result) {
			//개인정보수정 성공
			System.out.println("[log] ModifyUserInfoAction 개인정보 수정 성공!");
			forward.setPath("myPage.do");
			forward.setRedirect(false);
		}else {
			//개인정보수정 실패
			System.out.println("[log] ModifyUserInfoAction 개인정보 수정 실패");
		}

		return forward;
	}

}
