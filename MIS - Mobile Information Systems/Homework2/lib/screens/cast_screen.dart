import 'package:flutter/material.dart';
import 'package:homework2/services/tmdb_service.dart';

class CastScreen extends StatefulWidget {
  final String userId;
  final dynamic cast;

  CastScreen({required this.userId, required this.cast});

  @override
  _CastScreenState createState() => _CastScreenState();
}

class _CastScreenState extends State<CastScreen> {
  bool isLoading = true;
  String _bio = '';
  List<dynamic> _movies = [];

  @override
  void initState() {
    super.initState();
    _fetchActorDetails();
  }

  // Fetch actor's biography and movies
  Future<void> _fetchActorDetails() async {
    try {
      final bioAndMovies = await TMDbService.fetchActorDetails(widget.cast['id']);
      setState(() {
        _bio = bioAndMovies['bio'] ?? 'Biography not available.';
        _movies = bioAndMovies['movies'] ?? [];
        isLoading = false;
      });
    } catch (e) {
      setState(() {
        isLoading = false;
      });
      print("Error fetching actor details: $e");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        title: Text("${widget.cast['name']} - Cast Details"),
        backgroundColor: Colors.black,
        foregroundColor: Colors.white,
        elevation: 0,
      ),
      body: isLoading
          ? Center(child: CircularProgressIndicator())
          : SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Center(  // Centering the profile picture
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(100),
                  child: widget.cast['profile_path'] != null
                      ? Image.network(
                    'https://image.tmdb.org/t/p/w500${widget.cast['profile_path']}',
                    width: 150,
                    height: 150,
                    fit: BoxFit.cover,
                  )
                      : Image.asset(
                    'assets/images/default_actor.jpg',
                    width: 150,
                    height: 150,
                    fit: BoxFit.cover,
                  ),
                ),
              ),
            ),
            const SizedBox(height: 12),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20.0),
              child: Text(
                _bio,
                maxLines: 10,
                overflow: TextOverflow.ellipsis,
                textAlign: TextAlign.left,
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 14,  // Reduced font size for biography
                  fontWeight: FontWeight.w400,
                ),
              ),
            ),
            const SizedBox(height: 20),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20.0),
              child: const Text(
                'Movies',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 22,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            const SizedBox(height: 12),
            SizedBox(
              height: 280, // Increased height to give more room for movie content
              child: _movies.isEmpty
                  ? Center(
                child: Text(
                  'No movies available.',
                  style: TextStyle(color: Colors.white),
                ),
              )
                  : ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: _movies.length > 5 ? 5 : _movies.length,
                itemBuilder: (context, index) {
                  var movie = _movies[index];
                  return Padding(
                    padding: const EdgeInsets.only(right: 16.0),
                    child: Card(
                      color: Colors.grey[850],
                      elevation: 5,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(16),
                      ),
                      child: SizedBox(
                        width: 180, // Increased card width for more space
                        height: 240, // Increased card height
                        child: Padding(
                          padding: const EdgeInsets.all(12.0),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: [
                              ClipRRect(
                                borderRadius: BorderRadius.circular(12),
                                child: movie['poster_path'] != null
                                    ? Image.network(
                                  'https://image.tmdb.org/t/p/w500${movie['poster_path']}',
                                  width: 120,
                                  height: 180,
                                  fit: BoxFit.cover,
                                )
                                    : Image.asset(
                                  'assets/images/default_movie.jpg',
                                  width: 120,
                                  height: 180,
                                  fit: BoxFit.cover,
                                ),
                              ),
                              const SizedBox(height: 8),
                              Padding(
                                padding: const EdgeInsets.symmetric(horizontal: 4.0),
                                child: Text(
                                  movie['title'],
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 14,
                                  ),
                                  textAlign: TextAlign.center,
                                  maxLines: 1,
                                  overflow: TextOverflow.ellipsis,
                                ),
                              ),
                              Padding(
                                padding: const EdgeInsets.symmetric(horizontal: 4.0),
                                child: Text(
                                  movie['character'] ?? 'Role not available',
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 12,
                                  ),
                                  textAlign: TextAlign.center,
                                  maxLines: 1,
                                  overflow: TextOverflow.ellipsis,
                                ),
                              ),
                              Padding(
                                padding: const EdgeInsets.symmetric(horizontal: 4.0),
                                child: Text(
                                  movie['release_date']?.substring(0, 4) ?? 'Year not available',
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 12,
                                  ),
                                  textAlign: TextAlign.center,
                                  maxLines: 1,
                                  overflow: TextOverflow.ellipsis,
                                ),
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
    );
  }
}
