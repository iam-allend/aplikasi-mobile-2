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

$action = $_GET['action'];

switch ($action) {
    case 'login':
        $input_username = $_POST['username'];
        $input_password = $_POST['password'];

        $sql = "SELECT * FROM users WHERE username = '$input_username' AND password = '$input_password'";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            $sql_update = "UPDATE users SET status = 1 WHERE username = '$input_username'";
            $conn->query($sql_update);
            echo json_encode(["status" => "success", "message" => "Login berhasil"]);
        } else {
            echo json_encode(["status" => "error", "message" => "Username/password salah"]);
        }
        break;

    case 'register':
        $input_username = $_POST['username'];
        $input_password = $_POST['password'];
        $input_email = $_POST['email'];

        // Validasi username sudah terdaftar
        $check_sql = "SELECT * FROM users WHERE username = '$input_username'";
        $check_result = $conn->query($check_sql);
        if ($check_result->num_rows > 0) {
            echo json_encode(["status" => "error", "message" => "Username sudah terdaftar"]);
            exit;
        }

        // Lanjutkan registrasi jika username belum terdaftar
        $sql = "INSERT INTO users (username, password, email) VALUES ('$input_username', '$input_password', '$input_email')";
        if ($conn->query($sql) === TRUE) {
            echo json_encode(["status" => "success", "message" => "Registrasi berhasil"]);
        } else {
            echo json_encode(["status" => "error", "message" => "Registrasi gagal"]);
        }
        break;

        // Di api.php
    case 'logout':
        $input_username = $_POST['username'];

        // Update status menjadi 0 (logout)
        $sql = "UPDATE users SET status = 0 WHERE username = '$input_username'";
        if ($conn->query($sql) === TRUE) {
            echo json_encode(["status" => "success", "message" => "Logout berhasil"]);
        } else {
            echo json_encode(["status" => "error", "message" => "Logout gagal"]);
        }
        break;

    case 'get_user':
        $input_username = $_POST['username'];
        $sql = "SELECT username, email FROM users WHERE username = '$input_username'";
        $result = $conn->query($sql);
        $user = $result->fetch_assoc();
        echo json_encode($user);
        break;
}

$conn->close();
