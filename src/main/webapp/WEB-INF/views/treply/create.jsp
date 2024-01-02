<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Movie world</title>

<link href="/css/style.css" rel="Stylesheet" type="text/css">

<!-- jQuery 추가 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>

<body>
<c:import url="/menu/top.do" />

<div class='title_line'>댓글</div>

<div class='content_body'>
    <aside class="aside_right">
        <a href="./create.do">등록</a>
        <span class='menu_divide' >│</span>
        <a href="javascript:location.reload();">새로고침</a>
    </aside> 

    <div class='menu_line'></div>

    <form name='frm' method='post' action='./create.do' enctype="multipart/form-data">
        <input type='hidden' name='theaterno' value='${theaterno}'>
        <div>
            <label>댓글</label>
            <input type='text' name='treply' value='앨리멘탈' required="required" 
                autofocus="autofocus" class="form-control" style='width: 100%;'>
        </div>
        <div>
            <label>댓글</label>
            <input type='number' name='theaterno' value='' required="required" 
                autofocus="autofocus" class="form-control" style='width: 100%;'>
        </div>
        <div class="content_body_bottom">
            <!-- 댓글 등록 버튼을 일반 버튼으로 변경 -->
            <button type="button" id="btnSubmit" class="btn btn-primary">등록</button>
            <button type="button" onclick="location.href='./list_all.do" class="btn btn-primary">목록</button>
        </div>
    </form>
</div>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>
