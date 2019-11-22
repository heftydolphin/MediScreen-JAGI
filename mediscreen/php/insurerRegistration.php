<?php

	$username = $_POST['username'];
	$fName = $_POST['fNameInput'];
	$lName = $_POST['lNameInput'];
	$telNo = $_POST['telNoInput'];
	$email = $_POST['emailInput'];
	$password = $_POST['passwordInput'];
	
	$HOST = "localhost";
	$DB_USERNAME = "root";
	$DB_PASSWORD = "admin";
	$DB_NAME = "mediscreen";
	
	$conn = new mysqli($HOST, $DB_USERNAME, $DB_PASSWORD, $DB_NAME);
	
	if(mysqli_connect_error()){
		
		die('Connect Error('.mysqli_connect_error().')'. mysqli_connect_error());
	}
	else{
		$insert = $conn->prepare("INSERT INTO insurer(username, fName, lName, telNo, email, password)
			values(?, ?, ?, ?, ?, ?)");
			
		$insert->bind_param("sssiss", $username, $fName, $lName, $telNo, $email, $password);
		$insert->execute();
		$insert->close();
		$conn->close();
	}
	include '../html/userHomePage.html';
?>