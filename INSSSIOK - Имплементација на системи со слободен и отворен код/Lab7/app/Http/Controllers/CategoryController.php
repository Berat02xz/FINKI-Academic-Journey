<?php

namespace App\Http\Controllers;

use App\Http\Resources\CategoryResource;
use App\Models\Category;
use Database\Factories\CategoryFactory;
use Illuminate\Http\Request;

class CategoryController extends Controller
{
    /**
     * Display a listing of the resource.
     */
// Index: Get all categories
    public function index()
    {
        $categories = Category::all();
        return CategoryResource::collection($categories);
    }

    // Show: Get a single category by ID
    public function show($id)
    {
        $category = Category::findOrFail($id);
        return new CategoryResource($category);
    }

    // Store: Create a new category
    public function store(Request $request)
    {
        $validated = $request->validate([
            'name' => 'required|unique:categories,name',
        ]);

        $category = Category::create($validated);
        return new CategoryResource($category);
    }

    // Update: Update an existing category
    public function update(Request $request, $id)
    {
        $category = Category::findOrFail($id);

        $validated = $request->validate([
            'name' => 'required|unique:categories,name,' . $category->id,
        ]);

        $category->update($validated);
        return new CategoryResource($category);
    }

    // Destroy: Delete a category
    public function destroy($id)
    {
        $category = Category::findOrFail($id);
        $category->delete();
        return response()->json(null, 204);
    }
}
