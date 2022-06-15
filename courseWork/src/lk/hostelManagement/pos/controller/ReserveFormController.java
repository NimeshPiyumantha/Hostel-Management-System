package lk.hostelManagement.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.hostelManagement.pos.dto.RoomDTO;
import lk.hostelManagement.pos.dto.StudentDTO;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.ReserveTM;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ReserveFormController {

    public AnchorPane MainAnchorPane;
    public AnchorPane SubAnchorPane;
    public JFXComboBox<RoomDTO> cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtQty;
    public JFXTextField txtMonthlyRent;
    public Label lblDate;
    public TableView<ReserveTM> tblReserve;
    public Label llbResId;
    public JFXButton btnReserve;
    public JFXComboBox<StudentDTO> cmbStudentId;
    public JFXTextField txtStudentName;
    public Label lblTime;
    public CheckBox checkBoxPaid;
    public JFXTextField txtSearch;
    public JFXButton btnStudent;


    public void btnReserveOnAction(ActionEvent actionEvent) {
    }

    public void navigateToHome(MouseEvent mouseEvent) throws SQLException, IOException {
        UILoader.NavigateToHome(MainAnchorPane, "AdminDashBoardForm");
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        UILoader.loadUiDashBoard(SubAnchorPane, "ManageStudentForm");
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
}
