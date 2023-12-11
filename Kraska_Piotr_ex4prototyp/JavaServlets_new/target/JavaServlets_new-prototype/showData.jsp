<%-- 
    Document   : showData
    Created on : 28 lis 2023, 20:20:38
    Author     : Piotr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Return Data</title>
        <!--<style>
        </style>
        -->
    </head>
    <body>
        <h2>Data Panel</h2>
        <h1>Data from Servlet</h1>
        <div>
            
         <div class="menu">
        <h2>Statistics Spotify</h2>
        <form action="StatisticsSpotifyServlet" method="post">
            <label>Generate Report:</label>
            <button type="submit" name="action" value="generateReport">Generate Report</button>
            <hr>
            Show Most Popular Artist:
            <button type="submit" name="action" value="showMostPopularArtist">Show Most Popular Artist</button>
            <hr>
            <label>Show Least Popular Artist:</label>
            <button type="submit" name="action" value="showLeastPopularArtist">Show Least Popular Artist</button>
            <hr>
            <label>Show Most Popular Song:</label>
            <button type="submit" name="action" value="showMostPopularSong">Show Most Popular Song</button>
            <hr>
            <label>Show Least Popular Song:</label>
            <button type="submit" name="action" value="showLeastPopularSong">Show Least Popular Song</button>
            <hr>
            <label>Spearman Correlation:</label>
            <button type="submit" name="action" value="spearmanCorrelation">Spearman Correlation</button>
            <hr>
                        <!-- Add the combobox here -->
            <label>Select Continent:</label>
            <select name="continent" id="continentSelect" onchange="disableComboBox()">
                <option value="EU">EU</option>
                <option value="NA">NA</option>
                <option value="AF">AF</option>
                <option value="AS">AS</option>
                <option value="WorldWide">WorldWide</option>
            </select>
            </form>
            <hr>
        
            <form action="ShowReportServlet" method="post">
            <label>Show Report:</label>
            <button type="submit" name="action" value="showReport">Show Report</button>
            </form>
            <hr>
            <form action="ShowDatabaseServlet" method="post">
            <label>Show Details About Data:</label>
            <button type="submit" name="action" value="showDetails">Show Details About Data</button>
            </form>
            <hr>
            <form action="HistoryServlet" method="post">
            <label>Check History:</label>
            <button type="submit" name="action" value="history">Show History</button>
            <hr>
            </form>
            <!--
            <script>
                function disableComboBox() {
                    // Disable the combobox after selection
                    document.getElementById("continentSelect").disabled = true;
                }
            </script>
            -->
    </div>
            <!-- Add the output element to display the result -->
            Result:
            <output>${result}</output>
            </div> 
    </body>
</html>
