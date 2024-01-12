package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.MemberDTO;

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
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
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
		MemberDTO data = new MemberDTO();
		
		data.setMid("teemo");

		return data;
	}
	
	public boolean insert(MemberDTO mDTO) {
		return true;
	}
	
	public boolean update(MemberDTO mDTO) {
		return true;
	}
	
	public boolean delete(MemberDTO mDTO) {
		return true;
	}
}
