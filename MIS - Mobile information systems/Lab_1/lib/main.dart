import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '216130',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepOrange),
        useMaterial3: true,
      ),
      home: const ClothingListScreen(),
    );
  }
}

class ClothingListScreen extends StatelessWidget {
  const ClothingListScreen({super.key});

  // Change final to const here and make the list of clothing items constant
  static const List<Map<String, String>> clothes = [
    {
      'name': 'T-Shirt',
      'image': 'https://via.placeholder.com/150',
      'description': 'A comfortable cotton t-shirt.',
      'price': '\$15',
    },
    {
      'name': 'Jeans',
      'image': 'https://via.placeholder.com/150',
      'description': 'Stylish blue jeans.',
      'price': '\$40',
    },
    {
      'name': 'Jacket',
      'image': 'https://via.placeholder.com/150',
      'description': 'A warm and cozy jacket.',
      'price': '\$60',
    },
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('216130'), // Your index number here
      ),
      body: ListView.builder(
        itemCount: clothes.length,
        itemBuilder: (context, index) {
          return Card(
            margin: const EdgeInsets.all(8.0),
            child: ListTile(
              leading: Image.network(clothes[index]['image']!),
              title: Text(clothes[index]['name']!),
              subtitle: Text(clothes[index]['price']!),
              onTap: () {
                // Navigate to the details screen when an item is tapped
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => ClothingDetailScreen(
                      name: clothes[index]['name']!,
                      image: clothes[index]['image']!,
                      description: clothes[index]['description']!,
                      price: clothes[index]['price']!,
                    ),
                  ),
                );
              },
            ),
          );
        },
      ),
    );
  }
}

class ClothingDetailScreen extends StatelessWidget {
  final String name;
  final String image;
  final String description;
  final String price;

  const ClothingDetailScreen({
    required this.name,
    required this.image,
    required this.description,
    required this.price,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(name),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Center(child: Image.network(image, height: 200)),
            const SizedBox(height: 16),
            Text(
              name,
              style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Text(description),
            const SizedBox(height: 16),
            Text(
              'Price: $price',
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
          ],
        ),
      ),
    );
  }
}
