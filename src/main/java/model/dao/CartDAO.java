package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.CartDTO;

public class CartDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<CartDTO> selectAll(CartDTO cDTO) {
		return null;
	}
	
	public CartDTO selectOne(CartDTO cDTO) {
		return null;
	}
	
	public boolean insert(CartDTO cDTO) {
		return false;
	}
	
	public boolean update(CartDTO cDTO) {
		return false;
	}
	
	public boolean delete(CartDTO cDTO) {
		return false;
	}
}
