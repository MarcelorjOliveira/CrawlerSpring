<%-- 
    Document   : view
    Created on : Nov 28, 2014, 7:19:22 AM
    Author     : marcelooliveira
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.com.crawlerspring.controller.Routes" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
        <h1>Results</h1>
        <c:forEach items="${documents}" var="document">
            ${document.title}
            <br>
            <br>
            ${document.content}
            <br>
            <br>
            <br>
        </c:forEach>
            <a href="<%= Routes.main %>">Back</a>
    </body>
</html>
