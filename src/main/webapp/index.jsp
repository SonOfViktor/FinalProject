<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="localVisitor">VISITOR</c:set>
<html>
  <body>
  <c:choose>
    <c:when test="${sessionScope.role == localVisitor}">
      <jsp:forward page="ProjectServlet?command=to_login_page_command"/>
    </c:when>
    <c:otherwise>
      <jsp:forward page="ProjectServlet?command=to_home_page_command"/>
    </c:otherwise>
  </c:choose>
  </body>
</html>