/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;
/**
 *
 * @author ADMIN
 */
public class PlaceHolder extends JLabel implements FocusListener, DocumentListener {
 public enum Show {
        ALWAYS, FOCUS_GAINED, FOCUS_LOST;
    }

    private JTextComponent component;
    private Document document;
    private Show show;
    private boolean showPromptOnce;
    private int focusLost;

    public PlaceHolder(String text, JTextComponent component) {
        this(text, component, Show.ALWAYS);
    }

    public PlaceHolder(String text, JTextComponent component, Show show) {
        this.component = component;
        setShow(show);
        document = component.getDocument();

        setText(text);
        setFont(component.getFont());
        setForeground(component.getForeground());
        setBorder(new EmptyBorder(component.getInsets()));
        setHorizontalAlignment(JLabel.LEADING);

        component.addFocusListener(this);
        document.addDocumentListener(this);

        component.setLayout(new BorderLayout());
        component.add(this);
        checkForPrompt();
    }

    public void changeAlpha(float alpha) {
        changeAlpha((int) (alpha * 255));
    }

    public void changeAlpha(int alpha) {
        alpha = Math.max(0, Math.min(255, alpha));
        Color fg = getForeground();
        Color withAlpha = new Color(fg.getRed(), fg.getGreen(), fg.getBlue(), alpha);
        super.setForeground(withAlpha);
    }

    public void changeStyle(int style) {
        setFont(getFont().deriveFont(style));
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public boolean getShowPromptOnce() {
        return showPromptOnce;
    }

    public void setShowPromptOnce(boolean showPromptOnce) {
        this.showPromptOnce = showPromptOnce;
    }

    private void checkForPrompt() {
        if (document.getLength() > 0 || (showPromptOnce && focusLost > 0)) {
            setVisible(false);
            return;
        }

        if (component.hasFocus()) {
            setVisible(show == Show.ALWAYS || show == Show.FOCUS_GAINED);
        } else {
            setVisible(show == Show.ALWAYS || show == Show.FOCUS_LOST);
        }
    }

    public void focusGained(FocusEvent e) {
        checkForPrompt();
    }

    public void focusLost(FocusEvent e) {
        focusLost++;
        checkForPrompt();
    }

    public void insertUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    public void removeUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    public void changedUpdate(DocumentEvent e) {}
}
