<?php
// Initialize the session
session_start();
 
// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: scripts/login.php");
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
				<a href="scripts/logout.php">Logout</a>
			</ul>
			</div>
			
	</nav>
	
	<section id="user-functions" class="functions">
	
	<form id="search-patient-form" action="/website/scripts/search.php" method="post">
	<div class="input-group mb-3" style="width: 96.5%; margin-left: 2rem; margin-right: 2rem;">
		<input type="text" class="form-control" name="searchterm" placeholder="Search for a patient by entering a patient ID, name or email..." onfocus="this.placeholder = ''" onblur="this.placeholder = 'Search for a patient by entering a patient ID, name or email...'">
		<div class="input-group-append">
			<input type="submit" class="btn btn-primary" name="searchquery" value="Search">
		</div>
	</div>
	</form>
	
	<div class="card-deck" style="margin-top: 1rem; margin-left: 1rem; margin-right: 1rem;">
	
	<div class="card" style="width: 18rem;">
	  <div class="card-body">
		<h5 class="card-title">List GPs</h5>
		<p class="card-text">View a list of all general practitioners registered on the system.</p>
		<a href="scripts/ops/gp/list.php" class="btn btn-info">List GPs</a>
	  </div>
	</div>

	<div class="card" style="width: 18rem;">
	  <div class="card-body">
		<h5 class="card-title">List Insurers</h5>
		<p class="card-text">View a list of all insurance professionals registered on the system.</p>
		<a href="scripts/ops/ins/list.php" class="btn btn-info">List Insurers</a>
	  </div>
	</div>
	
	<div class="card" style="width: 18rem;">
	  <div class="card-body">
		<h5 class="card-title">List Patients</h5>
		<p class="card-text">View a list of all patients registered on the system.</p>
		<a href="scripts/ops/pat/list.php" class="btn btn-info">List Patients</a>
	  </div>
	</div>

  </div>
  
 
</div>
	
	</section>
    
</body>
</html>