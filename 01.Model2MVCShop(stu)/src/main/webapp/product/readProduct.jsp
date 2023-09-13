<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%-- <%@ page import="com.model2.mvc.service.product.product.*" %>
<%@ page import="com.model2.mvc.service.user.product.*" %> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- <%
	Productproduct product=(Productproduct)request.getAttribute("product");
	String menu = request.getParameter("menu");
	Userproduct userproduct = (Userproduct)session.getAttribute("user");
	//System.out.println(userproduct);
	
%> --%>









<html>
<head>
<title>��ǰ����ȸ</title>



<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>

<script type="text/javascript"  src="../javascript/calendar.js"></script>
<script type="text/javascript">
$(function () {
	
	$("td.ct_btn01:contains('Ȯ��')").on("click", function(){
		/* self.location="/product/listProduct?menu=manage"; */
		$("form").attr("method","POST").attr("action","/product/listProduct?menu=manage").submit();
	})
	
	$("td.ct_btn01:contains('����')").on("click", function(){
		history.go(-1)
	})
	$("img[src='/images/uploadFiles/${product.fileName}']").css({"width": "200px"})
	
})
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
<form  >

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" 	width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">��ǰ����ȸ</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 13px;">
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			��ǰ��ȣ <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105" >${product.prodNo}</td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			��ǰ�� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105" >${product.prodName}</td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">��ǰ�̹��� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/></td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<!-- ���̺� ���� -->
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="26">
				
						 <img src="/images/uploadFiles/${product.fileName }"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>

	<tr>
		<td width="104" class="ct_write">
			��ǰ������ <img  src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${product.prodDetail }</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>

	<tr>
		<td width="104" class="ct_write">
			��������
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${product.manuDate}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			����
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${product.price}</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			������� 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105" >${product.regDate}</td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>					
				
<%-- <%if(userproduct != null) {%> --%> 
					<c:if test="${user != null}">
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
<%-- <%}else if(menu.equals("search")){ %> --%>
					<c:if test="${param.menu == 'search'}">
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="/addPurchaseView.do?prodNo=${product.prodNo }">����</a>
					</td><td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td></c:if>
					
					<c:if test="${param.menu == 'ok'}">
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						Ȯ��</td><td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					</c:if>
<%-- <%} %> --%>
					
					</c:if> 
<%-- <%} %> --%>	<td width="30"></td>	
					
					
					
					<%-- <%}else{ %> --%>
					<c:if test="${param.menu =='search'}">
					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01"	 style="padding-top: 3px;">
					����
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					</c:if>
					
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
