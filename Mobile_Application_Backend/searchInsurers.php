<?php

	// Retrieve data from form, validate it and then insert into relevant tables
	if($_SERVER["REQUEST_METHOD"] == "POST"){
		
		// Criteria to search for	
		$email = trim($_POST['email']);
		
		// Connect to MySQL database
		require_once 'dbConnect.php';
		
		// Query MySQL search query
		$searchQuery = "SELECT * FROM insurer WHERE email=?";   
		
		$stmt = $conn->prepare($searchQuery);
		$stmt->bind_param("s", $email);
		$stmt->execute();	
		$stmt_result = $stmt->get_result();
		
		if(mysqli_num_rows($stmt_result) > 0){
			
			$result["success"] = 1;
			$result["message"] = "PHP(searchInsurers): Insurer was found and an invitation to connect will be sent to them";
			
			echo json_encode($result);
		}
		
			else{
			$result["success"] = 0;
			$result["message"] = "PHP(searchInsurers): Failed to find Insurer in system";

			echo json_encode($result);	
		}
	}
?>