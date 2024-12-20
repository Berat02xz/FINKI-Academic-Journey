<?php

namespace App\Http\Controllers;

use App\Http\Resources\BlogPostResource;
use App\Models\BlogPost;
use Illuminate\Http\Request;

class BlogPostController extends Controller
{
    // Index: Get all blog posts
    public function index()
    {
        $blogPosts = BlogPost::with('category')->get();
        return BlogPostResource::collection($blogPosts);
    }

    // Show: Get a single blog post by ID
    public function show($id): BlogPostResource
    {
        $blogPost = BlogPost::with('category')->findOrFail($id);
        return new BlogPostResource($blogPost);
    }

    // Store: Create a new blog post
    public function store(Request $request): BlogPostResource
    {
        $validated = $request->validate([
            'title' => 'required',
            'description' => 'required|min:50',
            'category_id' => 'required|exists:categories,id',
        ]);

        $blogPost = BlogPost::create($validated);
        return new BlogPostResource($blogPost);
    }

    // Update: Update an existing blog post
    public function update(Request $request, $id): BlogPostResource
    {
        $blogPost = BlogPost::findOrFail($id);

        $validated = $request->validate([
            'title' => 'required',
            'description' => 'required|min:50',
            'category_id' => 'required|exists:categories,id',
        ]);

        $blogPost->update($validated);
        return new BlogPostResource($blogPost);
    }

    // Destroy: Delete a blog post
    public function destroy($id): \Illuminate\Http\JsonResponse
    {
        $blogPost = BlogPost::findOrFail($id);
        $blogPost->delete();
        return response()->json(null, 204);
    }
}
