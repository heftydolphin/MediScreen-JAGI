<?php
    // Criteria to search for	
	$username = $_POST['username'];
	$password = $_POST['password'];
    
    // Connect to MySQL database
    $connect = mysqli_connect("localhost", "root", "admin","mediscreen");
    
    // Query MySQL search query
    $query_insurer = "SELECT * FROM insurer WHERE username = '$username' && password = '$password'";    
    $result_insurer = mysqli_query($connect, $query_insurer);
	
	$query_gp = "SELECT * FROM gp WHERE username = '$username' && password = '$password'";    
    $result_gp = mysqli_query($connect, $query_gp);
    
    // If user exists in insurer table bring to user home page
	// NOTE: The user is succesfully searched for but does not set as logged in
    if(mysqli_num_rows($result_insurer) > 0)
    {
      while ($row = mysqli_fetch_array($result_insurer))
      {
        $fname = $row['fName'];
		$lname = $row['lName'];
		
      }
		echo "$fname", " " ,"$lname";

	include '../html/userHomePage.php';
    }
	
    // If user exists in gp table bring to user home page
	// NOTE: The user is succesfully searched for but does not set as logged in
	else if (mysqli_num_rows($result_gp) > 0){
		
		while ($row = mysqli_fetch_array($result_gp)){
        
		$fname = $row['fName'];
		$lname = $row['lName'];
      }
	echo "$fname", " " ,"$lname";
	include '../html/userHomePage.php';
	}
   
    // If user is not found print message
		else{ /*if(empty($username) OR empty($password)){
				header("location:../html/loginPage.html?message=<div class='alert alert-danger'> Empty Input!! Enter Input");
				//echo "empty";
				//include '../html/loginPage.html';
				///exit();
			}else{
				header("location:../html/loginPage.html?message=<div class='alert alert-danger'> Unidentified ID");
				//echo "Undifined ID";
				//include '../html/loginPage.html';
				//exit();
			}*/
			//echo "Unidentified ID";
			$error = "Your Login Name or Password is invalid";
			include '../html/loginPage.html';
		
		}
    // Close database connection
    //mysqli_free_result($result);
    mysqli_close($connect);
?>