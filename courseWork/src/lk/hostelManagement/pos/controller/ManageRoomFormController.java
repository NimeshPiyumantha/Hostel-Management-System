package lk.hostelManagement.pos.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.hostelManagement.pos.bo.custom.RoomBO;
import lk.hostelManagement.pos.dto.RoomDTO;
import lk.hostelManagement.pos.util.NotificationController;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.RoomTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ManageRoomFormController implements Initializable {
    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

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
        String id = txtRoomId.getText();
        String type = txtRoomType.getText();
        String qty = txtQty.getText();
        double rent = Double.parseDouble(txtMRent.getText());

       /* if (!id.matches("^[ROO-]{3}$")) {
            NotificationController.Warring("Room ID", "Invalid Room ID.Check STU-000 type in your entered value.");
            txtRoomId.requestFocus();
            return;
        } else if (!type.matches("^[A-z ]{3,20}$")) {
            NotificationController.Warring("Room Type", "Invalid Room Type.");
            txtRoomType.requestFocus();
            return;
        } else if (!txtMRent.getText().matches("^[A-z0-9/ ]{4,30}$")) {
            NotificationController.Warring("Room Rent", "Invalid Room Rent.");
            txtMRent.requestFocus();
            return;
        } else if (!qty.matches("^[A-z ]{3,20}$")) {
            NotificationController.Warring("Room Qty", "Invalid Room Qty");
            txtQty.requestFocus();
            return;
        }*/
        if (btnSave.getText().equalsIgnoreCase("save")) {
            /*Save Rooms*/
            try {
                if (exitRooms(id)) {
                    NotificationController.WarringError("Save Rooms Warning", id, "Already exists ");
                }
                roomBO.saveRooms(new RoomDTO(id, type, rent, qty));
                tblRoom.getItems().add(new RoomTM(id, type, rent, qty));
                NotificationController.SuccessfulTableNotification("Save", "Rooms");
            } catch (SQLException e) {
                NotificationController.WarringError("Save Rooms Warning", id + e.getMessage(), "Failed to save the Rooms ");
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            /*Update Rooms*/
            try {
                if (!exitRooms(id)) {
                    NotificationController.WarringError("Update Rooms Warning", id, "There is no such Rooms associated with the ");
                }
                //Rooms update
                roomBO.updateRooms(new RoomDTO(id, type, rent, qty));
                NotificationController.SuccessfulTableNotification("Update", "Rooms");
            } catch (SQLException e) {
                NotificationController.WarringError("Update Rooms Warning", id + e.getMessage(), "Failed to update the Rooms ");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            RoomTM selectedRoom = tblRoom.getSelectionModel().getSelectedItem();
            selectedRoom.setRoom_id(id);
            selectedRoom.setType(type);
            selectedRoom.setMonthly_rent(rent);
            selectedRoom.setQty(qty);
            tblRoom.refresh();
        }
        btnAddNew.fire();
    }


    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtRoomId.setDisable(false);
        txtRoomType.setDisable(false);
        txtMRent.setDisable(false);
        txtQty.setDisable(false);
        txtRoomId.clear();
        txtRoomType.clear();
        txtMRent.clear();
        txtQty.clear();
        txtRoomId.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblRoom.getSelectionModel().clearSelection();

    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        /*Delete Rooms*/
        String code = tblRoom.getSelectionModel().getSelectedItem().getRoom_id();
        try {
            if (!exitRooms(code)) {
                NotificationController.WarringError("Delete Rooms Warning", code, "There is no such Rooms associated with the ");
            }
            roomBO.deleteRooms(code);
            tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
            NotificationController.SuccessfulTableNotification("Delete", "Rooms");
            tblRoom.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            NotificationController.WarringError("Delete Rooms Warning", code, "Failed to delete the Rooms ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_id"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("monthly_rent"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));


        initUI();

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                //------------------------Text Filed Load----------------------//
                txtRoomId.setText(newValue.getRoom_id());
                txtRoomType.setText(newValue.getType());
                txtMRent.setText(newValue.getMonthly_rent() + "");
                txtQty.setText(newValue.getQty());

                txtRoomId.setDisable(false);
                txtRoomType.setDisable(false);
                txtMRent.setDisable(false);
                txtQty.setDisable(false);
            }
        });

        txtQty.setOnAction(event -> btnSave.fire());
        loadAllRoom();
    }

    private void loadAllRoom() {
        tblRoom.getItems().clear();
        /*Get all Room*/
        try {
            ArrayList<RoomDTO> allRoom = roomBO.getAllRooms();
            for (RoomDTO roomDTO : allRoom) {
                tblRoom.getItems().add(new RoomTM(roomDTO.getRoom_id(), roomDTO.getType(), roomDTO.getMonthly_rent(), roomDTO.getQty()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void initUI() {
        txtRoomId.clear();
        txtRoomType.clear();
        txtMRent.clear();
        txtQty.clear();

        txtRoomId.setDisable(true);
        txtRoomType.setDisable(true);
        txtMRent.setDisable(true);
        txtQty.setDisable(true);

        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private boolean exitRooms(String id) throws SQLException, ClassNotFoundException {
        return roomBO.existRoomsID(id);
    }
}
