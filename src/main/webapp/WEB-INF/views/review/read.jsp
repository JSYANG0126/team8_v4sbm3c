<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="reviewno" value="${reviewVO.reviewno }" />
<c:set var="thumb1" value="${reviewVO.thumb1 }" />
<c:set var="file1saved" value="${reviewVO.file1saved }" />
<c:set var="rtitle" value="${reviewVO.rtitle }" />
<c:set var="rinfo" value="${reviewVO.rinfo }" />
<c:set var="rdate" value="${reviewVO.rdate }" />
<c:set var="file1" value="${reviewVO.file1 }" />
<c:set var="size1_label" value="${reviewVO.size1_label }" />
<c:set var="word" value="${reviewVO.word }" />
<c:set var="nice_cnt" value="${nice_cnt }" />
 
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
var nice_cnt = ${nice_cnt == null ? 0 : nice_cnt};
var reviewno = ${reviewno};
var memno = ${memno};

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/nice/nice_by_mem_cnt.do',
        cache: false,
        data: { reviewno: ${reviewno}, memno: ${memno} },
        dataType: 'json',
        success: function (response) {
            console.log(response.cnt);
            var mem_cnt = response.cnt;
            if (mem_cnt == 1) {
                $('#nicebtn').hide();
                $('#nicebtn2').show();
            } else {
                $('#nicebtn').show();
                $('#nicebtn2').hide();
            }
            $('#nice_cnt').text(response.nice_cnt);
        },
        error: function (error) {
            console.error('좋아요 확인 실패:', error);
        }
    });
});

function create() {
    $.ajax({
        type: 'POST',
        url: '/nice/create.do',
        data: { reviewno: reviewno, memno: memno },
        success: function (response) {
        	nice_cnt = response.nice_cnt;
                $('#nicebtn').hide();
                $('#nicebtn2').show();
                $('#nice_cnt').text(nice_cnt);
        },
        error: function (error) {
            console.error('좋아요 요청 실패:', error);
        }
    });
}

function unlike() {

    // 서버로 좋아요 요청을 보냄
    $.ajax({
        type: 'POST',
        url: '/nice/delete.do',
        data: { reviewno: reviewno, memno: memno },
        success: function (response) {
        	nice_cnt = response.nice_cnt;
                $('#nicebtn').show();
                $('#nicebtn2').hide();
                $('#nice_cnt').text(nice_cnt);
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
  <DIV class='title_line'><A href="javascript:location.reload();" class='title_link'>${rtitle }</A></DIV>

  <aside class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.id != null && sessionScope.memno == reviewVO.memno }">
      <%--
      http://localhost:9091/movie/create.do?genreno=1
      http://localhost:9091/movie/create.do?genreno=2
      http://localhost:9091/movie/create.do?genreno=3
      --%>
      <a href="./create.do?reviewno=${reviewno }">등록</a>
      <span class='menu_divide' >│</span>
      <a href="./update_text.do?reviewno=${reviewno}&now_page=${param.now_page}&word=${param.word }">글 수정</a>
      <span class='menu_divide' >│</span>
      <a href="./update_file.do?reviewno=${reviewno}&now_page=${param.now_page}">파일 수정</a>  
      <span class='menu_divide' >│</span>
      <a href="./delete.do?reviewno=${reviewno}&now_page=${param.now_page}">삭제</a>  
      <span class='menu_divide' >│</span>
    </c:if>

    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span>    
    <a href="./list_paging.do?now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">목록형</a>   
    <span class='menu_divide' >│</span>    
    <a href="./list_grid.do?now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">갤러리형</a>       
  </aside> 
  
<!--    <div style="text-align: right; clear: both;">  
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
  </div> -->
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">
          <c:choose>
            <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
              <%-- /static/movie/storage/ --%>
              <img src="/review/storage/${file1saved }" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:when>
            <c:otherwise> <!-- 기본 이미지 출력 -->
              <img src="/review/images/none1.png" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:otherwise>
          </c:choose>

          <span style="font-size: 1.5em; font-weight: bold;">${rtitle }</span>
          <span style="font-size: 1em;"> ${rdate }</span><br>
          ${rinfo }
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
            첨부 파일: <a href='/download?dir=/review/storage&filename=${file1saved}&downname=${file1}'>${file1}</a> (${size1_label}) 
            <a href='/download?dir=/review/storage&filename=${file1saved}&downname=${file1}'><img src="/review/images/download.png"></a>
          </c:if>
        </div>
      </li>   
    </ul>
  </fieldset>
  
   <form id="nice_form">						
	  	<input type="hidden" name="reviewno" value="${reviewno}">			
	  	<input type="hidden" name="memno" value="${memno}">			
			<img src="/nice/images/nice1.png"  onclick="create();" id="nicebtn" >	
			<img src="/nice/images/nice2.png"  onclick="unlike();" id="nicebtn2" style="display: none;">	
			<span id= "nice_cnt" name="nice_cnt">${nice_cnt} </span>
  </form>
  
  <c:if test="${sessionScope.id != null}">
  <form name='frm' method='post' action='/reply/create.do'>
 		<input type='hidden' name='memno' value='${memno}'>
    <input type='hidden' name='reviewno' value='${reviewno}'>
    <input type='hidden' name='now_page' value='${reviewVO.now_page}'>
    <div style="text-align: center;">
      <label>댓글</label>
      <input type="text" name="reply" value="" required="required" autofocus="autofocus" 
                 class="" style="width: 50%">
      <button type="submit" class="btn btn-sm" style="height: 28px; margin-bottom: 5px; background-color: #323232; color: white;">등록</button>
    </div>
  </form>
  </c:if>
	
	<table class="table table">
    <colgroup>
       <col style="width: 10%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col>
      </colgroup>
      <tbody>
      <c:forEach var="ReplyVO" items="${list_reply }" varStatus="info">
          <tr>
            <td>
             ${ReplyVO.mname}
            </td>
            <td>
              <span>${ReplyVO.reply}</span><br>
            </td>
            <td>
								${ReplyVO.cdate}
            </td>
            <td>
            <c:if test="${sessionScope.id != null && sessionScope.memno == ReplyVO.memno }">
            <a href="/reply/delete.do?replyno=${ReplyVO.replyno }&reviewno=${param.reviewno}"><img src="/reply/delete.png" title="삭제"></a>
            <a href="/reply/update.do?replyno=${ReplyVO.replyno }&reviewno=${param.reviewno}"><img src="/reply/update.png" title="수정"></a>
            </c:if>
            <c:if test="${sessionScope.grade == 1 }">
            <a href="/reply/delete.do?replyno=${ReplyVO.replyno }&reviewno=${param.reviewno}"><img src="/reply/delete.png" title="삭제"></a>
            <a href="/reply/update.do?replyno=${ReplyVO.replyno }&reviewno=${param.reviewno}"><img src="/reply/update.png" title="수정"></a>
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