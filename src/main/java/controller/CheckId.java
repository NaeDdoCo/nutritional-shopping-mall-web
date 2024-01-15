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

@WebServlet("/CheckId")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckId() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String MID = request.getParameter("MID");
		System.out.println(MID);
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		mDTO.setSearchCondition("아이디중복검사");
		mDTO.setMid(MID);
		mDTO=mDAO.selectOne(mDTO);
		System.out.println(mDTO);
		PrintWriter out = response.getWriter();
		if(mDTO!=null) {
			System.out.println("suc");
			out.print("suc");
			
		}else {
			System.out.println("fail");
			out.print("fail");
			
		}
	
	}

}
