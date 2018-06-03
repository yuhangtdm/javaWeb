package com.atguigu.mvcapp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.mvcapp.dao.CriteriaCustomer;
import com.atguigu.mvcapp.dao.CustomerDAO;
import com.atguigu.mvcapp.dao.factory.CustomerDAOFactory;
import com.atguigu.mvcapp.dao.impl.CustomerDAOXMLImpl;
import com.atguigu.mvcapp.domain.Customer;


public class CustomerServlet extends HttpServlet {


	private CustomerDAO customerDAO = 
			CustomerDAOFactory.getInstance().getCustomerDAO();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String method = request.getParameter("method");
//		
//		switch(method){
//			case "add":    add(request, response); break;
//			case "query":  query(request, response); break;
//			case "delete": delete(request, response); break;
//			case "update": update(request, response); break;
//		}
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//1. ��ȡ ServletPath: /edit.do �� /addCustomer.do
		String servletPath = req.getServletPath();

		//2. ȥ�� / �� .do, �õ������� edit �� addCustomer �������ַ���
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);
		
		try {
			//3. ���÷����ȡ methodName ��Ӧ�ķ���
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, 
					HttpServletResponse.class);
			//4. ���÷�����ö�Ӧ�ķ���
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			//������һЩ��Ӧ.
			resp.sendRedirect("error.jsp");
		}
		
	}

	private void edit(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		
		String forwardPath = "/error.jsp";
		
		//1. ��ȡ������� id
		String idStr = request.getParameter("id");
		
		//2. ���� CustomerDAO �� customerDAO.get(id) ��ȡ�� id ��Ӧ�� Customer ���� customer
		try {
			Customer customer = customerDAO.get(Integer.parseInt(idStr));
			if(customer != null){
				forwardPath = "/updatecustomer.jsp";
				//3. �� customer ���� request ��
				request.setAttribute("customer", customer);
			}
		} catch (NumberFormatException e) {} 
		
		
		//4. ��Ӧ updatecustomer.jsp ҳ��: ת��.
		request.getRequestDispatcher(forwardPath).forward(request, response);
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//1. ��ȡ������: id, name, address, phone, oldName
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String oldName = request.getParameter("oldName");
		
		//2. ���� name �Ƿ��Ѿ���ռ��:
		
		//2.1 �Ƚ� name �� oldName �Ƿ���ͬ, ����ͬ˵�� name ����. 
		//2.1 ������ͬ, ����� CustomerDAO �� getCountWithName(String name) ��ȡ name �����ݿ����Ƿ����
		if(!oldName.equalsIgnoreCase(name)){
			long count = customerDAO.getCountWithName(name);
			//2.2 ������ֵ���� 0, ����Ӧ updatecustomer.jsp ҳ��: ͨ��ת���ķ�ʽ����Ӧ newcustomer.jsp
			if(count > 0){
				//2.2.1 �� updatecustomer.jsp ҳ����ʾһ��������Ϣ: �û��� name �Ѿ���ռ��, ������ѡ��!
				//�� request �з���һ������ message: �û��� name �Ѿ���ռ��, ������ѡ��!, 
				//��ҳ����ͨ�� request.getAttribute("message") �ķ�ʽ����ʾ
				request.setAttribute("message", "�û���" + name + "�Ѿ���ռ��, ������ѡ��!");
				
				//2.2.2 newcustomer.jsp �ı�ֵ���Ի���. 
				//address, phone ��ʾ�ύ�����µ�ֵ, �� name ��ʾ oldName, ���������ύ�� name
				
				//2.2.3 ��������: return 
				request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);
				return;
			}
		}
		
		//3. ����֤ͨ��, ��ѱ�������װΪһ�� Customer ���� customer
		Customer customer = new Customer(name, address, phone);
		customer.setId(Integer.parseInt(id)); 
		
		//4. ���� CustomerDAO ��  update(Customer customer) ִ�и��²���
		customerDAO.update(customer);
		
		//5. �ض��� query.do
		response.sendRedirect("query.do");
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//��ȡģ����ѯ���������
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		//�����������װΪһ�� CriteriaCustomer ����
		CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);
		
		//1. ���� CustomerDAO �� getForListWithCriteriaCustomer() �õ� Customer �ļ���
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
		
		//2. �� Customer �ļ��Ϸ��� request ��
		request.setAttribute("customers", customers);
		
		//3. ת��ҳ�浽 index.jsp(����ʹ���ض���)
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idStr = request.getParameter("id");
		int id = 0;
		
		//try ... catch ������: ��ֹ idStr ����תΪ int ����
		//������ת�� id = 0, �޷������κε�ɾ������. 
		try {
			id = Integer.parseInt(idStr);
			System.out.println(id); 
			customerDAO.delete(id);
		} catch (Exception e) {}
		
		response.sendRedirect("query.do");
	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1. ��ȡ������: name, address, phone
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		//2. ���� name �Ƿ��Ѿ���ռ��:
		
		//2.1 ���� CustomerDAO �� getCountWithName(String name) ��ȡ name �����ݿ����Ƿ����
		long count = customerDAO.getCountWithName(name);
		
		//2.2 ������ֵ���� 0, ����Ӧ newcustomer.jsp ҳ��: 
		//ͨ��ת���ķ�ʽ����Ӧ newcustomer.jsp
		if(count > 0){
			//2.2.1 Ҫ���� newcustomer.jsp ҳ����ʾһ��������Ϣ: �û��� name �Ѿ���ռ��, ������ѡ��!
			//�� request �з���һ������ message: �û��� name �Ѿ���ռ��, ������ѡ��!, 
			//��ҳ����ͨ�� request.getAttribute("message") �ķ�ʽ����ʾ
			request.setAttribute("message", "�û���" + name + "�Ѿ���ռ��, ������ѡ��!");
			
			//2.2.2 newcustomer.jsp �ı�ֵ���Ի���. 
			//ͨ�� value="<%= request.getParameter("name") == null ? "" : request.getParameter("name") %>"
			//�����л���
			//2.2.3 ��������: return 
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		
		//3. ����֤ͨ��, ��ѱ�������װΪһ�� Customer ���� customer
		Customer customer = new Customer(name, address, phone);
		
		//4. ���� CustomerDAO ��  save(Customer customer) ִ�б������
		customerDAO.save(customer);
		
		//5. �ض��� success.jsp ҳ��: ʹ���ض�����Ա�����ֱ����ظ��ύ����.  
		response.sendRedirect("success.jsp");
		//request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

}
