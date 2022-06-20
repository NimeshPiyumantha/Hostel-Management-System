package lk.hostelManagement.pos.bo.custom.Impl;

import lk.hostelManagement.pos.bo.custom.ReserveBO;
import lk.hostelManagement.pos.dao.DAOFactory;
import lk.hostelManagement.pos.dao.custom.ReserveDAO;
import lk.hostelManagement.pos.dto.ReservationDTO;
import lk.hostelManagement.pos.entity.Reservation;

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
        for (Reservation reserve : all) {
            allReserve.add(new ReservationDTO(reserve.getRes_id(), reserve.getDate(), reserve.getStudent_id(), reserve.getRoom_type_id(), reserve.getStatus()));
        }
        return allReserve;
    }

    @Override
    public boolean saveReserve(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return reserveDAO.save(new Reservation(dto.getRes_id(), dto.getDate(), dto.getStudent_id(), dto.getRoom_type_id(), dto.getStatus()));
    }

    @Override
    public boolean updateReserve(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return reserveDAO.update(new Reservation(dto.getRes_id(), dto.getDate(), dto.getStudent_id(), dto.getRoom_type_id(), dto.getStatus()));

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
