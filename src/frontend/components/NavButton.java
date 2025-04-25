package frontend.components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NavButton extends JButton {
    private String iconURL;
    private boolean isSelected = false;

    public NavButton(String iconURL, String tooltip) {
        this.iconURL = iconURL;
        setToolTipText(tooltip);
        setBackground(null);
        setBorderPainted(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(48, 48));
        setIcon(new ImageIcon(getClass().getResource(this.iconURL)));
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        if (isSelected) {
            setIcon(new ImageIcon(getClass().getResource(this.iconURL.replace(".png", "_active.png"))));
        } else {
            setIcon(new ImageIcon(getClass().getResource(this.iconURL)));
        }
    }
}
