package controller.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@WebServlet("/checkTel")
public class CheckTel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckTel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		/* Servlet CheckTel
        * 
        * 번호가 3개 합치기
        * 인증번호 만들어서 문자 쏘고 세션에 저장
        * 
        * 번호 인증 버튼 눌렀어
        * 뷰 -- 전화번호 3개로 --> 컨트롤러
        * 컨트롤러 -- 인증번호(요청 파라미터) --> 뷰 (인증번호 확인은 뷰가)
		*/
		
		String phoneNumber1=(String)request.getParameter("phoneNum1");
		String phoneNumber2=(String)request.getParameter("phoneNum2");
		String phoneNumber3=(String)request.getParameter("phoneNum3");
		
		String combinedPhoneNumber=phoneNumber1+phoneNumber2+phoneNumber3;
		
		//인증번호 랜덤으로 생성
		Random random  = new Random();
        String numStr = "";
        for(int i=0; i<5; i++) {
            String ranNum = Integer.toString(random.nextInt(10));   // 0부터 9까지 랜덤으로 숫자생성
            numStr += ranNum;   // 랜덤으로 나온 숫자를 누적 => 5자리
        }
        
		//sms api를 사용하여 sms 발송
		DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize("NCSUDWN0MRTNVKOZ", "VFCCDXIBPRJJIOCEMFZAUIAUEX7YZPUJ", "https://api.coolsms.co.kr");
		// Message 패키지가 중복될 경우 net.nurigo.sdk.message.model.Message로 치환하여 주세요
		Message message = new Message();
		message.setFrom("01033157366");
		message.setTo(combinedPhoneNumber);
		message.setText("내또코 영양제 쇼핑몰 인증번호 ["+numStr+"]을 입력해주세요. (•ө•)♡");

		try {
		  messageService.send(message);
		} catch (NurigoMessageNotReceivedException exception) {
		  // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
		  System.out.println(exception.getFailedMessageList());
		  System.out.println(exception.getMessage());
		} catch (Exception exception) {
		  System.out.println(exception.getMessage());
		}
		
		//V에 인증번호 전달
		PrintWriter out = response.getWriter();
		out.print(numStr);	
	}

}
