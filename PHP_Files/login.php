<?php

      require "connect.php";

      $username = $_POST["username"];
      $password = $_POST["password"];

      $statement = mysqli_prepare($conn, "SELECT * FROM userInfo WHERE email = ? and password = ?");

      mysqli_stmt_bind_param($statement, "ss", $username, $password);

      mysqli_stmt_execute($statement);
      mysqli_stmt_store_result($statement);
      //$numRows = $statement->num_rows;

      //if($numRows == 1){
      //      echo "Login successful";
      //}else{
      //      echo "Login unsuccessful";
      //}

      mysqli_stmt_bind_result($statement, $userID, $firstName, $lastName, $email, $password, $dob);

      $response = array();
      $response["success"] = false;

      while(mysqli_stmt_fetch($statement)){
              $response["success"] = true;
              $response["userID"] = $userID;
              $response["firstName"] = $firstName;
              $response["lastName"] = $lastName;
              $response["email"] = $email;
              $response["password"] = $password;
              $response["dob"] = $dob;
      }

      //echo $response;

      echo json_encode($response);

?>
