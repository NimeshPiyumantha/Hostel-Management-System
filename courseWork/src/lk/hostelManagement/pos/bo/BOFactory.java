package lk.hostelManagement.pos.bo;

import lk.hostelManagement.pos.bo.custom.Impl.ReserveBOImpl;
import lk.hostelManagement.pos.bo.custom.Impl.RoomBOImpl;
import lk.hostelManagement.pos.bo.custom.Impl.StudentBOImpl;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVE:
                return new ReserveBOImpl() {
                };
            default:
                return null;
        }
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVE
    }
}
