<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9091/</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
  <div style='width: 100%;'> <%-- 갤러리 Layout 시작 --%>
    <c:forEach var="contentsVO" items="${recom_good }" varStatus="status">
      <c:set var="title" value="${movieVO.title.trim() }" />
      <c:set var="content" value="${movieVO.content.trim() }" />
      <c:set var="genreno" value="${movieVO.genreno }" />
      <c:set var="contentsno" value="${movieVO.contentsno }" />
      <c:set var="thumb1" value="${movieVO.thumb1 }" />
      <c:set var="size1" value="${movieVO.size1 }" />
        
      <!-- 4기준 하나의 이미지 widrh, 24% * 4 = 96% -->
      <!-- 5기준 하나의 이미지 widrh, 19.2% * 5 = 96% -->
      <!-- 5기준 하나의 이미지 widrh, 16% * 6 = 96% -->
      <div onclick="location.href='/movie/read.do?movieno=${movieno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }'" class='hover'  
             style='width: 13%; height: 168px; float: left; margin: 0.5%; padding: 0.1%; background-color: #EEEFFF; text-align: left;'>
        
        <c:choose> 
          <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
            <%-- registry.addResourceHandler("/contents/storage/**").addResourceLocations("file:///" +  Contents.getUploadDir()); --%>
            <img src="/movie/storage/${thumb1 }" style="width: 100%; height: 140px;">
          </c:when>
          <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/contents/images/none1.png -->
            <IMG src="/movie/images/none1.png" style="width: 100%; height: 140px;">
          </c:otherwise>
        </c:choose>
        
        <strong>
          <span style="font-size: 0.8em;">
            <c:choose>
              <c:when test="${title.length() > 20 }"> <%-- 20 이상이면 20자만 출력, 공백:  6자 --%>
                ${title.substring(0, 20)}...
              </c:when>
              <c:when test="${title.length() <= 20 }">
                ${title}
              </c:when>
            </c:choose>
          </span>
        </strong>
        <br>
       
      </div>
    </c:forEach>
</div>
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>