<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Category extends Model
{
    use HasFactory;

    // The attributes that are mass assignable.
    protected $fillable = ['name'];
    protected $table = 'Category'; // Match the exact name of your table

    // Define the relationship with BlogPost
    public function blogPosts(): \Illuminate\Database\Eloquent\Relations\HasMany
    {
        return $this->hasMany(BlogPost::class);
    }
}
