package lk.hostelManagement.pos.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.hostelManagement.pos.util.NotificationController;
import lk.hostelManagement.pos.util.UILoader;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class LoginFormController {
    public AnchorPane context;
    public TextField txtUserName;
    public PasswordField txtPassword;

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

    public void btnLoginPage(ActionEvent actionEvent) throws SQLException, IOException {
        UILoader.LoginOnAction(context, "AdminDashBoardForm");
        NotificationController.LoginSuccessfulNotification("Admin");

    }
}
