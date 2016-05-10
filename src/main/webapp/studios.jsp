<%-- 
    Document   : Anime
    Created on : Mar 17, 2016, 12:18:27 PM
    Author     : Nicholas
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Anime Store</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <style>
            html{
                background-color: black;
            }
            body{
                background-color: black;
            }
            body input{
                width: 90%;
            }
            .container {
                width: 90%;
                clear: both;
                position: absolute;
                margin: auto;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                box-shadow: 10px 10px 5px #888;
                background-color: white;
                color: black;
                overflow: auto;
            }
            .container table{
                margin-top: 25px;
                margin-bottom: 25px;
            }
            #top{
                margin-top: 50px;
            }
        </style>
    </head>
     <header>
        <%@ include file="header.jsp"%>
    </header>
    <body>
    <center><input type="button" name="goback" value="Go Back" class="btn-primary" id="top" onclick="location.href='index.jsp'"/></center>
    <sec:authorize access="hasAnyRole('ROLE_MGR')"><center><input type="submit" name="" value="Add Studio" class="btn-danger" id="animeId" onclick="location.href='StudioController?taskType=add'"/></center></sec:authorize>
        <div class="container">
    <table style="background-color: ${table}">
        <thead>
        <tr>
            <th>
                Studio Name
            </th>
            <sec:authorize access="hasAnyRole('ROLE_MGR')">
            <th>Edit</th>
            <th>Delete</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${studios}" var="item">
                <tr>
                    <td> ${item.studioName} </td>
                    <sec:authorize access="hasAnyRole('ROLE_MGR')">
                        <td>
                            <input name="edit" value="Edit" type="button" class="btn-primary" id="animeId" onclick="location.href='StudioController?taskType=edit&id=${item.studioId}'"/>
                        </td>
                        <td>
                            <input type="submit" name="" value="Delete" class="btn-danger" id="animeId" onclick="location.href='StudioController?taskType=delete&id=${item.studioId}'"/>
                        </td>
                    </sec:authorize>
                    </tr>
            </c:forEach>
        </tbody>
    </table>
        </div>
</body>
    <style>
        td,th{
            color: ${text};
        }
    </style>
        <script src="//code.jquery.com/jquery-1.12.0.min.js"/>
</html>