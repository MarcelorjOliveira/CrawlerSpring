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
        <title>Login HALYEN</title>
    </head>
    <body>
        <br>
        <br>
        <form action ="<%= Routes.loginAct%>" method="post">
           Login:  <input type ='text' name="login"/> <br/>
           Senha: <input type ="password" name ="password"/> <br/>
           <input type ="submit" value="Entrar"/>
        </form><a href ="<%= Routes.loginNew %>" > Cria Login </a>
    </body>
</html>
