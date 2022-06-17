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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.hostelManagement.pos.bo.BOFactory;
import lk.hostelManagement.pos.bo.custom.PurchaseRoomBO;
import lk.hostelManagement.pos.bo.custom.ReserveBO;
import lk.hostelManagement.pos.db.DBConnection;
import lk.hostelManagement.pos.dto.ReserveDTO;
import lk.hostelManagement.pos.dto.RoomDTO;
import lk.hostelManagement.pos.dto.StudentDTO;
import lk.hostelManagement.pos.util.NotificationController;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.ReserveTM;
import lk.hostelManagement.pos.view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
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
    public TableView<ReserveTM> tblReserve;
    public Label llbResId;
    public JFXButton btnReserve;
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtStudentName;
    public Label lblTime;
    public CheckBox checkBoxPaid;
    public JFXTextField txtSearch;
    public JFXButton btnStudent;
    private String RegID;


    public void btnReserveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        double rentFee = Double.parseDouble(txtMonthlyRent.getText());
//        purchaseRoomBO.PurchaseRoom(new ReserveDTO(llbResId.getText(), cmbStudentId.getValue(), cmbRoomId.getValue(), LocalDate.now(), rentFee));
//        NotificationController.SuccessfulTableNotification("Room Reserve", "Room Reserved in student ");

        cmbStudentId.setDisable(false);
        cmbRoomId.setDisable(false);
        txtRoomType.setDisable(false);
        txtQty.setDisable(false);
        txtMonthlyRent.setDisable(false);
        txtStudentName.setDisable(false);

        boolean b = saveReserve(RegID, cmbStudentId.getValue(), cmbRoomId.getValue(), LocalDate.now(), rentFee);
        if (b) {

            NotificationController.SuccessfulTableNotification("Room Reserve", "Room Reserved in student ");
        } else {
            System.out.println(b);
            NotificationController.UnSuccessfulTableNotification("Room Reserve", "Room Reserved in student ");
        }

        //    PrintBill(); //PrintBill
        RegID = generateNewOrderId(); //Generate id
        llbResId.setText(RegID);
        cmbRoomId.getSelectionModel().clearSelection();
        cmbStudentId.getSelectionModel().clearSelection();
        txtRoomType.clear();
        txtQty.clear();
        txtMonthlyRent.clear();
        txtStudentName.clear();
    }

    //----------------Save order---------------//
    public boolean saveReserve(String resId, String stId, String roomId, LocalDate orderDate, double rentFee) {
        try {
            return purchaseRoomBO.PurchaseRoom(new ReserveDTO(resId, stId, roomId, orderDate, rentFee));
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

        tblReserve.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_id"));
        tblReserve.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblReserve.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("room_id"));
        tblReserve.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReserve.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("key_money"));

        loadAllReserve();

        RegID = generateNewOrderId();
        llbResId.setText(RegID);
        lblDate.setText(LocalDate.now().toString());
        loadDateAndTime();//load Date and Time
        btnReserve.setDisable(true);
        loadAllRoomIds();
        loadAllStudentIds();

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisableRegisterButton();

            if (newValue != null) {
                try {
                    Connection connection = DBConnection.getDbConnection().getConnection();
                    try {
                        if (!exitStudent(newValue + "")) {
                            NotificationController.WarringError("Search Student Warning", newValue, "There is no such student associated with the ");
                        }
                        /*Search student*/
                        StudentDTO studentDTO = purchaseRoomBO.searchStudent(newValue + "");
                        txtStudentName.setText(studentDTO.getName());

                    } catch (SQLException e) {
                        NotificationController.WarringError("Search Student Warning", newValue, "Failed to find the Student ");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else {
                txtStudentName.clear();
            }
        });

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newRoomId) -> {
            btnReserve.setDisable(newRoomId == null);


            if (newRoomId != null) {
                try {
                    if (!exitRooms(newRoomId + "")) {

                    }

                    RoomDTO room = purchaseRoomBO.searchRoom(newRoomId + "");
                    txtRoomType.setText(room.getType());
                    txtQty.setText(room.getQty());
                    txtMonthlyRent.setText(String.valueOf(room.getMonthly_rent()));

                    Optional<ReserveTM> optionalReserve = tblReserve.getItems().stream().filter(detail -> detail.getRoom_id().equals(newRoomId)).findFirst();
               //     txtQty.setText((optionalReserve.isPresent() ? room.getQty() - optionalReserve.get().getQty()) : room.getQty()) + "");



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

    private void loadAllReserve() {
        tblReserve.getItems().clear();
        /*Get all Reserve*/
        try {
            ArrayList<ReserveDTO> allStudent = reserveBO.getAllReserve();
            for (ReserveDTO reserveDTO : allStudent) {
                tblReserve.getItems().add(new ReserveTM(reserveDTO.getRes_id(),reserveDTO.getStudent_id(),reserveDTO.getRoom_id(),reserveDTO.getDate(),reserveDTO.getKey_money()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

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

    private void loadAllRoomIds() {
        try {
            ArrayList<RoomDTO> all = purchaseRoomBO.getAllRooms();
            for (RoomDTO roomDTO : all) {
                cmbRoomId.getItems().add(roomDTO.getRoom_id());
            }

        } catch (SQLException e) {
            NotificationController.Warring("Rooms Load", "Failed to load customer ids.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private boolean exitStudent(String id) throws SQLException, ClassNotFoundException {
        return purchaseRoomBO.checkStudentIsAvailable(id);
    }

    private boolean exitRooms(String id) throws SQLException, ClassNotFoundException {
        return purchaseRoomBO.checkRoomIsAvailable(id);
    }

    private void enableOrDisableRegisterButton() {
        btnReserve.setDisable(cmbRoomId.getSelectionModel().getSelectedItem() == null);

    }

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
