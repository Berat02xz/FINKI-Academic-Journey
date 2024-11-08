<?php
include 'database3.php';

$db = getDatabaseConnection();
$productId = $_GET['id'];

$stmt = $db->prepare('SELECT * FROM products WHERE id = :id');
$stmt->bindValue(':id', $productId, SQLITE3_INTEGER);
$product = $stmt->execute()->fetchArray(SQLITE3_ASSOC);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
</head>
<body>
<h1>Edit Product</h1>
<form action="update_product.php" method="post">
    <input type="hidden" name="id" value="<?= $product['id'] ?>">
    <label for="name">Name:</label>
    <input type="text" name="name" id="name" value="<?= $product['name'] ?>" required>
    <br>
    <label for="description">Description:</label>
    <input type="text" name="description" id="description" value="<?= $product['description'] ?>" required>
    <br>
    <label for="price">Price:</label>
    <input type="number" name="price" id="price" value="<?= $product['price'] ?>" required>
    <br>
    <input type="submit" value="Update Product">
</form>
</body>
</html>
