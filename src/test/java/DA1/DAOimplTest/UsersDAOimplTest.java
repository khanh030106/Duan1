package DA1.DAOimplTest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DA1.Entity.Users;

public class UsersDAOimplTest {

    private final UsersDAOimpl dao = new UsersDAOimpl();
    private String testUserName;

    @BeforeMethod
    public void setUp() {
        // Create a unique username for the upcoming test
        testUserName = DbTestUtils.unique("test_user");
        // Ensure it's clean before the test runs
        DbTestUtils.cleanupUsersByUserName(testUserName);
    }

    @AfterMethod
    public void tearDown() {
        // Clean up the user created during the test
        if (testUserName != null) {
            DbTestUtils.cleanupUsersByUserName(testUserName);
        }
    }

    @Test
    public void TC01_UsersDAOimpl_create_valid() {
        Users u = Users.builder()
                .UserName(testUserName)
                .Fullname("Nguyen Van A")
                .PassWord("123456")
                .Photo("avatar.jpg")
                .Role(1)
                .IsActive(true)
                .build();

        dao.create(u);

        Users found = dao.findById(testUserName);
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getUserName(), testUserName);
    }

    @Test
    public void TC02_UsersDAOimpl_update_valid() {
        Users u = Users.builder()
                .UserName(testUserName)
                .Fullname("Before Update")
                .PassWord("123456")
                .Photo("avatar.jpg")
                .Role(1)
                .IsActive(true)
                .build();
        dao.create(u);

        Users found = dao.findById(testUserName);
        Assert.assertNotNull(found);
        found.setFullname("Updated Name");
        found.setRole(2);
        found.setIsActive(false);
        dao.update(found);

        Users after = dao.findById(testUserName);
        Assert.assertNotNull(after);
        Assert.assertEquals(after.getFullname(), "Updated Name");
        Assert.assertEquals(after.getRole(), 2);
        Assert.assertFalse(after.isIsActive());
    }

    @Test
    public void TC03_UsersDAOimpl_deleteById_valid() {
        dao.create(Users.builder()
                .UserName(testUserName)
                .Fullname("Temp")
                .PassWord("123456")
                .Photo("avatar.jpg")
                .Role(1)
                .IsActive(true)
                .build());

        Integer userId = DbTestUtils.getUserIdByUserName(testUserName);
        Assert.assertNotNull(userId);

        dao.deleteById(String.valueOf(userId));
        Users after = dao.findById(testUserName);
        Assert.assertNull(after);
    }

    @Test
    public void TC04_UsersDAOimpl_deleteByIdInt_valid() {
        dao.create(Users.builder()
                .UserName(testUserName)
                .Fullname("Temp")
                .PassWord("123456")
                .Photo("avatar.jpg")
                .Role(1)
                .IsActive(true)
                .build());

        Integer userId = DbTestUtils.getUserIdByUserName(testUserName);
        Assert.assertNotNull(userId);

        dao.deleteByIdInt(userId);
        Users after = dao.findById(testUserName);
        Assert.assertNull(after);
    }

    @Test
    public void TC05_UsersDAOimpl_findAll_basic() {
        List<Users> list = dao.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void TC06_UsersDAOimpl_findById_valid() {
        dao.create(Users.builder()
                .UserName(testUserName)
                .Fullname("Find Me")
                .PassWord("123456")
                .Photo("avatar.jpg")
                .Role(1)
                .IsActive(true)
                .build());

        Users found = dao.findById(testUserName);
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getUserName(), testUserName);
    }

    @Test
    public void TC07_UsersDAOimpl_findByName_partial() {
        List<Users> list = dao.findByName("Nguyen");
        Assert.assertNotNull(list);
    }
}

