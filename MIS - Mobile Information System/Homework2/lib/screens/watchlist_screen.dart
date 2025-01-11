import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class WatchlistScreen extends StatefulWidget {
  final String userId;  // You need to pass userId to the WatchlistScreen

  WatchlistScreen({required this.userId});

  @override
  _WatchlistScreenState createState() => _WatchlistScreenState();
}

class _WatchlistScreenState extends State<WatchlistScreen> {
  List<Map<String, dynamic>> watchlist = [];

  @override
  void initState() {
    super.initState();
    fetchWatchlist();
  }

  // Fetch the watchlist from Firestore
  Future<void> fetchWatchlist() async {
    try {
      final firestore = FirebaseFirestore.instance;
      final watchlistRef = firestore.collection('watchlist');

      // Get documents for the current user
      final querySnapshot = await watchlistRef
          .where('userId', isEqualTo: widget.userId)
          .get();

      // Map the query results to a list of movie data
      setState(() {
        watchlist = querySnapshot.docs
            .map((doc) => doc.data() as Map<String, dynamic>)
            .toList();
      });
    } catch (e) {
      print("Error fetching watchlist: $e");
    }
  }

  // Remove from watchlist (Firestore)
  void removeFromWatchlist(int index) async {
    try {
      final movieId = watchlist[index]['movieId'];

      // Delete from Firestore
      final firestore = FirebaseFirestore.instance;
      final watchlistRef = firestore.collection('watchlist');
      final snapshot = await watchlistRef
          .where('userId', isEqualTo: widget.userId)
          .where('movieId', isEqualTo: movieId)
          .get();

      if (snapshot.docs.isNotEmpty) {
        await snapshot.docs.first.reference.delete(); // Remove the movie
      }

      // Update the UI
      setState(() {
        watchlist.removeAt(index);
      });
    } catch (e) {
      print("Error removing from watchlist: $e");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Watchlist'),
        backgroundColor: Colors.black,  // Black app bar background
        foregroundColor: Colors.white,  // White text color
      ),
      backgroundColor: Colors.black,  // Set background to black
      body: watchlist.isEmpty
          ? Center(
        child: Text(
          'Your watchlist is empty',
          style: TextStyle(color: Colors.white),  // White text for empty message
        ),
      )
          : ListView.builder(
        padding: const EdgeInsets.all(16.0),  // Add padding for spacing
        itemCount: watchlist.length,
        itemBuilder: (context, index) {
          final movie = watchlist[index];
          return Padding(
            padding: const EdgeInsets.only(bottom: 16.0),  // Add margin between cards
            child: Card(
              color: Colors.black,
              elevation: 5,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Row(
                  children: [
                    ClipRRect(
                      borderRadius: BorderRadius.circular(8),
                      child: movie['poster_path'] != null && movie['poster_path'].isNotEmpty
                          ? Image.network(
                        'https://image.tmdb.org/t/p/w500${movie['poster_path']}',
                        width: 150,  // Increase poster width
                        height: 225, // Increase poster height
                        fit: BoxFit.cover,
                      )
                          : Container(
                        width: 150,
                        height: 225,
                        color: Colors.grey,
                        child: const Center(
                          child: Icon(
                            Icons.movie,
                            color: Colors.white,
                            size: 50,
                          ),
                        ),
                      ),
                    ),
                    const SizedBox(width: 16),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            movie['title'] ?? 'No Title',
                            style: const TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.bold,
                              fontSize: 16,
                            ),
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                          ),
                          const SizedBox(height: 8),
                          Align(
                            alignment: Alignment.centerRight,
                            child: IconButton(
                              icon: const Icon(Icons.remove_circle, color: Colors.red),
                              onPressed: () => removeFromWatchlist(index),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ),
          );
        },
      ),
    );
  }
}
