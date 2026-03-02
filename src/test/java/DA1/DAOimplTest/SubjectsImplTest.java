package DA1.DAOimplTest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DA1.Entity.Subjects;

public class SubjectsImplTest {

    private final SubjectsImpl dao = new SubjectsImpl();
    private String testTenMon;

    @BeforeMethod
    public void setUp() {
        testTenMon = DbTestUtils.unique("MonHoc");
        DbTestUtils.cleanupSubjectsByTenMon(testTenMon);
    }

    @AfterMethod
    public void tearDown() {
        if (testTenMon != null) {
            DbTestUtils.cleanupSubjectsByTenMon(testTenMon);
        }
    }

    @Test
    public void TC15_SubjectsImpl_findAll_basic() {
        List<Subjects> list = dao.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void TC16_SubjectsImpl_create_valid() {
        dao.create(Subjects.builder().TenMon(testTenMon).TinChi(3).build());

        Integer maMon = DbTestUtils.getSubjectIdByTenMon(testTenMon);
        Assert.assertNotNull(maMon);
    }

    @Test
    public void TC17_SubjectsImpl_update_valid() {
        String tenMonNew = testTenMon + "_NEW";
        DbTestUtils.cleanupSubjectsByTenMon(tenMonNew); // Clean up the new name as well

        dao.create(Subjects.builder().TenMon(testTenMon).TinChi(3).build());
        Integer maMon = DbTestUtils.getSubjectIdByTenMon(testTenMon);
        Assert.assertNotNull(maMon);

        dao.update(Subjects.builder().MaMon(maMon).TenMon(tenMonNew).TinChi(4).build());

        Integer after = DbTestUtils.getSubjectIdByTenMon(tenMonNew);
        Assert.assertNotNull(after);

        // Cleanup for the new name, as teardown only handles the original testTenMon
        DbTestUtils.cleanupSubjectsByTenMon(tenMonNew);
    }

    @Test
    public void TC18_SubjectsImpl_delete_valid() {
        dao.create(Subjects.builder().TenMon(testTenMon).TinChi(3).build());
        Integer maMon = DbTestUtils.getSubjectIdByTenMon(testTenMon);
        Assert.assertNotNull(maMon);

        dao.delete(maMon);

        Integer after = DbTestUtils.getSubjectIdByTenMon(testTenMon);
        Assert.assertNull(after);
    }

    @Test
    public void TC19_SubjectsImpl_findByName_partial() {
        List<Subjects> list = dao.findByName("Toan");
        Assert.assertNotNull(list);
    }
}

