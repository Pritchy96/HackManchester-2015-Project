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
<body>
  <div class="container">
    <div class="row">
      <div class="three columns offset-by-one columns" style="text-align: center;">
        <a class="header-link" href=".."><h4>Police Data</h4></a>
      </div>
      <div class="three columns offset-by-one columns" style="text-align: center;">
        <form action="out" method="GET">
          <input type="text" autocomplete="off" name="postcode" id="postcode" />
          <input type="submit" value="Go" />
        </form>
    </div>
    <div class="row">
      <div class="six columns offset-by-three" style="text-align: center;">
        <h3>Showing data for postcode: ${postcode}</h3>
      </div>
    </div>
    <div class="row">
      <div class="five columns offset-by-one columns" style="text-align: center;">
        <ul style="list-style: none;">
          <li>Force: ${force}</li>
          <li>Neighbourhood: ${neighbourhood}</li>
          <li>Longitude: ${longitude}</li>
          <li>Latitude: ${latitude}</li>
          <li>Waka: ${waka}</li>
          <li>Lol: ${lol}</li>
        </ul>
      </div>
      <div class="five columns" style="text-align: center;">
        <p>Placeholder</p>
      </div>
    </div>
</body>
</html>
