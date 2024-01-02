<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="memno1" value="${sessionScope.memno }" />
<c:set var="memno2" value="${theaterVO.memno }" />
<c:set var="tinfo" value="${theaterVO.tinfo }" />
<c:set var="theaterno" value="${theaterVO.theaterno }" />
<c:set var="tdate" value="${theaterVO.tdate }" />
<c:set var="map" value="${theaterVO.map }" />
<c:set var="thumbimg1" value="${theaterVO.thumbimg1 }" />
<c:set var="img1" value="${theaterVO.img1 }" />
<c:set var="img1saved" value="${theaterVO.img1saved }" />
<c:set var="word" value="${theaterVO.word }" />
<c:set var="size1" value="${theaterVO.size1 }" />
<c:set var="tname" value="${theaterVO.tname }" />

 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>영화 추천 블로그</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



<script type="text/javascript">
  

  $(document).ready(function () {
    // Modal 초기화
    var myModal = new bootstrap.Modal(document.getElementById('modal_panel'));

    // ---------------------------------------- 댓글 관련 시작 ----------------------------------------
    var frm_reply = $('#frm_reply');
    $('#treply', frm_reply).on('click', check_login);  // 댓글 작성시 로그인 여부 확인
    $('#btn_create', frm_reply).on('click', reply_create);  // 댓글 작성시 로그인 여부 확인

    list_by_theaterno_join();
    // ---------------------------------------- 댓글 관련 종료 ----------------------------------------

  

    // 댓글 작성시 로그인 여부 확인
    function check_login() {
      var frm_reply = $('#frm_reply');
      if ($('#memno', frm_reply).val().length == 0 ) {
        $('#modal_title').html('댓글 등록'); // 제목 
        $('#modal_content').html("로그인해야 등록 할 수 있습니다."); // 내용
        $('#modal_panel').modal();            // 다이얼로그 출력
        return false;  // 실행 종료
      }
      return true;
    }

    // 댓글 등록
    function reply_create() {
      var frm_reply = $('#frm_reply');

      if (check_login() != false ) { // 로그인 한 경우만 처리
        var params = frm_reply.serialize(); // 직렬화: 키=값&키=값&...

        console.log($('#treply', frm_reply).val().length );
        if ($('#treply', frm_reply).val().length > 300) {
          $('#modal_title').html('댓글 등록'); // 제목 
          $('#modal_content').html("댓글 내용은 300자이상 입력 할 수 없습니다."); // 내용
          $('#modal_panel').modal();           // 다이얼로그 출력
          return;  // 실행 종료
        }

        $.ajax({
          url: "../treply/create.do", // action 대상 주소
          type: "post",          // get, post
          cache: false,          // 브러우저의 캐시영역 사용안함.
          async: true,           // true: 비동기
          dataType: "json",   // 응답 형식: json, xml, html...
          data: params,        // 서버로 전달하는 데이터
          success: function(rdata) { // 서버로부터 성공적으로 응답이 온경우
            var msg = ""; // 메시지 출력
            var tag = ""; // 글목록 생성 태그
            console.log(rdata.cnt);
            ncnt = parseInt(rdata.cnt);
            if (ncnt > 0) {
              $('#modal_content').attr('class', 'alert alert-success'); // CSS 변경
              msg = "댓글을 등록했습니다.";
              $('#treply', frm_reply).val('');
              $('#pw', frm_reply).val('');
              
              $('#reply_list').html(''); // 댓글 목록 패널 초기화, val(''): 안됨
              $("#reply_list").attr("data-replypage", 1);  // 댓글이 새로 등록됨으로 1로 초기화
              
              list_by_theaterno_join(); // 페이징 댓글, 페이징 문제 있음.
              // alert('댓글 목록 읽기 시작');
              // global_rdata = new Array(); // 댓글을 새로 등록했음으로 배열 초기화
              // global_rdata_cnt = 0; // 목록 출력 글수
              
              // list_by_theaterno_join(); // 페이징 댓글
            } else {
              $('#modal_content').attr('class', 'alert alert-danger'); // CSS 변경
              msg = "댓글 등록에 실패했습니다.";
            }
            
            $('#modal_title').html('댓글 등록'); // 제목 
            $('#modal_content').html(msg);     // 내용
            $('#modal_panel').modal();           // 다이얼로그 출력
          },
          // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
          error: function(request, status, error) { // callback 함수
            var msg = 'ERROR request.status: '+request.status + '/ ' + error;
            console.log(msg); // Chrome에 출력
          }
        });
      }
      }

      
    }) // 끝

    //theaterno 별 소속된 댓글 목록
    function list_by_theaterno_join() {
    var params = 'theaterno=' + ${theaterno };
      console.log(params);
      $.ajax({
        url: "../treply/list_by_theaterno_join.do", // action 대상 주소
        type: "get",           // get, post
        cache: false,          // 브러우저의 캐시영역 사용안함.
        async: true,           // true: 비동기
        dataType: "json",   // 응답 형식: json, xml, html...
        data: params,        // 서버로 전달하는 데이터
        success: function(rdata) { // 서버로부터 성공적으로 응답이 온경우
          //alert(rdata);
          var msg = '';
          
          $('#reply_list').html(''); // 패널 초기화, val(''): 안됨
          
          for (i=0; i < rdata.list.length; i++) {
            var row = rdata.list[i];
            
            msg += "<DIV id='"+row.treplyno+"' style='border-bottom: solid 1px #EEEEEE; margin-bottom: 10px;'>";
            msg += "<span style='font-weight: bold;'>" + row.id + "</span>";
            msg += "  " + row.cdate;
            
            if ('${sessionScope.memno}' == row.memno) { // 글쓴이 일치여부 확인, 본인의 글만 삭제 가능함 ★
              msg += " <A href='javascript:reply_delete(" + row.treplyno + ")'><IMG src='/treply/images/delete.png'></A>";
            }
            msg += "  " + "<br>";
            msg += row.treply;
            msg += "</DIV>";
          }
          // alert(msg);
          $('#reply_list').append(msg);
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
        error: function(request, status, error) { // callback 함수
          console.log(error);
        }
    });

    // 댓글 삭제 레이어 출력
    function reply_delete(treplyno) {
      // alert('replyno: ' + replyno);
      var frm_reply_delete = $('#frm_reply_delete');
      $('#treplyno', frm_reply_delete).val(treplyno); // 삭제할 댓글 번호 저장
      $('#modal_panel_delete').modal();             // 삭제폼 다이얼로그 출력
    }
    
    

    // 댓글 삭제 처리
    function reply_delete_proc(treplyno) {
      // alert('replyno: ' + replyno);
      var params = $('#frm_reply_delete').serialize();
      $.ajax({
        url: "../treply/delete.do", // action 대상 주소
        type: "post",           // get, post
        cache: false,          // 브러우저의 캐시영역 사용안함.
        async: true,           // true: 비동기
        dataType: "json",   // 응답 형식: json, xml, html...
        data: params,        // 서버로 전달하는 데이터
        success: function(rdata) { // 서버로부터 성공적으로 응답이 온경우
          // alert(rdata);
          var msg = "";
          
          if (rdata.passwd_cnt ==1) { // 패스워드 일치
            if (rdata.delete_cnt == 1) { // 삭제 성공

              $('#btn_frm_reply_delete_close').trigger("click"); // 삭제폼 닫기, click 발생 
              
              $('#' + replyno).remove(); // 태그 삭제
                
              return; // 함수 실행 종료
            } else {  // 삭제 실패
              msg = "패스 워드는 일치하나 댓글 삭제에 실패했습니다. <br>";
              msg += " 다시한번 시도해주세요."
            }
          } else { // 패스워드 일치하지 않음.
            // alert('패스워드 불일치');
            // return;
            
            msg = "패스워드가 일치하지 않습니다.";
            $('#modal_panel_delete_msg').html(msg);

            $('#passwd', '#frm_reply_delete').focus();  // frm_reply_delete 폼의 passwd 태그로 focus 설정
            
          }
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
        error: function(request, status, error) { // callback 함수
          console.log(error);
        }
      });
    }
    

  }// 끝

    
</script>

</head> 
 
<body>
<c:import url="/menu/top.do" />
<!-- Modal 알림창 시작 -->
<div class="modal fade" id="modal_panel" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h4 class="modal-title" id='modal_title'></h4><!-- 제목 -->
      </div>
      <div class="modal-body">
        <p id='modal_content'></p>  <!-- 내용 -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div> <!-- Modal 알림창 종료 -->

<!-- -------------------- 댓글 삭제폼 시작 -------------------- -->
<div class="modal fade" id="modal_panel_delete" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h4 class="modal-title">댓글 삭제</h4><!-- 제목 -->
      </div>
      <div class="modal-body">
        <form name='frm_reply_delete' id='frm_reply_delete'>
          <input type='hidden' name='treplyno' id='treplyno' value=''>
          
          <label>패스워드</label>
          <input type='password' name='passwd' id='passwd' class='form-control'>
          <DIV id='modal_panel_delete_msg' style='color: #AA0000; font-size: 1.1em;'></DIV>
        </form>
      </div>
      <div class="modal-footer">
        <button type='button' class='btn btn-danger' 
        onclick="reply_delete_proc(${treplyno});">삭제</button>

        <button type="button" class="btn btn-default" data-dismiss="modal" 
                     id='btn_frm_reply_delete_close'>Close</button>
      </div>
    </div>
  </div>
</div>
<!-- -------------------- 댓글 삭제폼 종료 -------------------- -->

  <DIV class='title_line'>${tname } </A></DIV>

  <aside class="aside_right">

    <c:if test="${sessionScope.id != null && memno1 == memno2}">
      <a href="./update.do?theaterno=${theaterno}&now_page=${param.now_page}&word=${param.word }">글 수정</a>
      <span class='menu_divide' >│</span>
      <a href="./update_file.do?theaterno=${theaterno}&now_page=${param.now_page}">파일 수정</a>  
      <span class='menu_divide' >│</span>
      <a href="./map.do?theaterno=${theaterno}">지도</a>
      <span class='menu_divide' >│</span>
      <a href="./delete.do?theaterno=${theaterno}">삭제</a>  
      <span class='menu_divide' >│</span>
    </c:if>
    <a href='./list_all.do'>목록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
  </aside> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
				<li class="li_none">
				    <div style="width: 30%; float: left; margin-top: 0.5%; margin-right: 1%;">
				        <c:choose>
				            <c:when test="${thumbimg1.endsWith('jpg') || thumbimg1.endsWith('png') || thumbimg1.endsWith('gif')}">
				                <img src="/theater/storage/${img1saved}" style="width: 100%; word-break: break-all;">
				            </c:when>
				            <c:otherwise>
				                <img src="/theater/images/none1.png" style="width: 100%; word-break: break-all;">
				            </c:otherwise>
				        </c:choose>
				    </div>
				    <div style="width: 68%; float: left;">
				        <div>
				            <span style="font-size: 1.5em; font-weight: bold;">${tname }</span>
				            <span style="font-size: 1em;"> ${tdate}</span><br>
				        </div>
				        <div>
				            ${tinfo}
				        </div>
				    </div>
				</li>
     
      
      <li class="li_none" style="clear: both;">
        <DIV style='text-decoration: none;'>
          <br>
          검색어(키워드): ${word }
        </DIV>
      </li>

      <li class="li_none">
        <div>
          <c:if test="${img1.trim().length() > 0 }">
            첨부 파일: <a href='/download?dir=/theater/storage&filename=${img1saved}&downname=${img1}'>${img1}</a> (${size1_label}) 
            <a href='/download?dir=/theater/storage&filename=${img1saved}&downname=${img1}'><img src="/theater/images/download.png"></a>
          </c:if>
        </div>
      </li>   
      <c:if test="${map.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style='text-align: center; width:640px; height: 360px; margin: 0px auto;'>
            ${map }
          </DIV>
        </li>
      </c:if>

    </ul>
  </fieldset>

</DIV>

<!-- ------------------------------ 댓글 영역 시작 ------------------------------ -->
<DIV style='width: 80%; margin: 0px auto;'>
    <HR>
    <FORM name='frm_reply' id='frm_reply'> <%-- 댓글 등록 폼 --%>
      <input type='hidden' name='theaterno' id='theaterno' value='${theaterno}'>
      <input type='hidden' name='memno' id='memno' value='${memno1}'>
      
      <textarea name='treply' id='treply' style='width: 100%; height: 60px;' placeholder="댓글 작성, 로그인해야 등록 할 수 있습니다."></textarea>
      <input type='password' name='pw' id='pw' placeholder="비밀번호">
      <button type='button' id='btn_create'>등록</button>
    </FORM>

    
    <HR>
    <DIV id='reply_list' data-replypage='1'>  <%-- 댓글 목록 --%>
    </DIV>
    <DIV id='reply_list_btn' style='border: solid 1px #EEEEEE; margin: 0px auto; width: 100%; background-color: #EEFFFF;'>
        <button id='btn_add' style='width: 100%;'>더보기 ▽</button>
    </DIV>  
  
</DIV>

<!-- ------------------------------ 댓글 영역 종료 ------------------------------  -->
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

