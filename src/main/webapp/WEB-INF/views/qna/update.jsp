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
<div class='title_line'>질문 수정</div>

  
<form name='frm' method='post' action='/qna/update.do'>
  <input type='hidden' name='qnano' value="${param.qnano }">
  <div>
    <label>제목</label>
    <input type="text" name="qnatitle" value="${qnaVO.qnatitle }" required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>

  <div>
	  <label>문의사항</label>
	  <textarea name='qnainfo' required="required" class="form-control" rows="12" style='width: 100%;'>${qnaVO.qnainfo} </textarea>
	</div>
  
  <div class="content_body_bottom">
    <button type="submit" class="btn btn-sm" style="background-color: #323232; color: white;">저장</button>
    <button type="button" onclick="history.back();" class="btn btn-sm" style="background-color: #323232; color: white;">취소</button> 
  </div>
</form>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>

