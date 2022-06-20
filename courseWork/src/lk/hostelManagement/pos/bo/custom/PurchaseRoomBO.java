package lk.hostelManagement.pos.bo.custom;

import lk.hostelManagement.pos.bo.SuperBO;
import lk.hostelManagement.pos.dto.ReservationDTO;
import lk.hostelManagement.pos.dto.RoomDTO;
import lk.hostelManagement.pos.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface PurchaseRoomBO extends SuperBO {
    boolean PurchaseRoom(ReservationDTO dto) throws SQLException, ClassNotFoundException;

    StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;

    RoomDTO searchRoom(String code) throws SQLException, ClassNotFoundException;

    boolean checkRoomIsAvailable(String code) throws SQLException, ClassNotFoundException;

    boolean checkStudentIsAvailable(String id) throws SQLException, ClassNotFoundException;

    String generateNewReserveID() throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException;

    ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException;
}
