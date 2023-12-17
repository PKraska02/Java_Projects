<%-- 
    Document   : calculationHistory
    Created on : 8 gru 2023, 22:40:47
    Author     : Piotr
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Calculation History</title>
</head>
<body>
    <h2>Calculation History</h2>
    <ul>
        <c:forEach var="entry" items="${requestScope.calculationHistory}">
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


