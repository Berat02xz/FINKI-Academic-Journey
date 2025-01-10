import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginService {
  /// Logs in the user as a guest
  Future<String> loginAsGuest() async {
    try {
      // Sign in anonymously
      UserCredential userCredential = await FirebaseAuth.instance.signInAnonymously();
      User? user = userCredential.user;

      if (user == null) {
        throw Exception("Anonymous user login failed. User is null.");
      }

      String userId = user.uid;
      print("Guest user signed in with UID: $userId");

      // Create a Firestore document for the guest user
      await FirebaseFirestore.instance.collection('users').doc(userId).set({
        'userId': userId,
        'guest': true,
        'createdAt': Timestamp.now(),
      });
      print("Firestore document created for user: $userId");

      // Store userId in SharedPreferences
      await storeUserId(userId);
      print("UserId stored in SharedPreferences: $userId");

      return userId; // Return userId
    } catch (e) {
      print("Error logging in as guest: $e");
      throw Exception("Failed to log in as guest");
    }
  }

  /// Stores the userId in SharedPreferences
  Future<void> storeUserId(String userId) async {
    try {
      SharedPreferences prefs = await SharedPreferences.getInstance();
      bool result = await prefs.setString('userId', userId);
      if (!result) {
        throw Exception("Failed to store userId in SharedPreferences.");
      }
      print("Successfully stored userId: $userId");
    } catch (e) {
      print("Error storing userId: $e");
    }
  }

  /// Retrieves the userId from SharedPreferences
  Future<String?> getUserId() async {
    try {
      SharedPreferences prefs = await SharedPreferences.getInstance();
      String? userId = prefs.getString('userId');
      print("Retrieved userId from SharedPreferences: $userId");
      return userId;
    } catch (e) {
      print("Error retrieving userId: $e");
      return null;
    }
  }
}
