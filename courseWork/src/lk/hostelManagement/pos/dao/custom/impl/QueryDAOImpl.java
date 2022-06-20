package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.QueryDAO;
import lk.hostelManagement.pos.entity.Custom;
import lk.hostelManagement.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<Custom> getDetailsInKRemainKeyMoney() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT Reservation.res_id, Reservation.student_id, Reservation.room_type_id, Reservation.date, Room.key_money, Reservation.status, (room.key_money - Reservation.status) FROM Room INNER JOIN Reservation ON Room.room_id = Reservation.room_type_id order by res_id");
        ArrayList<Custom> allDetails = new ArrayList<>();
        while (rst.next()) {
            allDetails.add(new Custom(rst.getString(1), rst.getString(2), rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5), rst.getString(6), rst.getDouble(7)));
        }
        return allDetails;
    }

    @Override
    public ArrayList<Custom> getDetailsInKRemainKeyMoneySearch(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT Reservation.res_id, Reservation.student_id, Reservation.room_type_id, Reservation.date, Room.key_money,Reservation.status, (room.key_money - Reservation.status) FROM Room INNER JOIN Reservation ON Room.room_id = Reservation.room_type_id where Reservation.student_id=?", id);
        ArrayList<Custom> allDetails = new ArrayList<>();
        while (rst.next()) {
            allDetails.add(new Custom(rst.getString(1), rst.getString(2), rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5), rst.getString(6), rst.getDouble(7)));
        }
        return allDetails;
    }
}
