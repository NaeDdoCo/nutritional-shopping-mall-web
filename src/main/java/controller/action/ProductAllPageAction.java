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
		
		// 전체 페이지 수 확인
		pDTO.setSearchCondition("상품출력전체");
		pDTOs = pDAO.selectAll(pDTO);
		int totalPages = pDTOs.size() / 9;
		if (pDTOs.size() % 9 != 0) {
			totalPages++;
		}
		request.setAttribute("totalPages", totalPages);
		
		// 상품 목록 가져오기
		String p = request.getParameter("page");
		System.out.println("String p : " + p);
		int curPage = 1;
		if (isNumber(p)) {
			curPage = Integer.parseInt(p);
		}
		
		pDTO.setSearchCondition("상품목록페이지");
		
		pDTO.setAncSelectMin((curPage - 1) * 9 + 1);
		pDTO.setAncSelectMax((curPage - 1) * 9 + 9);
		pDTOs = pDAO.selectAll(pDTO);
		System.out.println("pDTOs : " + pDTOs);
		request.setAttribute("pDTOs", pDTOs);

		forward.setPath("productAll.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	public static boolean isNumber(String strValue) {
		return strValue != null && strValue.matches("[-+]?\\d*\\.?\\d+");
	}
}
