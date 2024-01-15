package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

@WebServlet("/CheckPw")
public class CheckPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckPw() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[CheckPw] doPost");
		
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		System.out.println("[CheckPw] " + password + " " + confirmPassword);
		
		PrintWriter out = response.getWriter();
		if(password.equals(confirmPassword)) {
			System.out.println("[CheckPw] suc");
			out.print("suc");
		}else {
			System.out.println("[CheckPw] fail");
			out.print("fail");
		}
	
	}

}
