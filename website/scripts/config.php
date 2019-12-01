<?php

# Define database credentials
define('host', 'localhost');
define('dbusername', 'root');
define('dbpassword', '');
define('dbname', 'medi');

# Connect to database
$conn = mysqli_connect(host, dbusername, dbpassword, dbname);

# Print error if connection cannot be made
if($conn === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

?>