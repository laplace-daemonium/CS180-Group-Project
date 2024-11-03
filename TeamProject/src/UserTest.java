import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for User class.
 * 
 * @author L04 - Team 06 
 * @version November, 2024
 */

class UserTest {

    @Test
    void getIdTest() {
        User user = new User();
        Assertions.assertEquals(0, user.getId());
    }

    @Test
    void getUsernameTest() {
        User user = new User("random", "randomPass");
        Assertions.assertEquals("random", user.getUsername());
    }

    @Test
    void getPasswordTest() {
        User user = new User("random", "randomPass");
        Assertions.assertEquals("randomPass", user.getPassword());
    }

    @Test
    void setUsernameTest() {
        User user = new User("random", "randomPass");
        user.setUsername("newUsername");
        Assertions.assertEquals("newUsername", user.getUsername());
    }

    @Test
    void setPasswordTest() {
        User user = new User("random", "randomPass");
        user.setPassword("newPassword");
        Assertions.assertEquals("newPassword", user.getPassword());
    }

}