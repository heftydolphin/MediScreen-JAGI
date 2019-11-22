<?php
	
	require_once "configDatabase.php";
	
	$fName = $_POST['fNameInput'];
	$lName = $_POST['lNameInput'];
	$telNo = $_POST['telNoInput'];
	$email = $_POST['emailInput'];
	$password = $_POST['passwordInput'];
	
	if(mysqli_connect_error()){
		
		die('Connect Error('.mysqli_connect_error().')'. mysqli_connect_error());
	}
	else{
		$insert = $conn->prepare("INSERT INTO insurer(fName, lName, telNo, email, password)
			values(?, ?, ?, ?, ?)");
			
		$insert->bind_param("ssiss", $fName, $lName, $telNo, $email, $password);
		$insert->execute();
		$insert->close();
		$conn->close();
	}
	include '../html/userHomePage.html';
?>