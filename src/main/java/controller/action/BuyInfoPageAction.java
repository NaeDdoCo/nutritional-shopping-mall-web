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
import model.dto.BuyInfoDTO;

public class BuyInfoPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		BuyInfoDAO bDAO = new BuyInfoDAO();
		BuyInfoDTO bDTO = new BuyInfoDTO();
		ArrayList<BuyInfoDTO> bDTOs = new ArrayList<BuyInfoDTO>();
		
		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		
		bDTO.setSearchCondition("구매내역");
		bDTO.setMID(MID);
		bDTOs = bDAO.selectAll(bDTO);
		System.out.println("[BuyInfoPageAction] bDTOs : " + bDTOs);
		request.setAttribute("bDTOs", bDTOs);
		
		forward.setPath("buyInfo.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
