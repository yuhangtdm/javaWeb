package com.atguigu.javaweb;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class ContentFilter extends HttpFilter{

    public void doFilter(HttpServletRequest request,
    		HttpServletResponse response, FilterChain filterChain)
    		throws IOException, ServletException {
    	//1. ��ȡ���� content ������ֵ
    	String content = request.getParameter("content");
    	
    	System.out.println(request); 
    	HttpServletRequest req = new MyHttpServletRequest(request);
    	
    	//2. ������ fuck, shit ���ַ����滻��Ϊ ****
    	if(content.contains(" fuck ")){
    		//SerletRequest, HttpServletRequest �в�û���ṩ���� setParameter(paramName, paramValue)
    		//�����������ķ���. 
    		
    		//Ŀ��: �ı� HttpServletRequest �� getParameter(String) ��������Ϊ: ���÷����ķ���ֵ��
    		//���� " fuck ", ���滻Ϊ " **** "
    		
    		//1. ������һ����ķ���������, ��Ҫ������д, ����ķ�ʽ��, �̳и���, ��д����. 
    		//��ʵ������Ҫ�̳� org.apache.catalina.connector.RequestFacade, ������� Tomcat
    		//��������ʵ��, ������������, �÷������޷�ʹ��. ��. 
    		
    		//2. ֱ��дһ�� HttpServletRequest �ӿڵ�ʵ����: �޷�ʵ��	���з���. ��
    		
    		//3. װ��Ŀǰ�� HttpServletRequest ����: װ���� getParameter ����, ����������������ʵ����ͬ.
    		//����һ����, ����ʵ�� HttpServletRequest �ӿ�, �ѵ�ǰ doFilter �е� request ���뵽������, ��Ϊ
    		//���Ա����, ʹ�øó�Ա����ȥʵ�ֽӿڵ�ȫ������. 
    		
    	}
    	
    	//3. ת��Ŀ��ҳ��
    	filterChain.doFilter(req, response);
    }

}
