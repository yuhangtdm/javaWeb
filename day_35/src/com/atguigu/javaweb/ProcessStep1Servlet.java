package com.atguigu.javaweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProcessStep1Servlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. ��ȡѡ�е�ͼ�����Ϣ
		String [] books = request.getParameterValues("book");
		//2. ��ͼ����Ϣ���뵽 HttpSession ��
		request.getSession().setAttribute("books", books);
		
		//2. �ض���ҳ�浽 shoppingcart/step-2.jsp
		System.out.println(request.getContextPath() + "/shoppingcart/step-2.jsp");
		response.sendRedirect(request.getContextPath() + "/shoppingcart/step-2.jsp");
	}

}
