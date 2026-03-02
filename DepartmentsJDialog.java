/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package DuAn1.ui;

import DA1.DAOimplTest.DepartmentImpl;
import DA1.Entity.Departments;
import DA1.util.XDialog;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DepartmentsJDialog extends javax.swing.JDialog {
    List<Departments> items = List.of();
    DepartmentImpl dao = new DepartmentImpl() ;
    Departments depart;
    /**
     * Creates new form DepartmentsJDialog
     */
    public DepartmentsJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private JTable tblDepartment;
    public void settblDepartment(JTable tbl){
        this.tblDepartment = tbl;
    }
    
    public void fillToTable(JTable tbl){
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        items = dao.findAll();
        items.forEach(item ->{
            Object[] rowData = {
                item.getMaKhoa(),
                item.getTenKhoa(),
                item.getSoLuongChuyenNganh(),
                false
            };
            model.addRow(rowData);
        });
    }
    
    public Departments getForm(){
        Departments depaart = new Departments();
        depaart.setTenKhoa(txtTen.getText());
        return depaart;
    }
    
    public void setForm(Departments de){
        txtID.setText(String.valueOf(de.getMaKhoa()));
        txtTen.setText(de.getTenKhoa());
    }
    
    public void create(){
        if (txtTen.getText().trim() == null) {
            XDialog.alert("Vui lòng nhập đầy đủ thông tin");
            return;
        }else{
            try {
                Departments de = this.getForm();
                dao.create(de);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                fillToTable(tblDepartment);
                this.dispose();
            } catch (Exception e) {
                System.out.println("LỖI"+ e);
            }
        }
    }
    
    public void update(){
        Departments de = this.getForm();
        de.setMaKhoa(Integer.parseInt(txtID.getText()));
        try {
            dao.update(de);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            this.dispose();
        } catch (Exception e) {
            System.out.println("LỖI"+ e);
        }
    }
    
    public void setEditable(boolean e){
        txtID.setEnabled(false);
        txtTen.setEnabled(true);
        
        btnCreate.setEnabled(e);
        btnUpdate.setEnabled(!e);
        btnFirst.setEnabled(!e);
        btnPrevious.setEnabled(!e);
        btnNext.setEnabled(!e);
        btnLasst.setEnabled(!e);
    }
    
    public void setCheckAll(boolean check, JTable tbl, int index) {
        for (int i = 0; i < tbl.getRowCount(); i++) {
            tbl.setValueAt(check, i, index);
        }
    }
    
     public void deleteCheckedItems(JTable tbl, int checkColIndex) {
    DefaultTableModel model = (DefaultTableModel) tbl.getModel();
    for (int i = model.getRowCount() - 1; i >= 0; i--) {
        Boolean checked = (Boolean) model.getValueAt(i, checkColIndex);
        if (checked) {
            String id = String.valueOf(model.getValueAt(i, 0));
            try {
                dao.deleteById(id);
            } catch (Exception e) {
                XDialog.alert("Không thể xóa khoa này!");
                return;
            }
        }
    }
    fillToTable(tbl);
    JOptionPane.showMessageDialog(null, "Xóa thành công!");
}
     public void clear(){
         this.setForm(new Departments()); 
         if (Integer.parseInt(txtID.getText()) ==0) { 
             txtID.setText("");
         }
     }
     
      public void edit(Departments de) {
        this.depart = depart;
        this.clear();
        this.setForm(de);
        this.setEditable(false);
    }
     
    public void moveFirst(JTable tbl) {
        this.moveTo(0, tbl);
    }

    public void movePrevious(JTable tbl) {
        this.moveTo(tbl.getSelectedRow() - 1, tbl);
    }

    public void moveNext(JTable tbl) {
        this.moveTo(tbl.getSelectedRow() + 1, tbl);
    }

    public void moveLast(JTable tbl) {
        this.moveTo(tbl.getRowCount() - 1, tbl);
    }

    public void moveTo(int index, JTable tbl) {
        if (index < 0) {
            this.moveLast(tbl);
        } else if (index >= tbl.getRowCount()) {
            this.moveFirst(tbl);
        } else {
            tbl.clearSelection();
            tbl.setRowSelectionInterval(index, index);
            Departments selected = items.get(index);
            this.edit(selected);
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

        jLabel1 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnLasst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(96, 200, 5));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCreate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(86, 246, 1));
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnCreate.setText("Thêm");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnLasst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/last.png"))); // NOI18N
        btnLasst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLasstActionPerformed(evt);
            }
        });

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/first.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(4, 240, 56));
        jLabel2.setText("KHOA");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("ID: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên khoa:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addGap(52, 52, 52)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLasst, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(212, 212, 212))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLasst, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        moveFirst(tblDepartment);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        movePrevious(tblDepartment);
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        moveNext(tblDepartment);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLasstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLasstActionPerformed
        // TODO add your handling code here:
        moveLast(tblDepartment);
    }//GEN-LAST:event_btnLasstActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DepartmentsJDialog dialog = new DepartmentsJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLasst;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
