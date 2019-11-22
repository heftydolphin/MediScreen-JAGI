<?php
// Initialize the session
session_start();
 
// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: ../php/userLogin.php");
    exit;
}
?>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
 
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="stylesheet.css">
  
</head>
<body>

	<nav class="navbar2">
		
			<h1><a id="title" href="/website/dashboard.php">Mediscreen Portal</a></h1>
			
			<div class="nav">
			<ul class="nav-links">
				<h1><b><?php echo htmlspecialchars($_SESSION["email"]); ?></b></h1>
			</ul>
			
			<ul class="re-log">
				<a href="scripts/reset.php">Reset Password</a> |
				<a href="/website/scripts/logout.php">Logout</a>
			</ul>
			</div>
			
	</nav>
	
	<section id="user-functions" class="functions">
	
	<div class="card-deck" style="margin-top: 1rem; margin-left: 1rem; margin-right: 1rem;">
	
	<div class="card" style="width: 18rem;">
	  <div class="card-body">
		<h5 class="card-title">List Users</h5>
		<p class="card-text">View a list of all medical and insurance professionals registered on the system.</p>
		<a href="scripts/listUsers.php" class="btn btn-info">List Users</a>
	  </div>
	</div>

	<div class="card" style="width: 18rem;">
	  <div class="card-body">
		<h5 class="card-title">Search Patients</h5>
		<p class="card-text">Search from a list of patients registered to the system. You'll need their patient ID in order to access the list.</p>
		<a href="#" class="btn btn-info">Search Patients</a>
	  </div>
	</div>

  </div>
  
 
</div>
	
	</section>
    
</body>
</html>