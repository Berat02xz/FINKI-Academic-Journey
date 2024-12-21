import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/joke.dart';
import '../services/api_services.dart';

class JokeScreen extends StatefulWidget {
  final String userId;

  const JokeScreen({Key? key, required this.userId}) : super(key: key);

  @override
  _JokeScreenState createState() => _JokeScreenState();
}

class _JokeScreenState extends State<JokeScreen> {
  late Future<Joke> _joke;

  @override
  void initState() {
    super.initState();
    _joke = ApiServices.fetchJoke() as Future<Joke>;  // Call API for joke
  }

  // Function to handle liking a joke
  void _likeJoke(Joke joke) {
    FirebaseFirestore.instance.collection('liked_jokes').add({
      'setup': joke.setup,
      'punchline': joke.punchline,
      'user_id': widget.userId,
      'created_at': FieldValue.serverTimestamp(),
    }).then((value) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Joke liked!')),
      );
    }).catchError((error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Error: $error')),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Joke of the Day'),
      ),
      body: FutureBuilder<Joke>(
        future: _joke,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());  // Show loader while waiting
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else if (snapshot.hasData) {
            final joke = snapshot.data!;
            return Center(
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    // Display the setup of the joke
                    Text(
                      joke.setup,
                      style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: 10),
                    // Display the punchline of the joke
                    Text(
                      joke.punchline,
                      style: const TextStyle(fontSize: 20, color: Colors.orange),
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: 20),
                    // Like button to like the joke
                    ElevatedButton(
                      onPressed: () => _likeJoke(joke),  // Like the joke
                      child: const Text('Like this Joke'),
                      style: ElevatedButton.styleFrom(
                        minimumSize: const Size(200, 50), // Button size
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(25),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            );
          } else {
            return const Center(child: Text('No joke found.'));
          }
        },
      ),
    );
  }
}
