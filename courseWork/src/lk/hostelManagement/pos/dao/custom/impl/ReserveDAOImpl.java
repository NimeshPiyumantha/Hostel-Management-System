package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.ReserveDAO;
import lk.hostelManagement.pos.entity.Reservation;
import lk.hostelManagement.pos.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ReserveDAOImpl implements ReserveDAO {
    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
/**
 ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Reservation");
 ArrayList<Reservation> allReserves = new ArrayList<>();
 while (rst.next()) {
 allReserves.add(new Reservation(rst.getString(1),LocalDate.parse(rst.getString(2)), rst.getString(3), rst.getString(4),  rst.getString(5)));
 }
 return allReserves;
 */
        ArrayList<Reservation> allReserve = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM Reservation");
        allReserve = (ArrayList<Reservation>) query.list();
        transaction.commit();
        session.close();
        return allReserve;
    }

    @Override
    public boolean save(Reservation reservation) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("INSERT INTO Reservation (res_id,date,student_id,room_type_id,status) VALUES (?,?,?,?,?)",reserve.getRes_id(),reserve.getDate(),reserve.getStudent_id(),reserve.getRoom_type_id(),reserve.getStatus());
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(reservation);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation reservation) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("UPDATE Reservation SET  date=?,room_type_id=?,status=? WHERE res_id=?", reserve.getDate(),reserve.getRoom_type_id(),reserve.getStatus(),reserve.getRes_id());
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(reservation);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("DELETE FROM Reservation WHERE res_id=?", id);
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Reservation reservation = session.get(Reservation.class, id);
        session.delete(reservation);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reservation search(String id) throws SQLException, ClassNotFoundException {
/**
 ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Reservation WHERE res_id=?", id);
 if (rst.next()) {
 return new Reservation(rst.getString(1),LocalDate.parse(rst.getString(2)),  rst.getString(3), rst.getString(4), rst.getString(5));
 }
 return null;
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Reservation reservation = session.get(Reservation.class, id);
        transaction.commit();
        session.close();
        return reservation;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeQuery("SELECT res_id FROM Reservation WHERE res_id=?", id).next();
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT res_id FROM Reservation WHERE res_id=:id");
        String id1 = (String) query.setParameter("id", id).uniqueResult();
        if (id1 != null) {
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
/**
 ResultSet rst = CrudUtil.executeQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC LIMIT 1;");
 return rst.next() ? String.format("REG-%03d", (Integer.parseInt(rst.getString("res_id").replace("REG-", "")) + 1)) : "REG-001";
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC LIMIT 1");
        String s = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        if (s != null) {
            int newStudentId = Integer.parseInt(s.replace("REG-", "")) + 1;
            return String.format("REG-%03d", newStudentId);
        }
        return "REG-001";
    }

    @Override
    public ArrayList<Reservation> getAllReserve(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> allReserveSearch = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT  res_id, date, student_id_student_id, room_type_id_room_type_id,key_money,advance, status FROM Reservation where student_id_student_id =:id");
        allReserveSearch = (ArrayList<Reservation>) query.list();
        transaction.commit();
        session.close();
        return allReserveSearch;
    }
}
