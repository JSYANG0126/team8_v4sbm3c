<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>영화리뷰 블로그</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>
    ${genreVO.name }
    <c:if test="${param.word.length() > 0 }">
      > 「${param.word }」 검색 ${search_count } 건
    </c:if> 
  </div>
  
  <aside class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.manager_id != null }">
      <a href="./create.do?genreno=${genreVO.genreno }">등록</a>
      <span class='menu_divide' >│</span>
    </c:if>
    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span>    
    <a href="./list_by_genreno.do?genreno=${param.genreno }&now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">목록형</a>    
    <span class='menu_divide' >│</span>
    <a href="./list_by_genreno_grid.do?genreno=${param.genreno }&now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">갤러리형</a>
  </aside>
  
  <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_genreno.do'>
      <input type='hidden' name='genreno' value='${param.genreno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우는 검색어를 출력 --%>
          <input type='text' name='word' id='word' value='${param.word }'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value=''>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px; background-color: #323232; color: white;">검색</button>
      <c:if test="${param.word.length() > 0 }"> <%-- 검색 상태하면 '검색 취소' 버튼을 출력 --%>
        <button type='button' class='btn btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px; background-color: #323232; color: white;"
                    onclick="location.href='./list_by_genreno.do?genreno=${param.genreno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </div>
    
  <div class="menu_line"></div> 
  
  <table class="table table-hover">
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
    <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    </thead>
    <tbody>
        <c:forEach var="movieVO" items="${list }" varStatus="info">
          <c:set var="movieno" value="${movieVO.movieno }" />
          <c:set var="thumb1" value="${movieVO.thumb1 }" />
    
          <tr onclick="location.href='./read.do?movieno=${movieno}&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }&genreno=${param.genreno }'" style="cursor: pointer; ">
            <td>
              <c:choose>
                <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                  <%-- registry.addResourceHandler("/movie/storage/**").addResourceLocations("file:///" +  movie.getUploadDir()); --%>
                  <img src="/movie/storage/${thumb1 }" style="width: 120px; height: 90px;">
                </c:when>
                <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/movie/images/none1.png -->
                  <img src="/movie/images/none1.png" style="width: 120px; height: 90px;">
                </c:otherwise>
              </c:choose>
            </td>
            <td class="td_bs_left">
              <span style="font-weight: bold;">${movieVO.title }</span><br>
              <c:choose>
                <c:when test="${movieVO.content.length() > 160 }">
                  ${movieVO.content.substring(0, 160) }...
                </c:when>
                <c:otherwise>
                  ${movieVO.content }
                </c:otherwise>
              </c:choose>
              (${movieVO.rdate.substring(0, 16) })
            </td>
            <td class="td_bs">
              <a href="/movie/delete.do?genreno=${genreno }&movieno=${movieno}&now_page=${param.now_page}" title="삭제"><img src="/movie/images/delete.png" class="icon"></a>
            </td>
          </tr>
        </c:forEach>
    </tbody>
      
  </table>
  
  <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
