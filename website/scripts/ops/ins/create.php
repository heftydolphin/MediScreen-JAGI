<?php
// Include config file
require_once __DIR__ . "/../../config.php";
 
// Define variables and initialize with empty values
$fname = "";
$lname = "";
$phone = "";
$email = "";
$password = "";
$confirm_password = "";

$fname_err = "";
$lname_err = "";
$phone_err = "";
$email_err = "";
$password_err = "";
$confirm_password_err = "";
 
// Processing form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){
	
	// VALIDATION
	// Validate first name
    if(empty(trim($_POST["fname"]))){
        $fname_err = "Please enter a name.";     
    } elseif(!preg_match("/^[a-zA-Z'-]+$/", $_POST["fname"])){
        $fname_err = "Name cannot contain numbers.";
    } else{
        $fname = trim($_POST["fname"]);
    }
	
	// Validate last name
    if(empty(trim($_POST["lname"]))){
        $lname_err = "Please enter a name.";     
    } elseif(!preg_match("/^[a-zA-Z'-]+$/", $_POST["lname"])){
        $lname_err = "Name cannot contain numbers.";
    } else{
        $lname = trim($_POST["lname"]);
    }
	
	// Validate phone number
    if(empty(trim($_POST["phone"]))){
        $phone_err = "Please enter a phone number.";     
    } elseif(preg_match("/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/", $_POST["phone"])){
        $phone_err = "Phone number cannot contain letters.";
    } else{
        $phone = trim($_POST["phone"]);
    }
	
    // Validate email
    if(empty(trim($_POST["email"]))){
        $email_err = "Please enter an email.";
    } else{
        // Prepare a select statement
        $sql = "SELECT email FROM login_details WHERE email = ?";
        
        if($stmt = mysqli_prepare($conn, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_email);
            
            // Set parameters
            $param_email = trim($_POST["email"]);
            
            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);
                
                if(mysqli_stmt_num_rows($stmt) == 1){
                    $email_err = "This email is already registered.";
                } else{
                    $email = trim($_POST["email"]);
                }
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }
         
        // Close statement
        mysqli_stmt_close($stmt);
    }
    
    // Validate password
    if(empty(trim($_POST["password"]))){
        $password_err = "Please enter a password.";     
    } elseif(strlen(trim($_POST["password"])) < 6){
        $password_err = "Password must have atleast 6 characters.";
    } else{
        $password = trim($_POST["password"]);
    }
    
    // Validate confirm password
    if(empty(trim($_POST["confirm_password"]))){
        $confirm_password_err = "Please confirm password.";     
    } else{
        $confirm_password = trim($_POST["confirm_password"]);
        if(empty($password_err) && ($password != $confirm_password)){
            $confirm_password_err = "Password did not match.";
        }
    }
    
    // Check input errors before inserting in database
	if(empty($fname_err) && empty($lname_err) && empty($phone_err) && empty($email_err) && empty($password_err) && empty($confirm_password_err)){
        
        // Prepare an insert statement
        $sql = "INSERT INTO insurer (first_name, last_name, phone_no, email, password) VALUES (?, ?, ?, ?, ?)";
         
        if($stmt = mysqli_prepare($conn, $sql)){
            // Bind variables to the prepared statement as parameters
				mysqli_stmt_bind_param($stmt, "ssiss", $param_fname, $param_lname, $param_phone, $param_email, $param_password);
            
            // Set parameters
			$param_fname = $fname;
			$param_lname = $lname;
			$param_phone = $phone;
			$param_email = $email;
			$param_password = password_hash($password, PASSWORD_DEFAULT);
            
            // Attempt to execute the prepared statement
				if(mysqli_stmt_execute($stmt)){
					
					// Prepare an insert statement
					$sql1 = "INSERT INTO login_details (email, password, user_type) VALUES (?, ?, ?)";
					 
					if($stmt1 = mysqli_prepare($conn, $sql1)){
						// Bind variables to the prepared statement as parameters
						mysqli_stmt_bind_param($stmt1, "sss", $param_email1, $param_password1, $param_user_type);
						
						// Set parameters
						$param_email1 = $email;
						$param_password1 = password_hash($password, PASSWORD_DEFAULT);
						$param_user_type = "INS";
						
						// Attempt to execute the prepared statement
						if(mysqli_stmt_execute($stmt1)){
							// Redirect to list page
							header("location: list.php");
						} else{
							echo "Something went wrong. Please try again later.";
						}
					}
					
					// Close statement
					mysqli_stmt_close($stmt1);
					
				} else{
					echo "Something went wrong. Please try again later.";
				}
        }
         
        // Close statement
        mysqli_stmt_close($stmt);
    }
    
    // Close connection
    mysqli_close($conn);
}

?>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Record</title>
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
                        <h2>Create New Insurer Account</h2>
                    </div>
                    <p>Fill in all of the details below to create a new user account.</p>
                    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
                        
						<div class="form-group <?php echo (!empty($fname_err)) ? 'has-error' : ''; ?>">
							<label>First Name</label>
							<input type="text" name="fname" class="form-control" value="<?php echo $fname; ?>">
							<span class="help-block"><?php echo $fname_err; ?></span>
						</div>
						
						<div class="form-group <?php echo (!empty($lname_err)) ? 'has-error' : ''; ?>">
							<label>Last Name</label>
							<input type="text" name="lname" class="form-control" value="<?php echo $lname; ?>">
							<span class="help-block"><?php echo $lname_err; ?></span>
						</div>
			
						<div class="form-group <?php echo (!empty($phone_err)) ? 'has-error' : ''; ?>">
							<label>Phone Number:</label>
							<input type="text" name="phone" class="form-control" value="<?php echo $phone; ?>">
							<span class="help-block"><?php echo $phone_err; ?></span>
						</div>
						
						<div class="form-group <?php echo (!empty($email_err)) ? 'has-error' : ''; ?>">
							<label>Email</label>
							<input type="text" name="email" class="form-control" value="<?php echo $email; ?>">
							<span class="help-block"><?php echo $email_err; ?></span>
						</div>
						
						<div class="form-group <?php echo (!empty($password_err)) ? 'has-error' : ''; ?>">
							<label>Password</label>
							<input type="password" name="password" class="form-control" value="<?php echo $password; ?>">
							<span class="help-block"><?php echo $password_err; ?></span>
						</div>
						
						<div class="form-group <?php echo (!empty($confirm_password_err)) ? 'has-error' : ''; ?>">
							<label>Confirm Password</label>
							<input type="password" name="confirm_password" class="form-control" value="<?php echo $confirm_password; ?>">
							<span class="help-block"><?php echo $confirm_password_err; ?></span>
						</div>
						
						
                        <input type="submit" class="btn btn-primary" value="Submit">
                        <a href="list.php" class="btn btn-default">Cancel</a>
                    </form>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>