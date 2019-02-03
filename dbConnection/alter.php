<?php
require_once('database.php');
$db = new Database();
$conn = $db->getConnection();
// Check connection
if ($conn->connect_error) {
	Echo '4';//Connection failed
    die("Connection failed: " . $conn->connect_error);
} 
// echo "Connected successfully";
$id = $_GET['id'];
$pin = $_GET['pin'];
$query = "UPDATE product SET Pin = ".$pin." WHERE ProductId = '".$id."';";
echo $query;
// Prepare statement
$stmt = $conn->prepare($query);
$stmt->execute();
http_response_code(200);
?>