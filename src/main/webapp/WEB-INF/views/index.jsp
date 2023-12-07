<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>http://localhost:9093/index.jsp</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<c:import url="/menu/top.do" />

  
 
  <c:choose>
  <c:when test="${sessionScope.id == null }">
    <div style="width: 70%; margin: 30px auto;">
      <img src="/images/web_main.jpg" style="width: 100%;">
    </div>
  </c:when>
  <c:otherwise>
    <div>
      <c:forEach var="movieVO" items="${list }" varStatus="status">
      <c:set var="title" value="${movieVO.title }" />
      <c:set var="content" value="${movieVO.content }" />
      <c:set var="genreno" value="${movieVO.genreno }" />
      <c:set var="movieno" value="${movieVO.movieno }" />
      <c:set var="thumb1" value="${movieVO.thumb1 }" />
      <c:set var="size1" value="${movieVO.size1 }" />
        
      <!-- 4기준 하나의 이미지, 24 * 4 = 96% -->
      <!-- 5기준 하나의 이미지, 19.2 * 5 = 96% -->
      <div onclick="location.href='./read.do?movieno=${movieno}&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }&genreno=${param.genreno }'" 
             style='width: 19%; height: 200px; float: left; margin: 0.5%; padding: 0.5%; background-color: #EEEFFF; text-align: left; cursor: pointer;'>
        
        <c:choose> 
          <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
            <%-- registry.addResourceHandler("/movie/storage/**").addResourceLocations("file:///" +  Movie.getUploadDir()); --%>
            <img src="/movie/storage/${thumb1 }" style="width: 100%; height: 120px;">
            <a>hello</a>
          </c:when>
          <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/movie/images/none1.png -->
            <IMG src="/movie/images/none1.png" style="width: 100%; height: 120px;">
          </c:otherwise>
        </c:choose>
        ${title }
        
      </div>
      
      <%-- 하나의 행에 이미지를 5개씩 출력후 행 변경, index는 0부터 시작 --%>
      <c:if test="${status.count % 5 == 0}"> 
        <HR class='menu_line'> <%-- 줄바꿈 --%>
      </c:if>
    </div>
    <%-- 하나의 행에 이미지를 5개씩 출력후 행 변경, index는 0부터 시작 --%>
      <c:if test="${status.count % 5 == 0}"> 
        <HR class='menu_line'> <%-- 줄바꿈 --%>
      </c:if>
      
    </c:forEach>
  </c:otherwise>
</c:choose>
  
<jsp:include page="./menu/bottom.jsp" flush='false' />   
</body>
</html>