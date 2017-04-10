/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradebook;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author allis
 */
public class StudentAssignmentHelper {

    Session session = null;

    public StudentAssignmentHelper() {

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getStudents() {

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

        return studentList;
    }

    public List getAssignments() {

        List<Assignment> assignmentList = null;

        String sql = "SELECT * FROM assignment";

        try {
            // Initialize transaction if not already initialized
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity(Assignment.class);

            assignmentList = (List<Assignment>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return assignmentList;
    }

    public List<Student> getStudentsFromAssignment(int aid) {
        // Create the list
        List<Student> studentList = null;

        String sql = "SELECT * FROM student "
                + "INNER JOIN student_assignment ON student.student_id = student_assignment.student_id "
                + "WHERE student_assignment.assignment_id = :aid";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Student.class);

            // Binding parameters
            query.setParameter("aid", aid);

            // Execute query
            studentList = (List<Student>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return studentList;

    }

    public int insertStudentToAssignment(int sid, int aid) {
        int result = 0;

        String sql = "INSERT INTO Student_Assignment(STUDENT_ID, ASSIGNMENT_ID) "
                + "VALUES (:sid, :aid)";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(StudentAssignment.class);
            query.setParameter("sid", sid);
            query.setParameter("aid", aid);

            result = query.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public List<Student> getStudentsExcludeAtAid(int aid) {
        // Create the list
        List<Student> studentAssignmentList = null;

        String sql = "SELECT * FROM student "
                + "LEFT JOIN student_assignment ON student.student_id = student_assignment.student_id "
                + "WHERE student_assignment.assignment_id = :aid";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Student.class);

            // Binding parameters
            query.setParameter("aid", aid);

            // Execute query
            studentAssignmentList = (List<Student>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return studentAssignmentList;

    }

    public Assignment getAssignmentAtID(int aid) {

        List<Assignment> assignmentList = null;

        String sql = "SELECT * FROM assignment WHERE ASSIGNMENT_ID = :aid";

        try {
            // Initialize transaction if not already initialized
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity(Assignment.class);

            query.setParameter("aid", aid);

            assignmentList = (List<Assignment>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (assignmentList.size() != 0) {
            return assignmentList.get(0);
        } else {
            return null;
        }
    }

    public List<Student> getStudentsFromGradebook(int gid) {
        // Create the list
        List<Student> studentList = null;

        String sql = "SELECT * FROM student "
                + "INNER JOIN gradebook_student ON student.student_id = gradebook_student.student_id "
                + "WHERE gradebook_student.gradebook_id = :gid";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Student.class);

            // Binding parameters
            query.setParameter("gid", gid);

            // Execute query
            studentList = (List<Student>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return studentList;

    }

}
