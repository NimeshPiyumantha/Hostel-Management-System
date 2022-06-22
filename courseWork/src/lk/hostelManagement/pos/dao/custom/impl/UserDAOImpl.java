package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.UserDAO;
import lk.hostelManagement.pos.entity.Login;
import lk.hostelManagement.pos.entity.Room;
import lk.hostelManagement.pos.entity.Student;
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
public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<Login> getAllUsers() throws SQLException, ClassNotFoundException {
/**
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Login");
        ResultSet rst = stm.executeQuery();
        ArrayList<Login> users = new ArrayList<>();
        while (rst.next()) {
            users.add(new Login(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        return users;
*/

        ArrayList<Login> login;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Login");
        login = (ArrayList<Login>) query.list();
        transaction.commit();
        session.close();
        return login;
    }
}
