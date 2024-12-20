<?php

namespace App\Providers;

use App\Models\BlogPost;
use App\Models\Category;
use App\Observers\BlogPostObserver;
use App\Observers\CategoryObserver;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     */
    public function register(): void
    {
        //
    }

    /**
     * Bootstrap any application services.
     */
    public function boot(): void
    {
        // Registering the observers
        Category::observe(CategoryObserver::class);
        BlogPost::observe(BlogPostObserver::class);
    }
}
