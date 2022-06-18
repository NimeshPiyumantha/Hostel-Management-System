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
import lk.hostelManagement.pos.bo.custom.QueryBO;
import lk.hostelManagement.pos.bo.custom.StudentBO;
import lk.hostelManagement.pos.dto.CustomDTO;
import lk.hostelManagement.pos.util.NotificationController;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.CustomTM;

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
    private final QueryBO customBo = (QueryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Custom);
    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);


    public AnchorPane MainAnchorPane;
    public TableView<CustomTM> tblRemain;
    public JFXTextField txtSearch;

    public void navigateToHome(MouseEvent mouseEvent) throws SQLException, IOException {
        UILoader.NavigateToHome(MainAnchorPane, "AdminDashBoardForm");
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

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtSearch.getText().trim().isEmpty()) {
            NotificationController.Warring("Empty Search Id", "Please Enter Current ID.");
            loadAllDetails();
        } else {
            if (StudentExit(txtSearch.getText())) {
                tblRemain.getItems().clear();
                ArrayList<CustomDTO> arrayList = customBo.getDetailsInKRemainKeyMoneySearch(txtSearch.getText());
                if (arrayList != null) {
                    for (CustomDTO custom : arrayList) {
                        tblRemain.getItems().add(new CustomTM(custom.getRes_id(), custom.getStudent_id(), custom.getRoom_id(), custom.getMonthly_rent(), custom.getDate(), custom.getKey_money(), custom.getArrest_money()));
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
        return studentBO.existStudentID(id);
    }

    private void loadAllDetails() {
        tblRemain.getItems().clear();
        /*Get all Details*/
        try {
            ArrayList<CustomDTO> allDetails = customBo.getDetailsInKRemainKeyMoney();
            for (CustomDTO custom : allDetails) {
                tblRemain.getItems().add(new CustomTM(custom.getRes_id(), custom.getStudent_id(), custom.getRoom_id(), custom.getMonthly_rent(), custom.getDate(), custom.getKey_money(), custom.getArrest_money()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblRemain.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_id"));
        tblRemain.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblRemain.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("room_id"));
        tblRemain.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblRemain.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("monthly_rent"));
        tblRemain.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("key_money"));
        tblRemain.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("arrest_money"));

        loadAllDetails();
    }
}
