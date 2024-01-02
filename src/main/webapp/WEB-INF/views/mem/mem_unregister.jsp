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
    회원 탈퇴
  </div>

  <aside class="aside_right">
    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span> 
    <a href='./create.do'>회원 가입</a>
  </aside> 
 
  <div class='menu_line'></div>
 

  <div class='message'>
    <script>
  function unregisterMember(memno) {
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = '/mem/mem_unregister.do'; // 탈퇴를 처리할 컨트롤러 URL

    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'memno'; // memno 파라미터
    input.value = memno; // memno 값

    var passwdInput = document.createElement('input');
    passwdInput.type = 'password';
    passwdInput.name = 'passwd'; // passwd 파라미터
    passwdInput.value = document.getElementById('passwd').value; // 패스워드 값

    form.appendChild(input);
    form.appendChild(passwdInput);

    document.body.appendChild(form);
    form.submit();
  }
</script>


'${memVO.mname }(${memVO.id })' 님, 정말로 탈퇴 하시겠습니까?<br><br>
      탈퇴를 원하시면 패스워드를 입력해주세요.<br>
      
<div class="form-group">
  <label style="margin-bottom:7px">
    <input type='password' class="form-control form-control-sm" name='passwd' id='passwd' value='' required="required" placeholder="패스워드를 입력해주세요.">
  </label>
</div>  
      
<button type="button" onclick="unregisterMember(${memVO.memno});" class="btn btn-sm" style="background-color: #323232; color: white;">탈퇴</button>
<button type="button" onclick="history.back();" class="btn btn-sm" style="background-color: #323232; color: white;">취소(목록)</button>
  </div>
  </body>
</html>