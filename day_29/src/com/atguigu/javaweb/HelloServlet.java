package com.atguigu.javaweb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet{

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig");
		return null;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo");
		return null;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("init");
		
		String user = servletConfig.getInitParameter("user");
		System.out.println("user�� " + user);
		
		Enumeration<String> names = servletConfig.getInitParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = servletConfig.getInitParameter(name);
			System.out.println("^^" + name + ": " + value);
		}
		
		String serlvetName = servletConfig.getServletName();
		System.out.println(serlvetName); 
		
		//��ȡ ServletContext ����
		ServletContext servletContext = servletConfig.getServletContext();
		
		String driver = servletContext.getInitParameter("driver");
		System.out.println("driver:" + driver);
		
		Enumeration<String> names2 = servletContext.getInitParameterNames();
		while(names2.hasMoreElements()){
			String name = names2.nextElement();
			System.out.println("-->" + name); 
		}
		
		String realPath = servletContext.getRealPath("/note.txt");
		//����: E:\Java\Source\atguigu\java-1\day_29\WebContent\note.txt
		System.out.println(realPath);
		
		String contextPath = servletContext.getContextPath();
		System.out.println(contextPath); 
		
		Properties pros = new Properties();
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream is = classLoader.getResourceAsStream("jdbc.properties");
			System.out.println("1. " +  is);
			pros.load(is);
			
			System.out.println(pros.getProperty("name"));  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pros = new Properties();
		
		try {
			InputStream is2 = servletContext.getResourceAsStream("/WEB-INF/classes/jdbc.properties");
			System.out.println("2. " + is2);
			pros.load(is2);
			System.out.println(pros.getProperty("name"));  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String picPath = servletContext.getRealPath("/WEB-INF/lib");
		System.out.println(picPath);
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("service");
	}

	public HelloServlet() {
		System.out.println("HelloServlet's constructor");
	}

}
