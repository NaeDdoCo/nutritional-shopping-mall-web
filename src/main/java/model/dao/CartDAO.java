package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.CartDTO;
import model.util.JDBCUtil;

public class CartDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	/*
	 * 기능 1) 장바구니 목록출력 2) X장바구니 속 물품 상세보기 제품 상세보기 페이지 활용 3) 장바구니에 물품 추가하가 장바구니에 추가
	 * 4) 장바구니 물품 수량변경 해당 제품이 보유중인 PID값의 재고 변경 5) 구매한 물품 제거 장바구니에서 체크한 상품만 구매하기 때문에
	 * 구매한 상품은 장바구니에서 제거
	 */

	/*
	 * 연산을 DB에서 하는 코드 private static final String
	 * SELECTALL="SELECT C.C_ID,C.M_ID, C.P_ID, C.C_QTY, P.P_NAME, P.SELLING_PRICE, P.IMAGEPATH, (C.C_QTY * P.SELLING_PRICE) AS TOTAL_PRICE "
	 * + "FROM CART C " + "JOIN PRODUCT P ON C.P_ID = P.P_ID;";
	 */

	private static final String SELECTALL = "SELECT C.C_ID, C.M_ID, C.P_ID, C.C_QTY, P.P_NAME, P.SELLING_PRICE, P.IMAGEPATH "
			+ "FROM CART C " + "JOIN PRODUCT P ON C.P_ID = P.P_ID";

	private static final String SELECTONE = "";

	// 장바구니 추가
	private static final String INSERT_CART = "INSERT INTO CART (C_ID, M_ID, P_ID, C_QTY) VALUES (NVL((SELECT MAX(C_ID) FROM CART), 0)+1, ?, ?, ?)";

	private static final String UPDATE = "";

	private static final String DELETE = "";

	public ArrayList<CartDTO> selectAll(CartDTO cDTO) {

		ArrayList<CartDTO> cartDTO = new ArrayList<CartDTO>();

		if (cDTO.getSearchCondition().equals("장바구니목록출력")) {

			conn = JDBCUtil.connect();

			try {
				pstmt = conn.prepareStatement(SELECTALL);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					CartDTO rsCartDTO = new CartDTO();
					rsCartDTO.setCid(rs.getInt("C_ID"));
					rsCartDTO.setMid(rs.getString("M_ID"));
					rsCartDTO.setPid(rs.getInt("P_ID"));
					rsCartDTO.setcQty(rs.getInt("C_QTY"));
					rsCartDTO.setpName(rs.getString("P_NAME"));
					rsCartDTO.setSellingPrice(rs.getInt("SELLING_PRICE"));
					// 이미지 경로 추가
					rsCartDTO.setImagePath(rs.getString("IMAGEPATH"));

					cartDTO.add(rsCartDTO);
				}

				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}

		return cartDTO;
	}

	private CartDTO selectOne(CartDTO cDTO) {
		return null;
	}

	public boolean insert(CartDTO cDTO) {
		if (cDTO.getSearchCondition().equals("장바구니추가")) {
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(INSERT_CART);
				pstmt.setString(1, cDTO.getMid());
				pstmt.setInt(2, cDTO.getPid());
				pstmt.setInt(3, cDTO.getcQty());

				int rs = pstmt.executeUpdate();
				if (rs <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		return true;
	}

	public boolean update(CartDTO cDTO) {
		return false;
	}

	public boolean delete(CartDTO cDTO) {
		return false;
	}
}
