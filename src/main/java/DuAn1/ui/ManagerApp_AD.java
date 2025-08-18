/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DuAn1.ui;
import DA1.DAOimpl.MarksImpl;
import DA1.DAOimpl.StudentsImpl;
import DA1.DAOimpl.SubjectsImpl;
import DA1.DAOimpl.TeachersImpl;
import DA1.DAOimpl.UsersDAOimpl;
import DA1.Entity.Marks;
import DA1.Entity.Students;
import DA1.Entity.Subjects;
import DA1.Entity.Teachers;
import DA1.Entity.Users;
import DA1.util.FormatTable;
import DA1.util.FormatList;
import DA1.util.PlaceHolder;
import DA1.util.UploadExcelData;
import DA1.util.XAuth;
import DA1.util.XDialog;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ManagerApp_AD extends javax.swing.JFrame {
    
    private Users user;
    UsersDAOimpl dao = new UsersDAOimpl();
    List<Users> items = List.of();
    TeachersImpl daoTe = new TeachersImpl();
    List<Teachers> itemsTe = List.of();
    StudentsImpl daoStd = new StudentsImpl();
    List<Students> itemsStd = List.of();
    MarksImpl daoMa = new MarksImpl();
    List<Marks> itemsMa = List.of();
    List<Subjects> itemsSu = List.of();
    SubjectsImpl daoSu = new SubjectsImpl() ;
    
    UsersManagerJDialog UMJ = new UsersManagerJDialog(this, rootPaneCheckingEnabled);
    SubjectsJDialog SJ = new SubjectsJDialog(this, rootPaneCheckingEnabled);
    TeachersJDialog TJ = new TeachersJDialog(this, true);
    StudentsJDialog SJD = new StudentsJDialog(this, rootPaneCheckingEnabled);
    ClassJDialog CJ = new ClassJDialog(this, rootPaneCheckingEnabled);
    MarksJDialog MJJ = new MarksJDialog(this, rootPaneCheckingEnabled);
    UploadExcelData ULED = new UploadExcelData();
    ClassList cl = new ClassList(this, rootPaneCheckingEnabled);

    /**
     * Creates new form ManagerApp_AD1
     */
    public ManagerApp_AD(Users user) {
        this.user = user;
        initComponents();
        open();
        listIcon();
        PlaceHolder PH3 = new PlaceHolder("Nhập mật khẩu cũ", txtOldPass);
        PlaceHolder PH4 = new PlaceHolder("Nhập mật khẩu mới", txtNewPass);
        PlaceHolder PH5 = new PlaceHolder("Xác nhận mật khẩu mới", txtConfirm);
        PlaceHolder PH6 = new PlaceHolder("Tên đăng nhập", txtUsername);
        PlaceHolder PH7 = new PlaceHolder("Nhập tên cần tìm", txtFind);
        PlaceHolder PH8 = new PlaceHolder("Nhập tên cần tìm", txtFindStudent);
        PlaceHolder PH9 = new PlaceHolder("Nhập tên cần tìm", txtFindTeacher);
        PH3.changeAlpha(0.3f);
        PH4.changeAlpha(0.3f);
        PH5.changeAlpha(0.3f);
        PH6.changeAlpha(0.3f);
        PH7.changeAlpha(0.3f);
        PH8.changeAlpha(0.3f);
        PH9.changeAlpha(0.3f);

        setLocationRelativeTo(null);
        
    }
    
    public void listIcon() {
        Map<String, Icon> iconMap = new HashMap<>();
        iconMap.put("Quản lý người dùng", new ImageIcon(FormatList.class.getResource("/images/users.png")));
        iconMap.put("Quản lý điểm", new ImageIcon(FormatList.class.getResource("/images/marks.png")));
        iconMap.put("Quản lý giáo viên", new ImageIcon(FormatList.class.getResource("/images/teachers.png")));
        iconMap.put("Xuất điểm", new ImageIcon(FormatList.class.getResource("/images/export.png")));
        iconMap.put("Quản lý học sinh", new ImageIcon(FormatList.class.getResource("/images/students.png")));
        iconMap.put("Quản lý lịch học", new ImageIcon(FormatList.class.getResource("/images/schedules.png")));
        iconMap.put("Phân công giáo viên", new ImageIcon(FormatList.class.getResource("/images/teachers.png")));
        iconMap.put("Quản lý môn học", new ImageIcon(FormatList.class.getResource("/images/subjects.png")));
        iconMap.put("Đổi mật khẩu", new ImageIcon(FormatList.class.getResource("/images/changePassword.png")));
        iconMap.put("Quản lý khoa", new ImageIcon(FormatList.class.getResource("/images/subjects.png")));
        iconMap.put("Quản lý chuyên ngành", new ImageIcon(FormatList.class.getResource("/images/subjects.png")));
        iconMap.put("Quản lý lớp học", new ImageIcon(FormatList.class.getResource("/images/Class.png")));
        managerList.setCellRenderer(new FormatList(iconMap));
    }

    public void open() {
        lblName.setText(user.getFullname());
        
        managerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = managerList.getSelectedIndex();
                    if (selectedIndex >= 0 && selectedIndex < managerTab.getTabCount()) {
                        managerTab.setSelectedIndex(selectedIndex);
                    }
                }
            }
        });
       TJ.setTargetTableTeachers(tblTeachers);
        SJD.setTargetTableTeachers(tblStudent);
        MJJ.setTargetTableMarks(tblMarks);
        SJ.setTargetTableSubjects(tblSubject);
        
        UMJ.fillToTable(tblUsers);
        SJ.fillToTable(tblSubject);
        TJ.fillToTable(tblTeachers);
        SJD.fillToTable(tblStudent);
        CJ.fillToTable(tblClass);
        MJJ.fillToTable(tblMarks);
        MJJ.fillToTable(tblExport);

        lblName.setText(user.getFullname());
        FormatTable.RendererColumn(tblUsers, 0);
        FormatTable.RendererColumn(tblUsers, 6);
        FormatTable.RendererColumn(tblUsers, 3);
        FormatTable.RendererColumn(tblUsers, 4);
        FormatTable.RendererColumn(tblUsers, 5);
        FormatTable.RendererColumn(tblSubject, 0);
        FormatTable.RendererColumn(tblSubject, 2);
        FormatTable.RendererColumn(tblTeachers, 0);
        FormatTable.RendererColumn(tblTeachers, 1);
        FormatTable.RendererColumn(tblTeachers, 3);
        FormatTable.RendererColumn(tblTeachers, 4);
        FormatTable.RendererColumn(tblStudent, 0);
        FormatTable.RendererColumn(tblStudent, 1);
        FormatTable.RendererColumn(tblStudent, 3);
        FormatTable.RendererColumn(tblStudent, 5);
        FormatTable.RendererColumn(tblStudent, 7);
        FormatTable.RendererColumn(tblClass, 0);
        FormatTable.RendererColumn(tblClass, 1);
        FormatTable.RendererColumn(tblMarks, 0);
        FormatTable.RendererColumn(tblMarks, 3);
        FormatTable.RendererColumn(tblMarks, 4);
        FormatTable.RendererColumn(tblMarks, 5);
        FormatTable.RendererColumn(tblMarks, 6);
        FormatTable.RendererColumn(tblExport, 0);
        FormatTable.RendererColumn(tblExport, 3);
        FormatTable.RendererColumn(tblExport, 4);
        FormatTable.RendererColumn(tblExport, 5);
        FormatTable.RendererColumn(tblExport, 6);
        FormatTable.RendererColumn(tblClass, 3);

        chkShowPass.addActionListener(e -> {
            if (chkShowPass.isSelected()) {
                txtOldPass.setEchoChar((char) 0);
                txtNewPass.setEchoChar((char) 0);
                txtConfirm.setEchoChar((char) 0);
            } else {
                txtOldPass.setEchoChar(('*'));
                txtNewPass.setEchoChar(('*'));
                txtConfirm.setEchoChar(('*'));
            }
        });
    }
        
    public void ChangePass() {
        String name = txtUsername.getText();
        String oldPass = new String(txtOldPass.getPassword());
        String newPass = new String(txtNewPass.getPassword());
        String confirmPass = new String(txtConfirm.getPassword());

        if (name.isEmpty() || oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            XDialog.alert("Hãy nhập đầy đủ thông tin");
            return;
        } else if (!name.equalsIgnoreCase(XAuth.user.getUserName())) {
            XDialog.alert("Sai tên đăng nhập");
        } else if (!oldPass.equalsIgnoreCase(XAuth.user.getPassWord())) {
            XDialog.alert("Sai mật khẩu đăng nhập");
        } else if (!newPass.equals(confirmPass)) { 
            XDialog.alert("Mật khẩu mới và xác nhận mật khẩu không khớp");
        } else {
            XAuth.user.setPassWord(newPass);
            dao.update(XAuth.user);
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
            txtUsername.setText("");
            txtOldPass.setText("");
            txtNewPass.setText("");
            txtConfirm.setText("");
        }

    }

     public void findUser() {
        DefaultTableModel model = (DefaultTableModel) tblUsers.getModel();
        model.setRowCount(0);
        String name = txtFind.getText().trim();
        items = dao.findByName(name);
        if (items.isEmpty()) {
            XDialog.alert("Không tìm thấy");
        } else {
            items.forEach(e -> {
                String role = switch (e.getRole()) {
                    case 0 ->
                        "Admin";
                    case 1 ->
                        "Giáo viên";
                    case 2 ->
                        "Học sinh";
                    default ->
                        "out";
                };
                String active = e.isIsActive() ? "Hoạt động" : "Tạm dừng";
                Object[] rowData = {
                    e.getUser_ID(),
                    e.getUserName(),
                    e.getFullname(),
                    e.getPassWord(),
                    e.getPhoto(),
                    role,
                    active,
                    false
                };
                model.addRow(rowData);
            });
        }
        items = dao.findAll();
    }
     
     public void findTeacher() {
        DefaultTableModel model = (DefaultTableModel) tblTeachers.getModel();
        model.setRowCount(0);
        String name = txtFindTeacher.getText().trim();
        itemsTe = daoTe.findByName(name);
        if (itemsTe.isEmpty()) {
            XDialog.alert("Không tìm thấy");
        } else {
            itemsTe.forEach(e -> {
            Object[] rowData = {
                e.getID_GV(),
                e.getUser_ID(),
                e.getName(),
                e.getPhoto(),
                e.getBirthdate(),
                e.getGmail(),
                e.getSubjects(),
                false
            };
            model.addRow(rowData);
        });
        }
        itemsTe = daoTe.findAll();
    }
     
     public void findStudent() {
        DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
        model.setRowCount(0);
        String name = txtFindStudent.getText().trim();
        itemsStd = daoStd.findByName(name);
        
        if (itemsStd.isEmpty()) {
            XDialog.alert("Không tìm thấy");
        } else {
            itemsStd.forEach(e -> {
            Object[] rowData = {
                e.getStudents_ID(),
                e.getUser_ID(),
                e.getFullname(),
                e.getPhoto(),
                e.getUserName(),
                e.getBirthDate(),
                e.getGmail(),
                e.getClasses(),
                false
            };
            model.addRow(rowData);
        });
        }
        itemsStd = daoStd.findAll();
    }
     
     public void findStMarks(JTextField txt, JTable tbl){
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String name = txt.getText().trim();
        itemsMa = daoMa.findMarks(name);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("0.0", symbols);
        if (itemsMa.isEmpty()) {
            XDialog.alert("Không tìm thấy!");
        }else{
            itemsMa.forEach(e ->{
                Double avg = (e.getRegularGrade() + e.getMidtermGrade() + e.getFinalExam())/3 ;
                Object[] rowData = {
                    e.getStudent_ID(),
                    e.getStudent(),
                    e.getSubject(),
                    e.getRegularGrade(),
                    e.getMidtermGrade(),
                    e.getFinalExam(),
                    df.format(avg),
                    false
                };
                model.addRow(rowData);
            });
            
        }
        
    }
     public void findSubject(){
        DefaultTableModel model = (DefaultTableModel) tblSubject.getModel();
        model.setRowCount(0);
        String name = txtFindSubject.getText().trim();
        itemsSu = daoSu.findByName(name);
         if (itemsSu.isEmpty()) {
             XDialog.alert("Không tìm thấy");
         }else{
             itemsSu.forEach(e ->{
                 Object[] rowData = {
                     e.getMaMon(),
                     e.getTenMon(),
                     e.getTinChi()
                 };
                 model.addRow(rowData);
             });
             
         }
    }
     private JTable targetTableClassList;
    public void setTargetTableClassList(JTable tbl) {
        this.targetTableClassList = tbl;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        managerList = new javax.swing.JList<>();
        managerTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnDelete = new javax.swing.JButton();
        btnReloadUser = new javax.swing.JButton();
        btnunCheckAll = new javax.swing.JButton();
        btncheckAll = new javax.swing.JButton();
        btnUpload = new javax.swing.JButton();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMarks = new javax.swing.JTable();
        btnDeleteMarks = new javax.swing.JButton();
        btnReloadMarks = new javax.swing.JButton();
        btnUnSelectMarks = new javax.swing.JButton();
        btnSelectMarks = new javax.swing.JButton();
        txtFindMarks = new javax.swing.JTextField();
        btnFindMarks = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTeachers = new javax.swing.JTable();
        deleteTeacher = new javax.swing.JButton();
        btnReloadTeachers = new javax.swing.JButton();
        uncheckTeacher = new javax.swing.JButton();
        checkTeacher = new javax.swing.JButton();
        txtFindTeacher = new javax.swing.JTextField();
        findTeacher = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblExport = new javax.swing.JTable();
        btnReloadExport = new javax.swing.JButton();
        btnExxport = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        txtExport = new javax.swing.JTextField();
        btnFindEx = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        btnDeleteStudent = new javax.swing.JButton();
        reloadStd = new javax.swing.JButton();
        uncheckStudent = new javax.swing.JButton();
        checkAllStudent = new javax.swing.JButton();
        txtFindStudent = new javax.swing.JTextField();
        btnFindStudent = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btnFindSu = new javax.swing.JButton();
        txtFindSubject = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSubject = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        txtNewPass = new javax.swing.JPasswordField();
        txtOldPass = new javax.swing.JPasswordField();
        txtConfirm = new javax.swing.JPasswordField();
        chkShowPass = new javax.swing.JCheckBox();
        btnSavePass = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblClass = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jButton23 = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AVT.png"))); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(0, 255, 0));
        lblName.setText("Admin");

        jLabel3.setBackground(new java.awt.Color(255, 102, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(249, 105, 13));
        jLabel3.setText("CHÀO MỪNG ĐẾN VỚI ỨNG DỤNG QUẢN LÝ ");

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(179, 5, 251));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout2.png"))); // NOI18N
        btnLogout.setText("Đăng xuất");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        managerList.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        managerList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        managerList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Quản lý người dùng", "Quản lý điểm", "Quản lý giáo viên", "Xuất điểm", "Quản lý học sinh", "Quản lý môn học", "Đổi mật khẩu", "Quản lý lớp học" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(managerList);

        managerTab.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tài khoản", "Họ tên", "Mật khẩu", "Ảnh", "Vai trò", "Trạng thái", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setShowGrid(true);
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsers);

        btnCreate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(153, 0, 255));
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Create.png"))); // NOI18N
        btnCreate.setText("Thêm người dùng");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 0, 0));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReloadUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReloadUser.setForeground(new java.awt.Color(255, 0, 0));
        btnReloadUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnReloadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadUserActionPerformed(evt);
            }
        });

        btnunCheckAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnunCheckAll.setForeground(new java.awt.Color(117, 12, 221));
        btnunCheckAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        btnunCheckAll.setText("Bỏ chọn");
        btnunCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnunCheckAllActionPerformed(evt);
            }
        });

        btncheckAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btncheckAll.setForeground(new java.awt.Color(142, 0, 240));
        btncheckAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        btncheckAll.setText("Chọn tất cả");
        btncheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncheckAllActionPerformed(evt);
            }
        });

        btnUpload.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upload.png"))); // NOI18N
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        btnFind.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReloadUser, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncheckAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnunCheckAll)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReloadUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btncheckAll, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnunCheckAll, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        managerTab.addTab("QL Người Dùng", jPanel1);

        tblMarks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID học sinh", "Họ tên", "Môn học", "Điểm thường xuyên", "Điểm giữa kỳ", "Điểm cuối kỳ", "Điểm trung bình", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMarks.setShowGrid(true);
        tblMarks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarksMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMarks);

        btnDeleteMarks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteMarks.setForeground(new java.awt.Color(255, 51, 51));
        btnDeleteMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDeleteMarks.setText("Xóa điểm");
        btnDeleteMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMarksActionPerformed(evt);
            }
        });

        btnReloadMarks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReloadMarks.setForeground(new java.awt.Color(255, 51, 51));
        btnReloadMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnReloadMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadMarksActionPerformed(evt);
            }
        });

        btnUnSelectMarks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUnSelectMarks.setForeground(new java.awt.Color(188, 1, 219));
        btnUnSelectMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        btnUnSelectMarks.setText("Bỏ chọn");
        btnUnSelectMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnSelectMarksActionPerformed(evt);
            }
        });

        btnSelectMarks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSelectMarks.setForeground(new java.awt.Color(188, 1, 219));
        btnSelectMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        btnSelectMarks.setText("Chọn tất cả");
        btnSelectMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectMarksActionPerformed(evt);
            }
        });

        txtFindMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindMarksActionPerformed(evt);
            }
        });

        btnFindMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnFindMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindMarksActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnDeleteMarks)
                        .addGap(319, 319, 319)
                        .addComponent(btnReloadMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFindMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(btnSelectMarks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUnSelectMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReloadMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUnSelectMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSelectMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFindMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnDeleteMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFindMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        managerTab.addTab("QL ĐIểm", jPanel2);

        tblTeachers.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));
        tblTeachers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã giáo viên", "Mã người dùng", "Họ tên", "Photo", "Ngày sinh", "Gmail", "Môn", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTeachers.setShowGrid(true);
        tblTeachers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTeachersMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblTeachers);

        deleteTeacher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteTeacher.setForeground(new java.awt.Color(255, 0, 51));
        deleteTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        deleteTeacher.setText("Xóa");
        deleteTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTeacherActionPerformed(evt);
            }
        });

        btnReloadTeachers.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReloadTeachers.setForeground(new java.awt.Color(199, 1, 248));
        btnReloadTeachers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnReloadTeachers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadTeachersActionPerformed(evt);
            }
        });

        uncheckTeacher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        uncheckTeacher.setForeground(new java.awt.Color(255, 51, 51));
        uncheckTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        uncheckTeacher.setText("Bỏ chọn");
        uncheckTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uncheckTeacherActionPerformed(evt);
            }
        });

        checkTeacher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        checkTeacher.setForeground(new java.awt.Color(0, 255, 0));
        checkTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        checkTeacher.setText("Chọn tất cả");
        checkTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTeacherActionPerformed(evt);
            }
        });

        findTeacher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        findTeacher.setForeground(new java.awt.Color(199, 1, 248));
        findTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        findTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findTeacherActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(deleteTeacher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReloadTeachers, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(279, 279, 279)
                        .addComponent(txtFindTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(findTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addComponent(checkTeacher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uncheckTeacher)))
                .addContainerGap())
            .addComponent(jScrollPane6)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReloadTeachers, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(findTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uncheckTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFindTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        managerTab.addTab("QL Giáo Viên", jPanel3);

        tblExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID học sinh", "Học sinh", "Môn học", "Điểm thường xuyên", "Điểm giữa kỳ", "Điểm cuối kỳ", "Điểm trung bình", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExport.setShowGrid(true);
        jScrollPane10.setViewportView(tblExport);

        btnReloadExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnReloadExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadExportActionPerformed(evt);
            }
        });

        btnExxport.setBackground(new java.awt.Color(250, 250, 240));
        btnExxport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExxport.setForeground(new java.awt.Color(204, 51, 255));
        btnExxport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/export.png"))); // NOI18N
        btnExxport.setText("Xuất điểm");
        btnExxport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExxportActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));

        btnFindEx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnFindEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindExActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReloadExport, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(314, 314, 314)
                .addComponent(txtExport, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFindEx, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExxport)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReloadExport, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExxport, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnFindEx, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtExport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        managerTab.addTab("Xuất Điểm", jPanel4);

        tblStudent.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));
        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã học sinh", "Mã người dùng", "Tên", "Photo", "Tên đăng nhập", "Ngày sinh", "Gmail", "Lớp", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudent.setShowGrid(true);
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblStudent);

        btnDeleteStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteStudent.setForeground(new java.awt.Color(247, 3, 10));
        btnDeleteStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDeleteStudent.setText("Xóa");
        btnDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStudentActionPerformed(evt);
            }
        });

        reloadStd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        reloadStd.setForeground(new java.awt.Color(247, 3, 10));
        reloadStd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        reloadStd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadStdActionPerformed(evt);
            }
        });

        uncheckStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        uncheckStudent.setForeground(new java.awt.Color(255, 0, 255));
        uncheckStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        uncheckStudent.setText("Bỏ chọn");
        uncheckStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uncheckStudentActionPerformed(evt);
            }
        });

        checkAllStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        checkAllStudent.setForeground(new java.awt.Color(0, 255, 0));
        checkAllStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        checkAllStudent.setText("Chọn tất cả");
        checkAllStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAllStudentActionPerformed(evt);
            }
        });

        btnFindStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFindStudent.setForeground(new java.awt.Color(247, 3, 10));
        btnFindStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnFindStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindStudentActionPerformed(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnDeleteStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reloadStd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addComponent(txtFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addComponent(checkAllStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uncheckStudent))
                    .addComponent(jSeparator5))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(reloadStd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkAllStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(uncheckStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        managerTab.addTab("QL Học Sinh", jPanel5);

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(190, 0, 247));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Create.png"))); // NOI18N
        jButton7.setText("Thêm môn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(247, 0, 22));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton8.setText("Xóa");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton38.setForeground(new java.awt.Color(247, 0, 22));
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(251, 5, 64));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        jButton9.setText("Bỏ chọn");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(46, 251, 4));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        jButton10.setText("Chọn tất cả");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        btnFindSu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFindSu.setForeground(new java.awt.Color(247, 0, 22));
        btnFindSu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnFindSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindSuActionPerformed(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));

        tblSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã môn", "Tên môn", "Tín chỉ", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSubject.setShowGrid(true);
        tblSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSubjectMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblSubject);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(236, 236, 236)
                        .addComponent(txtFindSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindSu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addContainerGap())))
            .addComponent(jScrollPane7)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFindSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindSu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        managerTab.addTab("QL Môn Học", jPanel6);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(244, 0, 0));
        jLabel2.setText("ĐỔI MẬT KHẨU");

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));

        txtNewPass.setPreferredSize(new java.awt.Dimension(68, 40));

        txtOldPass.setPreferredSize(new java.awt.Dimension(68, 40));

        txtConfirm.setPreferredSize(new java.awt.Dimension(68, 40));

        chkShowPass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkShowPass.setText("Hiển thị mật khẩu");

        btnSavePass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSavePass.setForeground(new java.awt.Color(200, 0, 251));
        btnSavePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnSavePass.setText("Lưu");
        btnSavePass.setPreferredSize(new java.awt.Dimension(84, 35));
        btnSavePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSavePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkShowPass))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                            .addComponent(txtOldPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(txtConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(73, 73, 73))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkShowPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSavePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(509, 509, 509))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );

        managerTab.addTab("Đổi Mật Khẩu", jPanel7);

        tblClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID lớp", "Tên", "GVCN", "Số lượng học sinh", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClass.setShowGrid(true);
        jScrollPane4.setViewportView(tblClass);

        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 255, 0));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Create.png"))); // NOI18N
        jButton19.setText("Thêm lớp");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 0, 7));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton20.setText("Xóa");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton21.setForeground(new java.awt.Color(204, 51, 255));
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        jButton21.setText("Bỏ chọn");

        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton22.setForeground(new java.awt.Color(204, 0, 255));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        jButton22.setText("Chọn tất cả");

        jButton36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton36.setForeground(new java.awt.Color(255, 0, 7));
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(153, 153, 153));

        jSeparator8.setForeground(new java.awt.Color(102, 102, 102));

        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton23.setForeground(new java.awt.Color(0, 255, 0));
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLast.setForeground(new java.awt.Color(0, 255, 0));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnPrevious.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPrevious.setForeground(new java.awt.Color(0, 255, 0));
        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(0, 255, 0));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFirst.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(0, 255, 0));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/first.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(465, 465, 465)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButton20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jButton22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jSeparator8)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPrevious, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNext, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFirst, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(514, 514, 514))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(jButton22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(506, 506, 506)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7))
                .addContainerGap())
        );

        managerTab.addTab("QL Lớp Học", jPanel8);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(managerTab, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblName)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addComponent(managerTab, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel10, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReloadMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadMarksActionPerformed
        // TODO add your handling code here:
        MJJ.fillToTable(tblMarks);
        txtFindMarks.setText("");
    }//GEN-LAST:event_btnReloadMarksActionPerformed

    private void txtFindMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindMarksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindMarksActionPerformed

    private void deleteTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTeacherActionPerformed
        // TODO add your handling code here:
        TJ.deleteCheckedItems(tblTeachers, 7);
    }//GEN-LAST:event_deleteTeacherActionPerformed

    private void btnReloadTeachersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadTeachersActionPerformed
        // TODO add your handling code here:
        TJ.fillToTable(tblTeachers);
        txtFindTeacher.setText("");
    }//GEN-LAST:event_btnReloadTeachersActionPerformed

    private void uncheckTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uncheckTeacherActionPerformed
        // TODO add your handling code here:
        TJ.setCheckAll(false, tblTeachers, 7);
    }//GEN-LAST:event_uncheckTeacherActionPerformed

    private void checkTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTeacherActionPerformed
        // TODO add your handling code here:
        TJ.setCheckAll(true, tblTeachers, 7);
    }//GEN-LAST:event_checkTeacherActionPerformed

    private void findTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findTeacherActionPerformed
        // TODO add your handling code here:
       findTeacher();
    }//GEN-LAST:event_findTeacherActionPerformed

    private void btnReloadExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadExportActionPerformed
        // TODO add your handling code here:
        MJJ.fillToTable(tblExport);
        txtExport.setText("");
    }//GEN-LAST:event_btnReloadExportActionPerformed

    private void btnExxportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExxportActionPerformed
        // TODO add your handling code here:
          MJJ.exportTable(tblExport);
    }//GEN-LAST:event_btnExxportActionPerformed

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() ==2){
            SJD.createStudents();
        }
    }//GEN-LAST:event_tblStudentMouseClicked

    private void btnDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStudentActionPerformed
        // TODO add your handling code here:
        SJD.deleteStudent(tblStudent);
    }//GEN-LAST:event_btnDeleteStudentActionPerformed

    private void reloadStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadStdActionPerformed
        // TODO add your handling code here:
        SJD.fillToTable(tblStudent);
        txtFindStudent.setText("");
    }//GEN-LAST:event_reloadStdActionPerformed

    private void uncheckStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uncheckStudentActionPerformed
        // TODO add your handling code here:
        TJ.setCheckAll(false, tblStudent, 8);
    }//GEN-LAST:event_uncheckStudentActionPerformed

    private void checkAllStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAllStudentActionPerformed
        // TODO add your handling code here:
        TJ.setCheckAll(true, tblStudent, 8);
    }//GEN-LAST:event_checkAllStudentActionPerformed

    private void btnFindStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindStudentActionPerformed
        // TODO add your handling code here:
       findStudent();
    }//GEN-LAST:event_btnFindStudentActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        SJ.deleteCheckedItems(tblSubject, 3);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnSavePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePassActionPerformed
        // TODO add your handling code here:
       ChangePass();
    }//GEN-LAST:event_btnSavePassActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        open();
    }//GEN-LAST:event_formWindowOpened

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        try {
            UMJ.setEditable(false);
            UMJ.clear();
            UMJ.setVisible(true);
            UMJ.fillToTable(tblUsers);

        } catch (Exception e) {
            XDialog.alert("Lỗi");
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            UMJ.deleteCheckedItems(tblUsers, 7);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        LoginJDialog LG = new LoginJDialog(this, true);
        this.dispose();
        LG.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            items = dao.findAll();
            Users selectedUser = items.get(tblUsers.getSelectedRow());
            UMJ.edit(selectedUser);
            UMJ.setTargetTableUser(tblUsers);
            UMJ.setVisible(true);
        }
    }//GEN-LAST:event_tblUsersMouseClicked

    private void btncheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncheckAllActionPerformed
        // TODO add your handling code here:
        UMJ.setCheckAll(true, tblUsers);
    }//GEN-LAST:event_btncheckAllActionPerformed

    private void btnunCheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnunCheckAllActionPerformed
        // TODO add your handling code here:
        UMJ.setCheckAll(false, tblUsers);
    }//GEN-LAST:event_btnunCheckAllActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        findUser();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnReloadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadUserActionPerformed
        // TODO add your handling code here:
        UMJ.fillToTable(tblUsers);
        txtFind.setText("");
    }//GEN-LAST:event_btnReloadUserActionPerformed

    private void tblTeachersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTeachersMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            TJ.createTeacher();
        }
 
    }//GEN-LAST:event_tblTeachersMouseClicked

    private void tblMarksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarksMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            itemsMa = daoMa.findAll();
            Marks selectdObject = itemsMa.get(tblMarks.getSelectedRow());
            selectdObject.setSubject(String.valueOf(tblMarks.getValueAt(tblMarks.getSelectedRow(), 2)));
            MJJ.clear();
            MJJ.setEditable(true);
            MJJ.setForm(selectdObject);
            MJJ.setVisible(true);
        }
    }//GEN-LAST:event_tblMarksMouseClicked

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        // TODO add your handling code here:
        ULED.uploadDataToDB();
    }//GEN-LAST:event_btnUploadActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        CJ.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void btnSelectMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectMarksActionPerformed
        // TODO add your handling code here:
        UMJ.setCheckAll(true, tblMarks);
    }//GEN-LAST:event_btnSelectMarksActionPerformed

    private void btnUnSelectMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnSelectMarksActionPerformed
        // TODO add your handling code here:
        UMJ.setCheckAll(false, tblMarks);
    }//GEN-LAST:event_btnUnSelectMarksActionPerformed

    private void btnFindMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindMarksActionPerformed
        // TODO add your handling code here:
        findStMarks(txtFindMarks, tblMarks);
        
    }//GEN-LAST:event_btnFindMarksActionPerformed

    private void btnDeleteMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMarksActionPerformed
        // TODO add your handling code here:
        MJJ.deleteMarks(tblMarks);
        MJJ.fillToTable(tblMarks);
    }//GEN-LAST:event_btnDeleteMarksActionPerformed

    private void btnFindExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindExActionPerformed
        // TODO add your handling code here:
        findStMarks(txtExport, tblExport);
        txtExport.setText("");
    }//GEN-LAST:event_btnFindExActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        SJ.setEitable(true);
        SJ.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
        SJ.fillToTable(tblSubject);
        txtFindSubject.setText("");
    }//GEN-LAST:event_jButton38ActionPerformed

    private void tblSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            itemsSu = daoSu.findAll();
            Subjects selectedSu = itemsSu.get(tblSubject.getSelectedRow());
            SJ.clear();
            SJ.setEitable(false);
            SJ.setForm(selectedSu);
            SJ.setVisible(true);
        }
    }//GEN-LAST:event_tblSubjectMouseClicked

    private void btnFindSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindSuActionPerformed
        // TODO add your handling code here:
        findSubject();
    }//GEN-LAST:event_btnFindSuActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        SJ.setCheckAll(true, tblSubject);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        SJ.setCheckAll(false, tblSubject);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        CJ.fillToTable(tblClass);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstActionPerformed

    /**
     * @param args the command line arguments 
     */
    public static void main(String args[]) {
       try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteMarks;
    private javax.swing.JButton btnDeleteStudent;
    private javax.swing.JButton btnExxport;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFindEx;
    private javax.swing.JButton btnFindMarks;
    private javax.swing.JButton btnFindStudent;
    private javax.swing.JButton btnFindSu;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnReloadExport;
    private javax.swing.JButton btnReloadMarks;
    private javax.swing.JButton btnReloadTeachers;
    private javax.swing.JButton btnReloadUser;
    private javax.swing.JButton btnSavePass;
    private javax.swing.JButton btnSelectMarks;
    private javax.swing.JButton btnUnSelectMarks;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton btncheckAll;
    private javax.swing.JButton btnunCheckAll;
    private javax.swing.JButton checkAllStudent;
    private javax.swing.JButton checkTeacher;
    private javax.swing.JCheckBox chkShowPass;
    private javax.swing.JButton deleteTeacher;
    private javax.swing.JButton findTeacher;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblName;
    private javax.swing.JList<String> managerList;
    private javax.swing.JTabbedPane managerTab;
    private javax.swing.JButton reloadStd;
    private javax.swing.JTable tblClass;
    private javax.swing.JTable tblExport;
    private javax.swing.JTable tblMarks;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTable tblSubject;
    private javax.swing.JTable tblTeachers;
    private javax.swing.JTable tblUsers;
    private javax.swing.JPasswordField txtConfirm;
    private javax.swing.JTextField txtExport;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtFindMarks;
    private javax.swing.JTextField txtFindStudent;
    private javax.swing.JTextField txtFindSubject;
    private javax.swing.JTextField txtFindTeacher;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtOldPass;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JButton uncheckStudent;
    private javax.swing.JButton uncheckTeacher;
    // End of variables declaration//GEN-END:variables
}
