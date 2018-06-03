package com.atguigu.javaweb;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		
		try {
			Method method = getClass().getMethod(methodName, 
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private UserDao userDao = new UserDao();
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. ��ȡ name
		String name = request.getParameter("name");
		
		//2. ���� UserDao ��ȡ�û���Ϣ, ���û���Ϣ���뵽 HttpSession ��
		User user = userDao.get(name);
		request.getSession().setAttribute("user", user);
		
		//3. �ض��� articles.jsp
		response.sendRedirect(request.getContextPath() + "/articles.jsp");
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. ��ȡ HttpSession
		
		//2. ʹ HttpSession ʧЧ
		request.getSession().invalidate();
		
		//3. �ض��� /loign.jsp
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	

}
