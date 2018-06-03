package com.atguigu.javaweb;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthorityFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
//		- ��ȡ servletPath, ������ /app_3/article1.jsp
		String servletPath = request.getServletPath();
		
		//����Ҫ�����ص� url �б�. 
		List<String> uncheckedUrls = Arrays.asList("/403.jsp", "/articles.jsp", 
				"/authority-manager.jsp", "/login.jsp", "/logout.jsp");
		
		if(uncheckedUrls.contains(servletPath)){
			filterChain.doFilter(request, response);
			return;
		}
		
//		- ���û��Ѿ���¼(��ʹ�� �û��Ƿ��¼ �Ĺ�����)�������, ��ȡ�û���Ϣ. session.getAttribute("user")
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		
//		- �ٻ�ȡ�û������е�Ȩ�޵���Ϣ: List<Authority>
		List<Authority> authorities = user.getAuthorities();
		
		// - �����û��Ƿ������� servletPath ��Ȩ��: ����˼�����˱�������, ��û�и��õ�ʵ�ַ�ʽ
		Authority authority = new Authority(null, servletPath);
		// - ����Ȩ����: ��Ӧ
		if (authorities.contains(authority)) {
			filterChain.doFilter(request, response);
			return;
		}
		
//		- ��û��Ȩ��: �ض��� 403.jsp 
		response.sendRedirect(request.getContextPath() + "/403.jsp");
		return;
	}

}
