<?php
include 'database3.php';

$db = getDatabaseConnection();

$stmt = $db->prepare('INSERT INTO products (name, description, price) VALUES (:name, :description, :price)');
$stmt->bindValue(':name', $_POST['name']);
$stmt->bindValue(':description', $_POST['description']);
$stmt->bindValue(':price', $_POST['price']);
$stmt->execute();

header('Location: Lab3.php');
exit();