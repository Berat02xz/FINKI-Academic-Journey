import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class SILab2Test {

    @Test
    // Unit test for Multiple Condition and Every Branch criteria
    public void testFunction() {
        // Multiple Condition test cases
        // Test case 1: user is null
        Assertions.assertThrows(RuntimeException.class, () -> SILab2.function(null, new ArrayList<>()));

        // Test case 2: user password is null
        Assertions.assertThrows(RuntimeException.class, () -> SILab2.function(new User("username", null, "email"), new ArrayList<>()));

        // Test case 3: user email is null
        Assertions.assertThrows(RuntimeException.class, () -> SILab2.function(new User("username", "password", null), new ArrayList<>()));

        // Every Branch test cases
        List<User> allUsers = new ArrayList<>();

        // Test case 4: user with null username, setting username to email
        User user1 = new User(null, "password", "email@domain.com");
        SILab2.function(user1, allUsers);
        Assertions.assertEquals("email@domain.com", user1.getUsername());

        // Test case 5: existing user with the same email
        User existingUser1 = new User("existingUser", "password", "existing@domain.com");
        allUsers.add(existingUser1);
        User user2 = new User("username", "password", "existing@domain.com");
        boolean result1 = SILab2.function(user2, allUsers);
        Assertions.assertFalse(result1);

        // Test case 6: existing user with the same username
        User existingUser2 = new User("username", "password", "existing2@domain.com");
        allUsers.add(existingUser2);
        User user3 = new User("username", "password", "email@domain.com");
        boolean result2 = SILab2.function(user3, allUsers);
        Assertions.assertFalse(result2);

        // Test case 7: password contains username and length < 8
        User user4 = new User("username", "password", "email@domain.com");
        boolean result3 = SILab2.function(user4, allUsers);
        Assertions.assertFalse(result3);

        // Test case 8: password contains special characters and same is 0
        User user5 = new User("username", "pa$$word", "email@domain.com");
        boolean result4 = SILab2.function(user5, allUsers);
        Assertions.assertTrue(result4);

        // Test case 9: password contains special characters and same is not 0
        User existingUser3 = new User("existingUser", "password", "existing3@domain.com");
        allUsers.add(existingUser3);
        User user6 = new User("username", "pa$$word", "email@domain.com");
        boolean result5 = SILab2.function(user6, allUsers);
        Assertions.assertFalse(result5);

        // Test case 10: password does not contain special characters
        User user7 = new User("username", "password", "email@domain.com");
        boolean result6 = SILab2.function(user7, allUsers);
        Assertions.assertFalse(result6);

        // Test case 11: password contains special characters, same is 0
        User user8 = new User("username", "pa$$word", "email@domain.com");
        boolean result7 = SILab2.function(user8, new ArrayList<>());
        Assertions.assertFalse(result7);

        // Test case 12: password contains special characters, same is 0
        User user9 = new User("username", "pa$$word", "email@domain.com");
        boolean result8 = SILab2.function(user9, allUsers);
        Assertions.assertTrue(result8);

    }



}
