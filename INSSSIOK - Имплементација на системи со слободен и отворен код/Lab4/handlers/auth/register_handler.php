<?php
session_start();

include '../../database/db_connection.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = trim($_POST['username']);
    $password = $_POST['password'];

    if (strlen($username) < 3 || strlen($password) < 6) {
        die('Username must be at least 3 chars and password at least 6 chars');
    }

    $hashedPassword = password_hash($password, PASSWORD_DEFAULT);

    $db = connectDatabase();

    $stmt = $db->prepare("INSERT INTO users (username, password) VALUES (:username, :password)");
    try {
        $stmt->bindValue(':username', $username);
        $stmt->bindValue(':password', $hashedPassword);

        $stmt->execute();

        echo "Success<a href='../../pages/auth/login.php'>Login here</a>";
    } catch (PDOException $e) {
        if ($e->getCode() == 23000) {
            die("Корисничкото име веќе постои.");
        } else {
            die("Грешка: " . $e->getMessage());
        }
    }
}
?>