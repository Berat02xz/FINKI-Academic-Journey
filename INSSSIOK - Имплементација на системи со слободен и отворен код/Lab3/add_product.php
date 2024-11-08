<?php

include 'db_connection.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['name'];
    $price = $_POST['price'];
    $description = $_POST['description'];

    $db = getDatabaseConnection();

    $stmt = $db->prepare("INSERT INTO products (name, price, description) VALUES(:name, :price, :description)");
    $stmt->bindValue(':name', $name);
    $stmt->bindValue(':price', $price);
    $stmt->bindValue(':description', $description);
    $stmt->execute();
    echo "New record created successfully";
    header('Location: index.php');
    echo "Headed to index";
}
exit();
?>