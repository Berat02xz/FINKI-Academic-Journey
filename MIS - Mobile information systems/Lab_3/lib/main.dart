import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'screens/joke_types_screen.dart';
import 'screens/joke_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(); // Initialize Firebase before running the app
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
      home: const HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  // Generate a unique userId, could be from Firebase or any other method
  String generateUserId() {
    return DateTime.now().millisecondsSinceEpoch.toString(); // Example userId (timestamp)
  }

  @override
  Widget build(BuildContext context) {
    // Generate userId when the screen is created
    final userId = generateUserId();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Random Jokes'),
      ),
      body: JokeTypesScreen(userId: userId), // Pass the userId to JokeTypesScreen
      bottomNavigationBar: Padding(
        padding: const EdgeInsets.all(8.0),
        child: ElevatedButton(
          onPressed: () {
            // Navigate to JokeScreen and pass the userId
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => JokeScreen(userId: userId), // Pass userId here
              ),
            );
          },
          style: ElevatedButton.styleFrom(
            minimumSize: const Size(200, 50),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(25),
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
