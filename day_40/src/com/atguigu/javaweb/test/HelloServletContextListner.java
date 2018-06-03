package com.atguigu.javaweb.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
    
public class HelloServletContextListner 
	implements ServletContextListener, ServletRequestListener, HttpSessionListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext ���󱻴����� " + sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContext �������١�" + sce.getServletContext());
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("HttpSession ��������");
	}
       
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession ������");
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		System.out.println("ServletRequest ������");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		System.out.println("ServletRequest ������");
	}

}
