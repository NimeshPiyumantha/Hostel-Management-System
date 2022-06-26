package lk.hostelManagement.pos.bo.custom.Impl;

import lk.hostelManagement.pos.bo.custom.ReserveBO;
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
public class ReserveBOImpl implements ReserveBO {
    private final ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);


    @Override
    public ArrayList<ReservationDTO> getAllReserve() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> all = reserveDAO.getAll();
        ArrayList<ReservationDTO> allReserve = new ArrayList<>();
        for (Reservation r : all) {
            allReserve.add(new ReservationDTO(r.getRes_id(), r.getDate(), r.getStudent_id().getStudent_id(), r.getRoom_type_id().getRoom_type_id(), r.getKey_money(), r.getAdvance(), r.getStatus()));
        }
        return allReserve;
    }

    @Override
    public ArrayList<ReservationDTO> getAllReserveSearch(String id) throws SQLException, ClassNotFoundException {
        Reservation all = reserveDAO.search(id);
        ArrayList<ReservationDTO> allSearchReserve = new ArrayList<>();
        allSearchReserve.add(new ReservationDTO(all.getRes_id(), all.getDate(), all.getStudent_id().getStudent_id(), all.getRoom_type_id().getRoom_type_id(), all.getKey_money(), all.getAdvance(), all.getStatus()));
        return allSearchReserve;
    }

    @Override
    public boolean updateReserve(ReservationDTO dto) throws SQLException, ClassNotFoundException {
/**
 return reserveDAO.update(new Reservation(dto.getRes_id(), dto.getDate(), dto.getStudent_id(), dto.getRoom_type_id(), dto.getStatus()));
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, dto.getStudent_id());
        Room room = session.get(Room.class, dto.getRoom_type_id());

        Reservation reserve = new Reservation(dto.getRes_id(), dto.getDate(), student, room, dto.getKey_money(), dto.getAdvance(), dto.getStatus());
        session.update(reserve);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteReserve(String id) throws SQLException, ClassNotFoundException {
        return reserveDAO.delete(id);

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return reserveDAO.generateNewId();
    }

    @Override
    public boolean existReserveID(String id) throws SQLException, ClassNotFoundException {
        return reserveDAO.exist(id);
    }

    @Override
    public boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return reserveDAO.existStudent(id);
    }

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

        room.setQty(room.getQty() - 1);
        session.update(room);

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
