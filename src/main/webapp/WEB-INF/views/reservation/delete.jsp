<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dev.mvc.reservation.ReservationVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/reservation/delete.do</title>
  
</head>

<body>
<c:set var="reservationno" value="${reservationVO.reservationno }" />
<c:set var="tname" value="${reservationVO.tname }" />

<jsp:include page="../menu/top.jsp" flush='false' />
<div class='title_line'>예매 페이지 > ${name }삭제</div>  
  <div id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <form name='frm_delete' id='frm_delete' method='post' action='./delete.do'>
      <input type="hidden" name="reservationno" value=${reservationno }>
      
      <div class="msg_warning">페이지를 삭제하면 복구 할 수 없습니다.</div>
      <label>페이지 이름</label>: ${tname }
  
      <button type="submit" id='submit' class="btn btn-sm" style="background-color: #323232; color: white; style='height': 28px; margin-bottom: 5px;">삭제</button>
      <button type="button" onclick="location.href='/cate/list_all.do'" class='btn btn-secondary btn-sm' style='height: 28px; margin-bottom: 5px;'>취소</button>
    </form>
  </div>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>