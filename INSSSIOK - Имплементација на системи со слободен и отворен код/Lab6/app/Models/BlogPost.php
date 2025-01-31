<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class BlogPost extends Model
{
    use HasFactory;

    // The attributes that are mass assignable.
    protected $fillable = ['title', 'description', 'category_id'];
    protected $table = 'BlogPost'; // Match the exact name of your table

    // Define the relationship with Category
    public function category(): \Illuminate\Database\Eloquent\Relations\BelongsTo
    {
        return $this->belongsTo(Category::class);
    }
}
