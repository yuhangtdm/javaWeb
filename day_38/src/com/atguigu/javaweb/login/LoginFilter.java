package com.atguigu.javaweb.login;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.javaweb.HttpFilter;

public class LoginFilter extends HttpFilter{

	//1. �� web.xml �ļ��л�ȡ sessionKey, redirectUrl, uncheckedUrls
	private String sessionKey;
	private String redirectUrl;
	private String unchekcedUrls;
	
	@Override
	protected void init() {
		ServletContext servletContext = getFilterConfig().getServletContext();
		
		sessionKey = servletContext.getInitParameter("userSessionKey");
		redirectUrl = servletContext.getInitParameter("rediretPage");
		///login/a.jsp,/login/list.jsp,/login/login.jsp,/login/doLogin.jsp
		unchekcedUrls = servletContext.getInitParameter("uncheckedUrls");
	}
	
	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		//1. ��ȡ����� servletPath
		// /login/b.jsp
		String servletPath = request.getServletPath();
		
		//2. ��� 1 ��ȡ�� servletPath �Ƿ�Ϊ����Ҫ���� URL �е�һ��, ����, ��ֱ�ӷ���. ��������
		List<String> urls = Arrays.asList(unchekcedUrls.split(","));
		if(urls.contains(servletPath)){
			filterChain.doFilter(request, response);
			return;
		}
		
		//3. �� session �л�ȡ sessionKey ��Ӧ��ֵ, ��ֵ������, ���ض��� redirectUrl
		Object user = request.getSession().getAttribute(sessionKey);
		if(user == null){
			response.sendRedirect(request.getContextPath() + redirectUrl);
			return;
		}
		
		//4. ������, �����, �������. 
		filterChain.doFilter(request, response);
	}

}
