<%@ page contentType="text/html; charset=euc-kr" %>

<%-- <%@ page import="java.util.List" %>

<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>회원 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script type="text/javascript">
// 검색 / page 두 가지 경우 모두 Form 전송을 위해 JavaScript를 이용하는 것이다!!
function fncGetUserList(currentPage){
	$("#currentPage").val(currentPage);
	$("form").attr("method","POST").attr("action","/user/listUser").submit();
}

$(function () {
	$("td.ct_btn01:contains('검색')").on("click", function () {
		
		fncGetUserList(1);
	})
	
	$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
		//Debug..
		//alert( $( this ).text() );
		//alert( $( this ).text().trim() );
		self.location ="/user/getUser?userId="+$(this).text().trim();
	});
	
	$( ".ct_list_pop td:nth-child(3)" ).css("color" , "rgb(262,162,138)");
	$("h7").css("color" , "rgb(262,162,138)");
	
	$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
	//$(".ct_list_pop:nth-child(5)" ).css("background-color" , "red");
	//console.log ( $(".ct_list_pop:nth-child(1)" ).html() );
	//console.log ( $(".ct_list_pop:nth-child(2)" ).html() );
	//console.log ( $(".ct_list_pop:nth-child(3)" ).html() );
	//console.log ( $(".ct_list_pop:nth-child(4)" ).html() ); //==> ok
	//console.log ( $(".ct_list_pop:nth-child(5)" ).html() ); 
	//console.log ( $(".ct_list_pop:nth-child(6)" ).html() ); //==> ok
	//console.log ( $(".ct_list_pop:nth-child(7)" ).html() ); 
	
})
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/user/listUser" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">회원 목록조회</td>
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
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${ !empty search.searchCondition && search.searchCondition == 0 ? "selected" : ""}>회원ID</option>
				<option value="1" ${ !empty search.searchCondition && search.searchCondition == 1 ? "selected" : ""}>회원명</option>
			</select>
			<input 	type="text" name="searchKeyword"  value="${!empty search.searchKeyword ? search.searchKeyword : ""}" 
							class="ct_input_g" style="width:200px; height:19px" >
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						검색
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
		<td colspan="11" >전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">
		회원ID
			<br>
			<h7 >(id click:상세정보)</h7>
		</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">이메일</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0"/>
	<c:forEach var="user" items="${list }">
		<c:set var="i" value="${i+1 }"/>
		<tr class="ct_list_pop">
			<td align="center">${i }</td>
			<td></td>
			<td align="left">
				${user.userId }
			</td>
			<td></td>
			<td align="left">${user.userName }</td>
			<td></td>
			<td align="left">${user.email }</td>		
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>	
	</c:forEach>
	
	
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		
    	<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>