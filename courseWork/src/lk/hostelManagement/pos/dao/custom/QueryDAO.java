package lk.hostelManagement.pos.dao.custom;

import lk.hostelManagement.pos.dao.SuperDAO;
import lk.hostelManagement.pos.dto.CustomDTO;
import lk.hostelManagement.pos.dto.ReservationDTO;
import lk.hostelManagement.pos.entity.CustomEntity;
import lk.hostelManagement.pos.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> getDetailsInKRemainKeyMoney() throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> getDetailsInKRemainKeyMoneySearch(String id) throws SQLException, ClassNotFoundException;

}
