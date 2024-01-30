package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.BuyInfoDTO;
import model.util.JDBCUtil;

public class BuyInfoDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	// 내 구매내역(해당 회원의 구매내역을 불러온다)
	// 선택할 컬럼 B_ID, M_ID, P_ID, CP_ID, 주문번호, 배송상태, 구매수량, 지불금액, 구매시간, 주소, 상품명, 상품주소
	// 테이블 BUYINFO
	// 조인 상품 테이블
	// 조건 M_ID가 전달받은 값과 같은 행
	private static final String SELECTALL_LIST = "SELECT "
			+ "B.B_ID, B.M_ID, B.P_ID, B.CP_ID, B.ORDER_NUM, B.DELI_STATE, B.B_QTY, B.PAYMENT_PRICE, B.BUY_TIME, B.B_POSTCODE, B.B_ADDRESS, B.B_DETAILED_ADDRESS, "
			+ "P.P_NAME, P.IMAGE_PATH "
			+ "FROM BUYINFO B "
			+ "JOIN PRODUCT P ON B.P_ID = P.P_ID "
			+ "WHERE B.M_ID = ? AND B.DELI_STATE NOT IN ('취소', '환불') "
			+ "ORDER BY B.ORDER_NUM DESC";
	
	// 주문번호의 최대값 +1
	private static final String SELECTONE_MAX_ORDER_NUM = "SELECT NVL(MAX(ORDER_NUM),0)+1 AS MAX_ORDER_NUM FROM BUYINFO";

	// 판매량 반환
	// 전달받은 값과 P_ID가 일치하는 행의 B_Qty를 더한다
	private static final String SELECTONE_QTY = "SELECT SUM(B_Qty) AS TOTAL_QTY FROM BUYINFO WHERE P_ID = ?";

	// 구매내역 추가
	// B_ID, M_ID, P_ID, CP_ID, 주문번호, 배송상태, 구매수량, 지불금액, 구매일, 배송지
	// 주문번호는 구매내역 테이블에서 ORDER_NUM컬럼의 가장 큰값에서 1을 더해서 반환한다
	// DELI_STATE는 테이블에서 DEFAULT로 결재완료를 입력함_01_30 제거
	private static final String INSERT = "INSERT INTO BUYINFO "
			+ "(B_ID, M_ID, P_ID, CP_ID, ORDER_NUM, B_QTY, PAYMENT_PRICE, BUY_TIME, B_POSTCODE, B_ADDRESS, B_DETAILED_ADDRESS) "
			+ "VALUES (NVL((SELECT MAX(B_ID) FROM BUYINFO), 0) + 1, " + "?, " + "?, " + "?, " + "?, " + "?, "
			+ "?, " + "SYSTIMESTAMP, " + "?, " + "?, " + "?)";

	// 구매상태변경(환불, 취소)
	// 컨트롤러에서 환불, 취소를 줄지 환불쿼리, 취소쿼리를 따로 작성할지
	private static final String UPDATE_STATE = "UPDATE BUYINFO SET DELI_STATE = ? WHERE B_ID = ?";

	// 
	private static final String DELETE = "";

	public ArrayList<BuyInfoDTO> selectAll(BuyInfoDTO bDTO) {

		ArrayList<BuyInfoDTO> buyList = null;
		BuyInfoDTO buyInfoDTO = null;

		conn = JDBCUtil.connect();

		if (bDTO.getSearchCondition().equals("구매내역")) {

			buyList = new ArrayList<BuyInfoDTO>();

			try {
				pstmt = conn.prepareStatement(SELECTALL_LIST);
				pstmt.setString(1, bDTO.getMID());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					buyInfoDTO = new BuyInfoDTO();
					buyInfoDTO.setBID(rs.getInt("B_ID"));
					buyInfoDTO.setMID(rs.getString("M_ID"));
					buyInfoDTO.setPID(rs.getInt("P_ID"));
					buyInfoDTO.setCPID(rs.getString("CP_ID"));
					buyInfoDTO.setOrderNum(rs.getInt("ORDER_NUM"));
					buyInfoDTO.setDeliState(rs.getString("DELI_STATE"));
					buyInfoDTO.setbQty(rs.getInt("B_QTY"));
					buyInfoDTO.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
					buyInfoDTO.setBuyTime(rs.getTimestamp("BUY_TIME"));
					buyInfoDTO.setbPostCode(rs.getInt("B_POSTCODE"));
					buyInfoDTO.setbAddress(rs.getString("B_ADDRESS"));
					buyInfoDTO.setbDetailedAddress(rs.getString("B_DETAILED_ADDRESS"));
					buyInfoDTO.setAncPName(rs.getString("P_NAME"));
					buyInfoDTO.setAncImagePath(rs.getString("IMAGE_PATH"));
					buyList.add(buyInfoDTO);
				}

				rs.close();

				if (buyList.size() > 0) {
					return buyList;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[로그_구매내역] 반환 NULL_예외처리");
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}

		}
		return null;
	}

	public BuyInfoDTO selectOne(BuyInfoDTO bDTO) {

		BuyInfoDTO buyInfoDTO = null;

		conn = JDBCUtil.connect();

		if (bDTO.getSearchCondition().equals("판매량")) {

			buyInfoDTO = new BuyInfoDTO();

			try {
				pstmt = conn.prepareStatement(SELECTONE_QTY);
				pstmt.setInt(1, bDTO.getPID());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					buyInfoDTO.setbQty(rs.getInt("TOTAL_QTY"));
				} else {
					buyInfoDTO = null;
				}

				rs.close();

				if (buyInfoDTO != null) {
					System.out.println("[로그_판매량] 성공");
					return buyInfoDTO;
				}

			} catch (SQLException e) {
				System.out.println("[로그_판매량] 반환 NULL_예외처리");
				e.printStackTrace();
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if(bDTO.getSearchCondition().equals("주문번호")) {
			
			buyInfoDTO = new BuyInfoDTO();
			
			try {
				pstmt = conn.prepareStatement(SELECTONE_MAX_ORDER_NUM);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					buyInfoDTO.setOrderNum(rs.getInt("MAX_ORDER_NUM"));
				} 
				
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("[로그_주문번호] 반환 NULL_예외처리");
				e.printStackTrace();
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			
			if(buyInfoDTO.getOrderNum() > 0) {
				System.out.println("[로그_주문번호] 성공");
				return buyInfoDTO;
			}
			
		}
		System.out.println("[로그_구매내역 SelectOne] 실패");
		return null;
	}

	public boolean insert(BuyInfoDTO bDTO) {

		conn = JDBCUtil.connect();

		if (bDTO.getSearchCondition().equals("구매내역추가")) {

			try {
				System.out.println("[로그_구매내역추가] INSERT SQL구문 준비");
				pstmt = conn.prepareStatement(INSERT);
				System.out.println("[로그_구매내역추가] SQL구문 바인딩변수 값 지정");
				pstmt.setString(1, bDTO.getMID()); // mid
				pstmt.setInt(2, bDTO.getPID()); // pid
				pstmt.setString(3, bDTO.getCPID()); // cpid
				pstmt.setInt(4, bDTO.getOrderNum()); // ordernum
				pstmt.setInt(5, bDTO.getbQty()); // QTY
				pstmt.setInt(6, bDTO.getPaymentPrice()); // price
				pstmt.setInt(7, bDTO.getbPostCode());
				pstmt.setString(8, bDTO.getbAddress());
				pstmt.setString(9, bDTO.getbDetailedAddress());

				int rs = pstmt.executeUpdate();

				if (rs > 0) {
					System.out.println("[로그_구매내역추가] rs > 0 true 반환");
					return true;
				}

			} catch (SQLException e) {
				System.out.println("[로그_구매내역추가] 예외처리 false 반환");
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		System.out.println("[로그_구매내역추가] 구매내역 추가 실패 false 반환");
		return false;
	}

	public boolean update(BuyInfoDTO bDTO) {

		conn = JDBCUtil.connect();

		if (bDTO.getSearchCondition().equals("구매상태변경")) {

			try {
				pstmt = conn.prepareStatement(UPDATE_STATE);
				pstmt.setString(1, bDTO.getDeliState());
				pstmt.setInt(2, bDTO.getBID());
				
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("[로그_구매상태변경] rs > 0 true 반환");
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println("[로그_구매상태변경] 예외처리 false 반환");
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		System.out.println("[로그_구매상태변경] 구매상태변경 실패 false 반환");
		return false;
	}

	public boolean delete(BuyInfoDTO bDTO) {
		return false;
	}

}
