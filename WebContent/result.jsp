<%@ page import="java.util.List,java.util.Map,com.policedata.objects.Objects.CrimesAtLocation" %>
<%
  Map<String, List<CrimesAtLocation>> crimeDict = (Map<String, List<CrimesAtLocation>>) request.getAttribute("crimeDict");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Police Data</title>
  <meta name="description" content="">
  <meta name="author" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/skeleton.css">
  <link rel="icon" type="image/png" href="images/favicon.png">
</head>
<body onload="plotPointsOnMap()">
  <div class="container">
    <div class="row" style="margin-top: 2em; border-bottom: 1px solid black;">
      <div class="three columns offset-by-one columns" style="text-align: center;">
        <a class="header-link" href=".."><h4>Police Data</h4></a>
      </div>
      <div class="five columns offset-by-one columns">
        <form action="out" method="GET">
          <input type="text" autocomplete="off" name="postcode" id="postcode" value="${postcode}" style="text-transform: uppercase;" />
          <input type="submit" value="Go" />
        </form>
    </div>
    <div class="row">
      <div class="six columns offset-by-three" style="text-align: center;">
        <h3>Showing data for postcode: <span style="text-transform: uppercase; font-weight: bold">${postcode}</span></h3>
      </div>
    </div>
    <div class="row">
      <div class="six columns offset-by-three" style="text-align: center">
        <h4>You are served by the <span style="text-decoration: underlined;">${force}</span> force</h4>
      </div>
    </div>
    <div class="row">
      <div class="five columns offset-by-one columns" style="text-align: center;">
        <h6>Crime in last 12 months</h6>
        <ul style="list-style: none;">
          <% for (Map.Entry<String, List<CrimesAtLocation>> item : crimeDict.entrySet()){ %>
          <li><%= item.getKey() %>: <%= item.getValue().size() %></li>
          <% } %>
        </ul>
      </div>
    </div>
</body>
</html>
