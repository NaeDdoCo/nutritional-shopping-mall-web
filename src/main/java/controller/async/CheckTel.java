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
        
		//받아온 연락처 010,XXXX,XXXX -> 010XXXXXXXX 로 합치기
		String phoneNumber1=(String)request.getParameter("phoneNum1");
		String phoneNumber2=(String)request.getParameter("phoneNum2");
		String phoneNumber3=(String)request.getParameter("phoneNum3");
		
		String combinedPhoneNumber=phoneNumber1+phoneNumber2+phoneNumber3;
		
		//5자리의 인증번호 랜덤으로 생성
		Random random  = new Random();
        String numStr = "";
        for(int i=0; i<5; i++) {
        	// 0부터 9까지 랜덤으로 숫자생성
            String ranNum = Integer.toString(random.nextInt(10));
            // 랜덤으로 나온 숫자를 누적 => 5자리
            numStr += ranNum;
        }
        
		//sms api를 사용하여 sms 발송
		DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize("NCSB74ETIIIUKKTG", "PK6JVDBUWKBA8FVCBACP6PALG9AYPJPD", "https://api.coolsms.co.kr");
		Message message = new Message();
		message.setFrom("01050929241");
		message.setTo(combinedPhoneNumber);
		message.setText("내또코 영양제 쇼핑몰 인증번호 ["+numStr+"]을 입력해주세요. (•ө•)♡");

		try {
		  messageService.send(message);
		} catch (NurigoMessageNotReceivedException exception) {
		  // 발송에 실패한 메시지 목록을 확인 가능!
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
