package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.CouponDTO;
import model.dto.MemberDTO;

public class MemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) {
		return null;
	}
	
	public MemberDTO selectOne(MemberDTO mDTO) {
		return null;
	}
	
	public boolean insert(MemberDTO mDTO) {
		return false;
	}
	
	public boolean update(MemberDTO mDTO) {
		return false;
	}
	
	public boolean delete(MemberDTO mDTO) {
		return false;
	}
}
