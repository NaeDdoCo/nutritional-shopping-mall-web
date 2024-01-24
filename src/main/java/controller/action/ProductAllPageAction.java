package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.ProductDAO;
import model.dto.ProductDTO;

public class ProductAllPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		ArrayList<ProductDTO> pDTOs = new ArrayList<ProductDTO>();
		ProductDTO pDTO = new ProductDTO();
		ProductDAO pDAO = new ProductDAO();
		
		pDTO.setSearchCondition("상품출력필터");
		pDTO.setAncSelectMin(1);
		pDTO.setAncSelectMax(100);
		
		String searchName = "";
		if (request.getParameter("searchName") != null) {
			searchName = (String)request.getParameter("searchName");
		}
		System.out.println("searchName : " + searchName);
		pDTO.setpName(searchName);
		
		String category = "";
		if (request.getParameter("category") != null) {
			category = (String)request.getParameter("category");
		}
		System.out.println("category : " + category);
		pDTO.setCategory(category);
		
		int price = 0;
		if (request.getParameter("price") != null) {
			price = Integer.parseInt((String)request.getParameter("price"));
		}
		System.out.println("price : " + price);
		pDTO.setSellingPrice(price);
//		pDTO.setSellingPrice(0);
		pDTOs = pDAO.selectAll(pDTO);
		
		/* 
		 * 전체 페이지 수 확인
		*/
		
		System.out.println("pDTOs.size : " + pDTOs.size());
		int totalPages = pDTOs.size() / 9;
		if (pDTOs.size() % 9 != 0) {
			totalPages++;
		}
		request.setAttribute("totalPages", totalPages);
		
		/* 
		 * 상품 목록 9개 띄우기
		*/
		
		// 뷰에서 넘겨준 page
		String strPage = request.getParameter("page");
		System.out.println("String strPage : " + strPage);
		int intPage = 1;
		
		// strPage == null 인 상황은, (메인 페이지 -> 전체 목록 페이지) 처음 올 때! 
		if (strPage != null) {
			intPage = Integer.parseInt(strPage);
		}
		// page가 유효한 범위를 벗어남
		if (intPage < 0 || intPage > totalPages) {
			forward.setPath("error.do");
			forward.setRedirect(true);
			
			return forward;
		}
		
		// retDTOs : 뷰에게 넘겨준 DTO들이고
		// pDTOs : 모델에서 가져온 필터가 적용된 모든 상품들 (9개 이상일 수 있음)
		ArrayList<ProductDTO> retDTOs = new ArrayList<ProductDTO>();
		int startProduct = (intPage - 1) * 9 + 1;
		int endProduct = (intPage - 1) * 9 + 9;
		
		// 마지막 페이지에서 상품이 9개가 안 될 경우 인덱스 에러가 날 수 있어서 방지!
		if (endProduct > pDTOs.size()) {
			endProduct = pDTOs.size() - 1;
		}
		
		// 해당 페이지에 포함되는 제품 9개 담아줌
		for (int i = startProduct; i <= endProduct; i++) {
			retDTOs.add(pDTOs.get(i));
		}
		
		request.setAttribute("pDTOs", retDTOs);

		forward.setPath("productAll.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	public static boolean isNumber(String strValue) {
		return strValue != null && strValue.matches("[-+]?\\d*\\.?\\d+");
	}
}
