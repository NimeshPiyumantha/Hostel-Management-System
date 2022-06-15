package lk.hostelManagement.pos.bo.custom;

import lk.hostelManagement.pos.bo.SuperBO;
import lk.hostelManagement.pos.dto.ReserveDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface ReserveBO extends SuperBO {

    ArrayList<ReserveDTO> getAllReserve() throws SQLException, ClassNotFoundException;

    boolean saveReserve(ReserveDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateReserve(ReserveDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteReserve(String id) throws SQLException, ClassNotFoundException;


    boolean existReserveID(String id) throws SQLException, ClassNotFoundException;

}
