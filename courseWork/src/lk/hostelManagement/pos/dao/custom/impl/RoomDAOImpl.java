package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.RoomDAO;
import lk.hostelManagement.pos.entity.Room;
import lk.hostelManagement.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Room");
        ArrayList<Room> allRooms = new ArrayList<>();
        while (rst.next()) {
            allRooms.add(new Room(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
        }
        return allRooms;
    }

    @Override
    public boolean save(Room room) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Room (room_id,type,key_money,qty) VALUES (?,?,?,?)", room.getRoom_id(), room.getType(), room.getKey_money(), room.getQty());

    }

    @Override
    public boolean update(Room room) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Room SET  type=?,key_money=?,qty=? WHERE room_id=?", room.getType(), room.getKey_money(), room.getQty(), room.getRoom_id());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Room WHERE room_id=?", id);
    }

    @Override
    public Room search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Room WHERE room_id=?", id);
        if (rst.next()) {
            return new Room(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4));
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT room_id FROM Room WHERE room_id=?", id).next();
    }
}
