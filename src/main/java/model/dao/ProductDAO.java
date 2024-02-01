package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.dto.ProductDTO;
import model.util.JDBCUtil;

public class ProductDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	// 상품선택(페이지)
	private static final String SELECTALL_PAGE = "SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGE_PATH "
			+ "FROM ("
			+ "    SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGE_PATH, ROWNUM AS RN "
			+ "    FROM PRODUCT " + "WHERE SELLING_STATE = '판매중'" + ") " + "WHERE RN BETWEEN ? AND ?";

//	// 상품필터 PART1
//	private static final String SELECTALL_FILTER_PART1 = "SELECT P.P_ID, P.P_NAME, P.P_DETAIL, P.COST_PRICE, P.REGULAR_PRICE, "
//			+ " P.SELLING_PRICE, P.P_QTY, P.INGREDIENT, P.CATEGORY, P.REG_TIME, "
//			+ " P.SELLING_STATE, P.IMAGE_PATH, NVL(SUM(B.B_QTY), 0) AS TOTAL_B_QTY "
//			+ " FROM (SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGE_PATH, ROWNUM AS RN FROM PRODUCT WHERE SELLING_STATE = '판매중') P "
//			+ " LEFT JOIN BUYINFO B ON P.P_ID = B.P_ID " + " WHERE 1=1 AND RN BETWEEN ? AND ? ";
//
//	// 상품필터 PART2
//	private static final String SELECTALL_FILTER_PART2 = "GROUP BY P.P_ID, P.P_NAME, P.P_DETAIL, P.COST_PRICE, P.REGULAR_PRICE, "
//			+ "P.SELLING_PRICE, P.P_QTY, P.INGREDIENT, P.CATEGORY, P.REG_TIME, P.SELLING_STATE, P.IMAGE_PATH "
//			+ "ORDER BY TOTAL_B_QTY DESC, REG_TIME DESC";
	
	// 상품필터 PART1
	private static final String SELECTALL_FILTER_PART1 = "SELECT P.P_ID, P.P_NAME, P.P_DETAIL, P.COST_PRICE, P.REGULAR_PRICE, "
			+ "P.SELLING_PRICE, P.P_QTY, P.INGREDIENT, P.CATEGORY, P.REG_TIME, "
			+ "P.SELLING_STATE, P.IMAGE_PATH, NVL(B.TOTAL_B_QTY, 0) AS TOTAL_B_QTY "
			+ "FROM ("
			+ "	SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, "
			+ "	SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, "
			+ "	SELLING_STATE, IMAGE_PATH, ROWNUM AS RN "
			+ "		FROM PRODUCT "
			+ "			WHERE SELLING_STATE = '판매중' "
			+ ") P "
			+ "LEFT JOIN ( "
			+ "SELECT P_ID, SUM(B_QTY) AS TOTAL_B_QTY "
			+ "FROM BUYINFO "
			+ "GROUP BY P_ID "
			+ ") "
			+ "B ON P.P_ID = B.P_ID "
			+ "WHERE 1=1 AND RN BETWEEN ? AND ? ";

	// 상품필터 PART2
	private static final String SELECTALL_FILTER_PART2 = "ORDER BY TOTAL_B_QTY DESC, REG_TIME DESC";

	// 상품상세출력
	private static final String SELECTONE_DETAIL = "SELECT " + "P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, "
			+ "SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, " + "SELLING_STATE, IMAGE_PATH, USAGE, EXP "
			+ "FROM PRODUCT " + "WHERE P_ID = ?";

	// 최대가 반환
	private static final String SELECTONE_MAX_PRICE = "SELECT MAX(SELLING_PRICE) AS PRICE FROM PRODUCT WHERE SELLING_STATE = '판매중'";

	// 카테고리 반환
	private static final String SELECTONE_CATEGORY = "SELECT CATEGORY FROM PRODUCT WHERE P_ID = ?";

	// 성공한 Insert
	private static final String INSERT = "INSERT INTO PRODUCT "
			+ "(P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGE_PATH) "
			+ "	VALUES ( " + "	  NVL((SELECT MAX(P_ID) FROM PRODUCT), 0) + 1, " + "	  ?, " + "	  ?, " + "	  ?, "
			+ "	  ?, " + "	  ?, " + "	  ?, " + "	  ?, " + "	  ?, " + "	  SYSTIMESTAMP, " + "	  ?, " + "	  ? "
			+ "	)";

	// 재고변경(상품구매)
	private static final String UPDATE_P_QTY = "UPDATE PRODUCT SET P_QTY = P_QTY - ? WHERE P_ID = ?";

	// 상품 판매상태 변경(판매중 -> 판매중지)
	private static final String UPDATE_SELLING_STATE = "UPDATE PRODUCT SET SELLING_STATE = '판매중지' " + "WHERE P_ID = ?";

	private static final String DELETE = "";

	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO) {

		ArrayList<ProductDTO> productList = null;
		ProductDTO productDTO = null;

		conn = JDBCUtil.connect();

		if (pDTO.getSearchCondition().equals("상품목록페이지")) {

			try {
				pstmt = conn.prepareStatement(SELECTALL_PAGE);
				pstmt.setInt(1, pDTO.getAncSelectMin());
				pstmt.setInt(2, pDTO.getAncSelectMax());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					if (productList == null) {
						productList = new ArrayList<ProductDTO>(); // productList가 null인 경우에만 객체 생성
					}
					productDTO = new ProductDTO();
					
					productDTO.setRegTime(rs.getTimestamp("REG_TIME"));

					SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

					String regTime = timeFormat.format(productDTO.getRegTime());
					
					productDTO.setPID(rs.getInt("P_ID"));
					productDTO.setpName(rs.getString("P_NAME"));
					productDTO.setCostPrice(rs.getInt("COST_PRICE"));
					productDTO.setRegularPrice(rs.getInt("REGULAR_PRICE"));
					productDTO.setSellingPrice(rs.getInt("SELLING_PRICE"));
					productDTO.setpQty(rs.getInt("P_QTY"));
					productDTO.setIngredient(rs.getString("INGREDIENT"));
					productDTO.setCategory(rs.getString("CATEGORY"));
					productDTO.setRegTime(rs.getTimestamp("REG_TIME"));
					productDTO.setAncRegTime(regTime);
					productDTO.setSellingState(rs.getString("SELLING_STATE"));
					productDTO.setImagePath(rs.getString("IMAGE_PATH"));
					productDTO.setpDetail(rs.getString("P_DETAIL"));
					productList.add(productDTO);
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

		} else if (pDTO.getSearchCondition().equals("상품출력필터")) {

			StringBuilder query = new StringBuilder();
			query.append(SELECTALL_FILTER_PART1);
			if (pDTO.getpName() != null) {
				query.append("AND P.P_NAME LIKE ? ");
			}
			if (pDTO.getCategory() != null) {
				query.append("AND P.CATEGORY LIKE ? ");
			}
			if (pDTO.getSellingPrice() != 0) {
				query.append("AND P.SELLING_PRICE <= ? ");
			}
			query.append(SELECTALL_FILTER_PART2);
			String SELECTALL_FILTER = query.toString();

			int bindingIndex = 3;

			System.out.println("[로그_제품출력페이지_필터] 진입");
			System.out.println("[로그_제품출력페이지_필터] 완성된 쿼리 : " + SELECTALL_FILTER);

			try {
				System.out.println("[로그_제품출력페이지_필터] try진입");
				pstmt = conn.prepareStatement(SELECTALL_FILTER);
				System.out.println("[로그]_제품출력페이지_필터 최소값" + pDTO.getAncSelectMin());
				System.out.println("[로그]_제품출력페이지_필터 최대값" + pDTO.getAncSelectMax());
				pstmt.setInt(1, pDTO.getAncSelectMin());
				pstmt.setInt(2, pDTO.getAncSelectMax());
				if (pDTO.getpName() != null) {
					pstmt.setString(bindingIndex++, "%" + pDTO.getpName() + "%");
				}
				if (pDTO.getCategory() != null) {
					pstmt.setString(bindingIndex++, "%" + pDTO.getCategory() + "%");
				}
				if (pDTO.getSellingPrice() != 0) {
					pstmt.setInt(bindingIndex++, pDTO.getSellingPrice());
				}

				System.out.println("[로그_제품출력페이지_필터] pstmt.set 성공");
				ResultSet rs = pstmt.executeQuery();
				System.out.println("[로그_제품출력페이지_필터] pstmt.execute 성공");

				while (rs.next()) {
					System.out.println("[로그_제품출력페이지_필터] rs.next" + rs.getInt("P_ID"));
					if (productList == null) {
						productList = new ArrayList<ProductDTO>();
					}

					productDTO = new ProductDTO();
					
					productDTO.setRegTime(rs.getTimestamp("REG_TIME"));

					SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

					String regTime = timeFormat.format(productDTO.getRegTime());
					
					productDTO.setPID(rs.getInt("P_ID"));
					productDTO.setpName(rs.getString("P_NAME"));
					productDTO.setCostPrice(rs.getInt("COST_PRICE"));
					productDTO.setRegularPrice(rs.getInt("REGULAR_PRICE"));
					productDTO.setSellingPrice(rs.getInt("SELLING_PRICE"));
					productDTO.setpQty(rs.getInt("P_QTY"));
					productDTO.setIngredient(rs.getString("INGREDIENT"));
					productDTO.setCategory(rs.getString("CATEGORY"));
					productDTO.setRegTime(rs.getTimestamp("REG_TIME"));
					productDTO.setAncRegTime(regTime);
					productDTO.setSellingState(rs.getString("SELLING_STATE"));
					productDTO.setImagePath(rs.getString("IMAGE_PATH"));
					productDTO.setpDetail(rs.getString("P_DETAIL"));
					productDTO.setAncTotalQty(rs.getInt("TOTAL_B_QTY"));
					productList.add(productDTO);
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
		System.out.println("[로그selectOne 진입]");

		conn = JDBCUtil.connect();

		ProductDTO productDTO = null;

		if (pDTO.getSearchCondition().equals("상품상세정보")) {
			System.out.println("[로그_상품상세정보] 진입");

			productDTO = new ProductDTO();

			try {
				pstmt = conn.prepareStatement(SELECTONE_DETAIL);
				pstmt.setInt(1, pDTO.getPID());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					
					productDTO.setRegTime(rs.getTimestamp("REG_TIME"));

					SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

					String regTime = timeFormat.format(productDTO.getRegTime());
					
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
					productDTO.setAncRegTime(regTime);
					productDTO.setSellingState(rs.getString("SELLING_STATE"));
					productDTO.setImagePath(rs.getString("IMAGE_PATH"));
					productDTO.setUsage(rs.getString("USAGE"));
					productDTO.setExp(rs.getString("EXP"));
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
		} else if (pDTO.getSearchCondition().equals("최대값")) {

			productDTO = new ProductDTO();

			try {
				pstmt = conn.prepareStatement(SELECTONE_MAX_PRICE);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					productDTO.setSellingPrice(rs.getInt("PRICE"));
				}

				rs.close();

			} catch (SQLException e) {
				System.out.println("[로그_맥스값] 오류발생");
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (pDTO != null) {
				System.out.println("[로그_맥스값] 성공 " + pDTO.getSellingPrice());
				return productDTO;
			}

		} else if (pDTO.getSearchCondition().equals("카테고리")) {

			productDTO = new ProductDTO();

			try {
				pstmt = conn.prepareStatement(SELECTONE_CATEGORY);
				pstmt.setInt(1, pDTO.getPID());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					productDTO.setCategory(rs.getString("CATEGORY"));
				} else {
					productDTO = null;
				}

				rs.close();

			} catch (SQLException e) {
				System.out.println("[로그_카테고리] 오류발생");
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (pDTO != null) {
				System.out.println("[로그_카테고리] 성공 " + productDTO.getCategory());
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

	// 재고 및 판매상태 변경
	public boolean update(ProductDTO pDTO) {

		conn = JDBCUtil.connect();

		int result;

		if (pDTO.getSearchCondition().equals("판매완료")) {

			try {
				pstmt = conn.prepareStatement(UPDATE_P_QTY);
				pstmt.setInt(1, pDTO.getpQty());
				pstmt.setInt(2, pDTO.getPID());

				result = pstmt.executeUpdate();

			} catch (SQLException e) {
				System.out.println("[로그]_판매완료 예외처리");
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}

			if (result > 0) {
				System.out.println("[로그]_판매완료 성공");
				return true;
			}

		} else if (pDTO.getSearchCondition().equals("판매중지")) {

			try {
				pstmt = conn.prepareStatement(UPDATE_SELLING_STATE);
				pstmt.setInt(1, pDTO.getPID());

				result = pstmt.executeUpdate();

			} catch (SQLException e) {
				System.out.println("[로그]_판매중지 예외처리");
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (result > 0) {
				System.out.println("[로그]_판매중지 성공");
				return true;
			}
		}
		System.out.println("[로그]_상품UPDATE 실패");
		return false;
	}

	public boolean delete(ProductDTO pDTO) {
		return false;
	}
}