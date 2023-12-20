<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>http://localhost:9093/index.jsp</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<body>
<c:import url="/menu/top.do" />
       <c:choose>
      <c:when test="${sessionScope.id == null}"> <%-- 로그인 안된 경우 기본 이미지만 출력 --%>
        <div style="width: 70%; margin: 30px auto;">
           <img src="/images/web_main.jpg" style="width: 100%;">
         </div>
      </c:when>
      <c:otherwise>
        <DIV style='width: 100%; margin: 30px auto; text-align: center;'> <%-- 로그인된 경우 추천 --%>
          <c:if test="${sessionScope.id != null}">
            <DIV style='width: 70%; margin: 10px auto; text-align: left;'>
              <h4>${sessionScope.mname} 님을 위한 최근 등록 영화</h4>
              <c:import url="/recommend/list_all.do" />  <%-- 좋아요가 높은 상품 --%>
            </DIV>
            
            <DIV style="clear: both; height: 20px;"></DIV>
          
            <DIV style='width: 70%; margin: 10px auto; text-align: left;'>
              <h4>${sessionScope.mname} 님을 위한 추천 많은 영화</h4>
              <c:import url="/recommend/recom_good.do" />  <%-- 좋아요가 높은 상품 --%>
            </DIV>
            
            <DIV style="clear: both; height: 20px;"></DIV>
            
            <DIV style='width: 70%; margin: 10px auto; text-align: left;'>
              <h4>${sessionScope.mname} 님을 위한 조회수가 높은 영화</h4>
              <c:import url="/recommend/recom_cnt.do" />
            </DIV>
            
            <DIV style="clear: both; height: 20px;"></DIV>
            
          </c:if>
        </DIV>
      </c:otherwise>
  </c:choose>
<jsp:include page="./menu/bottom.jsp" flush='false' />   
</body>
</html>