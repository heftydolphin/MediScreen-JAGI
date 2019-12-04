<?php

	if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
		$gender = $_POST['gender'];
		$age = $_POST['age'];
		$exercise = $_POST['exercise'];
		$bloodPressure = $_POST['bloodPressure'];
		$pastConditions = $_POST['pastConditions'];
		$glucose = $_POST['glucose'];
		$familyHistory = $_POST['familyHistory'];
		$cholesterol = $_POST['cholesterol'];
		$height = $_POST['height'];
		$weight = $_POST['weight'];
		$patientID = $_POST['patientID'];
		
		require_once 'dbConnect.php';
		
		$insertQuery  = "INSERT INTO 
							medicalHistory (gender, age, exercise, bloodPressure, pastConditions, glucose, familyHistory, cholesterol, height, weight, patientID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
								
		if($stmt = $conn->prepare($insertQuery)){
			
			$stmt->bind_param("sssssssssiis", $gender, $age, $exercise, $bloodPressure, $pastConditions, $glucose, $familyHistory, $cholesterol, $height, $weight, $patientID);
			$stmt->execute();	
		}
		
		if($stmt ->affected_rows == 1){
			
			$result["success"] = 1;
			$result["message"] = "PHP(saveMedicalHistory): Medical history stored in database";
			
			echo json_encode($result);	
			mysqli_close($conn);
		}
		else{
			$result["success"] = 0;
			$result["message"] = "PHP(saveMedicalHistory): Failed to insert medical history";
			
			echo json_encode($result);	
			mysqli_close($conn);
		}
	}
?>