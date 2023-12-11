<%-- 
    Document   : Report
    Created on : 11 gru 2023, 21:43:47
    Author     : Piotr
--%>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
/**
 * A view for displaying reports.
 * @author Piotr
 * @version 2.0
 */
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display File Data</title>
</head>
<body>
    <h2>File Data:</h2>
    <textarea id="fileData" rows="30" cols="50">${result}</textarea>
    

    <form action="ShowReportServlet" method="post">
    </form>
    <button onclick="cancelReading()">Cancel</button>
    <script>
        function cancelReading() {
            window.history.back();
        }
    </script>
</body>
</html>
