<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>영화 리뷰 블로그</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
   
</head> 
 
<body>
<c:import url="/menu/top.do" />
  <DIV class='title_line'><A href="./list_all.do" class='title_link'>질문</A></DIV>

  <aside class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.id != null && sessionScope.memno == qnaVO.memno }">
        <%--
        http://localhost:9091/movie/create.do?genreno=1
        http://localhost:9091/movie/create.do?genreno=2
        http://localhost:9091/movie/create.do?genreno=3
        --%>
        <a href="./delete.do?qnano=${param.qnano }">삭제</a> 
      <span class='menu_divide' >│</span> 
      <a href="./update.do?qnano=${param.qnano }">수정</a>  
      <span class='menu_divide' >│</span> 
    </c:if>
    
    <c:if test="${sessionScope.grade == 2 }">
      <%--
      http://localhost:9091/movie/create.do?genreno=1
      http://localhost:9091/movie/create.do?genreno=2
      http://localhost:9091/movie/create.do?genreno=3
      --%>
      <a href="./delete.do?qnano=${param.qnano }">삭제</a> 
      <span class='menu_divide' >│</span> 
      <a href="./update.do?qnano=${param.qnano }">수정</a>    
      <span class='menu_divide' >│</span> 
    </c:if>
    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span>    
    <a href="./list_all.do">목록</a>    
  </aside> 
  
<!--    <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_genreno.do'>
      <input type='hidden' name='genreno' value='${param.genreno }'>  
      
      <c:choose>
        <c:when test="${param.word != '' }"> 
          <input type='text' name='word' id='word' value='${param.word }'>
        </c:when>
        <c:otherwise> 검색하지 않는 경우
          <input type='text' name='word' id='word' value=''>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-secondary btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;">검색</button>
      <c:if test="${param.word.length() > 0 }"> 
        <button type='button' class='btn btn-secondary btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;"
                    onclick="location.href='./list_by_genreno.do?genreno=${param.genreno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </div> -->
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">   

          <span style="font-size: 1.5em; font-weight: bold;">${qnaVO.qnatitle }</span>
          <br><span style="font-size: 1em;"> ${qnaVO.qdate } 작성자: ${qnaVO.qname} </span><br>
          ${qnaVO.qnainfo }
        </DIV>
      </li>     
      
      
      <li class="li_none" style="clear: both;">
        <DIV style='text-decoration: none;'>
          <br>
          
        </DIV>
      </li>
    </ul>
  </fieldset>
  
  <c:if test="${sessionScope.grade == 2}">
	<form name='frm' method='post' action='/answer/create.do'>
 		<input type='hidden' name='memno' value='${memno}'>
    <input type='hidden' name='qnano' value='${param.qnano}'>
    <div style="text-align: center;">
      <label>답변</label>
      <input type="text" name="reply" value="" required="required" autofocus="autofocus" 
                 class="" style="width: 50%">
      <button type="submit" class="btn btn-sm" style="height: 28px; margin-bottom: 5px; background-color: #323232; color: white;">등록</button>
    </div>
  </form>
  </c:if>
	
	<table class="table table">
    <colgroup>
       <col style="width: 15%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 5%;"></col>
      </colgroup>
      <tbody>
      <c:forEach var="AnswerVO" items="${list }" varStatus="info">
          <tr>
            <td>
             ${AnswerVO.aname}
            </td>
            <td>
              <span>${AnswerVO.reply}</span><br>
            </td>
            <td>
								${AnswerVO.adate}
            </td>
            <td>
            <c:if test="${sessionScope.id != null && sessionScope.memno == QnaVO.memno }">
            <a href="/answer/delete.do?answerno=${AnswerVO.answerno }&qnano=${param.qnano}"><img src="/answer/delete.png" title="삭제"></a>
            <a href="/answer/update.do?answerno=${AnswerVO.answerno }&qnano=${param.qnano}"><img src="/answer/update.png" title="수정"></a>
            </c:if>
            <c:if test="${sessionScope.grade == 2}">
            <a href="/answer/delete.do?answerno=${AnswerVO.answerno }&qnano=${param.qnano}"><img src="/answer/delete.png" title="삭제"></a>
            <a href="/answer/update.do?answerno=${AnswerVO.answerno }&qnano=${param.qnano}"><img src="/answer/update.png" title="수정"></a></c:if>
            </td>
            
          </tr>

         </c:forEach>
    </tbody>
      
  </table>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

