package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.QueryDAO;
import lk.hostelManagement.pos.dto.CustomDTO;
import lk.hostelManagement.pos.dto.ReservationDTO;
import lk.hostelManagement.pos.entity.CustomEntity;
import lk.hostelManagement.pos.entity.Reservation;
import lk.hostelManagement.pos.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> getDetailsInKRemainKeyMoney() throws SQLException, ClassNotFoundException {
/**
 ResultSet rst = CrudUtil.executeQuery("SELECT Reservation.res_id, Reservation.student_id, Reservation.room_type_id, Reservation.date, Room.key_money, Reservation.status, (room.key_money - Reservation.status) FROM Room INNER JOIN Reservation ON Room.room_id = Reservation.room_type_id order by res_id");
 ArrayList<Custom> allDetails = new ArrayList<>();
 while (rst.next()) {
 allDetails.add(new Custom(rst.getString(1), rst.getString(2), rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5), rst.getString(6), rst.getDouble(7)));
 }
 return allDetails;
 */

        ArrayList<CustomEntity> allDetails = new ArrayList();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT Reservation.res_id, Reservation.student_id_student_id, Reservation.room_type_id_room_id, Room.key_money,  Reservation.date, Reservation.status, " +
                "(room.key_money - Reservation.status) FROM Room INNER JOIN Reservation ON Room.room_id = Reservation.room_type_id_room_id order by res_id");
        allDetails = (ArrayList<CustomEntity>) query.list();
        transaction.commit();
        session.close();
        return allDetails;
    }


    @Override
    public ArrayList<CustomEntity> getDetailsInKRemainKeyMoneySearch(String id) throws SQLException, ClassNotFoundException {
/**        ResultSet rst = CrudUtil.executeQuery("SELECT Reservation.res_id, Reservation.student_id, Reservation.room_type_id, Reservation.date, Room.key_money,Reservation.status, (room.key_money - Reservation.status) FROM Room INNER JOIN Reservation ON Room.room_id = Reservation.room_type_id where Reservation.student_id=?", id);
 ArrayList<Custom> allDetails = new ArrayList<>();
 while (rst.next()) {
 allDetails.add(new Custom(rst.getString(1), rst.getString(2), rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5), rst.getString(6), rst.getDouble(7)));
 }
 return allDetails;
 */

        ArrayList<CustomEntity> allDetailsSearch = new ArrayList();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT Reservation.res_id, Reservation.student_id_student_id, Reservation.room_type_id_room_id, Room.key_money,  Reservation.date, Reservation.status, " +
                "(room.key_money - Reservation.status) FROM Room INNER JOIN Reservation ON Room.room_id = Reservation.room_type_id_room_id where Reservation.student_id_student_id  LIKE '% + id + %' ");
        allDetailsSearch = (ArrayList<CustomEntity>) query.list();
        transaction.commit();
        session.close();
        return allDetailsSearch;
    }
}
