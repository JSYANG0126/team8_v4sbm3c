<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/mem/id_find.do</title>

<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<c:import url="/menu/top.do" />
    <div class="title_line">아이디 찾기</div>
    
    <div style="width: 60%; margin: 0px auto ">
        <div class="form-group" style ="padding-bottom: 5px">
        <label>성명:
            <input type='text' class="form-control form-control-sm" name='mname' id='mname' value='홍길동' required="required" placeholder="아이디" autofocus="autofocus">
        </label>
        </div>   
    
        <div class="form-group">
        <label>번호:
            <input type='text' class="form-control form-control-sm" name='tel' id='tel' value='010-0000-0000' required="required" placeholder="전화번호" autofocus="autofocus">
        </label>
        </div>   
    
        <div class="bottom_menu">
            <button type="button" id='btn_send' onclick="send()" class="btn btn-primary btn-sm">아이디 찾기</button>
            <button type="button" onclick="history.back()" class="btn btn-primary btn-sm">취소</button>
        </div>   
    </div>
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>