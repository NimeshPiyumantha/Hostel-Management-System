package lk.hostelManagement.pos.dao.custom;

import lk.hostelManagement.pos.dao.CrudDAO;
import lk.hostelManagement.pos.entity.Reserve;

import java.sql.SQLException;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface ReserveDAO extends CrudDAO<Reserve,String> {
    String generateNewId() throws SQLException, ClassNotFoundException;
}
