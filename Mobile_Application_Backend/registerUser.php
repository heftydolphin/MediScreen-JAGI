<?php

	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		$email = $_POST['email'];
		$password = $_POST['password'];
		$fName = $_POST['fName'];
		$lName = $_POST['lName'];
		$memberNumber = $_POST['insuranceMemberNum'];
		$policyNumber = $_POST['insurancePolicyNum'];
		
		require_once 'dbConnect.php';
		
				$insertQuery  = "INSERT INTO patient(email, password, fName, lName,
							insuranceMemberNum, insurancePolicyNum) VALUES (?,?,?,?,?,?)";
								
		if($stmt = $conn->prepare($insertQuery)){
			
			$stmt->bind_param("ssssii", $email, $password, $fName, $lName,
							$memberNumber, $policyNumber);
			$stmt->execute();	
		}
		
		if($stmt -> affected_rows == 1){
			
			$result["success"] = 1;
			$result["message"] = "PHP(registerUser): User stored in database";
			
			echo json_encode($result);	
			mysqli_close($conn);
		}
		else{
			$result["success"] = 0;
			$result["message"] = "PHP(registerUser): Failed to insert user";
			
			echo json_encode($result);	
			mysqli_close($conn);
		}
	}
?>