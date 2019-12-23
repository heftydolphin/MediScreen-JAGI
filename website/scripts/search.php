<?php

// Initialize the session
session_start();
 
// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: scripts/login.php");
    exit;
}

// Define variables and initialize to empty
$searchterm = "";
$searchterm_err = "";

// Retrieve data from form, validate it and then insert into relevant tables
if($_SERVER["REQUEST_METHOD"] == "POST"){
	
	// Validate search query
	if(empty(trim($_POST["searchterm"]))){
		$searchterm_err = "Please enter a patient ID, name or email.";     
	} else{
		$searchterm = trim($_POST["searchterm"]);
	}
	
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
	<link rel="stylesheet" href="/website/scripts/navstyle.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
    <style type="text/css">
        .wrapper{
            width: 650px;
            margin: 0 auto;
        }
        .page-header h2{
            margin-top: 0;
        }
        table tr td:last-child a{
            margin-right: 15px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();   
        });
    </script>
</head>
<body>
	<nav class="navbar2">
		
			<h1><a id="title" href="/website/dashboard.php">Mediscreen Portal</a></h1>
			
			<div class="nav">
			<ul class="re-log">
				<a href="#" onclick="history.go(-1)">Back</a> |
				<a href="scripts/logout.php">Logout</a>
			</ul>
			</div>
			
	</nav>
    <div class="wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="page-header clearfix" style="margin-top: 10rem;">
                        <h2 class="pull-left">Search Results</h2>
                    </div>
                    <?php
                    // Include config file
                    require_once __DIR__ . "/config.php";
					
					$stmt = $conn->prepare("SELECT * FROM patient WHERE patient_no LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR email LIKE ?");
					$stmt->bind_param('ssss', $searchterm, $searchterm, $searchterm, $searchterm);
					$stmt->execute();
					$result = $stmt->get_result();
							
					if(mysqli_num_rows($result) > 0){
						echo "<table class='table table-bordered table-striped'>";
							echo "<thead>";
								echo "<tr>";
									echo "<th>Patient ID</th>";
									echo "<th>Last Name</th>";
									echo "<th>Email</th>";
									echo "<th>GP Name</th>";
								echo "</tr>";
							echo "</thead>";
							echo "<tbody>";
							while($row = mysqli_fetch_array($result)){
								echo "<tr>";
									echo "<td>" . $row['patient_no'] . "</td>";
									echo "<td>" . $row['last_name'] . "</td>";
									echo "<td>" . $row['email'] . "</td>";
									echo "<td>" . $row['gp_name'] . "</td>";
								echo "</tr>";
							}
							echo "</tbody>";                            
						echo "</table>";
						// Free result set
						mysqli_free_result($result);
					} else{
						echo "<p class='lead'><em>No records were found for that search term.</em></p>";
					}
					
                    // Close connection
                    mysqli_close($conn);
                    ?>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>