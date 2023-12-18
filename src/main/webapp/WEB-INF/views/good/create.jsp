<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head>
 
<body>
<c:import url="/menu/top.do" />
 
	<div class="title_line" style='width: 50%;'>좋아요 삭제</div>
  
  <form name='frm' method='post' action='./create.do'>
    <table class='table' style='width: 50%; margin: 0px auto;'>
      <tr>
        <th>아이디</th>
        <td><input type='text' name='id' value='user1' style='width: 80%;'></td>
      </tr>
      <tr>
        <th>패스워드</th>
        <td><input type='password' name='passwd' value='1234' style='width: 80%;'></td>
      </tr>
  
    </table>
    
    <div class='bottom_menu'>
       <button type='submit'>조회</button>
    </div>
  </form>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>