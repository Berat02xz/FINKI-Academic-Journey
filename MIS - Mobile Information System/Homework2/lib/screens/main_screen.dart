import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:homework2/services/tmdb_service.dart';
import 'package:homework2/screens/watchlist_screen.dart'; // Import WatchlistScreen
import 'cast_screen.dart'; // Import the CastScreen

class MainScreen extends StatefulWidget {
  final String userId;

  MainScreen({required this.userId});

  @override
  _MainScreenState createState() => _MainScreenState();
}

final GlobalKey<ScaffoldMessengerState> _scaffoldMessengerKey = GlobalKey<ScaffoldMessengerState>();

class _MainScreenState extends State<MainScreen> {
  Map<String, dynamic>? movie;
  bool isLoading = true;
  String _movieBackdropUrl = '';
  String _movieTitle = '';
  String _movieDescription = '';
  String _movieRating = '';
  String _movieReleaseDate = '';
  List<dynamic> cast = []; // To hold the cast data

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
      final movieId = fetchedMovie['id']; // Get the movie ID
      final fetchedCast = await TMDbService.fetchMovieCast(movieId); // Fetch the cast for the movie

      setState(() {
        movie = fetchedMovie;
        cast = fetchedCast; // Set the cast data
        _movieBackdropUrl = 'https://image.tmdb.org/t/p/original${movie!['backdrop_path']}';
        _movieTitle = movie!['title'];
        _movieDescription = movie!['overview'] ?? 'No description available.';
        _movieRating = movie!['vote_average'].toString();
        _movieReleaseDate = movie!['release_date']?.substring(0, 4) ?? 'Unknown'; // Extract only the year
        isLoading = false;
      });
    } catch (e) {
      print('Error fetching movie: $e');
      setState(() {
        isLoading = false;
      });
      _scaffoldMessengerKey.currentState?.showSnackBar(
        SnackBar(
          content: const Text('Failed to fetch movie'),
          backgroundColor: Colors.red,
          duration: const Duration(seconds: 2),
        ),
      );
    }
  }

  // Function to add movie to Firestore watchlist
  void addToWatchlist() async {
    if (movie != null && movie!['id'] != null) {
      try {
        FirebaseFirestore firestore = FirebaseFirestore.instance;
        CollectionReference watchlistRef = firestore.collection('watchlist');

        await watchlistRef.add({
          'userId': widget.userId,
          'movieId': movie!['id'],
          'title': movie!['title'],
          'poster_path': movie!['poster_path'],
          'release_date': movie!['release_date'],
          'timestamp': FieldValue.serverTimestamp(),
        });

        // Display the Snackbar at the top of the screen using _scaffoldMessengerKey
        _scaffoldMessengerKey.currentState?.showSnackBar(
          SnackBar(
            content: const Text('Added to watchlist'),
            behavior: SnackBarBehavior.floating,
            margin: const EdgeInsets.only(top: 50, left: 20, right: 20), // Position at the top
            backgroundColor: Colors.green,
            duration: const Duration(seconds: 2),
          ),
        );

        fetchMovie(); // Fetch a new movie after adding
      } catch (e) {
        print("Error adding movie to watchlist: $e");

        // Display the Snackbar at the top of the screen using _scaffoldMessengerKey
        _scaffoldMessengerKey.currentState?.showSnackBar(
          SnackBar(
            content: const Text('Error adding to watchlist'),
            behavior: SnackBarBehavior.floating,
            margin: const EdgeInsets.only(top: 50, left: 20, right: 20), // Position at the top
            backgroundColor: Colors.red,
            duration: const Duration(seconds: 2),
          ),
        );
      }
    }
  }

  // Function to convert rating to stars (emoji or icons)
  Widget _buildRatingStars(double rating) {
    rating = rating.clamp(0.0, 5.0);

    int fullStars = rating ~/ 1;
    double decimalPart = rating - fullStars;
    int halfStars = decimalPart >= 0.5 ? 1 : 0;
    int emptyStars = 5 - fullStars - halfStars;

    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        ...List.generate(fullStars, (index) => Icon(Icons.star, color: Colors.yellow)),
        ...List.generate(halfStars, (index) => Icon(Icons.star_half, color: Colors.yellow)),
        ...List.generate(emptyStars, (index) => Icon(Icons.star_border, color: Colors.yellow)),
      ],
    );
  }

  // Function to limit description text
  Widget _buildDescription(String description) {
    const int maxLines = 3;  // Limit to 3 lines
    return Text(
      description,
      textAlign: TextAlign.center,
      maxLines: maxLines,
      overflow: TextOverflow.ellipsis,  // Adding ellipsis if the text exceeds max lines
      style: const TextStyle(
        color: Colors.white,
        fontSize: 14,
        fontFamily: 'Roboto',
        fontWeight: FontWeight.w400,
      ),
    );
  }

  Widget _buildCastSection() {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 6.0),
      child: Card(
        color: Colors.black.withOpacity(0.5), // Lighter background for the section
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15),
        ),
        child: Padding(
          padding: const EdgeInsets.all(1.0), // Padding for the whole cast section
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text(
                'Movie Cast',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 12,  // Smaller font size for title
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 5),
              // Scrollable horizontal list of cast members
              SizedBox(
                height: 180, // Decreased height for the cast cards
                child: ListView.builder(
                  scrollDirection: Axis.horizontal,
                  itemCount: cast.length > 5 ? 5 : cast.length, // Display up to 5 cast members
                  itemBuilder: (context, index) {
                    var actor = cast[index];
                    return Padding(
                      padding: const EdgeInsets.only(right: 10.0), // Fixed margin between cast cards
                      child: GestureDetector(
                        onTap: () {
                          // Navigate to CastScreen and pass the userId and the actor data
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => CastScreen(
                                userId: widget.userId,
                                cast: actor, // Pass the actor object
                              ),
                            ),
                          );
                        },
                        child: Card(
                          color: Colors.grey[850], // Light grey background for individual cast cards
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12),
                          ),
                          child: Padding(
                            padding: const EdgeInsets.all(4.0),  // Smaller padding
                            child: Column(
                              mainAxisSize: MainAxisSize.min, // Ensure the column is sized by content
                              children: [
                                // Actor image
                                ClipRRect(
                                  borderRadius: BorderRadius.circular(8), // Rounded image corners
                                  child: actor['profile_path'] != null
                                      ? Image.network(
                                    'https://image.tmdb.org/t/p/w500${actor['profile_path']}',
                                    width: 80, // Even smaller width
                                    height: 120, // Even smaller height
                                    fit: BoxFit.cover,
                                  )
                                      : Image.asset(
                                    'assets/images/default_actor.jpg',
                                    width: 80, // Even smaller width
                                    height: 120, // Even smaller height
                                    fit: BoxFit.cover,
                                  ),
                                ),
                                const SizedBox(height: 8),
                                // Actor name
                                Text(
                                  actor['name'],
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontSize: 10, // Smaller font size for the name
                                    fontWeight: FontWeight.bold,
                                    overflow: TextOverflow.ellipsis,
                                  ),
                                  maxLines: 1,
                                  textAlign: TextAlign.center,
                                ),
                              ],
                            ),
                          ),
                        ),
                      ),
                    );
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }









  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldMessengerKey,
      body: LayoutBuilder(
        builder: (context, constraints) {
          return Stack(
            children: [
              if (_movieBackdropUrl.isNotEmpty)
                Positioned(
                  top: 0,
                  left: 0,
                  right: 0,
                  child: Image.network(
                    _movieBackdropUrl,
                    fit: BoxFit.cover,
                    height: constraints.maxHeight * 0.55,
                    loadingBuilder: (context, child, loadingProgress) {
                      if (loadingProgress == null) return child;
                      return const Center(child: CircularProgressIndicator());
                    },
                  ),
                ),
              if (movie != null && movie!['poster_path'] != null)
                Positioned(
                  top: constraints.maxHeight * 0.06,
                  left: constraints.maxWidth * 0.25,
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(10),
                    child: Image.network(
                      'https://image.tmdb.org/t/p/w500${movie!['poster_path']}',
                      width: 200,
                      height: 300,
                    ),
                  ),
                ),
              // Top right button for WatchlistScreen
              Positioned(
                top: 30,
                right: 30,
                child: IconButton(
                  icon: const Icon(Icons.list, color: Colors.white, size: 30),
                  onPressed: () {
                    // Navigate to WatchlistScreen and pass userId
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => WatchlistScreen(userId: widget.userId),
                      ),
                    );
                  },
                ),
              ),
              Positioned(
                top: constraints.maxHeight * 0.45,
                bottom: 0,
                left: 0,
                right: 0,
                child: SingleChildScrollView(
                  child: Container(
                    padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 20),
                    decoration: BoxDecoration(
                      color: Colors.black.withOpacity(1),
                      borderRadius: const BorderRadius.only(topLeft: Radius.circular(0), topRight: Radius.circular(0)),
                    ),
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          _movieTitle,
                          style: const TextStyle(
                            color: Colors.white,
                            fontSize: 24,
                            fontFamily: 'Andada Pro',
                            fontWeight: FontWeight.bold,
                            height: 1.4,
                          ),
                          textAlign: TextAlign.center,  // Center the movie title
                        ),
                        const SizedBox(height: 8),
                        _buildRatingStars(double.parse(_movieRating)),
                        const SizedBox(height: 16),
                        _buildDescription(_movieDescription),
                        const SizedBox(height: 16),
                        Text(
                          'Release Date: $_movieReleaseDate',
                          style: const TextStyle(color: Colors.white, fontSize: 14),
                        ),
                        _buildCastSection(), // Cast section below description
                        const SizedBox(height: 100),
                      ],
                    ),
                  ),
                ),
              ),
              // Buttons at the bottom
              Positioned(
                bottom: 0,
                left: 0,
                right: 0,
                child: Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Container(
                        height: 55,
                        decoration: BoxDecoration(
                          color: Colors.green,
                          borderRadius: BorderRadius.circular(100),
                        ),
                        child: MaterialButton(
                          onPressed: addToWatchlist,
                          child: const Center(
                            child: Icon(
                              Icons.add,
                              color: Colors.white,
                              size: 24,
                            ),
                          ),
                        ),
                      ),
                      const SizedBox(width: 10),
                      Container(
                        height: 55,
                        decoration: BoxDecoration(
                          color: Colors.red,
                          borderRadius: BorderRadius.circular(100),
                        ),
                        child: MaterialButton(
                          onPressed: fetchMovie,
                          child: const Center(
                            child: Icon(
                              Icons.cancel,
                              color: Colors.white,
                              size: 24,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          );
        },
      ),
    );
  }
}
