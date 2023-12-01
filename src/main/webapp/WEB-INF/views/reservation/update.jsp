<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/reservation/update.do</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<div class='title_line'>예매 페이지 수정(관리자)</div>

<form name='frm' method='post' action='/reservation/update.do'>
  <c:set var="reservationno" value="${reservationVO.reservationno }" />
  <c:set var="tname" value="${reservationVO.tname }" />
  <c:set var="link" value="${reservationVO.link }" />
  <input type='hidden' name='reservationno' value=${reservationVO.reservationno }>
  
  <div>
    <label>예메 페이지 이름</label>
    <input type="text" name="tname" value=${tname } required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>

  <div style="margin-top: 20px;">
    <label>URL</label>
    <input type="text" name="link" value=${link } required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>
  
  <div class="content_body_bottom">
    <button type="submit" class="btn btn-secondary btn-sm">저장</button>
    <button type="button" onclick="history.back();" class="btn btn-secondary btn-sm">취소</button> 
  </div>
</form>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>

