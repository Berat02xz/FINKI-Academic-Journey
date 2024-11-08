<?php
include 'db_connection.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
$id = $_POST['id'];
$name = $_POST['name'];
$price = $_POST['price'];
$description = $_POST['description'];

$db = getDatabaseConnection();

$stmt = $db->prepare("UPDATE products SET name = :name, price = :price, description = :description WHERE id = :id");
$stmt->bindValue(':name', $name);
$stmt->bindValue(':price', $price);
$stmt->bindValue(':description', $description);
$stmt->bindValue(':id', $id);
$stmt->execute();
header('Location: index.php');
}
exit();
