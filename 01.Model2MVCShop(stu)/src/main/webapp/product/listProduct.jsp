<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>

<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	
	int total=0;
	ArrayList<ProductVO> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<ProductVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageSize() ;
		if(total%searchVO.getPageSize() >0)
			totalPage += 1;
	}
	
	String menu = request.getParameter("menu");
	System.out.println("map 입니다"+map);
	
	//String pg = request.getParameter("page");
	int pg = searchVO.getPage();
	String spU = (String)request.getAttribute("pageUnit");
	int pU = Integer.parseInt(spU);
	
	
	String sK = request.getParameter("searchKeyword");
	//String sK = searchVO.getSearchKeyword();
	String sC = request.getParameter("searchCondition");
	//String sC = searchVO.getSearchCondition();
	//System.out.println(pg);
	System.out.println(sK);
	System.out.println(sC);
	
	//System.out.println(menu);
%>
<html>
<head>

<%if(menu.equals("manage")){%>
	<title>상품 관리</title>
	
<%}else{ %>
<title>상품 목록조회</title>
<%} %>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetProductList(){
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?&menu=<%=menu%>" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<%
								if (menu.equals("manage")) {
								%>
								<td width="93%" class="ct_ttl01">상품 관리</td>

								<%
								} else {
								%>
								<td width="93%" class="ct_ttl01">상품 목록조회</td>
								<%} %>

								
							</tr>
						</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<%
		if(searchVO.getSearchCondition() != null && !searchVO.getSearchCondition().equals("null")) {
			
	%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
		<%
				if(searchVO.getSearchCondition().equals("0")){
		%>
				<option value="0" selected>상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
		<%
				}else if(searchVO.getSearchCondition().equals("1")){
		%>
				<option value="0" >상품번호</option>
				<option value="1" selected>상품명</option>
				<option value="2">상품가격</option>
		<%
				}else {//if(searchVO.getSearchCondition().equals("2")){
					
		%>		<option value="0" >상품번호</option>
				<option value="1" >상품명</option>
				<option value="2" selected>상품가격</option>
				
				<%}%>
		
			</select>
			
			
							<%-- <% request.setAttribute("serachKeyword", searchVO.getSearchKeyword()); %> --%>
		<!-- </td> -->
	<%
		}else{
	%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			</select>
			
			
			<!-- <input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" > -->
			
		<!-- </td> -->
	<%
		}
	%>
		<%if(searchVO.getSearchKeyword() != null && !searchVO.getSearchKeyword().equals("null")){%>
			<input 	type="text" name="searchKeyword"  value="<%=searchVO.getSearchKeyword()%>" 
							class="ct_input_g" style="width:200px; height:19px" >
			<%}else{%>
			
			<input 	type="text" name="searchKeyword" 
							class="ct_input_g" style="width:200px; height:19px" >				
			<%} %>
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  <%= total%> 건수, 현재 <%=currentPage %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>		
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<% 	
		 int no=list.size();
		for(int i=0; i<list.size(); i++) {
			ProductVO vo = (ProductVO)list.get(i); 
	%>
	<tr class="ct_list_pop">
		<td align="center"><%=no--%></td>
		<td></td>
		<td align="left">
		
		<%if(menu.equals("manage")){%>
			<a href="/updateProductView.do?prodNo=<%=vo.getProdNo() %>&menu=manage" ><%=vo.getProdName() %></a>
	
		<%}else{ %>
			<a href="/getProduct.do?prodNo=<%=vo.getProdNo() %>&menu=search" ><%=vo.getProdName() %></a>
		<%} %>
		
			
		</td>
		<td></td>
		<td align="left"><%= vo.getPrice() %></td>
		<td></td>
		<td align="left"><%= vo.getRegDate() %></td>		
		<td></td>
		<td align="left"><%= vo.getProTranCode() %></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<%if(pg == 1 || pg == 0){ %>
		◀ 이전
		<%}else{ %>
		<a href= /listProduct.do?page=<%=pg-1%>&&menu=<%=menu%>&searchKeyword=<%=sK%>&searchCondition=<%=sC%>""/>◀ 이전
			<%-- href="/listProduct.do?page=<%=i%>&&menu=<%=menu%>&searchKeyword=<%=sK%>&searchCondition=<%=sC%>" --%>
			
			<%} %>
		<%
			for(int i = pg ;i< pg + pU ;i++){
		%>
				<a href="/listProduct.do?page=<%=i%>&&menu=<%=menu%>&searchKeyword=<%=sK%>&searchCondition=<%=sC%>"><%=i %></a>
		<%		
			if(i==totalPage){break;}
			}
		%>
		
		<%if(pg == totalPage){ %>
		이후 ▶
		<%}else { %>
		<a href= /listProduct.do?page=<%=pg+1%>&&menu=<%=menu%>&searchKeyword=<%=sK%>&searchCondition=<%=sC%>""/>이후 ▶
		<%} %>	
		<%-- 화살표 구현 및 페이지 넘어가는거 멈추게 <% int%> int --%>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>
