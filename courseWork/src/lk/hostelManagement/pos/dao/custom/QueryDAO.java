package lk.hostelManagement.pos.dao.custom;

import lk.hostelManagement.pos.dao.SuperDAO;
import lk.hostelManagement.pos.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface QueryDAO extends SuperDAO {
    ArrayList<Custom> getDetailsInKRemainKeyMoney() throws SQLException, ClassNotFoundException;

    ArrayList<Custom> getDetailsInKRemainKeyMoneySearch(String id) throws SQLException, ClassNotFoundException;
}
