<?php
include 'database3.php';

$db = getDatabaseConnection();
$stmt = $db->prepare('UPDATE products SET name = :name, description = :description, price = :price WHERE id = :id');
$stmt->bindValue(':name', $_POST['name']);
$stmt->bindValue(':description', $_POST['description']);
$stmt->bindValue(':price', $_POST['price']);
$stmt->bindValue(':id', $_POST['id'], SQLITE3_INTEGER);
$stmt->execute();

header("Location: Lab3.php");
exit();
