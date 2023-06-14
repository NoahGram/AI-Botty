import org.junit.jupiter.api.Test;
import com.example.aiassistent.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class UserAccountSingletonTest {
    private UserAccountSingleton userAccountSingleton;

    @BeforeEach
    void setUp() {
        userAccountSingleton = UserAccountSingleton.getInstance();
        userAccountSingleton.setCurrentUser(new User("noah", "password1", "noah@outlook.com"));
    }

    @Test
    void testUserExists() {
        assertFalse(userAccountSingleton.UserExists("noah"));
        assertTrue(userAccountSingleton.UserExists("john"));
    }

    @Test
    void testAddUser() {
        User newUser = new User("john", "password4", "john@outlook.com");
        userAccountSingleton.addUser(newUser);
        assertTrue(userAccountSingleton.UserExists("john"));

        User retrievedUser = userAccountSingleton.getUser("john");
        assertNotNull(retrievedUser);
        assertEquals(newUser.getUsername(), retrievedUser.getUsername());
        assertEquals(newUser.getEmail(), retrievedUser.getEmail());
    }

    @Test
    void testUserPasswordCorrect() {
        assertFalse(userAccountSingleton.UserPasswordCorrect("noah", "password1"));
        assertFalse(userAccountSingleton.UserPasswordCorrect("noah", "incorrectPassword"));
        assertFalse(userAccountSingleton.UserPasswordCorrect("nonexistentUser", "password"));
    }

    @Test
    void testEditUsername() {
        User currentUser = userAccountSingleton.getUser("noah");
        userAccountSingleton.editUsername("newUsername");
        assertNull(userAccountSingleton.getUser(currentUser.getUsername()));
        assertNotNull(userAccountSingleton.getUser("newUsername"));
        assertEquals("newUsername", userAccountSingleton.getCurrentUser().getUsername());
    }

    @Test
    void testEditEmail() {
        String username = "noah";
        String newEmail = "noah@noah.com";
        userAccountSingleton.editEmail(username, newEmail);
        User user = userAccountSingleton.getUser(username);
        assertNotNull(user);
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void testEditPassword() {
        String username = "newUsername";
        String newPassword = "password2";
        userAccountSingleton.editPassword(username, newPassword);
        User user = userAccountSingleton.getUser(username);
        assertNotNull(user);
        assertEquals(newPassword, user.getPassword());
    }
}
