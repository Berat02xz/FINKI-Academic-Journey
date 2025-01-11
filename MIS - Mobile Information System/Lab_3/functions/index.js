const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

const messaging = admin.messaging();

// This function sends a push notification at 12:00 PM every day
exports.scheduledNotification = functions.pubsub.schedule('every day 12:00').timeZone('America/New_York').onRun(async (context) => {
  console.log('Sending daily notification...');

  const message = {
    notification: {
      title: 'New Joke Dropped',
      body: 'Check out the new joke for today!',
    },
    topic: 'daily-jokes',  // The topic users will subscribe to
  };

  try {
    await messaging.send(message);
    console.log('Notification sent!');
  } catch (error) {
    console.error('Error sending notification:', error);
  }
});
