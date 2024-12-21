import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class LikedJokesScreen extends StatelessWidget {
  final String userId;

  const LikedJokesScreen({Key? key, required this.userId}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Liked Jokes'),
      ),
      body: StreamBuilder<QuerySnapshot>(
        stream: FirebaseFirestore.instance
            .collection('liked_jokes')
            .where('user_id', isEqualTo: userId)
            .snapshots(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }

          if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          }

          if (!snapshot.hasData || snapshot.data!.docs.isEmpty) {
            return const Center(child: Text('No liked jokes yet.'));
          }

          final likedJokes = snapshot.data!.docs;

          return ListView.builder(
            itemCount: likedJokes.length,
            itemBuilder: (context, index) {
              final jokeData = likedJokes[index].data() as Map<String, dynamic>;
              final String setup = jokeData['setup'] ?? '';
              final String punchline = jokeData['punchline'] ?? '';

              return Padding(
                padding: const EdgeInsets.all(8.0),
                child: Card(
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                  elevation: 4,
                  child: Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          setup,
                          style: const TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        const SizedBox(height: 8),
                        Text(
                          punchline,
                          style: const TextStyle(
                            fontSize: 18,
                            color: Colors.deepOrangeAccent,
                          ),
                        ),
                        const SizedBox(height: 8),
                        ElevatedButton(
                          onPressed: () {
                            // Add logic to unlike the joke or perform another action
                            FirebaseFirestore.instance
                                .collection('liked_jokes')
                                .doc(likedJokes[index].id)
                                .delete();  // This will remove the joke from the liked list
                          },
                          style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.red,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(10),
                            ),
                          ),
                          child: const Text('Unlike'),
                        ),
                      ],
                    ),
                  ),
                ),
              );
            },
          );
        },
      ),
    );
  }
}
