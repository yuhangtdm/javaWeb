package com.atguigu.javaweb;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TestJspFragment extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		JspFragment bodyContent = getJspBody();
		//JspFragment.invoke(Witer): Writer ��Ϊ��ǩ������������ַ���, ��Ϊ null, ��
		//����� getJspContext().getOut(), �������ҳ����.
		
		//1. ���� StringWriter �õ���ǩ�������.
		StringWriter sw = new StringWriter();
		bodyContent.invoke(sw);
		
		//2. �ѱ�ǩ������ݶ���Ϊ��д
		String content = sw.toString().toUpperCase();
		
		//3. ��ȡ JSP ҳ��� out ��������, �����ҳ����
		getJspContext().getOut().print(content);
	}
	
}
