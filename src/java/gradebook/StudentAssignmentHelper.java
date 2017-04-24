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

    public List<Student> getStudentsFromAssignment(int gaid) {
        // Create the list
        List<Student> studentList = null;

        String sql = "SELECT * FROM student "
                + "INNER JOIN gradebook_student ON student.student_id = gradebook_student.student_id "
                + "INNER JOIN student_assignment ON student_assignment.STUDENT_ID = gradebook_student.STUDENT_ID "
                + "WHERE student_assignment.GRADEBOOK_ASSIGNMENT_ID = :gaid";

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
            query.setParameter("gaid", gaid);

            // Execute query
            studentList = (List<Student>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return studentList;

    }

    public int insertStudentToAssignment(int sid, int gaid, int grade) {
        int result = 0;

        String sql = "INSERT INTO Student_Assignment (STUDENT_ID, GRADEBOOK_ASSIGNMENT_ID, STUDENT_ASSIGNMENT_GRADE) "
                + "VALUES (:sid, :gaid, :sag)";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(StudentAssignment.class);
            query.setParameter("sid", sid);
            query.setParameter("gaid", gaid);
            query.setParameter("sag", grade);

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

    public List<Student> getStudentsFromGradebookAssignment(int gaid) {
        // Create the list
        List<Student> studentList = null;

        String sql = "SELECT * FROM student "
                + "INNER JOIN gradebook_student ON gradebook_student.STUDENT_ID = student.student_ID "
                + "INNER JOIN gradebook_assignment ON gradebook_student.GRADEBOOK_ID = gradebook_assignment.GRADEBOOK_ID "
                + "INNER JOIN student_assignment ON gradebook_assignment.GRADEBOOK_ASSIGNMENT_ID = student_assignment.GRADEBOOK_ASSIGNMENT_ID "
                + "WHERE student_assignment.GRADEBOOK_ASSIGNMENT_ID = :gaid";

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
            query.setParameter("gaid", gaid);

            // Execute query
            studentList = (List<Student>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return studentList;

    }

    public StudentAssignment getStudentAssignment(int sid, int gaid) {

        List<StudentAssignment> gs = null;

        String sql = "SELECT * FROM Student_Assignment WHERE student_id = :sid AND gradebook_assignment_id = :gaid";

        try {
            if (!this.session.getTransaction().isActive()) {
                this.session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(StudentAssignment.class);

            query.setParameter("sid", sid);
            query.setParameter("gaid", gaid);

            gs = (List<StudentAssignment>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gs.size() != 0) {
            return gs.get(0);
        } else {
            return null;
        }
    }

    public int changeGrade(int said, int grade) {

        int result = 0;

        String sql = "UPDATE Student_Assignment SET Student_Assignment_Grade = :grade WHERE Student_Assignment_ID = :said";

        try {
            if (!this.session.getTransaction().isActive()) {
                this.session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(StudentAssignment.class);

            query.setParameter("grade", grade);
            query.setParameter("said", said);

            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public StudentAssignment getGrade(int said) {
        // Create the list
        List<StudentAssignment> list = null;

        String sql = "SELECT * FROM Student_assignment WHERE STUDENT_ASSIGNMENT_ID = :said";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(StudentAssignment.class);

            // Binding parameters
            query.setParameter("said", said);

            // Execute query
            list = (List<StudentAssignment>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (list.size() != 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public int getGradebookAssignmentId(int gid, int aid) {
        // Create the list
        List<GradebookAssignment> gal = null;

        String sql = "SELECT * FROM gradebook_assignment WHERE GRADEBOOK_ID = :gid AND ASSIGNMENT_ID = :aid";
        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(GradebookAssignment.class);

            // Binding parameters
            query.setParameter("gid", gid);
            query.setParameter("aid", aid);

            // Execute query
            gal = (List<GradebookAssignment>) query.list();
            if (gal.size() != 0) {
                int gaid = gal.get(0).getGradebookAssignmentId();
                return gaid;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return 0;

    }

    public List<StudentAssignment> getStudentAssignmentList(int gaid) {
        // Create the list
        List<StudentAssignment> sal = null;

        String sql = "SELECT * FROM student_assignment WHERE GRADEBOOK_ASSIGNMENT_ID = :gaid";
        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(StudentAssignment.class);

            // Binding parameters
            query.setParameter("gaid", gaid);

            // Execute query
            sal = (List<StudentAssignment>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return sal;

    }

    public List<Student> getStudentsFromGradebook(int gid) {
        // Create the list
        List<Student> studentList = null;

        String sql = "SELECT * FROM student "
                + "INNER JOIN gradebook_student ON gradebook_student.STUDENT_ID = student.student_ID "
                + "WHERE gradebook_student.GRADEBOOK_ID = :gid";

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
