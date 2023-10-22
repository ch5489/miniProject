<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

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
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
 
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
	function fncGetProductList(currentPage){
		$("#currentPage").val(currentPage);
		$("form").attr("method","POST").attr("action","/product/listProduct?menu=${param.menu}").submit();
	}
	
	$(function () {
		$("td.ct_btn01:contains('�˻�')").on("click", function () {
			
			fncGetProductList(1);
		})
		
		/* $( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
			//Debug..
			//alert( $( this ).text() );
			//alert( $( this ).text().trim() );
			self.location ="/user/getUser?userId="+$(this).text().trim();
		}); */
		/* alert($( ".ct_list_pop td:nth-child(3)" ).html()) */
		$( ".ct_list_pop td:nth-child(3)" ).css("color" , "rgb(262,162,138)");
		$("h7").css("color" , "rgb(262,162,138)");
		
		/* alert($( ".ct_list_pop a" ).html().trim()) 
		*/
		$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
		
		/* $( ".ct_list_pop td:nth-child(3)" ).on("click",function(){
			
		}) */
		$(".ct_list_pop td:nth-child(3)").on("click",function(){
			//alert($(this).text().trim())
			var toName = $(this).text().trim()
			//alert($("input:hidden[name="+toName+"]").val())
			
			 if(${param.menu == 'manage'}){
			
				self.location="/product/updateProduct?prodNo="+$('input:hidden[name="'+toName+'"]').val()+"&menu=${param.menu}";
			}else{
				self.location="/product/getProduct?prodNo="+$('input:hidden[name="'+toName+'"]').val()+"&menu=${param.menu}";
			} 
			
			
		})
		
		
		$( "input:text[name='searchKeyword']" ).on("keyup",function(){
			
		var prod = ($("option:selected").val() == 0)? 'prodNo':	($("option:selected").val() == 1)? 'prodName' :($("option:selected").val() == 2)? 'price' :"";
			
		var availableTags = new Array();
			$.ajax( 
					{
						url : "/product/json/listProductAuto?searchKeyword="+$( "input:text[name='searchKeyword']" ).val()+"&searchCondition="+$( "option:selected" ).val() ,
						method : "GET" ,
						dataType : "json" ,
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData , status) {
							$.each(JSONData, function(index,value){
								//availableTags.push(JSONData[index].prodName); 
								availableTags.push(JSONData[index][prod].toString()); 
								//alert(prod)
								//alert(availableTags) 
							})
							
							$( "input:text[name='searchKeyword']" ).autocomplete({
							      source: availableTags
							    });
							  
							  
						}
						 
				});
		    
			
		
		})
	})
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form >

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
		if(search.getSearchCondition() != null && !search.getSearchCondition().equals("null")) {
			
	%> --%>
	<%-- <% System.out.println("search.getSearchCondition ; "+search.getSearchCondition()); %> --%>
	<select name="searchCondition" class="ct_input_g" style="width:80px">
	<c:choose>
		<c:when test="${search.searchCondition == '0'}">
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
		</c:when>
		
		<c:when test="${search.searchCondition == '1'}">
				<option value="0" >��ǰ��ȣ</option>
				<option value="1" selected>��ǰ��</option>
				<option value="2">��ǰ����</option>
		</c:when>
		
		<c:when test="${search.searchCondition == '2'}">		
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
	
	
		<%-- <%if(search.getSearchKeyword() != null && !search.getSearchKeyword().equals("null")){%> --%>
		<c:choose>
		<c:when test="${ !empty search.searchKeyword }">
			<input 	type="text" name="searchKeyword"  value="${search.searchKeyword }" 
							class="ct_input_g" style="width:200px; height:19px" >
		</c:when>
			<%-- <%}else{%> --%>
		<c:otherwise>
			<input 	type="text" name="searchKeyword" value = ""
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
						�˻�
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">��ü ${resultPage.totalCount } �Ǽ�, ����
						${resultPage.currentPage } ������</td>

					<%--  �߰�����, ${search.searchKeyword} --%>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">��ǰ��
					<br> <h7>(��ǰ�� click:������)</h7>
					</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�������</td>
					<td class="ct_list_b"></td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				<c:forEach var="list" items="${ list}" begin="0" step="1"
					varStatus="status">

					<tr class="ct_list_pop">
						<td align="center">${status.count }</td>

						<td></td>
						<td align="left">
									${list.prodName }
								</td>
						<td></td>
						<td align="left">${list.price }</td>
						<td></td>
						<td align="left">${list.regDate }</td>
						<td></td>
						<td align="left">
						<c:choose>
							<c:when test="${list.proTranCode eq '�Ǹ���'}">
								${list.proTranCode }
							</c:when>
							<c:otherwise>	
								�ǸſϷ�
							</c:otherwise>
						</c:choose> 
						<c:if test="${param.menu == 'manage'}">
							<c:if test="${list.proTranCode.trim() == '1' }">
										/	<a href="/purchase/updateTranCode?prodNo=${list.prodNo }&tranCode=2&currentPage=${resultPage.currentPage}&menu=${param.menu}">	����ϱ�</a>
							</c:if>
						</c:if>
						</td>
						<input type="hidden" name = '${list.prodName }'  value = ${list.prodNo }>
						
					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</c:forEach>

			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
		
		
		<c:choose>
			<c:when test="${empty resultPage.currentPage || resultPage.currentPage == 1}">
			�� ����
			</c:when>
			<c:when test="${resultPage.currentPage-resultPage.pageUnit <= 0}">
			<a href= "javascript:fncGetProductList('1')">
			�� ����</a>
			</c:when>
		<c:otherwise>
			<a href= "javascript:fncGetProductList('${resultPage.currentPage -resultPage.pageUnit }')">
			�� ����</a>
			</c:otherwise>
		</c:choose>
		
		<c:set var = "pageLoop" value = "false"/>
		<c:forEach var ="i" begin = "${resultPage.currentPage }"  end = "${resultPage.currentPage + resultPage.pageUnit -1}" step = "1">
<!-- 	loop ���߰� �ۿ� ������ �ϳ� �ξ� �������� �۵����ϰ� �� = break ���� ��� -->
			<c:if test="${not pageLoop }">
				<a href="javascript:fncGetProductList('${ i }')">${i }</a>
				<c:if test="${i == resultPage.maxPage}">
					<c:set var = "pageLoop" value = "true"/>
				</c:if>
			</c:if>
			
		</c:forEach>
		
		<c:choose>
			<c:when test="${resultPage.currentPage == resultPage.maxPage || resultPage.currentPage+resultPage.pageUnit >= resultPage.maxPage}">
			���� ��
			</c:when>
			<c:otherwise>
			<a href= "javascript:fncGetProductList('${resultPage.endUnitPage+1}')">
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
