<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>영화리뷰 블로그</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head>
 
<body>
<c:import url="/menu/top.do" />
 
	<div class='title_line'>${genreVO.name } > 글 등록</div>
	
	<div class='content_body'>
	  <aside class="aside_right">
	    <a href="./create.do?genreno=${genreVO.genreno }">등록</a>
	    <span class='menu_divide' >│</span>
	    <a href="javascript:location.reload();">새로고침</a>
	    <span class='menu_divide' >│</span>
	    <a href="./list_by_genreno.do?genreno=${genreVO.genreno }">기본 목록형</a>    
	    <span class='menu_divide' >│</span>
	    <a href="./list_by_genreno_grid.do?genreno=${genreVO.genreno }">갤러리형</a>
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
	      <button type='submit' class='btn btn-sm' style="background-color: #323232; color: white;">검색</button>
	      <c:if test="${param.word.length() > 0 }">
	        <button type='button' class='btn btn-sm' style="background-color: #323232; color: white;"
	                    onclick="location.href='./list_by_genreno_search.do?genreno=${genreVO.genreno}&word='">검색 취소</button>  
	      </c:if>    
	    </form>
	  </div>
	  
	  <div class='menu_line'></div>
	  
	  <form name='frm' method='post' action='./create.do' enctype="multipart/form-data">
	    <input type="hidden" name="genreno" value="${param.genreno }">
	    
	    <div>
	       <label>제목</label>
	       <input type='text' name='title' value='앨리멘탈' required="required" 
	                 autofocus="autofocus" class="form-control" style='width: 100%;'>
	    </div>
	    <div>
	       <label>내용</label>
	       <textarea name='content' required="required" class="form-control" rows="12" style='width: 100%;'>영화 리뷰 내용</textarea>
	    </div>
	    <div>
	       <label>검색어</label>
	       <input type='text' name='word' value='영화 리뷰, 앨리멘탈, cgv, 아이파크몰' required="required" 
	                 class="form-control" style='width: 100%;'>
	    </div>   
	    <div>
	       <label>이미지</label>
	       <input type='file' class="form-control" name='file1MF' id='file1MF' 
	                 value='' placeholder="파일 선택">
	    </div>   
	    <div>
	       <label>패스워드</label>
	       <input type='password' name='passwd' value='1234' required="required" 
	                 class="form-control" style='width: 50%;'>
	    </div>   
	    <div class="content_body_bottom">
	      <button type="submit" class="btn" style="background-color: #323232; color: white;">등록</button>
	      <button type="button" onclick="location.href='./list_by_genreno.do?genreno=${param.genreno}'" class="btn" style="background-color: #323232; color: white;">목록</button>
	    </div>
	  
	  </form>
</div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>