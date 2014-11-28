<%-- 
    Document   : login
    Created on : 04/11/2014, 06:23:40
    Author     : marcelo
--%>

<%@page import="br.com.crawlerspring.controller.Routes"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crawler Spring</title>
    </head>
    <body>
        <center><h1>Crawler Spring</h1></center>
        <br>
        <br>
        <form action ="<%= Routes.mainAct %>" method="post">
            <center> <input type ='text' name="parameters"/> <input type ="submit" value="Entrar"/> </center>
        </form>
    </body>
</html>
