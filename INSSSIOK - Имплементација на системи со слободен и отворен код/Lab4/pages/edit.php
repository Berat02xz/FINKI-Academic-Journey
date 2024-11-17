<?php
include '../database/db_connection.php';
require '../jwt_helper.php';

session_start();
if (!isset($_SESSION['jwt']) || !decodeJWT($_SESSION['jwt'])) {
    header("Location: ../pages/auth/login.php");
    exit;
}

if (isset($_GET['id'])) {
    $id = intval($_GET['id']);
    $db = connectDatabase();

    $stmt = $db->prepare("SELECT * FROM expenses WHERE id = :id");
    $stmt->bindValue(':id', $id, SQLITE3_INTEGER);
    $result = $stmt->execute();
    $expense = $result->fetchArray(SQLITE3_ASSOC);

    $db->close();
} else {
    die("Invalid expense ID.");
}
?>

<h1>Update Expense</h1>

<?php if ($expense): ?>
    <form action="../handlers/edit_handler.php" method="post">
        <input type="hidden" name="id" value="<?php echo htmlspecialchars($expense['id']); ?>">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" value="<?php echo htmlspecialchars($expense['name']); ?>" required>
        <br>
        <label for="date">Date:</label>
        <input type="date" name="date" id="date" value="<?php echo htmlspecialchars($expense['date']); ?>" required>
        <br>
        <label for="amount">Amount:</label>
        <input type="number" name="amount" id="amount" value="<?php echo htmlspecialchars($expense['amount']); ?>" required>
        <br>
        <label for="type">Payment type</label>
        <select name="type" id="type">
            <option <?php echo htmlspecialchars($expense['type']) === 'cash' ? 'selected=true' : ''; ?> value="cash">cash</option>
            <option <?php echo htmlspecialchars($expense['type']) === 'card' ? 'selected=true' : ''; ?> value="card">card</option>
        </select>
        <br/>
        <button type="submit">Update Expense</button>
    </form>
<?php else: ?>
    <p>Expense not found.</p>
<?php endif; ?>