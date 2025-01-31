<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Post - {{ $blogPost->title }}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>{{ $blogPost->title }}</h1>
    <p>{{ $blogPost->description }}</p>
    <p><strong>Category:</strong> {{ $blogPost->category->name }}</p>

    <a href="{{ route('blogposts.index') }}" class="btn btn-primary">Back to Blog Posts</a>
    <a href="{{ route('blogposts.edit', $blogPost->id) }}" class="btn btn-warning">Edit Blog Post</a>

    <form action="{{ route('blogposts.destroy', $blogPost->id) }}" method="POST" style="display:inline;">
        @csrf
        @method('DELETE')
        <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete Blog Post</button>
    </form>
</div>
</body>
</html>
