package controller.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CartDAO;
import model.dao.ProductDAO;
import model.dto.CartDTO;
import model.dto.ProductDTO;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@WebServlet("/InsertCart")
public class InsertCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("[Log] InsertCart");
        
		/* Servlet InsertCart
        * 
        * 실재고와 유효성 검사
		*/
		
		// 장바구니에 담을 상품 받아오기 (필수)
		System.out.println("PID: " + request.getParameter("PID"));
		if (request.getParameter("PID") == null) {
			out.print("false");
			return;
		}
		String strPid = (String)request.getParameter("PID");
		int intPid = Integer.parseInt(strPid);
		
		// 장바구니에 담는 수량 받아오기 (선택)
		// 디폴트는 1
		int intQty = 1;
		if (request.getAttribute("cQty") != null) {
			String strQty = (String)request.getParameter("cQty");
			intQty = Integer.parseInt(strQty);
		}
		System.out.println("cQty: " + intQty);
		
		// 실재고 유효성 검사
		// TODO: 메서드 빼는거 검토
		ProductDTO pDTO = new ProductDTO();
		ProductDAO pDAO = new ProductDAO();
		pDTO.setSearchCondition("상품상세정보");
		pDTO.setPID(intPid);
		pDTO = pDAO.selectOne(pDTO);
		if (intQty > pDTO.getpQty()) {
			System.out.println("재고 부족 cQty: " + intQty + "  pQty: " + pDTO.getpQty());
			out.print("false");
			return;
		}

		// 로그인한 사용자 받아오기 (필수)
		System.out.println("member: " + request.getAttribute("member"));
		HttpSession session = request.getSession();
		if (session.getAttribute("member") == null) {
			out.print("false");
			return;
		}
		String mid = (String)session.getAttribute("member");
		
		// 장바구니에 담기
		CartDTO cDTO = new CartDTO();
		CartDAO cDAO = new CartDAO();
		
		cDTO.setSearchCondition("장바구니추가");
		cDTO.setMid(mid);
		cDTO.setPid(intPid);
		cDTO.setcQty(intQty);
		System.out.println("cDAO.insert result: " + cDAO.insert(cDTO));
		if (cDAO.insert(cDTO)) {
			out.print("true");
		} else {
			out.print("false");
		}
		
		// V에 인증번호 전달
	}

}
