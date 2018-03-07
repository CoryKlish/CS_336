<?php
require "connect.php";

$userName = "hello";//$_POST[];
$pass = "hello"; //$_POST[];

$mysql_qry = "SELECT * FROM userInfo WHERE email = '$userName' and password = '$pass'";
$result = $conn->query($mysql_qry);

if ($result->num_rows > 0) {
   echo "Login Success";
}
else {
    echo "No UserName/Password Match";
}

$conn->close();

?>