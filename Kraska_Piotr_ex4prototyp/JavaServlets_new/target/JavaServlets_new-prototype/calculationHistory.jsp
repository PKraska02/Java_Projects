<%-- 
    Document   : calculationHistory
    Created on : 8 gru 2023, 22:40:47
    Author     : Piotr
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculation History</title>
</head>
<body>
    <h2>Calculation History</h2>
    <ul>
        <c:forEach var="entry" items="${calculationHistory}">
            <li>${entry}</li>
        </c:forEach>
    </ul>

    <button onclick="cancel()">Cancel</button>

    <script>
        function cancel() {
            window.history.back();
        }
    </script>
</body>
</html>


