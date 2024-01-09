package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.CouponDTO;

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
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<CouponDTO> selectAll(CouponDTO cpDTO) {
		return null;
	}
	
	public CouponDTO selectOne(CouponDTO cpDTO) {
		return null;
	}
	
	public boolean insert(CouponDTO cpDTO) {
		return false;
	}
	
	public boolean update(CouponDTO cpDTO) {
		return false;
	}
	
	public boolean delete(CouponDTO cpDTO) {
		return false;
	}
}
