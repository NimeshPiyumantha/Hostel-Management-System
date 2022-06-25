package lk.hostelManagement.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.hostelManagement.pos.bo.BOFactory;
import lk.hostelManagement.pos.bo.custom.PurchaseRoomBO;
import lk.hostelManagement.pos.bo.custom.ReserveBO;
import lk.hostelManagement.pos.dto.ReservationDTO;
import lk.hostelManagement.pos.dto.RoomDTO;
import lk.hostelManagement.pos.dto.StudentDTO;
import lk.hostelManagement.pos.util.NotificationController;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.ReservationTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ReserveFormController implements Initializable {
    private final PurchaseRoomBO purchaseRoomBO = (PurchaseRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PurchaseRoom);
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVE);

    public AnchorPane MainAnchorPane;
    public AnchorPane SubAnchorPane;
    public JFXComboBox<String> cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtQty;
    public JFXTextField txtMonthlyRent;
    public Label lblDate;
    public TableView<ReservationTM> tblReserve;
    public Label llbResId;
    public JFXButton btnReserve;
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtStudentName;
    public Label lblTime;
    public JFXButton btnStudent;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQTY;
    public JFXTextField txtAddress;
    public JFXTextField txtDOB;
    public JFXTextField txtGender;
    public JFXTextField txtContactNo;
    public JFXButton btnbill;
    private String RegID;

    //----------------Register Button---------------//
    public void btnReserveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        cmbStudentId.setDisable(false);
        cmbRoomId.setDisable(false);
        txtRoomType.setEditable(false);
        txtQty.setEditable(false);
        txtMonthlyRent.setEditable(false);
        txtStudentName.setEditable(false);
        txtQTY.setEditable(false);
        txtAddress.setEditable(false);
        txtDOB.setEditable(false);
        txtGender.setEditable(false);
        txtContactNo.setEditable(false);


        Double roomFee = Double.parseDouble(txtMonthlyRent.getText());
        Double advance = Double.parseDouble(txtKeyMoney.getText());
        String status = String.valueOf(roomFee - advance);

        boolean b = saveReserve(RegID, cmbStudentId.getValue(), cmbRoomId.getValue(), LocalDate.now(), txtMonthlyRent.getText(), advance, status);
        if (b) {
            NotificationController.SuccessfulTableNotification("Room Reserve", "Room Reserved in student ");
        } else {
            System.out.println(b);
            NotificationController.UnSuccessfulTableNotification("Room Reserve", "Room Reserved in student ");
        }

        RegID = generateNewOrderId(); //Generate id
        llbResId.setText(RegID);
        cmbRoomId.getSelectionModel().clearSelection();
        cmbStudentId.getSelectionModel().clearSelection();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        txtMonthlyRent.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtDOB.clear();
        txtGender.clear();
        txtContactNo.clear();

    }

    private void loadAllReserve() {
        tblReserve.getItems().clear();
        /*Get all Reserve*/
        try {
            ArrayList<ReservationDTO> allReserve = reserveBO.getAllReserve();
            for (ReservationDTO reservationDTO : allReserve) {
                tblReserve.getItems().add(new ReservationTM(reservationDTO.getRes_id(), reservationDTO.getDate(), reservationDTO.getStudent_id(), reservationDTO.getRoom_type_id(), reservationDTO.getKey_money(), reservationDTO.getAdvance(), reservationDTO.getStatus()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    //----------------Save Register---------------//
    public boolean saveReserve(String resId, String stId, String roomId, LocalDate orderDate, String keyMoney, double advance, String status) {
        try {
            return purchaseRoomBO.PurchaseRoom(new ReservationDTO(resId, orderDate, stId, roomId, keyMoney, advance, status));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        RegID = generateNewOrderId();
        llbResId.setText(RegID);
        lblDate.setText(LocalDate.now().toString());
        loadDateAndTime();//load Date and Time
        btnReserve.setDisable(true);
        loadAllRoomIds();
        loadAllStudentIds();

        //---------Student to Combo-------------//
        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisableRegisterButton();

            if (newValue != null) {
                try {
                    if (!exitStudent(newValue + "")) {
                        NotificationController.WarringError("Search Student Warning", newValue, "There is no such student associated with the ");
                    }
                    /*Search student*/
                    StudentDTO studentDTO = purchaseRoomBO.searchStudent(newValue + "");
                    txtStudentName.setText(studentDTO.getName());
                    txtAddress.setText(studentDTO.getAddress());
                    txtDOB.setText(studentDTO.getDob() + "");
                    txtGender.setText(studentDTO.getGender());
                    txtContactNo.setText(studentDTO.getContact_no());

                } catch (SQLException | ClassNotFoundException e) {
                    NotificationController.WarringError("Search Student Warning", newValue, "Failed to find the Student ");
                }
            } else {
                txtStudentName.clear();
            }
        });

        //---------Room to Combo-------------//
        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newRoomId) -> {
            btnReserve.setDisable(newRoomId == null);


            if (newRoomId != null) {
                try {
                    if (!exitRooms(newRoomId + "")) {

                    }
                    RoomDTO room = purchaseRoomBO.searchRoom(newRoomId + "");
                    txtRoomType.setText(room.getType());
                    txtQty.setText(String.valueOf(room.getQty()));
                    txtMonthlyRent.setText(room.getKey_money());

                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

            } else {
                txtMonthlyRent.clear();
                txtQty.clear();
                txtStudentName.clear();
                txtMonthlyRent.clear();
            }
        });
    }


    //---------Load Student to Combo-------------//
    private void loadAllStudentIds() {
        try {
            ArrayList<StudentDTO> all = purchaseRoomBO.getAllStudents();
            for (StudentDTO studentDTO : all) {
                cmbStudentId.getItems().add(studentDTO.getStudent_id());
            }

        } catch (SQLException e) {
            NotificationController.Warring("Student Load", "Failed to load customer ids.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //---------Load Room to Combo-------------//
    private void loadAllRoomIds() {
        try {
            ArrayList<RoomDTO> all = purchaseRoomBO.getAllRooms();
            for (RoomDTO roomDTO : all) {
                cmbRoomId.getItems().add(roomDTO.getRoom_type_id());
            }
        } catch (SQLException e) {
            NotificationController.Warring("Rooms Load", "Failed to load customer ids.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //----------Exit Student -------------//
    private boolean exitStudent(String id) throws SQLException, ClassNotFoundException {
        return purchaseRoomBO.checkStudentIsAvailable(id);
    }

    //---------- Exit Rooms -------------//
    private boolean exitRooms(String id) throws SQLException, ClassNotFoundException {
        return purchaseRoomBO.checkRoomIsAvailable(id);
    }

    //----------Enable Disable RegisterButton-------------//
    private void enableOrDisableRegisterButton() {
        btnReserve.setDisable(cmbRoomId.getSelectionModel().getSelectedItem() == null);

    }

    //---------- Load Time Date -------------//
    private void loadDateAndTime() {
        //Set Date
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //Set Time
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" +
                    currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    //----------Generate Order Id-------------//
    private String generateNewOrderId() {
        try {
            return purchaseRoomBO.generateNewReserveID();
        } catch (SQLException e) {
            NotificationController.Warring("Generate Order Id", "Failed to generate a new order id...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "REG-001";
    }
}
