<?php
	
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		$emailHolder = trim($_POST['emailHolder']);
		$email = trim($_POST['email']);
		$password = trim($_POST['password']);
		$fName = trim($_POST['fName']);
		$lName = trim($_POST['lName']);
		$memberNumber = trim($_POST['memberNum']);
		$policyNumber = trim($_POST['policyNum']);
		
		$user = array(  
			'email'=>$email,
			'password'=>$password,   
			'fName'=>$fName,  
			'lName'=>$lName, 
			'memberNum'=>$memberNumber, 
			'policyNum'=>$policyNumber); 
		
		// Connect to MySQL database
		require_once 'dbConnect.php';
		
		$updateQuery  = "UPDATE patient
						SET email=?, password=?, fName=?, lName=?, insuranceMemberNum=?, insurancePolicyNum=? 
						WHERE email=?";
						
		if($stmt = $conn->prepare($updateQuery)){
			
			$stmt->bind_param("ssssiis", $email, $password, $fName, $lName,
							$memberNumber, $policyNumber, $emailHolder);
			$stmt->execute();	
		}
		
		if($conn->affected_rows >= 1){
			
			$result["success"] = 1;
			$result["message"] = "PHP(updateUser): User updated in database successfully";
			$result["user"] = $user;
			
			echo json_encode($result);	
		}
		else{
			$result["success"] = 0;
			$result["message"] = "PHP(updateUser): Failed to update user";
			$result["user"] = $user;

			echo json_encode($result);	
		}	
	}
?>