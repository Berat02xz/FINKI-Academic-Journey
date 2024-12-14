<?php

use Illuminate\Support\Facades\Route;

use App\Http\Controllers\BlogPostController;
use App\Http\Controllers\CategoryController;

Route::resource('categories', CategoryController::class);
Route::resource('blogposts', BlogPostController::class);

Route::get('/', function () {
    return view('welcome');
});


