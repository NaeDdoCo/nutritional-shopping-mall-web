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
	
	// 조인, 서브쿼리를 사용할 수 있는 기능을 생각해보자
	
	/* 기능
	 *  1) 구매내역 불러오기
	 *  	로그인한 ID와 일치하는 구매내역 불러오기
	 *  	정렬을 구매일 순
	 *  2) 구매내역 상세보기
	 *  	해당 제품과 주문번호(orderNum)가 같은 제품을 출력
	 *  3) 구매내역 추가
	 *  4) 구매상태 변경(취소, 환불)
	 *  5) X
	 */ 
	
	private static final String SELECTALL="";
	
	// 판매량 반환
	private static final String SELECTONE_QTY="SELECT P_ID, SUM(B_QTY) AS TOTAL_QTY "
			+ "FROM BUYINFO "
			+ "WHERE P_ID = ? "
			+ "GROUP BY P_ID";

	// 구매내역 추가
	private static final String INSERT="INSERT INTO BUYINFO "
			+ "(B_ID, M_ID, P_ID, CP_ID, ORDER_NUM, DELI_STATE, B_QTY, PAYMENT_PRICE, BUY_TIME, B_POSTCODE, B_ADDRESS, B_DETAILED_ADDRESS) "
			+ "VALUES (NVL((SELECT MAX(B_ID) FROM BUYINFO), 0) + 1, "
			+ "?, "
			+ "?, "
			+ "?, "
			+ "?, "
			+ "?, "
			+ "?, "
			+ "?, "
			+ "SYSTIMESTAMP, "
			+ "?, "
			+ "?, "
			+ "?)";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<BuyInfoDTO> selectAll(BuyInfoDTO bDTO) {
		return null;
	}
	
	public BuyInfoDTO selectOne(BuyInfoDTO bDTO) {
		
		BuyInfoDTO buyInfoDTO = null;
		
		if (bDTO.getSearchCondition().equals("판매량")) {

			buyInfoDTO = new BuyInfoDTO();

			conn = JDBCUtil.connect();

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

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[로그_판매량] 반환 NULL_예외처리");
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			if (buyInfoDTO != null) {
				System.out.println("[로그_판매량] 성공");
				return buyInfoDTO;
			}
		}
		System.out.println("[로그_구매내역 SelectOne] 실패");
		return null;
	}
	
	public boolean insert(BuyInfoDTO bDTO) {
		System.out.println("[로그_구매내역추가] insert 진입");
		if (bDTO.getSearchCondition().equals("구매내역추가")) {
			conn = JDBCUtil.connect();
			try {
				System.out.println("[로그_구매내역추가] INSERT SQL구문 준비");
				pstmt = conn.prepareStatement(INSERT);
				System.out.println("[로그_구매내역추가] SQL구문 바인딩변수 값 지정");
	            pstmt.setString(1, "teemo");	//mid
	            pstmt.setInt(2, 1);				//pid
	            pstmt.setString(3, "CP001");	//cpid
	            pstmt.setInt(4, 12345);			//ordernum
	            pstmt.setString(5, "결재 완료");	//DELI_STATE
	            pstmt.setInt(6, 3);				//QTY
	            pstmt.setInt(7, 50000);			//price
	            pstmt.setInt(8, 12345);			
	            pstmt.setString(9, "서울시 강남구");
	            pstmt.setString(10, "123번지 456호");
	            
				int rs = pstmt.executeUpdate();
				if (rs <= 0) {
					System.out.println("[로그_구매내역추가] rs <=0 false 반환");
					return false;
				}
			} catch (SQLException e) {
				System.out.println("[로그_구매내역추가] 예외처리 false 반환");
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		System.out.println("[로그_구매내역추가] 구매내역 추가 성공 true 반환");
		return true;
	}
	
	public boolean update(BuyInfoDTO bDTO) {
		return false;
	}
	
	public boolean delete(BuyInfoDTO bDTO) {
		return false;
	}

}
