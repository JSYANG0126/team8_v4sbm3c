<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/chat/list.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
 
</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>채팅 목록</div>
  
  <aside class="aside_right">
	        <a href="javascript:location.reload();">새로고침</a>
  </aside>
  <div class="menu_line"></div> 
  
  <table class="table table-hover">
	    <colgroup>
	       <col style='width: 10%;'/>
	       <col style='width: 50%;'/>
	       <col style='width: 30%;'/>
	       <col style='width: 10%;'/>
	    </colgroup>
      <thead>
	        <tr>
	          <th class="th_bs">채팅 번호</th>
	          <th class="th_bs">채팅 내용</th>
	          <th class="th_bs">채팅 일자</th>
	          <th class="th_bs">비고</th>
	        </tr>
      </thead>
      <tbody>
        <c:forEach var="chatVO" items="${list }" varStatus="info">
          <c:set var="chatno" value="${chatVO.chatno }" />
          <c:set var="msg" value="${chatVO.msg }" />
          <tr>
            <td><a style="display: block; text-align:center;">${chatno }</a></td>
            <td><a style="display: block; text-align:center;">${msg }</a></td>
            <td><a style="display: block; text-align:center;">${chatVO.rdate }</a></td>            
            <td><a style="display: block; text-align:center;" href="./delete.do?chatno=${chatno }" title="삭제"><img src="/chat/images/delete.png" class="icon2" ></a></td>
          </tr>
        </c:forEach>
      </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
