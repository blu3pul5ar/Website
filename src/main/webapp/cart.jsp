<%-- 
    Document   : cart
    Created on : May 6, 2016, 3:37:02 PM
    Author     : Nicholas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <header>
        <%@ include file="header.jsp"%>
    </header>
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
                width: 25%;
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
    <body>
         <center><input type="button" name="goback" value="Go Back" class="btn-primary" id="top" onclick="location.href = 'AnimeController?taskType=viewAnime'"/></center>
         <div class="container">
        <div>Total: ${total}</div>
        <input type="button" value="Buy Now!"/>
        <table>
            <c:forEach items="${cart}" var="item">
                <tr>
                    <td> ${item.productName} </td>
                    <td> ${item.productCategory}</td>
                    <td>${item.productPrice}</td>
                    <td><a href="AnimeController?taskType=viewByStudio&id=${item.productStudio.studioId}">${item.productStudio.studioName}</a></td>
                    <td><input type="submit" name="" value="Remove From Cart" class="btn-danger" id="animeId" onclick="location.href='ShoppingCartController?taskType=Remove&id=${item.productId}'"/></td>
                    </tr>
            </c:forEach>
        </table>
         </div>
    </body>
</html>
