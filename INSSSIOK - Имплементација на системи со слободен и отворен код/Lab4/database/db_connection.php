<?php

function connectDatabase() {
    return new SQLite3(__DIR__. '/database.sqlite');
}