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
  
  <table class="table table-hover">
    <colgroup>
      <col style="width: 5%;"></col>
      <col style="width: 15%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 50%;"></col>
      <col style="width: 10%;"></col>
      </colgroup>
      <thead>
        <tr>
          <th style='text-align: center;'>번호</th>
          <th style='text-align: center;'>이미지</th>
          <th style='text-align: center;'>추천인 이름</th>
          <th style='text-align: center;'>영화관 소개</th>
          <th style='text-align: center;'>기타</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="theaterVO" items="${list }" varStatus="info">
          <c:set var="theaterno" value="${theaterVO.theaterno }" />
          <c:set var="thumbimg1" value="${theaterVO.thumbimg1 }" />
          <c:set var="memno" value="${theaterVO.memno }" />
          <c:set var="mname" value="${theaterVO.mname }" />
          <c:set var="now_page" value="${param.now_page }" />
    
          <tr onclick="location.href='./read.do?theaterno=${theaterno}&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }'" style="cursor: pointer;">
            <td class="td_bs">
               <span style="font-weight: bold;">${theaterno }</span><br>
            </td>
            <td>
              <c:choose>
                <c:when test="${thumbimg1.endsWith('jpg') || thumbimg1.endsWith('png') || thumbimg1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                  <%-- registry.addResourceHandler("/movie/storage/**").addResourceLocations("file:///" +  moviegetUploadDir()); --%>
                  <img src="/theater/storage/${thumbimg1 }" style="width: 120px; height: 90px;">
                </c:when>
                <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/movie/images/none1.png -->
                  <img src="/theater/images/none1.png" style="width: 120px; height: 90px;">
                </c:otherwise>
              </c:choose>
            </td>
            <td class="td_bs">
               <span style="font-weight: bold;">${mname } </span><br>
            </td>
           <td class="td_bs_left">
              <span style="font-weight: bold;">${theaterVO.tname }</span><br>
              <c:choose>
                <c:when test="${theaterVO.tinfo.length() > 160 }">
                  ${theaterVO.tinfo.substring(0, 160) }
                </c:when>
                <c:otherwise>
                  <DIV style='text-align: center; width:640px; height: 360px; margin: 0px auto;'>
                  ${theaterVO.tinfo }
                  </DIV>
                </c:otherwise>
              </c:choose><br>
              (${theaterVO.tdate.substring(0, 16) })
            </td>
            <td class="td_bs">
              <c:if test="${sessionScope.memno == theaterVO.memno }">
                <a href="./update.do?theaterno=${theaterno }&now_page=${now_page}&word=${word}" title="수정"><img src="/theater/images/update.png" class="icon"></a>
                <a href="./delete.do?theaterno=${theaterno }" title="삭제"><img src="/theater/images/delete.png" class="icon"></a>
              </c:if>  
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
