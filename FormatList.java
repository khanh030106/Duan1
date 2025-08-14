/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 *
 * @author ADMIN
 */
public class FormatList extends DefaultListCellRenderer {
    private final Map<String, Icon> iconMap;

    public FormatList(Map<String, Icon> iconMap) {
        this.iconMap = iconMap;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        String title = (String) value;
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, title, index, isSelected, cellHasFocus);

        // Gán icon tương ứng nếu có
        label.setIcon(iconMap.getOrDefault(title, null));

        // Style: center text, font, màu pastel
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(isSelected ? new Color(255, 255, 255) : new Color(102, 102, 255));
        label.setBackground(isSelected ? new Color(102, 102, 255) : new Color(250, 250, 240));
        label.setOpaque(true);

        // Viền dưới + padding
        label.setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
            new EmptyBorder(10, 15, 11, 10)
        ));

        return label;
    }

}
