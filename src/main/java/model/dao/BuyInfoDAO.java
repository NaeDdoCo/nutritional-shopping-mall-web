package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<BuyInfoDTO> selectAll(BuyInfoDTO bDTO) {
		return null;
	}
	
	public BuyInfoDTO selectOne(BuyInfoDTO bDTO) {
		return null;
	}
	
	public boolean insert(BuyInfoDTO bDTO) {
		return false;
	}
	
	public boolean update(BuyInfoDTO bDTO) {
		return false;
	}
	
	public boolean delete(BuyInfoDTO bDTO) {
		return false;
	}

}
