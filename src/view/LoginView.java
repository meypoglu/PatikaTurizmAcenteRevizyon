package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private final UserManager userManager;
    public LoginView(){
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(400,400);
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username,this.fld_password};
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMessage("fill");
            }else {
               User loginUser = this.userManager.findByLogin(this.fld_username.getText(),this.fld_password.getText());
               if (loginUser == null){
                   Helper.showMessage("notFound");
               }else {String role = loginUser.getRole();
                   if ("admin".equalsIgnoreCase(role)) {
                       AdminView adminView = new AdminView(loginUser);
                       adminView.setVisible(true);
                       dispose();
                   } else if ("employee".equalsIgnoreCase(role)) {
                       AdminView adminView = new AdminView(loginUser);
                       adminView.setVisible(true);
                       adminView.hideUserManagement();
                       dispose();
                   } else {
                       Helper.showMessage("Invalid role");
                   }
               }
            }

        });
    }
}
