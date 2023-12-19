<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="name" value="${genreVO.name }" />

<c:set var="genreno" value="${movieVO.genreno }" />
<c:set var="movieno" value="${movieVO.movieno }" />
<c:set var="thumb1" value="${movieVO.thumb1 }" />
<c:set var="file1saved" value="${movieVO.file1saved }" />
<c:set var="title" value="${movieVO.title }" />
<c:set var="content" value="${movieVO.content }" />
<c:set var="rdate" value="${movieVO.rdate }" />
<c:set var="file1" value="${movieVO.file1 }" />
<c:set var="size1_label" value="${movieVO.size1_label }" />
<c:set var="word" value="${movieVO.word }" />
<c:set var="good_cnt" value="${good_cnt }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script type="text/javascript">
var good_cnt = ${good_cnt};
var movieno = ${movieno};
var memno = ${memno};

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/good/good_by_mem_cnt.do',
        cache: false,
        data: { movieno: ${movieno}, memno: ${memno} },
        dataType: 'json',
        success: function (response) {
            console.log(response);
            var mem_cnt = response.cnt;
            if (mem_cnt == 1) {
                $('#goodbtn').hide();
                $('#goodbtn2').show();
            } else {
                $('#goodbtn').show();
                $('#goodbtn2').hide();
            }
            $('#good_cnt').text(response.good_cnt);
        },
        error: function (error) {
            console.error('좋아요 확인 실패:', error);
        }
    });
});

function create() {
    $.ajax({
        type: 'POST',
        url: '/good/create.do',
        data: { movieno: movieno, memno: memno },
        success: function (response) {
                good_cnt = response.good_cnt;
                $('#goodbtn').hide();
                $('#goodbtn2').show();
                $('#good_cnt').text(good_cnt);
        },
        error: function (error) {
            console.error('좋아요 요청 실패:', error);
        }
    });
}

function unlike() {
    var movieno = ${movieno};
    var memno = ${memno};

    // 서버로 좋아요 요청을 보냄
    $.ajax({
        type: 'POST',
        url: '/good/delete.do',
        data: { movieno: movieno, memno: memno },
        success: function (response) {
                good_cnt = response.good_cnt;
                $('#goodbtn').show();
                $('#goodbtn2').hide();
                $('#good_cnt').text(good_cnt);
        },
        error: function (error) {
            console.error('좋아요 요청 실패:', error);
        }
    });
}
</script>

</head> 
 
<body>
<c:import url="/menu/top.do" />
  <DIV class='title_line'><A href="./list_by_genreno.do?genreno=${genreVO.genreno }" class='title_link'>${genreVO.name }</A></DIV>

  <aside class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.manager_id != null }">
      <%--
      http://localhost:9091/movie/create.do?genreno=1
      http://localhost:9091/movie/create.do?genreno=2
      http://localhost:9091/movie/create.do?genreno=3
      --%>
      <a href="./create.do?genreno=${genreno }">등록</a>
      <span class='menu_divide' >│</span>
      <a href="./update_text.do?movieno=${movieno}&now_page=${param.now_page}&word=${param.word }">글 수정</a>
      <span class='menu_divide' >│</span>
      <a href="./update_file.do?movieno=${movieno}&now_page=${param.now_page}">파일 수정</a>  
      <span class='menu_divide' >│</span>
      <a href="./delete.do?movieno=${movieno}&now_page=${param.now_page}&genreno=${genreno}">삭제</a>  
      <span class='menu_divide' >│</span>
    </c:if>

    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span>    
    <a href="./list_by_genreno.do?genreno=${genreno }&now_page=${param.now_page}&word=${param.word }">목록형</a>    
    <span class='menu_divide' >│</span>
    <a href="./list_by_genreno_grid.do?genreno=${genreno }&now_page=${param.now_page}&word=${param.word }">갤러리형</a>
  </aside> 
  
  <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_genreno.do'>
      <input type='hidden' name='genreno' value='${param.genreno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우는 검색어를 출력 --%>
          <input type='text' name='word' id='word' value='${param.word }'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value=''>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-secondary btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;">검색</button>
      <c:if test="${param.word.length() > 0 }"> <%-- 검색 상태하면 '검색 취소' 버튼을 출력 --%>
        <button type='button' class='btn btn-secondary btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;"
                    onclick="location.href='./list_by_genreno.do?genreno=${param.genreno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </div>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">
          <c:choose>
            <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
              <%-- /static/movie/storage/ --%>
              <img src="/movie/storage/${file1saved }" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:when>
            <c:otherwise> <!-- 기본 이미지 출력 -->
              <img src="/movie/images/none1.png" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:otherwise>
          </c:choose>

          <span style="font-size: 1.5em; font-weight: bold;">${title }</span>
          <span style="font-size: 1em;"> ${rdate }</span><br>
          ${content }
        </DIV>
      </li>
      
      <li class="li_none" style="clear: both;">
        <DIV style='text-decoration: none;'>
          <br>
          검색어(키워드): ${word }
        </DIV>
      </li>

      <li class="li_none">
        <div>
          <c:if test="${file1.trim().length() > 0 }">
            첨부 파일: <a href='/download?dir=/movie/storage&filename=${file1saved}&downname=${file1}'>${file1}</a> (${size1_label}) 
            <a href='/download?dir=/movie/storage&filename=${file1saved}&downname=${file1}'><img src="/movie/images/download.png"></a>
          </c:if>
        </div>
      </li>   
    </ul>
  </fieldset>
  
  <form id="good_form">						
	  	<input type="hidden" name="movieno" value="${movieno}">			
	  	<input type="hidden" name="memno" value="${memno}">			
			<img src="/good/images/good1.png"  onclick="create();" id="goodbtn" style="display: none;">	
			<img src="/good/images/good2.png"  onclick="unlike();" id="goodbtn2" style="display: none;">	
			<span id= "good_cnt" name="good_cnt">${good_cnt }</span>
  </form>

    
 <form name='frm' method='post' action='/comments/create.do'>
 		<input type='hidden' name='memno' value='${memno}'>
    <input type='hidden' name='movieno' value='${movieno}'>
    <div style="text-align: center;">
      <label>댓글</label>
      <input type="text" name="reply" value="" required="required" autofocus="autofocus" 
                 class="" style="width: 50%">
      <button type="submit" class="btn btn-secondary btn-sm" style="height: 28px; margin-bottom: 5px;">등록</button>
      <button type="button" onclick="location.href='./list_all.do'" class="btn btn-secondary btn-sm" 
                  style="height: 28px; margin-bottom: 5px;">목록</button> 
    </div>
  </form>
	
	<table class="table table">
    <colgroup>
       <col style="width: 10%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col>
      </colgroup>
      <tbody>
      <c:forEach var="CommentsVO" items="${list_comments }" varStatus="info">
          <tr>
            <td>
             ${CommentsVO.cname}
            </td>
            <td>
              <span>${CommentsVO.reply}</span><br>
            </td>
            <td>
								${CommentsVO.cdate}
            </td>
            <td>
            <c:if test="${sessionScope.id != null && sessionScope.memno == CommentsVO.memno }">
            <a href="/comments/delete.do?commentno=${CommentsVO.commentno }&movieno=${param.movieno}"><img src="/comments/delete.png" title="삭제"></a>
            <a href="/comments/update.do?commentno=${CommentsVO.commentno }&movieno=${param.movieno}"><img src="/comments/update.png" title="수정"></a>
            </c:if>
            <c:if test="${sessionScope.memno == 1}">
            <a href="/comments/delete.do?commentno=${CommentsVO.commentno }&movieno=${param.movieno}"><img src="/comments/delete.png" title="삭제"></a>
            <a href="/comments/update.do?commentno=${CommentsVO.commentno }&movieno=${param.movieno}"><img src="/comments/update.png" title="수정"></a>
            </c:if>
            </td>
            
          </tr>

         </c:forEach>
    </tbody>
      
  </table>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

