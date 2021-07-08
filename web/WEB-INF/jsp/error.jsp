<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Error :O</title>
</head>
<body>
<h1>Epic fail</h1>
<%--<c:if test="${! empty sessionScope.error}">--%>
    <h2>${sessionScope.error}</h2>
<%--</c:if>--%>
</body>
</html>
