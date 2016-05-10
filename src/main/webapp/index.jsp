<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title>Anime Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link href="bgrins-spectrum-98454b5/spectrum.css" rel="stylesheet" type="text/css"/>
    </head>
    <style>
    html{
        background-color: black;
    }
    .container {
    width: 450px;
    clear: both;
    position: absolute;
    margin: auto;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    height: 250px;
    box-shadow: 10px 10px 5px #888;
    background-color: white;
    color: black;
    text-align: center;
}
.container input {
    width: 100%;
    clear: both;
}</style>
    <header>
        <%@ include file="header.jsp"%>
    </header>
    <body>
        <div class="container">
            <c:if test="${msg!=null || msg !=''}">
            <h3>${msg}</h3>
        </c:if>
            <h2>View The Store</h2>
                <form method="POST" action="<%= response.encodeURL("AnimeController?taskType=viewAnime")%>">
                    <button type="submit" class="btn btn-primary">View Anime Store</button>
                </form><br>
                <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
                    <form method="POST" action="<%= response.encodeURL("SignUpController?taskType=SignUp")%>">
                       <button type="submit" class="btn btn-primary">Sign Up!</button>                     
                    </form>
                </sec:authorize>
                    <br>
                <sec:authorize access="hasAnyRole('ROLE_MGR')">
                <form method="POST" action="<%= response.encodeURL("StudioController?taskType=view")%>">
                    <button type="submit" class="btn btn-primary">View All Studios</button>
                </form><br>
                    <form method="POST" action="<%= response.encodeURL("SignUpController?taskType=SignUp")%>">
                    <button type="submit" class="btn btn-primary">Create new User</button>
                </form>
                </sec:authorize>
        </div>
        <script src="https://code.jquery.com/jquery-latest.min.js"></script>
        <script src="bgrins-spectrum-98454b5/spectrum.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>