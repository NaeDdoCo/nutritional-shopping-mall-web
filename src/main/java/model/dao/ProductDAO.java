package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ProductDTO;
import model.util.JDBCUtil;

public class ProductDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	/*
	 * 기능 1) 제품목록 보기 2) 제품상세 보기 3) 제품추가 4) 제품 정보 변경 판매중단은 있고 삭제는 없음 5) X 제품 삭제
	 */

	// 상품선택(페이지)
	private static final String SELECTALL_PAGE = "SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH "
			+ "FROM ("
			+ "    SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH, ROWNUM AS RN "
			+ "    FROM PRODUCT " + "WHERE SELLING_STATE = '판매중'" + ") " + "WHERE RN BETWEEN ? AND ?";

	// 상품전체
	private static final String SELECTALL_ALL = "SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH "
			+ "FROM PRODUCT";
	
	// 상품전체
	private static final String SELECTALL_FILTER = "SELECT P.P_ID, P.P_NAME, P.P_DETAIL, P.COST_PRICE, P.REGULAR_PRICE, "
			+ " P.SELLING_PRICE, P.P_QTY, P.INGREDIENT, P.CATEGORY, P.REG_TIME, "
			+ " P.SELLING_STATE, P.IMAGEPATH, NVL(SUM(B.B_QTY), 0) AS TOTAL_B_QTY " + " FROM PRODUCT P "
			+ " LEFT JOIN BUYINFO B ON P.P_ID = B.P_ID " + " WHERE P.SELLING_STATE = ? "
			+ " AND (P.P_NAME LIKE ? OR P.P_NAME IS NULL) " + " AND (P.CATEGORY LIKE ? OR P.CATEGORY IS NULL) "
			+ " AND (P.SELLING_PRICE <= ? OR P.SELLING_PRICE IS NULL) "
			+ " GROUP BY P.P_ID, P.P_NAME, P.P_DETAIL, P.COST_PRICE, P.REGULAR_PRICE, "
			+ " P.SELLING_PRICE, P.P_QTY, P.INGREDIENT, P.CATEGORY, P.REG_TIME, " + " P.SELLING_STATE, P.IMAGEPATH "
			+ " ORDER BY TOTAL_B_QTY DESC, REG_TIME DESC";

	// 상품상세출력
	private static final String SELECTONE_DETAIL = "SELECT P.P_ID, P.P_NAME, P.P_DETAIL, P.COST_PRICE, P.REGULAR_PRICE, "
			+ "P.SELLING_PRICE, P.P_QTY, P.INGREDIENT, P.CATEGORY, P.REG_TIME, "
			+ "P.SELLING_STATE, P.IMAGEPATH, P.USAGE, P.EXP, R.R_ID, R.M_ID, R.B_ID, R.SCORE, R.CONTENTS, R.CREATE_TIME "
			+ "FROM PRODUCT P " + "JOIN BUYINFO B ON P.P_ID = B.P_ID " + "JOIN REVIEW R ON B.B_ID = R.B_ID "
			+ "WHERE P.P_ID = ? " + "ORDER BY R.CREATE_TIME DESC";

	// 성공한 Insert
	private static final String INSERT = "INSERT INTO PRODUCT "
			+ "(P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH) "
			+ "	VALUES ( " + "	  NVL((SELECT MAX(P_ID) FROM PRODUCT), 0) + 1, " + "	  ?, " + "	  ?, " + "	  ?, "
			+ "	  ?, " + "	  ?, " + "	  ?, " + "	  ?, " + "	  ?, " + "	  SYSTIMESTAMP, " + "	  ?, " + "	  ? "
			+ "	)";

	private static final String UPDATE = "";

	private static final String DELETE = "";

	private static final String MAX_PRICE = "SELECT MAX(SELLING_PRICE) AS PRICE FROM PRODUCT";

	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO) {

		ArrayList<ProductDTO> productList = new ArrayList<ProductDTO>();

		if (pDTO.getSearchCondition().equals("상품목록페이지")) {

			conn = JDBCUtil.connect();

			try {
				pstmt = conn.prepareStatement(SELECTALL_PAGE);
				pstmt.setInt(1, pDTO.getAncSelectMin());
				pstmt.setInt(2, pDTO.getAncSelectMax());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					if (productList == null) {
						productList = new ArrayList<ProductDTO>(); // productList가 null인 경우에만 객체 생성
					}
					ProductDTO productTempDTO = new ProductDTO();
					productTempDTO.setPID(rs.getInt("P_ID"));
					productTempDTO.setpName(rs.getString("P_NAME"));
					productTempDTO.setCostPrice(rs.getInt("COST_PRICE"));
					productTempDTO.setRegularPrice(rs.getInt("REGULAR_PRICE"));
					productTempDTO.setSellingPrice(rs.getInt("SELLING_PRICE"));
					productTempDTO.setpQty(rs.getInt("P_QTY"));
					productTempDTO.setIngredient(rs.getString("INGREDIENT"));
					productTempDTO.setCategory(rs.getString("CATEGORY"));
					productTempDTO.setRegTime(rs.getTimestamp("REG_TIME"));
					productTempDTO.setSellingState(rs.getString("SELLING_STATE"));
					productTempDTO.setImagePath(rs.getString("IMAGEPATH"));
					productTempDTO.setpDetail(rs.getString("P_DETAIL"));
					productList.add(productTempDTO);
				}

				rs.close();

			} catch (SQLException e) {
				System.out.println("[로그_제품출력페이지] 오류발생");
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (productList != null) {
				System.out.println("[로그_제품출력페이지] 성공");
				return productList;
			}

		}
		else if (pDTO.getSearchCondition().equals("상품출력전체")) {

			conn = JDBCUtil.connect();

			try {
				pstmt = conn.prepareStatement(SELECTALL_ALL);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					if (productList == null) {
						productList = new ArrayList<ProductDTO>(); // productList가 null인 경우에만 객체 생성
					}
					ProductDTO productTempDTO = new ProductDTO();
					productTempDTO.setPID(rs.getInt("P_ID"));
					productTempDTO.setpName(rs.getString("P_NAME"));
					productTempDTO.setCostPrice(rs.getInt("COST_PRICE"));
					productTempDTO.setRegularPrice(rs.getInt("REGULAR_PRICE"));
					productTempDTO.setSellingPrice(rs.getInt("SELLING_PRICE"));
					productTempDTO.setpQty(rs.getInt("P_QTY"));
					productTempDTO.setIngredient(rs.getString("INGREDIENT"));
					productTempDTO.setCategory(rs.getString("CATEGORY"));
					productTempDTO.setRegTime(rs.getTimestamp("REG_TIME"));
					productTempDTO.setSellingState(rs.getString("SELLING_STATE"));
					productTempDTO.setImagePath(rs.getString("IMAGEPATH"));
					productTempDTO.setpDetail(rs.getString("P_DETAIL"));
					productList.add(productTempDTO);
				}

				rs.close();

			} catch (SQLException e) {
				System.out.println("[로그_제품출력페이지] 오류발생");
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (productList != null) {
				System.out.println("[로그_제품출력페이지] 성공");
				return productList;
			}

		} 
		else if (pDTO.getSearchCondition().equals("상품출력필터")) {
			
			conn = JDBCUtil.connect();
			
			System.out.println("[로그_제품출력페이지_필터] 진입");

			if (pDTO.getSellingPrice() == 0) {

				
				try {
					pstmt = conn.prepareStatement(MAX_PRICE);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						pDTO.setSellingPrice(rs.getInt("PRICE"));
						
					}
					rs.close();
				} catch (SQLException e) {
					System.out.println("[로그_맥스값] 오류발생");
					e.printStackTrace();
				} 
				if (pDTO != null) {
					System.out.println("[로그_맥스값] 성공 "+pDTO.getSellingPrice());
				}
			}			

			try {
				System.out.println("[로그_제품출력페이지_필터] try진입");
				pstmt = conn.prepareStatement(SELECTALL_FILTER);
				pstmt.setString(1, pDTO.getSellingState());
				pstmt.setString(2, "%" + pDTO.getpName() + "%");
				pstmt.setString(3, "%" + pDTO.getCategory() + "%");
				pstmt.setInt(4, pDTO.getSellingPrice());


				System.out.println("[로그_제품출력페이지_필터] pstmt.set 성공");
				ResultSet rs = pstmt.executeQuery();
				System.out.println("[로그_제품출력페이지_필터] pstmt.execute 성공");

				while (rs.next()) {
					System.out.println("[로그_제품출력페이지_필터] rs.next" + rs.getInt("P_ID"));
					if (productList == null) {
						productList = new ArrayList<ProductDTO>();
					}
					//System.out.println("[로그] " + rs.getInt("P_ID"));
					ProductDTO productTempDTO = new ProductDTO();
					productTempDTO.setPID(rs.getInt("P_ID"));
					productTempDTO.setpName(rs.getString("P_NAME"));
					productTempDTO.setCostPrice(rs.getInt("COST_PRICE"));
					productTempDTO.setRegularPrice(rs.getInt("REGULAR_PRICE"));
					productTempDTO.setSellingPrice(rs.getInt("SELLING_PRICE"));
					productTempDTO.setpQty(rs.getInt("P_QTY"));
					productTempDTO.setIngredient(rs.getString("INGREDIENT"));
					productTempDTO.setCategory(rs.getString("CATEGORY"));
					productTempDTO.setRegTime(rs.getTimestamp("REG_TIME"));
					productTempDTO.setSellingState(rs.getString("SELLING_STATE"));
					productTempDTO.setImagePath(rs.getString("IMAGEPATH"));
					productTempDTO.setpDetail(rs.getString("P_DETAIL"));
					productTempDTO.setAncTotalQty(rs.getInt("TOTAL_B_QTY"));
					productList.add(productTempDTO);
				}
				rs.close();

			} catch (SQLException e) {
				System.out.println("[로그_제품출력페이지] 오류발생");
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (productList != null) {
				System.out.println("[로그_제품출력페이지] 성공");
				return productList;
			}

		}
		System.out.println("[로그_제품출력페이지] 실패");
		return null;
	}

	public ProductDTO selectOne(ProductDTO pDTO) {
		System.out.println("[로그_SELECTALL 진입]");

		ProductDTO productDTO = null;

		if (pDTO.getSearchCondition().equals("상품상세정보")) {
			System.out.println("[로그_상품상세정보] 진입");

			productDTO = new ProductDTO();

			conn = JDBCUtil.connect();

			try {
				pstmt = conn.prepareStatement(SELECTONE_DETAIL);
				pstmt.setInt(1, pDTO.getPID());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					productDTO.setPID(rs.getInt("P_ID"));
					productDTO.setpName(rs.getString("P_NAME"));
					productDTO.setpDetail(rs.getString("P_DETAIL"));
					productDTO.setCostPrice(rs.getInt("COST_PRICE"));
					productDTO.setRegularPrice(rs.getInt("REGULAR_PRICE"));
					productDTO.setSellingPrice(rs.getInt("SELLING_PRICE"));
					productDTO.setpQty(rs.getInt("P_QTY"));
					productDTO.setIngredient(rs.getString("INGREDIENT"));
					productDTO.setCategory(rs.getString("CATEGORY"));
					productDTO.setRegTime(rs.getTimestamp("REG_TIME"));
					productDTO.setSellingState(rs.getString("SELLING_STATE"));
					productDTO.setImagePath(rs.getString("IMAGEPATH"));
					productDTO.setUsage(rs.getString("USAGE"));
					productDTO.setExp(rs.getString("EXP"));
					// 조인한 속성들
					productDTO.setAncRID(rs.getInt("R_ID"));
					productDTO.setAncMID(rs.getString("M_ID"));
					productDTO.setAncBID(rs.getInt("B_ID"));
					productDTO.setAncScore(rs.getInt("SCORE"));
					productDTO.setAncContents(rs.getString("CONTENTS"));
					productDTO.setAncCreateTime(rs.getTimestamp("CREATE_TIME"));
				} else {
					productDTO = null;
				}

				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[로그_상품상세] 반환 NULL_예외처리");
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (productDTO != null) {
				System.out.println("[로그_상품상세] 성공");
				return productDTO;
			}
		}
		System.out.println("[로그_SELECTALL 실패]");
		return null;
	}

	public boolean insert(ProductDTO pDTO) {
		System.out.println("[로그] 추가 로직");

		conn = JDBCUtil.connect();

		if (pDTO.getSearchCondition().equals("상품추가")) {

			System.out.println("[로그] 제품추가 로직");

			try {
				System.out.println("[로그] 트라이 로직");
				pstmt = conn.prepareStatement(INSERT);
				pstmt.setString(1, pDTO.getpName());
				pstmt.setString(2, pDTO.getpDetail());
				pstmt.setInt(3, pDTO.getCostPrice());
				pstmt.setInt(4, pDTO.getRegularPrice());
				pstmt.setInt(5, pDTO.getSellingPrice());
				pstmt.setInt(6, pDTO.getpQty());
				pstmt.setString(7, pDTO.getIngredient());
				pstmt.setString(8, pDTO.getCategory());
				pstmt.setString(9, pDTO.getSellingState());
				pstmt.setString(10, pDTO.getImagePath());

				int result = pstmt.executeUpdate();

				System.out.println("[로그] 제품추가" + result);

				if (result <= 0) {
					return false;
				}

			} catch (SQLException e) {
				System.out.println("[로그] 예외처리");
				e.printStackTrace();
				return false;
			} finally {
				System.out.println("[로그] 연결종료");
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		System.out.println("[로그] 트루 반환");
		return true;
	}

	public boolean update(ProductDTO pDTO) {
		return false;
	}

	public boolean delete(ProductDTO pDTO) {
		return false;
	}
}