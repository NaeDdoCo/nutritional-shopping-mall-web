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
		// TODO: pDAO.selectAll
	
		request.setAttribute("rcmDTOs", rcmDTOs);
		request.setAttribute("pDTOs", pDTOs);
		
		forward.setPath("main.jsp");
		forward.setRedirect(false);		
		
		return forward;
	}

}
