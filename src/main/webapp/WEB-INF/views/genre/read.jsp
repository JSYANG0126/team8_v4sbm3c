<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dev.mvc.genre.GenreVO" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/genre/read.do?genreno=1</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<div class='title_line'>카테고리 조회</div>
  <%
  GenreVO genreVO = (GenreVO)request.getAttribute("genreVO");  
  %>
  <div class="container mt-3">
    <ul class="list-group list-group-flush">
      <li class="list-group-item">번호: <%=genreVO.getGenreno() %></li>
      <li class="list-group-item">이름: <%=genreVO.getName() %></li>
      <li class="list-group-item">등록 글수: <%=genreVO.getCnt() %></li>
      <li class="list-group-item">등록일: <%=genreVO.getRdate() %></li>
    </ul>
  </div>

  <div class="content_body_bottom">
    <button type="button" onclick="location.href='./create.do'" class="btn btn-secondary btn-sm">등록</button>
    <button type="button" onclick="location.href='./list_all.do'" class="btn btn-secondary btn-sm">목록</button> 
  </div>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>

