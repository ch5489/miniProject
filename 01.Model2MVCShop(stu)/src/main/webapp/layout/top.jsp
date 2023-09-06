<%@ page contentType="text/html; charset=euc-kr" %>

<%-- <%@ page import="com.model2.mvc.service.domain.*" %>
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <%
	User vo=(User)session.getAttribute("user");
%> --%>

<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script type="text/javascript">
$(function(){
	$("td[width='115']:contains('login')").on("click",function(){
		$(window.parent.frames["rightFrame"].document.location).attr("href","/user/login");	
	})	
	$("td[width='56']:contains('logout')").on("click",function(){
		$(window.parent.frames.document.location).attr("href","/user/logout");	
	})	
})
</script>
</head>

<body topmargin="0" leftmargin="0">
 
<table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td height="10"></td>
	<td height="10" >&nbsp;</td>
  </tr>
  <tr>
    <td width="800" height="30"><h2>Model2 MVC Shop</h2></td>
  </tr>
  <tr>
    <td height="20" align="right" background="/images/img_bg.gif">
	    <table width="200" border="0" cellspacing="0" cellpadding="0">
	        <tr> 
	          <td width="115">
	          <c:if test="${ empty user }">
	          	 login   
	          </c:if>
	          </td>
	          
	          <td width="1">&nbsp;</td>
	          <td width="56">
	           <c:if test="${ !empty user }">
	           	logout  
	           </c:if>
	          
	        </td>
	        </tr>
	    </table>
    </td>
    <td height="20" background="/images/img_bg.gif">&nbsp;</td>
  </tr>
</table>

</body>
</html>
