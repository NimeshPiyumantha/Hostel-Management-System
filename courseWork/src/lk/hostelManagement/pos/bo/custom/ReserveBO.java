package lk.hostelManagement.pos.bo.custom;

import lk.hostelManagement.pos.bo.SuperBO;
import lk.hostelManagement.pos.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface ReserveBO extends SuperBO {

    ArrayList<ReservationDTO> getAllReserve() throws SQLException, ClassNotFoundException;

    ArrayList<ReservationDTO> getAllReserveSearch(String id) throws SQLException, ClassNotFoundException;

    boolean updateReserve(ReservationDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteReserve(String id) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    boolean existReserveID(String id) throws SQLException, ClassNotFoundException;

    boolean existStudent(String id) throws SQLException, ClassNotFoundException;

}
