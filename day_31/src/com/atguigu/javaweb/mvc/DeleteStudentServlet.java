package com.atguigu.javaweb.mvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteStudentServlet
 */
public class DeleteStudentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flowId = request.getParameter("flowId");
		
		StudentDao studentDao = new StudentDao();
		studentDao.deleteByFlowId(Integer.parseInt(flowId));
		
		request.getRequestDispatcher("/success.jsp").forward(request, response); 
	}

}
