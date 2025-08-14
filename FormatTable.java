/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class FormatTable {
    public static DefaultTableCellRenderer RendererColumn(JTable tbl, int index) {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        
        tbl.getColumnModel().getColumn(index).setCellRenderer(render); 
        return render;
    }
    public static DefaultTableCellRenderer setColumnSize(JTable tbl,int index, int with) {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        
        tbl.getColumnModel().getColumn(index).setPreferredWidth(with);
        return render;
    }
}
