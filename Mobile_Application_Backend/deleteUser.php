
<?php

	if($_SERVER['REQUEST_METHOD'] == 'POST'){

		$email = trim($_POST['email']);

		require_once 'dbConnect.php';
			
		$deleteQuery  = "DELETE FROM patient
						WHERE email=?";
						
		if($stmt = $conn->prepare($deleteQuery)){
			
			$stmt->bind_param("s", $email);
			$stmt->execute();	
		}
		
		if($conn->affected_rows >= 1){
			
			$result["success"] = 1;
			$result["message"] = "PHP(deleteUser): User deleted from database successfully";
			
			echo json_encode($result);	
		}
		else{
			$result["success"] = 0;
			$result["message"] = "PHP(deleteUser): Failed to delete user";
			echo json_encode($result);	
		}
	}
?>