import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:geolocator/geolocator.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/event.dart'; // Import the Event class

class MapScreen extends StatefulWidget {
  final Event event;

  MapScreen({required this.event}); // Constructor to accept Event object

  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  GoogleMapController? _mapController;
  List<LatLng> _routePoints = [];
  late LatLng _eventLocation;

  @override
  void initState() {
    super.initState();
    // Replace with dynamic location based on widget.event.location
    _eventLocation = LatLng(41.9989, 21.4316); // Example event location
    _fetchRoute();
  }

  Future<void> _fetchRoute() async {
    // Get the current location of the user
    Position position = await Geolocator.getCurrentPosition(
      desiredAccuracy: LocationAccuracy.high,
    );

    final origin = '${position.latitude},${position.longitude}';
    final destination = '${_eventLocation.latitude},${_eventLocation.longitude}';
    final apiKey = 'YOUR_GOOGLE_API_KEY';

    // Build the URI for the Google Directions API
    final Uri uri = Uri.https('maps.googleapis.com', '/maps/api/directions/json', {
      'origin': origin,
      'destination': destination,
      'key': apiKey,
    });

    // Send the HTTP request
    final response = await http.get(uri);

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      if (data['routes'].isNotEmpty) {
        final points = data['routes'][0]['overview_polyline']['points'];
        setState(() {
          _routePoints = _decodePolyline(points); // Decode the polyline points
        });
      }
    } else {
      // Handle API error
      print('Failed to fetch route: ${response.body}');
    }
  }

  // Decode the polyline string to a list of LatLng
  List<LatLng> _decodePolyline(String polyline) {
    List<LatLng> result = [];
    int index = 0, len = polyline.length;
    int lat = 0, lng = 0;

    while (index < len) {
      int shift = 0, resultValue = 0;
      int b;
      do {
        b = polyline.codeUnitAt(index++) - 63;
        resultValue |= (b & 0x1F) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlat = ((resultValue & 1) != 0 ? ~(resultValue >> 1) : (resultValue >> 1));
      lat += dlat;

      shift = 0;
      resultValue = 0;
      do {
        b = polyline.codeUnitAt(index++) - 63;
        resultValue |= (b & 0x1F) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlng = ((resultValue & 1) != 0 ? ~(resultValue >> 1) : (resultValue >> 1));
      lng += dlng;

      result.add(LatLng(lat / 1E5, lng / 1E5));
    }

    return result;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.event.title), // Display the event title
      ),
      body: GoogleMap(
        initialCameraPosition: CameraPosition(
          target: _eventLocation,
          zoom: 14,
        ),
        onMapCreated: (controller) {
          _mapController = controller;
        },
        polylines: {
          if (_routePoints.isNotEmpty)
            Polyline(
              polylineId: PolylineId('route'),
              points: _routePoints,
              color: Colors.blue,
              width: 5,
            ),
        },
        markers: {
          Marker(
            markerId: MarkerId('event'),
            position: _eventLocation,
          ),
        },
      ),
    );
  }
}
