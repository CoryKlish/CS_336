<?php
require "connect.php";

$firstName = "Cory";
$lastName = "Klish";
$email = "cory551043@gmail.com";
$pass = "hello"; //$_POST[];
$dob = "dob";

$mysql_qry = "INSERT INTO userInfo (firstName, lastName, email, password, dob)
			VALUES ('$firstName', '$lastName', '$email', '$pass', '$dob')";

$result = $conn->query($mysql_qry);

if ($conn->query($mysql_qry) === TRUE) {
    echo "New record created successfully. Continue to login";
} else {
    echo "Error: " . $conn->error;
}

$conn->close();

?>