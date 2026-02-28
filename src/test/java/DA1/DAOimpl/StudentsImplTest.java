package DA1.DAOimpl;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DA1.Entity.Students;
import DA1.Entity.Users;

public class StudentsImplTest {

    private final StudentsImpl studentsDao = new StudentsImpl();
    private final UsersDAOimpl usersDao = new UsersDAOimpl();
    private String testUserName;
    private Integer testUserId;

    @BeforeMethod
    public void setUp() {
        testUserName = DbTestUtils.unique("student_user");
        DbTestUtils.cleanupUsersByUserName(testUserName); // Cleanup before to be safe

        // Create a prerequisite User for the student
        usersDao.create(Users.builder()
                .UserName(testUserName)
                .Fullname("Student Test")
                .PassWord("123456")
                .Photo("student.jpg")
                .Role(2) // Role for Student
                .IsActive(true)
                .build());
        testUserId = DbTestUtils.getUserIdByUserName(testUserName);
        Assert.assertNotNull(testUserId, "Prerequisite User must be created for student test");
    }

    @AfterMethod
    public void tearDown() {
        // Cleanup happens in reverse order of creation: students first, then users.
        if (testUserId != null) {
            DbTestUtils.cleanupStudentsByUserId(testUserId);
        }
        if (testUserName != null) {
            DbTestUtils.cleanupUsersByUserName(testUserName);
        }
    }

    @Test
    public void TC08_StudentsImpl_create_valid() {
        // Prerequisite User is already created by setUp()
        String fullname = "Le Thi B " + DbTestUtils.unique("fn");
        Students st = Students.builder()
                .User_ID(testUserId)
                .Fullname(fullname)
                .Photo("student.jpg")
                .BirthDate(new Date())
                .Gmail("student@test.com")
                .Classes("10A1")
                .build();
        studentsDao.create(st);

        List<Students> found = studentsDao.findByName(fullname);
        Assert.assertNotNull(found);
        Assert.assertFalse(found.isEmpty(), "Student should be found after creation");
    }

    @Test
    public void TC09_StudentsImpl_update_valid() {
        // Prerequisite User is already created by setUp()
        Students st = Students.builder()
                .User_ID(testUserId)
                .Fullname("Student Upd")
                .Photo("student.jpg")
                .BirthDate(new Date())
                .Gmail("old@test.com")
                .Classes("10A1")
                .build();
        studentsDao.create(st);

        Integer studentsId = DbTestUtils.getStudentsIdByUserId(testUserId);
        Assert.assertNotNull(studentsId);

        Students upd = Students.builder()
                .Students_ID(studentsId)
                .BirthDate(new Date())
                .Gmail("new@test.com")
                .Classes("11A1")
                .build();
        studentsDao.update(upd);

        List<Students> found = studentsDao.findByName("Student Upd");
        Assert.assertNotNull(found);
        boolean ok = found.stream().anyMatch(s -> "11A1".equals(s.getClasses()) && "new@test.com".equals(s.getGmail()));
        Assert.assertTrue(ok, "Student data should be updated");
    }

    @Test
    public void TC10_StudentsImpl_findAll_basic() {
        List<Students> list = studentsDao.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void TC11_StudentsImpl_findAllStudentForSelectMark_basic() {
        List<Students> list = studentsDao.findAllStudentForSelectMark(List.of(1, 2, 3));
        Assert.assertNotNull(list);
    }

    @Test
    public void TC12_StudentsImpl_findByName_partial() {
        List<Students> list = studentsDao.findByName("Le");
        Assert.assertNotNull(list);
    }

    @Test
    public void TC13_StudentsImpl_deleteStById_valid() {
        // Prerequisite User is already created by setUp()
        Students st = Students.builder()
                .User_ID(testUserId)
                .Fullname("Student Del")
                .Photo("student.jpg")
                .BirthDate(new Date())
                .Gmail("del@test.com")
                .Classes("10A1")
                .build();
        studentsDao.create(st);

        Integer studentsId = DbTestUtils.getStudentsIdByUserId(testUserId);
        Assert.assertNotNull(studentsId);

        studentsDao.deleteStById(studentsId);
        Integer still = DbTestUtils.getStudentsIdByUserId(testUserId);
        Assert.assertNull(still, "Student record should be null after deletion");
    }

    @Test
    public void TC14_StudentsImpl_classList_valid() {
        List<Students> list = studentsDao.classList("10A1");
        Assert.assertNotNull(list);
    }
}

