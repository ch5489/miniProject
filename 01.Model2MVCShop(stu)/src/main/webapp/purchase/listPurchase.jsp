<%@ page contentType="text/html; charset=euc-kr" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
 <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
// �˻� / page �� ���� ��� ��� Form ������ ���� JavaScript�� �̿��ϴ� ���̴�!!
<!--
function fncGetUserList(currentPage){
	$("#currentPage").val(currentPage);
	
	$("form").attr("method","POST").attr("action","/purchase/listPurchase").submit();
}

$(function () {
	$("td.ct_btn01:contains('�˻�')").on("click", function () {
		
		fncGetUserList(1);
	})
	
	$( ".ct_list_pop td:nth-child(1)" ).on("click" , function() {
		//Debug..
		//alert( $( this ).text() );
		//alert( $( this ).text().trim() );
		
		self.location ="/purchase/getPurchase?tranNo="+$('input:hidden[name='+ $( this ).text().trim()+']').val();
	});
	
	$( ".ct_list_pop td:nth-child(1)" ).css("color" , "rgb(262,162,138)");
	$("h7").css("color" , "rgb(262,162,138)");
	
	$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
	
	$("a:contains('���ǵ���')").on("click",function(){
		
		var closestTr = $(this).closest('tr.ct_list_pop');
	    var hiddenInputs = closestTr.find('input[type="hidden"]');
	    let hiddenInputsV = $(hiddenInputs[1]).val()
		
		 $.ajax({
			url : "/purchase/json/updateTranCode/"+hiddenInputsV+"?tranCode=3",
			
			method : "GET" ,
			dataType : "json" ,
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success: function (JSONData, status) {
	            // �������� ��ȯ�� JSON �����͸� data�� �޾Ƽ� ���� ó��
	            // �� ������ �ʿ��� ������ ó�� �� ������ ������Ʈ ������ ����
	            // ��: alert ������ ������ Ȯ��
	            
	            closestTr.find('td:nth-child(9)').empty();
	            closestTr.find('td:nth-child(9)').append('���ſϷ�, �����մϴ�!!');
	            closestTr.find('td:nth-child(11)').remove();
				
	        }
			
		}) 
	})
	
	
})


-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/user/listUser" method="post">

<input type="hidden" id="getcurrentPage" name="currentPage" value="${resultPage.currentPage}"/>
<input type="hidden" id="menu" name="menu" value="${param.menu}"/>

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
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
		<td colspan="11" >��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage } ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">
		ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>			
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0"/>
	<c:forEach var="purchase" items="${list }">
		<c:set var="i" value="${i+1 }"/>
		<tr class="ct_list_pop">
			<td align="center">${i }</td>
			<td></td>
			<td align="left" >
				${purchase.buyer.userId }
			</td>
			<td></td>
			<td align="left">${purchase.receiverName }</td>
			<td></td>
			 <c:choose>
				<c:when test="${purchase.receiverPhone eq 'null' }">
					<td align="left">�̱���</td>	
				</c:when>
				<c:otherwise>
					<td align="left">${purchase.receiverPhone }</td>
				</c:otherwise>
			</c:choose>
			<td></td>
			<td align="left">
				<c:if test="${purchase.tranCode.trim() eq '1' }">
					�����Ϸ�, ����غ����Դϴ�!!
				</c:if>
				<c:if test="${purchase.tranCode.trim() eq '2' }">
					�����, ��ۿϷ� �� ���ǵ����� �����ּ���!!
				</c:if>
				<c:if test="${purchase.tranCode.trim() eq '3' }">
					���ſϷ�, �����մϴ�!!
				</c:if>
			
			</td>	
			<td></td>
			<td align="left">	
			<c:choose>
				<c:when test="${purchase.tranCode.trim() eq '2' }">
					<a >���ǵ���</a>
				</c:when>
			</c:choose>
			</td>
			<td></td>
			<input type="hidden" name = ${i } value = ${purchase.tranNo }>
			<input type="hidden" name = ${i } value = ${purchase.purchaseProd.prodNo }>
					
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
    	<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->
</form>



</body>
</html>