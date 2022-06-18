package lk.hostelManagement.pos.bo.custom.Impl;

import lk.hostelManagement.pos.bo.custom.QueryBO;
import lk.hostelManagement.pos.dao.DAOFactory;
import lk.hostelManagement.pos.dao.custom.QueryDAO;
import lk.hostelManagement.pos.dto.CustomDTO;
import lk.hostelManagement.pos.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class QueryBOImpl implements QueryBO {
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Custom);

    @Override
    public ArrayList<CustomDTO> getDetailsInKRemainKeyMoney() throws SQLException, ClassNotFoundException {
        ArrayList<Custom> all = queryDAO.getDetailsInKRemainKeyMoney();
        ArrayList<CustomDTO> allDetails = new ArrayList<>();
        for (Custom custom : all) {
            allDetails.add(new CustomDTO(custom.getRes_id(), custom.getStudent_id(), custom.getRoom_id(), custom.getMonthly_rent(), custom.getDate(), custom.getKey_money(), custom.getArrest_money()));
        }
        return allDetails;
    }

    @Override
    public ArrayList<CustomDTO> getDetailsInKRemainKeyMoneySearch(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Custom> all = queryDAO.getDetailsInKRemainKeyMoneySearch(id);
        ArrayList<CustomDTO> allDetails = new ArrayList<>();
        for (Custom custom : all) {
            allDetails.add(new CustomDTO(custom.getRes_id(), custom.getStudent_id(), custom.getRoom_id(), custom.getMonthly_rent(), custom.getDate(), custom.getKey_money(), custom.getArrest_money()));
        }
        return allDetails;
    }
}
