package lk.hostelManagement.pos.bo.custom.Impl;

import lk.hostelManagement.pos.bo.custom.ReserveBO;
import lk.hostelManagement.pos.dao.DAOFactory;
import lk.hostelManagement.pos.dao.custom.ReserveDAO;
import lk.hostelManagement.pos.dto.ReservationDTO;
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

    @Override
    public ArrayList<ReservationDTO> getAllReserve() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> all = reserveDAO.getAll();
        ArrayList<ReservationDTO> allReserve = new ArrayList<>();
        try {
            for (Reservation r : all) {
                allReserve.add(new ReservationDTO(r.getRes_id(), r.getDate(), r.getStudent_id().getStudent_id(), r.getRoom_type_id().getRoom_type_id(), r.getStatus()));
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return allReserve;

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

        Reservation reserve = new Reservation(dto.getRes_id(), dto.getDate(), student, room, dto.getStatus());
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
}
