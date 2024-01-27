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

	// 장바구니 목록 출력
	// P_ID
	private static final String SELECTALL_CART = "SELECT C.C_ID, C.P_ID, C.C_QTY, P.P_NAME, P.SELLING_PRICE, P.IMAGE_PATH "
			+ "FROM CART C "
			+ "JOIN PRODUCT P ON C.P_ID = P.P_ID "
			+ "WHERE C.M_ID = ?";

	// 장바구니 상품확인(존재여부)
	private static final String SELECTONE = "SELECT P_ID, M_ID, C_ID, C_QTY FROM CART WHERE M_ID = ? AND P_ID = ?";

	// 장바구니 추가
	private static final String INSERT_CART = "INSERT INTO CART (C_ID, M_ID, P_ID, C_QTY) VALUES (NVL((SELECT MAX(C_ID) FROM CART), 0)+1, ?, ?, ?)";

	// 장바구니 갱신(기존에 있던 상품 추가)
	private static final String UPDATE_QTY_ADD = "UPDATE CART SET C_QTY = C_QTY + ? WHERE C_ID = ?";

	// 장바구니 상품 1개삭제
	private static final String DELETE_CID = "DELETE FROM CART WHERE C_ID = ?";

	// 장바구니 비우기
	private static final String DELETE_CART = "DELETE FROM CART WHERE M_ID = ?";

	public ArrayList<CartDTO> selectAll(CartDTO cDTO) {

		ArrayList<CartDTO> cartDTO = new ArrayList<CartDTO>();

		conn = JDBCUtil.connect();

		if (cDTO.getSearchCondition().equals("장바구니목록출력")) {

			try {
				pstmt = conn.prepareStatement(SELECTALL_CART);
				System.out.println(SELECTALL_CART);
				pstmt.setString(1, cDTO.getMID());
				
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					CartDTO rsCartDTO = new CartDTO();
					rsCartDTO.setCID(rs.getInt("C_ID"));
					rsCartDTO.setPID(rs.getInt("P_ID"));
					rsCartDTO.setcQty(rs.getInt("C_QTY"));
					rsCartDTO.setpName(rs.getString("P_NAME"));
					rsCartDTO.setSellingPrice(rs.getInt("SELLING_PRICE"));
					rsCartDTO.setImagePath(rs.getString("IMAGE_PATH"));
					cartDTO.add(rsCartDTO);
				}
				
				rs.close();

			} catch (SQLException e) {
				System.out.println("[로그_장바구니목록] 예외처리");
				e.printStackTrace();
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			
			if (cartDTO.size() > 0) {
				System.out.println("[로그_장바구니목록] 성공");
				return cartDTO;
			}
			
		}
		System.out.println("[로그_장바구니ALL] 실패");
		return null;
	}

	public CartDTO selectOne(CartDTO cDTO) {

		CartDTO cartDTO = null;

		conn = JDBCUtil.connect();

		if (cDTO.getSearchCondition().equals("상품확인")) {
			System.out.println("[로그]_상품확인 ID : "+cDTO.getMID());
			System.out.println("[로그]_상품확인 P_ID : "+cDTO.getPID());

			try {
				pstmt = conn.prepareStatement(SELECTONE);
				pstmt.setString(1, cDTO.getMID());
				pstmt.setInt(2, cDTO.getPID());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					cartDTO = new CartDTO();
					cartDTO.setMID(rs.getString("M_ID"));
					cartDTO.setPID(rs.getInt("P_ID"));
					cartDTO.setCID(rs.getInt("C_ID"));
					System.out.println("[로그]_상품확인 ID : "+cartDTO.getMID());
					System.out.println("[로그]_상품확인 P_ID : "+cartDTO.getPID());
					System.out.println("[로그]_상품확인 C_ID : "+cartDTO.getCID());
				} else {
					System.out.println("[로그]_상품확인 else로 null저장");
					cartDTO = null;
				}
				
				rs.close();

			} catch (SQLException e) {
				System.out.println("[로그]_상품확인 예외처리 null반환");
				e.printStackTrace();
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (cartDTO != null) {
				return cartDTO;
			}
		}
		System.out.println("[로그]_장바구니Select 실패 null반환");
		return null;
	}

	public boolean insert(CartDTO cDTO) {

		conn = JDBCUtil.connect();

		if (cDTO.getSearchCondition().equals("장바구니추가")) {
			try {
				pstmt = conn.prepareStatement(INSERT_CART);
				pstmt.setString(1, cDTO.getMID());
				pstmt.setInt(2, cDTO.getPID());
				pstmt.setInt(3, cDTO.getcQty());

				int rs = pstmt.executeUpdate();

				if (rs > 0) {
					System.out.println("[로그_장바구니추가] 성공");
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		System.out.println("[로그_장바구니INSERT] 실패");
		return false;
	}

	public boolean update(CartDTO cDTO) {

		conn = JDBCUtil.connect();

		if (cDTO.getSearchCondition().equals("동일상품추가")) {

			try {
				pstmt = conn.prepareStatement(UPDATE_QTY_ADD);
				pstmt.setInt(1, cDTO.getcQty());
				pstmt.setInt(2, cDTO.getCID());

				int result = pstmt.executeUpdate();

				if (result > 0) {
					System.out.println("[로그_수량갱신] 성공");
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
//		else if (cDTO.getSearchCondition().equals("수량갱신")) {
//
//			try {
//				pstmt = conn.prepareStatement(UPDATE_QTY);
//				pstmt.setInt(1, cDTO.getcQty());
//				pstmt.setInt(2, cDTO.getCid());
//
//				int result = pstmt.executeUpdate();
//
//				if (result > 0) {
//					System.out.println("[로그_수량갱신] 성공");
//					return true;
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return false;
//			} finally {
//				JDBCUtil.disconnect(pstmt, conn);
//			}
//		} else if (cDTO.getSearchCondition().equals("수량증가")) {
//
//			try {
//				pstmt = conn.prepareStatement(UPDATE_PLUS);
//				pstmt.setInt(1, cDTO.getCid());
//
//				int result = pstmt.executeUpdate();
//
//				if (result > 0) {
//					System.out.println("[로그_수량갱신] 성공");
//					return true;
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return false;
//			} finally {
//				JDBCUtil.disconnect(pstmt, conn);
//			}
//		} else if (cDTO.getSearchCondition().equals("수량감소")) {
//
//			try {
//				pstmt = conn.prepareStatement(UPDATE_MINUS);
//				pstmt.setInt(1, cDTO.getCid());
//
//				int result = pstmt.executeUpdate();
//
//				if (result > 0) {
//					System.out.println("[로그_수량갱신] 성공");
//					return true;
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return false;
//			} finally {
//				JDBCUtil.disconnect(pstmt, conn);
//			}
//		}
		System.out.println("[로그_수량UPDATE] 실패");
		return false;
	}

	public boolean delete(CartDTO cDTO) {

		conn = JDBCUtil.connect();

		if (cDTO.getSearchCondition().equals("장바구니삭제")) {

			try {
				pstmt = conn.prepareStatement(DELETE_CID);
				pstmt.setInt(1, cDTO.getCID());

				int result = pstmt.executeUpdate();

				if (result > 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}

		} else if (cDTO.getSearchCondition().equals("장바구니비우기")) {

			try {
				pstmt = conn.prepareStatement(DELETE_CART);
				pstmt.setString(1, cDTO.getMID());

				int result = pstmt.executeUpdate();
				System.out.println("[로그_장바구니비우기] " + result + "개 삭제");
				if (result > 0) {
					System.out.println("[로그_장바구니비우기] 성공");
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}

		}
		System.out.println("[로그_장바구니DELETE] 실패");
		return false;
	}
}
