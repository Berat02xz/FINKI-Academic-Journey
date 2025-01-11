// screens/joke_screen.dart
import 'package:flutter/material.dart';
import '../models/joke.dart';
import '../services/api_services.dart';

class JokeScreen extends StatefulWidget {
  const JokeScreen({Key? key}) : super(key: key);

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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Joke of the Day'),
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
            colors: [Colors.purpleAccent, Colors.blueAccent],  // Background gradient
          ),
        ),
        child: Center(
          child: FutureBuilder<Joke>(
            future: _joke,
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.waiting) {
                return const CircularProgressIndicator();  // Show loader while waiting for the response
              } else if (snapshot.hasError) {
                return Text('Error: ${snapshot.error}');
              } else {
                final joke = snapshot.data!;
                return Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Card(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(16.0),  // Rounded corners for the card
                    ),
                    elevation: 8.0,  // Adding shadow for card
                    color: Colors.white.withOpacity(0.9),  // Card color with slight transparency
                    child: Padding(
                      padding: const EdgeInsets.all(24.0),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          // Setup Text with big, bold font
                          Text(
                            joke.setup,
                            style: const TextStyle(
                              fontSize: 28,  // Larger font size for the setup
                              fontWeight: FontWeight.bold,  // Bold font for setup
                              color: Colors.black,  // Text color for better contrast
                            ),
                            textAlign: TextAlign.center,
                          ),
                          const SizedBox(height: 20),
                          // Punchline Text with slightly smaller font but still prominent
                          Text(
                            joke.punchline,
                            style: const TextStyle(
                              fontSize: 24,  // Slightly smaller font for punchline
                              fontWeight: FontWeight.w600,  // Bold but not as much as setup
                              color: Colors.deepOrangeAccent,  // Text color for punchline
                            ),
                            textAlign: TextAlign.center,
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              }
            },
          ),
        ),
      ),
    );
  }
}
