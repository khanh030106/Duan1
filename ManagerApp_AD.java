/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package DuAn1.ui;
import DA1.DAOimpl.DepartmentImpl;
import DA1.DAOimpl.MarksImpl;
import DA1.DAOimpl.StudentsImpl;
import DA1.DAOimpl.TeachersImpl;
import DA1.DAOimpl.UsersDAOimpl;
import DA1.Entity.Departments;
import DA1.Entity.Marks;
import DA1.Entity.Students;
import DA1.Entity.Teachers;
import DA1.Entity.Users;
import DA1.util.FormatTable;
import DA1.util.FormatList;
import DA1.util.PlaceHolder;
import DA1.util.XAuth;
import DA1.util.XDialog;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
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
    DepartmentImpl daoDE = new DepartmentImpl();
    List<Departments> itemsDE = List.of();
    MarksImpl daoMa = new MarksImpl();
    List<Marks> itemsMa = List.of();
    
    UsersManagerJDialog UMJ = new UsersManagerJDialog(this, rootPaneCheckingEnabled);
    DepartmentsJDialog DJ = new DepartmentsJDialog(this, rootPaneCheckingEnabled);
    MajorsJDialog MJ = new MajorsJDialog(this, rootPaneCheckingEnabled);
    SubjectsJDialog SJ = new SubjectsJDialog(this, rootPaneCheckingEnabled);
    TeachersJDialog TJ = new TeachersJDialog(this, true);
    StudentsJDialog SJD = new StudentsJDialog(this, rootPaneCheckingEnabled);
    ClassJDialog CJ = new ClassJDialog(this, rootPaneCheckingEnabled);
    MarksJDialog MJJ = new MarksJDialog(this, rootPaneCheckingEnabled);
    
    

    /**
     * Creates new form ManagerApp_AD
     */
    public ManagerApp_AD(Users user) {
        
        this.user = user;
        initComponents();
        listIcon();
        formatPanel();
        txtName.setText(user.getFullname());
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

    public void formatPanel() {
        managerTab.setBorder(new MatteBorder(3, 3, 3, 3, new Color(165, 189, 240)));
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
        iconMap.put("Quản lý chuyên ngành", new ImageIcon(FormatList.class.getResource("/images/subjects.png")));
        iconMap.put("Quản lý lớp học", new ImageIcon(FormatList.class.getResource("/images/Class.png")));
        managerList.setCellRenderer(new FormatList(iconMap));
    }

    public void open() {
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
        DJ.settblDepartment(tblDepartment); 
        MJJ.setTargetTableMarks(tblMarks);
        
        UMJ.fillToTable(tblUsers);
        DJ.fillToTable(tblDepartment);
        MJ.fillToTable(tblMajor);
        SJ.fillToTable(tblSubject);
        TJ.fillToTable(tblTeachers);
        SJD.fillToTable(tblStudent);
        CJ.fillToTable(tblClass);
        MJJ.fillToTable(tblMarks);
        MJJ.fillToTable(tblExport);

        txtName.setText(user.getFullname());
        FormatTable.RendererColumn(tblUsers, 0);
        FormatTable.RendererColumn(tblUsers, 6);
        FormatTable.RendererColumn(tblUsers, 3);
        FormatTable.RendererColumn(tblUsers, 4);
        FormatTable.RendererColumn(tblUsers, 5);
        FormatTable.RendererColumn(tblDepartment, 0);
        FormatTable.RendererColumn(tblDepartment, 2);
        FormatTable.RendererColumn(tblMajor, 0);
        FormatTable.RendererColumn(tblMajor, 2);
        FormatTable.RendererColumn(tblSubject, 0);
        FormatTable.RendererColumn(tblSubject, 2);
        FormatTable.RendererColumn(tblSubject, 3);
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
        FormatTable.setColumnSize(tblExport, 0, 10);

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
        } else if (newPass != confirmPass) {
            XDialog.alert("Mật khẩu mới và xác nhận mật khẩu không khớp");
        } else {
            XAuth.user.setPassWord(newPass);
            dao.update(XAuth.user);
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
            this.dispose();
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
        txtFind.setText(""); 
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
                e.getFullname(),
                e.getPhoto(),
                e.getBirthdate(),
                e.getGmail(),
                e.getDepartment(),
                false
            };
            model.addRow(rowData);
        });
        }
        txtFindTeacher.setText(""); 
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
        txtFindStudent.setText(""); 
        itemsStd = daoStd.findAll();
    }
    public void findDepartment() {
        DefaultTableModel model = (DefaultTableModel) tblDepartment.getModel();
        model.setRowCount(0);
        String name = txtDeparrt.getText().trim();
        itemsDE = daoDE.findByName(name);
        
        if (itemsDE.isEmpty()) {
            XDialog.alert("Không tìm thấy");
        } else {
            itemsDE.forEach(e -> {
            Object[] rowData = {
                e.getMaKhoa(),
                e.getTenKhoa(),
                e.getSoLuongChuyenNganh(),
                false
            };
            model.addRow(rowData);
        });
        }
        txtDeparrt.setText(""); 
        itemsDE = daoDE.findAll();
    }
    
    public void findStMarks(){
        DefaultTableModel model = (DefaultTableModel) tblExport.getModel();
        model.setRowCount(0);
        String name = txtExport.getText().trim();
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

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        managerTab = new javax.swing.JTabbedPane();
        pnlQLND = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnunCheckAll = new javax.swing.JButton();
        btncheckAll = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        btnReloadUser = new javax.swing.JButton();
        pnlQLD = new javax.swing.JPanel();
        btnDeleteMarks = new javax.swing.JButton();
        btnUnSelectMarks = new javax.swing.JButton();
        btnReloadMarks = new javax.swing.JButton();
        btnSelectMarks = new javax.swing.JButton();
        txtFindMarks = new javax.swing.JTextField();
        btnFindMarks = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblMarks = new javax.swing.JTable();
        pnlQLGV = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTeachers = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        deleteTeacher = new javax.swing.JButton();
        uncheckTeacher = new javax.swing.JButton();
        checkTeacher = new javax.swing.JButton();
        txtFindTeacher = new javax.swing.JTextField();
        findTeacher = new javax.swing.JButton();
        btnReloadTeachers = new javax.swing.JButton();
        pnlXD = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblExport = new javax.swing.JTable();
        btnExxport = new javax.swing.JButton();
        txtExport = new javax.swing.JTextField();
        btnExport = new javax.swing.JButton();
        btnReloadExport = new javax.swing.JButton();
        pnlQLHS = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        uncheckStudent = new javax.swing.JButton();
        checkAllStudent = new javax.swing.JButton();
        txtFindStudent = new javax.swing.JTextField();
        btnFindStudent = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        reloadStd = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        pnlQLLH = new javax.swing.JPanel();
        pnlPCGV = new javax.swing.JPanel();
        pnlQLMH = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jButton38 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSubject = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        txtOldPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        txtConfirm = new javax.swing.JPasswordField();
        chkShowPass = new javax.swing.JCheckBox();
        btnSave = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnCrDepartments = new javax.swing.JButton();
        btnDelDepartments = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtDeparrt = new javax.swing.JTextField();
        btnDelDepartments1 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        btnDelDepartments2 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblDepartment = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        jButton37 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMajor = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblClass = new javax.swing.JTable();
        txtName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        managerList = new javax.swing.JList<>();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        managerTab.setBackground(new java.awt.Color(250, 250, 240));
        managerTab.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(165, 194, 251), null));
        managerTab.setForeground(new java.awt.Color(230, 0, 0));
        managerTab.setPreferredSize(new java.awt.Dimension(1014, 780));

        pnlQLND.setBackground(new java.awt.Color(250, 250, 240));
        pnlQLND.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));

        tblUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));
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
        btnCreate.setForeground(new java.awt.Color(140, 54, 237));
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Create.png"))); // NOI18N
        btnCreate.setText("Thêm người dùng");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(251, 22, 22));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnunCheckAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnunCheckAll.setForeground(new java.awt.Color(206, 3, 248));
        btnunCheckAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        btnunCheckAll.setText("Bỏ chọn");
        btnunCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnunCheckAllActionPerformed(evt);
            }
        });

        btncheckAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btncheckAll.setForeground(new java.awt.Color(224, 10, 251));
        btncheckAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        btncheckAll.setText("Chọn tất cả");
        btncheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncheckAllActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        btnFind.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFind.setForeground(new java.awt.Color(166, 216, 0));
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnReloadUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnReloadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlQLNDLayout = new javax.swing.GroupLayout(pnlQLND);
        pnlQLND.setLayout(pnlQLNDLayout);
        pnlQLNDLayout.setHorizontalGroup(
            pnlQLNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLNDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQLNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(pnlQLNDLayout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReloadUser, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(btncheckAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnunCheckAll, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        pnlQLNDLayout.setVerticalGroup(
            pnlQLNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLNDLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlQLNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQLNDLayout.createSequentialGroup()
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlQLNDLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlQLNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReloadUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFind, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnunCheckAll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncheckAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(224, 224, 224))
        );

        managerTab.addTab("QLND", pnlQLND);

        btnDeleteMarks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteMarks.setForeground(new java.awt.Color(255, 51, 51));
        btnDeleteMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDeleteMarks.setText("Xóa");

        btnUnSelectMarks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUnSelectMarks.setForeground(new java.awt.Color(188, 1, 219));
        btnUnSelectMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        btnUnSelectMarks.setText("Bỏ chọn");

        btnReloadMarks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReloadMarks.setForeground(new java.awt.Color(255, 51, 51));
        btnReloadMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnReloadMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadMarksActionPerformed(evt);
            }
        });

        btnSelectMarks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSelectMarks.setForeground(new java.awt.Color(188, 1, 219));
        btnSelectMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        btnSelectMarks.setText("Chọn tất cả");

        txtFindMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindMarksActionPerformed(evt);
            }
        });

        btnFindMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N

        jSeparator3.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));

        tblMarks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID học sinh", "Học sinh", "Môn học", "Điểm thường xuyên", "Điểm giữa kỳ", "Điểm cuối kỳ", "Điểm trung bình", ""
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
        jScrollPane7.setViewportView(tblMarks);

        javax.swing.GroupLayout pnlQLDLayout = new javax.swing.GroupLayout(pnlQLD);
        pnlQLD.setLayout(pnlQLDLayout);
        pnlQLDLayout.setHorizontalGroup(
            pnlQLDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeleteMarks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReloadMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(txtFindMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFindMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(btnSelectMarks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUnSelectMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator3)
            .addComponent(jScrollPane7)
        );
        pnlQLDLayout.setVerticalGroup(
            pnlQLDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLDLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlQLDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeleteMarks, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReloadMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlQLDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUnSelectMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSelectMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFindMarks)
                    .addComponent(btnFindMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        managerTab.addTab("QLĐ", pnlQLD);

        pnlQLGV.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));

        tblTeachers.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));
        tblTeachers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã giáo viên", "Mã người dùng", "Họ tên", "Photo", "Ngày sinh", "Gmail", "Khoa", ""
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

        jSeparator4.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator4.setForeground(new java.awt.Color(153, 153, 153));

        deleteTeacher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteTeacher.setForeground(new java.awt.Color(255, 0, 51));
        deleteTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        deleteTeacher.setText("Xóa");
        deleteTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTeacherActionPerformed(evt);
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

        btnReloadTeachers.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReloadTeachers.setForeground(new java.awt.Color(199, 1, 248));
        btnReloadTeachers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnReloadTeachers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadTeachersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlQLGVLayout = new javax.swing.GroupLayout(pnlQLGV);
        pnlQLGV.setLayout(pnlQLGVLayout);
        pnlQLGVLayout.setHorizontalGroup(
            pnlQLGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLGVLayout.createSequentialGroup()
                .addGroup(pnlQLGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQLGVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(deleteTeacher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReloadTeachers, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(txtFindTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(findTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(checkTeacher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uncheckTeacher))
                    .addComponent(jScrollPane6))
                .addContainerGap())
            .addComponent(jSeparator4)
        );
        pnlQLGVLayout.setVerticalGroup(
            pnlQLGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLGVLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlQLGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReloadTeachers, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlQLGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(findTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlQLGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uncheckTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtFindTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        managerTab.addTab("tab3", pnlQLGV);

        tblExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
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

        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnReloadExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnReloadExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlXDLayout = new javax.swing.GroupLayout(pnlXD);
        pnlXD.setLayout(pnlXDLayout);
        pnlXDLayout.setHorizontalGroup(
            pnlXDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlXDLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnReloadExport, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtExport, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220)
                .addComponent(btnExxport)
                .addGap(20, 20, 20))
        );
        pnlXDLayout.setVerticalGroup(
            pnlXDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlXDLayout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlXDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExxport, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(txtExport)
                    .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReloadExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        managerTab.addTab("tab4", pnlXD);

        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(247, 3, 10));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton15.setText("Xóa");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
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

        jSeparator5.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));

        reloadStd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        reloadStd.setForeground(new java.awt.Color(247, 3, 10));
        reloadStd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        reloadStd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadStdActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout pnlQLHSLayout = new javax.swing.GroupLayout(pnlQLHS);
        pnlQLHS.setLayout(pnlQLHSLayout);
        pnlQLHSLayout.setHorizontalGroup(
            pnlQLHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(pnlQLHSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadStd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(txtFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(checkAllStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uncheckStudent)
                .addContainerGap())
            .addComponent(jScrollPane8)
        );
        pnlQLHSLayout.setVerticalGroup(
            pnlQLHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLHSLayout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlQLHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloadStd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkAllStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uncheckStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );

        managerTab.addTab("tab5", pnlQLHS);

        javax.swing.GroupLayout pnlQLLHLayout = new javax.swing.GroupLayout(pnlQLLH);
        pnlQLLH.setLayout(pnlQLLHLayout);
        pnlQLLHLayout.setHorizontalGroup(
            pnlQLLHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1041, Short.MAX_VALUE)
        );
        pnlQLLHLayout.setVerticalGroup(
            pnlQLLHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        managerTab.addTab("tab6", pnlQLLH);

        javax.swing.GroupLayout pnlPCGVLayout = new javax.swing.GroupLayout(pnlPCGV);
        pnlPCGV.setLayout(pnlPCGVLayout);
        pnlPCGVLayout.setHorizontalGroup(
            pnlPCGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1041, Short.MAX_VALUE)
        );
        pnlPCGVLayout.setVerticalGroup(
            pnlPCGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        managerTab.addTab("tab7", pnlPCGV);

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(190, 0, 247));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Create.png"))); // NOI18N
        jButton7.setText("Thêm môn");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(247, 0, 22));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton8.setText("Xóa");

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(251, 5, 64));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        jButton9.setText("Bỏ chọn");

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(46, 251, 4));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        jButton10.setText("Chọn tất cả");

        jButton34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton34.setForeground(new java.awt.Color(247, 0, 22));
        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N

        jSeparator6.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator6.setForeground(new java.awt.Color(102, 102, 102));

        jButton38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton38.setForeground(new java.awt.Color(247, 0, 22));
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N

        tblSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã môn", "Tên môn", "Tín chỉ", "Mã chuyên ngành", "Tên chuyên ngành", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSubject.setShowGrid(true);
        jScrollPane5.setViewportView(tblSubject);

        javax.swing.GroupLayout pnlQLMHLayout = new javax.swing.GroupLayout(pnlQLMH);
        pnlQLMH.setLayout(pnlQLMHLayout);
        pnlQLMHLayout.setHorizontalGroup(
            pnlQLMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLMHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap())
            .addComponent(jSeparator6)
            .addComponent(jScrollPane5)
        );
        pnlQLMHLayout.setVerticalGroup(
            pnlQLMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLMHLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlQLMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlQLMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        managerTab.addTab("tab8", pnlQLMH);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(244, 0, 0));
        jLabel2.setText("ĐỔI MẬT KHẨU");

        jPanel6.setBackground(new java.awt.Color(250, 250, 240));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));
        jPanel6.setForeground(new java.awt.Color(250, 250, 240));

        txtOldPass.setPreferredSize(new java.awt.Dimension(68, 40));

        txtNewPass.setPreferredSize(new java.awt.Dimension(68, 40));

        txtConfirm.setPreferredSize(new java.awt.Dimension(68, 40));

        chkShowPass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkShowPass.setText("Hiển thị mật khẩu");

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(200, 0, 251));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.setPreferredSize(new java.awt.Dimension(84, 35));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkShowPass)))
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(txtOldPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(chkShowPass)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(415, 415, 415))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(131, 131, 131))
        );

        managerTab.addTab("tab9", jPanel2);

        btnCrDepartments.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCrDepartments.setForeground(new java.awt.Color(150, 0, 255));
        btnCrDepartments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Create.png"))); // NOI18N
        btnCrDepartments.setText("Thêm khoa");
        btnCrDepartments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrDepartmentsActionPerformed(evt);
            }
        });

        btnDelDepartments.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelDepartments.setForeground(new java.awt.Color(255, 0, 51));
        btnDelDepartments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDelDepartments.setText("Xóa");
        btnDelDepartments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelDepartmentsActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(226, 0, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        jButton1.setText("Bỏ chọn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(226, 0, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        jButton2.setText("Chọn tất cả");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnDelDepartments1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelDepartments1.setForeground(new java.awt.Color(255, 0, 51));
        btnDelDepartments1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnDelDepartments1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelDepartments1ActionPerformed(evt);
            }
        });

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator7.setForeground(new java.awt.Color(102, 102, 102));

        btnDelDepartments2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelDepartments2.setForeground(new java.awt.Color(255, 0, 51));
        btnDelDepartments2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N
        btnDelDepartments2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelDepartments2ActionPerformed(evt);
            }
        });

        tblDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã khoa", "Tên Khoa", "Số chuyên ngành", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
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
        tblDepartment.setShowGrid(true);
        tblDepartment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDepartmentMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblDepartment);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCrDepartments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelDepartments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelDepartments2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(txtDeparrt, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelDepartments1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(12, 12, 12))
            .addComponent(jSeparator7)
            .addComponent(jScrollPane9)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelDepartments, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btnCrDepartments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtDeparrt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnDelDepartments2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelDepartments1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        managerTab.addTab("tab10", jPanel3);

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 253, 40));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Create.png"))); // NOI18N
        jButton11.setText("Thêm chuyên ngành");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(251, 7, 22));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton12.setText("Xóa");

        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(153, 102, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unSelect.png"))); // NOI18N
        jButton13.setText("Bỏ chọn");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(172, 0, 253));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        jButton14.setText("Chọn tất cả");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton35.setForeground(new java.awt.Color(251, 7, 22));
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N

        jSeparator8.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator8.setForeground(new java.awt.Color(102, 102, 102));

        jButton37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton37.setForeground(new java.awt.Color(251, 7, 22));
        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N

        tblMajor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã chuyên ngành", "Tên chuyên ngành", "Mã khoa", "Tên khoa", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
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
        tblMajor.setShowGrid(true);
        jScrollPane3.setViewportView(tblMajor);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addContainerGap())
            .addComponent(jSeparator8)
            .addComponent(jScrollPane3)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        managerTab.addTab("tab11", jPanel4);

        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 255, 0));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Create.png"))); // NOI18N
        jButton19.setText("Thêm lớp");

        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 0, 7));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton20.setText("Xóa");

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

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Reload.png"))); // NOI18N

        jSeparator9.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator9.setForeground(new java.awt.Color(102, 102, 102));

        tblClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID lớp", "Tên", "GVCN", "Khoa", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(jButton22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21))
            .addComponent(jSeparator9)
            .addComponent(jScrollPane4)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton36, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        managerTab.addTab("tab12", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(managerTab, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(managerTab, javax.swing.GroupLayout.PREFERRED_SIZE, 560, Short.MAX_VALUE)
        );

        managerTab.getAccessibleContext().setAccessibleName("QL người dùng");

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(113, 189, 37));
        txtName.setText("Admin");

        jLabel3.setBackground(new java.awt.Color(255, 102, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(249, 105, 13));
        jLabel3.setText("CHÀO MỪNG ĐẾN VỚI ỨNG DỤNG QUẢN LÝ TRƯỜNG HỌC");

        jSeparator2.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AVT.png"))); // NOI18N

        managerList.setBackground(new java.awt.Color(250, 250, 240));
        managerList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 204, 0))); // NOI18N
        managerList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        managerList.setForeground(new java.awt.Color(0, 0, 0));
        managerList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Quản lý người dùng", "Quản lý điểm", "Quản lý giáo viên", "Xuất điểm", "Quản lý học sinh", "Quản lý lịch học", "Phân công giáo viên", "Quản lý môn học", "Đổi mật khẩu", "Quản lý khoa", "Quản lý chuyên ngành", "Quản lý lớp học" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        managerList.setMaximumSize(new java.awt.Dimension(168, 268));
        managerList.setMinimumSize(new java.awt.Dimension(168, 268));
        managerList.setPreferredSize(new java.awt.Dimension(168, 268));
        jScrollPane1.setViewportView(managerList);

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(240, 19, 39));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout2.png"))); // NOI18N
        btnLogout.setText("Đăng Xuất");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(txtName))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        ChangePass();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        findUser();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnReloadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadUserActionPerformed
        // TODO add your handling code here:
        UMJ.fillToTable(tblUsers);
    }//GEN-LAST:event_btnReloadUserActionPerformed

    private void btnFindStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindStudentActionPerformed
        // TODO add your handling code here:
        findStudent();
    }//GEN-LAST:event_btnFindStudentActionPerformed

    private void reloadStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadStdActionPerformed
        // TODO add your handling code here:
        SJD.fillToTable(tblStudent);
    }//GEN-LAST:event_reloadStdActionPerformed

    private void btnReloadTeachersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadTeachersActionPerformed
        // TODO add your handling code here:
        TJ.fillToTable(tblTeachers);
    }//GEN-LAST:event_btnReloadTeachersActionPerformed

    private void tblTeachersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTeachersMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() ==2) {
            TJ.createTeacher(); 
        }
    }//GEN-LAST:event_tblTeachersMouseClicked

    private void deleteTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTeacherActionPerformed
        // TODO add your handling code here:
        TJ.deleteCheckedItems(tblTeachers, 7);
    }//GEN-LAST:event_deleteTeacherActionPerformed

    private void checkTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTeacherActionPerformed
        // TODO add your handling code here:
        TJ.setCheckAll(true, tblTeachers, 7);
    }//GEN-LAST:event_checkTeacherActionPerformed

    private void uncheckTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uncheckTeacherActionPerformed
        // TODO add your handling code here:
        TJ.setCheckAll(false, tblTeachers, 7);
    }//GEN-LAST:event_uncheckTeacherActionPerformed

    private void findTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findTeacherActionPerformed
        // TODO add your handling code here:
        findTeacher();
    }//GEN-LAST:event_findTeacherActionPerformed

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() ==2){
            SJD.createStudents();
        }
    }//GEN-LAST:event_tblStudentMouseClicked

    private void checkAllStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAllStudentActionPerformed
        // TODO add your handling code here:
        TJ.setCheckAll(true, tblStudent, 8);
    }//GEN-LAST:event_checkAllStudentActionPerformed

    private void uncheckStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uncheckStudentActionPerformed
        // TODO add your handling code here:
        TJ.setCheckAll(false, tblStudent, 8);
    }//GEN-LAST:event_uncheckStudentActionPerformed

    private void btnDelDepartmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDepartmentsActionPerformed
        // TODO add your handling code here:
        if (XDialog.confirm("Bạn muốn xóa khoa này")) {
            DJ.deleteCheckedItems(tblDepartment, 3);
        }
    }//GEN-LAST:event_btnDelDepartmentsActionPerformed

    private void btnCrDepartmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrDepartmentsActionPerformed
        // TODO add your handling code here:
        DJ.setEditable(true);
        DJ.setVisible(true);
    }//GEN-LAST:event_btnCrDepartmentsActionPerformed

    private void tblDepartmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepartmentMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() ==2) {
            DJ.setEditable(false);
            itemsDE = daoDE.findAll();
            Departments selectedDepart = itemsDE.get(tblDepartment.getSelectedRow());
            DJ.setForm(selectedDepart);
            DJ.setVisible(true);
            DJ.clear();
        }
    }//GEN-LAST:event_tblDepartmentMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DJ.setCheckAll(true, tblDepartment, 3);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DJ.setCheckAll(false, tblDepartment, 3);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDelDepartments2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDepartments2ActionPerformed
        // TODO add your handling code here:
        DJ.fillToTable(tblDepartment); 
    }//GEN-LAST:event_btnDelDepartments2ActionPerformed

    private void btnDelDepartments1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDepartments1ActionPerformed
        // TODO add your handling code here:
        findDepartment();
    }//GEN-LAST:event_btnDelDepartments1ActionPerformed

    private void txtFindMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindMarksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindMarksActionPerformed

    private void tblMarksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarksMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            itemsMa = daoMa.findAll();
            Marks selectdObject = itemsMa.get(tblMarks.getSelectedRow());
            MJJ.clear();
            MJJ.setEditable(true);
            MJJ.setForm(selectdObject);
            MJJ.setVisible(true);
        }
    }//GEN-LAST:event_tblMarksMouseClicked

    private void btnReloadMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadMarksActionPerformed
        // TODO add your handling code here:
        MJJ.fillToTable(tblMarks);
    }//GEN-LAST:event_btnReloadMarksActionPerformed

    private void btnExxportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExxportActionPerformed
        // TODO add your handling code here:
        MJJ.exportTable(tblExport);
    }//GEN-LAST:event_btnExxportActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        findStMarks();
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnReloadExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadExportActionPerformed
        // TODO add your handling code here:
        MJJ.fillToTable(tblExport);
    }//GEN-LAST:event_btnReloadExportActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrDepartments;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelDepartments;
    private javax.swing.JButton btnDelDepartments1;
    private javax.swing.JButton btnDelDepartments2;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteMarks;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnExxport;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFindMarks;
    private javax.swing.JButton btnFindStudent;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReloadExport;
    private javax.swing.JButton btnReloadMarks;
    private javax.swing.JButton btnReloadTeachers;
    private javax.swing.JButton btnReloadUser;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSelectMarks;
    private javax.swing.JButton btnUnSelectMarks;
    private javax.swing.JButton btncheckAll;
    private javax.swing.JButton btnunCheckAll;
    private javax.swing.JButton checkAllStudent;
    private javax.swing.JButton checkTeacher;
    private javax.swing.JCheckBox chkShowPass;
    private javax.swing.JButton deleteTeacher;
    private javax.swing.JButton findTeacher;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JList<String> managerList;
    private javax.swing.JTabbedPane managerTab;
    private javax.swing.JPanel pnlPCGV;
    private javax.swing.JPanel pnlQLD;
    private javax.swing.JPanel pnlQLGV;
    private javax.swing.JPanel pnlQLHS;
    private javax.swing.JPanel pnlQLLH;
    private javax.swing.JPanel pnlQLMH;
    private javax.swing.JPanel pnlQLND;
    private javax.swing.JPanel pnlXD;
    private javax.swing.JButton reloadStd;
    private javax.swing.JTable tblClass;
    private javax.swing.JTable tblDepartment;
    private javax.swing.JTable tblExport;
    private javax.swing.JTable tblMajor;
    private javax.swing.JTable tblMarks;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTable tblSubject;
    private javax.swing.JTable tblTeachers;
    private javax.swing.JTable tblUsers;
    private javax.swing.JPasswordField txtConfirm;
    private javax.swing.JTextField txtDeparrt;
    private javax.swing.JTextField txtExport;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtFindMarks;
    private javax.swing.JTextField txtFindStudent;
    private javax.swing.JTextField txtFindTeacher;
    private javax.swing.JLabel txtName;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtOldPass;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JButton uncheckStudent;
    private javax.swing.JButton uncheckTeacher;
    // End of variables declaration//GEN-END:variables
}
