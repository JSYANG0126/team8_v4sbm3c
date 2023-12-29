<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/theater/list_all.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>
    영화관
    <c:if test="${param.word.length() > 0 }">
      > 「${param.word }」 검색 ${search_count } 건
    </c:if> 
  </div>
  
  <aside class="aside_right">
    <%-- 회원으로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.id != null }">
      <a href="./create.do">등록</a>
      <span class='menu_divide' >│</span>    
    <a href="./list_by_search_paging.do?now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">목록형</a>    
    <span class='menu_divide' >│</span>
    <a href="./list_by_grid.do?now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">갤러리형</a>
      <span class='menu_divide' >│</span>
    </c:if>
    <a href="javascript:location.reload();">새로고침</a>
    
  </aside>
  
  <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_search_paging.do'>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우는 검색어를 출력 --%>
          <input type='text' name='word' id='word' value='${param.word }'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value=''>
        </c:otherwise>
      </c:choose>
      <button type='submit' class="btn btn-sm" style="background-color: #323232; color: white; padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;">검색</button>
      <c:if test="${param.word.length() > 0 }"> <%-- 검색 상태하면 '검색 취소' 버튼을 출력 --%>
        <button type='button' class="btn btn-sm" style="background-color: #323232; color: white; padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;"
                    onclick="location.href='./list_by_search_paging.do?&word='">검색 취소</button>  
      </c:if>    
    </form>
  </div>
    
  <div class="menu_line"></div> 
  
  <div style='width: 100%;'> <%-- 갤러리 Layout 시작 --%>
        <c:forEach var="theaterVO" items="${list }" varStatus="status">
          <c:set var="theaterno" value="${theaterVO.theaterno }" />
          <c:set var="tname" value="${theaterVO.tname }" />
          <c:set var="thumbimg1" value="${theaterVO.thumbimg1 }" />
          <c:set var="memno" value="${theaterVO.memno }" />
          <c:set var="mname" value="${memVO.mname }" />
          <c:set var="now_page" value="${param.now_page }" />
    
          <!-- 4기준 하나의 이미지, 24 * 4 = 96% -->
		      <!-- 5기준 하나의 이미지, 19.2 * 5 = 96% -->
		      <div onclick="location.href='./read.do?theaterno=${theaterno}&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }'" 
		             style='width: 19%; height: 200px; float: left; margin: 0.5%; padding: 0.5%; background-color: #EEEFFF; text-align: left; cursor: pointer;'>
		        
		        <c:choose> 
		          <c:when test="${thumbimg1.endsWith('jpg') || thumbimg1.endsWith('png') || thumbimg1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
		            <%-- registry.addResourceHandler("/movie/storage/**").addResourceLocations("file:///" +  Movie.getUploadDir()); --%>
		            <img src="/theater/storage/${thumbimg1 }" style="width: 100%; height: 120px;">
		          </c:when>
		          <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/movie/images/none1.png -->
		            <IMG src="/theater/images/none1.png" style="width: 100%; height: 120px;">
		          </c:otherwise>
		        </c:choose>
		        ${tname}
		        
		      </div>
		      
		      <%-- 하나의 행에 이미지를 5개씩 출력후 행 변경, index는 0부터 시작 --%>
		      <c:if test="${status.count % 5 == 0}"> 
		        <HR class='menu_line'> <%-- 줄바꿈 --%>
		      </c:if>
        </c:forEach>  
  </div>
  
  <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
