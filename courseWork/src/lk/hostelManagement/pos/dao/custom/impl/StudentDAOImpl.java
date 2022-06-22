package lk.hostelManagement.pos.dao.custom.impl;

import lk.hostelManagement.pos.dao.custom.StudentDAO;
import lk.hostelManagement.pos.entity.Student;
import lk.hostelManagement.pos.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
/**
 ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Student");
 ArrayList<Student> allStudents = new ArrayList<>();
 while (rst.next()) {
 allStudents.add(new Student(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6)));
 }
 return allStudents;
 */
        ArrayList<Student> allStudents;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Student ");
        allStudents = (ArrayList<Student>) query.list();
        transaction.commit();
        session.close();
        return allStudents;
    }

    @Override
    public boolean save(Student student) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("INSERT INTO Student (student_id,name,address,contact_no,dob,gender) VALUES (?,?,?,?,?,?)", student.getStudent_id(), student.getName(), student.getAddress(), student.getContact_no(), student.getDob(), student.getGender());
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("UPDATE Student SET  name=?,address=?,contact_no=?,dob=?, gender=? WHERE student_id=?", student.getName(), student.getAddress(), student.getContact_no(), student.getDob(), student.getGender(), student.getStudent_id());
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeUpdate("DELETE FROM Student WHERE student_id=?", id);
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.delete(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
/**
 ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Student WHERE student_id=?", id);
 if (rst.next()) {
 return new Student(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
 }
 return null;
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
/**
 return CrudUtil.executeQuery("SELECT student_id FROM Student WHERE student_id=?", id).next();
 */
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT student_id FROM Student WHERE student_id=:id");
        String id1 = (String) query.setParameter("id", id).uniqueResult();
        if (id1 != null) {
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
