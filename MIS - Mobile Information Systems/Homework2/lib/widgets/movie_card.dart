import 'package:flutter/material.dart';

class MovieCard extends StatelessWidget {
  final String title;
  final String posterPath;
  final VoidCallback onRemove;

  MovieCard({
    required this.title,
    required this.posterPath,
    required this.onRemove,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      child: ListTile(
        leading: posterPath.isNotEmpty
            ? Image.network(
          'https://image.tmdb.org/t/p/w92$posterPath',
          width: 50,
        )
            : Placeholder(fallbackWidth: 50),
        title: Text(title),
        trailing: IconButton(
          icon: Icon(Icons.close),
          onPressed: onRemove,
        ),
      ),
    );
  }
}
