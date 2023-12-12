<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/theater/list_all.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>영화관 목록</div>
  
  <aside class="aside_right">
    <c:if test="${sessionScope.id != null }">
      <a href="./create.do?memno=${sessionScope.memno }">등록</a>
      <span class='menu_divide' >│</span>
    </c:if>
    <a href="javascript:location.reload();">새로고침</a>
  </aside>
  <div class="menu_line"></div> 
  
  <table class="table table-hover">
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 10%;"></col>
      </colgroup>
      <thead>
        <tr>
          <th style='text-align: center;'>추천인 번호</th>
          <th style='text-align: center;'>추천인 이름</th>
          <th style='text-align: center;'>영화관 소개</th>
          <th style='text-align: center;'>기타</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="theaterVO" items="${list }" varStatus="info">
          <c:set var="theaterno" value="${theaterVO.theaterno }" />
          <c:set var="memno" value="${theaterVO.memno }" />
          <c:set var="mname" value="${memVO.mname }" />
    
          <tr onclick="location.href='./read.do?theaterno=${theaterno}'" style="cursor: pointer;">
            <td class="td_bs">
               <span style="font-weight: bold;">${memno }</span><br>
            </td>
            <td class="td_bs">
               <span style="font-weight: bold;">${mname }</span><br>
            </td>
            <td class="td_bs_left">
              <span style="font-weight: bold;">${theaterVO.tname }</span><br>
              <c:choose>
                <c:when test="${theaterVO.tinfo.length() > 160 }">
                  ${theaterVO.tinfo.substring(0, 160) }...
                </c:when>
                <c:otherwise>
                  ${theaterVO.tinfo }
                </c:otherwise>
              </c:choose>
              (${theaterVO.tdate.substring(0, 16) })
            </td>
            <td class="td_bs">
              <c:if test="${sessionScope.memno == theaterVO.memno }">
					      <a href="./update.do?theaterno=${theaterno }" title="수정"><img src="/theater/images/update.png" class="icon"></a>
                <a href="./delete.do?theaterno=${theaterno }" title="삭제"><img src="/theater/images/delete.png" class="icon"></a>
					    </c:if>  
            </td>
          </tr>
        </c:forEach>
    </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>