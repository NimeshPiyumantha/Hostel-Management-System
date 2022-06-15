package lk.hostelManagement.pos.dao;

import lk.hostelManagement.pos.dao.custom.impl.ReserveDAOImpl;
import lk.hostelManagement.pos.dao.custom.impl.RoomDAOImpl;
import lk.hostelManagement.pos.dao.custom.impl.StudentDAOImpl;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVE:
                return new ReserveDAOImpl();
            default:
                return null;
        }
    }

    //public final static integer values
    public enum DAOTypes {
        STUDENT, ROOM, RESERVE
    }
}
