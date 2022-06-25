package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.RoomDAO;
import lk.hostelManagement.pos.entity.Reservation;
import lk.hostelManagement.pos.entity.Room;
import lk.hostelManagement.pos.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
/**
 ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Room");
 ArrayList<Room> allRooms = new ArrayList<>();
 while (rst.next()) {
 allRooms.add(new Room(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
 }
 return allRooms;
 */
        ArrayList<Room> allRoom;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Room");
        allRoom = (ArrayList<Room>) query.list();
        transaction.commit();
        session.close();
        return allRoom;
    }

    @Override
    public boolean save(Room room) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("INSERT INTO Room (room_id,type,key_money,qty) VALUES (?,?,?,?)", room.getRoom_id(), room.getType(), room.getKey_money(), room.getQty());
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room room) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("UPDATE Room SET  type=?,key_money=?,qty=? WHERE room_id=?", room.getType(), room.getKey_money(), room.getQty(), room.getRoom_id());
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("DELETE FROM Room WHERE room_id=?", id);
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room = session.get(Room.class, id);
        session.delete(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room search(String id) throws SQLException, ClassNotFoundException {
/**
 ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Room WHERE room_id=?", id);
 if (rst.next()) {
 return new Room(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4));
 }
 return null;
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room = session.get(Room.class, id);
        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeQuery("SELECT room_id FROM Room WHERE room_id=?", id).next();
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT room_type_id FROM Room WHERE room_type_id=:id");
        String id1 = (String) query.setParameter("id", id).uniqueResult();
        if (id1 != null) {
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
