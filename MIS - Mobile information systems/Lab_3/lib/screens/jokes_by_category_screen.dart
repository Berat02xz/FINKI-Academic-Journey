import 'package:flutter/material.dart';
import '../models/joke.dart';
import '../services/api_services.dart';

class JokesByCategoryScreen extends StatefulWidget {
  final String type;

  const JokesByCategoryScreen({Key? key, required this.type}) : super(key: key);

  @override
  _JokesByCategoryScreenState createState() => _JokesByCategoryScreenState();
}

class _JokesByCategoryScreenState extends State<JokesByCategoryScreen> {
  late Future<List<Joke>> _jokes;
  Map<int, bool> _likedJokes = {}; // Store like state for each joke

  @override
  void initState() {
    super.initState();
    _jokes = ApiServices.fetchJokesByType(widget.type) as Future<List<Joke>>; // Fetch jokes based on type
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('${widget.type} Jokes'),
        backgroundColor: Colors.deepPurpleAccent, // Matching the theme color
      ),
      body: FutureBuilder<List<Joke>>(
        future: _jokes,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('No jokes available.'));
          } else {
            final jokes = snapshot.data!;
            return ListView.builder(
              padding: const EdgeInsets.all(16.0),
              itemCount: jokes.length,
              itemBuilder: (context, index) {
                final joke = jokes[index];
                return Padding(
                  padding: const EdgeInsets.only(bottom: 16.0), // Space between cards
                  child: Card(
                    color: Colors.deepPurpleAccent,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(12), // Rounded corners
                    ),
                    elevation: 5, // Shadow effect
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            joke.setup,
                            style: const TextStyle(
                              color: Colors.white,
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          const SizedBox(height: 8),
                          Text(
                            joke.punchline,
                            style: const TextStyle(
                              color: Colors.white70,
                              fontSize: 16,
                            ),
                          ),
                          const SizedBox(height: 10),
                          IconButton(
                            icon: Icon(
                              _likedJokes[index] == true
                                  ? Icons.favorite
                                  : Icons.favorite_border,
                              size: 30,
                              color: _likedJokes[index] == true
                                  ? Colors.red
                                  : Colors.grey,
                            ),
                            onPressed: () {
                              setState(() {
                                _likedJokes[index] = !(_likedJokes[index] ?? false);
                              });
                            },
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              },
            );
          }
        },
      ),
    );
  }
}
