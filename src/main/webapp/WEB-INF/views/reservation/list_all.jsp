<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/reservation/list.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
 
</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>카테고리</div>
  
  <aside class="aside_right">
    <a href="./create.do?reservationno=${reservationVO.reservationno }">등록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside>
  <div class="menu_line"></div> 
  
  <form name='frm' method='post' action='/reservation/create.do'>
    <div style="text-align: center;">
      <label>페이지 이름</label>
      <input type="text" name="name" value="" required="required" autofocus="autofocus" 
                 class="" style="width: 50%">
      <button type="submit" class="btn btn-secondary btn-sm" style="height: 28px; margin-bottom: 5px;">등록</button>
      <button type="button" onclick="location.href='./list.do'" class="btn btn-secondary btn-sm" 
                  style="height: 28px; margin-bottom: 5px;">목록</button> 
    </div>
  </form>
  
  <table class="table table-hover">
    <colgroup>
        <col style='width: 10%;'/>
        <col style='width: 40%;'/>
        <col style='width: 10%;'/>    
        <col style='width: 20%;'/>
        <col style='width: 20%;'/>
      </colgroup>
      <thead>
        <tr>
          <th class="th_bs">순서</th>
          <th class="th_bs">카테고리 이름</th>
          <th class="th_bs">자료수</th>
          <th class="th_bs">등록일</th>
          <th class="th_bs">기타</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="reservationVO" items="${list }" varStatus="info">
          <c:set var="reservationno" value="${reservationVO.reservationno }" />
          <c:set var="seqno" value="${reservationVO.seqno }" />
    
          <tr>
            <td class="td_bs">${seqno }</td>
            <td><a href="../mreview/list_by_reservationno.do?reservationno=${reservationno }" style="display: block;">${reservationVO.name }</a></td>
            <td class="td_bs">${reservationVO.cnt }</td>
            <td class="td_bs">${reservationVO.rdate.substring(0, 10) }</td>
            <td class="td_bs">
              <a href="../mreview/create.do?reservationno=${reservationno }" title="등록"><img src="/reservation/images/create.png" class="icon"></a>
              <c:choose>
                <c:when test="${reservationVO.visible == 'Y'}">
                  <a href="./update_visible_n.do?reservationno=${reservationno }" title="카테고리 공개 설정"><img src="/reservation/images/show.png" class="icon"></a>
                </c:when>
                <c:otherwise>
                  <a href="./update_visible_y.do?reservationno=${reservationno }" title="카테고리 비공개 설정"><img src="/reservation/images/hide.png" class="icon"></a>
                </c:otherwise>
              </c:choose>    
              

              <a href="./update.do?reservationno=${reservationno }" title="수정"><img src="/reservation/images/update.png" class="icon"></a>
              <a href="./delete.do?reservationno=${reservationno }" title="삭제"><img src="/reservation/images/delete.png" class="icon"></a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
