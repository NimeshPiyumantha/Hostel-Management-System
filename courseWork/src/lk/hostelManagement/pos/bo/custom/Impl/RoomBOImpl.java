package lk.hostelManagement.pos.bo.custom.Impl;

import lk.hostelManagement.pos.bo.custom.RoomBO;
import lk.hostelManagement.pos.dao.DAOFactory;
import lk.hostelManagement.pos.dao.custom.RoomDAO;
import lk.hostelManagement.pos.dto.RoomDTO;
import lk.hostelManagement.pos.entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class RoomBOImpl implements RoomBO {
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allSRoom = new ArrayList<>();
        for (Room room : all) {
            allSRoom.add(new RoomDTO(room.getRoom_id(), room.getType(), room.getKey_money(), room.getQty()));
        }
        return allSRoom;
    }

    @Override
    public boolean saveRooms(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return roomDAO.save(new Room(dto.getRoom_id(), dto.getType(), dto.getKey_money(), dto.getQty()));

    }

    @Override
    public boolean updateRooms(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new Room(dto.getRoom_id(), dto.getType(), dto.getKey_money(), dto.getQty()));

    }

    @Override
    public boolean deleteRooms(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(id);

    }

    @Override
    public boolean existRoomsID(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.exist(id);

    }
}
