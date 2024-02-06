package kakaoLogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import kotlin.collections.CollectionSystemProperties;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class KakaoLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		//카카오id받아오기
		String kakaoId = request.getParameter("kakaoId");
		System.out.println();
		
		mDTO.setSearchCondition("카카오로그인");
		mDTO.setKakaoId(kakaoId);
		mDTO = mDAO.selectOne(mDTO);
		
//		없는 계정이면 회원가입 (joinPage.do)
		if(mDTO == null) {
			request.setAttribute("kakaoId", kakaoId);
			System.out.println("[log]kakaoId : " + kakaoId);
			forward.setPath("joinPage.do");
			forward.setRedirect(false);
		}
//		있는 계정이면 로그인 성공 (mainPage.do)
		else {
			HttpSession session = request.getSession();
			String MID = mDTO.getMID();
			session.setAttribute("member", MID);
			System.out.println("[log]mid : " + MID);
			forward.setPath("mainPage.do");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
