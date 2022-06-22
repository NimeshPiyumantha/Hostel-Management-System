package lk.hostelManagement.pos.dao.custom;

import lk.hostelManagement.pos.dao.SuperDAO;
import lk.hostelManagement.pos.entity.Login;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface UserDAO extends SuperDAO {
    ArrayList<Login> getAllUsers() throws SQLException, ClassNotFoundException;
}
