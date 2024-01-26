package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import model.dto.CouponDTO;
import model.util.JDBCUtil;

public class CouponDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	/*
	 * 기능 1) 보유한 쿠폰 보기 마이페이지에서 보유한 쿠폰 출력(사용가능, 사용불가) 사용기간이 만료되면 그냥 삭제 2) X 쿠폰의 상세정보를
	 * 볼 일이 없다... 3) 쿠폰추가 관리자가 추가시켜주는 형식 일단 샘플로는 관리자가 입력한 MID의 회원에게 지급 4) X쿠폰상태
	 * 변경(취소, 환불) 쿠폰의 할인률, 이름등 관리자가 잘못 줘서 수정할 때에나 사용할 듯 5) 쿠폰삭제 사용기간이 지나면 삭제
	 */

	/*--쿠폰목록출력(마이페이지, 사용, 미사용 정렬 후 만료일 순 정렬)--
	-- 내림차순으로 해야 사용이위로 옴
	
	
	--쿠폰목록출력(쿠폰적용, 미사용쿠폰을 만료일 순으로 정렬하여 사용)--
	;*/

	private static final String SELECTALL_MYCP = "SELECT CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY, USED "
			+ "	FROM COUPON "
			+ "WHERE M_ID = ? "
			+ "ORDER BY USED DESC, PERIOD ASC";

	private static final String SELECTALL_USECP = "SELECT CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY, USED "
			+ "	FROM COUPON "
			+ "WHERE M_ID = ? AND USED = '미사용' "
			+ "ORDER BY PERIOD ASC";

	private static final String SELECTONE = "";

	// --현재시간 +30일----SYSTIMESTAMP + INTERVAL '30' DAY--
	private static final String INSERT = "INSERT INTO COUPON (CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY) "
			+ "	VALUES ("
			+ "?, "
			+ "?, "
			+ "?, "
			+ "SYSTIMESTAMP, "
			+ "?, "
			+ "?)";

	private static final String UPDATE = "UPDATE COUPON SET USED = '사용' WHERE CP_ID = ?";

	private static final String DELETE = "DELETE FROM COUPON WHERE USED = '미사용' AND PERIOD < SYSTIMESTAMP";

	public ArrayList<CouponDTO> selectAll(CouponDTO cpDTO) {

		ArrayList<CouponDTO> couponList = null;
		CouponDTO couponDTO = null;

		conn = JDBCUtil.connect();

		if (cpDTO.getSearchCondition().equals("쿠폰목록")) {
			System.out.println("[로그_쿠폰목록] 진입");
			couponList = new ArrayList<>();

			try {
				pstmt = conn.prepareStatement(SELECTALL_MYCP);
				pstmt.setString(1, cpDTO.getMID());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					couponDTO = new CouponDTO();
					couponDTO.setCPID(rs.getString("CP_ID"));
					couponDTO.setMID(rs.getString("M_ID"));
					couponDTO.setCpName(rs.getString("CP_NAME"));
					couponDTO.setPeriod(rs.getTimestamp("PERIOD"));
					couponDTO.setDiscount(rs.getInt("DISCOUNT"));
					couponDTO.setCategory(rs.getString("CATEGORY"));
					couponDTO.setUsed(rs.getString("USED"));
					couponList.add(couponDTO);
				}
				System.out.println("[로그_쿠폰목록] "+couponList.size());
				if (couponList.size() > 0) {
					System.out.println("[로그_쿠폰목록] 성공");
					return couponList;
				}

					rs.close();

			} catch (SQLException e) {
				System.out.println("[로그_쿠폰목록] 예외처리");
				e.printStackTrace();
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		else if (cpDTO.getSearchCondition().equals("사용가능쿠폰")) {
			System.out.println("[로그_사용가능쿠폰] 진입");
			couponList = new ArrayList<>();

			try {
				pstmt = conn.prepareStatement(SELECTALL_USECP);
				pstmt.setString(1, cpDTO.getMID());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					couponDTO = new CouponDTO();
					couponDTO.setCPID(rs.getString("CP_ID"));
					couponDTO.setMID(rs.getString("M_ID"));
					couponDTO.setCpName(rs.getString("CP_NAME"));
					couponDTO.setPeriod(rs.getTimestamp("PERIOD"));
					couponDTO.setDiscount(rs.getInt("DISCOUNT"));
					couponDTO.setCategory(rs.getString("CATEGORY"));
					couponDTO.setUsed(rs.getString("USED"));
					couponList.add(couponDTO);
				}
				System.out.println("[로그_사용가능쿠폰] "+couponList.size());
				if (couponList.size() > 0) {
					System.out.println("[로그_사용가능쿠폰] 성공");
					return couponList;
				}

					rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		System.out.println("[로그_쿠폰SelectAll] 실패");
		return null;
	}

	public CouponDTO selectOne(CouponDTO cpDTO) {
		return null;
	}

	public boolean insert(CouponDTO cpDTO) {

		conn = JDBCUtil.connect();

		if (cpDTO.getSearchCondition().equals("쿠폰추가")) {

			int couponLength = 10;

			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

			// 랜덤 쿠폰 번호 생성을 위한 StringBuilder 초기화
			StringBuilder couponCode = new StringBuilder(couponLength);

			Random rand = new Random();

			for (int i = 0; i < couponLength; i++) {

				int randomIndex = rand.nextInt(characters.length());

				char randomChar = characters.charAt(randomIndex);

				couponCode.append(randomChar);
			}

			System.out.println("[로그] 생성된 쿠폰번호 " + couponCode.toString());

			try {
				pstmt = conn.prepareStatement(INSERT);
				pstmt.setString(1, couponCode.toString());
				pstmt.setString(2, cpDTO.getMID());
				pstmt.setString(3, cpDTO.getCpName());
				pstmt.setInt(4, cpDTO.getDiscount());
				pstmt.setString(5, cpDTO.getCategory());
				System.out.println("[로그_쿠폰추가] pstmt 완료");

				int rs = pstmt.executeUpdate();

				System.out.println("[로그_쿠폰추가] SQL문 실행완료");

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

	public boolean update(CouponDTO cpDTO) {
		
		conn = JDBCUtil.connect();
		
		if(cpDTO.getSearchCondition().equals("쿠폰사용")) {
			
			try {
				pstmt = conn.prepareStatement(UPDATE);
				pstmt.setString(1, cpDTO.getCPID());
				
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}		
		}		
		return false;
	}

	public boolean delete(CouponDTO cpDTO) {
		
		conn = JDBCUtil.connect();
		
		if(cpDTO.getSearchCondition().equals("쿠폰삭제")) {
			
			try {
				pstmt = conn.prepareStatement(DELETE);
				
				int result = pstmt.executeUpdate();
				System.out.println("[로그_쿠폰삭제] "+result);
				if(result > 0) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}			
		}	
		return false;
	}
}
