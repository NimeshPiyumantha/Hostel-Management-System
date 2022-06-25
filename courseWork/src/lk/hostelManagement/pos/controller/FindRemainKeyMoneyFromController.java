package lk.hostelManagement.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.hostelManagement.pos.bo.BOFactory;
import lk.hostelManagement.pos.bo.custom.ReserveBO;
import lk.hostelManagement.pos.dto.ReservationDTO;
import lk.hostelManagement.pos.util.NotificationController;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.ReservationTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class FindRemainKeyMoneyFromController implements Initializable {
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVE);

    public AnchorPane MainAnchorPane;
    public TableView<ReservationTM> tblRemain;
    public JFXTextField txtSearch;
    public AnchorPane SubAnchorPane;

    //------Navigate To Home-----//
    public void navigateToHome(MouseEvent mouseEvent) throws SQLException, IOException {
        UILoader.NavigateToHome(MainAnchorPane, "AdminDashBoardForm");
    }

    //------Minimize-----//
    public void BtnMinimizeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    //------Close-----//
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

    //------Search REGISTRATION ID-----//
    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (txtSearch.getText().trim().isEmpty()) {
            NotificationController.Warring("Empty Search Id", "Please Enter Current ID.");
            loadAllReserve();
        } else {
            if (RegExit(txtSearch.getText())) {
                tblRemain.getItems().clear();
                ArrayList<ReservationDTO> arrayList = reserveBO.getAllReserveSearch(txtSearch.getText());
                if (arrayList != null) {
                    for (ReservationDTO reservationDTO : arrayList) {
                        tblRemain.getItems().add(new ReservationTM(reservationDTO.getRes_id(), reservationDTO.getDate(), reservationDTO.getStudent_id(), reservationDTO.getRoom_type_id(), reservationDTO.getKey_money(), reservationDTO.getAdvance(), reservationDTO.getStatus()));
                    }
                }
            } else {
                tblRemain.getItems().clear();
                NotificationController.Warring("Empty Data Set", "Please Enter Current ID.");
            }
        }
    }

    //------------------------Student Exit----------------------//
    private boolean StudentExit(String id) throws SQLException, ClassNotFoundException {
        return reserveBO.existStudent(id);
    }

    //------------------------Reservation Exit----------------------//
    private boolean RegExit(String id) throws SQLException, ClassNotFoundException {
        return reserveBO.existReserveID(id);
    }

    //---------Load Reserve to Table-------------//
    private void loadAllReserve() {
        tblRemain.getItems().clear();
        /*Get all Reserve*/
        try {
            ArrayList<ReservationDTO> allReserve = reserveBO.getAllReserve();
            for (ReservationDTO reservationDTO : allReserve) {
                tblRemain.getItems().add(new ReservationTM(reservationDTO.getRes_id(), reservationDTO.getDate(), reservationDTO.getStudent_id(), reservationDTO.getRoom_type_id(), reservationDTO.getKey_money(), reservationDTO.getAdvance(), reservationDTO.getStatus()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblRemain.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_id"));
        tblRemain.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblRemain.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblRemain.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblRemain.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("key_money"));
        tblRemain.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("advance"));
        tblRemain.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("status"));
        loadAllReserve();

    }
}
