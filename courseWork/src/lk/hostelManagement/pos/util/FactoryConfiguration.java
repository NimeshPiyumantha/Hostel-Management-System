package lk.hostelManagement.pos.util;

import lk.hostelManagement.pos.entity.Reservation;
import lk.hostelManagement.pos.entity.Room;
import lk.hostelManagement.pos.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class FactoryConfiguration {
/*    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {

        Configuration config = new Configuration().addAnnotatedClass(Student.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class);
        sessionFactory = config.buildSessionFactory();
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }*/
}

