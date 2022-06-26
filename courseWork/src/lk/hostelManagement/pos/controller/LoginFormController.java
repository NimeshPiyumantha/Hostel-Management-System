package lk.hostelManagement.pos.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public Label lblHide;
    int attempts = 0;

    //------Minimize-----//
    public void BtnMinimizeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    //------Cancel-----//
    public void BtnCloseOnAction(MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }

    //------Restore-----//
    public void BtnRestoreDownOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setFullScreenExitHint("");
        stage.setResizable(true);
    }

    //------Cancel-----//
    public void goToCancelPage(ActionEvent actionEvent) {
        txtUserName.setText("");
        txtPassword.setText("");
    }

    //------Login OnAction-----//
    public void btnLoginPage(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {

        ArrayList<LoginDTO> loginDTOS = userBO.getAllUsers();
        attempts++;
        loginDTOS.forEach(e -> {
            if (attempts <= 3) {
                if (e.getUserID().equals(txtUserName.getText()) && e.getPassword().equals(txtPassword.getText())) {
                    try {
                        UILoader.LoginOnAction(context, "AdminDashBoardForm");
                        NotificationController.LoginSuccessfulNotification("Admin");
                    } catch (IOException | SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    //try again
                    //     NotificationController.LoginUnSuccessfulNotification("Admin");
                }
            } else {
                txtUserName.setEditable(false);
                txtPassword.setEditable(false);
                NotificationController.LoginUnSuccessfulNotification("Account is Temporarily Disabled or You Did not Sign in Correctly.");
            }
        });
    }

    //------Show Password-----//
    public void showPasswordOnMousePressed(MouseEvent mouseEvent) {
        Image img = new Image("/lk/hostelManagement/pos/view/assets/images/show.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        lblHide.setGraphic(view);

        txtPassword.setPromptText(txtPassword.getText());
        txtPassword.setText("");
        txtPassword.setDisable(true);
        txtPassword.requestFocus();
    }

    //------Hide Password-----//
    public void hidePasswordOnMousePressed(MouseEvent mouseEvent) {
        Image img = new Image("/lk/hostelManagement/pos/view/assets/images/hide.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        lblHide.setGraphic(view);

        txtPassword.setText(txtPassword.getPromptText());
        txtPassword.setPromptText("");
        txtPassword.setDisable(false);
    }
}
