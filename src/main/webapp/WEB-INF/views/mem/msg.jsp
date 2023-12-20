<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head> 
<body>
<c:import url="/menu/top.do" />

<div class='title_line'>알림</div>

<div class='message'>
  <fieldset class='fieldset_basic'>
    <ul>
      <c:choose>
        <c:when test="${param.code == 'create_success'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_success">${param.mname }님(${param.id }) 회원 가입을 축하합니다.</span>
          </li>  
          <li class='li_none'>
            <button type='button' 
                         onclick="location.href='./login.do?id=${param.id}'"
                         class="btn btn-secondary btn-sm">로그인</button>
          </li> 
        </c:when>
        
        <c:when test="${param.code == 'create_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">회원 가입에 실패했습니다. 다시 시도해주세요.</span>
          </li>                                                                      
        </c:when>

        <c:when test="${param.code == 'duplicate_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">ID가 이미 사용중입니다. 회원 가입에 실패했습니다. <br>다시 시도해주세요.</span>
          </li>                                                                      
        </c:when>
        
        <c:when test="${param.code == 'update_success'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_success">${param.mname }님(아이디: ${param.id }) 회원 정보를 변경했습니다.</span>
          </li>
          <li class='li_none'>
            <button type='button' 
                         onclick="location.href='/'"
                         class="btn btn-secondary btn-sm">시작 화면</button>
            <button type='button' 
                         onclick="location.href='/mem/list.do'"
                         class="btn btn-secondary btn-sm">회원 목록</button>                   
          </li>                                                                       
        </c:when>
                
        <c:when test="${param.code == 'update_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">${param.mname }님(${param.id }) 회원 정보 수정에 실패했습니다.</span>
          </li>                                                                      
        </c:when>
        
        <c:when test="${param.code == 'delete_success'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_success">${param.mname }님(${param.id }) 회원 정보 삭제에 성공했습니다.</span>
          </li>   
          <li class='li_none'>
            <button type='button' 
                         onclick="location.href='/mem/list.do'"
                         class="btn btn-secondary btn-sm">회원 목록</button>
          </li>                                                                     
        </c:when>    
            
        <c:when test="${code == 'delete_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">${param.mname }님(${param.id }) 회원 정보 삭제에 실패했습니다.</span>
          </li>                                                                      
        </c:when> 
        
        <c:when test="${param.code == 'passwd_update_success'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_success">${param.mname }님(${param.id }) 패스워드를 변경했습니다.</span>
          </li>   
          <li class='li_none'>
            <button type='button' 
                         onclick="location.href='/'"
                         class="btn btn-secondary btn-sm">확인</button>
          </li>                                                                     
        </c:when>   
        
        <c:when test="${code == 'passwd_update_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">${param.mname }님(${param.id }) 패스워드 변경에 실패했습니다.</span>
          </li>                                                                      
        </c:when>  
        
        <c:when test="${param.code == 'wd_check_fail'}">
            <li class='li_none'>
              <span class="span_fail"> 이 계정은 탈퇴 처리된 계정입니다.</span>
            </li>
            <li class='li_none'>
              <button type='button' 
                    onclick="location.href='/'"
                    class="btn btn-secondary btn-sm">확인</button>
               </li>  
        </c:when>
        
        <c:when test="${param.code == 'unregister_success'}">
            <li class='li_none'>
              <span class="span_success"> 회원 탈퇴에 성공하였습니다.</span>
            </li>
            <li class='li_none'>
              <button type='button' 
                    onclick="location.href='/'"
                    class="btn btn-secondary btn-sm">확인</button>
            </li>
        </c:when>
        
        <c:when test="${param.code == 'unregister_fail'}">
            <li class='li_none'>
              <span class="span_fail"> 비밀번호를 다시 입력해주세요.</span>
            </li>
            <li class='li_none'>
              <button type='button' 
                    onclick="history.back();"
                    class="btn btn-secondary btn-sm">다시 입력</button>
                    
              <button type='button' 
                    onclick="location.href='/'"
                    class="btn btn-secondary btn-sm">취소</button>
            </li>
        </c:when>
        
        <c:when test="${param.code == 'id_find_success'}">
        <li class='li_none'>
            <span class="span_success"> 회원님의 아이디는 
                <script>
                    var id = "${param.id}";
                    var maskedID = id.substring(0, 8);
                    for (var i = 8; i < id.length; i++) {
                        maskedID += "*";
                    }
                    document.write(maskedID);
                </script> 
                입니다.
            </span>
        </li>
            <li class='li_none'>
                <button type='button' 
                    onclick="location.href='/';"
                    class="btn btn-secondary btn-sm">확인</button>
            </li>  
        </c:when>
        
        <c:when test="${param.code == 'id_find_fail'}">
            <li class='li_none'>
              <span class="span_fail"> 이름/전화번호를 다시 입력해주세요.</span>
            </li>
            <li class='li_none'>
              <button type='button' 
                    onclick="history.back();"
                    class="btn btn-secondary btn-sm">다시 입력</button>
              <button type='button' 
                    onclick="location.href='/'"
                    class="btn btn-secondary btn-sm">취소</button>
            </li>
        </c:when>
        
        <c:when test="${param.code == 'passwd_find_success'}">
            <li class='li_none'>
              <span class="span_success"> 아이디(메일)로 비밀번호를 전송하였습니다.</span>
            </li>
            <li class='li_none'>
              <button type='button' 
                    onclick="location.href='/'"
                    class="btn btn-secondary btn-sm">확인</button>
             </li>
        </c:when>
        
        <c:when test="${param.code == 'passwd_find_fail'}">
            <li class='li_none'>
              <span class="span_fail"> 아이디/이름/전화번호를 다시 입력해주세요.</span>
            </li>
            <li class='li_none'>
              <button type='button' 
                    onclick="history.back();"
                    class="btn btn-secondary btn-sm">다시 입력</button>
              <button type='button' 
                    onclick="location.href='/'"
                    class="btn btn-secondary btn-sm">취소</button>
             </li>
        </c:when>
        
        <c:otherwise>
          <li class='li_none_left'>
            <span class="span_fail">알 수 없는 에러로 작업에 실패했습니다.</span>
          </li>
          <li class='li_none_left'>
            <span class="span_fail">다시 시도해주세요.</span>
          </li>
        </c:otherwise>
        
      </c:choose>
      <li class='li_none'>
        <br>
        <c:choose>
            <c:when test="${param.cnt == 0 }">
                <button type='button' onclick="history.back()" class="btn btn-secondary btn-sm">다시 시도</button>    
            </c:when>
        </c:choose>
        
        <%-- <a href="./list_by_genreno.do?genreno=${param.genreno}" class="btn btn-secondary">목록</a> --%>
        <%-- <button type='button' onclick="location.href='./list_by_genreno_search.do?genreno=${param.genreno}'" class="btn btn-secondary">목록</button> --%>
        <%-- <button type='button' onclick="location.href='./list_by_genreno_search_paging.do?genreno=${param.genreno}'" class="btn btn-secondary">목록</button> --%>

      </li>
    </ul>
  </fieldset>

</div>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

