package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.BuyInfoDAO;
import model.dao.MemberDAO;
import model.dao.ProductDAO;
import model.dto.BuyInfoDTO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class MainPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		ArrayList<ProductDTO> rcmDTOs = new ArrayList<ProductDTO>();
		ArrayList<ProductDTO> pDTOs = new ArrayList<ProductDTO>();
		ProductDTO pDTO = new ProductDTO();
		ProductDAO pDAO = new ProductDAO();
		
		// 상품목록페이지
		pDTO.setSearchCondition("상품목록페이지");
		pDTO.setAncSelectMin(1);
		pDTO.setAncSelectMax(8);
		pDTOs = pDAO.selectAll(pDTO);
		
		// 상품출력전체
		pDTO.setSearchCondition("상품출력전체");
		rcmDTOs = pDAO.selectAll(pDTO);
		rcmDTOs = recommendProduct(request, rcmDTOs); // TODO: 모델과 협의후 테스트
		
		// 추천 상품이 0개면 판매량순 추천
		request.setAttribute("pDTOs", pDTOs);
		if (rcmDTOs == null || rcmDTOs.size() == 0) {
			request.setAttribute("rcmDTOs", pDTOs);
		} else {
			request.setAttribute("rcmDTOs", rcmDTOs);
		}
		
		forward.setPath("main.jsp");
		forward.setRedirect(false);		
		
		return forward;
	}
	
	/**
	 * @return
	 * 		추천 상품 목록
	 * 		0이면 추천 상품 없음
	 * 		null이면 에러
	 */
	private ArrayList<ProductDTO> recommendProduct(HttpServletRequest request, ArrayList<ProductDTO> allDTOs) {
		ArrayList<ProductDTO> rcmDTOs = new ArrayList<ProductDTO>();
		String health = "";
		// 상품 추천 알고리즘
		// 로그인: HEALTH
		// 비로그인: 판매량
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("member");
		
		MemberDAO mDAO = new MemberDAO();
		MemberDTO mDTO = new MemberDTO();
		
		mDTO.setSearchCondition("건강상태");
		mDTO.setMid(mid);
		mDTO = mDAO.selectOne(mDTO);
		if (mDTO != null) {
			health = mDTO.getHealth();			
		} else {
			return null;
		}
		
		if (mid != null) { // 로그인 : HEALTH
			for (ProductDTO pDTO : allDTOs) {
				if (health.equals(pDTO.getCategory())) {
					rcmDTOs.add(pDTO);
				}
			}
			// TODO: 정렬 고민
		} else { // 비로그인 : 판매량순
			// allDTOs 판매량순 정렬
	        Comparator<ProductDTO> comparator = new Comparator<ProductDTO>(){
	            @Override
	            public int compare(ProductDTO o1, ProductDTO o2) {
	            	BuyInfoDAO bDAO = new BuyInfoDAO();
	            	BuyInfoDTO bDTO = new BuyInfoDTO();
	            	
	            	bDTO.setSearchCondition("판매량");
	            	bDTO.setPID(o1.getPID());
	            	bDTO = bDAO.selectOne(bDTO);
	            	int qty1 = bDTO.getbQty();
	            	
	            	bDTO.setSearchCondition("판매량");
	            	bDTO.setPID(o2.getPID());
	            	bDTO = bDAO.selectOne(bDTO);
	            	int qty2 = bDTO.getbQty();
	            	
	                return qty2 - qty1;
	            }
	        };

			allDTOs.sort(comparator);
			for (int i = 0; i < 8; i++) {
				rcmDTOs.add(allDTOs.get(i));
			}
		}
		
		System.out.println(rcmDTOs);
//		for (ProductDTO data : rcmDTOs) {
//			retDTOs.add(data);
//		}
		
		return rcmDTOs;
	}

}
