<%-- 
    Document   : signUp
    Created on : May 6, 2016, 6:19:15 PM
    Author     : Nicholas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    </head>
    <style>
            html{
                background-color: black;
            }
            body{
                background-color: black;
            }
            body input{
                width: 90%;
                color:black;
            }
            .container {
                width: 20%;
                height: 100px;
                position: absolute;
                top:0;
                bottom: 0;
                left: 0;
                right: 0;
                margin: auto;
                color: white;
                
            }
            
            
        </style>
    <header>
        <%@ include file="header.jsp"%>
    </header>
    <body>
        <div class="container" id="top">
        <form method="POST" action=<%= response.encodeURL("SignUpController?taskType=newUser")%>>
            <label>Enter your Email:</label>
            <input type="email" name="email" />
            <label>Enter a Password:</label>
            <input type="password" name="password"/>
            <input type="submit" value="Submit" class="btn-primary"/>
        </form>
        </div>
    </body>
</html>
