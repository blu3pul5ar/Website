<%-- 
    Document   : add
    Created on : Mar 17, 2016, 5:40:36 PM
    Author     : Nicholas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Anime</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    </head>
    <script>
        function validateForm() {
            var x = document.forms["myForm"]["animeName"].value;
            if (x === null || x === "") {
            alert("Name must be filled out");
            return false;
        }
            x = document.forms["myForm"]["price"].value;
            if (x === null || x === "") {
            alert("Price must be filled out");
            return false;
        }
            x = document.forms["myForm"]["image"].value;
            if (x === null || x === "") {
            alert("Image must be filled out");
            return false;
        }
    }
    </script>
    <style>
    html{
        background-color: black;
    }
    .container {
    width: 500px;
    clear: both;
    position: absolute;
    margin: auto;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    height: 300px;
    box-shadow: 10px 10px 5px #888;
    background-color: white;
    color: black;
}
.container input {
    width: 100%;
    clear: both;
    color: black;
    text-align: center;
}
.container select{
    width: 100%;
    clear: both;
    text-align: center;
}
#top{
    margin-top: 15px;
}
    </style>
     <header>
        <%@ include file="header.jsp"%>
    </header>
    <body>
        <div class="container">
        <form method="post" name="myForm" onsubmit="return validateForm()" action=<%= response.encodeURL("AnimeController?taskType=new")%>>
            <label>Enter the name of the anime</label>
            <input type="text" name="animeName"/>
            <label>Select the category</label>
            <select name="animeCategory">
                <option value="Action">Action</option>
                <option value="Comedy">Comedy</option>
                <option value="Slice of Life">Slice of Life</option>
                <option value="RomCom">RomCom</option>
                <option value="Sports">Sports</option
                <option value="Drama">Drama</option>
                <option value="Psychological">Psychological</option>
                <option value="Mecha">Mecha</option>
                <option value="Fantasy">Fantasy</option>
            </select>
            <label>Set the price</label>
            <input type="number" step="0.01" min="0" name="price"/>
            <label>Set Stock</label>
            <input type="number" step="1" min="0" name="stock"/>
            <label>Set the studio</label>
            <select name="studioId">
                <c:forEach items="${dropDownStudios}" var="items">
                    <option value="${items.studioId}">${items.studioName}</option>
                </c:forEach>
            </select>
            <label>Enter image location</label>
            <input type="text" name="image"/>
            <input type="submit" value="save" id="top" class="btn-success"/>
            <input type="button" value="cancel" onclick="location.href='AnimeController?taskType=cancel'" class="btn-danger"/>
        </form>
        </div>
    </body>
</html>
