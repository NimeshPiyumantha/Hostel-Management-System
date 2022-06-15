package lk.hostelManagement.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.hostelManagement.pos.util.UILoader;
import lk.hostelManagement.pos.view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ManageStudentFormController {

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


    public void btnSave_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
    }

    public void navigateToHome(MouseEvent mouseEvent) throws SQLException, IOException {
        UILoader.NavigateToHome(MainAnchorePane, "ReserveForm");
    }
}
