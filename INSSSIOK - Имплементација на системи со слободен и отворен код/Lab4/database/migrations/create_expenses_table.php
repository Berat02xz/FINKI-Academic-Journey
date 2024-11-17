<?php

include './database/db_connection.php';

$db = connectDatabase();

$query = 'CREATE TABLE IF NOT EXISTS expenses(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        date DATE NOT NULL,
        amount REAL NOT NULL,
        type TEXT NOT NULL
    );';

$db->exec($query);