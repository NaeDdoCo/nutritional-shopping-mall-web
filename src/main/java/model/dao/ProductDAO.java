package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class ProductDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO) {
		return null;
	}
	
	public ProductDTO selectOne(ProductDTO DTO) {
		return null;
	}
	
	public boolean insert(ProductDTO pDTO) {
		return false;
	}
	
	public boolean update(ProductDTO pDTO) {
		return false;
	}
	
	public boolean delete(ProductDTO pDTO) {
		return false;
	}
}
