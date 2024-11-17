<?php
include './database/db_connection.php';
include './jwt_helper.php';

session_start();

if (!isset($_SESSION['jwt']) || !decodeJWT($_SESSION['jwt'])) {
    header("Location: pages/auth/login.php");
    exit;
}

$db = connectDatabase();

$query = "SELECT * FROM expenses";
$result = $db->query($query);

if (!$result) {
    die("Error fetching expenses: " . $db->lastErrorMsg());
}
?>

<body>
<div>
    <h1>Expenses List</h1>
    <a href="pages/create.php">
        Add Expense
    </a>
    <a href="handlers/auth/logout_handler.php">
        Одјави се
    </a>
</div>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date</th>
        <th>Amount</th>
        <th>Type</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <?php if ($result): ?>
        <?php while ($expense = $result->fetchArray(SQLITE3_ASSOC)): ?>
            <tr>
                <td><?php echo htmlspecialchars($expense['id']); ?></td>
                <td><?php echo htmlspecialchars($expense['name']); ?></td>
                <td><?php echo htmlspecialchars($expense['date']); ?></td>
                <td><?php echo htmlspecialchars($expense['amount']); ?></td>
                <td><?php echo htmlspecialchars($expense['type']); ?></td>
                <td>
                    <?php if ($expense['amount'] <= 100): ?>
                        <form action="handlers/delete_handler.php" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<?php echo $expense['id']; ?>">
                            <button type="submit">Delete</button>
                        </form>
                    <?php endif ?>
                    <form action="pages/edit.php" method="get" style="display:inline;">
                        <input type="hidden" name="id" value="<?php echo $expense['id']; ?>">
                        <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
        <?php endwhile; ?>
    <?php else: ?>
        <tr>
            <td colspan="5">No expenses found.</td>
        </tr>
    <?php endif; ?>
    </tbody>
</table>
</body>