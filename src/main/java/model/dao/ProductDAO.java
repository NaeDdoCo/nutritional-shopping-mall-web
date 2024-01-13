package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ProductDTO;
import model.util.JDBCUtil;

public class ProductDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	/* 기능
	 *  1) 제품목록 보기
	 *  2) 제품상세 보기
	 *  3) 제품추가
	 *  4) 제품 정보 변경
	 *  	판매중단은 있고 삭제는 없음
	 *  5) X 제품 삭제
	 */ 
	
	private static final String SELECTALL = "";

	private static final String SELECTONE = "";

	private static final String INSERT = "INSERT INTO PRODUCT (P_ID, P_NAME, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_DATE, SELLING_STATE, IMAGEPATH) VALUES (NVL((SELECT MAX(P_ID) FROM PRODUCT), 0) + 1, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?)";

	private static final String UPDATE = "";

	private static final String DELETE = "";

	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO) {
		return null;
	}

	public ProductDTO selectOne(ProductDTO DTO) {
		return null;
	}

	public boolean insert(ProductDTO pDTO) {

		conn = JDBCUtil.connect();
		if (pDTO.getSearchCondition().equals("제품추가")) {
			try {

				pstmt = conn.prepareStatement(INSERT);

				pstmt.setString(1, pDTO.getpName());
				pstmt.setInt(2, pDTO.getCostPrice());
				pstmt.setInt(3, pDTO.getRegularPrice());
				pstmt.setInt(4, pDTO.getSellingPrice());
				pstmt.setInt(5, pDTO.getpQty());
				pstmt.setString(6, pDTO.getIngredient());
				pstmt.setString(7, pDTO.getCategory());
				pstmt.setString(8, pDTO.getSellingState());
				pstmt.setString(9, pDTO.getImagePath());

				int result = pstmt.executeUpdate();

				if (result <= 0) {
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean update(ProductDTO pDTO) {
		return false;
	}

	public boolean delete(ProductDTO pDTO) {
		return false;
	}
}