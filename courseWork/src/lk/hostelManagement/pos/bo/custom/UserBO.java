package lk.hostelManagement.pos.bo.custom;

import lk.hostelManagement.pos.bo.SuperBO;
import lk.hostelManagement.pos.dto.LoginDTO;
import lk.hostelManagement.pos.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface UserBO extends SuperBO {
    ArrayList<LoginDTO> getAllUsers() throws SQLException, ClassNotFoundException;

    ArrayList<LoginDTO> searchAllUser(String id) throws SQLException, ClassNotFoundException;

    boolean saveUser(LoginDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateUser(LoginDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;

    boolean existUser(String id) throws SQLException, ClassNotFoundException;
}
