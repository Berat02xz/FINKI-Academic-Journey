import 'dart:math';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:lab_2/screens/liked_jokes.dart';
import 'screens/joke_types_screen.dart';
import 'screens/joke_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(); // Initialize Firebase before running the app
  FirebaseMessaging.onBackgroundMessage(_firebaseMessagingBackgroundHandler);
  runApp(const JokeApp());
}
Future<void> _firebaseMessagingBackgroundHandler(RemoteMessage message) async {
  print("Handling a background message: ${message.messageId}");
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

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String? userId;
  bool isLoading = false;

  /// Generates a random user ID and saves it to Firestore if it doesn't exist
  Future<void> _initializeUser() async {
    setState(() {
      isLoading = true;
    });

    final randomUserId = _generateRandomString(16); // Generate a random string
    final usersCollection = FirebaseFirestore.instance.collection('users');

    // Check if the user already exists or create a new entry
    final userDoc = await usersCollection.doc(randomUserId).get();
    if (!userDoc.exists) {
      await usersCollection.doc(randomUserId).set({'userId': randomUserId});
    }

    setState(() {
      userId = randomUserId;
      isLoading = false;
    });
  }

  /// Generates a random alphanumeric string of a given length
  String _generateRandomString(int length) {
    const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    final random = Random();
    return String.fromCharCodes(
      Iterable.generate(length, (_) => chars.codeUnitAt(random.nextInt(chars.length))),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Random Jokes'),
      ),
      body: Column(
        children: [
          // Space for JokeTypesScreen to appear
          Expanded(
            child: userId == null
                ? Center(
              child: ElevatedButton(
                onPressed: isLoading ? null : _initializeUser,
                style: ElevatedButton.styleFrom(
                  minimumSize: const Size(200, 50),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25),
                  ),
                  backgroundColor: Colors.blue,
                ),
                child: isLoading
                    ? const CircularProgressIndicator(color: Colors.white)
                    : const Text(
                  'Generate User ID',
                  style: TextStyle(fontSize: 18, color: Colors.white),
                ),
              ),
            )
                : const JokeTypesScreen(), // Space for joke types
          ),

          // Space for buttons
          if (userId != null)
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                children: [
                  // "Joke of the Day" button in a large card
                  Card(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    elevation: 5,
                    child: SizedBox(
                      width: double.infinity, // Expand to full width
                      height: 100, // Large size for the card
                      child: ElevatedButton(
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => JokeScreen(userId: userId!),
                            ),
                          );
                        },
                        style: ElevatedButton.styleFrom(
                          backgroundColor: Colors.blue,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(15),
                          ),
                        ),
                        child: const Text(
                          'Joke of the Day',
                          style: TextStyle(fontSize: 20, color: Colors.white),
                        ),
                      ),
                    ),
                  ),
                  const SizedBox(height: 16), // Spacing between cards

                  // "Liked Jokes" button in a large card
                  Card(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    elevation: 5,
                    child: SizedBox(
                      width: double.infinity, // Expand to full width
                      height: 100, // Large size for the card
                      child: ElevatedButton(
                        onPressed: () {
                          if (userId != null) {
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                builder: (context) => LikedJokesScreen(userId: userId!),
                              ),
                            );
                          }
                        },
                        style: ElevatedButton.styleFrom(
                          backgroundColor: Colors.blue,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(15),
                          ),
                        ),
                        child: const Text(
                          'Go to Liked Jokes',
                          style: TextStyle(fontSize: 20, color: Colors.white),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
        ],
      ),
    );
  }
}