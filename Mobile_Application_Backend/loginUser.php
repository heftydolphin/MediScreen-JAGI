<?php
// Criteria to search for	
	$email = $_POST['email'];
	$password = $_POST['password'];
    
    // Connect to MySQL database
	require_once 'dbConnect.php';
    
    // Query MySQL search query
    $search_query = "SELECT * FROM patient WHERE email = '$email' && password = '$password'";    
    $list = mysqli_query($conn, $search_query);
	
	 
    $stmt = $conn->prepare("SELECT * FROM patient WHERE email = '$email'");   
    $stmt->execute();  
    $stmt->bind_result($email, $password, $fName, $lName, $memberNum, $policyNum);  
    $stmt->fetch();  
   
    $user = array(  
		'email'=>$email,   
        'password'=>$password,   
        'fName'=>$fName,  
        'lName'=>$lName, 
		'memberNum'=>$memberNum, 
		'policyNum'=>$policyNum);  
   
    $stmt->close();  
 
    
    // If user exists in insurer table bring to user home page
	// NOTE: The user is succesfully searched for but does not set as logged in
    if(mysqli_num_rows($list) > 0)
    {
      while ($row = mysqli_fetch_array($list))
      {
       # $fname = $row['fName'];
			
			$result["success"] = 1;
			$result["message"] = "PHP(loginUser): User found in database";
			$result['user'] = $user;   

			echo json_encode($result);	
			mysqli_close($conn);
      }
		#echo "$fname";
    }
    
    // If user is not found print message
    else {
        #echo "Undifined ID";
            $fname = "";

			$result["success"] = 0;
			$result["message"] = "PHP(loginUser): Failed to find user";
			
			echo json_encode($result);	
			mysqli_close($conn);
    }
?>