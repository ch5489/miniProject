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
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
 <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
// 검색 / page 두 가지 경우 모두 Form 전송을 위해 JavaScript를 이용하는 것이다!!
<!--
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
	
	var mouseX, mouseY;
        $(document).on("mousemove",function(e) {
            mouseX = e.pageX;
            mouseY = e.pageY;
        })
	
	$( ".ct_list_pop td:nth-child(9)" ).on("mouseenter",function () {
		var userId = $(this).closest("tr").find("td:eq(2)").text().trim();;
		
		$.ajax( 
				{
					url : "/user/json/getUser/"+userId ,
					method : "GET" ,
					dataType : "json" ,
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					success : function(JSONData , status) {
						var messageText = "휴대폰 : "+JSONData.phone+"<br>"
										+"주소 : "+JSONData.addr+"<br>"
										+"ROLE : "+JSONData.role+"<br>"
										+"등록일 : "+JSONData.regDateString+"<br>"+"<br>"
										+"= 상세정보 확인 및 수정은 회원 ID를 클릭해주세요 =";
						
						   var dialog = $( "#dialog-message" ).dialog({
							   
							   modal: true,
						      buttons: {
						        Ok: function() {
						          $( this ).dialog( "close" );
						        }
						      },
						   position: {
				                my: "left top",
				                at: "left+" + mouseX + " top+" + mouseY,
				                of: window
				            }
						    });
						   dialog.html(messageText);
						   dialog.mouseleave(function(){
							   dialog.dialog("close");
						   })
						  
					}
			});
	})
	
	
	let lastScroll = 0;

        $(window).on('scroll',function(){
            //현재 높이 저장
            console.log("이제 여기서 데이터를 더 불러와 주면 된다.11111");
            var currentScroll = $(this).scrollTop();
            //전체 문서의 높이
            var documentHeight = $(document).height();

            //(현재 화면상단 + 현재 화면 높이)
            var nowHeight = $(this).scrollTop() + $(window).height();


            //스크롤이 아래로 내려갔을때만 해당 이벤트 진행.
            if(currentScroll > lastScroll){

                //nowHeight을 통해 현재 화면의 끝이 어디까지 내려왔는지 파악가능 
                //즉 전체 문서의 높이에 일정량 근접했을때 글 더 불러오기)
                if(documentHeight < (nowHeight + (documentHeight*0.1))){
                    console.log("이제 여기서 데이터를 더 불러와 주면 된다.");
                }
            }

            //현재위치 최신화
            lastScroll = currentScroll;

        });
	
	
})


-->
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
		<td class="ct_line02"></td>
		<td class="ct_list_b">추가 회원정보 보기<br>
			<h7 >(마우스를 올려 확인해주세요)</h7></td>		
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
			<td></td>
			<td align="center">√</td>		
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
</div>

<div id="dialog-message" title="회원정보 추가보기">
  
  
</div>


</body>
</html>