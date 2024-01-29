package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.BuyInfoDAO;
import model.dao.CartDAO;
import model.dao.CouponDAO;
import model.dto.BuyInfoDTO;
import model.dto.CartDTO;
import model.dto.CouponDTO;

public class BuyCompPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		/*
		 * View -> Ctrl : 
		 * Ctrl : BuyInfo insert, Coupon update
		 * Ctrl -> View : buyComp.jsp
		 * */
		
		BuyInfoDTO bDTO = new BuyInfoDTO();
		BuyInfoDAO bDAO = new BuyInfoDAO();
		ArrayList<BuyInfoDTO> bDTOs = new ArrayList<BuyInfoDTO>();
		
		// 주문번호 가져오기
		bDTO.setSearchCondition("주문번호");
		bDTO = bDAO.selectOne(bDTO);
//		int orderNum = bDTO.getOrderNum(); // bDTO에 포함된 상태
		
		// 구매내역 추가
		bDTO.setSearchCondition("구매내역추가");
		
		// MID 가져오기
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("member");
		bDTO.setMID(mid);
		
		// 잘못된 mPostcode => error 페이지
		bDTO.setbPostCode(Integer.parseInt(request.getParameter("bPostcode")));
		bDTO.setbAddress(request.getParameter("bAddress"));
		bDTO.setbDetailedAddress(request.getParameter("bDetailedAddress"));
		
		
		// 상품 관련 정보
		String[] PIDs = request.getParameterValues("PID[]");
		String[] CIDs = request.getParameterValues("CID[]");
		String[] sellingPrices = request.getParameterValues("sellingPrice[]");
		String[] productCategories = request.getParameterValues("productCategory[]");
		String[] qtys = request.getParameterValues("qty[]");
		
		// 쿠폰 관련 정보
		String[] CPIDs = request.getParameterValues("CPID[]");
		String[] discounts = request.getParameterValues("discount[]");
		String[] couponCategories = request.getParameterValues("couponCategory[]");
		
		int PID = 0;
		int CID = 0;
		int sellingPrice = 0;
		String productCategory = "";
		int qty = 0;
		
		String CPID = "";
		int discount = 0;
		String couponCategory = "";
		for (int i = 0; i < PIDs.length; i++) {
			PID = Integer.parseInt(PIDs[i]);
			CID = Integer.parseInt(CIDs[i]);
			sellingPrice = Integer.parseInt(sellingPrices[i]);
			productCategory = productCategories[i];
			qty = Integer.parseInt(qtys[i]);
			
			// 쿠폰 적용되는 price 계산
			CPID = "";
			for (int j = 0; j < CPIDs.length; j++) {
				CPID = CPIDs[j];
				discount = Integer.parseInt(discounts[j]);
				couponCategory = couponCategories[j];
				
				if (productCategory.equals(couponCategory)) {
					sellingPrice = sellingPrice * (100 - discount) / 100;
					break;
				}
			}
			if (!CPID.equals("")) {
				bDTO.setCPID(CPID);
			}
			
			bDTO.setPID(PID);
			bDTO.setbQty(qty);
			bDTO.setPaymentPrice(sellingPrice);
			bDAO.insert(bDTO);
			
			bDTOs.add(bDTO);
			System.out.println("[BuyCompPageAction] bDTO insert 성공! : " + bDTO);
			
			// 구매한 상품 장바구니에서 삭제
			CartDTO cDTO = new CartDTO();
			CartDAO cDAO = new CartDAO();
			
			cDTO.setSearchCondition("장바구니삭제");
			cDTO.setCID(CID);
			cDAO.delete(cDTO);
		}
		
		// coupon 상태 '사용'으로 변경
		CouponDTO cpDTO = new CouponDTO();
		CouponDAO cpDAO = new CouponDAO();
		
		cpDTO.setSearchCondition("쿠폰사용");
		for (String data : CPIDs) {
			cpDTO.setCPID(data);
			cpDAO.update(cpDTO);
		}

		request.setAttribute("bDTOs", bDTOs);
		
		forward.setPath("buyComp.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
