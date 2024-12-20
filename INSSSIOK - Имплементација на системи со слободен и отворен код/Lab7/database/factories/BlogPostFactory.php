<?php

namespace Database\Factories;

use App\Models\BlogPost;
use App\Models\Category;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Str;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\BlogPost>
 */
class BlogPostFactory extends Factory
{
    protected $model = BlogPost::class;
    public function definition(): array
    {
        return [
            'title' => $this->faker->sentence(),
            'slug' => Str::slug($this->faker->sentence()),
            'description' => $this->faker->paragraph(),
            'category_id' => Category::factory(), // Creates a random category
        ];
    }
}
