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
      ReviewDAO rDAO=new ReviewDAO();
      ReviewDTO rDTO=new ReviewDTO();
      
      String strPID = request.getParameter("PID");
      int PID = Integer.parseInt(strPID);
      
      pDTO.setPID(Integer.parseInt(request.getParameter("PID")));
      pDTO.setSearchCondition("상품상세정보");
      pDTO = pDAO.selectOne(pDTO);
      
      //해당 PID의 평균 별점 가져오기
      int avgRating = getAverageRating(PID);
      pDTO.setAncAvgRating(avgRating);
      System.out.println("[log] ProductDetailPageAction rDTO PID : " + pDTO.getPID());
      System.out.println("[log] ProductDetailPageAction rDTO 평균별점 : " + pDTO.getAncAvgRating());
      
      //PID로 해당 제품의 리뷰 정보 조회
      ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
      
      rDTO.setAncPID(PID);
      rDTO.setSearchCondition("상품리뷰");
      reviewList = rDAO.selectAll(rDTO);
     
      rDTO.setAncPID(PID);
       
      // 조회된 상품 정보와 리뷰 정보를 JSP에 전달
      request.setAttribute("productDetail", pDTO);
      request.setAttribute("reviewList", reviewList);
        
      forward.setPath("productDetail.jsp");
      forward.setRedirect(false);
      
      return forward;
   }
   
   //평균별점 계산 후 가져오기_소수점자리 절삭
	private int getAverageRating(int PID) {
		System.out.println("getAverageRating 메서드 진입");
	    ReviewDTO rDTO = new ReviewDTO();
	    ReviewDAO rDAO = new ReviewDAO();
	    
	    rDTO.setAncPID(PID);
	    rDTO.setSearchCondition("별점평균");
	    
	    rDTO = rDAO.selectOne(rDTO);
	    
	    if (rDTO != null) {
	        // 평균값이 있다면, int 평균 별점 값을 반환
	        return (int) rDTO.getAncAvgScore();
	    } else {
	        // 평균 별점이 없을 경우 기본값 0 반환
	        return 0;
	    }
	}
   

}