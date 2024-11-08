<?php

include 'db_connection.php';

if (isset($_GET['id'])) {

    $db = getDatabaseConnection();

    $id = $_GET['id'];
    $stmt = $db->prepare('SELECT * FROM products WHERE id = :id');
    $stmt->bindValue(':id', $id, SQLITE3_INTEGER);
    $result=$stmt->execute();
    $product = $result->fetchArray(SQLITE3_ASSOC);
}
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Student</title>
</head>
<body>
<h1>Update Student</h1>

<?php if ($product): ?>
    <form action="update_product.php" method="post">
        <input type="hidden" name="id" value="<?php echo htmlspecialchars($product['id']); ?>">
        <label for="name">Name:</label>
        <input type="text" name="name" value="<?php echo htmlspecialchars($product['name']); ?>" required><br><br>
        <label for="description">Description:</label>
        <input type="text" name="description" value="<?php echo htmlspecialchars($product['description']); ?>" required><br><br>
        <label for="price">Price:</label>
        <input type="number" name="price" value="<?php echo htmlspecialchars($product['price']); ?>" required><br><br>
        <button type="submit">Update Product</button>
    </form>
<?php else: ?>
    <p>Student not found.</p>
<?php endif; ?>
</body>
</html>
