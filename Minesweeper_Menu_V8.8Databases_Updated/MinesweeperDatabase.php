<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
            <?php 
            //setting the background for the php page
            $profpic = "BackgroundCart.jpg";
            ?>
        <style type="text/css">

        body {
        background-color: black;
        color: white;
        }
        .datatable{
            font-color: white;
        }
        table, th, td {
            border: 1px solid white;
            color: white;
          }
        </style>
    </head>
        <body>
            <?php
            $PlayerName = trim($_COOKIE["playername"], '"'); 
            echo $PlayerName;
            echo"<br>";
            $ScoreTotal = trim($_COOKIE["scoretotal"], '"'); 
            echo $ScoreTotal;
            echo"<br>";
            $Time = trim($_COOKIE["time"], '"'); 
            echo $Time;        


            //Straight out of W3Schools
            $servername = "localhost";
            $username = "root";
            $password = "";
            $dbname = "test";
            // Create connection
            $conn = new mysqli($servername, $username, $password, $dbname);
            // Check connection
            if ($conn->connect_error) {
                    die("Connection failed: " . $conn->connect_error);
            } 
            echo "Connected successfully";


            //Query the database
            $sql="SELECT `Minesweeper-ID`, `PlayerName`, `ScoreTotal`, `time` FROM `rnaime_entity_minesweeper`;";
            $result=$conn->query($sql);
            //$rs = mysqli_query($conn,$query);
            echo "<h1>High Score List</h1>";
            echo "<table class='datatable' border='1'>";
                echo "<tr><th>".'Minesweeper-ID'."</th>";
                echo "<th>".'PlayerName'."</th>";
                echo "<th>".'ScoreTotal'."</th>";
                echo "<th>".'Time'."</th></tr>";
            while($re = $result->fetch_assoc()){
                      echo "<tr><td>".$re['Minesweeper-ID']."</td>";
                      echo "<td>".$re['PlayerName']."</td>";
                      echo "<td>".$re['ScoreTotal']."</td>";
                      echo "<td>".$re['time']."</td></tr>";
            }
            echo "</table>";

                if($PlayerName != null && $ScoreTotal != null && $Time != null){

               $query = "INSERT INTO `test`.`rnaime_entity_minesweeper` (`PlayerName`, `ScoreTotal`, `time`)"
                        . " VALUES ('$PlayerName', '$ScoreTotal', '$Time');";

                if ($conn->query($query) === TRUE) {
                    echo "New record created successfully";
                } else {
                    echo "Error: " . $query . "<br>" . $conn->error;
                  }
                }
                else{
                    echo "oops please input all the information!";
               }
                echo "<br><br>";         


            //Query the database
            $sql="SELECT `Minesweeper-ID`, `PlayerName`, `ScoreTotal`, `time` FROM `rnaime_entity_minesweeper`;";
            $result=$conn->query($sql);
            //$rs = mysqli_query($conn,$query);
            echo "<h1>High Score List Updated!</h1>";
            echo "<table border='1'>";
                echo "<tr><th>".'Minesweeper-ID'."</th>";
                echo "<th>".'PlayerName'."</th>";
                echo "<th>".'ScoreTotal'."</th>";
                echo "<th>".'Time'."</th></tr>";
            while($re = $result->fetch_assoc()){
                      echo "<tr><td>".$re['Minesweeper-ID']."</td>";
                      echo "<td>".$re['PlayerName']."</td>";
                      echo "<td>".$re['ScoreTotal']."</td>";
                      echo "<td>".$re['time']."</td></tr>";
            }
            echo "</table>";

            //$result=$conn->query($query);
            ?>
        </body>
</html>
