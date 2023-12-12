<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dev.mvc.reservation.ReservationVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/chat/delete.do</title>
  
</head>

<body>
<c:set var="chatno" value="${chatVO.chatno }" />
<c:set var="msg" value="${chatVO.msg }" />

<c:import url="/menu/top.do" />

<div class='title_line'>채팅 내용 삭제</div>  
  <div id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <form name='frm_delete' id='frm_delete' method='post' action='./delete.do'>
      <input type="hidden" name="chatno" value=${chatno }>
      
      <div class="msg_warning">채팅 내용 삭제하면 복구 할 수 없습니다.</div>
      <label>내용</label>: ${msg }
  
      <button type="submit" id='submit' class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>삭제</button>
      <button type="button" onclick="location.href='/cate/list_all.do'" class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>취소</button>
    </form>
  </div>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>