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
import model.dao.ProductDAO;
import model.dto.CouponDTO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class BuyPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();

		CouponDTO cpDTO = new CouponDTO();
		CouponDAO cpDAO = new CouponDAO();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		ProductDAO productDAO = new ProductDAO();

		//세션의 member의 mid가져오기
		String MID = (String)session.getAttribute("member");
		//회원정보가져오기
		mDTO.setMID(MID);
		mDTO.setSearchCondition("주문정보");
		mDTO = mDAO.selectOne(mDTO);
		request.setAttribute("memberInfo", mDTO);
		System.out.println("[log] mDTO : " + mDTO);

		//쿠폰목록 가져오기
		cpDTO.setSearchCondition("사용가능쿠폰");
		System.out.println("[log] MID : " + MID);
		cpDTO.setMID(MID);
		System.out.println("[log] cpDTO.getMID() :  " + cpDTO.getMID());

		ArrayList<CouponDTO> couponList = cpDAO.selectAll(cpDTO);
		request.setAttribute("couponList", couponList);

		System.out.println("[log] couponList : " + couponList);

		//장바구니 상품은 체크박스로 선택된 상품만 배열로 각각 정보가 넘어옴
		String[] PIDs = request.getParameterValues("PID[]");
		String[] CIDs = request.getParameterValues("CID[]");
		String[] imagePaths = request.getParameterValues("imagePath[]");
		String[] pNames = request.getParameterValues("pName[]");
		String[] sellingPrices = request.getParameterValues("sellingPrice[]");
		String[] cQtys = request.getParameterValues("cQty[]");

		//로그
		//      for (int i = 0; i < imagePaths.length; i++) {
		//    	  System.out.println("[log] imagePaths : " + imagePaths[i]);
		//      }
		//      for (int i = 0; i < pNames.length; i++) {
		//    	  System.out.println("[log] pNames : " + pNames[i]);
		//      }
		//      for (int i = 0; i < cQtys.length; i++) {
		//    	  System.out.println("[log] cQtys : " + cQtys[i]);
		//      }
		//      for (int i = 0; i < cQtys.length; i++) {
		//    	  System.out.println("[log] sellingPrices : " + sellingPrices[i]);
		//      }

		//PID로 category 가져오기
		ArrayList<ProductDTO> selectedProductsList = new ArrayList<>();
		for (int i = 0; i < PIDs.length; i++) {
			ProductDTO productDTO = new ProductDTO();
			int PID = Integer.parseInt(PIDs[i]);
			String imagePath = imagePaths[i];
			String pName = pNames[i];
			int sellingPrice = Integer.parseInt(sellingPrices[i]);
			int cQty = Integer.parseInt(cQtys[i]);
			int CID = Integer.parseInt(CIDs[i]);

			//PID로 CATEGORY 가져오기
			productDTO.setSearchCondition("카테고리");
			productDTO.setPID(PID);
			productDTO = productDAO.selectOne(productDTO);
			System.out.println("[log] productDTO category : " + productDTO);
			//V에서 받아온 CID/imagePath/pName/cQty/sellingPrice + category selectedProductsList에 넣기
			productDTO.setPID(PID);
			productDTO.setAncCID(CID);
			productDTO.setImagePath(imagePath);
			productDTO.setpName(pName);
			productDTO.setpQty(cQty);
			productDTO.setSellingPrice(sellingPrice);
			selectedProductsList.add(productDTO);
		}
		//V로 반환     
		request.setAttribute("selectedProductsList", selectedProductsList);

		for (ProductDTO productDTOs : selectedProductsList) {
			System.out.println("[log] productDTOs : " + productDTOs);
		}

		forward.setPath("buy.jsp");
		forward.setRedirect(false);

		return forward;
	}

}