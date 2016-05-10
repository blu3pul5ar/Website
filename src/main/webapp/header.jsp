<%-- 
    Document   : header
    Created on : Mar 21, 2016, 4:19:59 PM
    Author     : Nicholas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            #header{
                    background-color: black;
                    color: white;
                    height: 100px;
                    font-size: 45px;
                    text-align: center;
                    vertical-align: middle;
                    margin-left: 35%;
                    margin-top: 25px;
                    float: left;
                }
        #signInForm{
                    width: 300px;
                    background-color: black;
                    color: black;
                    float: right;
                    height:100px;
                }
                #right{
                    float: right;
                }
        nav input{
                width: 90%;
            }
        </style>
    </head>
    <nav>
        <div id="header">
           Nicks Awesome Anime Store!
        </div>
        <form id="signInForm" role="form" method='POST' action="<c:url value='j_spring_security_check' />">
            <sec:csrfInput />
            <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
            <div class="col-sm-6" id="right">
                <h3 style="font-weight: 200; color: white;">Sign in </h3>
                <div>
                    <input id="j_username" name="j_username" placeholder="Email address" type="text" autofocus />
                    <input id="j_password" name="j_password" type="password" placeholder="password" />
                    <input class="btn btn-warning" name="submit" type="submit" value="Sign in" />
                </div>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_USER')">
                <input name="edit" value="Go To Cart" type="button" class="btn-primary" id="animeId" onclick="location.href='ShoppingCartController?taskType=getCart'"/>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_MGR','ROLE_USER')">
            <div style='color: white;'>Logged in as: <sec:authentication property="principal.username"></sec:authentication> ::
                <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Me Out</a></div>
        </sec:authorize>  
            </div>
        </form>
    </nav>
</html>
