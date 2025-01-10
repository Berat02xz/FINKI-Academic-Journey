import 'dart:convert';
import 'package:http/http.dart' as http;

class TMDbService {
  static const String apiKey = '06e568602a9b8e0745998806511e1b16';
  static const String baseUrl = 'https://api.themoviedb.org/3';

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
}
