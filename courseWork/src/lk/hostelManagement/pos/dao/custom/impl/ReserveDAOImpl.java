package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.ReserveDAO;
import lk.hostelManagement.pos.entity.Reservation;
import lk.hostelManagement.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ReserveDAOImpl implements ReserveDAO {
    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Reservation");
        ArrayList<Reservation> allReserves = new ArrayList<>();
        while (rst.next()) {
            allReserves.add(new Reservation(rst.getString(1),LocalDate.parse(rst.getString(2)), rst.getString(3), rst.getString(4),  rst.getString(5)));
        }
        return allReserves;
    }

    @Override
    public boolean save(Reservation reserve) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Reservation (res_id,date,student_id,room_type_id,status) VALUES (?,?,?,?,?)",reserve.getRes_id(),reserve.getDate(),reserve.getStudent_id(),reserve.getRoom_type_id(),reserve.getStatus());
    }

    @Override
    public boolean update(Reservation reserve) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Reservation SET  date=?,room_type_id=?,status=? WHERE res_id=?", reserve.getDate(),reserve.getRoom_type_id(),reserve.getStatus(),reserve.getRes_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Reservation WHERE res_id=?", id);
    }

    @Override
    public Reservation search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Reservation WHERE res_id=?", id);
        if (rst.next()) {
            return new Reservation(rst.getString(1),LocalDate.parse(rst.getString(2)),  rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT res_id FROM Reservation WHERE res_id=?", id).next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC LIMIT 1;");
        return rst.next() ? String.format("REG-%03d", (Integer.parseInt(rst.getString("res_id").replace("REG-", "")) + 1)) : "REG-001";
    }
}
