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
        ResultSet rst = CrudUtil.executeQuery("SELECT Reserve.res_id,Reserve.student_id,Reserve.room_id,Room.monthly_rent,Reserve.date,Reserve.key_money,(room.monthly_rent-Reserve.key_money) FROM Room INNER JOIN Reserve ON Room.room_id = Reserve.room_id order by res_id");
        ArrayList<Custom> allDetails = new ArrayList<>();
        while (rst.next()) {
            allDetails.add(new Custom(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), LocalDate.parse(rst.getString(5)), rst.getDouble(6), rst.getDouble(7)));
        }
        return allDetails;
    }

    @Override
    public ArrayList<Custom> getDetailsInKRemainKeyMoneySearch(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT Reserve.res_id,Reserve.student_id,Reserve.room_id,Room.monthly_rent,Reserve.date,Reserve.key_money,(room.monthly_rent-Reserve.key_money) FROM Room INNER JOIN Reserve ON Room.room_id = Reserve.room_id where Reserve.student_id=?", id);
        ArrayList<Custom> allDetails = new ArrayList<>();
        while (rst.next()) {
            allDetails.add(new Custom(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), LocalDate.parse(rst.getString(5)), rst.getDouble(6), rst.getDouble(7)));
        }
        return allDetails;
    }
}
