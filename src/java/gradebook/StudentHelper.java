package gradebook;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author allis
 */
public class StudentHelper {

    Session session = null;

    GradebookStudentHelper gradebookStudentHelper;

    public StudentHelper() {

        gradebookStudentHelper = new GradebookStudentHelper();

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List getStudents() {

        List<Student> studentList = null;

        String sql = "SELECT * FROM student";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity(Student.class);

            studentList = (List<Student>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }

    public int getStudentNumber() {

        List<Student> studentList = null;

        String sql = "select * from student";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Student.class);

            studentList = (List<Student>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList.size();
    }

    public int getStudentID() {
        int studentID = 0;

        String sql = "select * from student where student_id = :id ORDER BY STUDENT_ID DESC LIMIT 1";

        try {

            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Student.class);

            q.setParameter("id", studentID);

            studentID = (int) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentID;
    }

    public int getStudentID(String fName, String lName) {
        
        Student student;
        int studentID = 0;
        
        
        String sql = "SELECT * FROM STUDENT WHERE student_FName = :fname AND student_LName = :lname "
                + "ORDER BY STUDENT_ID DESC LIMIT 1";
        
        try {

            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Student.class);    
            
            q.setParameter("fname", fName);
            q.setParameter("lname", lName);
            
            student = (Student) q.uniqueResult();
            studentID = student.getStudentId();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentID;
    }

    public int insertStudent(String studentFName, String studentLName) {
        int result = 0;

        String sql = "insert into student(student_FName, student_LName)"
                + "values (:studentFName, :studentLName)";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);
            q.addEntity(Student.class);
            q.setParameter("studentFName", studentFName);
            q.setParameter("studentLName", studentLName);

            result = q.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
