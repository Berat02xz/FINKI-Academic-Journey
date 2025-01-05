import 'package:awesome_notifications/awesome_notifications.dart';

class NotificationController {
  @pragma("vm:entry-point")
  static Future<void> onNotificationCreateMethod(
      ReceivedNotification receivedNotification) async {}

  @pragma("vm:entry-point")
  static Future<void> onNotificationDisplayed(
      ReceivedNotification receivedNotification) async {}

  @pragma("vm:entry-point")
  static Future<void> onDismissActionReceiveMethod(
      ReceivedNotification receivedNotification) async {}

  @pragma("vm:entry-point")
  static Future<void> onActionReceiveMethod(
      ReceivedNotification receivedNotification) async {}
}
