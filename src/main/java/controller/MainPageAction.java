package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;
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
		rcmDTOs = recommendProduct(rcmDTOs);
		
		request.setAttribute("rcmDTOs", rcmDTOs);
		request.setAttribute("pDTOs", pDTOs);
		
		forward.setPath("main.jsp");
		forward.setRedirect(false);		
		
		return forward;
	}
	
	private ArrayList<ProductDTO> recommendProduct(ArrayList<ProductDTO> rcmDTOs) {
		ArrayList<ProductDTO> retDTOs = new ArrayList<ProductDTO>();
		
		// TODO: 상품 추천 알고리즘
		for (ProductDTO data : rcmDTOs) {
			retDTOs.add(data);
		}
		
		return retDTOs;
	}

}
