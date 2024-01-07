<%-- 
    Document   : loginStatus
    Created on : 17 gru 2023, 22:18:16
    Author     : Piotr
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Status</title>
</head>
<body>

    <h1>Login Status</h1>

    <c:if test="${not empty loginStatus}">
        <p>${loginStatus}</p>
    </c:if>
    <br>
    <form action="insertArtist.jsp" method="post">
        <button type="submit">Insert Artist</button>
    </form>
    <br>
    <form action="insertSong.jsp" method="post">
        <button type="submit">Insert Song</button>
    </form>
    <br>
    <form action="javascript:history.go(-1)">
        <button type="submit">Back</button>
    </form>

</body>
</html>


