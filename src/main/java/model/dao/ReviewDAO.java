package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.ProductDTO;
import model.dto.ReviewDTO;

public class ReviewDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	/* 기능
	 *  1) 해당제품 리뷰 출력, 마이페이지 내가 쓴 리뷰 출력
	 *  2) ?리뷰 선택?
	 *  3) 리뷰 작성
	 *  4) ?리뷰 변경?
	 *  5) 리뷰 삭제
	 */ 
	
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
