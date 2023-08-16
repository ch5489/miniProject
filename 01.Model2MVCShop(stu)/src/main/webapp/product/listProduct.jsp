<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%-- <%@ page import="java.util.*"  %>
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
	System.out.println("map �Դϴ�"+map);
	
	//String pg = request.getParameter("page");
	int pg = searchVO.getPage();
	String spU = (String)request.getAttribute("pageUnit");
	int pU = Integer.parseInt(spU);
	
	
	String sK = request.getParameter("searchKeyword");
	//String sK = searchVO.getSearchKeyword();
	//String sC = request.getParameter("searchCondition");
	//String sC = searchVO.getSearchCondition();
	//System.out.println(pg);
	System.out.println(sK);
	//System.out.println(sC);
	
	//System.out.println(menu);
%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<%-- <%if(menu.equals("manage")){%> --%>
<c:if test="${param.menu == 'manage'}">
	<title>��ǰ ����</title>
</c:if>
<%-- <%}else{ %> --%>
<c:if test="${param.menu == 'search'}">
<title>��ǰ �����ȸ</title>
<%-- <%} %> --%>
</c:if>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetProductList(){
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?&menu=${param.menu }" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<%-- <%
								if (menu.equals("manage")) {
								%> --%>
								<c:if test="${param.menu == 'manage'}">
								<td width="93%" class="ct_ttl01">��ǰ ����</td>
								</c:if>
								<%-- <%
								} else {
								%> --%>
								<c:if test="${param.menu == 'search'}">
								<td width="93%" class="ct_ttl01">��ǰ �����ȸ</td>
								<%-- <%} %> --%>
								</c:if>

								
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
	<td align="right">
	<%-- <%
		if(searchVO.getSearchCondition() != null && !searchVO.getSearchCondition().equals("null")) {
			
	%> --%>
	<%-- <% System.out.println("searchVO.getSearchCondition ; "+searchVO.getSearchCondition()); %> --%>
	<select name="searchCondition" class="ct_input_g" style="width:80px">
	<c:choose>
		<c:when test="${searchVO.searchCondition == '0'}">
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
		</c:when>
		
		<c:when test="${searchVO.searchCondition == '1'}">
				<option value="0" >��ǰ��ȣ</option>
				<option value="1" selected>��ǰ��</option>
				<option value="2">��ǰ����</option>
		</c:when>
		
		<c:when test="${searchVO.searchCondition == '2'}">		
				<option value="0" >��ǰ��ȣ</option>
				<option value="1" >��ǰ��</option>
				<option value="2" selected>��ǰ����</option>
		</c:when>
		<c:otherwise>
				<option value="0">��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
		</c:otherwise>
	</c:choose>
	</select>
	<%-- <c:if test="${ searchVO.searchCondition != null}">
	
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
			<% System.out.println("���� ������ ����� ");%>
		<%
				if(searchVO.getSearchCondition().equals("0")){
		%> --%>
		<%-- 	<c:if test="${searchVO.searchCondition == '0' }">
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
			</c:if> --%>
		<%-- <%
				}else if(searchVO.getSearchCondition().equals("1")){
		%> --%>
		<%-- 	<c:if test="${searchVO.searchCondition == '1' }">
				<option value="0" >��ǰ��ȣ</option>
				<option value="1" selected>��ǰ��</option>
				<option value="2">��ǰ����</option>
			</c:if> --%>
<%-- 		<%
				}else {//if(searchVO.getSearchCondition().equals("2")){
					
		%>	 --%>	
		<%-- 	<c:if test="${searchVO.searchCondition == '2' }">
				<option value="0" >��ǰ��ȣ</option>
				<option value="1" >��ǰ��</option>
				<option value="2" selected>��ǰ����</option>
			</c:if>	
			</select>
		
		</c:if> --%>
			<%-- 	<%}%> --%>
		
			
			
			
	<%-- <% request.setAttribute("serachKeyword", searchVO.getSearchKeyword()); %> --%>
		<!-- </td> -->
	<%-- <%
		}else{
	%> --%>
<%-- 	<c:if test="${searchVO.searchCondition == null}">
	
	<% System.out.println("�Ʒ� ������ ����� ");%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
			</select>
	</c:if>	 --%>
	
	<%-- <%
		}
	%> --%>

					<!-- <input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" > -->
			
		<!-- </td> -->
	
		<%-- <%if(searchVO.getSearchKeyword() != null && !searchVO.getSearchKeyword().equals("null")){%> --%>
		<c:choose>
		<c:when test="${ !empty searchVO.searchKeyword && searchVO.searchKeyword}">
			<input 	type="text" name="searchKeyword"  value="${searchVO.searchKeyword }" 
							class="ct_input_g" style="width:200px; height:19px" >
		</c:when>
			<%-- <%}else{%> --%>
		<c:otherwise>
			<input 	type="text" name="searchKeyword" 
							class="ct_input_g" style="width:200px; height:19px" >				
		</c:otherwise>
		</c:choose>
			<%-- <%} %> --%>
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">�˻�</a>
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
		<td colspan="11" >��ü  ${map.count } �Ǽ�, ���� ${searchVO.page } ������</td> 
		
		<%--  �߰�����, ${searchVO.searchKeyword} --%>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>		
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	
	<%-- <% 	
		 int no=list.size();
		for(int i=0; i<list.size(); i++) {
			ProductVO vo = (ProductVO)list.get(i); 
	%> --%>
	<%-- <%=no--%> 
	<%-- <%if(menu.equals("manage")){%> --%>
	<%-- <%}else{ %> --%>
	<%-- <%} %> --%>
<%-- 	<% } %> --%>
	<c:forEach var = "list" items = "${ map.list}" begin = "0" step = "1" varStatus = "status">
	
	<tr class="ct_list_pop">
		<td align="center">${status.count }</td>
		
		<td></td>
		<td align="left">
		
		<c:choose>
		<c:when test="${param.menu == 'manage'}">
			<a href="/updateProductView.do?prodNo=${list.prodNo }&menu=manage">${list.prodName }</a>
		</c:when>
		
		<c:otherwise>
			<a href="/getProduct.do?prodNo=${list.prodNo }&menu=search">${list.prodName }</a>
		
		</c:otherwise>
		</c:choose>
		
			
		</td>
		<td></td>
		<td align="left">${list.price }</td>
		<td></td>
		<td align="left">${list.regDate }</td>		
		<td></td>
		<td align="left">${list.proTranCode }</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>

</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<%-- <%if(pg == 1 || pg == 0){ %>
		�� ����
		<%}else{ %>
		<a href= /listProduct.do?page=<%=pg-1%>&&menu=<%=menu%>&searchKeyword=<%=sK%>&searchCondition=<%=searchVO.getSearchCondition()%>>
		�� ����</a>
			href="/listProduct.do?page=<%=i%>&&menu=<%=menu%>&searchKeyword=<%=sK%>&searchCondition=<%=sC%>"
			
			<%} %>
		<%
			for(int i = pg ;i< pg + pU ;i++){
		%>
				<a href="/listProduct.do?page=<%=i%>&&menu=<%=menu%>&searchKeyword=<%=sK%>&searchCondition=<%=searchVO.getSearchCondition()%>"><%=i %></a>
		<%		
			if(i==totalPage){break;}
			}
		%>
		
		<%if(pg == totalPage){ %>
		���� ��
		<%}else { %>
		<a href= /listProduct.do?page=<%=pg+1%>&&menu=<%=menu%>&searchKeyword=<%=sK%>&searchCondition=<%=searchVO.getSearchCondition()%>>
		���� ��</a>
		<%} %>	 --%>
		
		<c:choose>
		<c:when test="${empty searchVO.page || searchVO.page == 1}">
		�� ����
		</c:when>
		<c:when test="${searchVO.page-pageUnit <= 0}">
		<a href= "/listProduct.do?page=1&&menu=${param.menu }&searchKeyword=${searchVO.searchKeyword }&searchCondition=${searchVO.searchCondition }">
		�� ����</a>
		</c:when>
		<c:otherwise>
		<a href= "/listProduct.do?page=${searchVO.page -pageUnit }&&menu=${param.menu }&searchKeyword=${searchVO.searchKeyword }&searchCondition=${searchVO.searchCondition }">
		�� ����</a>
		</c:otherwise>
		</c:choose>
		
		<c:set var = "pageLoop" value = "false"/>
		<c:forEach var ="i" begin = "${searchVO.page }"  end = "${searchVO.page + pageUnit -1}" step = "1">
<!-- 	loop ���߰� �ۿ� ������ �ϳ� �ξ� �������� �۵����ϰ� �� = break ���� ��� -->
			<c:if test="${not pageLoop }">
				<a href="/listProduct.do?page=${i }&&menu=${param.menu }&searchKeyword=${searchVO.searchKeyword }&searchCondition=${searchVO.searchCondition }">${i }</a>
				<c:if test="${i == searchVO.allPageSize}">
					<c:set var = "pageLoop" value = "true"/>
				</c:if>
			</c:if>
			
		</c:forEach>
		
		<c:choose>
		<c:when test="${searchVO.page == searchVO.allPageSize || searchVO.page+pageUnit >= searchVO.allPageSize}">
		���� ��
		</c:when>
		<c:otherwise>
		<a href= "/listProduct.do?page=${searchVO.page +pageUnit }&&menu=${param.menu }&searchKeyword=${searchVO.searchKeyword }&searchCondition=${searchVO.searchCondition }">
		���� ��</a>
		</c:otherwise>
		</c:choose>
		
		
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->
</form>
</div>

</body>
</html>
