import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/joke.dart';
import '../services/api_services.dart';

class JokesByCategoryScreen extends StatefulWidget {
  final String type;
  final String userId;

  const JokesByCategoryScreen({Key? key, required this.type, required this.userId}) : super(key: key);

  @override
  _JokesByCategoryScreenState createState() => _JokesByCategoryScreenState();
}

class _JokesByCategoryScreenState extends State<JokesByCategoryScreen> {
  late Future<List<Joke>> _jokes;

  @override
  void initState() {
    super.initState();
    _jokes = ApiServices.fetchJokesByType(widget.type) as Future<List<Joke>>; // Fetch jokes based on type
  }

  void _likeJoke(Joke joke) {
    FirebaseFirestore.instance.collection('liked_jokes').add({
      'setup': joke.setup,
      'punchline': joke.punchline,
      'user_id': widget.userId,
      'created_at': FieldValue.serverTimestamp(),
    });
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
              itemCount: jokes.length,
              itemBuilder: (context, index) {
                return ListTile(
                  title: Text(jokes[index].setup),
                  subtitle: Text(jokes[index].punchline),
                  trailing: IconButton(
                    icon: Icon(Icons.favorite_border),
                    onPressed: () => _likeJoke(jokes[index]),  // Like button functionality
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
