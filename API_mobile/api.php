<?php
header("Content-Type: application/json");

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "universitas";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM mahasiswa";
$result = $conn->query($sql);

$mahasiswa = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $mahasiswa[] = $row;
    }
}

echo json_encode($mahasiswa);

$conn->close();
