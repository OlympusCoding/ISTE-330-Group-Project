/*
 * File: Presentation Layer
 * Group: 2
 * Project: Group Project
 * Class: ISTE-330
 */

package frontend;

import backend.DataLayer;
import frontend.views.Login;
import com.formdev.flatlaf.FlatDarkLaf;

public class PresentationLayer {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        DataLayer dataLayer = new DataLayer();
        Login login = new Login();
        login.setVisible(true);
    }
}
