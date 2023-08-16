package com.model2.mvc.framework;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources");
		mapper=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		
		String url = request.getRequestURI();
		//System.out.println("url �Ľ� :"+url);
		String contextPath = request.getContextPath();
		//System.out.println("contextPath �Ľ� :"+contextPath);
		String path = url.substring(contextPath.length());
		System.out.println(path);
		
		try{
			Action action = mapper.getAction(path);
			//map�� �Ľ��� �н��� �׼� ��������
			action.setServletContext(getServletContext());
			//�������ؽ�Ʈ ����
			//System.out.println("������������Ʈ ����");
			String resultPage=action.execute(request, response);
			//System.out.println(request);
			//System.out.println(response);
			//System.out.println("100000"+resultPage);
			String result=resultPage.substring(resultPage.indexOf(":")+1);
			
			if(resultPage.startsWith("forward:"))
				HttpUtil.forward(request, response, result);
			else
				HttpUtil.redirect(response, result);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}