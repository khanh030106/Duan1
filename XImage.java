/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author ADMIN
 */
public class XImage {

    public static boolean saveImage(File file) {
        File dir = new File("Images");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void setImageToForm(String image, JLabel label) {
        if (image == null || image.isBlank()) {
            label.setIcon(null); 
            label.setToolTipText(null);
            return;
        }

        File file = new File("Images", image);
        try {
            Image img = ImageIO.read(file);
            label.setIcon(new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
            label.setToolTipText(image);
        } catch (Exception e) {
            try {
                file = new File("Images", "no_image.png");
                Image img = ImageIO.read(file);
                label.setIcon(new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
                label.setToolTipText("no_image.png");
            } catch (Exception e1) {
            }
        }
    }

    public static void selectImage(JLabel lbl, String path){
        JFileChooser choosenFile = new JFileChooser(path);
        if (choosenFile.showDialog(new JDialog(), "Chọn ảnh") == JFileChooser.APPROVE_OPTION) {
            File file = choosenFile.getSelectedFile();
            try {
                Image img = ImageIO.read(file);
                lbl.setIcon(new ImageIcon(img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH)));
                XImage.saveImage(file);
                XImage.setImageToForm(file.getName(), lbl);
                lbl.setToolTipText(file.getName());
                lbl.setText(null);
            } catch (Exception e) {
            }
        }
    }
}
