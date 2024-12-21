import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class LikedJokesScreen extends StatelessWidget {
  final String userId;

  const LikedJokesScreen({super.key, required this.userId});

  @override
  Widget build(BuildContext context) {
    print("User ID passed: $userId"); // Debug print

    return Scaffold(
      appBar: AppBar(
        title: const Text('Liked Jokes'),
      ),
      body: StreamBuilder<QuerySnapshot>(
        stream: FirebaseFirestore.instance
            .collection('liked_jokes')
            .where('userId', isEqualTo: userId) // Filter jokes by userId
            .snapshots(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.docs.isEmpty) {
            return const Center(child: Text('No liked jokes yet!'));
          } else {
            final jokes = snapshot.data!.docs;

            // Debugging: Print the document data to the console
            for (var joke in jokes) {
              print('Joke: ${joke['setup']} - ${joke['punchline']}');
            }

            return Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      // Display the userId
                      Text(
                        'Current userId: $userId',
                        style: const TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      const SizedBox(height: 8),
                      // Display the total number of liked jokes
                      Text(
                        'Total liked jokes: ${jokes.length}',
                        style: const TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ],
                  ),
                ),
                Expanded(
                  child: ListView.builder(
                    itemCount: jokes.length,
                    itemBuilder: (context, index) {
                      final joke = jokes[index];
                      final setup = joke['setup'] ?? 'No setup available';
                      final punchline = joke['punchline'] ?? 'No punchline available';

                      return Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Card(
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(16.0),
                          ),
                          elevation: 8.0,
                          color: Colors.white.withOpacity(0.9),
                          child: Padding(
                            padding: const EdgeInsets.all(16.0),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  setup,
                                  style: const TextStyle(
                                    fontSize: 22,
                                    fontWeight: FontWeight.bold,
                                    color: Colors.black,
                                  ),
                                ),
                                const SizedBox(height: 8),
                                Text(
                                  punchline,
                                  style: const TextStyle(
                                    fontSize: 20,
                                    color: Colors.deepOrangeAccent,
                                  ),
                                ),
                              ],
                            ),
                          ),
                        ),
                      );
                    },
                  ),
                ),
              ],
            );
          }
        },
      ),
    );
  }
}
