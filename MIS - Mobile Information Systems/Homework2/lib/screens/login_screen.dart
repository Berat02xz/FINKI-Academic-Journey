import 'package:flutter/material.dart';
import 'package:homework2/services/login_service.dart';  // Import the login_service.dart
import 'main_screen.dart';  // Import MainScreen to navigate after login

class LoginScreen extends StatelessWidget {
  final LoginService loginService = LoginService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Login Screen')),
      body: Center(
        child: ElevatedButton(
          onPressed: () async {
            try {
              // Log in as guest and get the userId
              String userId = await loginService.loginAsGuest();

              // After guest login, navigate to the MainScreen
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(
                  builder: (context) => MainScreen(userId: userId),
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
          child: const Text('Login as Guest'),
        ),
      ),
    );
  }
}
