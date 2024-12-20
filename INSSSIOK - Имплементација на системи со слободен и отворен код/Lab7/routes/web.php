<?php

use App\Http\Controllers\BlogPostController;
use App\Http\Controllers\CategoryController;
use App\Http\Resources\BlogPostResource;
use App\Http\Resources\CategoryResource;
use App\Models\BlogPost;
use App\Models\Category;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/categories', function () {
    return CategoryResource::collection(Category::all());
});

Route::get('/blogposts', function () {
    return BlogPostResource::collection(BlogPost::all());
});
