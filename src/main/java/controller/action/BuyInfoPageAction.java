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
		
		BuyInfoDTO bDTO = new BuyInfoDTO();
		ArrayList<BuyInfoDTO> bDTOs = new ArrayList<BuyInfoDTO>();
		BuyInfoDAO bDAO = new BuyInfoDAO();
		
		HttpSession session = request.getSession();
		String MID = (String)session.getAttribute("member");
		
		bDTO.setMID(MID);
		bDTOs = bDAO.selectAll(bDTO);
		System.out.println("[BuyInfoPageAction] bDTOs : " + bDTOs);
		request.setAttribute("bDTOs", bDTOs);
		
		return forward;
	}

}
