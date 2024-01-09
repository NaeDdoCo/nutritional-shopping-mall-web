package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.dto.CartDTO;

public class CartDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	/* 기능
	 *  1) 장바구니 목록출력
	 *  2) X장바구니 속 물품 상세보기
	 *  	제품 상세보기 페이지 활용
	 *  3) 장바구니에 물품 추가하가
	 *  	장바구니에 추가
	 *  4) 장바구니 물품 수량변경
	 *  	해당 제품이 보유중인 PID값의 재고 변경
	 *  5) 구매한 물품 제거
	 *  	장바구니에서 체크한 상품만 구매하기 때문에
	 *  	구매한 상품은 장바구니에서 제거
	 */ 
	
	private static final String SELECTALL="";
	
	private static final String SELECTONE="";

	private static final String INSERT="";
	
	private static final String UPDATE="";
	
	private static final String DELETE="";
	
	public ArrayList<CartDTO> selectAll(CartDTO cDTO) {
		return null;
	}
	
	public CartDTO selectOne(CartDTO cDTO) {
		return null;
	}
	
	public boolean insert(CartDTO cDTO) {
		return false;
	}
	
	public boolean update(CartDTO cDTO) {
		return false;
	}
	
	public boolean delete(CartDTO cDTO) {
		return false;
	}
}
