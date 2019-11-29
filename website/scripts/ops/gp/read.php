<?php
// Check existence of email parameter before processing further
if(isset($_GET["email"]) && !empty(trim($_GET["email"]))){
    // Include config file
    require_once __DIR__ . "/../../config.php";
    
    // Prepare a select statement
    $sql = "SELECT * FROM gp WHERE email = ?";
    
    if($stmt = mysqli_prepare($conn, $sql)){
        // Bind variables to the prepared statement as parameters
        mysqli_stmt_bind_param($stmt, "s", $param_email);
        
        // Set parameters
        $param_email = trim($_GET["email"]);
        
        // Attempt to execute the prepared statement
        if(mysqli_stmt_execute($stmt)){
            $result = mysqli_stmt_get_result($stmt);
    
            if(mysqli_num_rows($result) == 1){
                /* Fetch result row as an associative array. Since the result set contains only one row, we don't need to use while loop */
                $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
                
                // Retrieve individual field value
                $first_name = $row["first_name"];
				$second_name = $row["last_name"];
                $phone_no = $row["phone_no"];
				$add1 = $row["address1"];
				$add2 = $row["address2"];
				$add3 = $row["address3"];
                $email = $row["email"];
				$password = $row["password"];
            } else{
                // URL doesn't contain valid email parameter. Redirect to error page
                header("location: error.php");
                exit();
            }
            
        } else{
            echo "Oops! Something went wrong. Please try again later.";
        }
    }
     
    // Close statement
    mysqli_stmt_close($stmt);
    
    // Close connection
    mysqli_close($conn);
} else{
    // URL doesn't contain email parameter. Redirect to error page
    header("location: error.php");
    exit();
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View GP</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <style type="text/css">
        .wrapper{
            width: 500px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="page-header">
                        <h1>View GP</h1>
                    </div>

                    <div class="form-group">
                        <label>First Name</label>
                        <p class="form-control-static"><?php echo $row["first_name"]; ?></p>
                    </div>
					
                    <div class="form-group">
                        <label>Last Name</label>
                        <p class="form-control-static"><?php echo $row["last_name"]; ?></p>
                    </div>
					
					<div class="form-group">
                        <label>Phone Number</label>
                        <p class="form-control-static"><?php echo $row["phone_no"]; ?></p>
                    </div>
					
					<div class="form-group">
                        <label>Address Line 1</label>
                        <p class="form-control-static"><?php echo $row["address1"]; ?></p>
                    </div>
					
					<div class="form-group">
                        <label>Address Line 2</label>
                        <p class="form-control-static"><?php echo $row["address2"]; ?></p>
                    </div>
					
					<div class="form-group">
                        <label>Address Line 3</label>
                        <p class="form-control-static"><?php echo $row["address3"]; ?></p>
                    </div>
					
					<div class="form-group">
                        <label>Email</label>
                        <p class="form-control-static"><?php echo $row["email"]; ?></p>
                    </div>
					
					<div class="form-group">
                        <label>Password</label>
                        <p class="form-control-static"><?php echo $row["password"]; ?></p>
                    </div>
					
                    <p><a href="list.php" class="btn btn-primary">Back</a></p>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>