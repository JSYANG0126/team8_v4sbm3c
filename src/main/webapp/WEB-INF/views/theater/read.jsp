<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="memno1" value="${sessionScope.memno }" />
<c:set var="memno2" value="${theaterVO.memno }" />
<c:set var="tinfo" value="${theaterVO.tinfo }" />
<c:set var="theaterno" value="${theaterVO.theaterno }" />
<c:set var="tdate" value="${theaterVO.tdate }" />
<c:set var="map" value="${theaterVO.map }" />
<c:set var="thumbimg1" value="${theaterVO.thumbimg1 }" />
<c:set var="img1" value="${theaterVO.img1 }" />
<c:set var="img1saved" value="${theaterVO.img1saved }" />
<c:set var="word" value="${theaterVO.word }" />
<c:set var="size1" value="${theaterVO.size1 }" />

 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>영화 추천 블로그</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head> 
 
<body>
<c:import url="/menu/top.do" />
  <DIV class='title_line'>${theaterVO.tname } </A></DIV>

  <aside class="aside_right">

    <c:if test="${sessionScope.id != null && memno1 == memno2}">
      <a href="./update.do?theaterno=${theaterno}&now_page=${param.now_page}&word=${param.word }">글 수정</a>
      <span class='menu_divide' >│</span>
      <a href="./update_file.do?theaterno=${theaterno}&now_page=${param.now_page}">파일 수정</a>  
      <span class='menu_divide' >│</span>
      <a href="./map.do?theaterno=${theaterno}">지도</a>
      <span class='menu_divide' >│</span>
      <a href="./delete.do?theaterno=${theaterno}">삭제</a>  
      <span class='menu_divide' >│</span>
    </c:if>
    <a href='./list_all.do'>목록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">
          <c:choose>
            <c:when test="${thumbimg1.endsWith('jpg') || thumbimg1.endsWith('png') || thumbimg1.endsWith('gif')}">
              <%-- /static/movie/storage/ --%>
              <img src="/theater/storage/${img1saved }" style='width: 30%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:when>
            <c:otherwise> <!-- 기본 이미지 출력 -->
              <img src="/theater/images/none1.png" style='width: 30%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:otherwise>
          </c:choose>
     
        </DIV>
          <span style="font-size: 1.5em; font-weight: bold;">${tname }</span>
          <span style="font-size: 1em;"> ${tdate }</span><br>
          ${tinfo }
      </li>
     
      
      <li class="li_none" style="clear: both;">
        <DIV style='text-decoration: none;'>
          <br>
          검색어(키워드): ${word }
        </DIV>
      </li>

      <li class="li_none">
        <div>
          <c:if test="${img1.trim().length() > 0 }">
            첨부 파일: <a href='/download?dir=/theater/storage&filename=${img1saved}&downname=${img1}'>${img1}</a> (${size1_label}) 
            <a href='/download?dir=/theater/storage&filename=${img1saved}&downname=${file1}'><img src="/theater/images/download.png"></a>
          </c:if>
        </div>
      </li>   
      <c:if test="${map.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style='text-align: center; width:640px; height: 360px; margin: 0px auto;'>
            ${map }
          </DIV>
        </li>
      </c:if>

    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

