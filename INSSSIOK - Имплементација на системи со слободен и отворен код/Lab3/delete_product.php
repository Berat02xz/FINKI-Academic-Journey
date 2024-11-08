<?php

include "database3.php";
$db = getDatabaseConnection();

$stmt = $db->prepare('DELETE FROM products WHERE id = :id');
$stmt->bindValue(':id', $_GET['id']);
$stmt->execute();

header('Location: Lab3.php');
exit();