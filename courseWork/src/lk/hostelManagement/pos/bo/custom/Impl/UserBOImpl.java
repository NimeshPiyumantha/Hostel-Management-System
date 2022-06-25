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
        ArrayList<LoginDTO> allUSer = new ArrayList<>();
        for (Login users : all) {
            allUSer.add(new LoginDTO(users.getUserID(), users.getName(), users.getAddress(), users.getContact_no(), users.getPassword(), users.getGender()));
        }
        return allUSer;
    }

    @Override
    public ArrayList<LoginDTO> searchAllUser(String id) throws SQLException, ClassNotFoundException {
        Login all = userDAO.search(id);
        ArrayList<LoginDTO> allUsersSearch = new ArrayList<>();
        allUsersSearch.add(new LoginDTO(all.getUserID(), all.getName(), all.getAddress(), all.getContact_no(), all.getPassword(), all.getGender()));
        return allUsersSearch;
    }

    @Override
    public boolean saveUser(LoginDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new Login(dto.getUserID(), dto.getName(), dto.getAddress(), dto.getContact_no(), dto.getPassword(), dto.getGender()));
    }

    @Override
    public boolean updateUser(LoginDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new Login(dto.getUserID(), dto.getName(), dto.getAddress(), dto.getContact_no(), dto.getPassword(), dto.getGender()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public boolean existUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.exist(id);
    }
}
