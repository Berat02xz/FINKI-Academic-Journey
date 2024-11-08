<?php
function getDatabaseConnection() {
    $db = new SQLite3(__DIR__ . '/database/products_db.sqlite');

    // Check if the table exists and create it if it doesn't
    $query = "CREATE TABLE IF NOT EXISTS products (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        description TEXT,
        price REAL NOT NULL
    )";
    $db->exec($query);

    return $db;
}
?>
