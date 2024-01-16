package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.MemberDTO;
import model.util.JDBCUtil;

public class MemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	/* 기능
	 *  1) 회원목록보기?
	 *  	관리자가 회원목록을 보나?
	 *  2-1) 로그인
	 *  	사용자가 입력한 id와 pw가 같은 행에 일치하는지 확인
	 *  2-2) 추천알고리즘
	 *  	로그인한 회원의 mid와 일치하는 행의 건강상태 속성값을 가져와서 사용
	 *  2-3) 추천알고리즘
	 *  	로그인한 회원의 mid와 일치하는 행의 건강상태 속성값을 가져와서 사용
	 *  2-4) 회원가입 시 아이디 중복확인 기능
	 *  3) 회원가입
	 *  	회원가입
	 *  4) 회원정보 변경
	 *  5) 회원탈퇴
	 */ 
	
	private static final String SELECTALL = "";

	// 로그인(마이페이지는 세션(M_ID) + 입력값(M_PW)
	private static final String SELECTONE_LOGIN = "SELECT "
			+ "M_ID, M_NAME, DOB, GENDER, GRADE, HEALTH "
			+ "FROM MEMBER WHERE M_ID=? AND M_PASSWORD = ?";

	// 아이디 중복검사
	private static final String SELECTONE_ID_CHECK = "SELECT M_ID FROM MEMBER WHERE M_ID=?";

	// 회원가입
	private static final String INSERT = "INSERT INTO "
			+ "MEMBER (M_ID, M_NAME, M_PASSWORD, DOB, GENDER, PHONE_NUMBER, EMAIL, M_POSTCODE, M_ADDRESS, M_DETAILED_ADDRESS, GRADE, HEALTH) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'USER', ?)";

	private static final String UPDATE = "";

	private static final String DELETE = "";
	
	
	

	public MemberDTO selectOne(MemberDTO mDTO) {

		MemberDTO memberDTO = null;

		if (mDTO.getSearchCondition().equals("아이디중복검사")) {

			memberDTO = new MemberDTO();

			conn = JDBCUtil.connect();

			try {
				pstmt = conn.prepareStatement(SELECTONE_ID_CHECK);
				pstmt.setString(1, mDTO.getMid());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					memberDTO.setMid(rs.getString("M_ID"));
				} else {
					memberDTO = null;
				}

				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[로그_아이디중복검사] 반환 NULL_예외처리");
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (memberDTO != null) {
				System.out.println("[로그_아이디중복검사] 성공");
				return memberDTO;
			}
		}

		else if (mDTO.getSearchCondition().equals("로그인")) {
			memberDTO = new MemberDTO();

			conn = JDBCUtil.connect();

			try {
				pstmt = conn.prepareStatement(SELECTONE_LOGIN);
				pstmt.setString(1, mDTO.getMid());
				pstmt.setString(2, mDTO.getmPassword());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					memberDTO.setMid(rs.getString("M_ID"));
					memberDTO.setmName(rs.getString("M_NAME"));
					//memberDTO.setmPassword(rs.getString("M_PASSWORD"));
					memberDTO.setDob(rs.getDate("DOB"));
					memberDTO.setGender(rs.getString("GENDER"));
					//memberDTO.setPhoneNumber(rs.getString("PHONE_NUMBER"));
					//memberDTO.setEmail(rs.getNString("EMAIL"));
					//memberDTO.setAddress(rs.getString("ADDRESS"));
					memberDTO.setGrade(rs.getString("GRADE"));
					memberDTO.setHealth(rs.getString("HEALTH"));
				} else {
					memberDTO = null;
				}

				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[로그_로그인] 반환 NULL_예외처리");
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (memberDTO != null) {
				System.out.println("[로그_로그인] 성공");
				return memberDTO;
			}
		}
		System.out.println("[로그_SelectOne] 반환 NULL");
		return null;
	}

	public boolean insert(MemberDTO mDTO) {
		if (mDTO.getSearchCondition().equals("회원가입")) {
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(INSERT);
				pstmt.setString(1, mDTO.getMid());
				pstmt.setString(2, mDTO.getmName());
				pstmt.setString(3, mDTO.getmPassword());
				pstmt.setDate(4, mDTO.getDob());
				pstmt.setString(5, mDTO.getGender());
				pstmt.setString(6, mDTO.getPhoneNumber());
				pstmt.setString(7, mDTO.getEmail());
				pstmt.setInt(8, mDTO.getmPostCode());
				pstmt.setString(9, mDTO.getmAddress());
				pstmt.setString(10, mDTO.getmDetailedAddress());
				pstmt.setString(11, mDTO.getHealth());
				int rs = pstmt.executeUpdate();
				if (rs <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		return true;
	}

	public boolean update(MemberDTO mDTO) {
		return false;
	}

	public boolean delete(MemberDTO mDTO) {
		return false;
	}
}