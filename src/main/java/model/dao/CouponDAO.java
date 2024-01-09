package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.CouponDTO;

public class CouponDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<CouponDTO> selectAll(CouponDTO cpDTO) {
		return null;
	}
	
	public CouponDTO selectOne(CouponDTO cpDTO) {
		return null;
	}
	
	public boolean insert(CouponDTO cpDTO) {
		return false;
	}
	
	public boolean update(CouponDTO cpDTO) {
		return false;
	}
	
	public boolean delete(CouponDTO cpDTO) {
		return false;
	}
}
