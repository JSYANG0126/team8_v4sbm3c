<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>영화 리뷰 블로그</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
 
</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>예매 페이지 링크</div>
  
  <aside class="aside_right">
    <c:choose>
	    <c:when test="${sessionScope.manager_id != null}">
	        <a href="./create.do">등록</a>
			    <span class='menu_divide' >│</span>
			    <a href="javascript:location.reload();">새로고침</a>
	    </c:when>
	    <c:otherwise>
	        <a href="javascript:location.reload();">새로고침</a>
	    </c:otherwise>
    </c:choose>
  </aside>
  <div class="menu_line"></div> 
  
  <table class="table table-hover">
    <colgroup>
        <col style='width: 10%;'/>
        <col style='width: 40%;'/>
        <col style='width: 30%;'/>    
        <col style='width: 20%;'/>
      </colgroup>
      <thead>
        <tr>
          <th class="th_bs">순서</th>
          <th class="th_bs">홈페이지</th>
          <th class="th_bs">URL</th>
          <th class="th_bs">비고</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="reservationVO" items="${list }" varStatus="info">
          <c:set var="reservationno" value="${reservationVO.reservationno }" />
          <c:set var="link" value="${reservationVO.link }" />
          <tr>
            <td><a style="display: block; text-align:center;">${reservationno }</a></td>
            <td><a style="display: block; text-align:center;">${reservationVO.tname }</a></td>
           <td style="text-align: center;"><a href="javascript:void(0);" onclick="openLink('${link}')">${link}</a></td>
						
						<script>
						    function openLink(link) {
						        // 주소가 http:// 또는 https://로 시작하는지 확인
						        if (!link.startsWith('http://') && !link.startsWith('https://')) {
						            link = 'https://' + link;
						        }
						        window.open(link, '_blank');
						    }
						</script>
            
            <td class="td_bs">
              <c:choose>
					      <c:when test="${sessionScope.manager_id != null}">
					         <a href="./update.do?reservationno=${reservationno }" title="수정"><img src="/reservation/images/update.png" class="icon"></a>
                   <a href="./delete.do?reservationno=${reservationno }" title="삭제"><img src="/reservation/images/delete.png" class="icon"></a>
					      </c:when>
					      <c:otherwise>
					         <a href="../qna/list_all.do">링크 문제시 문의</a>
					      </c:otherwise>
				      </c:choose>
             
            </td>
          </tr>
        </c:forEach>
      </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
