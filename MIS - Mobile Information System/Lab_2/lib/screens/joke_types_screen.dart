import 'package:flutter/material.dart';
import '../services/api_services.dart';
import 'jokes_by_category_screen.dart'; // New screen to show jokes by category
import 'dart:math'; // To generate random colors

class JokeTypesScreen extends StatefulWidget {
  const JokeTypesScreen({Key? key}) : super(key: key);

  @override
  _JokeTypesScreenState createState() => _JokeTypesScreenState();
}

class _JokeTypesScreenState extends State<JokeTypesScreen> {
  late Future<List<String>> _jokeTypes;

  @override
  void initState() {
    super.initState();
    _jokeTypes = ApiServices.fetchJokeTypes(); // Fetch joke types
  }

  // Function to generate random colors
  Color _getRandomColor() {
    final random = Random();
    return Color.fromARGB(
      255,
      random.nextInt(256), // Red
      random.nextInt(256), // Green
      random.nextInt(256), // Blue
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      body: FutureBuilder<List<String>>(
        future: _jokeTypes,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('No joke categories available.'));
          } else {
            final jokeTypes = snapshot.data!;
            return Container(
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  colors: [Colors.blueAccent, Colors.blueAccent], // Background gradient
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                ),
              ),
              child: GridView.builder(
                padding: const EdgeInsets.all(16.0),
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2, // Two cards per row
                  crossAxisSpacing: 16.0, // Horizontal spacing
                  mainAxisSpacing: 16.0, // Vertical spacing
                  childAspectRatio: 1.5, // Adjust size of cards
                ),
                itemCount: jokeTypes.length,
                itemBuilder: (context, index) {
                  return Card(
                    color: _getRandomColor(), // Random background color
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(12),
                    ),
                    elevation: 5,
                    child: InkWell(
                      onTap: () {
                        // Navigate to screen with jokes of this type
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => JokesByCategoryScreen(type: jokeTypes[index]),
                          ),
                        );
                      },
                      child: Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Center(
                          child: Text(
                            jokeTypes[index].toUpperCase(),
                            textAlign: TextAlign.center,
                            style: const TextStyle(
                              color: Colors.white,
                              fontSize: 18,
                              fontWeight: FontWeight.bold,

                            ),
                          ),
                        ),
                      ),
                    ),
                  );
                },
              ),
            );
          }
        },
      ),
    );
  }
}
