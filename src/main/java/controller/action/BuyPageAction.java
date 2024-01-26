package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.CouponDAO;
import model.dao.MemberDAO;
import model.dto.CouponDTO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class BuyPageAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      ActionForward forward = new ActionForward();

      HttpSession session = request.getSession();
      String mid = request.getParameter("member"); //mid가져오기

      CouponDTO cpDTO = new CouponDTO();
      CouponDAO cpDAO = new CouponDAO();
      MemberDTO mDTO = new MemberDTO();
      MemberDAO mDAO = new MemberDAO();
      ProductDTO pDTO = new ProductDTO();


      //회원정보가져오기
      mDTO.setMID(mid);
      mDTO.setSearchCondition("");
      mDTO = mDAO.selectOne(mDTO);
      request.setAttribute("memberInfo", mDTO);


      //쿠폰목록가져오기
      cpDTO.setSearchCondition("사용가능쿠폰");
      cpDTO.setMID(mid);
      ArrayList<CouponDTO> couponList = cpDAO.selectAll(cpDTO);
      request.setAttribute("couponList", couponList);

      //장바구니 상품은 체크박스로 선택된 상품만 배열로 각각정보가 넘어옴
      String[] imagePaths = request.getParameterValues("imagePath[]");
      String[] pNames = request.getParameterValues("pName[]");
      String[] sellingPrices = request.getParameterValues("sellingPrice[]");
      String[] cQtys = request.getParameterValues("cQty[]");
      
      
//      for (int i = 0; i < imagePaths.length; i++) {
//    	  System.out.println("[log] imagePaths :" + imagePaths[i]);
//      }
//      for (int i = 0; i < pNames.length; i++) {
//    	  System.out.println("[log] pNames :" + pNames[i]);
//      }
//      for (int i = 0; i < cQtys.length; i++) {
//    	  System.out.println("[log] cQtys :" + cQtys[i]);
//      }
//      for (int i = 0; i < cQtys.length; i++) {
//    	  System.out.println("[log] sellingPrices :" + sellingPrices[i]);
//      }
      
      ArrayList<ProductDTO> selectedProductsList = new ArrayList<>();
      for (int i = 0; i < pNames.length; i++) {
         String imagePath = imagePaths[i];
         String pName = pNames[i];
         int sellingPrice = Integer.parseInt(sellingPrices[i]);
         int cQty = Integer.parseInt(cQtys[i]);
         pDTO.setImagePath(imagePath);
         pDTO.setpName(pName);
         pDTO.setpQty(cQty);
         pDTO.setSellingPrice(sellingPrice);
         selectedProductsList.add(pDTO);
      }

      request.setAttribute("selectedProductsList", selectedProductsList);
//    System.out.println("[log] selectedProductsList : "+ selectedProductsList);
      
      
      

      forward.setPath("buy.jsp");
      forward.setRedirect(false);

      return forward;
   }

}