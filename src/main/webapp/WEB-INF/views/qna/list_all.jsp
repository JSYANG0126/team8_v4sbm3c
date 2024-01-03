<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>영화 리뷰 블로그</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<c:import url="/menu/top.do" />

  <div class='title_line'>문의사항 목록</div>
  
  <aside class="aside_right">
  	<a href="./create.do">등록</a>
	  <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside>
  <div class="menu_line"></div> 
  
  <table class="table table-hover">
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
      </colgroup>
      <thead>
        <tr>
        	<th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>내용</th>
          <th style='text-align: center;'>닉네임</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="qnaVO" items="${list }" varStatus="info">
          <c:set var="qnano" value="${qnaVO.qnano }" />
    
          <tr onclick="location.href='./read.do?qnano=${qnano}'" style="cursor: pointer;">
            <td class="td_bs_left">
              <span style="font-weight: bold;">${qnaVO.qnatitle }</span><br>
            </td>
            <td class="td_bs">
              <c:choose>
                <c:when test="${qnaVO.qnainfo.length() > 40 }">
                  ${qnaVO.qnainfo.substring(0, 40) }...
                </c:when>
                <c:otherwise>
                  ${qnaVO.qnainfo }
                </c:otherwise>
              </c:choose>
              (${qnaVO.qdate.substring(0, 16) })
            </td>
            <td class="td_bs"> <!-- 작성자 아이디 표시 -->
              ${qnaVO.qname }
          </tr>
        </c:forEach>
    </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>