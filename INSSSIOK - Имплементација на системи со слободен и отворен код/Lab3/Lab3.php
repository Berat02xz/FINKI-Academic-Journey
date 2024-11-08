<?php
include 'database3.php';

$db = getDatabaseConnection();
$results = $db->query('SELECT * FROM products');
$productList = [];

while ($row = $results->fetchArray(SQLITE3_ASSOC)) {
    $products[] = $row;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lab 3</title>
</head>
<body>
    <a href="add_product.html">Add Product</a>
    <h1>Products</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <?php foreach ($products as $product): ?>
            <tr>
                <td><?= $product['name'] ?></td>
                <td><?= $product['description'] ?></td>
                <td><?= $product['price'] ?></td>
                <a href="edit_product.php?id=<?= $product['id'] ?>">Edit</a>
                <a href="delete_product.php?id=<?= $product['id'] ?>">Delete</a>
            </tr>

        <?php endforeach; ?>
    </table>
</body>
</html>


