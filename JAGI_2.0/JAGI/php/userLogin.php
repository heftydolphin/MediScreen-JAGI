<?php
    // Criteria to search for	
	$email = $_POST['email'];
	$password = $_POST['password'];
    
    // Connect to MySQL database
    $connect = mysqli_connect("localhost", "root", "","mediscreen");
    
    // Query MySQL search query
    $query_insurer = "SELECT * FROM insurer WHERE email = '$email' && password = '$password'";    
    $result_insurer = mysqli_query($connect, $query_insurer);
	
	$query_gp = "SELECT * FROM gp WHERE email = '$email' && password = '$password'";    
    $result_gp = mysqli_query($connect, $query_gp);
    
    // If user exists in insurer table bring to user home page
	// NOTE: The user is succesfully searched for but does not set as logged in
    if(mysqli_num_rows($result_insurer) > 0)
    {
      while ($row = mysqli_fetch_array($result_insurer))
      {
        $fname = $row['fName'];
		
      }
		echo "$fname";

	include '../html/userHomePage.html';
    }
	
    // If user exists in gp table bring to user home page
	// NOTE: The user is succesfully searched for but does not set as logged in
	else if (mysqli_num_rows($result_gp) > 0){
		
		while ($row = mysqli_fetch_array($result_gp)){
        
		$fname = $row['fName'];
      }
	echo "$fname";
	include '../html/userHomePage.html';
	}
    
    // If user is not found print message
    else {
        echo "Undifined ID";
            $fname = "";
    }
    
    // Close database connection
    mysqli_free_result($result);
    mysqli_close($connect);
?>