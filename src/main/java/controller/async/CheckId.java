package controller.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

@WebServlet("/checkId")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckId() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request로 MID 받아오기
		String MID = request.getParameter("MID");
		
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		
		//중복된 ID가 있는지 확인
		mDTO.setSearchCondition("아이디중복검사");
		mDTO.setMID(MID);
		mDTO = mDAO.selectOne(mDTO);
		
		PrintWriter out = response.getWriter();
		
		//mDTO가 null이면 중복된 아이디가 없다는 의미
		if(mDTO == null) {
			//V에서 모달에 띄워줄 정보 보내기
			//suc -> 성공
			out.print("suc");
		}else {
			//fail -> 실패
			out.print("fail");
		}
	
	}

}
