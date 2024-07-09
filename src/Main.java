import business.UserManager;
import com.formdev.flatlaf.FlatLightLaf;
import core.Helper;
import view.AdminView;
import view.LoginView;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            UIManager.put("Button.arc", 8);
            UIManager.put("Button.background", new Color(0x9724D7));
            UIManager.put("Button.hoverBackground", new Color(0x5C1385));
            UIManager.put("Button.foreground", Color.WHITE);

            UIManager.put("Table.alternateRowColor", new Color(0xf2f2f2));
            UIManager.put("Table.gridColor", new Color(0xdee2e6));
            UIManager.put("TableHeader.background", new Color(0x9724D7));
            UIManager.put("TableHeader.foreground", Color.WHITE); //

            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("TextField.foreground", new Color(0x343a40));
            UIManager.put("TextField.focusColor", new Color(0x0D539E));

            FontUIResource font = new FontUIResource("Open Sans", Font.PLAIN, 14);
            UIManager.put("defaultFont", font);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        LoginView loginView = new LoginView();
        UserManager userManager = new UserManager();
        AdminView adminView = new AdminView(userManager.findByLogin("admin","123"));
    }
}