package frontend.views.portal.panels;

import javax.swing.*;

import backend.GUIFascade;
import frontend.views.portal.Portal;

import java.awt.*;

public class AddPanel extends JPanel {

    public AddPanel(Portal parent, int userID) {
        GUIFascade fascade = parent.getFascade();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Page Title
        JLabel titleLabel = new JLabel("Add New Records");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 18f));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                64));

        add(titleLabel);

    }
}
