import 'package:flutter/material.dart';
import 'package:palette_generator/palette_generator.dart';
import 'package:homework2/services/login_service.dart'; // Import the login_service.dart
import 'package:homework2/services/tmdb_service.dart'; // Import TMDbService
import 'main_screen.dart'; // Import MainScreen to navigate after login

class LoginScreen extends StatefulWidget {
  final LoginService loginService = LoginService();

  LoginScreen({Key? key}) : super(key: key);

  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  Color _backgroundColor = Colors.black; // Default to solid black
  Color _buttonColor = Colors.black;
  String _movieBackdropUrl = ''; // Default to empty string
  String _movieTitle = ''; // Default to empty string

  @override
  void initState() {
    super.initState();
    _updateColorsFromImage();
    _fetchMovieData(); // Fetch random movie data
  }

  Future<void> _updateColorsFromImage() async {
    final PaletteGenerator paletteGenerator =
    await PaletteGenerator.fromImageProvider(
      const NetworkImage(
          'https://w0.peakpx.com/wallpaper/594/350/HD-wallpaper-the-lighthouse-2-art-cinematography.jpg'),
    );

    setState(() {
      // Use dominant color for the background
      _backgroundColor = Colors.black;

      // Use light vibrant color for the button, fallback to dominant color
      _buttonColor = Colors.white70;
    });
  }

  // Fetch random movie data from TMDbService
  Future<void> _fetchMovieData() async {
    try {
      final movieData = await TMDbService.fetchRandomMovieForLogin();
      setState(() {
        _movieBackdropUrl = movieData['backdropUrl'] ?? '';
        _movieTitle = movieData['title'] ?? 'Unknown Movie';
      });
    } catch (e) {
      print("Error fetching movie: $e");
      // You can display an error message or fallback content if needed
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: LayoutBuilder(
        builder: (context, constraints) {
          return Stack(
            children: [
              // Background Image (from random movie)
              if (_movieBackdropUrl.isNotEmpty) // Only show if URL is fetched
                Positioned(
                  top: 0,
                  left: 0,
                  right: 0,
                  child: Image.network(
                    _movieBackdropUrl,
                    fit: BoxFit.cover,
                    height: constraints.maxHeight * 0.52,
                    loadingBuilder: (context, child, loadingProgress) {
                      if (loadingProgress == null) return child;
                      return const Center(child: CircularProgressIndicator());
                    },
                  ),
                ),
              // Rounded Element at the bottom
              Positioned(
                top: constraints.maxHeight * 0.5,
                bottom: 0, // Ensures it always stays at the bottom
                left: 0,
                right: 0,
                child: Container(
                  padding: const EdgeInsets.symmetric(horizontal: 60, vertical: 100),
                  decoration: BoxDecoration(
                    color: _backgroundColor, // Fully opaque solid color
                    borderRadius: const BorderRadius.only(
                      topLeft: Radius.circular(20),
                      topRight: Radius.circular(20),
                    ),
                  ),
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      // Movie title and year
                      Text(
                        _movieTitle,
                        textAlign: TextAlign.center,
                        style: const TextStyle(
                          color: Colors.white,
                          fontSize: 15,
                          fontFamily: 'Andada Pro',
                          fontWeight: FontWeight.w400,
                          height: 1.4,
                        ),
                      ),
                      const SizedBox(height: 16),
                      Text(
                        "Track films you've watched. Save those you want to see. Tell your friends what's good.",
                        textAlign: TextAlign.center,
                        style: const TextStyle(
                          color: Colors.white,
                          fontSize: 20,
                          fontFamily: 'Andada Pro',
                          fontWeight: FontWeight.w700,
                          height: 1.4,
                        ),
                      ),
                      const SizedBox(height: 32),
                      Container(
                        constraints: const BoxConstraints(maxWidth: 279),
                        height: 55,
                        decoration: BoxDecoration(
                          color: _buttonColor, // Fully opaque solid color
                          borderRadius: BorderRadius.circular(100),
                        ),
                        child: MaterialButton(
                          onPressed: () async {
                            try {
                              // Log in as guest and get the userId
                              String userId =
                              await widget.loginService.loginAsGuest();

                              // Navigate to MainScreen after login
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(
                                  builder: (context) =>
                                      MainScreen(userId: userId),
                                ),
                              );
                            } catch (e) {
                              // Handle error, show a message, etc.
                              print("Error: $e");
                              ScaffoldMessenger.of(context).showSnackBar(
                                SnackBar(content: Text("Error: $e")),
                              );
                            }
                          },
                          child: const Center(
                            child: Text(
                              'Open As Guest',
                              style: TextStyle(
                                color: Colors.black,
                                fontSize: 14,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w500,
                                letterSpacing: 0.1,
                              ),
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
