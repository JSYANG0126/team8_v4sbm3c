<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="genreno" value="${genreVO.genreno }" />

<c:set var="movieno" value="${movieVO.movieno }" />
<c:set var="title" value="${movieVO.title }" />
<c:set var="content" value="${movieVO.content }" />
<c:set var="word" value="${movieVO.word }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>영화리뷰 블로그</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'> ${genreVO.name } > ${title } > 수정</DIV>
  
  <aside class="aside_right">
    <a href="./create.do?genreno=${genreno }">등록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span>    
    <a href="./list_by_genreno.do?genreno=${genreno }&now_page=${param.now_page}&word=${param.word }">목록형</a>    
    <span class='menu_divide' >│</span>
    <a href="./list_by_genreno_grid.do?genreno=${genreno }&now_page=${param.now_page}&word=${param.word }">갤러리형</a>
  </aside>
  
  <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_genreno_search_paging.do'>
      <input type='hidden' name='genreno' value='${genreVO.genreno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px; background-color: #323232; color: white;">검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='btn btn-sm' 
                    onclick="location.href='./list_by_genreno.do?genreno=${genreVO.genreno}&word='" style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px; background-color: #323232; color: white;">검색 취소</button>  
      </c:if>    
    </form>
  </div>
  
  <div class='menu_line'></div>
  
  <form name='frm' method='post' action='./update_text.do'>
    <input type="hidden" name="genreno" value="${genreno }">
    <input type="hidden" name="movieno" value="${movieno }">
    <input type="hidden" name="now_page" value="${param.now_page }">
    
    <div>
       <label>제목</label>
       <input type='text' name='title' value='${title }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='content' required="required" class="form-control" rows="12" style='width: 100%;'>${content }</textarea>
    </div>
    <div>
       <label>검색어</label>
       <input type='text' name='word' value="${word }" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
    
    <div>
      <label>패스워드</label>
      <input type='password' name='passwd' value='' required="required" 
                class="form-control" style='width: 50%;'>
    </div>
       
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-sm" style="background-color: #323232; color: white;">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-sm" style="background-color: #323232; color: white;">취소</button>
    </div>
  
  </FORM>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

