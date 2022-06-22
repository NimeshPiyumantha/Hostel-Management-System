package lk.hostelManagement.pos.bo.custom;

import lk.hostelManagement.pos.bo.SuperBO;
import lk.hostelManagement.pos.dto.LoginDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface UserBO extends SuperBO {
    ArrayList<LoginDTO> getAllUsers() throws SQLException, ClassNotFoundException;
}
