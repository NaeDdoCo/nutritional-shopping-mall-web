package controller;

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
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getCharacterEncoding() == null) {
			
			request.setCharacterEncoding(encoding);
		}
		System.out.println("[로그] 필터 서블릿 클래스에서 작성한 로그");
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		//최초 단한번 실행되는 메서드: 유사 생성자
		this.encoding=fConfig.getServletContext().getInitParameter("encoding");
	
	}

}
