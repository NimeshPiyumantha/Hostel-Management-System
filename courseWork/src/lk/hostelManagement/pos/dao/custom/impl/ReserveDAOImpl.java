package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.ReserveDAO;
import lk.hostelManagement.pos.entity.Reserve;
import lk.hostelManagement.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class ReserveDAOImpl implements ReserveDAO {
    @Override
    public ArrayList<Reserve> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Reserve");
        ArrayList<Reserve> allReserves = new ArrayList<>();
        while (rst.next()) {
            allReserves.add(new Reserve(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getDouble(5)));
        }
        return allReserves;
    }

    @Override
    public boolean save(Reserve reserve) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Reserve (res_id,student_id,room_id,date,key_money) VALUES (?,?,?,?,?)", reserve.getRes_id(), reserve.getStudent_id(), reserve.getRoom_id(), reserve.getDate(), reserve.getKey_money());

    }

    @Override
    public boolean update(Reserve reserve) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Reserve SET  student_id=?,room_id=?,date=?,key_money=? WHERE res_id=?", reserve.getStudent_id(), reserve.getRoom_id(), reserve.getDate(), reserve.getKey_money(), reserve.getRes_id());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Reserve WHERE res_id=?", id);
    }

    @Override
    public Reserve search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT res_id FROM Reserve WHERE res_id LIKE ?", id);
        if (rst.next()) {
            return new Reserve(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getDouble(5));
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT res_id FROM Reserve WHERE res_id=?", id).next();
    }
}
