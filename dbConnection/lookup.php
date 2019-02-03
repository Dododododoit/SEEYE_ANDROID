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
// $category = $_GET['cat'];
$query = "SELECT * FROM product";
// WHERE Category='".$category."'";
if(isset($_GET['cat']) || isset($_GET['id']) || isset($_GET['pin']))
{
    $query = $query." WHERE";
}
if(isset($_GET['cat']))
{
    $query = $query." Category='".$_GET['cat']."'";
}
if(isset($_GET['id']))
{
    $query = $query." productID='".$_GET['id']."'";
}
if(isset($_GET['pin']))
{
    $query = $query." Pin='".$_GET['pin']."'";
}
$statement = $conn->prepare($query);
if($statement->execute())
{	
	$result = $statement->fetchall(PDO::FETCH_ASSOC);
	if ($result !== false) {
		$json = json_encode($result);
		echo $json;
	} else {
	    echo '2'; //Empty result
	}
}
else {
	echo '3';//Query Failed
}
http_response_code(200);
?>