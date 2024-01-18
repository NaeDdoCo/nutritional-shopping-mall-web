package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import model.dto.CouponDTO;
import model.util.JDBCUtil;

public class CouponDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	/* 기능
	 *  1) 보유한 쿠폰 보기
	 *  	마이페이지에서 보유한 쿠폰 출력(사용가능, 사용불가)
	 *  	사용기간이 만료되면 그냥 삭제
	 *  2) X
	 *  	쿠폰의 상세정보를 볼 일이 없다...
	 *  3) 쿠폰추가
	 *  	관리자가 추가시켜주는 형식
	 *  	일단 샘플로는 관리자가 입력한 MID의 회원에게 지급
	 *  4) X쿠폰상태 변경(취소, 환불)
	 *  	쿠폰의 할인률, 이름등 관리자가 잘못 줘서 수정할 때에나 사용할 듯
	 *  5) 쿠폰삭제
	 *  	사용기간이 지나면 삭제
	 */ 
	
	/*INSERT INTO COUPON (CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY)
	VALUES (
	 '4', 	--쿠폰번호--
	 'teemo',		--소유자MID--
	 '임시쿠폰 1',	--쿠폰이름--
	 SYSTIMESTAMP,	--현재시간 +30일--
	 30,			-- 할인율
	 '눈'
	);
	--쿠폰업데이트--
	UPDATE COUPON 
	SET USED = '사용'
	WHERE CP_ID = '5';

	--쿠폰목록출력(마이페이지, 사용, 미사용 정렬 후 만료일 순 정렬)--
	-- 내림차순으로 해야 사용이위로 옴
	SELECT CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY, USED 
	FROM COUPON
	WHERE M_ID = 'teemo'
	ORDER BY USED DESC, PERIOD ASC;

	--쿠폰목록출력(쿠폰적용, 미사용쿠폰을 만료일 순으로 정렬하여 사용)--
	SELECT CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY, USED 
	FROM COUPON
	WHERE M_ID = 'teemo' AND USED = '미사용'
	ORDER BY PERIOD ASC;*/	
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="INSERT INTO COUPON (CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY) "
			+ "	VALUES ( "
			+ "	 ?, "
			+ "	 ?, "
			+ "	 ?, "
			+ "	 SYSTIMESTAMP, "
			+ "	 ? "
			+ "	 ? "
			+ "	)";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<CouponDTO> selectAll(CouponDTO cpDTO) {
		return null;
	}
	
	public CouponDTO selectOne(CouponDTO cpDTO) {
		return null;
	}
	
	public boolean insert(CouponDTO cpDTO) {
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
	        
			conn = JDBCUtil.connect();
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
		return false;
	}
	
	public boolean delete(CouponDTO cpDTO) {
		return false;
	}
}
