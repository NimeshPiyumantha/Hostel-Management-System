package lk.hostelManagement.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.hostelManagement.pos.bo.BOFactory;
import lk.hostelManagement.pos.bo.custom.StudentBO;
import lk.hostelManagement.pos.dto.StudentDTO;
import lk.hostelManagement.pos.util.NotificationController;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ManageStudentFormController implements Initializable {
    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public AnchorPane MainAnchorePane;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public JFXTextField txtDOB;
    public JFXTextField txtGender;
    public JFXButton btnSave;
    public TableView<StudentTM> tblStudent;
    public JFXButton btnDelete;
    public JFXButton btnAddNew;


    public void btnSave_OnAction(ActionEvent actionEvent) throws ParseException {
        String id = txtId.getText();
        String name = txtName.getText();
        String cNO = txtContactNo.getText();
        String address = txtAddress.getText();
        String dob = txtDOB.getText();
        String gender = txtGender.getText();


        if (!id.matches("^(ST-[0-9]{3,4})$")) {
            NotificationController.Warring("Student Id", "Invalid Student Id.Check STU-000 type in your entered value.");
            txtId.requestFocus();
            return;
        } else if (!name.matches("^([A-Z a-z]{5,40})$")) {
            NotificationController.Warring("Student Name", "Student Name.");
            txtName.requestFocus();
            return;
        } else if (!cNO.matches("^(07(0|1|2|4|5|6|7|8)[0-9]{7})$")) {
            NotificationController.Warring("Contact Number", "Invalid Student Contact Number.");
            txtContactNo.requestFocus();
            return;
        } else if (!address.matches("^([A-Za-z]{4,10})$")) {
            NotificationController.Warring("Address", "Invalid Student Address.");
            txtAddress.requestFocus();
            return;
        }else if (!dob.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
            NotificationController.Warring("Date of Birth", "Invalid Student Date of Birth.");
            txtDOB.requestFocus();
            return;
        } else if (!gender.matches("^([A-Z a-z]{4,20})$")) {
            NotificationController.Warring("Gender", "Invalid Student Gender.");
            txtGender.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            /*Save Student*/
            try {
                if (exitStudent(id)) {
                    NotificationController.WarringError("Save Student Warning", id, "Already exists ");
                }
                studentBO.saveStudent(new StudentDTO(id, name, address, cNO, dob, gender));
                tblStudent.getItems().add(new StudentTM(id, name, address, cNO, dob, gender));
                NotificationController.SuccessfulTableNotification("Save", "Student");
            } catch (SQLException e) {
                NotificationController.WarringError("Save Student Warning", id + e.getMessage(), "Failed to save the Student ");
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            /*Update Rooms*/
            try {
                if (!exitStudent(id)) {
                    NotificationController.WarringError("Update Student Warning", id, "There is no such Student associated with the ");
                }
                //Rooms update
                studentBO.updateStudent(new StudentDTO(id, name, address, cNO, dob, gender));
                NotificationController.SuccessfulTableNotification("Update", "Rooms");
            } catch (SQLException e) {
                NotificationController.WarringError("Update Student Warning", id + e.getMessage(), "Failed to update the Student ");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            selectedItem.setStudent_id(id);
            selectedItem.setName(name);
            selectedItem.setAddress(address);
            selectedItem.setContact_no(cNO);
            selectedItem.setDob(dob);
            selectedItem.setGender(gender);
            tblStudent.refresh();
        }
        btnAddNew.fire();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        /*Delete Student*/
        String code = tblStudent.getSelectionModel().getSelectedItem().getStudent_id();
        try {
            if (!exitStudent(code)) {
                NotificationController.WarringError("Delete Student Warning", code, "There is no such Student associated with the ");
            }
            studentBO.deleteStudent(code);
            tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
            NotificationController.SuccessfulTableNotification("Delete", "Student");
            tblStudent.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            NotificationController.WarringError("Delete Student Warning", code, "Failed to delete the Student ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtContactNo.setDisable(false);
        txtAddress.setDisable(false);
        txtDOB.setDisable(false);
        txtGender.setDisable(false);

        txtId.clear();
        txtName.clear();
        txtContactNo.clear();
        txtAddress.clear();
        txtDOB.clear();
        txtGender.clear();
        txtId.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStudent.getSelectionModel().clearSelection();
    }

    public void navigateToHome(MouseEvent mouseEvent) throws SQLException, IOException {
        UILoader.NavigateToHome(MainAnchorePane, "ReserveForm");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        initUI();

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                //------------------------Text Filed Load----------------------//
                txtId.setText(newValue.getStudent_id());
                txtName.setText(newValue.getName());
                txtContactNo.setText(newValue.getContact_no());
                txtAddress.setText(newValue.getAddress());
                txtDOB.setText(newValue.getDob() + "");
                txtGender.setText(newValue.getGender());

                txtId.setDisable(false);
                txtName.setDisable(false);
                txtContactNo.setDisable(false);
                txtAddress.setDisable(false);
                txtDOB.setDisable(false);
                txtGender.setDisable(false);
            }
        });

        txtGender.setOnAction(event -> btnSave.fire());
        loadAllStudent();
    }

    private void loadAllStudent() {
        tblStudent.getItems().clear();
        /*Get all Student*/
        try {
            ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
            for (StudentDTO studentDTO : allStudent) {
                tblStudent.getItems().add(new StudentTM(studentDTO.getStudent_id(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact_no(), studentDTO.getDob(), studentDTO.getGender()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllRoom() {
        tblStudent.getItems().clear();
        /*Get all Student*/
        try {
            ArrayList<StudentDTO> allRoom = studentBO.getAllStudent();
            for (StudentDTO studentDTO : allRoom) {
                tblStudent.getItems().add(new StudentTM(studentDTO.getStudent_id(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact_no(), studentDTO.getDob(), studentDTO.getGender()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtContactNo.clear();
        txtAddress.clear();
        txtDOB.clear();
        txtGender.clear();

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtContactNo.setDisable(true);
        txtAddress.setDisable(true);
        txtDOB.setDisable(true);
        txtGender.setDisable(true);

        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private boolean exitStudent(String id) throws SQLException, ClassNotFoundException {
        return studentBO.existStudentID(id);
    }
}
