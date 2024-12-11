// services/api_services.dart
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:lab_2/models/joke.dart';

class ApiServices {
  static Future<Joke> fetchJoke() async {
    final response = await http.get(
        Uri.parse('https://official-joke-api.appspot.com/random_joke'));

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      return Joke.fromJson(data);
    } else {
      throw Exception('Не може да се добие шега.');
    }
  }

  // Fetch available joke types (categories)
  static Future<List<String>> fetchJokeTypes() async {
    final response = await http.get(Uri.parse('https://official-joke-api.appspot.com/types'));

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return List<String>.from(data); // Return a list of joke types
    } else {
      throw Exception('Failed to load joke types');
    }
  }

  // Fetch jokes by type
  static Future<List<Joke>> fetchJokesByType(String type) async {
    final response = await http.get(
        Uri.parse('https://official-joke-api.appspot.com/jokes/$type/ten'));

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return data.map((jokeJson) => Joke.fromJson(jokeJson)).toList();
    } else {
      throw Exception('Failed to load jokes');
    }
  }
}