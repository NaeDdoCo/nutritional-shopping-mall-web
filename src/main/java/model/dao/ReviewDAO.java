package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.ProductDTO;
import model.dto.ReviewDTO;

public class ReviewDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<ReviewDTO> selectAll(ReviewDTO rDTO) {
		return null;
	}
	
	public ProductDTO ReviewDTO(ReviewDTO rDTO) {
		return null;
	}
	
	public boolean insert(ReviewDTO rDTO) {
		return false;
	}
	
	public boolean update(ReviewDTO rDTO) {
		return false;
	}
	
	public boolean delete(ReviewDTO rDTO) {
		return false;
	}
}
