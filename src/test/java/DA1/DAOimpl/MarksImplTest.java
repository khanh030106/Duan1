package DA1.DAOimpl;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DA1.Entity.Marks;
import DA1.Entity.SubjectTitle;
import DA1.Entity.Subjects;

public class MarksImplTest {

    private final MarksImpl dao = new MarksImpl();
    private String testSubject;
    private final int testStudentId = 1; // Assuming student with ID 1 always exists for these tests

    @BeforeMethod
    public void setUp() {
        testSubject = DbTestUtils.unique("Sub");
        // Ensure the test environment is clean before each test
        DbTestUtils.cleanupMarksByStudentAndSubject(testStudentId, testSubject);
    }

    @AfterMethod
    public void tearDown() {
        // Clean up any marks created during the test
        if (testSubject != null) {
            DbTestUtils.cleanupMarksByStudentAndSubject(testStudentId, testSubject);
        }
    }

    @Test
    public void TC26_MarksImpl_findAll_basic() {
        List<Marks> list = dao.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void TC27_MarksImpl_findAllSubjects_basic() {
        List<Subjects> list = dao.findAllSubjects();
        Assert.assertNotNull(list);
    }

    @Test
    public void TC28_MarksImpl_findAllBySubjectsTitle_valid() {
        List<Marks> list = dao.findAllBySubjectsTitle("Toan Hoc");
        Assert.assertNotNull(list);
    }

    @Test
    public void TC29_MarksImpl_update_valid() {
        // Insert a record to be updated
        dao.insert(Marks.builder().Student_ID(testStudentId).Student("Nguyen Van A").Subject(testSubject).build());

        dao.update(Marks.builder()
                .Student_ID(testStudentId)
                .Subject(testSubject)
                .RegularGrade(8.5)
                .MidtermGrade(7.0)
                .FinalExam(9.0)
                .build());

        Double regular = DbTestUtils.getMarkRegular(testStudentId, testSubject);
        Assert.assertNotNull(regular);
        Assert.assertEquals(regular, 8.5);
    }

    @Test
    public void TC30_MarksImpl_insert_valid() {
        dao.insert(Marks.builder().Student_ID(testStudentId).Student("Nguyen Van A").Subject(testSubject).build());

        // Verify existence by checking if the row can be retrieved
        Integer exists = DA1.util.XJdbc.getValue("SELECT TOP 1 1 FROM Marks WHERE Student_ID = ? AND Subject = ?", testStudentId, testSubject);
        Assert.assertNotNull(exists, "Mark record should exist after insert");
    }

    @Test
    public void TC31_MarksImpl_findMarks_partial() {
        List<Marks> list = dao.findMarks("Nguyen");
        Assert.assertNotNull(list);
    }

    @Test
    public void TC32_MarksImpl_getAllSubjectTitle_basic() {
        List<SubjectTitle> list = dao.getAllSubjectTitle();
        Assert.assertNotNull(list);
    }

    @Test
    public void TC33_MarksImpl_deleteMarks_valid() {
        // Insert a record to be deleted
        dao.insert(Marks.builder().Student_ID(testStudentId).Student("Nguyen Van A").Subject(testSubject).build());
        dao.update(Marks.builder()
                .Student_ID(testStudentId)
                .Subject(testSubject)
                .RegularGrade(8.5)
                .build());

        dao.deleteMarks(Marks.builder().Student_ID(testStudentId).Subject(testSubject).build());

        Double regular = DbTestUtils.getMarkRegular(testStudentId, testSubject);
        Assert.assertNull(regular, "Expected RegularGrade to be NULL after deleteMarks()");
    }
}

