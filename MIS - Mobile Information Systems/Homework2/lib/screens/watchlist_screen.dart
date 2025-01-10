import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import '../widgets/movie_card.dart';

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
        title: Text('Watchlist'),
      ),
      body: watchlist.isEmpty
          ? Center(child: Text('Your watchlist is empty'))
          : ListView.builder(
        itemCount: watchlist.length,
        itemBuilder: (context, index) {
          final movie = watchlist[index];
          return MovieCard(
            title: movie['title'] ?? 'No Title',
            posterPath: movie['poster_path'] ?? '',
            onRemove: () => removeFromWatchlist(index),
          );
        },
      ),
    );
  }
}
