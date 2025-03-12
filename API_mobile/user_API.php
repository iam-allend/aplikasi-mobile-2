<?php
header("Content-Type: application/json");
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "e-commerce";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$action = $_GET['action'];

switch ($action) {
    case 'login':
        // Cek apakah email dan password ada di POST
        if (!isset($_POST['email']) || !isset($_POST['password'])) {
            echo json_encode([
                "status" => "error",
                "message" => "Email dan password harus diisi!"
            ]);
            exit;
        }
    
        $input_email = $_POST['email'];
        $input_password = $_POST['password'];
    
        // Lanjutkan proses login...
        $sql = "SELECT * FROM tbl_pelanggan WHERE email = '$input_email' AND password = '$input_password'";
        // ... (kode selanjutnya tetap sama)        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            $sql_update = "UPDATE tbl_pelanggan SET status = 1 WHERE email = '$input_email'";
            $conn->query($sql_update);
            echo json_encode(["status" => "success", "message" => "Login berhasil"]);
        } else {
            echo json_encode(["status" => "error", "message" => "Email/password salah"]);
        }
        break;

    case 'register':
        $input_nama = $_POST['nama'];
        $input_password = $_POST['password'];
        $input_email = $_POST['email'];

        // Validasi email sudah terdaftar
        $check_sql = "SELECT * FROM tbl_pelanggan WHERE email = '$input_email'";
        $check_result = $conn->query($check_sql);
        if ($check_result->num_rows > 0) {
            echo json_encode(["status" => "error", "message" => "Email sudah terdaftar"]);
            exit;
        }

        // Lanjutkan registrasi jika email belum terdaftar
        $sql = "INSERT INTO tbl_pelanggan (nama, password, email) VALUES ('$input_nama', '$input_password', '$input_email')";
        if ($conn->query($sql) === TRUE) {
            echo json_encode(["status" => "success", "message" => "Registrasi berhasil"]);
        } else {
            echo json_encode(["status" => "error", "message" => "Registrasi gagal"]);
        }
        break;

    case 'logout':
        $input_email = $_POST['email'];

        // Update status menjadi 0 (logout)
        $sql = "UPDATE tbl_pelanggan SET status = 0 WHERE email = '$input_email'";
        if ($conn->query($sql)) {
            echo json_encode(["status" => "success", "message" => "Logout berhasil"]);
        } else {
            echo json_encode(["status" => "error", "message" => "Logout gagal"]);
        }
        break;

    case 'get_user':
        $input_email = $_POST['email'];
        $sql = "SELECT nama, email FROM tbl_pelanggan WHERE email = '$input_email'";
        $result = $conn->query($sql);
        $user = $result->fetch_assoc();
        echo json_encode($user);
        break;
}

$conn->close();
