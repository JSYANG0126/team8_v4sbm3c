<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>영화리뷰 블로그</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
  
</head>
<body>
<c:import url="/menu/top.do" />

    <div class='title_line'>로그인 내역</div>
    
    <table class="table table-hover">
    <colgroup>
        <col style='width: 10%;'/>
        <col style='width: 15%;'/>
        <col style='width: 25%;'/>    
        <col style='width: 30%;'/>
        <col style='width: 15%;'/>
      </colgroup>
      <thead>
        <tr>
          <th class="th_bs">순서</th>
          <th class="th_bs">회원번호</th>
          <th class="th_bs">ip주소</th>
          <th class="th_bs">최근 접속일시</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="mloginVO" items="${list }" varStatus="info">
          <c:set var="mloginno" value="${mloginVO.mloginno }" />
          <c:set var="memno" value="${mloginVO.memno }" />
          <c:set var="ip" value="${mloginVO.ip }" />
          <c:set var="logindate" value="${mloginVO.logindate }" />
    
          <tr>
            <td class="td_bs">${mloginno }</td>
            <td class="td_bs">${memno }</a></td>
            <td class="td_bs">${ip }</td>
            <td class="td_bs">${logindate }</td>
          </tr>
        </c:forEach>
      </tbody>
      
  </table>
  
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>