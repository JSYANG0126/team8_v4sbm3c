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

  <div class='title_line'>장르</div>
  
  <aside class="aside_right">
    <a href="./create.do?genreno=${genreVO.genreno }">등록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside>
  <div class="menu_line"></div> 
  
  <form name='frm' method='post' action='/genre/create.do'>
    <div style="text-align: center;">
      <label>장르 이름</label>
      <input type="text" name="name" value="" required="required" autofocus="autofocus" 
                 class="" style="width: 50%">
      <button type="submit" class="btn btn-sm" style="height: 28px; margin-bottom: 5px; background-color: #323232; color:white;">등록</button>
      <button type="button" onclick="location.href='./list_all.do'" class="btn btn-sm" 
                  style="height: 28px; margin-bottom: 5px; background-color: #323232; color:white;">목록</button> 
    </div>
  </form>
  
  <table class="table table-hover">
    <colgroup>
        <col style='width: 10%;'/>
        <col style='width: 40%;'/>
        <col style='width: 10%;'/>    
        <col style='width: 20%;'/>
        <col style='width: 20%;'/>
      </colgroup>
      <thead>
        <tr>
          <th class="th_bs">순서</th>
          <th class="th_bs">장르 이름</th>
          <th class="th_bs">자료수</th>
          <th class="th_bs">등록일</th>
          <th class="th_bs">기타</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="genreVO" items="${list }" varStatus="info">
          <c:set var="genreno" value="${genreVO.genreno }" />
          <c:set var="seqno" value="${genreVO.seqno }" />
    
          <tr>
            <td class="td_bs">${seqno }</td>
            <td><a href="../movie/list_by_genreno.do?genreno=${genreno }" style="display: block;">${genreVO.name }</a></td>
            <td class="td_bs">${genreVO.cnt }</td>
            <td class="td_bs">${genreVO.rdate.substring(0, 10) }</td>
            <td class="td_bs">
              <a href="../movie/create.do?genreno=${genreno }" title="등록"><img src="/genre/images/create.png" class="icon"></a>
              <c:choose>
                <c:when test="${genreVO.visible == 'Y'}">
                  <a href="./update_visible_n.do?genreno=${genreno }" title="카테고리 공개 설정"><img src="/genre/images/show.png" class="icon"></a>
                </c:when>
                <c:otherwise>
                  <a href="./update_visible_y.do?genreno=${genreno }" title="카테고리 비공개 설정"><img src="/genre/images/hide.png" class="icon"></a>
                </c:otherwise>
              </c:choose>    
              
              <a href="./update_seqno_forward.do?genreno=${genreno }" title="우선 순위 높임"><img src="/genre/images/decrease.png" class="icon"></a>
              <a href="./update_seqno_backward.do?genreno=${genreno }" title="우선 순위 낮춤"><img src="/genre/images/increase.png" class="icon"></a>
              <a href="./update.do?genreno=${genreno }" title="수정"><img src="/genre/images/update.png" class="icon"></a>
              <a href="./delete.do?genreno=${genreno }" title="삭제"><img src="/genre/images/delete.png" class="icon"></a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
