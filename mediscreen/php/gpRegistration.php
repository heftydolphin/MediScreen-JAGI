<?php
	
	$username = $_POST['username'];	
	$fName = $_POST['fNameInput'];
	$lName = $_POST['lNameInput'];
	$practice_address1 = $_POST['addressInput1'];
	$practice_address2 = $_POST['addressInput2'];
	$practice_address3 = $_POST['addressInput3'];
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
		$insert = $conn->prepare("INSERT INTO gp(username, fName, lName, practiceAddress1,
								practiceAddress2, practiceAddress3, telNo,
								email, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
		$insert->bind_param("ssssssiss", $username, $fName, $lName, $practice_address1,
							$practice_address2, $practice_address3, 
							$telNo, $email, $password);
		$insert->execute();
		$insert->close();
		$conn->close();
	}
	include '../html/userHomePage.html';
?>