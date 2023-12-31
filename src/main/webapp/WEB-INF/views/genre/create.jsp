<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>영화리뷰 블로그</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <%-- /static/css/style.css 기준 --%>
    
</head>  
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 <div class='title_line'>카테고리 등록</div>
 
<form name='frm' method='post' action='/genre/create.do'>
    <div>
        <label>카테고리 이름</label>
        <input type='text' name='name' value="" required="required" autofocus="autofocus" 
                    class="form-contro form-control-sml" style="width:50%">
    </div>
    
    <div class = "content_body_bottom">
        <button type="submit" class="btn btn-sm" style="background-color: #323232;">등록</button>
        <button type="button" onclick="location.href='./list_all.do'"class="btn btn-sm" style="background-color: #323232;">목록</button>
    </div>
</form>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>