<%@ page contentType="text/html; charset=euc-kr" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
 <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
// 검색 / page 두 가지 경우 모두 Form 전송을 위해 JavaScript를 이용하는 것이다!!
<!--
function fncGetUserList(currentPage){
	$("#currentPage").val(currentPage);
	
	$("form").attr("method","POST").attr("action","/purchase/listPurchase").submit();
}

$(function () {
	$("td.ct_btn01:contains('검색')").on("click", function () {
		
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
	
	$("a:contains('물건도착')").on("click",function(){
		
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
	            // 서버에서 반환한 JSON 데이터를 data로 받아서 이후 처리
	            // 이 곳에서 필요한 데이터 처리 및 페이지 업데이트 로직을 구현
	            // 예: alert 등으로 데이터 확인
	            
	            closestTr.find('td:nth-child(9)').empty();
	            closestTr.find('td:nth-child(9)').append('구매완료, 감사합니다!!');
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
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
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
		<td colspan="11" >전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">
		회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>			
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
					<td align="left">미기입</td>	
				</c:when>
				<c:otherwise>
					<td align="left">${purchase.receiverPhone }</td>
				</c:otherwise>
			</c:choose>
			<td></td>
			<td align="left">
				<c:if test="${purchase.tranCode.trim() eq '1' }">
					결제완료, 배송준비중입니다!!
				</c:if>
				<c:if test="${purchase.tranCode.trim() eq '2' }">
					배송중, 배송완료 후 물건도착을 눌러주세요!!
				</c:if>
				<c:if test="${purchase.tranCode.trim() eq '3' }">
					구매완료, 감사합니다!!
				</c:if>
			
			</td>	
			<td></td>
			<td align="left">	
			<c:choose>
				<c:when test="${purchase.tranCode.trim() eq '2' }">
					<a >물건도착</a>
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
<!--  페이지 Navigator 끝 -->
</form>



</body>
</html>