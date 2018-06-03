package com.atguigu.javaweb;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ParentTag extends SimpleTagSupport {
	
	private String name = "www.ATGUIGU.com";
	
	public String getName() {
		return name;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("����ǩ�ı�ǩ�������� name: " + name);
		getJspBody().invoke(null);
	}
	
}
