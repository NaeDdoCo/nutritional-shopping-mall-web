package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.BuyInfoDTO;

public class BuyInfoDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	/* 기능
	 *  1) 
	 * 
	 */
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<BuyInfoDTO> selectAll(BuyInfoDTO bDTO) {
		return null;
	}
	
	public BuyInfoDTO selectOne(BuyInfoDTO bDTO) {
		return null;
	}
	
	public boolean insert(BuyInfoDTO bDTO) {
		return false;
	}
	
	public boolean update(BuyInfoDTO bDTO) {
		return false;
	}
	
	public boolean delete(BuyInfoDTO bDTO) {
		return false;
	}

}
