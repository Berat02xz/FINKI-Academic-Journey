import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';
import '../models/event.dart';
import 'map_screen.dart';

class CalendarScreen extends StatefulWidget {
  @override
  _CalendarScreenState createState() => _CalendarScreenState();
}

class _CalendarScreenState extends State<CalendarScreen> {
  final Map<DateTime, List<Event>> events = {
    DateTime(2024, 1, 10): [Event('Математика', '10:00 AM', 'Амфитеатар 2')],
    DateTime(2024, 1, 15): [Event('Физика', '1:00 PM', 'Лабораторија 3')],
  };

  late DateTime _selectedDay;
  List<Event> _selectedEvents = [];

  @override
  void initState() {
    super.initState();
    _selectedDay = DateTime.now();
    _selectedEvents = events[_selectedDay] ?? [];
  }

  List<Event> _getEventsForDay(DateTime day) {
    return events[day] ?? [];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Календар')),
      body: Column(
        children: [
          TableCalendar(
            focusedDay: DateTime.now(),
            firstDay: DateTime(2023, 1, 1),
            lastDay: DateTime(2024, 12, 31),
            eventLoader: _getEventsForDay,
            selectedDayPredicate: (day) => isSameDay(_selectedDay, day),
            onDaySelected: (selectedDay, focusedDay) {
              setState(() {
                _selectedDay = selectedDay;
                _selectedEvents = _getEventsForDay(selectedDay);
              });
            },
          ),
          Expanded(
            child: ListView.builder(
              itemCount: _selectedEvents.length,
              itemBuilder: (context, index) {
                final event = _selectedEvents[index];
                return ListTile(
                  title: Text(event.title),
                  subtitle: Text('${event.time} - ${event.location}'),
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => MapScreen(event: event), // Pass the event object
                      ),
                    );
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
