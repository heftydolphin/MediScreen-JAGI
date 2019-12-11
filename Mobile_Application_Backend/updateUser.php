<?php
	
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		$emailHolder = $_POST['emailHolder'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		$fName = $_POST['fName'];
		$lName = $_POST['lName'];
		$memberNumber = $_POST['memberNumber'];
		$policyNumber = $_POST['policyNumber'];
		
		$user = array(  
			'email'=>$email,
			'password'=>$password,   
			'fName'=>$fName,  
			'lName'=>$lName, 
			'memberNum'=>$memberNumber, 
			'policyNum'=>$policyNumber); 
		
		// Connect to MySQL database
		require_once 'dbConnect.php';
		
		$updateQuery  = "UPDATE insurer 
						SET email=?, password=?, fName=?, lName=?, insuranceMemberNum=?, insurancePolicyNum=? 
						WHERE email=?";
						
		if($stmt = $conn->prepare($updateQuery)){
			
			$stmt->bind_param("ssssii", $email, $password, $fName, $lName,
							$memberNumber, $policyNumber);
			$stmt->execute();	
			

		}
		
		if($stmt -> affected_rows == 1){
			
			$result["success"] = 1;
			$result["message"] = "PHP(updateUser): User updated in database successfully";
			$result["user"] = $user;
			
			echo json_encode($result);	
			mysqli_close($conn);
		}
		else{
			$result["success"] = 0;
			$result["message"] = "PHP(registerUser): Failed to update user";
			
			echo json_encode($result);	
			mysqli_close($conn);
		}
		
	}
?>