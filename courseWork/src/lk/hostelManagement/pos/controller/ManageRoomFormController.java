package lk.hostelManagement.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.RoomTM;

import java.io.IOException;
import java.sql.SQLException;


/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ManageRoomFormController {


    public AnchorPane RoomContext;
    public JFXTextField txtRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtMRent;
    public JFXTextField txtQty;
    public TableView<RoomTM> tblRoom;
    public JFXButton btnSave;
    public JFXButton btnAddNew;
    public JFXButton btnDelete;


    public void btnSave_OnAction(ActionEvent actionEvent) {
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

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

    public void navigateToHome(MouseEvent mouseEvent) throws SQLException, IOException {
        UILoader.NavigateToHome(RoomContext, "AdminDashBoardForm");
    }
}
