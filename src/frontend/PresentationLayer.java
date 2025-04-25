/*
 * File: Presentation Layer
 * Group: 2
 * Project: Group Project
 * Class: ISTE-330
 */

package frontend;

import backend.DataLayer;
import backend.GUIFascade;
import frontend.views.Login;
import utility.Encryption;

import java.io.File;
import java.awt.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

public class PresentationLayer {
    public static void main(String[] args) {
        Color backgroundColor = Color.decode("#202225");

        try {
            // Initialize the GUI Helpers.
            File fontFile = new File(
                    PresentationLayer.class.getResource("/frontend/assets/fonts/DMSans.ttf").getPath());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(14f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("defaultFont", font);
            UIManager.put("OptionPane.background", backgroundColor);
            UIManager.put("Panel.background", backgroundColor);

            // Initialize the DataLayer and login screen.
            DataLayer dataLayer = new DataLayer();
            dataLayer.connect("root", "student", "330_project_research");
            GUIFascade fascade = new GUIFascade(dataLayer);
            Login login = new Login(fascade);
            login.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
