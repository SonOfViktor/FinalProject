<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="localVisitor">VISITOR</c:set>
<html>
<head>
  <script type="text/javascript">
    window.history.forward();
    function noBack()
    {
      window.history.forward();
    }
  </script>
</head>
  <body>
    <jsp:forward page="ProjectServlet?command=to_home_page"/>
  </body>
</html>