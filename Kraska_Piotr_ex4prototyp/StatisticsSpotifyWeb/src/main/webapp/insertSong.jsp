<%-- 
    Document   : insertSong
    Created on : 6 sty 2024, 22:13:10
    Author     : Piotr
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Song</title>
</head>
<body>

    <h1>Add Song</h1>

    <form action="InsertSongServlet" method="post">
    <label for="song_id">Song ID:</label>
    <input type="text" id="song_id" name="song_id" required>
    <br>
    <label for="songTitle">Song Title:</label>
    <input type="text" id="songTitle" name="songTitle" required>
    <br>
    <label for="artist_id">Artist ID:</label>
    <input type="text" id="artist_id" name="artist_id" required>
    <br>
    <label for="playsCount">Plays Count:</label>
    <input type="number" id="playsCount" name="playsCount" required>
    <br>
    <label for="continent">Continent:</label>
    <input type="text" id="continent" name="continent" required>
    <br>
    <label for="songTime">Song Time:</label>
    <input type="text" id="songTime" name="songTime" required>
    <br>
    <button type="submit">Add</button>
</form>


    <br>
    <form action="javascript:history.go(-1)">
        <button type="submit">Back</button>
    </form>

</body>
</html>

