package lk.hostelManagement.pos.bo.custom.Impl;

import lk.hostelManagement.pos.bo.custom.UserBO;
import lk.hostelManagement.pos.dao.DAOFactory;
import lk.hostelManagement.pos.dao.custom.UserDAO;
import lk.hostelManagement.pos.dto.LoginDTO;
import lk.hostelManagement.pos.entity.Login;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList<LoginDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<Login> all = userDAO.getAllUsers();
        ArrayList<LoginDTO> allOrder = new ArrayList<>();
        for (Login users : all) {
            allOrder.add(new LoginDTO(users.getUserID(), users.getPassword()));
        }
        return allOrder;

    }
}
