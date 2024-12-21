import 'package:flutter/material.dart';
import 'screens/joke_types_screen.dart';
import 'screens/joke_screen.dart'; // Import JokeScreen

void main() {
  runApp(const JokeApp());
}

class JokeApp extends StatelessWidget {
  const JokeApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Random Jokes',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const HomeScreen(), // HomeScreen which wraps all the screens
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Random Jokes'),
      ),
      body: const JokeTypesScreen(), // Initial screen with joke categories
      bottomNavigationBar: Padding(
        padding: const EdgeInsets.all(8.0),
        child: ElevatedButton(
          onPressed: () {
            // Navigate to the JokeScreen when the button is pressed
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const JokeScreen()),
            );
          },
          style: ElevatedButton.styleFrom(
            minimumSize: const Size(200, 50), // Button size
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(25), // Rounded corners
            ),
          ),
          child: const Text(
            'Joke of the Day',
            style: TextStyle(fontSize: 18),
          ),
        ),
      ),
    );
  }
}
