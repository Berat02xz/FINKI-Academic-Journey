import 'dart:convert';
import 'package:http/http.dart' as http;

class TMDbService {
  static const String apiKey = '06e568602a9b8e0745998806511e1b16';
  static const String baseUrl = 'https://api.themoviedb.org/3';

  // Original fetchRandomMovie function
  static Future<Map<String, dynamic>> fetchRandomMovie() async {
    final url = Uri.parse('$baseUrl/movie/popular?api_key=$apiKey');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      final movies = data['results'] as List;
      movies.shuffle(); // Randomize the movie list
      return movies.first;
    } else {
      throw Exception('Failed to fetch movies: ${response.reasonPhrase}');
    }
  }

// Fetch actor's biography and movie list
  static Future<Map<String, dynamic>> fetchActorDetails(int actorId) async {
    final response = await http.get(Uri.parse('$baseUrl/person/$actorId?api_key=$apiKey&language=en-US'));

    if (response.statusCode == 200) {
      final actorData = json.decode(response.body);

      // Fetch the actor's movie credits
      final creditsResponse = await http.get(Uri.parse('$baseUrl/person/$actorId/movie_credits?api_key=$apiKey&language=en-US'));
      if (creditsResponse.statusCode == 200) {
        final creditsData = json.decode(creditsResponse.body);
        return {
          'bio': actorData['biography'] ?? '',
          'movies': creditsData['cast'] ?? [],
        };
      } else {
        throw Exception('Failed to fetch movie credits');
      }
    } else {
      throw Exception('Failed to fetch actor details');
    }
  }

  static Future<List<dynamic>> fetchMovieCast(int movieId) async {
    final url = Uri.parse('$baseUrl/movie/$movieId/credits?api_key=$apiKey');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      return data['cast'] as List; // Extract the cast from the response
    } else {
      throw Exception('Failed to fetch movie cast: ${response.reasonPhrase}');
    }
  }

  // New function to fetch a random movie for the login screen
  static Future<Map<String, dynamic>> fetchRandomMovieForLogin() async {
    final url = Uri.parse('$baseUrl/movie/popular?api_key=$apiKey');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      final movies = data['results'] as List;

      // Ensure the list is not empty
      if (movies.isEmpty) {
        throw Exception('No movies found.');
      }

      // Shuffle the list and pick a random movie
      movies.shuffle();

      final randomMovie = movies.first;

      // Extract title, release date, and backdrop image
      final title = randomMovie['title'] ?? 'Unknown';
      final releaseDate = randomMovie['release_date'] ?? '';
      final year = releaseDate.isNotEmpty ? releaseDate.substring(0, 4) : 'Unknown';
      final backdropPath = randomMovie['backdrop_path'] ?? '';
      final backdropUrl = backdropPath.isNotEmpty
          ? 'https://image.tmdb.org/t/p/w780$backdropPath'
          : '';

      // Return the movie info (title with year and backdrop)
      return {
        'title': '$title - $year',  // Movie title and year
        'backdropUrl': backdropUrl, // Movie backdrop image URL
      };
    } else {
      throw Exception('Failed to fetch movie: ${response.reasonPhrase}');
    }
  }
}
