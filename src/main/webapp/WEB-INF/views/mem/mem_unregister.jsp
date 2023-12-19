<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>

<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head> 
<body>
<c:import url="/menu/top.do" />
 
  <div class='title_line'>
    회원 삭제
  </div>

  <aside class="aside_right">
    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span> 
    <a href='./create.do'>회원 가입</a>
    <span class='menu_divide' >│</span> 
    <a href='./list.do'>목록</a>
  </aside> 
 
  <div class='menu_line'></div>
 
 
  <div class='message'>
    <form name='frm' method='post' action='./mem_unregister.do?memno=${memVO.memno }'>
      '${memVO.mname }(${memVO.id })' 님, 정말로 탈퇴 하시겠습니까?<br><br>
      탈퇴를 원하시면 패스워드를 입력해주세요.<br><br>         

      <div class="form-group">
      <label style="margin-bottom:10px">
        <input type='password' class="form-control form-control-sm" name='passwd' id='passwd' value='' required="required" placeholder="패스워드를 입력해주세요.">
      </label>
    </div>   
          
      <button type="submit" class="btn btn-secondary btn-sm">탈퇴</button>
      <button type="button" onclick="history.back();" class="btn btn-secondary btn-sm">취소(목록)</button>
   
    </form>
  </div>