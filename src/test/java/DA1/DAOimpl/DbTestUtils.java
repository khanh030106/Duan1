package DA1.DAOimpl;

import DA1.util.XJdbc;

import java.util.UUID;

/**
 * Helper utilities for integration tests that hit SQL Server via XJdbc.
 * Keep everything best-effort cleanup to make reruns stable.
 */
public final class DbTestUtils {
    private DbTestUtils() {}

    public static String unique(String prefix) {
        return prefix + "_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    public static Integer getUserIdByUserName(String userName) {
        return XJdbc.getValue("SELECT TOP 1 User_ID FROM Users WHERE UserName = ?", userName);
    }

    public static Integer getStudentsIdByUserId(int userId) {
        return XJdbc.getValue("SELECT TOP 1 Students_ID FROM Students WHERE User_ID = ?", userId);
    }

    public static Integer getStudentsIdByFullname(String fullname) {
        return XJdbc.getValue("SELECT TOP 1 Students_ID FROM Students WHERE Fullname = ?", fullname);
    }

    public static Integer getTeacherIdGvByUserId(int userId) {
        return XJdbc.getValue("SELECT TOP 1 ID_GV FROM Teachers WHERE User_ID = ?", userId);
    }

    public static Integer getSubjectIdByTenMon(String tenMon) {
        return XJdbc.getValue("SELECT TOP 1 MaMon FROM MonHoc WHERE TenMon = ?", tenMon);
    }

    public static Double getMarkRegular(int studentId, String subject) {
        return XJdbc.getValue("SELECT TOP 1 RegularGrade FROM Marks WHERE Student_ID = ? AND Subject = ?", studentId, subject);
    }

    public static void cleanupMarksByStudentAndSubject(int studentId, String subject) {
        try {
            XJdbc.executeUpdate("DELETE FROM Marks WHERE Student_ID = ? AND Subject = ?", studentId, subject);
        } catch (Exception ignored) {}
    }

    public static void cleanupTeachersByUserId(int userId) {
        try {
            XJdbc.executeUpdate("DELETE FROM Teachers WHERE User_ID = ?", userId);
        } catch (Exception ignored) {}
    }

    public static void cleanupStudentsByUserId(int userId) {
        try {
            XJdbc.executeUpdate("DELETE FROM Students WHERE User_ID = ?", userId);
        } catch (Exception ignored) {}
    }

    public static void cleanupUsersByUserName(String userName) {
        try {
            XJdbc.executeUpdate("DELETE FROM Users WHERE UserName = ?", userName);
        } catch (Exception ignored) {}
    }

    public static void cleanupSubjectsByTenMon(String tenMon) {
        try {
            XJdbc.executeUpdate("DELETE FROM MonHoc WHERE TenMon = ?", tenMon);
        } catch (Exception ignored) {}
    }
}

