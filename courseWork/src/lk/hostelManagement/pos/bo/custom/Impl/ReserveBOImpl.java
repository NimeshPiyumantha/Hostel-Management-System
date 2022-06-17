package lk.hostelManagement.pos.bo.custom.Impl;

import lk.hostelManagement.pos.bo.custom.ReserveBO;
import lk.hostelManagement.pos.dao.DAOFactory;
import lk.hostelManagement.pos.dao.custom.ReserveDAO;
import lk.hostelManagement.pos.dto.ReserveDTO;
import lk.hostelManagement.pos.entity.Reserve;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ReserveBOImpl implements ReserveBO {
    private final ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVE);

    @Override
    public ArrayList<ReserveDTO> getAllReserve() throws SQLException, ClassNotFoundException {
        ArrayList<Reserve> all = reserveDAO.getAll();
        ArrayList<ReserveDTO> allReserve = new ArrayList<>();
        for (Reserve reserve : all) {
            allReserve.add(new ReserveDTO(reserve.getRes_id(), reserve.getStudent_id(), reserve.getRoom_id(), reserve.getDate(), reserve.getKey_money()));
        }
        return allReserve;
    }

    @Override
    public boolean saveReserve(ReserveDTO dto) throws SQLException, ClassNotFoundException {
        return reserveDAO.save(new Reserve(dto.getRes_id(), dto.getStudent_id(), dto.getRoom_id(), dto.getDate(), dto.getKey_money()));

    }

    @Override
    public boolean updateReserve(ReserveDTO dto) throws SQLException, ClassNotFoundException {
        return reserveDAO.update(new Reserve(dto.getRes_id(), dto.getStudent_id(), dto.getRoom_id(), dto.getDate(), dto.getKey_money()));

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
