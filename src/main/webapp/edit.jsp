<%-- 
    Document   : edit
    Created on : Mar 17, 2016, 9:01:27 PM
    Author     : Nicholas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Anime</title>
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
        height: 350px;
        box-shadow: 10px 10px 5px #888;
        background-color: white;
        color: black;
    }
.container input {
    width: 100%;
    clear: both;
    text-align: center;
}.container select{
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
        <form method="post" name="myForm" onsubmit="return validateForm()" action=<%= response.encodeURL("AnimeController?taskType=save")%>>
            <label>ID:</label>
            <input type="text" readonly name="animeId" value="${anime.productId}" />
            <br><label>Name:</label>
            <input type="text" name="animeName" value="${anime.productName}"/>
            <br><label>Category:</label>
            <select name="animeCategory">
                <option value="Action" ${anime.productCategory == 'Action' ? 'selected' : ''}>Action</option>
                <option value="Comedy"${anime.productCategory == 'Comedy' ? 'selected' : ''}>Comedy</option>
                <option value="Slice of Life"${anime.productCategory == 'Slice of Life' ? 'selected' : ''}>Slice of Life</option>
                <option value="RomCom"${anime.productCategory == 'RomCom' ? 'selected' : ''}>RomCom</option>
                <option value="Sports"${anime.productCategory == 'Sports' ? 'selected' : ''}>Sports</option
                <option value="Drama"${anime.productCategory == 'Drama' ? 'selected' : ''}>Drama</option>
                <option value="Psychological"${anime.productCategory == 'Psychological' ? 'selected' : ''}>Psychological</option>
                <option value="Mecha"${anime.productCategory == 'Mecha' ? 'selected' : ''}>Mecha</option>
                <option value="Fantasy"${anime.productCategory == 'Fantasy' ? 'selected' : ''}>Fantasy</option>
            </select>
            <br><label>Price:</label>
            <input type="number" step="0.01" min="0.00" name="price" value="${anime.productPrice}" />
            <label>Set Stock</label>
            <input type="number" step="1" min="0" name="stock" value="${anime.productStock}"/>
            <label>Set the studio</label>
            <select name="studioId">
                <c:forEach items="${dropDownStudios}" var="items">
                    <option value="${items.studioId}"${anime.productStudio.studioId == items.studioId ? 'selected' : ''}>${items.studioName}
                    </option>
                </c:forEach>
            </select>
            <br><label>Image URL:</label>
            <input type="text" name="image" value="${anime.productImage}"/>
            <br>
            <input type="submit" value="save" id="top" class="btn-success"/>
            <input type="button" value="cancel" onclick="location.href='AnimeController?taskType=cancel'" class="btn-danger"/>
        </form>
    </div>
    </body>
</html>
