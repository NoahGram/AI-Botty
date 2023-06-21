import com.example.aiassistent.User;
import com.example.aiassistent.UserAccountSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserAccountSingletonTest {
    private final UserAccountSingleton userAccount = new UserAccountSingleton();

    @Before
    public void setUp() {
        User testuser = new User("testuser", "testpass", "tesmail@test.nl", true);
        userAccount.addUser(testuser);
        userAccount.setCurrentUser(testuser);
    }

    @Test
    public void testIfUserExists() {
        Assert.assertTrue(userAccount.UserExists("testuser"));
        Assert.assertFalse(userAccount.UserExists("john"));
    }

    @Test
    public void testAddUser() {
        boolean accountMadeTrue = userAccount.addUser(new User("john", "password4", "john@outlook.com", true));
        Assert.assertTrue(accountMadeTrue);

        boolean accountMadeFalse = userAccount.addUser(new User("testuser", "password5", "testuser2@outlook.com", true));
        Assert.assertFalse(accountMadeFalse);
    }

    @Test
    public void testUserPasswordCorrect() {
        Assert.assertTrue(userAccount.UserPasswordCorrect("testuser", "testpass"));
        Assert.assertFalse(userAccount.UserPasswordCorrect("testuser", "wrongpassword"));
    }

    @Test
    public void testEditUsername() {
        User originalUser = userAccount.getUser("testuser");
        String originalUsername = originalUser.getUsername();

        userAccount.setCurrentUser(originalUser);
        userAccount.editUsername("testusernew");

        Assert.assertNull(userAccount.getUser(originalUsername));
        Assert.assertNotNull(userAccount.getUser("testusernew"));
        Assert.assertEquals("testusernew", userAccount.getCurrentUser().getUsername());
    }

    @Test
    public void testEditEmail() {
        userAccount.editEmail("testuser", "newemail@outlook.com");

        Assert.assertEquals("newemail@outlook.com", userAccount.getUser("testuser").getEmail());
        Assert.assertEquals("newemail@outlook.com", userAccount.getCurrentUser().getEmail());

        userAccount.editEmail("john", "newemail2@outlook.com");
        Assert.assertNull(userAccount.getUser("john"));
    }

    @Test
    public void testEditPassword() {
        userAccount.editPassword("testuser", "newpassword");

        Assert.assertEquals("newpassword", userAccount.getUser("testuser").getPassword());
        Assert.assertEquals("newpassword", userAccount.getCurrentUser().getPassword());

        userAccount.editPassword("john", "newpassword2");
        Assert.assertNull(userAccount.getUser("john"));
    }

    @Test
    public void testUserAdmin() {
        Assert.assertTrue(userAccount.getCurrentUser().getAdmin());
        userAccount.getCurrentUser().setAdmin(false);
        Assert.assertFalse(userAccount.getCurrentUser().getAdmin());
    }
}
