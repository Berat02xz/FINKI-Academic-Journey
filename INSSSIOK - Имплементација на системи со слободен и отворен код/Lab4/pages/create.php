<?php
session_start();
require '../jwt_helper.php';

if (!isset($_SESSION['jwt']) || !decodeJWT($_SESSION['jwt'])) {
    header("Location: ../pages/auth/login.php");
    exit;
}
?>

<form action="../handlers/create_handler.php" method="POST">
    <label for="name">Name:</label>
    <input type="text" name="name" id="name" required>
    <br />
    <label for="date">Date:</label>
    <input type="date" name="date" id="date" required>
    <br />
    <label for="amount">Amount:</label>
    <input type="number" name="amount" id="amount" required>
    <br />
    <label for="type">Payment type</label>
    <select name="type" id="type">
        <option value="cash">cash</option>
        <option value="card">card</option>
    </select>
    <br />
    <button type="submit">Add Expense</button>
</form>