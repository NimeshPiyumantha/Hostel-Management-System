package lk.hostelManagement.pos.bo.custom.Impl;

import lk.hostelManagement.pos.bo.custom.PurchaseRoomBO;
import lk.hostelManagement.pos.dao.DAOFactory;
import lk.hostelManagement.pos.dao.custom.ReserveDAO;
import lk.hostelManagement.pos.dao.custom.RoomDAO;
import lk.hostelManagement.pos.dao.custom.StudentDAO;
import lk.hostelManagement.pos.dto.ReservationDTO;
import lk.hostelManagement.pos.dto.RoomDTO;
import lk.hostelManagement.pos.dto.StudentDTO;
import lk.hostelManagement.pos.entity.Reservation;
import lk.hostelManagement.pos.entity.Room;
import lk.hostelManagement.pos.entity.Student;
import lk.hostelManagement.pos.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/

public class PurchaseRoomBOImpl implements PurchaseRoomBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVE);

    @Override
    public boolean PurchaseRoom(ReservationDTO dto) throws SQLException, ClassNotFoundException {
/**
 Connection connection = DBConnection.getDbConnection().getConnection();
 connection.setAutoCommit(false);
 boolean save = reserveDAO.save(new Reservation(dto.getRes_id(), dto.getDate(), dto.getStudent_id(), dto.getRoom_type_id(), dto.getStatus()));

 if (!save) {
 connection.rollback();
 connection.setAutoCommit(true);
 return false;
 }

 //        //Search & Update Item
 //        int x=1;
 //        RoomDTO room = searchRoom(dto.getRoom_id());
 //        room.setQty(room.getQty()-);
 //
 //        //update item
 //        boolean update = roomDAO.update(new Room(room.getRoom_id(),room.getType(),room.getMonthly_rent(),room.getQty()));
 //
 //        if (!update) {
 //            connection.rollback();
 //            connection.setAutoCommit(true);
 //            return false;
 //        }
 connection.commit();
 connection.setAutoCommit(true);
 return true;
 */

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, dto.getStudent_id());
        Room room = session.get(Room.class, dto.getRoom_type_id());

        Reservation reserve = new Reservation(dto.getRes_id(), dto.getDate(), student, room, dto.getKey_money(), dto.getAdvance(), dto.getStatus());
        session.save(reserve);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student ent = studentDAO.search(id);
        return new StudentDTO(ent.getStudent_id(), ent.getName(), ent.getAddress(), ent.getContact_no(), ent.getDob(), ent.getGender());
    }

    @Override
    public RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException {
        Room ent = roomDAO.search(id);
        return new RoomDTO(ent.getRoom_type_id(), ent.getType(), ent.getKey_money(), ent.getQty());
    }

    @Override
    public boolean checkRoomIsAvailable(String code) throws SQLException, ClassNotFoundException {
        return roomDAO.exist(code);
    }

    @Override
    public boolean checkStudentIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.exist(id);
    }

    @Override
    public String generateNewReserveID() throws SQLException, ClassNotFoundException {
        return reserveDAO.generateNewId();
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(), student.getContact_no(), student.getDob(), student.getGender()));
        }
        return allStudent;
    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRoom = new ArrayList<>();
        for (Room room : all) {
            allRoom.add(new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
        }
        return allRoom;
    }
}
