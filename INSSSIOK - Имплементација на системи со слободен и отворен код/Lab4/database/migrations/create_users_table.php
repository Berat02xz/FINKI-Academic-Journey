<?php

include './database/db_connection.php';

$db = connectDatabase();

$query = "CREATE TABLE IF NOT EXISTS users (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        username TEXT NOT NULL,
        password TEXT NOT NULL
    )";

$db->exec($query);