<?php

	if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
		$gender = trim($_POST['gender']);
		$age = trim($_POST['ageGroup']);
		$exercise = trim($_POST['exercise']);
		$pastConditions = trim($_POST['pastConditions']);
		$glucose = trim($_POST['glucose']);
		$familyHistory = trim($_POST['familyHistory']);
		$bloodPressure = trim($_POST['bloodPressure']);
		$cholesterol = trim($_POST['cholesterol']);
		$height = trim($_POST['height']);
		$weight = trim($_POST['weight']);
		$patientID = trim($_POST['patientID']);
		
		require_once 'dbConnect.php';
		
		$insertQuery  = "INSERT INTO medicalHistory(gender, ageGroup, exercise, pastConditions, glucose, familyHistory, bloodPressure, cholesterol, height, weight, patientID)
						VALUES (?,?,?,?,?,?,?,?,?,?,?)";
								
		if($stmt = $conn->prepare($insertQuery)){
			
			$stmt->bind_param("ssssssssiis", $gender, $age, $exercise, $bloodPressure, $pastConditions, $glucose, $familyHistory, $cholesterol, $height, $weight, $patientID);
			$stmt->execute();	
		}
		
		if($conn->affected_rows >= 1){
			
			$result["success"] = 1;
			$result["message"] = "PHP(saveMedicalHistory): Medical history stored in database";
			
			echo json_encode($result);	
		}
		else{
			$result["success"] = 0;
			$result["message"] = "PHP(saveMedicalHistory): Failed to insert medical history";
			
			echo json_encode($result);	
		}
	}
?>