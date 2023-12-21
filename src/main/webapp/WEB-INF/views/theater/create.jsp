<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>http://localhost:9093/</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <%-- /static/css/style.css 기준 --%>
    
</head>
  <input type='hidden' name='memno' value="${sessionScope.memno }" required="required" autofocus="autofocus" 
                    class="form-contro form-control-sml" style="width:30%"><br>
  <input type='hidden' name='map' value="지도 추가 필요" required="required" autofocus="autofocus" 
                    class="form-contro form-control-sml" style="width:30%"><br>  
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

 <div class='title_line'>추천 영화관 등록</div>
  <form name='frm' method='post' action='/theater/create.do'>
     <div class='content_body'>
    <aside class="aside_right">
      <a href="javascript:location.reload();">새로고침</a>
      <span class='menu_divide' >│</span>
      <a href="./list_by_search_paging.do?nowpage=1">목록형</a>    
      <span class='menu_divide' >│</span>
      <a href="./list_by_grid.do?nowpage=1">갤러리형</a>
    </aside> 
    </form>
    
    <div class='menu_line'></div>
    
    <form name='frm' method='post' action='./create.do' enctype="multipart/form-data">
      
      <div>
         <label>제목</label>
         <input type='text' name='tname' value='메가박스 별내점' required="required" 
                   autofocus="autofocus" class="form-control" style='width: 100%;'>
      </div>
      <div>
         <label>내용</label>
         <textarea name='tinfo' required="required" class="form-control" rows="12" style='width: 100%;'>별내에서 가장 좋은 영화관</textarea>
      </div>
      <div>
         <label>검색어</label>
         <input type='text' name='word' value='메가박스 megabox , 아이파크몰' required="required" 
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
        <button type="submit" class="btn btn-secondary btn-sm">등록</button>
        <button type="button" onclick="location.href='./list_by_search_paging.do?nowpage=1'" class="btn btn-secondary btn-sm">목록</button>
      </div>
    
    </form>
  </div>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>