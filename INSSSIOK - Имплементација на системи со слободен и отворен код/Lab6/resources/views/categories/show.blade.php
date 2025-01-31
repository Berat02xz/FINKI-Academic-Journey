<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category - {{ $category->name }}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Category: {{ $category->name }}</h1>

    <h3>Related Blog Posts</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        @foreach ($category->blogPosts as $blogPost)
            <tr>
                <td>{{ $blogPost->id }}</td>
                <td>{{ $blogPost->title }}</td>
                <td>
                    <a href="{{ route('blogposts.show', $blogPost->id) }}" class="btn btn-info">View</a>
                    <a href="{{ route('blogposts.edit', $blogPost->id) }}" class="btn btn-warning">Edit</a>
                    <form action="{{ route('blogposts.destroy', $blogPost->id) }}" method="POST" style="display:inline;">
                        @csrf
                        @method('DELETE')
                        <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</button>
                    </form>
                </td>
            </tr>
        @endforeach
        </tbody>
    </table>

    <a href="{{ route('categories.index') }}" class="btn btn-primary">Back to Categories</a>
</div>
</body>
</html>
