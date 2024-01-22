package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.ProductDAO;
import model.dao.ReviewDAO;
import model.dto.ProductDTO;
import model.dto.ReviewDTO;

public class ProductDetailPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		ProductDAO pDAO = new ProductDAO();
		ProductDTO pDTO = new ProductDTO();
//		ReviewDAO rDAO=new ReviewDAO();
//		ReviewDTO rDTO=new ReviewDTO();
		
		pDTO.setPID(Integer.parseInt(request.getParameter("PID")));
		pDTO.setSearchCondition("상품상세정보");
		pDTO=pDAO.selectOne(pDTO);
		
		System.out.println("[log] ProductDetailPageAction pDTO : "+ pDTO);
		
//	     // pid로 해당 제품의 리뷰 정보 조회
//        ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
//        
//        rDTO.setPID(Integer.parseInt(request.getParameter("PID")));
//        rDTO.setSearchCondition("");
//        rDTO=rDAO.selectAll(rDTO);

        // 조회된 상품 정보와 리뷰 정보를 JSP에 전달
        request.setAttribute("productDetail", pDTO);
//        request.setAttribute("reviewList", reviewList);
        
		forward.setPath("productDetailPage.do");
		forward.setRedirect(false);
		
		return forward;
	}

}
