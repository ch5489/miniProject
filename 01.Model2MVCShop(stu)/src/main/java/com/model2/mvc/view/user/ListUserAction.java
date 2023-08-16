package com.model2.mvc.view.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListUserAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		SearchVO searchVO=new SearchVO();
		
		int page=1;
		if(request.getParameter("currentPage") != null)
			page=Integer.parseInt(request.getParameter("currentPage"));
		
		//System.out.println("현재 페이지:"+page);
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt( getServletContext().getInitParameter("pageUnit"));
		searchVO.setPageSize(pageSize);
		
		UserService service=new UserServiceImpl();
		Map<String,Object> map=service.getUserList(searchVO);
		
		Page resultPage =
				new Page( page, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("ListUserAction ::"+resultPage);
		
		request.setAttribute("list", map.get("list"));
		//System.out.println("map.get(\"list\")"+map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/user/listUser.jsp";
	}
}

//System.out.println(request.getParameter("page"));