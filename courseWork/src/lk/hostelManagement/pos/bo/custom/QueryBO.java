package lk.hostelManagement.pos.bo.custom;

import lk.hostelManagement.pos.bo.SuperBO;
import lk.hostelManagement.pos.dto.CustomDTO;
import lk.hostelManagement.pos.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface QueryBO extends SuperBO {
    ArrayList<CustomDTO> getDetailsInKRemainKeyMoney() throws SQLException, ClassNotFoundException;

    ArrayList<CustomDTO> getDetailsInKRemainKeyMoneySearch(String id) throws SQLException, ClassNotFoundException;

}
