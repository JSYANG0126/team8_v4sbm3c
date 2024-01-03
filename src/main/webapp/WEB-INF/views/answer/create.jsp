<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>영화 리뷰 블로그</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head>
 
<body>
<c:import url="/menu/top.do" />
 
	<div class='title_line'>답변</div>
	
	<div class='content_body'>
	  <aside class="aside_right">
	    <a href="./create.do">등록</a>
	    <span class='menu_divide' >│</span>
	    <a href="javascript:location.reload();">새로고침</a>
	  </aside> 

	  
	  <div class='menu_line'></div>
	  
	  <form name='frm' method='post' action='./create.do' enctype="multipart/form-data">
              <input type='hidden' name='memno' value='${memno}'>
	    <div>
	       <label>댓글</label>
	       <input type='text' name='reply' value='앨리멘탈' required="required" 
	                 autofocus="autofocus" class="form-control" style='width: 100%;'>
	    </div>
	    <div class="content_body_bottom">
	      <button type="submit" class="btn btn-secondary btn-sm">등록</button>
	      <button type="button" onclick="location.href='./list_all.do" class="btn btn-secondary btn-sm">목록</button>
	    </div>
	  
	  </form>
</div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>