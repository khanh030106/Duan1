/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

import DA1.DAOimplTest.UsersDAOimpl;
import DA1.Entity.Students;
import DA1.Entity.Users;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class UploadExcelData {

    UsersDAOimpl dao = new UsersDAOimpl();

   private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return XDate.format(cell.getDateCellValue(), XDate.SHORT_PATERN);
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    public void uploadDataToDB() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file");
        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File file = fileChooser.getSelectedFile();

        try {
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                Users user = new Users();
                user.setUserName(getCellValue(row.getCell(0)));
                user.setFullname(getCellValue(row.getCell(1)));
                user.setPassWord(getCellValue(row.getCell(2)));
                user.setPhoto(getCellValue(row.getCell(3)));
                user.setRole(Integer.parseInt(getCellValue(row.getCell(4))));
                user.setIsActive(Boolean.parseBoolean(getCellValue(row.getCell(5))));
                
                Students std = new Students();
                
                dao.create(user);
            }
            JOptionPane.showMessageDialog(null, "Thành công");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Lỗi");
        }
    }
}
