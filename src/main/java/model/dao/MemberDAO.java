package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	private static final String SELECTALL="";
	
	// 아이디 중복검사
	private static final String SELECTONE_ID_CHECK = "SELECT M_ID FROM MEMBER WHERE M_ID=?";

	// 회원가입
	private static final String INSERT = "INSERT INTO "
			+ "MEMBER (M_ID, M_NAME, M_PASSWORD, DOB, GENDER, PHONE_NUMBER, EMAIL, ADDRESS, GRADE, HEALTH) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'USER', ?)";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) {
		ArrayList<MemberDTO> datas = new ArrayList<MemberDTO>();
		MemberDTO data = new MemberDTO();
		
		data.setMid("teemo");
		datas.add(data);
		return datas;
	}
	
public MemberDTO selectOne(MemberDTO mDTO) {
		
		MemberDTO memberDTO = null;                
		
		if(mDTO.getSearchCondition().equals("아이디중복검사")) {
			
			memberDTO = new MemberDTO();
			
			conn = JDBCUtil.connect();
			
			try {
				pstmt = conn.prepareStatement(SELECTONE_ID_CHECK);
				pstmt.setString(1, mDTO.getMid());
				
				ResultSet rs = pstmt.executeQuery();				
				
				if(rs.next()) {
					memberDTO.setMid(rs.getString("M_ID"));
				}
				
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}			
		}
		return memberDTO;
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
				pstmt.setString(8, mDTO.getAddress());
				pstmt.setString(9, mDTO.getHealth());
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
		return true;
	}
	
	public boolean delete(MemberDTO mDTO) {
		return true;
	}
}
