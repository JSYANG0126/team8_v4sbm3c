<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="memno" value="${Param.memno }" />
<c:set var="tinfo" value="${theaterVO.tinfo }" />
<c:set var="theaterno" value="${theaterVO.theaterno }" />
<c:set var="tdate" value="${theaterVO.tdate }" />
<c:set var="map" value="${theaterVO.map }" />

 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>영화 추천 블로그</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head> 
 
<body>
<c:import url="/menu/top.do" />
  <DIV class='title_line'>${theaterVO.tname }</A></DIV>

  <aside class="aside_right">

    <c:if test="${sessionScope.id != null }">
      <a href="./update.do?theaterno=${theaterno }">글 수정</a>
      <span class='menu_divide' >│</span>
      <a href="./map.do?theaterno=${theaterno}">지도</a>
      <span class='menu_divide' >│</span>
      <a href="./delete.do?contentsno=${contentsno}">삭제</a>  
      <span class='menu_divide' >│</span>
    </c:if>
    <a href='./list_all.do'>목록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <div style="width: 100%; word-break: break-all;">
          <span style="font-size: 1.5em; font-weight: bold;">${title }</span>
          ${tinfo }<br>
          <span style="font-size: 1em;"> ${tdate }</span><br>
        </div>
      </li>
      
      <c:if test="${map.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style='text-align: center; width:640px; height: 360px; margin: 0px auto;'>
            ${map }
          </DIV>
        </li>
      </c:if>

    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

