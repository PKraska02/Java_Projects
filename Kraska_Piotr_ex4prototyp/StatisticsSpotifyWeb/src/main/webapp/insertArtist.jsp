<%-- 
    Document   : insertArtist
    Created on : 6 sty 2024, 22:10:44
    Author     : Piotr
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Artist</title>
</head>
<body>

    <h1>Add Artist</h1>

    <form action="InsertArtistServlet" method="post">
        <label for="index">Index:</label>
        <input type="number" id="index" name="index" required>
        <br>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" name="lastname" required>
        <br>
        <button type="submit">Add</button>
    </form>

    <br>
    <form action="javascript:history.go(-1)">
        <button type="submit">Back</button>
    </form>

</body>
</html>

