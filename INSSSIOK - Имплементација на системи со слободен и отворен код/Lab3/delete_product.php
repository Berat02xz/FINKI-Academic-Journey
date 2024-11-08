<?php

include 'db_connection.php';

$db = getDatabaseConnection();

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['id'])) {
    $stmt = $db->prepare("DELETE FROM products WHERE id = :id");
    $id = $_POST['id'];
    $stmt->bindValue(':id', $id, PDO::PARAM_INT);
    $stmt->execute();
    header('Location: index.php');
}
exit();
?>