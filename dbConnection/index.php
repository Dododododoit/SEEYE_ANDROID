<?php
// class Database{
 
//     // specify your own database credentials
//     private $host = "localhost";
//     private $db_name = "api_db";
//     private $username = "root";
//     private $password = "";
//     public $conn;
 
//     // get the database connection
//     public function getConnection(){
 
//         $this->conn = null;
 
//         try{
//             $this->conn = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->db_name, $this->username, $this->password);
//             $this->conn->exec("set names utf8");
//         }catch(PDOException $exception){
//             echo "Connection error: " . $exception->getMessage();
//         }
 
//         return $this->conn;
//     }
// }
$servername = "mysql-instance1.cqilpo3gbwln.us-east-1.rds.amazonaws.com";
$username = "Administrator";
$password = "seeyetech";

// Create connection
$conn = new mysqli($servername, $username, $password);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
echo "Connected successfully";
?>