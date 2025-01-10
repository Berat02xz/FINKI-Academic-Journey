import 'package:flutter/material.dart';

class CastScreen extends StatelessWidget {
  final Map<String, dynamic> cast;

  CastScreen({required this.cast});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(cast['name'] ?? 'Cast Member'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            cast['profile_path'] != null
                ? Image.network(
              'https://image.tmdb.org/t/p/w500${cast['profile_path']}',
              height: 300,
            )
                : Placeholder(fallbackHeight: 200),
            SizedBox(height: 20),
            Text(
              cast['name'] ?? 'Name not available',
              style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10),
            Text(
              'Known for: ${cast['known_for_department'] ?? 'Unknown'}',
              style: TextStyle(fontSize: 16),
            ),
            SizedBox(height: 10),
            Text(
              cast['biography'] ?? 'Biography not available',
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 16),
            ),
            Spacer(),
            ElevatedButton(
              onPressed: () {
                Navigator.pop(context);
              },
              child: Text('Go Back'),
            ),
          ],
        ),
      ),
    );
  }
}
