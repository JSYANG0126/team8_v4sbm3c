<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>http://localhost:9093/</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <%-- /static/css/style.css 기준 --%>
    
</head>  
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 <div class='title_line'>추천 영화관 등록</div>
 
<form name='frm' method='post' action='/reservation/create.do'>
    <div>
        <label>영화관 이름</label>
        <input type='text' name='tname' value="" required="required" autofocus="autofocus" 
                    class="form-contro form-control-sml" style="width:50%"><br>
        <label>페이지 URL </label>            
        <input type='text' name='link' value="" required="required" autofocus="autofocus" 
                    class="form-contro form-control-sml" style="width:50%">            
    </div>
    
    <div class = "content_body_bottom">
        <button type="submit" class="btn btn-primary btn-sm">등록</button>
        <button type="button" onclick="location.href='./list_all.do'"class="btn btn-primary btn-sm">목록</button>
    </div>
</form>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>