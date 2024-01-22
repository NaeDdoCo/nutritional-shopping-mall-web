package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		pDTO.setSearchCondition("상품목록페이지");
		pDTO.setAncSelectMin(1);
		pDTO.setAncSelectMax(9);
		pDTOs = pDAO.selectAll(pDTO);
		request.setAttribute("pDTOs", pDTOs);

		forward.setPath("productDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
