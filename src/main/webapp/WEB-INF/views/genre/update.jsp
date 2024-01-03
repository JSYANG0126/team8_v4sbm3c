<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dev.mvc.genre.GenreVO" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>영화리뷰 블로그</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<div class='title_line'>카테고리 수정</div>

<%
GenreVO genreVO = (GenreVO)request.getAttribute("genreVO");
int genreno = genreVO.getGenreno();
%>
  
<form name='frm' method='post' action='/genre/update.do'>
  <input type='hidden' name='genreno' value='<%=genreno %>'>
  <div>
    <label>카테고리 이름</label>
    <input type="text" name="name" value="<%=genreVO.getName() %>" required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>

  <div style="margin-top: 20px;">
    <label>글수</label>
    <input type="text" name="cnt" value="<%=genreVO.getCnt() %>" required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>
  
  <div class="content_body_bottom">
    <button type="submit" class="btn btn-secondary btn-sm" style="background-color: #323232; color: white;">저장</button>
    <button type="button" onclick="history.back();" class="btn btn-secondary btn-sm" style="background-color: #323232; color: white;">취소</button> 
  </div>
</form>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>

