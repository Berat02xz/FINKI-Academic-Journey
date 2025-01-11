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
      'name': 'Aelfric Eden',
      'image': 'https://i.pinimg.com/736x/ef/f1/c0/eff1c0e489ed7d48274ce2d0c0ca7e49.jpg',
      'description': 'A comfortable cotton piece of art.',
      'price': '\$115',
    },
    {
      'name': 'Double O - Jacket',
      'image': 'https://i.pinimg.com/736x/e1/96/0f/e1960f5b98f6dee433e0da7a51f3c5c0.jpg',
      'description': 'Stylish art.',
      'price': '\$400',
    },
    {
      'name': 'Green Apple Jacket',
      'image': 'https://i.pinimg.com/736x/11/54/41/115441dc46a13e0eb7cc097d20017111.jpg',
      'description': 'A warm and cozy jacket.',
      'price': '\$160',
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
