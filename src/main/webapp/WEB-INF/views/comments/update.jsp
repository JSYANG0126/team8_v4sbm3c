<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/qna/update.do?genreno=1</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<div class='title_line'>댓글 수정</div>

  
<form name='frm' method='post' action='/comments/update.do'>
  <input type='hidden' name='commentno' value="${commentsVO.commentno }">
  <input type='hidden' name='movieno' value="${commentsVO.movieno }">
  <div>
    <label>댓글</label>
    <input type="text" name="reply" value="${commentsVO.reply}" required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>
  
  <div class="content_body_bottom">
    <button type="submit" class="btn btn-sm" style="background-color: #323232; color: white;">저장</button>
    <button type="button" onclick="history.back();" class="btn btn-sm" style="background-color: #323232; color: white;">취소</button> 
  </div>
</form>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>

