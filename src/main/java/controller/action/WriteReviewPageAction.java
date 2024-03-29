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

public class WriteReviewPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 구매내역에서 리뷰작성버튼 클릭시
		 * 리뷰작성페이지로 이동
		 * 로그인된 회원의 ID와 email정보 전달하기
		 */
		ActionForward forward = new ActionForward();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		
		//세션의 MID 가져오기
		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		
		//DB에서 해당 MID로 email 가져오기
		mDTO.setMID(MID);
		mDTO.setSearchCondition("회원정보");
		mDTO = mDAO.selectOne(mDTO);
		//MID 추가
		mDTO.setMID(MID);
//		System.out.println("[log] WriteReviewPageAction mDTO [" + mDTO + "]");
		
		//MID, email을 V로 전달
		request.setAttribute("reviewInfo", mDTO);
			
		//정보를 보낼 경로와 redirect방식 설정
		forward.setPath("writeReview.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
