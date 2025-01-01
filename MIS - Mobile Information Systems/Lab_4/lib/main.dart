import 'package:flutter/material.dart';
import 'screens/calendar_screen.dart'; // Import the CalendarScreen

void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false, // Optional: Remove the debug banner
    home: CalendarScreen(), // Set CalendarScreen as the home screen
  ));
}
