package controller.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calculPrice")
public class calculPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public calculPrice() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// V에서 상품 ID와 수량 가져오기
        int productId = Integer.parseInt(request.getParameter("PId"));
        int quantity = Integer.parseInt(request.getParameter("cQTY"));

        //1개의 상품 가격을 가져오는 로직
        int productPrice = productId * quantity;

        //장바구니의 총 상품에 대한 총금액 계산
        int totalPrice = productPrice * quantity;

        request.setAttribute("productPrice", productPrice);
        request.setAttribute("totalPrice", totalPrice);
        
	}
}
