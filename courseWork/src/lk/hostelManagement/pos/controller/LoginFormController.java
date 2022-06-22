package lk.hostelManagement.pos.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.hostelManagement.pos.bo.BOFactory;
import lk.hostelManagement.pos.bo.custom.UserBO;
import lk.hostelManagement.pos.dto.LoginDTO;
import lk.hostelManagement.pos.util.NotificationController;
import lk.hostelManagement.pos.util.UILoader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class LoginFormController {
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public AnchorPane context;
    public TextField txtUserName;
    public PasswordField txtPassword;
    int attempts = 0;

    public void BtnMinimizeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void BtnCloseOnAction(MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void BtnRestoreDownOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setFullScreenExitHint("");
        stage.setResizable(true);
    }

    public void goToCancelPage(ActionEvent actionEvent) {
        txtUserName.setText("");
        txtPassword.setText("");
    }

    public void btnLoginPage(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {

        UILoader.LoginOnAction(context, "AdminDashBoardForm");
        NotificationController.LoginSuccessfulNotification("Admin");

      /*  ArrayList<LoginDTO> loginDTOS = userBO.getAllUsers();
        attempts++;
        loginDTOS.forEach(e -> {
            if (attempts <= 3) {
                if (e.getUserID().equals(txtUserName.getText()) && e.getPassword().equals(txtPassword.getText())) {
                    try {
                        UILoader.LoginOnAction(context, "AdminDashBoardForm");
                    } catch (IOException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    NotificationController.LoginSuccessfulNotification("Admin");
                } else {
                    //try again
                    NotificationController.LoginUnSuccessfulNotification("Admin");
                }
            } else {
                txtUserName.setEditable(false);
                txtPassword.setEditable(false);
                NotificationController.LoginUnSuccessfulNotification("Account is Temporarily Disabled or You Did not Sign in Correctly.");
            }
        });
*/
    }
}
