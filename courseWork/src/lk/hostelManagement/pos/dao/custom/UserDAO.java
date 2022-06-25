package lk.hostelManagement.pos.dao.custom;

import lk.hostelManagement.pos.dao.CrudDAO;
import lk.hostelManagement.pos.entity.Login;
import lk.hostelManagement.pos.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface UserDAO extends CrudDAO <Login, String> {
    ArrayList<Login> getAllUsers() throws SQLException, ClassNotFoundException;
}
