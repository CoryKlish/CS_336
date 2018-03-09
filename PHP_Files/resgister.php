<?php

      require "connect.php";

      $firstName = $_POST["firstName"];
      $lastName = $_POST["lastName"];
      $email = $_POST["email"];
      $password = $_POST["password"];
      $dob = $_POST["dob"];

      $statement = mysqli_prepare($conn, "INSERT INTO userInfo (firstName, lastName, email, password, dob) VALUES(?, ?, ?, ?, ?)");
      mysqli_stmt_bind_param($statement, "sssss", $firstName, $lastName, $email, $password, $dob);

      mysqli_stmt_execute($statement);

      //if(mysqli_stmt_execute($statement)){
      //      echo "Registration success";
      //      $response = array();
      //      $response["success"] = true;
      //}else{
      //      echo "Registration failed";
      //      $response = array();
      //      $response["success"] = false;
      //}

      $response = array();
      $response["success"] = true;

      //echo $response;

      echo json_encode($response);

?>
