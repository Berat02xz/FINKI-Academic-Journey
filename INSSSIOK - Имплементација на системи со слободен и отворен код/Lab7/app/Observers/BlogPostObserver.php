<?php

namespace App\Observers;

use App\Models\BlogPost;

class BlogPostObserver
{
    /**
     * Handle the BlogPost "created" event.
     */
    public function created(BlogPost $blogPost): void
    {
        // Automatically generate the slug if not set
        if (empty($blogPost->slug)) {
            $blogPost->slug = str_slug($blogPost->title);
        }
    }

    /**
     * Handle the BlogPost "updated" event.
     */
    public function updated(BlogPost $blogPost): void
    {
        // Regenerate the slug if the title is updated
        if ($blogPost->isDirty('title')) {
            $blogPost->slug = str_slug($blogPost->title);
        }
    }

    /**
     * Handle the BlogPost "deleted" event.
     */
    public function deleted(BlogPost $blogPost): void
    {
        //
    }

    /**
     * Handle the BlogPost "restored" event.
     */
    public function restored(BlogPost $blogPost): void
    {
        //
    }

    /**
     * Handle the BlogPost "force deleted" event.
     */
    public function forceDeleted(BlogPost $blogPost): void
    {
        //
    }
}
