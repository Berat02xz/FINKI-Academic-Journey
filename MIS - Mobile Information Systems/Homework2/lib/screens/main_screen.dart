import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:homework2/services/tmdb_service.dart';
import 'package:homework2/screens/cast_screen.dart';
import 'package:homework2/screens/watchlist_screen.dart';

class MainScreen extends StatefulWidget {
  final String userId;

  MainScreen({required this.userId});

  @override
  _MainScreenState createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  Map<String, dynamic>? movie;
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    fetchMovie();
  }

  Future<void> fetchMovie() async {
    setState(() {
      isLoading = true;
    });
    try {
      final fetchedMovie = await TMDbService.fetchRandomMovie();
      setState(() {
        movie = fetchedMovie;
        isLoading = false;
      });
    } catch (e) {
      print('Error fetching movie: $e');
      setState(() {
        isLoading = false;
      });
    }
  }

  // Function to add movie to Firestore watchlist
  void addToWatchlist() async {
    if (movie != null && movie!['id'] != null) {
      try {
        // Reference to Firestore watchlist collection
        FirebaseFirestore firestore = FirebaseFirestore.instance;
        CollectionReference watchlistRef = firestore.collection('watchlist');

        // Add movie with userId and movieId
        await watchlistRef.add({
          'userId': widget.userId,
          'movieId': movie!['id'],
          'title': movie!['title'],
          'poster_path': movie!['poster_path'],
          'release_date': movie!['release_date'],
          'timestamp': FieldValue.serverTimestamp(),
        });

        // Optionally, show a success message
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Added to watchlist')));

        // Fetch a new movie after adding
        fetchMovie();
      } catch (e) {
        print("Error adding movie to watchlist: $e");
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Error adding to watchlist')));
      }
    } else {
      print("No movie to add to watchlist.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Discover Movies'),
        actions: [
          IconButton(
            icon: Icon(Icons.list),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => WatchlistScreen(userId: widget.userId), // Pass userId here
                ),
              );
            },
          ),
        ],
      ),
      body: isLoading
          ? Center(child: CircularProgressIndicator())
          : movie == null
          ? Center(child: Text('No movie found'))
          : Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Image.network(
              'https://image.tmdb.org/t/p/w500${movie!['poster_path']}',
              height: 300,
            ),
            SizedBox(height: 20),
            Text(
              '${movie!['title']} (${movie!['release_date'].split('-')[0]})',
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10),
            // Safeguard for 'credits' and 'cast'
            movie?['credits']?['cast'] != null
                ? Expanded(
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: movie!['credits']['cast'].length,
                itemBuilder: (context, index) {
                  final cast = movie!['credits']['cast'][index];
                  return GestureDetector(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => CastScreen(cast: cast),
                        ),
                      );
                    },
                    child: Column(
                      children: [
                        cast['profile_path'] != null
                            ? Image.network(
                          'https://image.tmdb.org/t/p/w92${cast['profile_path']}',
                          width: 50,
                        )
                            : Placeholder(fallbackWidth: 50),
                        SizedBox(height: 5),
                        Text(
                          cast['name'] ?? '',
                          style: TextStyle(fontSize: 12),
                        ),
                      ],
                    ),
                  );
                },
              ),
            )
                : Center(child: Text('No cast information available')),
            SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: addToWatchlist,
                  child: Text('Add to Watchlist'),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.green,
                  ),
                ),
                SizedBox(width: 10),
                ElevatedButton(
                  onPressed: fetchMovie,
                  child: Text('Ignore'),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.red,
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
