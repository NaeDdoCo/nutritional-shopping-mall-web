package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter({ "*.jsp", "*.do" })
public class EncFilter extends HttpFilter implements Filter {
       
	private String encoding; // 로직부분에 하드코딩을 없애기 위해 존재하는 멤버변수
	
    public EncFilter() {
        super();
    }

	public void destroy() {
	//필터 소멸시 호출되는 메서드
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//요청이나 응답이 오기전에 이 메서드가 호출되어 필터가 처리를 수행
		
		//인코딩 설정이 되어있지 않다면, 인코딩 설정
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
		}
//		System.out.println("[로그] 필터 서블릿 클래스에서 작성한 로그");
		
		//다음 필터로 제어를 넘기는 역할 : 현재 필터처리 후 다른 필터나 서블릿을 수행하게 함
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		//필터가 초기화될 때 호출되는 메서드
	    //최초 단한번 실행되는 메서드(유사 생성자)
	    //web.xml에 설정된 'encoding'이라는 이름으로 설정된 값을 읽어와서 EncFilter 클래스의 멤버 변수인 encoding에 할당해 줌
		this.encoding=fConfig.getServletContext().getInitParameter("encoding");
	
	}

}
