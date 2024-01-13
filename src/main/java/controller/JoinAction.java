package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		// mpwCheck 체크하는 로직
		String mpw = request.getParameter("mPassword");
		String pwc = request.getParameter("mpwCheck");
		if (!mpw.equals(pwc)) {
			// TODO: 설계 후 에러처리
		}
		
        // 년, 월, 일을 합쳐서 하나의 날짜 문자열로 만듭니다.
        String dateString = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
        java.sql.Date sqlDate = null;
        try {
            // SimpleDateFormat을 사용하여 문자열을 Date 객체로 변환합니다.
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dateString);

            // java.util.Date를 java.sql.Date로 변환합니다.
            sqlDate = new java.sql.Date(utilDate.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("[JoinAction] " + sqlDate);
        
        // phoneNumber 조합
        String phoneNumber = request.getParameter("phoneNumber0") + "-" + request.getParameter("phoneNumber1") + "-" + request.getParameter("phoneNumber2");
		
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		mDTO.setSearchCondition("회원가입");
		mDTO.setMid(request.getParameter("MID"));
		mDTO.setmName(request.getParameter("mName"));
		mDTO.setmPassword(mpw);
		mDTO.setDob(sqlDate);
		mDTO.setGender(request.getParameter("gender"));
		mDTO.setPhoneNumber(phoneNumber);
		mDTO.setEmail(request.getParameter("email"));
		mDTO.setAddress(request.getParameter("address"));
		mDTO.setHealth(request.getParameter("health"));
		System.out.println("[JoinAction] " + mDTO);
		
		if (mDAO.insert(mDTO)) {
			forward.setPath("mainPage.do");
			forward.setRedirect(false);		
		} else {
			return null;
		}
		
		
		
		return forward;
	}

}
