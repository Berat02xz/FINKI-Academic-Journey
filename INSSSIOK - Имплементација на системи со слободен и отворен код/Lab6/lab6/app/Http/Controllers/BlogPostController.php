<?php

namespace App\Http\Controllers;

use App\Models\BlogPost;
use App\Models\Category;
use Illuminate\Http\Request;

class BlogPostController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): \Illuminate\Contracts\View\View|\Illuminate\Contracts\View\Factory|\Illuminate\Foundation\Application
    {
        $blogPosts = BlogPost::all();
        return view('blogposts.index', compact('blogPosts'));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create(): \Illuminate\Contracts\View\View|\Illuminate\Contracts\View\Factory|\Illuminate\Foundation\Application
    {
        $categories = Category::all(); // Get all categories for the dropdown
        return view('blogposts.create', compact('categories'));
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request): \Illuminate\Http\RedirectResponse
    {
        $request->validate([
           'title' => 'required|string|max:255',
           'description' => 'required|string',
           'category_id' => 'required|exists:categories,id',
        ]);

        BlogPost::create($request->all());

        return redirect()->route('blogposts.index')->with('success', 'Blog post created successfully.');


    }

    /**
     * Display the specified resource.
     */
    public function show($id): \Illuminate\Contracts\View\View|\Illuminate\Contracts\View\Factory|\Illuminate\Foundation\Application
    {
        $blogPost = BlogPost::with('category')->findOrFail($id);
        return view('blogposts.show', compact('blogPost'));
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit($id): \Illuminate\Contracts\View\View|\Illuminate\Contracts\View\Factory|\Illuminate\Foundation\Application
    {
        $blogPost = BlogPost::findOrFail($id);
        $categories = Category::all(); // Get all categories for the dropdown
        return view('blogposts.edit', compact('blogPost', 'categories'));
    }

    // Update the specified blog post
    public function update(Request $request, $id): \Illuminate\Http\RedirectResponse
    {
        $request->validate([
            'title' => 'required|string|max:255',
            'description' => 'required|string',
            'category_id' => 'required|exists:categories,id',
        ]);

        $blogPost = BlogPost::findOrFail($id);
        $blogPost->update($request->all());

        return redirect()->route('blogposts.index')->with('success', 'Blog post updated successfully.');
    }

    // Remove the specified blog post
    public function destroy($id): \Illuminate\Http\RedirectResponse
    {
        $blogPost = BlogPost::findOrFail($id);
        $blogPost->delete();

        return redirect()->route('blogposts.index')->with('success', 'Blog post deleted successfully.');
    }
}
