package DA1.DAOimpl;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DA1.Entity.Teachers;
import DA1.Entity.Users;

public class TeachersImplTest {

    private final TeachersImpl teachersDao = new TeachersImpl();
    private final UsersDAOimpl usersDao = new UsersDAOimpl();
    private String testUserName;
    private Integer testUserId;

    @BeforeMethod
    public void setUp() {
        testUserName = DbTestUtils.unique("teacher_user");
        DbTestUtils.cleanupUsersByUserName(testUserName);

        usersDao.create(Users.builder()
                .UserName(testUserName)
                .Fullname("Teacher Test")
                .PassWord("123456")
                .Photo("teacher.jpg")
                .Role(1) // Role for Teacher
                .IsActive(true)
                .build());
        testUserId = DbTestUtils.getUserIdByUserName(testUserName);
        Assert.assertNotNull(testUserId, "Prerequisite User must be created for teacher test");
    }

    @AfterMethod
    public void tearDown() {
        if (testUserId != null) {
            DbTestUtils.cleanupTeachersByUserId(testUserId);
        }
        if (testUserName != null) {
            DbTestUtils.cleanupUsersByUserName(testUserName);
        }
    }

    @Test
    public void TC20_TeachersImpl_create_valid() {
        teachersDao.create(Teachers.builder()
                .User_ID(testUserId)
                .Name("Tran Van C")
                .Photo("teacher.jpg")
                .Birthdate(new Date())
                .Gmail("t@test.com")
                .Subjects("Toan")
                .build());

        Integer idGv = DbTestUtils.getTeacherIdGvByUserId(testUserId);
        Assert.assertNotNull(idGv);
    }

    @Test
    public void TC21_TeachersImpl_update_valid() {
        teachersDao.create(Teachers.builder()
                .User_ID(testUserId)
                .Name("Teacher Upd")
                .Photo("teacher.jpg")
                .Birthdate(new Date())
                .Gmail("old@test.com")
                .Subjects("Toan")
                .build());

        teachersDao.update(Teachers.builder()
                .User_ID(testUserId)
                .Name("Teacher Updated")
                .Photo("teacher.jpg")
                .Birthdate(new Date())
                .Gmail("new@test.com")
                .Subjects("Ly")
                .build());

        List<Teachers> list = teachersDao.findByName("Teacher Updated");
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty(), "Teacher should be found by new name after update");
    }

    @Test
    public void TC22_TeachersImpl_deleteById_valid() {
        teachersDao.create(Teachers.builder()
                .User_ID(testUserId)
                .Name("Teacher Del")
                .Photo("teacher.jpg")
                .Birthdate(new Date())
                .Gmail("del@test.com")
                .Subjects("Toan")
                .build());

        Integer idGv = DbTestUtils.getTeacherIdGvByUserId(testUserId);
        Assert.assertNotNull(idGv);

        teachersDao.deleteById(String.valueOf(idGv));

        Integer still = DbTestUtils.getTeacherIdGvByUserId(testUserId);
        Assert.assertNull(still, "Teacher record should be null after deletion");
    }

    @Test
    public void TC23_TeachersImpl_findAll_basic() {
        List<Teachers> list = teachersDao.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void TC24_TeachersImpl_findByName_partial() {
        List<Teachers> list = teachersDao.findByName("Tran");
        Assert.assertNotNull(list);
    }

    @Test
    public void TC25_TeachersImpl_fillToTeacher_basic() {
        List<Teachers> list = teachersDao.fillToTeacher();
        Assert.assertNotNull(list);
    }
}

